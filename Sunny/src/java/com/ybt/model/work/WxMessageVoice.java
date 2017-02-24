package com.ybt.model.work;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 语音
 *
 */
@Entity
@Table(name = "sun_message_voice")
@PrimaryKeyJoinColumn(name = "id")
public class WxMessageVoice extends WxMessage {

	public WxMessageVoice(String touser,String mediaId) {
		super(touser,"voice");
		this.voice = new SunZyVoice();
		this.voice.setId(mediaId);
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "media_id")	
	public SunZyVoice voice;
	public SunZyVoice getVoice() {
		return voice;
	}
	public void setVoice(SunZyVoice voice) {
		this.voice = voice;
	}

}
