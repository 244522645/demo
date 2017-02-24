package com.ybt.dao.work;

import org.springframework.data.jpa.repository.Query;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunDdProcess;

public interface DmOrderProcessDao extends BaseDao<SunDdProcess, String> {
	 @Query("select a from SunDdProcess a  where a.deleted = 0 and a.payment=? and a.delivery=?")
	 public SunDdProcess getOrderProcess(int payment,int delivery);
}
