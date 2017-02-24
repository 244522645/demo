package com.ybt.common.bean;


/**
 * 交易实体返回对象
 * */
public class Result<T>{
	
	public int state = 0;            //返回状态 0 失败 ，1 成功 
	public String message = "";      //错误说明
	private T t; //返回对象
	public Result(){
	}
	public Result(String message,T t){
		if(message==null||"".equals(message))
			this.state = 1;
		this.message = message;
		this.t = t;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getT() {
		return this.t;
	}
	public void setT(T t) {
		if(t!=null){
			this.state = 1;
			this.message = "";
		}
		this.t = t;
	}
	
}
