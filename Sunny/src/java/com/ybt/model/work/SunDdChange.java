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
@Table(name = "sun_dd_change")
public class SunDdChange extends BaseEntity{
	private String orderId;//订单ID
	private String beforeStatus;//前一状态
	private String afterStatus;//后一状态
	private String workerId;//操作人ID

	@Column(name = "order_id")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Column(name = "before_status")
	public String getBeforeStatus() {
		return beforeStatus;
	}

	public void setBeforeStatus(String beforeStatus) {
		this.beforeStatus = beforeStatus;
	}
	@Column(name = "after_status")
	public String getAfterStatus() {
		return afterStatus;
	}

	public void setAfterStatus(String afterStatus) {
		this.afterStatus = afterStatus;
	}
	@Column(name = "worker_id")
	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
}
