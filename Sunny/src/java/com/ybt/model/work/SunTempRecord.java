package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;

/*
 * 临时纪录
 */
@Entity
@Table(name = "sun_temp_record")
public class SunTempRecord extends BaseEntity{
	
	@Column(name = "type")
	protected String type;			//纪录类型，GIFT_ECARD_20161015、
	@Column(name = "user_id")
	protected String userId;			// 绑定人id
	@Column(name = "content")
	protected String content;			//内容
	@Column(name = "level")
	protected Integer level;			//等级状态  0 1 2 3 4 5
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
}
