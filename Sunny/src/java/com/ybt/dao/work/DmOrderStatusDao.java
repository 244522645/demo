package com.ybt.dao.work;

import org.springframework.data.jpa.repository.Query;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunDdStatus;

public interface DmOrderStatusDao extends BaseDao<SunDdStatus, String> {
	 @Query("select a from SunDdStatus a  where a.deleted != 1 and a.processId=?")
	 public SunDdStatus getOrderStatusByProcessId(String processId);
}
