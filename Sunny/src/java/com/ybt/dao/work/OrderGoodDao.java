package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunDdGood;

public interface OrderGoodDao extends BaseDao<SunDdGood, String> {
	 /**
	 * 订单商品
	 */
	 @Query("select a from SunDdGood a where  a.orderId=? and  a.deleted = 0")
	 public List<SunDdGood> getOrderGoodByOrderId(String orderId);

	 
	 /**
	 * 删除购物车商品
	 */
	 @Modifying
	 @Query("update SunDdGood a set a.deleted =1 where a.id= ?")
	 public int deleteCartGood(String goodId);
	 
/*	 *//**
	  * 根据商品Id查询一个订单多个商品
	  *//*
	 public List<SunDdGood> getOrderGoodsByGoodsId(String sqlWhere);*/
}
