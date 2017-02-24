package com.ybt.service.work.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunCollectDao;
import com.ybt.model.work.SunCollect;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunCollectService;

@Component
public class SunCollectServiceImpl extends BaseServiceImpl<SunCollect, String> implements SunCollectService{
	
	@Autowired
	private SunCollectDao sunCollectDao;
	
	@Override
	public BaseDao<SunCollect, String> getDao() {
		return sunCollectDao;
	}

}
