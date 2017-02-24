package com.ybt.model.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ybt.model.BaseEntity;


/**
 * @module  会员   () 
 * @author   bqy  @time:2015年10月13日上午9:33:32
 */
@Entity
@Table(name = "sun_member")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class SunMember extends BaseEntity{

	private Integer type=0;  //会员类型
	private Integer states=0;//会员状态
	private String name;	//名称
	private String sex;		//性别
	private Integer rank=0; //会员级别
	@JsonIgnore //使用json时 ，不包装此属性
	private Date uptime;
	@JsonIgnore //使用json时 ，不包装此属性
	private Date exptime;  		//过期时间
	private Double money=0.00;  //余额
	private String email;		//邮箱
	private String phone;       //手机
	private Integer scores=0;   //积分
	private Integer matt=0;     //推荐次数
	@JsonIgnore 
	private Date jointime;	    //注册时间
	private String joinip;		//注册ip
	@JsonIgnore //使用json时 ，不包装此属性
	private Date logintime;
	@JsonIgnore //使用json时 ，不包装此属性
	private String loginip;     //登录时间
	private String birthday; //生日
	private String province;//所在省份
	private String city;//所在城市
	private String signed;//个性签名signed
	
	@Column(name = "signed",length=500)
	public String getSigned() {
		return signed;
	}
	public void setSigned(String signed) {
		this.signed = signed;
	}
	
	@Column(name = "province")
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}


	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	@Column(name = "type", nullable = false)
	public Integer geType() {
		return this.type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name = "states")
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	
	@Column(name = "name", length = 36)
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex", length = 2)
	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name = "rank", nullable = false)
	public Integer getRank() {
		return this.rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "uptime", nullable = false)
	public Date getUptime() {
		return this.uptime;
	}
	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	@Column(name = "exptime", nullable = false)
	public Date getExptime() {
		return this.exptime;
	}
	public void setExptime(Date exptime) {
		this.exptime = exptime;
	}

	@Column(name = "money", nullable = false)
	public Double getMoney() {
		return this.money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "email", nullable = false, length = 50)
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "phone", nullable = false, length = 50)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "scores", nullable = false)
	public Integer getScores() {
		return this.scores;
	}
	public void setScores(Integer scores) {
		this.scores = scores;
	}

	@Column(name = "matt", nullable = false)
	public Integer getMatt() {
		return this.matt;
	}
	public void setMatt(Integer matt) {
		this.matt = matt;
	}

	@Column(name = "jointime", nullable = false)
	public Date getJointime() {
		return this.jointime;
	}
	public void setJointime(Date jointime) {
		this.jointime = jointime;
	}

	@Column(name = "joinip", length = 16)
	public String getJoinip() {
		return this.joinip;
	}
	public void setJoinip(String joinip) {
		this.joinip = joinip;
	}

	@Column(name = "logintime")
	public Date getLogintime() {
		return this.logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	@Column(name = "loginip", length = 16)
	public String getLoginip() {
		return this.loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}


	@Column(name = "birthday")
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}