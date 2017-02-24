package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;


/**
 * @file SunGySms.java 创建时间:2015年8月7日下午2:29:26
 * @description 描述（短信发送记录）
 * @module 模块: 短信
 * @author   bqy
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
@Entity
@Table(name = "sun_gy_sms")
public class SunGySms extends BaseEntity{
	protected String phone;   			// 手机
	protected String code;   			// 码 （验证码，密码，登录码）
	protected String content;   		// 内容
	protected String type; //验证码类型：1商户注册验证，校验不能有已存在的账户信息；2商户绑定注册，校验被绑定的手机号必须有已存在的账户信息；3获取密码
						   //通知类型：1会员卡开通，2会员卡充值；3会员卡消费
	protected Integer states;		    // 发送状态    0 待发送 1 已发送 2 发送异常
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "states")
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	
}
