package com.ybt.model.work;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ybt.model.BaseEntity;


/**
 * 定制
 */
@Entity
@Table(name = "sun_goods_made")
public class SunGoodsMade extends BaseEntity{
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "image_id")
	protected SunZyImages image;
	protected String text;
	protected String name;
	
	public SunZyImages getImage() {
		return image;
	}
	public void setImage(SunZyImages image) {
		this.image = image;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
