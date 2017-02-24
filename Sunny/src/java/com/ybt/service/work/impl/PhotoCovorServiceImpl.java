package com.ybt.service.work.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ybt.common.util.PropertyFilter;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.PhotoCovorDao;
import com.ybt.model.work.SunZyPhotoCover;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.PhotoCovorService;

@Component
public class PhotoCovorServiceImpl extends BaseServiceImpl<SunZyPhotoCover, String> implements PhotoCovorService{
	
	@Autowired
	private PhotoCovorDao photoDao;
	
	@Override
	public BaseDao<SunZyPhotoCover, String> getDao() {
		return photoDao;
	}
	
	/**
	 *@name    分页查询全部图片
	 *@description 相关说明
	 *@time    创建时间:2016年6月16日下午4:35:11
	 *@param filters
	 *@param pageable
	 *@return
	 * @author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Override
	public Page<SunZyPhotoCover> findSunZyPhotoCoverByProperty(final List<PropertyFilter> filters, Pageable pageable) {
		Page<SunZyPhotoCover>  page = photoDao.findAll(
		new Specification<SunZyPhotoCover>(){
			@SuppressWarnings("incomplete-switch")
			public Predicate toPredicate(Root<SunZyPhotoCover> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				 List<Predicate> list = new ArrayList<Predicate>();  
				 	for(PropertyFilter pf:filters){
				 		switch (pf.getMatchType()) {
						case EQ:
						    list.add(cb.equal(root.get(pf.getPropertyName()).as(pf.getPropertyClass()), pf.getMatchValue()));  
							break;
						case LIKE:
							list.add(cb.like(root.get(pf.getPropertyName()).as(String.class), "%"+pf.getMatchValue()+"%")); 
							break;
						}
				 	}
				    list.add(cb.equal(root.get("deleted").as(String.class), "0")); 
				    Predicate[] p = new Predicate[list.size()];  
				    return cb.and(list.toArray(p));  
			}}, (Pageable) new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),new Sort(Direction.DESC, new String[] {"shootingTime", "createTime"})));
		return page;
	}
	/**
	 *@name    获取照片信息
	 *@description 相关说明
	 *@time    创建时间:2016年6月16日下午4:35:20
	 *@param imgId
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public SunZyPhotoCover getOnePhoto(String imgId) {
		return photoDao.findOne(imgId);
	}
	
	

	/**
	 *@name    修改照片信息
	 *@description 相关说明
	 *@time    创建时间:2016年6月17日下午1:53:24
	 *@param photo
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Override
	public SunZyPhotoCover savePhoto(SunZyPhotoCover photo) {
		String id = photo.getId();
		//ID为空则不修改
		if(id==null||"".equals(id)){
			return null;
		}
		SunZyPhotoCover photoOld =  photoDao.findOne(id);
		photoOld.setAddress(photoOld.getAddress());
		return photoDao.save(photoOld);
	}
	
	/**
	 *@name    删除照片信息
	 *@description 相关说明
	 *@time    创建时间:2016年6月16日下午4:35:20
	 *@param imgId
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Transactional
	public SunZyPhotoCover delPhoto(String imgId) {
		SunZyPhotoCover photo = photoDao.findOne(imgId);
		photo.setDeleted(1);
		photoDao.save(photo);
		return photo;
	}
	
	/**
	 *@name    发布照片信息
	 *@description 相关说明
	 *@time    创建时间:2016年6月16日下午4:35:20
	 *@param imgId
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Override
	public SunZyPhotoCover releasePhoto(String imgId) {
		SunZyPhotoCover photo = photoDao.findOne(imgId);
		photo.setReleased(1);
		photo.setWorkerId("");//登录人的ID
		return photoDao.save(photo);
	}

	@Override
	public List<SunZyPhotoCover> findSunZyPhotoCoverByCameristId(String cameristid) {
		return photoDao.findSunZyPhotoCoverByCameristId(cameristid);
	}

}
