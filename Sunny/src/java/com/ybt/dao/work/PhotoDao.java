package com.ybt.dao.work;

import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunZyPhoto;

@Component
public interface PhotoDao extends BaseDao<SunZyPhoto, String>{
	//通过地点和摄影时间来查询
	public com.ybt.common.util.Page<SunZyPhoto> findSunZyPhotoByCityAndShootingTime(String city,String date,com.ybt.common.util.Page<SunZyPhoto> page);
    
}
