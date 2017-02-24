package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "sun_message_text")
@PrimaryKeyJoinColumn(name = "id")
public class WxMessageText extends WxMessage {

	public WxMessageText(String touser) {
		super(touser,"text");
	}

	public WxMessageText(String touser,String content){
		this(touser);
		this.setText(content);
	}
	
	@Column(name = "text",length=500)	
	private String text;

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

}
