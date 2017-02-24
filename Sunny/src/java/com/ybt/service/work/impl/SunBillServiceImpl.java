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

import com.ybt.common.util.PropertyFilter;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunBillDao;
import com.ybt.model.work.SunBill;
import com.ybt.model.work.SunZhDaybook;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunBillService;

@Component
public class SunBillServiceImpl extends BaseServiceImpl<SunBill, String> implements SunBillService{
	
	@Autowired
	private SunBillDao billDao;
	
	@Override
	public BaseDao<SunBill, String> getDao() {
		return billDao;
	}

	@Override
	public List<SunZhDaybook> findBillByMid(String mid) {
		return billDao.findBillByMid(mid);
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
	public Page<SunBill> findSunBillByProperty(final List<PropertyFilter> filters, Pageable pageable) {
		Page<SunBill>  page = billDao.findAll(
		new Specification<SunBill>(){
			@SuppressWarnings("incomplete-switch")
			public Predicate toPredicate(Root<SunBill> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
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
			}}, (Pageable) new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),new Sort(Direction.DESC, new String[] { "createTime"})));
		return page;
	}
	
	

}
