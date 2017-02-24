package com.ybt.service.work;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ybt.common.util.PropertyFilter;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.base.IBaseService;

@Component
@Service("photoService") 
public interface PhotoService extends IBaseService<SunZyPhoto, String>{
	/**
	 * 根据用户属性值获取用户
	 * @return User
	 */
	public Page<SunZyPhoto> findSunZyPhotoByProperty(List<PropertyFilter> filters,Pageable pageable);

	/**
	 * 根据搜索内容 查询
	 * @return User
	 */
	public Page<SunZyPhoto> findSunZyPhotoByProperty(String search,List<PropertyFilter> filters,Pageable pageable);

	
	public SunZyPhoto getOnePhoto(String imgId);

	public com.ybt.common.util.Page<SunZyPhoto> findSunZyPhotoByCityAndShootingTime(String city,String date,com.ybt.common.util.Page<SunZyPhoto> page) throws Exception;
	
	SunZyPhoto savePhoto(SunZyPhoto photo);

	SunZyPhoto delPhoto(String imgId);

	SunZyPhoto releasePhoto(String imgId);
}
