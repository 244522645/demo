package com.ybt.model.work;
// default package

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ybt.model.BaseEntity;

/**   
 * 喜欢
 * @author AndyBao  
 * @version 4.0, 2016年12月12日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_photo_love")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunPhotoLove extends BaseEntity implements java.io.Serializable {

	protected static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER,optional=true)
	@JoinColumn(name = "photo_id")
	protected SunZyPhoto photo;            //	id
	
	
	@ManyToOne(fetch = FetchType.EAGER,optional=true)
	@JoinColumn(name = "user_id")
	protected SunWechatUser user;       //	用户id
	
	public SunZyPhoto getPhoto() {
		return photo;
	}

	public void setPhoto(SunZyPhoto photo) {
		this.photo = photo;
	}

	public SunWechatUser getUser() {
		return user;
	}

	public void setUser(SunWechatUser user) {
		this.user = user;
	}

}