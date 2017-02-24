package com.ybt.model.work;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;


/**
 * 搜索 关键字 纪录
 */
@Entity
@Table(name = "sun_search_keywords")
public class SunSearchKeywords extends BaseEntity{

	@Column(name = "keywords")
	private String keywords;   //搜搜关键字
	@Column(name = "select_date")
	private String selectDate;   //选择日期
	@Column(name = "type" ,length=10)
	private Integer type;   //搜索类型 
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getSelectDate() {
		return selectDate;
	}
	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
