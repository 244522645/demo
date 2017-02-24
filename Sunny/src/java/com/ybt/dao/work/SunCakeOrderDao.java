package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunCakeOrder;

@Component
public interface SunCakeOrderDao extends BaseDao<SunCakeOrder, String> {

	/**
	 * 查询 最大值
	 * */
	@Query("select Max(number) from SunCakeOrder a")
	public Long findMaxValue();
	@Query("select a from SunCakeOrder a where a.phone=?")
	public List<SunCakeOrder> findByPhone(String phone);
	
	@Query("select a from SunCakeOrder a where a.ispay=1 and a.userId.id=? order by createTime desc")
	public List<SunCakeOrder> findByUser(String userId);
}
