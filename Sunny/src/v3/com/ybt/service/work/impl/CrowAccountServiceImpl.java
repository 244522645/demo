package com.ybt.service.work.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.SystemConstant;
import com.ybt.common.constant.Wechat;
import com.ybt.common.util.CommonUtil;
import com.ybt.common.util.DateUtil;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.CrowBillDao;
import com.ybt.dao.work.CrowUserInfoDao;
import com.ybt.model.work.CrowBill;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.CrowAccountService;
import com.ybt.service.work.CrowBillService;
import com.ybt.service.work.CrowUserInfoService;

import wechat.api.MessageAPI;
import wechat.bean.templatemessage.TemplateMessage;
import wechat.bean.templatemessage.TemplateMessageItem;
import wechat.support.TokenManager;
import wechat.util.WXUtil;

@Component
public class CrowAccountServiceImpl extends BaseServiceImpl<CrowUserInfo,String> implements CrowAccountService{
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private HashMap constant;
	@Autowired
	private CrowUserInfoDao crowUserInfoDao;
	@Autowired
	private CrowUserInfoService crowUserInfoService;
	@Autowired
	private CrowBillDao crowBillDao;
	@Autowired
	private CrowBillService crowBillService;
	public BaseDao<CrowUserInfo, String> getDao() {
		return crowUserInfoDao;
	}


	@Override
	public CrowUserInfo findCrowUserBalance(String userid) {
		// TODO Auto-generated method stub
		return crowUserInfoService.findByUserID(userid);
	}


	@Override
	public double findCrowAllUserBalance() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Result<BigDecimal> minusBalance(String title, Double deduct, String userid) {
		// TODO Auto-generated method stub
		CrowUserInfo info =	findCrowUserBalance(userid);
		if(info == null)
			new  Result<Double>("余额不足",0.00); 
		int compare = CommonUtil.compare(deduct+"", info.getBalance().toString());
		if(compare == 1)
			new  Result<Double>("余额不足",0.00); 
		//禁止提现抵扣
		int ban=0;
		if(ban==1)
			new  Result<Double>("余额不足",0.00); 
		
		info.setBalance(CommonUtil.subtract(info.getBalance(), new BigDecimal(deduct)));
		info.setUpdateTime(new Date());
		crowUserInfoDao.save(info);
		
		CrowBill cb = new CrowBill();
		cb.setCurrency("CNY");
		cb.setUserId(userid);
		cb.setActualPayment(new BigDecimal(deduct));
		cb.setMoney(new BigDecimal(deduct));
		cb.setDeduct(new BigDecimal(0));
		cb.setOrderId(crowBillDao.getNewOrderNo());
		cb.setPayType("余额抵扣");
		cb.setStatus(1);//申请中
		cb.setTradeId("");
		cb.setType(0);
		cb.setInstruction(title);
		cb.setCreateTime(new Date());
		cb.setUpdateTime(new Date());
		crowBillDao.save(cb);
		
		return new  Result<BigDecimal>("",info.getBalance()); 
	}

	@Override
	public Result<BigDecimal> addBalance(String title,Double money, String userid) {
		// TODO Auto-generated method stub
				CrowUserInfo info =	findCrowUserBalance(userid);
				if(info == null)
					new  Result<Double>("余额不足",0.00); 
				/*int compare = CommonUtil.compare(money.toString(), info.getBalance().toString());
				if(compare == 1)
					new  Result<Double>("余额不足",0.00); */
				if(money==0){
					new  Result<Double>("余额不足",0.00); 
				}
				info.setBalance(new BigDecimal(info.getBalance().doubleValue()+money));
				info.setUpdateTime(new Date());
				crowUserInfoDao.save(info);
				// 创建回复
				LinkedHashMap<String, TemplateMessageItem>   map2= new LinkedHashMap<String, TemplateMessageItem>();
				map2.put("first", new TemplateMessageItem(title+" \n","#ff6200"));
				map2.put("date", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
				map2.put("type", new TemplateMessageItem("","#000000"));
				map2.put("adCharge", new TemplateMessageItem("+"+money,"#000000"));
				map2.put("remark", new TemplateMessageItem("点击\"详情\" 前往我的账户。 ","#000000"));
				TemplateMessage tm2= new TemplateMessage();
				tm2.setTemplate_id(SystemConstant.TEMPLATE_ZHANGHU);
				tm2.setTouser(userid);
				tm2.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/myAccount", false));
				tm2.setData(map2);
				MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm2);
	
				CrowBill cb = new CrowBill();
				cb.setCurrency("CNY");
				cb.setUserId(userid);
				cb.setActualPayment(new BigDecimal(money));
				cb.setMoney(new BigDecimal(money));
				cb.setDeduct(new BigDecimal(0));
				cb.setOrderId(crowBillDao.getNewOrderNo());
				cb.setPayType("挑战金");
				cb.setStatus(1);//申请中
				cb.setTradeId("");
				cb.setType(1);
				cb.setInstruction("个人挑战金奖励");
				cb.setCreateTime(new Date());
				cb.setUpdateTime(new Date());
				crowBillDao.save(cb);
				return new  Result<BigDecimal>("",info.getBalance()); 
	}


	@Override
	public Result<CrowBill> applyBill(int type,Double actualPayment, Double deduct, String title, String userid, String otherUserId) {
		
		/*Result<BigDecimal> rr =minusBalance("",deduct,userid);
		if (rr.getState()==0) {
			new  Result<CrowBill>("余额不足",null); 
		}*/
		
		String orderNo =getNewOrder(); //提前生成；
		CrowBill cb = new CrowBill();
		cb.setCurrency("CNY");
		cb.setUserId(userid);
		cb.setLeizhu_openID(otherUserId);   //擂主ID
		cb.setActualPayment(new BigDecimal(actualPayment));
		cb.setMoney(new BigDecimal(actualPayment+deduct));
		cb.setDeduct(new BigDecimal(deduct));
		cb.setOrderId(orderNo);
		if(actualPayment == 0){
			cb.setPayType("余额抵扣");
		}else{
			cb.setPayType("微信支付");
		}
		cb.setStatus(0);//交易状态(0申请中,1成功,2：失败,3:平台已扣款）
		cb.setTradeId("");
		cb.setType(type); //0 支出 1收入
		cb.setInstruction(title);
		cb.setCreateTime(new Date());
		cb.setUpdateTime(new Date());
		CrowBill save = crowBillDao.save(cb);
		
		return new  Result<CrowBill> ("",save);
	}


	@Override
	public String getNewOrder() {
		// TODO Auto-generated method stub
		return crowBillDao.getNewOrderNo();
	}


	@Override
	public CrowBill findCrowBillByOrderId(String orderId) {
		return crowBillDao.findByOrderId(orderId);
	}


}
