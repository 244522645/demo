package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;


/**
 *
 * @project 云备胎微信版
 * @package com.ybt.model.work
 * @file SunScGoodClass.java 创建时间:2016年6月20日下午4:58:29
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2016 云讯科技有限公司
 * @company 云讯科技有限公司
 * @module 模块: 商品属性列表
 * @author   高艳花
 * @reviewer 金双江
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */

@Entity
@Table(name = "sun_goods_property")
public class SunGoodsProperty extends BaseEntity{

	private String name; 			// 种类名称
	private String classId;   		// 类别编号
	private Integer sort;  			// 排序
	
	@Column(name = "father_id", length = 32)
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "sort", length = 10)
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
