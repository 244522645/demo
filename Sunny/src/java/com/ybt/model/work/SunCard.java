package com.ybt.model.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import com.ybt.common.util.DateTimeHelper;
import com.ybt.model.BaseEntity;

/*
 * 阳光卡
 */
@Entity
@Table(name = "sun_card")
public class SunCard extends BaseEntity{
	
	private String number;			//卡号
	private String cardPassword;		//卡密码
	private Date failureTime;			//失效时间
	private String type;				//卡类型，A,B,C,D,... 根据英文字母分类型  A阳光卡实卡 B电子卡
	private String userId;			// 绑定人id
	private String prefix;			//前缀码
	private Date bindingTime;			//卡号与用户绑定时间
	private Integer status;			//状态  ,0未激活，1已激活，2已使用,3已过期 , 4 24小时候激活 ,5 送人的
	
	@Transient
	private String failureTime_str;			//失效时间  用于页面显示
	
	
	public String getFailureTime_str() {
		return DateTimeHelper.dateTimeToStr(failureTime, DateTimeHelper.DEFAULT_DATE_FORMATE);
	}
	public void setFailureTime_str(String failureTime_str) {
		this.failureTime_str = failureTime_str;
	}
	
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "prefix",length=18)
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	@Column(name = "number",length=18)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Column(name = "card_password")
	public String getCardPassword() {
		return cardPassword;
	}
	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}
	
	
	
	@Column(name = "failure_time")
	public Date getFailureTime() {
		return failureTime;
	}
	public void setFailureTime(Date failureTime) {
		this.failureTime = failureTime;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
