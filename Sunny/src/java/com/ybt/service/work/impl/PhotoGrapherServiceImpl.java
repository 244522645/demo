package com.ybt.service.work.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.PhotoGrapherDao;
import com.ybt.model.work.SunZyPhotoCover;
import com.ybt.model.work.SunZyPhotoGrapher;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.PhotoGrapherService;

@Component
public class PhotoGrapherServiceImpl extends BaseServiceImpl<SunZyPhotoGrapher, String> implements PhotoGrapherService{
	
	@Autowired
	private PhotoGrapherDao photoGrapherDao;
	
	@Override
	public BaseDao<SunZyPhotoGrapher, String> getDao() {
		return photoGrapherDao;
	}

	@Override
	public SunZyPhotoGrapher savePhotoGrapher(SunZyPhotoCover photo) {
		return null;
	}
	

}
