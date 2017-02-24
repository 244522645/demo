package com.model;

import java.io.Serializable;
import java.util.Date;

public class NoticeLogs implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer lid;

	private String userid;

	private Integer noticeid;

	private Date readtime;

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid == null ? null : userid.trim();
	}

	public Integer getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(Integer noticeid) {
		this.noticeid = noticeid;
	}

	public Date getReadtime() {
		return readtime;
	}

	public void setReadtime(Date readtime) {
		this.readtime = readtime;
	}
}