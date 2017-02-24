package com.ybt.dao.work.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunZyImages;
@Component
public class ImagesDaoImpl{
	 @PersistenceContext  
	 private EntityManager em;
	 
	@SuppressWarnings("unchecked")
	public List<SunZyImages> findByIds(String[] ids){
		 String idsstr="";
		 for (String string : ids) {
			 idsstr= ",'" + string + "'";
		}
		 idsstr = idsstr.substring(1);
		 String sql = "select a from SunZyImages a where  a.id in ("+idsstr+")";
		 Query query = em.createQuery(sql,SunZyImages.class);
		 return query.getResultList();
	 }
	 
}
