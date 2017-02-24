package com.ybt.model.work;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;

/**
 * @file YbtDmPattern.java 创建时间:2016年1月27日上午10:34:02
 * @description 订单商品信息表（ybt_order_good）		
 * @module 模块: 商品基础
 * @author zhz
 * @version 3.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
@Entity
@Table(name = "sun_dd_good")
public class SunDdGood extends BaseEntity{
	private String orderId;         //	订单ID
	private String goodId;          //	商品ID
	private String name;          	//	商品名称
	private String classId;          //	商品种类
	private Double markPrice;       //	标志价格	double
	private Double realityPrice;    //	实际价格	double
	private Integer number;         //	数量	int（11）

	@Column(name = "order_id", length = 32)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "good_id", length = 32)
	public String getGoodId() {
		return this.goodId;
	}

	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}

	@Column(name = "mark_price", precision = 22, scale = 0)
	public Double getMarkPrice() {
		return this.markPrice;
	}

	public void setMarkPrice(Double markPrice) {
		this.markPrice = markPrice;
	}

	@Column(name = "reality_price", precision = 22, scale = 0)
	public Double getRealityPrice() {
		return this.realityPrice;
	}

	public void setRealityPrice(Double realityPrice) {
		this.realityPrice = realityPrice;
	}

	@Column(name = "number")
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "class_id")
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
}
