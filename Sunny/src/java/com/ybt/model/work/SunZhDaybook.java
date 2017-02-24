package com.ybt.model.work;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * YbtTradeDaybook entity. @author MyEclipse Persistence Tools
 * 芸备胎账户交易记录表（ybt_daybook）		
 */
@Entity
@Table(name = "sun_zh_daybook")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunZhDaybook{

	private String id;          //	id
	private String orderId;     //	订单ID
	private String buyerId;     //	付款方ID
	private String sellerId;    //	收款方ID
	private Double originalMoney; //账户原记录
	private Double money;       //	金额
	private String status;      //	状态（0已提交，1成功到账，2到账失败）
	private String type;        //	交易类型(0转入,1转出，2芸备胎补贴,3芸备胎提现到银行卡，4手续费)
	private String payType;     //	支付方式代码（来自ping++,and 芸备胎银行打款）
	private String pingppId;    //	ping++接口信息id(银行流水号也计入本字段)
	private Integer deleted=0;     //	删除标志（0正常，1删除）
	private Date createTime = new Date();    //	创建时间
	private Date updateTime = new Date();    //	修改时间

	// Property accessors
	@Id
	@GenericGenerator(name = "id", strategy = "uuid")
	@GeneratedValue(generator = "id")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "order_id", length = 32)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "buyer_id", length = 32)
	public String getBuyerId() {
		return this.buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	@Column(name = "seller_id", length = 32)
	public String getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name = "money", precision = 22, scale = 0)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "status", length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "type", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "pay_type", length = 20)
	public String getPayType() {
		return this.payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "pingpp_id", length = 20)
	public String getPingppId() {
		return this.pingppId;
	}

	public void setPingppId(String pingppId) {
		this.pingppId = pingppId;
	}

	@Column(name = "deleted")
	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@Column(name = "create_time", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "original_money", precision = 22, scale = 0)
	public Double getOriginalMoney() {
		return originalMoney;
	}
	public void setOriginalMoney(Double originalMoney) {
		this.originalMoney = originalMoney;
	}
}