package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.CrowBill;

public interface CrowBillDao extends BaseDao<CrowBill, String> {
	@Query(value="select * from crow_bill a where a.deleted = 0 and a.user_id = :userId and a.status = 1 and a.create_time <= :date order by a.create_time desc", nativeQuery = true)
	public List<CrowBill> findByUserIdAndDate(@Param("userId") String userId, @Param("date") String date);
	
	public String getNewOrderNo ();
	
	@Query(value="select * from crow_bill a where a.deleted = 0 and a.order_id = :ordeId", nativeQuery = true)
	public CrowBill findByOrderId(@Param("ordeId") String ordeId);
}
