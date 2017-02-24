package com.ybt.common.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class VerCodeBean {
	private String type; //验证码类型：1商户注册验证，校验不能有已存在的账户信息；2商户绑定注册，校验被绑定的手机号必须有已存在的账户信息；3获取密码
	private String phone; //接收验证码的手机号
	private String name; //接收验证码的手机机主姓名
	private String code; //验证码
	private String password; //密码
	private String contentPhone; //当前操作手机号
	private String charset; //编码类型
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContentPhone() {
		return contentPhone;
	}
	public void setContentPhone(String contentPhone) {
		this.contentPhone = contentPhone;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
}
