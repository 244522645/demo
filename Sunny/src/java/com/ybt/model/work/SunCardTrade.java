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

/*
 * 阳光卡-交易明细
 */
@Entity
@Table(name = "sun_card_trade")
public class SunCardTrade extends BaseEntity{
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "card_id")
	protected SunCard card;			//卡
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	protected  SunWechatUser userId;            //阳光卡的拥有者
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "from_user_id")
	protected  SunWechatUser fromUserId;          //赠送者
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "to_user_id")
	protected  SunWechatUser toUserId;          //接收者
	@Column(name = "to_user_name")
	protected String toUserName;			//收件人
	
	@Column(name = "status",columnDefinition="INT default 0")
	protected Integer status;			//状态 （例如正在路上）
	
	@Column(name = "type",columnDefinition="INT default 0")
	protected Integer type;			//0 送 1 日出消费
	
	
	@Column(name = "in_or_out",columnDefinition="INT default 0")
	protected Integer inout;			//0进   1出 	
	
	@Column(name = "inout_type",columnDefinition="INT default 0")
	protected Integer inoutType;			//0:系统 ,1: 他人送 ;   0:消费,1:赠送他人
	
	public SunWechatUser getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(SunWechatUser fromUserId) {
		this.fromUserId = fromUserId;
	}
	public Integer getInout() {
		return inout;
	}
	public void setInout(Integer inout) {
		this.inout = inout;
	}
	public Integer getInoutType() {
		return inoutType;
	}
	public void setInoutType(Integer inoutType) {
		this.inoutType = inoutType;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public SunCard getCard() {
		return card;
	}
	public void setCard(SunCard card) {
		this.card = card;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
