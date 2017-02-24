package com.ybt.dao.work.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunCard;
@Component
public class SunCardDaoImpl{
	@PersistenceContext  
	 private EntityManager em;


	/*//绑定阳光卡
	public int blind(String cardNum, String cardPass) {
		// TODO Auto-generated method stub
		return 0;
	}
*/
	
	 @SuppressWarnings("unchecked")
	 public com.ybt.common.util.Page<SunCard> findSunCardByNumber(String number,com.ybt.common.util.Page<SunCard> page){
			 
			 String sql = "SELECT * FROM SunCard";
			 Query query = em.createQuery(sql,SunCard.class);
			 
			 page.setResult(query.getResultList());//获取阳光卡信息列表
			 
			 return page;
		 }
	 /**
	 * 生成卡号
	 * */
	public  Long createCardNumber(){
		try{
			Query query = em.createNativeQuery("select max(number) from sun_card;");
			Long res = Long.parseLong((String)query.getSingleResult());
			return res+1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
