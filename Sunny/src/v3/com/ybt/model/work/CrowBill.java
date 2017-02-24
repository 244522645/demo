package com.ybt.model.work;

import java.math.BigDecimal;

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

@Entity
@Table(name = "crow_bill")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CrowBill extends BaseEntity{
	
	@Column(name = "balance",precision=22 ,scale = 2)
	protected BigDecimal balance;  //余额
	@Column(name = "currency" ,length=255)
	protected String currency;  //货币类型  CNY、SUNB
	@Column(name = "message" ,length=255)
	protected String message;   //交易信息
	@Column(name = "user_id")
	protected  String userId;            //	会员
	@Column(name = "leizhu_openID")
	protected  String leizhu_openID;     //擂主ID（发起挑战人）
	@Column(name = "money",precision=22 ,scale = 2)
	protected BigDecimal money;   //应付挑战金额 = 抵扣 + 实付
	@Column(name = "deduct",precision=22 ,scale = 2)
	protected BigDecimal deduct;   //抵扣（例如：钱包余额抵扣）
	@Column(name = "actual_Payment",precision=22 ,scale = 2)
	protected BigDecimal actualPayment;   //实付
	@Column(name = "order_id" ,length=255)
	protected String orderId;  //订单号
	@Column(name = "payType")
	protected String payType;   //交易方式(weixin、alipay)
	@Column(name = "pid")
	protected String pid;   //产品id
	@Column(name = "status")
	protected int status;   //交易状态(0申请中,1成功,2：失败,3:平台已扣款）
	@Column(name = "tradeId")
	protected String tradeId;   //微信交易流水号
	@Column(name = "type")
	protected int type;   //0 支出 1收入
	@Column(name = "instruction")
	protected String instruction;   //交易明细说明
	
	public String getLeizhu_openID() {
		return leizhu_openID;
	}
	public void setLeizhu_openID(String leizhu_openID) {
		this.leizhu_openID = leizhu_openID;
	}
	public BigDecimal getDeduct() {
		return deduct;
	}
	public void setDeduct(BigDecimal deduct) {
		this.deduct = deduct;
	}
	public BigDecimal getActualPayment() {
		return actualPayment;
	}
	public void setActualPayment(BigDecimal actualPayment) {
		this.actualPayment = actualPayment;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	
}
