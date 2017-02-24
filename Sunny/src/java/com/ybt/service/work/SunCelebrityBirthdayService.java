package com.ybt.service.work;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunCelebrityBirthday;
import com.ybt.service.base.IBaseService;

@Component
public interface SunCelebrityBirthdayService extends IBaseService<SunCelebrityBirthday, String>{
	//获取当天的数据
	public SunCelebrityBirthday findByBirthday(String birthday);
}
