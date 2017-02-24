package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;


/**   
 * 意见反馈
 * @author AndyBao  
 * @version 4.0, 2016年6月29日 下午12:50:16   
 */   
@Entity
@Table(name = "sun_feedback")
public class SunFeedback extends BaseEntity{
	
	@Column(name = "contact")
	protected String contact;			//用户联系方式
	@Column(name = "openid")
	protected String openid;			//微信id
	@Column(name = "body")
	protected String body;			//反馈内容
	@Column(name = "type")
	protected String type;			//反馈类型
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
