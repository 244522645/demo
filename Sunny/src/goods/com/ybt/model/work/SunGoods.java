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
@Table(name = "sun_goods")
public class SunGoods extends BaseEntity{

	private String classId;   		// 商品类别ID
	private String title; 			// 商品标题
	private Double lPrice;  		// 最低价格
	private Double hPrice;  		// 最高价格
	private Integer browseNum;  	// 浏览量
	private Integer salesNum;  		// 销售量
	@Column(name = "status")
	private int status=0;  		// 1 上架 0 下架
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name = "class_id", length = 32)
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	@Column(name = "title", length =200)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "l_price", length =10)
	public Double getlPrice() {
		return lPrice;
	}
	public void setlPrice(Double lPrice) {
		this.lPrice = lPrice;
	}
	@Column(name = "h_price", length =10)
	public Double gethPrice() {
		return hPrice;
	}
	public void sethPrice(Double hPrice) {
		this.hPrice = hPrice;
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
