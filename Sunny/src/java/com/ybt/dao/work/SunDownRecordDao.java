package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunDownRecord;

@Component
public interface SunDownRecordDao extends BaseDao<SunDownRecord, String> {

	@Query("select a from SunDownRecord a where a.order.id = ?")
	public List<SunDownRecord> findSunDownRecordByOrderId(String orderId);
}
