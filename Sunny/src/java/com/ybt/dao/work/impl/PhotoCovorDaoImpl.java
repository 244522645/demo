package com.ybt.dao.work.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunZyPhoto;
import com.ybt.model.work.SunZyPhotoCover;
@Component
public class PhotoCovorDaoImpl{
	 @PersistenceContext  
	 private EntityManager em;
	 
	 @SuppressWarnings("unchecked")
	public com.ybt.common.util.Page<SunZyPhotoCover> findSunZyPhotoByCityAndShootingTime(String city,String date,com.ybt.common.util.Page<SunZyPhotoCover> page){
		 
		 String sql = "SELECT * FROM SunZyPhotoCover";
	 	/* if(city != null){
	 		 sql += "and a.city="+city;
	 	 }
	 	sql += " order by createTime desc";*/
		 Query query = em.createQuery(sql,SunZyPhoto.class);
//		 query.setParameter("shootingTime", "");
		 
		 page.setResult(query.getResultList());
		 
		 return page;
	 }
	 
}
