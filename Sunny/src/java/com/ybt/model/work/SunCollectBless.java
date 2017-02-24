package com.ybt.model.work;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ybt.model.BaseEntity;

/**   
 * 收集祝福
 * @author AndyBao  
 * @version 4.0, 2016年7月24日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_collect_bless")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunCollectBless extends BaseEntity implements java.io.Serializable {

	protected static final long serialVersionUID = 1L;
	
	@Column(name = "collect_id",length=50)
	protected String collectId;            //	祝福id
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	protected  SunWechatUser userId;            //	会员

	@Column(name = "bless")
	protected String bless;            //	祝福语
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "image")
	protected SunZyImages image;       //   图片
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "voice")
	protected SunZyVoice voice;       // 语音
	
	@Column(name = "read")
	protected int read = 0;       // 已读
	
	@Column(name = "good")
	protected long good;  //	集赞

	public String getCollectId() {
		return collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public SunWechatUser getUserId() {
		return userId;
	}

	public void setUserId(SunWechatUser userId) {
		this.userId = userId;
	}

	public String getBless() {
		return bless;
	}

	public void setBless(String bless) {
		this.bless = bless;
	}

	public SunZyImages getImage() {
		return image;
	}

	public void setImage(SunZyImages image) {
		this.image = image;
	}

	public SunZyVoice getVoice() {
		return voice;
	}

	public void setVoice(SunZyVoice voice) {
		this.voice = voice;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public long getGood() {
		return good;
	}

	public void setGood(long good) {
		this.good = good;
	}
	
}