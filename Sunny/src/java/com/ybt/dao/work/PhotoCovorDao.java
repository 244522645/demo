package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunZyPhotoCover;

@Component
public interface PhotoCovorDao extends BaseDao<SunZyPhotoCover, String>{
	
	public com.ybt.common.util.Page<SunZyPhotoCover> findSunZyPhotoByCityAndShootingTime(String city,String date,com.ybt.common.util.Page<SunZyPhotoCover> page);

	@Query("select a from SunZyPhotoCover a where  a.cameristId = ? order by a.createTime desc")
	public List<SunZyPhotoCover> findSunZyPhotoCoverByCameristId(String cameristid);

}
