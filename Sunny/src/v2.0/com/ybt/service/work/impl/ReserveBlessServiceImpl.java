package com.ybt.service.work.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.util.DateUtil;
import com.ybt.model.work.MemberRelation;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunQrcode;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.work.MemberRelationService;
import com.ybt.service.work.OrderService;
import com.ybt.service.work.QrcodeService;
import com.ybt.service.work.ReserveBlessService;
import com.ybt.service.work.SunBlessService;

@Component("reserveBlessService")
public class ReserveBlessServiceImpl  extends SunBlessServiceImpl implements ReserveBlessService{
	 @Autowired
	 private OrderService orderService;
	 @Autowired
	 @Qualifier("sunBlessService")
	 private SunBlessService blessService;
	 @Autowired
	 private MemberRelationService relationService;
	 @Autowired
	 private QrcodeService qrcodeService;
	@Override
	public SunDdOrder saveOrder(SunWechatUser w, String relationId, String relationName, String relationRelation,
			String relationPhone, String relationBirth, String message, Double price, String sunPayType) {
		SunDdOrder order = new SunDdOrder();
		order.setTitle(w.getWechatNickname()+" 祝：" +relationName);
		
		if(price>0){
			if(sunPayType.equals("SUNCARD")){
				order.setPayment(2);//在线卡支付
			}else{
				order.setPayment(1);//在线支付
			}
			
			order.setDelivery(0);//在线浏览
		}else{
			order.setPayment(0);//无需支付
			order.setDelivery(0);//在线浏览
		}
		order.setBuyerId(w.getId());//买家ID
		
		order.setBuyerName(w.getWechatNickname());
		order.setTotalPrice(price);//应付总价
		order.setPayTotalPrice(price);//实付总价
		order.setTotalGoodPrice(price);//商品总价
		order.setMessage(message);
		order.setSendeeName(relationName);
		order.setSendPrice(0.00);//物流费用
		
		order = orderService.saveOrder(order, w.getId());
		return order;
	}

	@Override
	public Result<SunBless> saveReserveBless(SunWechatUser user, String sender, String relation, String bless,
			String[] citys, SunDdOrder order) {
		// TODO Auto-generated method stub
		if(relation==null)
			return new Result<SunBless>("没有此人",null);
		
		MemberRelation mr = relationService.findOne(relation);
		if(mr==null)
			return new Result<SunBless>("没有此人",null);
		
		String citystr="";
		for (String str : citys) {
			citystr+=","+str;
		}
		if(!citystr.equals("")){
			citystr= citystr.substring(1);
		}
		
		SunBless sbless =new SunBless();
		if(order!=null){
			SunBless sblesss=blessService.findByOrder(order.getId());
			if( sblesss!=null)
				sbless =sblesss;
		}
		sbless.setBless(bless);
		sbless.setCreateTime(new Date());
		sbless.setBirthday(DateUtil.getBirthDay(mr.getBirthday()));
		sbless.setIsread(0);
		sbless.setOrder(order);
		sbless.setReceiver(mr.getName());
		sbless.setRelation(mr);
		sbless.setCitys(citystr);
		sbless.setType(1);//预订
		sbless.setSender(sender);
		sbless.setStatus(0);
		sbless.setUserId(user);
		Result<SunQrcode> result = qrcodeService.createTempQrcode("", 0L, "order");
		if(null != result && 1 == result.getState()){
			sbless.setSunQrcode(result.getT());
		}
		blessService.save(sbless);
		
		return new Result<SunBless>("",sbless);
	}
	
}
