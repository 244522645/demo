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
 * 代言用户关联
 * @author AndyBao  
 * @version 4.0, 2016年7月24日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_daiyan_user")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunDaiyanUser extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	protected  SunWechatUser userId;            //	会员
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "daiyan_id")
	protected  SunDaiyan daiyan;          //	代言人
	@Column(name = "status",columnDefinition="INT default 0")
	protected int status = 0;       		// 状态  
	public SunWechatUser getUserId() {
		return userId;
	}
	public void setUserId(SunWechatUser userId) {
		this.userId = userId;
	}
	public SunDaiyan getDaiyan() {
		return daiyan;
	}
	public void setDaiyan(SunDaiyan daiyan) {
		this.daiyan = daiyan;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}