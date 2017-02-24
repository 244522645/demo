package com.ybt.dao.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunZyImages;

@Component
public interface ImagesDao extends BaseDao<SunZyImages, String> {
	/**
	 *@description 查询图片集
	 *@return
	 */
	public List<SunZyImages> findByIds(String[] ids);

}
