package com.ybt.service.work.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunCelebrityBirthdayDao;
import com.ybt.model.work.SunCelebrityBirthday;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunCelebrityBirthdayService;

@Component
public class SunCelebrityBirthdayServiceImpl extends BaseServiceImpl<SunCelebrityBirthday, String> implements SunCelebrityBirthdayService{

	@Autowired
	private SunCelebrityBirthdayDao sunCelebrityBirthdayDao;
	
	public BaseDao<SunCelebrityBirthday, String> getDao() {
		return sunCelebrityBirthdayDao;
	}

	public SunCelebrityBirthday findByBirthday(String birthday) {
		return sunCelebrityBirthdayDao.findByBirthdayLike(birthday);
	}
	
}
