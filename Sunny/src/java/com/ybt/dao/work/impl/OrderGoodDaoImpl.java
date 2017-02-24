package com.ybt.dao.work.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
@Component
public class OrderGoodDaoImpl{
	 @PersistenceContext  
	 private EntityManager em;


}
