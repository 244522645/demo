package com.ybt.model.work;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;

/**   
 * 自己挑战表
 * @author AndyBao  
 * @version 4.0, 2017年2月11日 上午10:45:34   
 */   
@Entity
@Table(name = "crow_pk_me")
public class CrowPkMe extends BaseEntity {
	
	
	@Column(name ="order_id")
	private String orderId;     //后加字段   订单id 账单id
	@Column(name ="user_id")
	private String userId;     //用户id
	@Column(name ="start_date")
	private Date startDate;   //开始日期
	@Column(name ="end_date")
	private Date endDate;      //结束日期
	@Column(name ="stop_date")
	private Date stopDate;    //停止日期
	@Column(name ="day",columnDefinition="INT default 0")
	private Integer day;    //挑战天数
	@Column(name ="complete_day",columnDefinition="INT default 0")
	private Integer completeDay;  //完成天数
	@Column(name = "money", precision = 22, scale = 2)
	protected BigDecimal money;   //契约金
	
	@Column(name ="pk_level",columnDefinition="INT default 0")
	private Integer pkLevel;  //挑战级别    1/初级5天, 2/ 高级 21天 
	@Column(name ="pk_type",columnDefinition="INT default 0")
	private Integer pkType;  //挑战类型  1 个人 2 一对一 3 斗鸡
	@Column(name ="pk_status",columnDefinition="INT default 0")
	private int pkStatus;  //挑战状态  0关闭、 1开启   2无结果	
	@Column(name = "pk_result",columnDefinition="INT default 0")
	private Integer pkResult; //挑战结果  0失败 1完成   2无结果	
	
	@Column(name ="compute_time")
	private Date computeTime;    //计算时间
	
	public Date getComputeTime() {
		return computeTime;
	}
	public void setComputeTime(Date computeTime) {
		this.computeTime = computeTime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getCompleteDay() {
		return completeDay;
	}
	public void setCompleteDay(Integer completeDay) {
		this.completeDay = completeDay;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Integer getPkLevel() {
		return pkLevel;
	}
	public void setPkLevel(Integer pkLevel) {
		this.pkLevel = pkLevel;
	}
	public Integer getPkType() {
		return pkType;
	}
	public void setPkType(Integer pkType) {
		this.pkType = pkType;
	}
	public int getPkStatus() {
		return pkStatus;
	}
	public void setPkStatus(int pkStatus) {
		this.pkStatus = pkStatus;
	}
	public Integer getPkResult() {
		return pkResult;
	}
	public void setPkResult(Integer pkResult) {
		this.pkResult = pkResult;
	}

}
