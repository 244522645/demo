package com.ybt.model.work;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "sun_message_image")
@PrimaryKeyJoinColumn(name = "id")
public class WxMessageImage extends WxMessage {

	public WxMessageImage(String touser,String mediaId) {
		super(touser,"image");
		this.image = new SunZyImages();
		this.image.setId(mediaId);
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "media_id")
	private SunZyImages image;
	public SunZyImages getImage() {
		return image;
	}
	public void setImage(SunZyImages image) {
		this.image = image;
	}

}
