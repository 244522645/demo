package com.ybt.service.work.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.AttractionDao;
import com.ybt.model.work.SunAttraction;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.AttractionService;

@Component
public class AtrractionServiceImpl extends BaseServiceImpl<SunAttraction,String> implements AttractionService{
	
	
	@Autowired
	private AttractionDao attractionDao;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	public HashMap constant;
	
	public BaseDao<SunAttraction, String> getDao() {
		// TODO Auto-generated method stub
		return attractionDao;
	}

	@Override
	public List<SunAttraction> getCityListByGroupByCity() {
		return attractionDao.findByGroupByCity();
	}


}
