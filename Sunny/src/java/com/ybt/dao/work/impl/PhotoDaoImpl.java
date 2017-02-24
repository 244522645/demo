package com.ybt.dao.work.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunZyPhoto;
@Component
public class PhotoDaoImpl{
	 @PersistenceContext  
	 private EntityManager em;
	 
	 @SuppressWarnings("unchecked")
	public com.ybt.common.util.Page<SunZyPhoto> findSunZyPhotoByCityAndShootingTime(String city,String date,com.ybt.common.util.Page<SunZyPhoto> page){
		 
		// String sql = "SELECT a FROM SunZyPhoto a where (a.title =:City or a.province =:City a.city =:City ) and a.shootingTime = :shootingTime order by shootingTime desc,city";
		 String sql = "SELECT a FROM SunZyPhoto a where a.shootingTime = :shootingTime order by shootingTime desc,city";
		 
		 /* if(city != null){
	 		 sql += "and a.city="+city;""
	 	 }
	 	sql += " order by createTime desc";*/
		 Query query = em.createQuery(sql,SunZyPhoto.class);
//		 query.setParameter("City",  '%'+city+'%');
//		 query.setParameter("City",  '%'+city+'%');
//		 query.setParameter("City",  '%'+city+'%');
		 query.setParameter("shootingTime", '%'+date+'%');
		 
		 page.setResult(query.getResultList());
		 
		 return page;
	 }
	 
}
