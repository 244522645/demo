package com.ybt.model.work;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ybt.model.BaseEntity;

/**   
 * 蛋糕订单贺卡
 * @author AndyBao  
 * @version 4.0, 2016年11月24日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_cake_shop")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunCakeShop extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	protected  SunWechatUser userId;            //	会员
	@Column(name = "name")
	protected String name;            	// 收货人
	@Column(name = "phone")
	protected String phone;            	//	手机
	@Column(name = "address")
	protected String address;           	 	//地址
	
	public SunWechatUser getUserId() {
		return userId;
	}
	public void setUserId(SunWechatUser userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}