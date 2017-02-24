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
 *
 * @project 下载纪录
 * @package com.ybt.model.work
 * @file YbtOrderChange.java 创建时间:2016年2月23日下午1:50:56
 * @title 订单状态变更记录表
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2016 云讯科技有限公司
 * @company 云讯科技有限公司
 * @module 模块:订单模块
 * @author   AndyBao
 * @reviewer 金双江
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@Entity
@Table(name = "sun_downrecord")
public class SunDownRecord extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "order_id")
	private SunDdOrder order;  //订单号
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "bless_id")
	private SunBless bless;  //订单号
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	private SunWechatUser user;  // 会员id
	@Column(name = "email")
	private String email;   //email
	@Column(name = "status")
	private Integer status;   //发送状态  0未发送 1 已送
	@Column(name = "message")
	private String message;        //信息
	
	public SunBless getBless() {
		return bless;
	}
	public void setBless(SunBless bless) {
		this.bless = bless;
	}
	public SunDdOrder getOrder() {
		return order;
	}
	public void setOrder(SunDdOrder order) {
		this.order = order;
	}
	public SunWechatUser getUser() {
		return user;
	}
	public void setUser(SunWechatUser user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
