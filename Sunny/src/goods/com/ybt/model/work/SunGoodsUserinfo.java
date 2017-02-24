package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ybt.model.BaseEntity;

/**
 * 收货信息
 */
@Entity
@Table(name = "sun_goods_userinfo")
public class SunGoodsUserinfo extends BaseEntity{

/*
--------------------------------
userid(消费者id)   PKString(50)
oid(订单号)        String(80)
consignee(收货人)  String(15)
address(地址)      String(255)
zip(邮编)          Integer
tel(电话)          String(255)
email(邮箱)        String(255)
des(留言)          String(255)
mid(会员id)        FKString(50)
 * */

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	protected  SunWechatUser user;            //	会员
	@Column(name = "name")
	protected String name;
	@Column(name = "address")
	protected String address;
	@Column(name = "zip")
	protected String zip;
	@Column(name = "tel")
	protected String tel;
	@Column(name = "email")
	protected String email;
	@Column(name = "des")
	protected String des;
	public SunWechatUser getUser() {
		return user;
	}
	public void setUser(SunWechatUser user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

}