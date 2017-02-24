package com.ybt.common.bean;

import javax.persistence.Column;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @description 串货搜索列表
 */
@JsonSerialize
public class shoppingCartBean {
	private String busName;
	private String id;              //	编号	varchar（32）
	private String businessId;      //	卖方商户ID	varchar（32）
	private String stockId;          //	商品ID	varchar（32）
	private String name;          //	商品名称ID	varchar（32）
	private Double markPrice;       //	标志价格	double
	private Double realityPrice;    //	实际价格	double
	private Integer number;         //	数量	int（11）
	private Integer delete;     //是否有效
	//配送设置
	private String payOnLine;//线上付款 1支持 0不支持 null未设置
	private String payOffLine;//货到付款 1支持 0不支持 null未设置
	private String selfGet;//自提   1支持 0不支持 null未设置
	private String sendGet;//送货上门  1支持 0不支持 null未设置
	private Double bottomPrice;//送货起价 null未设置
	private Double unitPrice;//每公里单价 
	private Double scope;//配送范围
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStockId() {
		return this.stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public Double getMarkPrice() {
		return this.markPrice;
	}

	public void setMarkPrice(Double markPrice) {
		this.markPrice = markPrice;
	}

	public Double getRealityPrice() {
		return this.realityPrice;
	}

	public void setRealityPrice(Double realityPrice) {
		this.realityPrice = realityPrice;
	}

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

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public Integer getDelete() {
		return delete;
	}

	public void setDelete(Integer delete) {
		this.delete = delete;
	}

	public String getPayOnLine() {
		return payOnLine;
	}

	public void setPayOnLine(String payOnLine) {
		this.payOnLine = payOnLine;
	}

	public String getPayOffLine() {
		return payOffLine;
	}

	public void setPayOffLine(String payOffLine) {
		this.payOffLine = payOffLine;
	}

	public String getSelfGet() {
		return selfGet;
	}

	public void setSelfGet(String selfGet) {
		this.selfGet = selfGet;
	}

	public String getSendGet() {
		return sendGet;
	}

	public void setSendGet(String sendGet) {
		this.sendGet = sendGet;
	}

	public Double getBottomPrice() {
		return bottomPrice;
	}

	public void setBottomPrice(Double bottomPrice) {
		this.bottomPrice = bottomPrice;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getScope() {
		return scope;
	}

	public void setScope(Double scope) {
		this.scope = scope;
	}
}
