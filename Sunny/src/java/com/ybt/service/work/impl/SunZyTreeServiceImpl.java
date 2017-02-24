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
import com.ybt.dao.work.SunZyTreeDao;
import com.ybt.model.work.SunZyTree;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunZyTreeService;

@Component
public class SunZyTreeServiceImpl extends BaseServiceImpl<SunZyTree, String> implements SunZyTreeService{
	
	@Autowired
	private SunZyTreeDao photoDao;
	
	@Override
	public BaseDao<SunZyTree, String> getDao() {
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
	public Page<SunZyTree> findSunZyTreeByProperty(final List<PropertyFilter> filters,Pageable pageable) {
		Page<SunZyTree>  page = photoDao.findAll(
		new Specification<SunZyTree>(){
			@SuppressWarnings("incomplete-switch")
			public Predicate toPredicate(Root<SunZyTree> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
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
			}}, (Pageable) new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),new Sort(Direction.DESC, new String[] { "createTime" })));
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
	public SunZyTree getOneTree(String imgId) {
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
	public SunZyTree saveSunZyTree(SunZyTree photo) {
		String id = photo.getId();
		//ID为空则不修改
		if(id==null||"".equals(id)){
			return null;
		}
		SunZyTree photoOld =  photoDao.findOne(id);
		
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
	public SunZyTree delSunZyTree(String imgId) {
		SunZyTree photo = photoDao.findOne(imgId);
		photo.setDeleted(1);
		photoDao.save(photo);
		return photo;
	}

	/* 
	 * 获取 新树
	 */
	public SunZyTree findOneNewTree() {
		return null;
	}

	@Override
	public SunZyTree findSunZyTreeByUserPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SunZyTree findSunZyTreeByOpenid(String openid) {
		// TODO Auto-generated method stub
		return null;
	}

}
