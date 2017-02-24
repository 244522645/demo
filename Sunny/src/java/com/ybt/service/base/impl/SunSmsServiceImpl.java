package com.ybt.service.base.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.VerCodeBean;
import com.ybt.common.constant.Juhe;
import com.ybt.common.util.RandomCode;
import com.ybt.common.util.SMSUtil;
import com.ybt.common.util.StringUtil;
import com.ybt.dao.work.SmsDao;
import com.ybt.model.work.SunDdGood;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunGySms;
import com.ybt.service.base.SunSmsService;

@Component
public class SunSmsServiceImpl implements SunSmsService{
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private SmsDao smsDao;
	
/*	@Autowired
	private AccountDao accountDao;
	@Autowired
	private BusinessDao businessDao;*/
	public SunGySms saveSms(SunGySms sms) {
		smsDao.save(sms);
		return  sms;
	}

	/**
	 *@name    校验验证码是否正确
	 *@description 相关说明
	 *@time    创建时间:2015年8月20日下午4:18:07
	 *@param phone 手机号
	 *@param code 验证码
	 *@param time 有效时间
	 *@param type 验证码里类型
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public SunGySms getSmsByPhoneAndCodeAndCreateTime(String phone, String code, Date time,String type) {
		List<SunGySms> lsms =  smsDao.findByPhoneAndCodeAndCreateTime(phone,code, time,type);
		if(lsms!=null&&!lsms.isEmpty())
			return lsms.get(0);
		else
			return null;
	}

	/**
	 *@name    发送验证信息
	 *@description 根据类型不同发送不同的格式验证信息 
	 *@type 验证码类型：
	 *@time    创建时间:2015年8月20日下午2:46:37
	 *@param verCodeBean
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Override	
	public SunGySms sendSmsCode(VerCodeBean verCodeBean) {
		String code = RandomCode.getCode();//验证码
		String phone = verCodeBean.getPhone();
		String type = verCodeBean.getType();
		SunGySms sms=new SunGySms();
		//如果5分钟内已经保存过3次以上验证码，则证明用户接收异常，禁止频繁发送
		Date min5 =new Date(new Date().getTime() - 1000*60*5);//5分钟
		List<SunGySms> smslist= smsDao.findByPhonAndCreateTime(phone,min5,type);
		if(smslist!=null&&smslist.size()>3){
			sms.setStates(2);
			return sms;
		}
		//保存验证码信息
		StringBuffer content= new StringBuffer();
		content.append("【芸备胎】您的验证码是");
		content.append(code);
		content.append("，30分钟内有效。如非本人操作，请忽略本短信");
		boolean reCode = SMSUtil.sendVerifyCode(phone, code);
		if(reCode){
			sms.setStates(1);
		}else{
			sms.setStates(2);
		}
		sms.setContent(content.toString());
		sms.setCode(code);
		sms.setCreateTime(new Date());
		sms.setPhone(phone);
		sms.setType(type);
		return smsDao.save(sms);
	}
	

	/**
	 *@name   订单短信通知 （新订单通知卖家，发货通知卖家）
	 *@description 相关说明
	 *@time    创建时间:2016年3月14日下午1:12:02
	 *@param order
	 *@param type 2.下单通知，3库房发货通知
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@SuppressWarnings("unused")
	@Override	
	public void sendSmsOrder(SunDdOrder order,String type) {
		/*YbtBusiness business= businessDao.findOne(order.getSellerId());*/
		List<String> phoneList=null;
	/*	if("2".equals(type)){
			phoneList =getPhoneList(business.getSmsPhone());
		}else if("3".equals(type)){
			phoneList =getPhoneList(business.getStockPhone());
		}*/
		//电话为空，不发送
		if(phoneList==null||phoneList.size()==0){
			return;
		}
		String orderNo=order.getOrderNo();
		String goodInfo ="买方店铺名称：【"+order.getBuyerName()+"】。商品清单：";
		List<SunDdGood> goods =order.getOrderGoods();
		for(int i=0;i<goods.size();i++){
			SunDdGood good = goods.get(i);
			String name =good.getName();
			name=name.length()>32?name.substring(0,32).substring(0,name.substring(0,32).lastIndexOf(" ")):name;//短商品名称
			name=name.replace("【", "[");
			name=name.replace("】", "]");
			goodInfo+="【"+(i+1)+"】"+name;
			goodInfo+="（"+good.getNumber()+"条）; ";
		}
		
		//封装消息内容
		String value="#orderId#="+orderNo+"&#goodInfo#="+goodInfo+"&#person#="+order.getSendeeName()+"&#phone#="+order.getSendeePhone();//短信内容
		String juheId="";
		String content= "";//数据库保存内容
		if("1".equals(type)){
			juheId = Juhe.JUHE_SMS_C_ORDER_CGXD;
			content+="通知类短信模板ID:"+juheId+";";
			content+=" 短信内容：【芸备胎采购系统】新订单！订单号："+orderNo+"订单内容："+goodInfo+"联系人："+order.getSendeeName()+"，联系电话："+order.getSendeePhone()+"，请及时处理。详情查看http://www.yunbeitai.com/ych";
		}else if("2".equals(type)){
			juheId = Juhe.JUHE_SMS_C_ORDER_CGFH;
			content+="通知类短信模板ID:"+juheId+";";
			content+=" 短信内容：【芸备胎采购系统】发货提醒！订单号："+orderNo+"订单内容："+goodInfo+"联系人："+order.getSendeeName()+"，联系电话："+order.getSendeePhone()+"，请及时发货。详情查看http://www.yunbeitai.com/ych";
		}
		
