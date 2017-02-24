package com.ybt.service.work.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunTempRecordDao;
import com.ybt.model.work.SunTempRecord;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunTempRecordService;

@Component
public class SunTempRecordServiceImpl extends BaseServiceImpl<SunTempRecord, String> implements SunTempRecordService {
    
	@Autowired
	private SunTempRecordDao  SunTempRecordDao;

	@Resource
	public Map<String,String> constant;
	@Override
	public BaseDao<SunTempRecord, String> getDao() {
		// TODO Auto-generated method stub
		return SunTempRecordDao;
	}
	@Override
	public List<SunTempRecord> findByUserIdAndType(String userId, String type) {
		// TODO Auto-generated method stub
		return SunTempRecordDao.findByUserIdAndType(userId, type);
	}

}
