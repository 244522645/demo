package com.model;

/**
 * KUser entity. @author MyEclipse Persistence Tools
 */
public class KUser implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9040156366503069810L;
	// Fields

	/**
	 * 
	 */
	
	private String id;  //主键
	private String password; //用户名
	private String username; //密码
	private  String roleID;	 //userRole id
	private int isZero;  //2表示可以0余额开卡,1表示不可以
	private int userType; //1表示代理商,2表示加盟商,3表示直销,0表示管理员和财务
	private double balance; //余款
	private boolean isAbleLogin; //1表示允许登录,0表示不允许登录
	private double jmBiLi;  //加盟比例
	private double remindMoney; //提醒金额
	public KUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KUser(String id, String password, String username, String roleID, int isZero, int userType, double balance,
			boolean isAbleLogin, double jmBiLi, double remindMoney) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
		this.roleID = roleID;
		this.isZero = isZero;
		this.userType = userType;
		this.balance = balance;
		this.isAbleLogin = isAbleLogin;
		this.jmBiLi = jmBiLi;
		this.remindMoney = remindMoney;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public int getIsZero() {
		return isZero;
	}
	public void setIsZero(int isZero) {
		this.isZero = isZero;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public boolean isAbleLogin() {
		return isAbleLogin;
	}
	public void setAbleLogin(boolean isAbleLogin) {
		this.isAbleLogin = isAbleLogin;
	}
	public double getJmBiLi() {
		return jmBiLi;
	}
	public void setJmBiLi(double jmBiLi) {
		this.jmBiLi = jmBiLi;
	}
	public double getRemindMoney() {
		return remindMoney;
	}
	public void setRemindMoney(double remindMoney) {
		this.remindMoney = remindMoney;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}