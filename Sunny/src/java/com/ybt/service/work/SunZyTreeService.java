package com.ybt.service.work;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ybt.common.util.PropertyFilter;
import com.ybt.model.work.SunZyTree;
import com.ybt.service.base.IBaseService;

@Component
public interface SunZyTreeService extends IBaseService<SunZyTree, String>{
	/**
	 * 根据用户属性值获取用户
	 * @return User
	 */
	public Page<SunZyTree> findSunZyTreeByProperty(List<PropertyFilter> filters,Pageable pageable);

	public SunZyTree getOneTree(String imgId);
	
	public SunZyTree findSunZyTreeByUserPhone(String phone);
	
	public SunZyTree findSunZyTreeByOpenid(String openid);
	
	public SunZyTree findOneNewTree();
	
	SunZyTree saveSunZyTree(SunZyTree photo);

	SunZyTree delSunZyTree(String imgId);

}
