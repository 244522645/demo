package com.ybt.common.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *  jsonBean
 * @author buddy
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResultBean {
	private String m="";//消息
	private int s=0;//状态 0失败 1成功
	public String getM() {
		return m;
	}
	public void setM(String m) {
		this.m = m;
	}
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}
	
}
