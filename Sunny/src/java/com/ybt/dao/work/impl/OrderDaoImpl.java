package com.ybt.dao.work.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
@Component
public class OrderDaoImpl{
	 @PersistenceContext  
	 private EntityManager em;
	 public String getDDH(){
		 Query query = em.createNativeQuery("SELECT DDH()");
		 String ddh =  (String)query.getSingleResult();
		 return ddh;
	 }
}
