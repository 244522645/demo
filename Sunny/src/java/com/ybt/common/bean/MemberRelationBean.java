package com.ybt.common.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ybt.common.util.DateTimeHelper;
import com.ybt.common.util.DateUtil;
import com.ybt.model.work.MemberRelation;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MemberRelationBean {

	private String id;
	private String relation;
	private String name;
	private String birthday;
	private String days;  //距离过生日还有多少天
	private String mobileNo; //头像
	private String headImg; //头像
	
	public MemberRelationBean() {
		super();
	}

	public MemberRelationBean(MemberRelation mb) {
		super();
		this.id = mb.getId();
		this.relation = mb.getRelation();
		this.name = mb.getName();
		this.mobileNo = mb.getMobileNo();
		this.birthday = DateTimeHelper.dateTimeToStr(mb.getBirthday(), DateTimeHelper.DEFAULT_CN_DATE_FORMATE);
		int days = DateUtil.todayBetweenBirthday(DateTimeHelper.dateTimeToStr(mb.getBirthday(), DateTimeHelper.DEFAULT_DATE_QD_FORMATE));
		if(days < 0){
			this.days = "";
		}else if(days == 0){
			this.days = "今天生日";
		}else{
			this.days = days+"后过生日";
		}
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
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
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	
	
	
}
