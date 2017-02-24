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
 * @module 模块: 商品种类列表
 * @author   高艳花
 * @reviewer 金双江
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@Entity
@Table(name = "sun_goods_sku")
public class SunGoodsSku extends BaseEntity{

	private String goodId;   		// 商品ID
	private String proValue; 		// 
	private Double price;  			// 价格
	private Integer number;  		// 数量
	private Integer browseNum;  	// 浏览量
	private Integer salesNum;  		// 销售量
	
	@Column(name = "good_id", length = 32)
	public String getGoodId() {
		return goodId;
	}
	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}
	@Column(name = "pro_value", length = 500)
	public String getProValue() {
		return proValue;
	}
	public void setProValue(String proValue) {
		this.proValue = proValue;
	}
	@Column(name = "price")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Column(name = "number")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Column(name = "browse_num", length =10)
	public Integer getBrowseNum() {
		return browseNum;
	}
	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}
	
	@Column(name = "sales_num", length =10)
	public Integer getSalesNum() {
		return salesNum;
	}
	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

}
