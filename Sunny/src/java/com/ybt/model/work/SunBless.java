package com.ybt.model.work;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.TodayDateFormat;
import com.ybt.model.BaseEntity;

/**   
 * 祝福 贺卡
 * @author AndyBao  
 * @version 4.0, 2016年7月24日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_bless")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunBless extends BaseEntity{

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
	@Column(name = "music")
	protected String music;           	 	//	背景音乐
	@Column(name = "bless")
	protected String bless;            		//	祝福语
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "card_img_id")
	protected SunZyImages cardImage;       		//  图片
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "photo_id")
	protected SunZyPhoto photo;       		//  图片
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "qrcode_id")
	protected SunQrcode sunQrcode;       		//  二维码
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "order_id")
	protected SunDdOrder order;       		//  订单id
	
	@Column(name = "isread",columnDefinition="INT default 0")
	protected int isread = 0;       			// 0未读，1已读
	@Column(name = "issend",columnDefinition="INT default 0")
	protected int issend = 0;       			// 0未发，1已发
	@Column(name = "status",columnDefinition="INT default 0")
	protected int status = 0;       		// 状态  ,1已支付
	@Column(name = "isdown",columnDefinition="INT default 0")
	protected int isdown = 0;       			// 0未下载，1已下载
	@Column(name = "downemail")
	protected String downemail;            		//	下载邮箱
	
	//推荐
	@Column(name = "rec", length = 10)
	private Integer rec ;			// 1 推荐 
	//预订
	@Column(name = "type", length = 10)
	private Integer type ;			//卡片类型，0 订购 1 预订
	
	
	@Column(name = "birthday")
	private Date birthday;			//预订日期
	@Column(name = "citys", length = 10)
	private String citys="";			//城市： 北京-抚远
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "relation_id")
	private MemberRelation relation;			//关系
	
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
	
	
	public SunQrcode getSunQrcode() {
		return sunQrcode;
	}
	public void setSunQrcode(SunQrcode sunQrcode) {
		this.sunQrcode = sunQrcode;
	}
	public String getDownemail() {
		return downemail;
	}
	public void setDownemail(String downemail) {
		this.downemail = downemail;
	}
	public int getIsdown() {
		return isdown;
	}
	public void setIsdown(int isdown) {
		this.isdown = isdown;
	}
	public Date getBirthday() {
		try{
			if(type==null){
				return birthday;
			}
			if(birthday!=null){
				return birthday;
			}
			if(type==1&&relation!=null&&relation.getBirthday()!=null){
				birthday = DateUtil.getBirthDay(relation.getBirthday());
			}
			
		}catch(Exception e){e.printStackTrace();}
		
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getRec() {
		return rec;
	}
	public void setRec(Integer rec) {
		this.rec = rec;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCitys() {
		return citys;
	}
	public void setCitys(String citys) {
		this.citys = citys;
	}
	public MemberRelation getRelation() {
		return relation;
	}
	public void setRelation(MemberRelation relation) {
		this.relation = relation;
	}
	public int getIssend() {
		return issend;
	}
	public void setIssend(int issend) {
		this.issend = issend;
	}
	public SunZyPhoto getPhoto() {
		return photo;
	}
	public void setPhoto(SunZyPhoto photo) {
		this.photo = photo;
	}
	public SunDdOrder getOrder() {
		return order;
	}
	public void setOrder(SunDdOrder order) {
		this.order = order;
	}
	public SunZyImages getCardImage() {
		return cardImage;
	}
	public void setCardImage(SunZyImages cardImage) {
		this.cardImage = cardImage;
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
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public String getBless() {
		return bless;
	}
	public void setBless(String bless) {
		this.bless = bless;
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
}