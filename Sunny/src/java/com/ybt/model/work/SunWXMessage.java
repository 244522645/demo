package com.ybt.model.work;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;

/*
 * 微信消息管理
 */
@Entity
@Table(name = "sun_wx_message")
public class SunWXMessage extends BaseEntity{
	
	protected String user;     //平台
	
	protected String touser;   //用户

	protected String templateId; //模板id

	protected String url;	//消息地址

	protected String topcolor; //模板消息颜色

	//private LinkedHashMap<String, TemplateMessageItem> data;
	protected String data;  //发送数据
	
	protected String message; //消息内容
	
	protected String title; //消息标题
	
	protected String msgtype; //消息类型 
	
	protected int states; //发送状态

	public  SunWXMessage(String touser,String message){
		this.touser=touser;
		this.message=message;
		this.msgtype="text";
		this.states=0;
	}
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public int getStates() {
		return states;
	}

	public void setStates(int states) {
		this.states = states;
	}
	
}
