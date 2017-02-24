package com.ybt.common.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *  jsonBean
 * @author buddy
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResultTBean<T> extends ResultBean{
	private T b;

	public T getB() {
		return b;
	}

	public void setB(T b) {
		this.b = b;
	}
	
}
