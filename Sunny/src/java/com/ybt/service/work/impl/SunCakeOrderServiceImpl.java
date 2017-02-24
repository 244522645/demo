package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunCakeOrderDao;
import com.ybt.model.work.SunCakeOrder;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunCakeOrderService;

@Component
public class SunCakeOrderServiceImpl extends BaseServiceImpl<SunCakeOrder, String> implements SunCakeOrderService{
	
	@Autowired
	private SunCakeOrderDao sunCakeOrderDao;
	@Override
	public BaseDao<SunCakeOrder, String> getDao() {
		return sunCakeOrderDao;
	}
	@Override
	public Result<SunCakeOrder> saveSunCakeOrder(SunWechatUser user, String shop, String name, String phone,
			String address, String note, SunZyPhoto photo ,String photoInfo) {
		
		Long max = sunCakeOrderDao.findMaxValue();
		max = max==null?0:max;
		
		SunCakeOrder co = new SunCakeOrder();
		co.setNumber(max+1);
		if(user!=null)
		co.setUserId(user);
		co.setShop(shop);
		if(user!=null)
		co.setPhoto(photo);
		co.setPhotoInfo(photoInfo);
		co.setName(name);
		co.setPhone(phone);
		co.setAddress(address);
		co.setBless(note);
		co.setStatus(0);
		co.setCreateTime(new Date());
		
		sunCakeOrderDao.save(co);
		
		return new Result<SunCakeOrder>("",co);
	}
	@Override
	public List<SunCakeOrder> findByPhone(String phone) {
		// TODO Auto-generated method stub
		return sunCakeOrderDao.findByPhone(phone);
	}
	@Override
	public List<SunCakeOrder> findByUser(String userId) {
		// TODO Auto-generated method stub
		return sunCakeOrderDao.findByUser(userId);
	}
}
