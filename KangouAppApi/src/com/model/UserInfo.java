package com.model;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String cinamausername;

	private String cinamauserpwd;

	private String parentid;

	private String menu;

	private Date adddate;

	private boolean status;

	private String cinemanickname;

	private String workstartdays;

	private String passwordmd5;

	private Integer userPoint;

	private String mobileType;
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getCinamausername() {
		return cinamausername;
	}

	public void setCinamausername(String cinamausername) {
		this.cinamausername = cinamausername == null ? null : cinamausername.trim();
	}

	public String getCinamauserpwd() {
		return cinamauserpwd;
	}

	public void setCinamauserpwd(String cinamauserpwd) {
		this.cinamauserpwd = cinamauserpwd == null ? null : cinamauserpwd.trim();
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid == null ? null : parentid.trim();
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu == null ? null : menu.trim();
	}

	public Date getAdddate() {
		return adddate;
	}

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getCinemanickname() {
		return cinemanickname;
	}

	public void setCinemanickname(String cinemanickname) {
		this.cinemanickname = cinemanickname == null ? null : cinemanickname.trim();
	}

	public String getWorkstartdays() {
		return workstartdays;
	}

	public void setWorkstartdays(String workstartdays) {
		this.workstartdays = workstartdays == null ? null : workstartdays.trim();
	}

	public String getPasswordmd5() {
		return passwordmd5;
	}

	public void setPasswordmd5(String passwordmd5) {
		this.passwordmd5 = passwordmd5 == null ? null : passwordmd5.trim();
	}

	public Integer getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(Integer userPoint) {
		this.userPoint = userPoint;
	}

	public String getMobileType() {
		return mobileType;
	}

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

}