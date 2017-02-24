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
 *摄影师
 *
 */
@Entity
@Table(name = "sun_zy_photo_grapher")
public class SunZyPhotoGrapher extends BaseEntity{
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "head")
	protected SunZyImages head;   		// 图片ID
	@Column(name = "name")
	protected String name;   	//	本地名
	@Column(name = "phone")
	protected String phone; 		// 摄影师ID
	@Column(name = "email")
	protected String email;  	// 摄影师名称
	@Column(name = "tags")
	protected String tags; 			//拍摄地
	@Column(name = "introduce")
	protected String introduce; 	//介绍
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "work")
	protected SunZyImages work;   		// 图片ID
	@Column(name = "day_count")
	protected int dayCount; 	//介绍
	
	public SunZyImages getWork() {
		return work;
	}
	public void setWork(SunZyImages work) {
		this.work = work;
	}
	public SunZyImages getHead() {
		return head;
	}
	public void setHead(SunZyImages head) {
		this.head = head;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getDayCount() {
		return dayCount;
	}
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}
	
}
