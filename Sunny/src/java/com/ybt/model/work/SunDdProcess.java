package com.ybt.model.work;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;


/**
 *
 * @project 芸备胎串货系统
 * @package com.ybt.model.work
 * @file YbtOrderChange.java 创建时间:2016年2月23日下午1:50:56
 * @title 订单流程代码表
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
@Table(name = "sun_dd_process")
public class SunDdProcess extends BaseEntity{
	private String process;//流程说明
	private Integer payment=0;       //	支付方式（0点数换购、1支付、2卡兑换）
	private Integer delivery=0;  	//	配送方式（0下载，1邮件，2邮寄）

	@Column(name = "process")
	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	@Column(name = "payment")
	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	@Column(name = "delivery")
	public Integer getDelivery() {
		return delivery;
	}

	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}

}
