package com.ybt.common.bean;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SmsInformBean extends VerCodeBean{
	private String InformType; //通知类型：1会员卡开通，2会员卡充值；3会员卡消费
	private Date time;//时间
	private String cardNumber;//会员卡名称
	private String cardName;//会员卡卡号
	private double money;//消费金额
	private int count;//消费次数
	private String bName;//商户名
	public String getInformType() {
		return InformType;
	}
	public void setInformType(String informType) {
		InformType = informType;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	
}
