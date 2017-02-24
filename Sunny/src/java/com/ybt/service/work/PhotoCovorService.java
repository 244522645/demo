package com.ybt.service.work;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ybt.common.util.PropertyFilter;
import com.ybt.model.work.SunZyPhotoCover;
import com.ybt.service.base.IBaseService;

@Component
public interface PhotoCovorService extends IBaseService<SunZyPhotoCover, String>{
	/**
	 * 根据用户属性值获取用户
	 * @return User
	 */
	public Page<SunZyPhotoCover> findSunZyPhotoCoverByProperty(List<PropertyFilter> filters,Pageable pageable);

	public SunZyPhotoCover getOnePhoto(String imgId);

	SunZyPhotoCover savePhoto(SunZyPhotoCover photo);

	SunZyPhotoCover delPhoto(String imgId);

	SunZyPhotoCover releasePhoto(String imgId);
	
	public List<SunZyPhotoCover> findSunZyPhotoCoverByCameristId(String cameristid);

}
