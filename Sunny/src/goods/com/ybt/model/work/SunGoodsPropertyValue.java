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
@Table(name = "sun_goods_property_value")
public class SunGoodsPropertyValue extends BaseEntity{

	private String typeId;   			// 属性类别编号
	private String fatherId;   			// 上级属性值
	private String proValue;   			// 属性值
	private Integer dataType;   			// 属性值类型（0文字，1图片）
	private String imId;   				// 图片ID
	private Integer sort;  				// 排序

	@Column(name = "sort", length = 10)
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Column(name = "type_id", length = 32)
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	@Column(name = "father_id", length = 32)
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	@Column(name = "pro_value", length = 100)
	public String getProValue() {
		return proValue;
	}
	public void setProValue(String proValue) {
		this.proValue = proValue;
	}
	@Column(name = "data_type", length = 1)
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	@Column(name = "im_id", length = 32)
	public String getImId() {
		return imId;
	}
	public void setImId(String imId) {
		this.imId = imId;
	}
}
