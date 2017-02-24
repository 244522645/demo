package com.ybt.service.base;

import java.io.Serializable;
import java.util.List;

import com.ybt.common.util.PropertyFilter;

/**
 * 基础service类，包括一些通用的增删改查业务。
 * @author Jiang
 * */
public interface IBaseService<T, ID extends Serializable> {
	
	
	/**
	 * 单个实体分页查询
	 * */
	public com.ybt.common.util.Page<T> findAll(final List<PropertyFilter> filters,final com.ybt.common.util.Page<T> page);
	
	/**
	 * 获取全部实体
	 * */
	public List<T> findAll();
	
	/**
	 * 删除实体
	 * */
	public void delete(T t);
	
	/**
	 * 根据主键删除实体
	 * */
	public void delete(ID id);
	
	/**
	 * 批量 删除实体
	 * */
	public void delete(List<T> list);
	
	/**
	 * 根据主键id查找实体
	 * */
	public T findOne(ID id);
	
	/**
	 * 保存实体
	 * */
	public T save(T t);
	
	/**
	 * 批量保存实体
	 * */
	public List<T> save(List<T> list);
	
	
	
}
