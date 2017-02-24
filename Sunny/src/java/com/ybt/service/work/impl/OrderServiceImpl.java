package com.ybt.service.work.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;
import com.ybt.common.bean.Result;
import com.ybt.common.constant.MyPingPP;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.StringUtil;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.CrowBillDao;
import com.ybt.dao.work.CrowUserInfoDao;
import com.ybt.dao.work.DmOrderProcessDao;
import com.ybt.dao.work.DmOrderStatusDao;
import com.ybt.dao.work.OrderChangeDao;
import com.ybt.dao.work.OrderDao;
import com.ybt.dao.work.TradeAccountDao;
import com.ybt.dao.work.TradeDBooksDao;
import com.ybt.model.work.CrowBill;
import com.ybt.model.work.CrowPkOne;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunDdChange;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunDdProcess;
import com.ybt.model.work.SunDdStatus;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZhAccount;
import com.ybt.model.work.SunZhDaybook;
import com.ybt.service.aop.IsTryAgain;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.CrowAccountService;
import com.ybt.service.work.CrowBillService;
import com.ybt.service.work.CrowPkOneService;
import com.ybt.service.work.CrowUserInfoService;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.OrderService;
import com.ybt.service.work.SunBlessService;
import com.ybt.service.work.SunCardTradeService;

@Component
@Transactional
public class OrderServiceImpl extends BaseServiceImpl<SunDdOrder, String>  implements OrderService,ApplicationContextAware{
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private DmOrderStatusDao statusDao;
	@Autowired
	private OrderChangeDao changeDao;
	@Autowired
	private DmOrderProcessDao processDao;
	@Autowired
	private TradeAccountDao tradeAccountDao;
	@Autowired
	private TradeDBooksDao tradeDBooksDao;
	@Autowired
	private CrowBillDao crowBillDao;
	@Autowired
	private CrowUserInfoDao crowUserInfoDao;
	@Autowired
	private SunCardTradeService cardTradeService;
	@Autowired
	private CrowUserInfoService crowUserInfoService;
	@Autowired
	@Qualifier("sunBlessService")
	private SunBlessService blessService;
	@Autowired
	private CrowPkOneService crowPkOneService;
	@Autowired
	private IWechatService wechatService;
	
	@Autowired
	private CrowAccountService crowAccountService;
	@Autowired
	private CrowBillService crowBillService;
	
