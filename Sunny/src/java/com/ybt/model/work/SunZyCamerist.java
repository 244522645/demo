package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;

/**
 *
 * @project 云备胎微信版
 * @package com.ybt.model.work
 * @file SunZyCamerist.java 创建时间:2016年6月15日下午3:54:51
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2016 云讯科技有限公司
 * @company 云讯科技有限公司
 * @module 模块: 摄影师表
 * @author   高艳花
 * @reviewer 金双江
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */



@Entity
@Table(name = "sun_zy_camerist")
public class SunZyCamerist extends BaseEntity{
	private String phone;   		 	// 手机号（登录账号）
	private String nickname; 			// 摄影师昵称
	private String openId;  			// 微信openId
	private String password;  			// 密码
	private String salt;  				// 秘钥
	private String headPhotosId;  		// 头像ID
	private String name;  				// 姓名
	private String sex;  				// 性别
	private String address;  			// 地址
	private String province;  			// 省
	private String city;  				// 市
	private String area;  				// 区
	private String loginAddress;  		// 登录地址
	private String email;  				// 邮箱
	private String longitude;  			// 经度
	private String latitude;  			// 纬度
	private int    auth;  				// 审核标志(0未发布，1已发布)
	private String workerId;  			// 审核人ID
	private int released=0;  			// 发布标志(0未发布，1已发布)

	@Column(name = "phone", length = 11)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "nickname", length = 30)
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Column(name = "worker_id", length = 32)
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	
	@Column(name = "open_id", length = 32)
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Column(name = "password", length = 50)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "salt", length = 50)
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Column(name = "head_photos_id", length =32)
	public String getHeadPhotosId() {
		return headPhotosId;
	}
	public void setHeadPhotosId(String headPhotosId) {
		this.headPhotosId = headPhotosId;
	}
	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "sex", length = 1)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "address", length = 50)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "province", length = 50)
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name = "city", length = 50)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "area", length = 50)
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Column(name = "login_address", length = 200)
	public String getLoginAddress() {
		return loginAddress;
	}
	public void setLoginAddress(String loginAddress) {
		this.loginAddress = loginAddress;
	}
	@Column(name = "email", length = 50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "released", length = 50)
	public void setReleased(int released) {
		this.released = released;
	}
	
	@Column(name = "longitude", length = 20)
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name = "latitude", length = 20)
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Column(name = "auth", length = 1)
	public int getExplainIds() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	@Column(name = "released", length = 1)
	public int getReleased() {
		return released;
	}
	public void setReleased(Integer released) {
		this.released = released;
	}
}
