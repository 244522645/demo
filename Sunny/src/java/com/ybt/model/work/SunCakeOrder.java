package com.ybt.model.work;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ybt.common.util.TodayDateFormat;
import com.ybt.model.BaseEntity;

/**   
 * 蛋糕订单贺卡
 * @author AndyBao  
 * @version 4.0, 2016年11月24日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_cake_order")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunCakeOrder extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	protected  SunWechatUser userId;            //	会员
	@Column(name = "number")
	protected Long number;            	// 
	@Column(name = "name")
	protected String name;            	// 收货人
	@Column(name = "shop")
	protected String shop;            	// 店铺
	@Column(name = "phone")
	protected String phone;            	//	手机
	@Column(name = "photo_info")
	protected String photoInfo;            	//	照片信息
	@Column(name = "address")
	protected String address;           	 	//地址
	@Column(name = "bless")
	protected String bless;            		//	祝福语
	@Column(name = "waybill")
	protected String waybill ;            		//	运单信息
	
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "photo_id")
	protected SunZyPhoto photo;       		//  图片
	
	@Column(name = "issend",columnDefinition="INT default 0")
	protected int issend = 0;       			// 0未发，1已发
	@Column(name = "ispay",columnDefinition="INT default 0")
	protected int ispay = 0;       		// 状态  ,1已支付
	@Column(name = "status",columnDefinition="INT default 0")
	protected int status = 0;       		// 状态  ,1已支付
	
	@Transient
	private String statusF;
	
	public int getIspay() {
		return ispay;
	}
	public void setIspay(int ispay) {
		this.ispay = ispay;
	}
	public String getStatusF() {
		if(status==1){
			return "完成";
		}
		if(status==0&&ispay==1){
			return "已支付";
		}
		if(status==0&&ispay==0){
			return "待支付";
		}
		
		return "完成";
	}
	public void setStatusF(String statusF) {
		this.statusF = statusF;
	}
	@Transient
	private String shootingTimeF;
	public String getShootingTimeF() {
		return TodayDateFormat.format(createTime);
	}
	public void setShootingTimeF(String shootingTimeF) {
		this.shootingTimeF = shootingTimeF;
	}
	
	public String getPhotoInfo() {
		return photoInfo;
	}
	public void setPhotoInfo(String photoInfo) {
		this.photoInfo = photoInfo;
	}
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
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
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
	public String getBless() {
		return bless;
	}
	public void setBless(String bless) {
		this.bless = bless;
	}
	public String getWaybill() {
		return waybill;
	}
	public void setWaybill(String waybill) {
		this.waybill = waybill;
	}
	public SunZyPhoto getPhoto() {
		return photo;
	}
	public void setPhoto(SunZyPhoto photo) {
		this.photo = photo;
	}
	public int getIssend() {
		return issend;
	}
	public void setIssend(int issend) {
		this.issend = issend;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}