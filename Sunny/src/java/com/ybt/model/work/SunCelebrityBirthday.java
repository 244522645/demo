package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;

/**
 * @file SunCelebrityBirthday.java 创建时间:2016年18月23日上午10:34:02
 * @description 名人生日
 * @module 模块: 首页基础
 * @author jsj
 * @version 3.0
 * @history 
 */
@Entity
@Table(name = "sun_celebrity_birthday")
public class SunCelebrityBirthday  extends BaseEntity implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "title")
	protected String title;    //文章标题
	
	@Column(name = "content")
	protected String content;   //文章内容
	
	@Column(name = "name")
	protected String name;      //名人名称
	
	@Column(name = "birthday")
	protected String birthday;   //名人生日

	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
