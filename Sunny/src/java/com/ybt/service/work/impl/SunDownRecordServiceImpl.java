package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.bean.VerCodeBean;
import com.ybt.common.constant.chxtConstant;
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.RandomCode;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.OrderDao;
import com.ybt.dao.work.SunDownRecordDao;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunDownRecord;
import com.ybt.model.work.SunWXMessage;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SmsService;
import com.ybt.service.work.SunBlessService;
import com.ybt.service.work.SunCakeOrderService;
import com.ybt.service.work.SunDownRecordService;
import com.ybt.service.work.WXMessageService;

@Component
public class SunDownRecordServiceImpl extends BaseServiceImpl<SunDownRecord, String> implements SunDownRecordService{
	
	@Autowired
	private SunDownRecordDao baseDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	@Qualifier("sunBlessService")
	private SunBlessService blessService;
	@Autowired
	private SunCakeOrderService sunCakeService;
	@Autowired 
	private WXMessageService wxMessageService;
	@Autowired
	private SmsService   smsService;
	@Override
	public BaseDao<SunDownRecord, String> getDao() {
		return baseDao;
	}

	@Override
	public Result<SunDownRecord> saveSunDownRecord(String orderId, SunWechatUser user, String email) {
		// TODO Auto-generated method stub
		SunDdOrder order = orderDao.findOne(orderId);
		if(order==null)
			new Result<>("未找到相关订单",null);
		
		SunDownRecord record = getSunDownRecordByOrderId(order.getId());
		if(record == null){
			record = new SunDownRecord();
			record.setCreateTime(new Date());
		}
		record.setOrder(order);
		record.setUser(user);
		record.setEmail(email);
		record.setUpdateTime(new Date());
		baseDao.save(record);
		
		return new Result<SunDownRecord>("",record);
	}
	@Override
	public Result<SunDownRecord> saveSunDownRecord(String blessId,String photoId, SunWechatUser user, String email) {
		// TODO Auto-generated method stub
		if(blessId==null)
			new Result<>("未找到相关订单",null);
		SunBless bless = blessService.findOne(blessId);
		if(bless==null)
			new Result<>("未找到相关订单",null);
		
		SunDownRecord record = getSunDownRecordByOrderId(bless.getOrder().getId());
		if(record == null){
			record = new SunDownRecord();
			record.setCreateTime(new Date());
		}
		record.setOrder(bless.getOrder());
		record.setBless(bless);
		record.setUser(user);
		record.setEmail(email);
		record.setUpdateTime(new Date());
		baseDao.save(record);
		
		bless.setDownemail(email);
		blessService.save(bless);
		
		SunZyPhoto photo = bless.getPhoto();
		String photoInfo =photo.getProvinceF()+":"+photo.getLocalName();
		String dateTime =DateUtil.getDateFormat(photo.getCreateTime(), "yyyy/MM/dd HH:mm");
		String userInfo =""+record.getEmail()+"昵称("+user.getWechatNickname()+")";
		//临时 发短信提醒
			VerCodeBean verCodeBean = new VerCodeBean();
			String code = RandomCode.getCode();//验证码
			verCodeBean.setCode(bless.getOrder().getOrderNo()+"");
			verCodeBean.setType(chxtConstant.SMS_BDYZM);//注册绑定手机号
			StringBuffer content= new StringBuffer();
			content.append("【给点儿阳光】有新订单");
			content.append(code);
			content.append("，尽快处理。如非本人操作，请忽略本短信");
			
			verCodeBean.setPhone("13581566690");//双江
			smsService.sendOrderWarn(verCodeBean,content.toString());
			verCodeBean.setPhone("18600825086");//继祥
			smsService.sendPhotoDownRequest("18600825086",photoInfo+"-"+dateTime,userInfo);
			smsService.sendPhotoDownRequest("13581566690",photoInfo+"-"+dateTime,userInfo);
//			
			//发送微信消息
			StringBuffer wxcontent= new StringBuffer();
			wxcontent.append("下载请求提醒:\n");
			wxcontent.append(photo.getLocalName()+"\n");
			wxcontent.append("照片:"+photoInfo+"\n"+dateTime+"\n");
			wxcontent.append("用户:"+userInfo+"\n");
			//继祥微信 我的oWL5RuJoTvOo2ZyHwLfafhE3B3-M
			SunWXMessage wxMessage= new SunWXMessage("o3qhbv0KstBJQiQvQp8sTJrZ6-V8",wxcontent.toString());
			wxMessageService.sendMessage(wxMessage);
			 wxMessage= new SunWXMessage("o3qhbvyzTW8Vfjj0MVyCIPvoyHCM",wxcontent.toString());
			wxMessageService.sendMessage(wxMessage);
		
		return new Result<SunDownRecord>("",record);
	}

	@Override
	public SunDownRecord getSunDownRecordByOrderId(String orderId) {
		// TODO Auto-generated method stub
		List<SunDownRecord> list = baseDao.findSunDownRecordByOrderId(orderId);
		if(!list.isEmpty()&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
