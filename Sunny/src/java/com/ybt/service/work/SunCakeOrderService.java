package com.ybt.service.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunCakeOrder;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.base.IBaseService;

@Component
public interface SunCakeOrderService extends IBaseService<SunCakeOrder, String> {
	
	public Result<SunCakeOrder> saveSunCakeOrder(SunWechatUser user,String shop,String name,String phone,String address,String note,SunZyPhoto photo, String photoInfo);


	public List<SunCakeOrder> findByPhone(String phone);
	
	public List<SunCakeOrder> findByUser(String userId);
}
