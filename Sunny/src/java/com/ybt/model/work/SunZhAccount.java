package com.ybt.model.work;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * YbtTradeAccount entity. @author MyEclipse Persistence Tools
 * 芸备胎账户表（ybt_trade_account)		
 */
@Entity
@Table(name = "sun_zh_account")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunZhAccount implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String id;               //	ID
	private Double money;            //	金额（可提现）
	private Double cardMoney;            //	阳光卡金额
	private Double totalMoney;       //	总金额（交易金额，包含不可提现）
	private Date createTime = new Date();  //	创建时间
	private Date updateTime;     //	修改时间
	private Integer version=0;   //jpa乐观锁
	
	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}	


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

	@Column(name = "money", precision = 22, scale = 0)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	@Column(name = "card_money", precision = 22, scale = 0)
	public Double getCardMoney() {
		return cardMoney;
	}
	public void setCardMoney(Double cardMoney) {
		this.cardMoney = cardMoney;
	}
	@Column(name = "total_money", precision = 22, scale = 0)
	public Double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
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

}