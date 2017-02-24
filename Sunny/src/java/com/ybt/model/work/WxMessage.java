package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ybt.model.BaseEntity;
@Entity
@Table(name = "sun_message")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class WxMessage extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "touser")	
	private SunWechatUser touser;
	@Column(name = "msgtype",length=50)	
	private String msgtype;
	@Column(name = "reply",columnDefinition="INT default 0")
	protected int reply = 0;       				//1 回复
	
	public WxMessage() {
		super();
	}
	
	protected WxMessage(String touser,String msgtype) {
		super();
		this.touser = new SunWechatUser();
		this.touser.setId(touser);
		this.msgtype = msgtype;
	}


	public SunWechatUser getTouser() {
		return touser;
	}

	public void setTouser(SunWechatUser touser) {
		this.touser = touser;
	}
	
	public int getReply() {
		return reply;
	}

	public void setReply(int reply) {
		this.reply = reply;
	}

	public String getMsgtype() {
		return msgtype;
	}

	protected void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
}
