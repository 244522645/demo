package com.ybt.model.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @file YbtWechat.java 创建时间:2015年9月7日上午9:13:24
 * @description 描述（微信信息-公众号会员表）
 * @module 模块: 用户微信信息
 * @author   bqy
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
@Entity
@Table(name = "sun_wechat")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunWechatUser {
	private String id; // 主键 openId 微信唯一标示,
	private String address; // 地址,
	private String phone; // 手机号,
	private String wechatId; // 微信号码,一般获取不到
	private String wechatNickname; // 微信昵称,
	private String wechatHeadUrl; // 微信头像地址,
	private String language;
	private String city;//城市
	private String province;
	private Integer sex;			//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String unionid;			//多个公众号之间用户帐号互通UnionID机制
	private Integer deleted = 0; // 删除标志, 1 标示删除，0标示未删除
	private Date createTime; // 创建时间,
	private Date updateTime; // 创建时间,
	private Date deletedTime; // 删除时间,
	private String longitude; // 经度,
	private String latitude; // 纬度
	@Column(name = "channel")
	private String channel; // 渠道
	private Integer subscribe = 0;//类型：0 未关注 ，1 已关注 ,2 无授权 无信息；

	@Id
	@Column(name = "id", length = 100)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Column(name = "wechat_id")
	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	@Column(name = "wechat_nickname")
	public String getWechatNickname() {
		return wechatNickname;
	}

	public void setWechatNickname(String wechatNickname) {
		this.wechatNickname = wechatNickname;
	}

	@Column(name = "wechat_head_url")
	public String getWechatHeadUrl() {
		return wechatHeadUrl;
	}

	public void setWechatHeadUrl(String wechatHeadUrl) {
		this.wechatHeadUrl = wechatHeadUrl;
	}

	@Column(name = "deleted")
	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "deleted_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
	}

	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "subscribe")
	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	@Column(name = "language")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "unionid")
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
}
