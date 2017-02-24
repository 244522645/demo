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
	
	private String id;  //����
	private String password; //�û���
	private String username; //����
	private  String roleID;	 //userRole id
	private int isZero;  //2��ʾ����0����,1��ʾ������
	private int userType; //1��ʾ������,2��ʾ������,3��ʾֱ��,0��ʾ����Ա�Ͳ���
	private double balance; //���
	private boolean isAbleLogin; //1��ʾ�����¼,0��ʾ�������¼
	private double jmBiLi;  //���˱���
	private double remindMoney; //���ѽ��
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