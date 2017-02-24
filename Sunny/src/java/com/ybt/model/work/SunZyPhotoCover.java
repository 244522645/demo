package com.ybt.model.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ybt.common.util.TodayDateFormat;
import com.ybt.model.BaseEntity;

/**
 *
 * @project 云备胎微信版
 * @package com.ybt.model.work
 * @file SunZyPhoto.java 创建时间:2016年6月15日下午3:53:52
 * @title 投稿
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2016 云讯科技有限公司
 * @company 云讯科技有限公司
 * @module 模块: 照片商品表
 * @author   高艳花
 * @reviewer 金双江
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@Entity
@Table(name = "sun_zy_photo_cover")
public class SunZyPhotoCover extends BaseEntity{
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "img_id")
	private SunZyImages imgId;   		// 图片ID
	private String localName;   	//	本地名
	private String cameristId; 		// 摄影师ID
	private String cameristName;  	// 摄影师名称
	private String title;   //标题
	private Date shootingTime;  // 摄影时间
	private String address;  	//摄影地点
	private String story;  			// 照片故事
	
	private String workerId;  	// 审核人ID
	private int released=0;  	// 发布标志(0未发布，1已发布)
	
	@SuppressWarnings("unused")
	private String shootingTimeF;
	@Transient
	public String getShootingTimeF() {
		return TodayDateFormat.format(shootingTime);
	}
	public void setShootingTimeF(String shootingTimeF) {
		this.shootingTimeF = shootingTimeF;
	}
	
	@Column(name = "local_name")
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public SunZyImages getImgId() {
		return this.imgId;
	}
	public void setImgId(SunZyImages imgId) {
		this.imgId = imgId;
	}
	@Column(name = "camerist_id", length = 32)
	public String getCameristId() {
		return cameristId;
	}
	public void setCameristId(String cameristId) {
		this.cameristId = cameristId;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "camerist_name", length = 50)
	public String getCameristName() {
		return cameristName;
	}
	public void setCameristName(String cameristName) {
		this.cameristName = cameristName;
	}
	@Column(name = "story", length = 500)
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	@Column(name = "shooting_time")
	public Date getShootingTime() {
		return shootingTime;
	}
	public void setShootingTime(Date shootingTime) {
		this.shootingTime = shootingTime;
	}
	@Column(name = "address", length = 200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "released", length = 1)
	public int getReleased() {
		return released;
	}
	public void setReleased(int released) {
		this.released = released;
	}
	
	@Column(name = "worker_id", length = 32)
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
}