		for(String phone:phoneList){
			//发送短信，并保存发送记录
			sendOrderXx(content, phone, value, juheId, type);
		}
	}
	

	/**
	 *@name   催买家付款
	 *@description 相关说明
	 *@time    创建时间:2016年3月14日下午1:12:02
	 *@param order
	 *@param type 4.催买家付款
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Override	
	public int sendSmsRemind(SunDdOrder order,String type) {
		int reCode = 0;
		List<String> phoneList = getPhoneList(order.getSendeePhone());
		if(phoneList==null||phoneList.size()==0){
			/*YbtBusiness business= businessDao.findOne(order.getBuyerId());
			 phoneList = getPhoneList(business.getSmsPhone());*/
		}
		//电话为空，不发送
		if(phoneList==null||phoneList.size()==0){
			return reCode;
		}
	
		
		String orderNo=order.getOrderNo();
		String goodInfo ="";
		List<SunDdGood> goods =order.getOrderGoods();
		for(int i=0;i<goods.size();i++){
			SunDdGood good = goods.get(i);
			String name =good.getName();
			name=name.length()>32?name.substring(0,32).substring(0,name.substring(0,32).lastIndexOf(" ")):name;//短商品名称
			name=name.replace("【", "[");
			name=name.replace("】", "]");
			goodInfo+="【"+(i+1)+"】"+name;
			goodInfo+="（"+good.getNumber()+"条）; ";
		}

		//封装消息内容
		String juheId = Juhe.JUHE_SMS_C_ORDER_TZ;
		goodInfo="总金额:"+order.getPayTotalPrice()+"元，正等待您的"+(order.getPayment()==1?"支付，":"确认")+"。清单"+goodInfo;
		//数据库保存内容
		String content="通知类短信模板ID:"+juheId+";";
		content+=" 短信内容：【芸备胎采购系统】订单提醒！订单号："+orderNo+"订单内容："+goodInfo+"，请及时处理。详情查看http://www.yunbeitai.com/ych";
		
		String value="#orderId#="+orderNo+"&#goodInfo#="+goodInfo;//短信内容
		
		for(String phone:phoneList){
			//发送短信，并保存发送记录
			Calendar todayStart = Calendar.getInstance();  
	        todayStart.set(Calendar.HOUR, 0);  
	        todayStart.set(Calendar.MINUTE, 0);  
	        todayStart.set(Calendar.SECOND, 0);  
	        todayStart.set(Calendar.MILLISECOND, 0);  
	        Date nowDate =todayStart.getTime(); 
			List<SunGySms> smsList = smsDao.findByPhonAndCreateTime(phone, nowDate, type);
			if(smsList.size()>0){
				for(SunGySms sms:smsList){
					String cont = sms.getContent();
					if(cont.indexOf(order.getOrderNo())>=0){
						//今天已经发送过了。
						reCode = 2;
						return reCode;
					}
				}
			}
			boolean reCodeOne = sendOrderXx(content, phone, value, juheId, type);
			if(reCodeOne){
				reCode=1;
			}
		}
		return reCode;
	}
	
	/**
	 *@name    发送短信，并保存发送记录
	 *@description 相关说明
	 *@time    创建时间:2016年5月4日上午11:19:23
	 *@param content
	 *@param phone
	 *@param value
	 *@param juheId
	 *@param type
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	private boolean sendOrderXx(String content,String phone,String value,String juheId,String type){
		//不为11位不发送
		if(phone.length()!=11){
			return false;
		}
		//发送短信
		boolean reCode = SMSUtil.sendOrderXd(phone,value,juheId);
		//保存短信信息
		SunGySms sms=new SunGySms();
		if(reCode){
			sms.setStates(1);
		}else{
			sms.setStates(2);
		}
		sms.setContent(content.toString());
		sms.setCreateTime(new Date());
		sms.setPhone(phone);
		sms.setType(type);
		smsDao.save(sms);
		return reCode ;
	}
	
	/**
	 *@name    发送短信，并保存发送记录
	 *@description 相关说明
	 *@time    创建时间:2016年5月4日上午11:19:23
	 *@param content
	 *@param phone
	 *@param value
	 *@param juheId
	 *@param type
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	private List<String> getPhoneList(String phoneStr){
		List<String> phoneList = null;
		if(StringUtil.notNull(phoneStr)){
			phoneList= new ArrayList<String>();
			phoneStr=phoneStr.replace("，", ",");
			phoneStr=phoneStr.replace(" ", ",");
			String[]tempArray= phoneStr.split( ",");
			for(String phone:tempArray){
				if(StringUtil.notNull(phone)&&phone.length()==11){
					phoneList.add(phone);
				}
			}
		}
		return phoneList;
	}

	@Override
	public SunGySms sendSmsReserveOrder(String phone,String name,String urls) {
		SunGySms sms=new SunGySms();
		//保存验证码信息
		StringBuffer content= new StringBuffer();
		content.append("【给点儿阳光】"+name+" 给你预定了生日礼物。查看礼物"+urls+"");
		boolean reCode = SMSUtil.sendYudingTongzhi(phone, name, urls);
		if(reCode){
			sms.setStates(1);
		}else{
			sms.setStates(2);
		}
		sms.setContent(content.toString());
		sms.setCreateTime(new Date());
		sms.setPhone(phone);
		sms.setType("Y");
		return smsDao.save(sms);
		
	}
}
