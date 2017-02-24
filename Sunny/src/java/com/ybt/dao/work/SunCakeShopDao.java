package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunCakeOrder;
import com.ybt.model.work.SunCakeShop;

@Component
public interface SunCakeShopDao extends BaseDao<SunCakeShop, String> {

	@Query("select a from SunCakeOrder a where a.phone=?")
	public List<SunCakeOrder> findByPhone(String phone);
	
	@Query("select a from SunCakeOrder a where a.userId.id=?")
	public List<SunCakeOrder> findByUser(String userId);
}
