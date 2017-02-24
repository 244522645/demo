package com.ybt.dao.work.impl;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
@Component
public class OrderChangeDaoImpl{
	 @PersistenceContext  
	 private EntityManager em;
	 

	/**
	 *@name    购买列表状态更新数量
	 *@description 相关说明
	 *@time    创建时间:2016年3月28日上午8:52:59
	 *@param buyerId
	 *@param searchTime
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public int getBuyOrderChange(String buyerId,Date searchTime){
		 String sql ="select count(*) from ybt_order_change a,ybt_order b,ybt_dm_order_status c "
		 		+ "where a.order_id=b.id and a.before_status=c.id and a.deleted = 0 and b.deleted=0 and b.buy_deleted=0 "
		 		+ "and c.performer=1 and b.buyer_id=:buyerId and a.after_status!='-1' and a.create_time>:searchTime";
		
		 Query query =  em.createNativeQuery(sql);
		 query.setParameter("buyerId", buyerId);
		 query.setParameter("searchTime", searchTime);
		 Integer count =  ((BigInteger)query.getResultList().get(0)).intValue();
		 return count;
	 }

	/**
	 *@name    销售列表状态更新数量
	 *@description 相关说明
	 *@time    创建时间:2016年3月28日上午8:53:33
	 *@param sellerId
	 *@param searchTime
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public int getSellOrderChange(String sellerId,Date searchTime){
		 String sql ="select count(*) from ybt_order_change a LEFT JOIN ybt_dm_order_status c on a.before_status=c.id,ybt_order b "
 		+ "where a.order_id=b.id and a.deleted = 0 and b.deleted=0 and b.sell_deleted=0 "
 		+ "and (c.performer=0 or c.performer is NULL) and b.seller_id=:sellerId and a.after_status!='-1' and a.create_time>:searchTime";

		
		 Query query =  em.createNativeQuery(sql);
		 query.setParameter("sellerId", sellerId);
		 query.setParameter("searchTime", searchTime);
		 Integer count =  ((BigInteger)query.getResultList().get(0)).intValue();
		 return count;
	 }
	
}
