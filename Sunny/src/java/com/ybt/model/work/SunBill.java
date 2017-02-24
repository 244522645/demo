package com.ybt.model.work;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;


/**
 *
 * @project 收支明细
 * @package com.ybt.model.work
 * @file YbtOrderChange.java 创建时间:2016年2月23日下午1:50:56
 * @title 订单状态变更记录表
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2016 云讯科技有限公司
 * @company 云讯科技有限公司
 * @module 模块:订单模块
 * @author   高艳花
 * @reviewer 金双江
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@Entity
@Table(name = "sun_bill")
public class SunBill extends BaseEntity{

	private String oid;  //订单号
	private String mid;  // 会员id
	private Integer money;   //价格
	private Integer balance;    //余额
	private String pid;   //产品id
	private Integer type;   //0 支出 1收入
	private String currency = "RMB"; //货币类型  RMB、SUNB
	private Integer status = 0;     //	交易类型(0转入,1转出，2芸备胎补贴,3芸备胎提现到银行卡，4手续费)
	private String paytype;        //交易方式 alipay wxpay wall
	private String tradeid;  // 交易流水号
	private String message;        //交易信息

	
	@Column(name = "currency" ,length=10)
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "oid")
	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	@Column(name = "mid")
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	@Column(name = "pid")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	@Column(name = "type",columnDefinition="int default 0")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "paytype")
	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	@Column(name = "tradeid")
	public String getTradeid() {
		return tradeid;
	}

	public void setTradeid(String tradeid) {
		this.tradeid = tradeid;
	}

	@Column(name = "money")
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	@Column(name = "balance")
	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

}
