package com.ybt.model.work;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;


/**
 *
 * @project 芸备胎串货系统
 * @package com.ybt.model.work
 * @file YbtDmOrderStatus.java 创建时间:2016年2月23日下午1:50:56
 * @title 订单状态代码表
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
@Table(name = "sun_dd_status")
public class SunDdStatus extends BaseEntity{
	private Integer performer;//执行者，0买家，1卖家
	private String buyerStatus;//买家显示状态
	private String sellerStatus;//卖家显示状态
	private String buyerWait;//买家等待状态
	private String sellerWait;//卖家等待状态
	private String buyerButton;//买家显示按钮
	private String sellerButton;//卖家显示按钮
	private String buyerPrompt;//买家提示信息
	private String sellerPrompt;//卖家提示信息
	private String nextId;//下一状态代码ID
	private String processId;//订单流程种类代码
	
	@Column(name = "performer")
	public Integer getPerformer() {
		return performer;
	}

	public void setPerformer(Integer performer) {
		this.performer = performer;
	}

	@Column(name = "buyer_status")
	public String getBuyerStatus() {
		return buyerStatus;
	}

	public void setBuyerStatus(String buyerStatus) {
		this.buyerStatus = buyerStatus;
	}
	
	@Column(name = "seller_status")
	public String getSellerStatus() {
		return sellerStatus;
	}

	public void setSellerStatus(String sellerStatus) {
		this.sellerStatus = sellerStatus;
	}
	
	@Column(name = "buyer_wait")
	public String getBuyerWait() {
		return buyerWait;
	}

	public void setBuyerWait(String buyerWait) {
		this.buyerWait = buyerWait;
	}
	
	@Column(name = "seller_wait")
	public String getSellerWait() {
		return sellerWait;
	}

	public void setSellerWait(String sellerWait) {
		this.sellerWait = sellerWait;
	}
	
	@Column(name = "buyer_button")
	public String getBuyerButton() {
		return buyerButton;
	}

	public void setBuyerButton(String buyerButton) {
		this.buyerButton = buyerButton;
	}
	
	@Column(name = "seller_button")
	public String getSellerButton() {
		return sellerButton;
	}

	public void setSellerButton(String sellerButton) {
		this.sellerButton = sellerButton;
	}
	
	@Column(name = "buyer_prompt")
	public String getBuyerPrompt() {
		return buyerPrompt;
	}

	public void setBuyerPrompt(String buyerPrompt) {
		this.buyerPrompt = buyerPrompt;
	}
	
	@Column(name = "seller_prompt")
	public String getSellerPrompt() {
		return sellerPrompt;
	}

	public void setSellerPrompt(String sellerPrompt) {
		this.sellerPrompt = sellerPrompt;
	}
	
	@Column(name = "next_id")
	public String getNextId() {
		return nextId;
	}
	public void setNextId(String nextId) {
		this.nextId = nextId;
	}
	
	@Column(name = "process_id")
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

}
