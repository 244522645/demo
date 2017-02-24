package com.ybt.model.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;


/**   
 * 文冠果树 
 * @author AndyBao  
 * @version 4.0, 2016年6月29日 下午12:50:16   
 */   
@Entity
@Table(name = "sun_zy_tree")
public class SunZyTree extends BaseEntity{
	
	protected String number;			//卡号
	protected String type;				//树类型，A,B,C,D,... 根据英文字母分类型  A救援卡 B电子救援卡
	protected String userPhone;			//用户手机
	protected String userName;			//用户姓名
	protected String openid;			//微信id
	protected Date bindingTime;			//卡号与用户绑定时间
	protected String parentId;			//父类 id //入口id
	protected String address;			//地点
	protected String images;			//照片
	protected Integer status;			//状态  ,0未激活，1已激活,2已过期
	
	
	@Column(name = "images")
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	@Column(name = "parent_id")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "openid")
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "number")
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "user_phone")
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	@Column(name = "binding_time")
	public Date getBindingTime() {
		return bindingTime;
	}
	public void setBindingTime(Date bindingTime) {
		this.bindingTime = bindingTime;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
