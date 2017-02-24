package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunTempRecord;

@Component
public interface SunTempRecordDao extends BaseDao<SunTempRecord,String>{
	
	/**
	 *  查询 纪录
	 * */
	@Query("select a from SunTempRecord a where a.userId = ? and a.type=?")
	public List<SunTempRecord> findByUserIdAndType(String userId,String type);
	
}
