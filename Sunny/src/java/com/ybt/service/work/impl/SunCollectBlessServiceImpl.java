package com.ybt.service.work.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunCollectBlessDao;
import com.ybt.model.work.SunCollectBless;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunCollectBlessService;

@Component
public class SunCollectBlessServiceImpl extends BaseServiceImpl<SunCollectBless, String> implements SunCollectBlessService{
	
	@Autowired
	private SunCollectBlessDao sunCollectBlessDao;
	
	@Override
	public BaseDao<SunCollectBless, String> getDao() {
		return sunCollectBlessDao;
	}

}
