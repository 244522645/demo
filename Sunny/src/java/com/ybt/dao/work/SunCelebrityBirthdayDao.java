package com.ybt.dao.work;

import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunCelebrityBirthday;

@Component
public interface SunCelebrityBirthdayDao extends BaseDao<SunCelebrityBirthday,String>{
	
	public SunCelebrityBirthday findByBirthdayLike(final String birthday);
}
