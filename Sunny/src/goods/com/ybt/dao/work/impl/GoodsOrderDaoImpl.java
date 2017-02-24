package com.ybt.dao.work.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
@Component
public class GoodsOrderDaoImpl{
	 @PersistenceContext  
	 private EntityManager em;
	 public String getGoodsOrderNo(){
		 Query query = em.createNativeQuery("SELECT GOODSORDERNO()");
		 String ddh =  (String)query.getSingleResult();
		 return ddh;
	 }
}
