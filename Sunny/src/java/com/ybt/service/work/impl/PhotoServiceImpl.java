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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ybt.common.util.PropertyFilter;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.PhotoDao;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.PhotoService;

@Component
@Service("photoService") 
public class PhotoServiceImpl extends BaseServiceImpl<SunZyPhoto, String> implements PhotoService{
	
	@Autowired
	private PhotoDao photoDao;
	
	@Override
	public BaseDao<SunZyPhoto, String> getDao() {
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
	public Page<SunZyPhoto> findSunZyPhotoByProperty(final List<PropertyFilter> filters,Pageable pageable) {
		Page<SunZyPhoto>  page = photoDao.findAll(
		new Specification<SunZyPhoto>(){
			@SuppressWarnings("incomplete-switch")
			public Predicate toPredicate(Root<SunZyPhoto> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
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
			}}, (Pageable) new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),new Sort(Direction.DESC, new String[] {"shootingTime", "city" , "createTime"})));
		return page;
	}
	
	public com.ybt.common.util.Page<SunZyPhoto> findSunZyPhotoByCityAndShootingTime(String city,String date,com.ybt.common.util.Page<SunZyPhoto> page) throws Exception{
		photoDao.findSunZyPhotoByCityAndShootingTime(city, date, page);
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
	public SunZyPhoto getOnePhoto(String imgId) {
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
	public SunZyPhoto savePhoto(SunZyPhoto photo) {
		String id = photo.getId();
		//ID为空则不修改
		if(id==null||"".equals(id)){
			return null;
		}
		SunZyPhoto photoOld =  photoDao.findOne(id);
		photoOld.setProvince(photoOld.getProvince());
		photoOld.setArea(photoOld.getArea());
		photoOld.setCity(photoOld.getCity());
		photoOld.setAddress(photoOld.getAddress());
		photoOld.setLongitude(photoOld.getLongitude());
		photoOld.setLatitude(photoOld.getLatitude());
		photoOld.setPrice(photoOld.getPrice());
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
	public SunZyPhoto delPhoto(String imgId) {
		SunZyPhoto photo = photoDao.findOne(imgId);
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
	public SunZyPhoto releasePhoto(String imgId) {
		SunZyPhoto photo = photoDao.findOne(imgId);
		photo.setReleased(1);
		photo.setWorkerId("");//登录人的ID
		return photoDao.save(photo);
	}

	@Override
	public Page<SunZyPhoto> findSunZyPhotoByProperty(final String search,final List<PropertyFilter> filters,final Pageable pageable) {
			Page<SunZyPhoto>  page = photoDao.findAll(
					new Specification<SunZyPhoto>(){
						@SuppressWarnings("incomplete-switch")
						public Predicate toPredicate(Root<SunZyPhoto> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
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
							 	if(search!=null&&!"".equals(search)){
							 		 Predicate pp = cb.or(cb.like(root.get("title").as(String.class),'%'+ search +'%'), cb.like(root.get("province").as(String.class), '%'+ search +'%'),cb.like(root.get("city").as(String.class), '%'+ search +'%')); 
								        list.add(pp);
							 	}
							     
							        Predicate[] p = new Predicate[list.size()];  
							    return cb.and(list.toArray(p));  
						}}, (Pageable) new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),new Sort(Direction.DESC, new String[] {"shootingTime", "city" , "createTime"})));
					return page;
		}
}
