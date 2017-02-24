package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunBill;
import com.ybt.model.work.SunZhDaybook;

@Component
public interface SunBillDao extends BaseDao<SunBill, String> {

	/**
	 * 查询芸备胎流水
	 */
	@Query("select a from SunBill a where a.mid = ? and a.deleted=0")
	List<SunZhDaybook> findBillByMid(String mid);
	
}
