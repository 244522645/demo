package com.ybt.common.bean;


/*
 * 百度生成短网址bean  请参见：http://dwz.cn/api
 * 
 * 2.怎样生成一个短网址？
	请求：向dwz.cn/create.php发送post请求，发送数据包括url=长网址
	返回：json格式的数据
	status!=0 出错，查看err_msg获得错误信息（UTF-8编码）
	成功，返回生成的短网址 tinyurl字段
	
	3.自定义短网址
	请求：向dwz.cn/create.php发送post请求，发送数据包括url=长网址&alias=自定义网址
	返回：json格式的数据
	Status!=0 出错，查看err_msg获得错误信息（UTF-8编码）
	成功，返回生成的短网址 tinyurl字段
	
	4.显示原网址
	请求：向dwz.cn/query.php发送post请求，发送数据包括tinyurl=查询的短地址
	返回：json格式的数据
	status!=0 出错，查看err_msg获得错误信息（UTF-8编码）
	成功，返回原网址 longurl字段
 */
public class BaiduShortUrlBean {
	private int status;//status!=0 出错
	private String err_msg;//查看err_msg获得错误信息（UTF-8编码）
	private String tinyurl;//短网址 tinyurl字段
	private String longurl;//返回原网址 longurl字段
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	public String getTinyurl() {
		return tinyurl;
	}
	public void setTinyurl(String tinyurl) {
		this.tinyurl = tinyurl;
	}
	public String getLongurl() {
		return longurl;
	}
	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}
	
	
	
}
