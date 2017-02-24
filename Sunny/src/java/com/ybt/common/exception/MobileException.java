package com.ybt.common.exception;

@SuppressWarnings("serial")
public class MobileException extends Exception {

	private String msg = "数据报错或系统出错";

	public MobileException(String msg) {
		this.msg = msg;
	}

	public MobileException() {
	}

	@Override
	public String getMessage() {
		return msg;
	}

	@Override
	public String toString() {
		return msg;
	}
}
