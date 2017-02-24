package com.ybt.model.work;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ybt.model.BaseEntity;

/**   
 * 代言
 * @author AndyBao  
 * @version 4.0, 2016年7月24日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_daiyan")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunDaiyan extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	protected  SunWechatUser userId;            //	会员
	@Column(name = "name")
	protected String name;            			// 姓名
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "qrcode")
	protected SunQrcode qrcode;           	 	// 二维码
	@Column(name = "bless")
	protected String bless;            			//	祝福语
	@Column(name = "theme")
	protected String theme;       			// 皮肤
	@Column(name = "referrer")
	protected String referrer;       			// 推荐人
	@Column(name = "expire_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expireTime;//有效时间
	@Column(name = "flow",columnDefinition="INT default 0")
	protected int flow = 0;       				// 流量
	@Column(name = "share",columnDefinition="INT default 0")
	protected int share = 0;       				// 分享
	@Column(name = "status",columnDefinition="INT default 0")
	protected int status = 0;       			// 状态  ,1有效
	
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public SunWechatUser getUserId() {
		return userId;
	}
	public void setUserId(SunWechatUser userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SunQrcode getQrcode() {
		return qrcode;
	}
	public void setQrcode(SunQrcode qrcode) {
		this.qrcode = qrcode;
	}
	public String getBless() {
		return bless;
	}
	public void setBless(String bless) {
		this.bless = bless;
	}
	public String getReferrer() {
		return referrer;
	}
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public int getFlow() {
		return flow;
	}
	public void setFlow(int flow) {
		this.flow = flow;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}