	@Override
	public BaseDao<SunDdOrder, String> getDao() {
		// TODO Auto-generated method stub
		return orderDao;
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
	public SunDdOrder getOrderByOrderNo(String orderNo) {
		return orderDao.getOrderByOrderNo(orderNo);
	}
	/**
	 *@name   根据支付方式、配送方式获取订单流程
	 *@description 相关说明
	 *@time    创建时间:2016年2月26日下午5:24:05
	 *@param payment 支付方式
	 *@param delivery 配送方式
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Override
	public List<SunDdStatus> getLcxx(int payment,int delivery) {
		SunDdProcess process = processDao.getOrderProcess(payment, delivery);
		List<SunDdStatus>status=statusDao.findAll();
		String statusStartId="";
		for(SunDdStatus sta:status){
			if(sta.getDeleted()==0&&process.getId().equals(sta.getProcessId())){
				statusStartId =sta.getId();
				break;
			}
		}
		List<SunDdStatus>returnList = new ArrayList<SunDdStatus>();
		if(StringUtil.notNull(statusStartId)){
		boolean flag =true;
		while (flag) {
			for(SunDdStatus sta:status){
				if(sta.getDeleted()==0&&sta.getId().equals(statusStartId)){
					returnList.add(sta);
					statusStartId=sta.getNextId();
					if(!StringUtil.notNull(sta.getNextId())){
						flag= false;
					}
				}
			}
			
		}
		}
		return returnList;
	}
	
	/**
	 *@name    保存修改订单状态
	 *@description 相关说明
	 *@time    创建时间:2016年2月23日下午3:16:34
	 *@param order 订单
	 *@param openId 操作员ID
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	 @Override
	public SunDdOrder saveOrder(SunDdOrder order,String openId) {
		// 保存订单
		String id = order.getId();
		String oldstatus = null;
		String newstatus = null;
		if (StringUtil.notNull(id)) {
			/*******************************修改订单状态*****************************/
			//如果有变化，只允许变更状态
			order = orderDao.findOne(id);
			oldstatus=order.getStatus();
			newstatus = getNextStadus(order);
		}
		else{
			/*******************************新订单*****************************/
			// 根据流程代码查询首个状态值
			newstatus = getNextStadus(order);
			order.setOrderNo(orderDao.getDDH());
		}
		//保存订单信息
		order.setUpdateTime(new Date());
		order.setStatus(newstatus);
		order = orderDao.save(order);
		// 保存订单状态变更信息
		saveOrderChange(order,oldstatus,openId);
		return order;
	}
	 
	 
	 /**
	 *@name    获取订单下一状态
	 *@description 相关说明
	 *@time    创建时间:2016年7月7日上午8:47:47
	 *@param order
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public String getNextStadus(SunDdOrder order){
		 String oldstatus=order.getStatus();
		
		 if(oldstatus==null||"".equals(oldstatus)){
			 Integer payment = order.getPayment();
			 Integer delivery = order.getDelivery();
			 if ((payment==1||payment==2)&&delivery==0){
				 return "10";
			 }
			 else if (payment==0&&delivery==0){
				 return "20";
			 }else{
				 return "20";
			 }
		 }
		 else if("10".equals(oldstatus)){
			 return "20";
		 }else if("20".equals(oldstatus)){
			 return "100";
		 }else{
			 return "100";
		 }
	 }
	
	@Override
	public SunDdOrder payPingPP(Charge charge) {
		String err = "";
		//charge成功支付，包含订单号-- charge.getPaid() 
//		charge.getPaid() = false  支付失败 TODO ?
		if(charge!=null&&charge.getPaid()&&!charge.getOrderNo().isEmpty()&&charge.getOrderNo().length()>14){
			
			//闻鸡起五业务Map<String, String> metadata  = new HashMap<String, String>();
        
			Map<String,String> metadata=charge.getMetadata();
			if(metadata.containsKey("service_type")){
				String serviceType = metadata.get("service_type");
				if("wjqw".equals(serviceType)){
					String orderNo = charge.getOrderNo().substring(0,15);
					CrowBill  bill =	crowAccountService.findCrowBillByOrderId(orderNo);
					
					// 如果已处理  则跳出
					if(bill==null)return null;
					if(bill.getStatus()!=0)return null;
					if(bill.getStatus()==0){
						
						//减
						if(bill.getType()==0){
							crowUserInfoService.updateUserBalance(bill.getUserId(), "subtract", ""+bill.getDeduct());
						}
						//加
						if(bill.getType()==1){
							//增加用户余额
							crowUserInfoService.updateUserBalance(bill.getUserId(), "add", ""+bill.getActualPayment());
						}
					}
					if(metadata.containsKey("service_type")){
						String payType = metadata.get("pay_type");
						if("yzZhiFu".equals(payType)){   //yzZhiFu:应战支付
							//只有“接受挑战”支付完成以后才走下面的流程
							String yingzhan_openID = bill.getUserId();
							String leizhu_openID = bill.getLeizhu_openID();
							SunWechatUser leizhu = wechatService.findById(leizhu_openID);
							SunWechatUser yingzhan = wechatService.findById(yingzhan_openID);
							//避免微信回调方法和“完成”回调方法创建重复挑战记录，先查一下
							List<CrowPkOne> list = crowPkOneService.isFirstAcceptChallenge(yingzhan_openID, leizhu_openID);
							if(list.size() < 1){
								//记录应战方和擂主方再CrowPkOne表的记录
								crowPkOneService.creatBothrecordsAndSendMessage(yingzhan, leizhu, orderNo);
							}
						}
					}
					
					bill.setTradeId(charge.getTransactionNo());  //记录微信的交易流水号
					bill.setStatus(1);
					bill.setUpdateTime(new Date());
					crowBillService.save(bill);
					System.out.println("==========PingPPWebhooks调用的后台方法===============OrderServiceImpl===================闻鸡起五业务===========");
				}
				
				return null;
			}
			
			//订单号：订单号(15位)+当前时间是第几秒+点击次数
			String orderNo = charge.getOrderNo().substring(0,15);
			SunDdOrder order = orderDao.getOrderByOrderNo(orderNo);
			System.out.println("============orderNo=======orderNo="+orderNo);
			if(order!=null){
				payOrderOnLine(order.getId(), "", order.getBuyerId(), charge.getChannel(), charge.getId());
				return order;
			}else{
				err="charge无法对应订单";
			}
		//如果charge有错误，插入charge信息记录
		}else
			err="charge对象错误";
		//插入charge信息记录--TODO
		System.out.println("============payPingPP=======err="+err);
		return null;
	}
	
	@Override
	public SunDdOrder paySunCard(SunDdOrder order,SunCard card) {
		String err = "";
			if(order!=null){
				payOrderOnLine(order.getId(), "", order.getBuyerId(), "SUNCARD", card.getNumber());
				return order;
			}else{
				err="charge无法对应订单";
			}
		System.out.println("============payPingPP=======err="+err);
		return null;
	}
	/**
	 *@name    支付订单后修改状态，保存支付记录
	 *@description 相关说明
	 *@time    创建时间:2016年2月23日下午3:16:34
	 *@param order 订单
	 *@param accountId 操作员ID
	 *@param bid 操作修改状态的商户id
	 *@param payType ping++支付方式
	 *@param pingppId ping++支付id
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Override
	public SunDdOrder payOrderOnLine(String id,String accountId,String bid,String payType,String pingppId) {
		 System.out.println("**************OrderSer-163"+id);
		// 保存订单
		if (id==null||"".equals(id)) {
			return null;
		}
		
		SunDdOrder order = orderDao.findOne(id);
		String oldstatus = order.getStatus();
		
		//如果当前人不是买方，或者当前状态不是待付款状态，则返回空
		System.out.println("====================oldstatus ==" +bid+"====order.getBuyerId()="+order.getBuyerId());
		if(!"10".equals(oldstatus)){
			return null;
		}
		String newstatus = getNextStadus(order);
		//保存订单信息
		order.setUpdateTime(new Date());
		order.setStatus(newstatus);
		//支付信息
		order.setPayInfoId(pingppId);
		order = orderDao.save(order);
		System.out.println("==========order==="+order.getStatus());
		// 保存订单状态变更信息
		saveOrderChange(order,oldstatus,accountId);
		
		//临时修改 贺卡流程V2
		if(order!=null&&order.getStatus()=="20"){
			SunBless b=blessService.findByOrder(order.getId());
			b.setStatus(1);
			blessService.save(b);
			
		}
		
		//保存支付记录 TODO
		System.out.println("==================//保存支付记录 TODO===============");
		/*OrderService orderSer = (OrderService) applicationContext.getBean("orderService");*/
		saveTradeForOrder(order, payType, pingppId, "0");
		return order;
	}
	 /**
	 *@name    保存订单状态变更表
	 *@description 相关说明
	 *@time    创建时间:2016年2月23日下午3:18:26
	 *@param order
	 *@param oldstatus
	 *@param accountId
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	private void saveOrderChange(SunDdOrder order,String oldstatus,String accountId) {
		 SunDdChange change = new SunDdChange();
		 change.setOrderId(order.getId());
		 if(oldstatus!=null) change.setBeforeStatus(oldstatus);
		 change.setWorkerId(accountId);//从session中获取当前 操作员ID
		 change.setAfterStatus(order.getStatus());
		 changeDao.save(change);
	}
	
	/**
	 *@name    保存账户信息--付款流程
	 *@description 相关说明
	 *@time    创建时间:2016年3月9日下午1:56:03
	 *@param order
	 *@param payType
	 *@param pingppId
	 *@param paymentSccount 支付账户（0其他账户，1芸备胎账户 ）
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Override
	@IsTryAgain
	public synchronized int saveTradeForOrder(SunDdOrder order,String payType,String pingppId,String paymentSccount){

		Date nowTime = new Date();
		String ybtPayType ="0";//芸备胎账户操作类型(0转入,1转出)
		String orderId = order.getId(); //orderId 订单ID
		String buyerId = order.getBuyerId();//buyserId 金额转出方ID
		String sellerId = order.getSellerId();
		Double money = order.getPayTotalPrice();
		SunZhDaybook book = new SunZhDaybook();//芸备胎账户流水信息
		DecimalFormat df =new DecimalFormat("#.00");
		
		//*******************************-----保存芸备胎官方转账记录-----**************************************
		//不是芸备胎支付的，记录资金入账记录
		if(("0".equals(paymentSccount)||!StringUtil.notNull(paymentSccount))&&StringUtil.notNull(pingppId)){
			SunZhAccount tradeAccount = tradeAccountDao.findOne(MyPingPP.YBT_ACCOUNT_ID);
			int tradeAccountVersion = tradeAccount.getVersion(); //乐观锁
			book.setOrderId(orderId);
			book.setBuyerId(buyerId);
			book.setSellerId(sellerId);
			book.setMoney(money);
			book.setOriginalMoney(tradeAccount.getTotalMoney());
			book.setPayType(payType);
			book.setPingppId(pingppId);
			book.setStatus("1");//	状态（0已提交，1成功到账，2到账失败）
			book.setType(ybtPayType);
			book.setCreateTime(nowTime);
			tradeDBooksDao.save(book);
	
			//修改芸备胎账户信息（支付金额加到总金额&到账金额上）
			tradeAccount.setTotalMoney(new Double(df.format(tradeAccount.getTotalMoney()+money)));
			if(payType.equals(MyPingPP.SUN_CARD)){
				Double d=tradeAccount.getCardMoney();
				if(d==null)
					d=0.00;
				tradeAccount.setCardMoney(new Double(df.format(d+money)));//支付已到账
				
				cardTradeService.saveOrderCardTrade(order,pingppId);
			}else{
				tradeAccount.setMoney(new Double(df.format(tradeAccount.getMoney()+money)));//支付已到账
			}
			tradeAccount.setUpdateTime(nowTime);
			tradeAccount.setVersion(tradeAccountVersion+1);
			tradeAccount = tradeAccountDao.save(tradeAccount);
			
		}
		return 1;//保存成功
	}
	

	/**
	 *@name   分页加载收到贺卡信息
	 *@description 相关说明
	 *@time    创建时间:2016年6月26日下午4:32:35
	 *@param filters
	 *@param pageable
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Override
	public Page<SunDdOrder> findOrderGetByOpenId(final List<PropertyFilter> filters,Pageable pageable) {
		Page<SunDdOrder>  page = orderDao.findAll(
		new Specification<SunDdOrder>(){
			@SuppressWarnings("incomplete-switch")
			public Predicate toPredicate(Root<SunDdOrder> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				 List<Predicate> list = new ArrayList<Predicate>();  
				 	for(PropertyFilter pf:filters){
				 		switch (pf.getMatchType()) {
						case EQ:
						    list.add(cb.equal(root.get(pf.getPropertyName()).as(pf.getPropertyClass()), pf.getMatchValue()));  
							break;
						case LIKE:
							list.add(cb.like(root.get(pf.getPropertyName()).as(String.class), "%"+pf.getMatchValue()+"%")); 
							break;
						}
				 	}

					list.add(cb.equal(root.get("status").as(String.class), "100")); 
				    list.add(cb.equal(root.get("deleted").as(String.class), "0")); 
				    Predicate[] p = new Predicate[list.size()];  
				   // return cb.and(list.toArray(p));  
					query.where(cb.and(list.toArray(p)));
					return query.getRestriction();
			}}, (Pageable) new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),new Sort(Direction.DESC, new String[] { "createTime" })));
		return page;
	}
	

	/**
	 *@name    分页加载送出贺卡信息
	 *@description 相关说明
	 *@time    创建时间:2016年6月26日下午4:32:17
	 *@param filters
	 *@param pageable
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Override
	public Page<SunDdOrder> findOrderSendByOpenId(final List<PropertyFilter> filters,Pageable pageable) {
		Page<SunDdOrder>  page = orderDao.findAll(
		new Specification<SunDdOrder>(){
			public Predicate toPredicate(Root<SunDdOrder> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				 List<Predicate> list = new ArrayList<Predicate>();  
				 	for(PropertyFilter pf:filters){
				 		switch (pf.getMatchType()) {
				 		case EQ: // 等于运算符
							list.add(cb.equal(root.get(pf.getPropertyName()).as(pf.getPropertyClass()),pf.getMatchValue()));
							break;
						case LIKE:// 模糊运算符
							list.add(cb.like(root.get(pf.getPropertyName()).as(String.class),"%" + pf.getMatchValue() + "%"));
							break;
						case LT:// 小于
							list.add(cb.lessThan(root.get(pf.getPropertyName()).as(String.class), pf.getMatchValue().toString()));
							break;
						case LE:// 小于等于
							list.add(cb.lessThanOrEqualTo(root.get(pf.getPropertyName()).as(String.class), pf.getMatchValue().toString()));
							break;
						case GT:// 大于
							list.add(cb.greaterThan(root.get(pf.getPropertyName()).as(String.class),((String) pf.getMatchValue())));
							break;
						case GE:// 大于等于
							list.add(cb.greaterThanOrEqualTo(root.get(pf.getPropertyName()).as(String.class),((String) pf.getMatchValue())));
							break;
						case IN:// in
							list.add(cb.in(root.get(pf.getPropertyName())).value(pf.getMatchValue()));
							break;
						case NULL:// null
							list.add(cb.isNull(root.get(pf.getPropertyName()).as(pf.getPropertyClass())));
							break;
						case NOTNULL:// not null
							list.add(cb.isNotNull(root.get(pf.getPropertyName()).as(pf.getPropertyClass())));
							break;
						}
				 	}
				 	list.add(cb.equal(root.get("status").as(String.class), "100")); 
				    list.add(cb.equal(root.get("deleted").as(String.class), "0")); 
				    Predicate[] p = new Predicate[list.size()];  
				   // return cb.and(list.toArray(p));  
					query.where(cb.and(list.toArray(p)));
					return query.getRestriction();

			}}, (Pageable) new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),new Sort(Direction.DESC, new String[] { "createTime" })));
		return page;
	}
	@SuppressWarnings("serial")
	@Override
	public com.ybt.common.util.Page<SunDdOrder> findOrderSendByOpenId(final String openid, com.ybt.common.util.Page<SunDdOrder> page) throws Exception {
		
		 orderDao.findByHqlName(page, "getMyList1", new HashMap<String,Object>(){
			{
					{
						this.put("SENDEEID", openid);
						//this.put("STATUS", new String[]{"20","100"});
						//this.put("STATUS","\"20\",\"100\"");
					}
			}
		});
		 
		 return page;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ybt.service.work.OrderService#findOrderbyerByOpenId(java.lang.String, com.ybt.common.util.Page)
	 */
	@SuppressWarnings("serial")
	@Override
	public com.ybt.common.util.Page<SunDdOrder> findOrderbyerByOpenId(final String openid, com.ybt.common.util.Page<SunDdOrder> page) throws Exception {
		
		 orderDao.findByHqlName(page, "getMyList2", new HashMap<String,Object>(){
			{
					{
						this.put("BUYERID", openid);
						//this.put("STATUS","\"20\",\"100\"");
					}
			}
		});
		 
		 return page;
	}
	@Override
	public com.ybt.common.util.Page<SunDdOrder> getMyOrderList(String openid, com.ybt.common.util.Page<SunDdOrder> page) {
		// TODO Auto-generated method stub
		page.setResult(orderDao.getMyOrderList(openid, page.getPageSize()*(page.getPageNo()-1), page.getPageSize()));
		return page;
	}
	@Override
	public com.ybt.common.util.Page<SunDdOrder> getMyOrderListByState(String openid,String state,
			com.ybt.common.util.Page<SunDdOrder> page) {
		page.setResult(orderDao.getMyOrderListByState(openid, state,  page.getPageSize()*(page.getPageNo()-1), page.getPageSize()));
		return page;
	}
	@Override
	public Transfer EntPayToPerPingPP(Map<String, Object> paramMap) throws Exception{
		
		Pingpp.apiKey = MyPingPP.Ping_ApiKey_Live;
		String userId =  (String) paramMap.get("userOpenId");
		String nowBalance = (String) paramMap.get("nowBalance");
		String withdrawCash = (String) paramMap.get("withdrawCash");
		String orderId = crowAccountService.getNewOrder(); //提前生成；
		String tradeId = "";
		
		//修改用户余额
		CrowUserInfo userInfo = crowUserInfoService.findByUserID(userId);
		userInfo.setBalance(new BigDecimal(nowBalance));
		crowUserInfoDao.save(userInfo);
		
		//创建用户提现流水数据 
		CrowBill cb = new CrowBill();
		cb.setBalance(new BigDecimal(nowBalance));
		cb.setCurrency("CNY");
		cb.setUserId(userId);
		cb.setMoney(new BigDecimal(withdrawCash));
		cb.setOrderId(orderId);
		cb.setPayType("微信提现");
		cb.setStatus(3);//3:平台已扣款
		cb.setTradeId(tradeId);
		cb.setType(0);//0 支出 1收入
		cb.setInstruction("提现");
		Transfer transfer = null;
		try{
			CrowBill save = crowBillDao.save(cb);
			//String orderNo = save.getId();
			System.out.println("orderId===" + orderId);
			Map<String, Object> transferMap = new HashMap<String, Object>();
			transferMap.put("channel", "wx_pub");//此处 wx_pub 为公众平台的支付
			transferMap.put("order_no", orderId);
			transferMap.put("amount", (int)(new Double(withdrawCash)*100));//订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
			transferMap.put("type", "b2c");
			transferMap.put("currency", "cny");
			transferMap.put("recipient", paramMap.get("userOpenId"));//企业付款给指定用户的 open_id
			transferMap.put("description", paramMap.get("description"));
			Map<String, String> app = new HashMap<String, String>();
			app.put("id", MyPingPP.Ping_AppId);
			transferMap.put("app", app);
			/*Map<String, Object> extra = new HashMap<String, Object>();
			extra.put("user_name", null);
			extra.put("force_check", false);
			transferMap.put("extra", extra);*/
			transfer = Transfer.create(transferMap);
			System.out.println("transfer====>:"+ transfer);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return transfer;
	}


}
