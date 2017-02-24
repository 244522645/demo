package com.ybt.model.work;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.ybt.model.BaseEntity;

@Entity
@Table(name = "crow_user_info")
public class CrowUserInfo extends BaseEntity {
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	private SunWechatUser user; // 微信用户
	@Column(name="level",columnDefinition="INT default 0")  //感召人数
	private int level; // 用户级别  0/1/2/3/4 普通、蛋、小鸡、公鸡、凤凰
	@Column(name = "all_days")
	private int alldays; // 总签到天数
	@Column(name = "continuous_day")
	private int continuousDay; // 持续打卡天数
	private int days; // 挑战天数
	private BigDecimal balance; // 余额
	@Column(name = "pk_count")
	private int pkCount; // 总挑战次数

	@Column(name = "km", precision = 22, scale = 2)
	private double km; // 总公里数
	@Column(name = "money", precision = 22, scale = 2)
	private BigDecimal bonus; // 总奖金
	@Column(name = "user_number")
	private int userNumber; // 用户编号
	
	@Column(name="impel",columnDefinition="INT default 0")  //感召人数
	private int impel;
	@OneToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "qrcode_id")
	protected SunQrcode sunQrcode;       		//  二维码
	@Column(name = "first_punch_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date firstPunchDate;//第一次打卡时间
	@Column(name = "last_punch_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastPunchDate;//最后一次时间
	@Column(name="win",columnDefinition="INT default 0")
	private int win;          //挑战成功
	@Column(name="lose",columnDefinition="INT default 0")
	private int lose;         //挑战失败
	

	private int isremind; // 打卡提醒开关
	@Column(name = "remind_time")
	private String remindTime;  //提醒时间
	@Column(name="sublevel",columnDefinition="INT default 0")
	private int sublevel;       //用户level 的 升级过程 0-n代表过程，0  3  6  9  12 15 18 21  鸡蛋到小鸡 8张图   -1 代表孵化失败了,200代表成功
	private int iscard ;//是否显示下载分享卡
	
	
	public int getIscard() {
		return iscard;
	}
	public void setIscard(int iscard) {
		this.iscard = iscard;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public Date getFirstPunchDate() {
		return firstPunchDate;
	}
	public void setFirstPunchDate(Date firstPunchDate) {
		this.firstPunchDate = firstPunchDate;
	}
	public Date getLastPunchDate() {
		return lastPunchDate;
	}
	public void setLastPunchDate(Date lastPunchDate) {
		this.lastPunchDate = lastPunchDate;
	}
	public int getImpel() {
		return impel;
	}

	public void setImpel(int impel) {
		this.impel = impel;
	}

	public SunQrcode getSunQrcode() {
		return sunQrcode;
	}

	public void setSunQrcode(SunQrcode sunQrcode) {
		this.sunQrcode = sunQrcode;
	}

	public SunWechatUser getUser() {
		return user;
	}

	public void setUser(SunWechatUser user) {
		this.user = user;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAlldays() {
		return alldays;
	}

	public void setAlldays(int alldays) {
		this.alldays = alldays;
	}

	public int getContinuousDay() {
		return continuousDay;
	}

	public void setContinuousDay(int continuousDay) {
		this.continuousDay = continuousDay;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getPkCount() {
		return pkCount;
	}

	public void setPkCount(int pkCount) {
		this.pkCount = pkCount;
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public int getIsremind() {
		return isremind;
	}

	public void setIsremind(int isremind) {
		this.isremind = isremind;
	}

	public String getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(String remindTime) {
		this.remindTime = remindTime;
	}

	public int getSublevel() {
		return sublevel;
	}

	public void setSublevel(int sublevel) {
		this.sublevel = sublevel;
	}
	
}
