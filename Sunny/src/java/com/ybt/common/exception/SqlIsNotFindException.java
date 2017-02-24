package com.ybt.common.exception;

@SuppressWarnings("serial")
public class SqlIsNotFindException extends Exception {

	private String msg = "Sql文件没有被查询到。";

	public SqlIsNotFindException(String msg) {
		this.msg = msg;
	}

	public SqlIsNotFindException() {
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
