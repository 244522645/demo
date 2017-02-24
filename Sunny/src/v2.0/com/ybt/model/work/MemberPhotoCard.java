package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ybt.model.BaseEntity;

/**
 *  日出卡片
 */
/**   
 * This class is used for ...   
 * @author AndyBao  
 * @version 4.0, 2017年1月4日 上午8:47:39   
 */   
@Entity
@Table(name = "member_photo_card")
public class MemberPhotoCard extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	protected  SunWechatUser user;            //	会员
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "to_user_id")
	protected  SunWechatUser toUser;          //	领取会员
	@ManyToOne(fetch = FetchType.LAZY,optional=true)
	@JoinColumn(name = "photo_id")
	private SunZyPhoto photo;     
	
	@Column(name = "content")
	private String content;       //卡片内容
	
	@Column(name = "citys")
	private String citys;       //城市
	
	@ManyToOne(fetch = FetchType.LAZY,optional=true)
	@JoinColumn(name = "relation")
	private MemberRelation relation;  //关系

	@Column(name = "type", length = 10)
	private String type="birthday";			//卡片类型，默认 birthday
	
	public SunWechatUser getToUser() {
		return toUser;
	}

	public void setToUser(SunWechatUser toUser) {
		this.toUser = toUser;
	}

	public SunWechatUser getUser() {
		return user;
	}

	public void setUser(SunWechatUser user) {
		this.user = user;
	}

	public String getCitys() {
		return citys;
	}

	public void setCitys(String citys) {
		this.citys = citys;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MemberRelation getRelation() {
		return relation;
	}

	public void setRelation(MemberRelation relation) {
		this.relation = relation;
	}

	public SunZyPhoto getPhoto() {
		return photo;
	}

	public void setPhoto(SunZyPhoto photo) {
		this.photo = photo;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}