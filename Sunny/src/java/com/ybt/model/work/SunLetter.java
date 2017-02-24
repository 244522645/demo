package com.ybt.model.work;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ybt.common.util.TodayDateFormat;
import com.ybt.model.BaseEntity;

/**   
 * 信件
 * @author AndyBao  
 * @version 4.0, 2016年7月24日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_letter")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunLetter extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	protected  SunWechatUser userId;            //	会员
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "to_user_id")
	protected  SunWechatUser toUserId;          //	领取会员
	@Column(name = "sender")
	protected String sender;            	// 发件人
	@Column(name = "receiver")
	protected String receiver;            	//	收件人
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "music")
	protected SunZyMusic music;           	 	//	背景音乐
	@Column(name = "stamp")
	protected String stamp;            		//	邮票
	@Column(name = "bless")
	protected String bless;            		//	祝福语
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "image")
	protected SunZyImages image;       		//  图片
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "voice")
	protected SunZyVoice voice;       		// 语音
	@Column(name = "isvoice")
	protected int isvoice = 0;       			// 1有语音
	@Column(name = "isphoto")
	protected int isphoto = 0;       			// 1有照片
	@Column(name = "iscard")
	protected int iscard = 0;       			// 1有阳光卡
	@Column(name = "isorder")
	protected int isorder = 0;       			// 1有祝福照片
	@Column(name = "isread",columnDefinition="INT default 0")
	protected int isread = 0;       			// 0未读，1已读
	@Column(name = "issend",columnDefinition="INT default 0")
	protected int issend = 0;       			// 0未发，1已发
	@Column(name = "status",columnDefinition="INT default 0")
	protected int status = 0;       		// 状态  ,1已支付
	@Transient
	protected int role = 0;    //0发送者， 1接受者 
	@Transient
	private String shootingTimeF;
	public String getShootingTimeF() {
		return TodayDateFormat.format(createTime);
	}
	public void setShootingTimeF(String shootingTimeF) {
		this.shootingTimeF = shootingTimeF;
	}
	
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	public int getIssend() {
		return issend;
	}
	public void setIssend(int issend) {
		this.issend = issend;
	}
	public int getIsorder() {
		return isorder;
	}
	public void setIsorder(int isorder) {
		this.isorder = isorder;
	}
	public int getIscard() {
		return iscard;
	}
	public void setIscard(int iscard) {
		this.iscard = iscard;
	}
	public SunWechatUser getUserId() {
		return userId;
	}
	public void setUserId(SunWechatUser userId) {
		this.userId = userId;
	}
	public SunWechatUser getToUserId() {
		return toUserId;
	}
	public void setToUserId(SunWechatUser toUserId) {
		this.toUserId = toUserId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public SunZyMusic getMusic() {
		return music;
	}
	public void setMusic(SunZyMusic music) {
		this.music = music;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
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
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsvoice() {
		return isvoice;
	}
	public void setIsvoice(int isvoice) {
		this.isvoice = isvoice;
	}
	public int getIsphoto() {
		return isphoto;
	}
	public void setIsphoto(int isphoto) {
		this.isphoto = isphoto;
	}
}