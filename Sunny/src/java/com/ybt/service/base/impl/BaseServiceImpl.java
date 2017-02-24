package com.ybt.service.base.impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.ReflectUtils;
import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunLetter;
import com.ybt.service.base.IBaseService;

public  abstract class BaseServiceImpl<T,ID  extends Serializable> implements IBaseService<T, ID> {
	
	public abstract BaseDao<T,ID> getDao();

	/**
	 * 删除实体
	 * */
	public void delete(T t) {
		getDao().delete(t);
	}
	
	
	/**
	 * 单个实体分页查询
	 * */
	public com.ybt.common.util.Page<T> findAll(final List<PropertyFilter> filters,final com.ybt.common.util.Page<T> page) {
		Page<T> pageSpring = getDao().findAll(new Specification<T>() {
					public Predicate toPredicate(Root<T> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
						List<Predicate> list = new ArrayList<Predicate>();
						for (PropertyFilter pf : filters) {
							switch (pf.getMatchType()) {
							case EQ: // 等于运算符
								list.add(cb.equal(root.get(pf.getPropertyName()).as(pf.getPropertyClass()),pf.getMatchValue()));
								break;
							case LIKE:// 模糊运算符
								list.add(cb.like(root.get(pf.getPropertyName()).as(String.class),"%" + pf.getMatchValue() + "%"));
								break;
							case LT:// 小于
								list.add(cb.lessThan(root.get(pf.getPropertyName()).as(String.class), pf.getMatchValue().toString()));
								break;
							case LE:// 小于等于
								list.add(cb.lessThanOrEqualTo(root.get(pf.getPropertyName()).as(String.class), pf.getMatchValue().toString()));
								break;
							case GT:// 大于
								list.add(cb.greaterThan(root.get(pf.getPropertyName()).as(String.class),((String) pf.getMatchValue())));
								break;
							case GE:// 大于等于
								list.add(cb.greaterThanOrEqualTo(root.get(pf.getPropertyName()).as(String.class),((String) pf.getMatchValue())));
								break;
							case IN:// in
								list.add(cb.in(root.get(pf.getPropertyName())).value(pf.getMatchValue()));
								break;
							case NULL:// null
								list.add(cb.isNull(root.get(pf.getPropertyName()).as(pf.getPropertyClass())));
								break;
							case NOTNULL:// not null
								list.add(cb.isNotNull(root.get(pf.getPropertyName()).as(pf.getPropertyClass())));
								break;
							}
						}
						if(page.getOrderBy()!=null&&!"".equals(page.getOrderBy())){
							if("desc".equals(page.getOrder()))
								query.orderBy(cb.desc(root.get(page.getOrderBy()).as(String.class)));
							else
								query.orderBy(cb.asc(root.get(page.getOrderBy()).as(String.class)));
						}
						Predicate[] p = new Predicate[list.size()];
						query.where(cb.and(list.toArray(p)));
						return query.getRestriction();
					}
				},
				(Pageable) new PageRequest(page.getPageNo() - 1, page.getPageSize()));
		page.setTotalCount(pageSpring.getTotalElements());
		page.setResult(pageSpring.getContent());
		return page;
	}

	public List<T> findAll(){
		return getDao().findAll();
	}
	
	public void delete(ID id) {
		Type type = ReflectUtils.getClassGenricType(this.getClass());
	    Class<?> entityClass = (Class<?>) type;  
		if(ReflectUtils.equalsParam(entityClass, "deleted")){ 
			getDao().deletion(id); //逻辑删除
		}else{                                                
			getDao().delete(id);   //物理删除
		}
	}

	public void delete(List<T> list){
		getDao().delete(list);
	}

	public T findOne(ID id) {
		return getDao().findOne(id);
	}

	public T save(T t) {
		return getDao().save(t);
	}
	
	public List<T> save(List<T> list){
		return getDao().save(list);
	}

}
