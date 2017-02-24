package com.ybt.model.work;

import java.math.BigDecimal;
import java.util.Date;

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
 * 一对一挑战表
 * 张卫恒
 * 2017-02-09
 */
@Entity
@Table(name = "crow_pk_one")
public class CrowPkOne extends BaseEntity {

	@Column(name = "start_date")
	private Date startDate; // 开始时间
	@Column(name = "end_date")
	private Date endDate; // 结束时间
	@Column(name ="stop_date")
	private Date stopDate;    //停止日期
	@Column(name ="other_stop_date")
	private Date otherStopDate;    //停止日期
	@Column(name = "day")
	private int day; // 挑战天数
	@Column(name = "money")
	private BigDecimal money; // 挑战金额
	@Column(name = "pk_type")
	private int pkType; // 挑战类型 1:一对一
	@Column(name = "pk_status")
	private int pkStatus; // 挑战状态 0是进行中 2是挑战结束
	@Column(name = "pk_result")
	private int pkResult; // 挑战结果 1.发起人win 2.应战win 3.双赢 4.双输 5.进行中
	@Column(name = "is_rec")
	private String is_rec; // 是否接受挑战者  (0:是，1：不是)
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	private SunWechatUser user; // 用户id
	@Column(name = "user_pk_status")
	private int userPkStatus; // 用户挑战结果  0代表失败 1代表成功 2代表挑战中 3 未开始
	@Column(name = "user_pk_complete_day")
	private int userPkCompleteDay; // 用户挑战完成天数
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "other_user_id")
	private SunWechatUser touser; // 用户id
	@Column(name = "other_user_pk_status")
	private int otherUserPkStatus; // 对方挑战结果  0代表失败 1代表成功 2代表挑战中 3 未开始
	@Column(name = "other_user_pk_complete_day")
	private int otherUserPkCompleteDay; // 对方挑战完成天数
	
	@Column(name ="compute_time")
	private Date computeTime;    //计算时间
	
	
	public String getIs_rec() {
		return is_rec;
	}
	public void setIs_rec(String is_rec) {
		this.is_rec = is_rec;
	}
	public Date getComputeTime() {
		return computeTime;
	}
	public void setComputeTime(Date computeTime) {
		this.computeTime = computeTime;
	}
	public Date getOtherStopDate() {
		return otherStopDate;
	}
	public void setOtherStopDate(Date otherStopDate) {
		this.otherStopDate = otherStopDate;
	}
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
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
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public int getPkType() {
		return pkType;
	}
	public void setPkType(int pkType) {
		this.pkType = pkType;
	}
	public int getPkStatus() {
		return pkStatus;
	}
	public void setPkStatus(int pkStatus) {
		this.pkStatus = pkStatus;
	}
	public int getPkResult() {
		return pkResult;
	}
	public void setPkResult(int pkResult) {
		this.pkResult = pkResult;
	}
	public SunWechatUser getUser() {
		return user;
	}
	public void setUser(SunWechatUser user) {
		this.user = user;
	}
	public int getUserPkStatus() {
		return userPkStatus;
	}
	public void setUserPkStatus(int userPkStatus) {
		this.userPkStatus = userPkStatus;
	}
	public int getUserPkCompleteDay() {
		return userPkCompleteDay;
	}
	public void setUserPkCompleteDay(int userPkCompleteDay) {
		this.userPkCompleteDay = userPkCompleteDay;
	}
	
	public SunWechatUser getTouser() {
		return touser;
	}
	public void setTouser(SunWechatUser touser) {
		this.touser = touser;
	}
	public int getOtherUserPkStatus() {
		return otherUserPkStatus;
	}
	public void setOtherUserPkStatus(int otherUserPkStatus) {
		this.otherUserPkStatus = otherUserPkStatus;
	}
	public int getOtherUserPkCompleteDay() {
		return otherUserPkCompleteDay;
	}
	public void setOtherUserPkCompleteDay(int otherUserPkCompleteDay) {
		this.otherUserPkCompleteDay = otherUserPkCompleteDay;
	}


}
