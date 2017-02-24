package com.ybt.service.work.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import wechat.api.MessageAPI;
import wechat.bean.message.TextMessage;
import wechat.support.TokenManager;
import wechat.util.WXUtil;

import com.pingplusplus.model.Transfer;
import com.ybt.common.constant.MyPingPP;
import com.ybt.common.constant.Wechat;
import com.ybt.common.uitl.SendWeixinMessage;
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.Page;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.CrowBillDao;
import com.ybt.dao.work.TradeAccountDao;
import com.ybt.dao.work.TradeDBooksDao;
import com.ybt.model.work.CrowBill;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.SunZhAccount;
import com.ybt.model.work.SunZhDaybook;
import com.ybt.service.aop.IsTryAgain;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.CrowAccountService;
import com.ybt.service.work.CrowBillService;
import com.ybt.service.work.CrowUserInfoService;
import com.ybt.service.work.TradeAccountService;

@Component
@Transactional
public class TradeAccountServiceImpl extends
		BaseServiceImpl<SunZhAccount, String> implements TradeAccountService,ApplicationContextAware  {

	private Logger logger = Logger.getLogger(getClass());
	
	private static final String String = null;
	@Autowired
	private TradeAccountDao tradeAccountDao;//芸备胎账户信息
	@Autowired
	private TradeDBooksDao tradeDaybookDao;//芸备胎账户流水记录
	@Autowired
	private CrowBillDao crowBilldao;
	@Autowired
	private CrowUserInfoService crowUserInfoService;
	@Autowired
	private CrowAccountService crowAccountService;
	@SuppressWarnings("rawtypes")
	@Autowired
	public HashMap constant;

	@Override
	public BaseDao<SunZhAccount, String> getDao() {

		return tradeAccountDao;
	}
	
	
	 // Spring应用上下文环境  
    private static ApplicationContext applicationContext;  
    /** 
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境 
     *  
     * @param applicationContext 
     */  
    public void setApplicationContext(ApplicationContext a) {  
        applicationContext = a;  
    }  
    /** 
     * @return ApplicationContext 
     */  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
    /** 
     * 获取对象 
     *  
     * @param name 
     * @return Object
     * @throws BeansException 
     */  
    public static Object getBean(String name) throws BeansException {  
        return applicationContext.getBean("tradeAccountSer");  
    }

    @Override
	@IsTryAgain
	public void saveTransfer(String bid, Transfer transfer,boolean isYpay) {
    	//a,插入商户账户流水记录，b,减商户可提现金额＆总金额，c,芸备胎官方出账记录(非芸备胎余额支付),d,减 芸备胎总额＆总金额(非芸备胎余额支付)
    	Date date = new Date();
		String ybtPayType ="1";//交易类型(0转入,1转出，2芸备胎补贴,3芸备胎提现到银行卡，4手续费)
		Double money = (transfer.getAmount()+0.0d)/100;
		String pingPPId = transfer.getTransaction_no();
		String status="0"; //状态（0交易中，1成功,2失败）
		String payType = isYpay?MyPingPP.YBT_PAY:transfer.getChannel();//ping++ 或ybt_pay
		SunZhDaybook book = new SunZhDaybook();//芸备胎账户流水信息
		SunZhAccount  tradeAccount = new SunZhAccount();//芸备胎账户

		//付款状态。目前支持 3 种状态（pending: 处理中; paid: 付款成功; failed: 付款失败）。
		if(transfer.getStatus()!=null&&"pending".equals(transfer.getStatus())){
			status = "0";
		}else if(transfer.getStatus()!=null&&"paid".equals(transfer.getStatus())){
			status = "1";
		}else{
			status = "2";
		}
		DecimalFormat df =new DecimalFormat("#.00");

		//c,芸备胎官方出账记录(非芸备胎余额支付),
		if(!isYpay){
			book.setMoney(money);
			book.setSellerId(bid);//收款方ID
			book.setOriginalMoney(tradeAccount.getTotalMoney());
			book.setPayType(payType);
			book.setPingppId(pingPPId);
			book.setStatus(status);
			book.setType(ybtPayType);
			book.setCreateTime(date);
			tradeDaybookDao.save(book);
			//	d,减 芸备胎总额(非芸备胎余额支付)
			tradeAccount = tradeAccountDao.findOne(MyPingPP.YBT_ACCOUNT_ID);
			tradeAccount.setTotalMoney(new Double(df.format(tradeAccount.getTotalMoney()-money)));
			tradeAccount.setMoney(new Double(df.format(tradeAccount.getMoney()-money)));
			tradeAccount.setUpdateTime(date);
			tradeAccount.setVersion(tradeAccount.getVersion()+1);//乐观锁
			tradeAccount = tradeAccountDao.save(tradeAccount);
		}
		
	}
    
    @Override
	@IsTryAgain
	public void transferSuccess(Transfer transfer) {
    	
    	//webhooks接受提现到账推送后，成功：a,修改商户流水记录-成功(使用交易流水号transaction_no)，b，修改芸备胎官方出账记录-成功(使用交易流水号transaction_no) c,短信通知到账成功，
	    							//失败:a,修改商户流水记录-失败，b，修改芸备胎官方出账记录-失败 c,增加商户可提现金额  ＆　总金额，d， 增加芸备胎总额  ＆　总金额   e,短信通知到账失败，
    	//付款状态。目前支持 3 种状态（pending: 处理中; paid: 付款成功; failed: 付款失败）。
    	Date date = new Date();
    	DecimalFormat df =new DecimalFormat("#.00");
    	Double money = (transfer.getAmount()+0.0d)/100;//元
		String pingPPId = transfer.getTransaction_no();
    	if(transfer!=null&&"paid".equals(transfer.getStatus())){//付款成功
			//	b，修改芸备胎官方出账记录-成功(使用交易流水号transaction_no) 
			SunZhDaybook book = tradeDaybookDao.getBookByPidAndType(pingPPId,"1");//	交易类型(0转入,1转出)
			book.setUpdateTime(date);
			book.setStatus("2"); //	状态（1已提交，2成功到账，3到账失败）
			tradeDaybookDao.save(book);
			//c,短信通知到账成功， TODO
			
		}
    	if(transfer!=null&&"paid".equals(transfer.getStatus())){//付款失败
    		SunZhAccount  tradeAccount = new SunZhAccount();//芸备胎账户
    	
			//b，修改芸备胎官方出账记录-失败 
			SunZhDaybook book = tradeDaybookDao.getBookByPidAndType(pingPPId,"1");//	交易类型(0转入,1转出)
			book.setUpdateTime(date);
			book.setStatus("3"); //	状态（1已提交，2成功到账，3到账失败）
			tradeDaybookDao.save(book);
			
			//d， 增加芸备胎总额  ＆　总金额
			tradeAccount = tradeAccountDao.findOne(MyPingPP.YBT_ACCOUNT_ID);
			tradeAccount.setTotalMoney(new Double(df.format(tradeAccount.getTotalMoney()+money)));
			tradeAccount.setMoney(new Double(df.format(tradeAccount.getMoney()+money)));
			tradeAccount.setUpdateTime(date);
			tradeAccount.setVersion(tradeAccount.getVersion()+1);//乐观锁
			tradeAccount = tradeAccountDao.save(tradeAccount);
			//e, 短信通知到账失败，
			
		}
	}

	@Override
	public void ybtTixian(SunZhDaybook book, Double handerMoney) {
//		 a, 芸备胎总金额减 -可提现金额减
//         b, 流水--提款（银行流水号）
//         c, 手续费减总账，可提现
//         d,手续费--流水（银行流水号）
		DecimalFormat df =new DecimalFormat("#.00");
		String sellerId = book.getSellerId();//进账id
		String buyerId = MyPingPP.YBT_ACCOUNT_ID;//出账id-芸备胎
		String payType = MyPingPP.YBT_PAY;//出账id-芸备胎
		String pingppId = book.getPingppId();
		Double money = book.getMoney();
		Date date = new Date();
		String status="1"; //状态（0交易中，1成功,2失败）
		SunZhAccount  tradeAccount = new SunZhAccount();//芸备胎账户
		//a, 芸备胎总金额减 -可提现金额减
		tradeAccount = tradeAccountDao.findOne(MyPingPP.YBT_ACCOUNT_ID);
		tradeAccount.setTotalMoney(new Double(df.format(tradeAccount.getTotalMoney()-money)));
		tradeAccount.setMoney(new Double(df.format(tradeAccount.getMoney()-money)));
		//b, 流水--提款（银行流水号）
		book.setMoney(money);
		book.setSellerId(sellerId);//收款方ID
		book.setBuyerId(buyerId);
		book.setOriginalMoney(tradeAccount.getMoney());
		book.setPayType(payType);
		book.setPingppId(pingppId);
		book.setStatus(status);
		book.setType("3");//3芸备胎提现到银行卡)
		book.setCreateTime(date);
		tradeDaybookDao.save(book);
		//c, 手续费减总账，可提现
		tradeAccount.setTotalMoney(new Double(df.format(tradeAccount.getTotalMoney()-handerMoney)));
		tradeAccount.setMoney(new Double(df.format(tradeAccount.getMoney()-handerMoney)));
		tradeAccount.setUpdateTime(date);
		tradeAccount.setVersion(tradeAccount.getVersion()+1);//乐观锁
		tradeAccount = tradeAccountDao.save(tradeAccount);
		// d,手续费--流水（银行流水号）  
		book = new SunZhDaybook();
		book.setMoney(handerMoney);//手续费
		book.setSellerId(sellerId);//收款方ID
		book.setBuyerId(buyerId);
		book.setOriginalMoney(tradeAccount.getMoney());
		book.setPayType(payType);
		book.setPingppId(pingppId);
		book.setStatus(status);
		book.setType("4");//4手续费
		book.setCreateTime(date);
		tradeDaybookDao.save(book);
	}
	
	@Override
	public List<SunZhDaybook> queryDaybook(Page<T> page) {
		return tradeDaybookDao.queryDaybook();
	}
	@Override
	public int queryDaybookCount(Page<T> page) {
		return (int) tradeDaybookDao.queryDaybookCount();
	}
	@Override
	public void wjqwTixian(String event) {
		//webhooks接受提现到账推送后，目前支持 3 种状态（pending: 处理中; paid: 付款成功; failed: 付款失败）。
		System.out.println("【【【=====wjqwTixian的event：=======》"+event+"========】】】");
		logger.error("【【【=====wjqwTixian的event：=======》"+event+"========】】】");
		JSONObject jsonObject = JSONObject.fromObject(event).getJSONObject("data").getJSONObject("object");
		String status = jsonObject.getString("status");
		Double amount = jsonObject.getDouble("amount");
		String userId = jsonObject.getString("recipient");
		String orderId = jsonObject.getString("order_no");
		String failure_msg = jsonObject.getString("failure_msg");
		if(jsonObject!=null&&"paid".equals(status)){//付款成功
			System.out.println("==================付款成功==============");
			CrowBill cb = crowBilldao.findByOrderId(orderId);
			if(null != cb){
				cb.setStatus(1); //成功
				crowBilldao.save(cb);
				StringBuffer sbstr=new StringBuffer("");
				BigDecimal amount_bd = new BigDecimal(amount).divide(new BigDecimal("100"));
				
				SendWeixinMessage.sendMessage(
						" 通知",
						"恭喜您，提现成功！\n 提现金额为: "+amount_bd.toString()+" 元",
						"提现", DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm"), "查看账户详情",
						WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/myAccount",  false), userId);
				
			}else{
				logger.error("========orderId不能够查出crowBill对象=================");
			}
			
		}
		if(jsonObject!=null&&"failed".equals(status)){//付款失败
			System.out.println("==================付款失败==============");
			CrowBill cb = crowBilldao.findByOrderId(orderId);
			if(null != cb){
				try{
					cb.setStatus(2); //失败
					crowBilldao.save(cb);
					
					//需要将用户提现的金额返还到余额 中，即： balance + money
					CrowUserInfo userInfo = crowUserInfoService.findByUserID(userId);
					BigDecimal newBalance = userInfo.getBalance().add(cb.getMoney());
					crowUserInfoService.save(userInfo);
					//创建流水数据 
					String newOrderId = crowAccountService.getNewOrder(); //提前生成；
					CrowBill cb2 = new CrowBill();
					cb2.setBalance(newBalance);
					cb2.setCurrency("CNY");
					cb2.setUserId(userId);
					cb2.setMoney(cb.getMoney());
					cb2.setOrderId(newOrderId);
					cb2.setPayType("平台返还");
					cb2.setStatus(1);//3:平台已扣款
					cb2.setType(1);//0 支出 1收入
					cb2.setInstruction("提现失败后平台返还用户的提现金额");
				}catch(Exception e){
					logger.error("异常信息",e);
				}
				
			}else{
				logger.error("========orderId不能够查出crowBill对象=================");
			}
			logger.error("失败原因："+failure_msg);
			
			SendWeixinMessage.sendMessage(
					"通知",
					"十分抱歉，提现失败！我们已将您的提现金额返还到您的余额中，如有疑问请联系管理员。",
					"提现", DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm"), "查看账户详情",
					WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/myAccount",  false), userId);
			
			StringBuffer sbstr=new StringBuffer("");
			sbstr.append("提现失败,失败原因:"+failure_msg+"");
			MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), new TextMessage(userId,sbstr.toString()));
			
			
		}
		System.out.println("微信异步通知之行结束+++++++++++++++++++++++++=");
	}

}
