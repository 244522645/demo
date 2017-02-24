package com.model;

import java.io.Serializable;
import java.util.Date;

public class UserError  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer errorid;

    private String erroruserid;

    private Date errortime;

    private Integer errortype;

    private String errornum;

    public Integer getErrorid() {
        return errorid;
    }

    public void setErrorid(Integer errorid) {
        this.errorid = errorid;
    }

    public String getErroruserid() {
        return erroruserid;
    }

    public void setErroruserid(String erroruserid) {
        this.erroruserid = erroruserid == null ? null : erroruserid.trim();
    }

    public Date getErrortime() {
        return errortime;
    }

    public void setErrortime(Date errortime) {
        this.errortime = errortime;
    }

    public Integer getErrortype() {
        return errortype;
    }

    public void setErrortype(Integer errortype) {
        this.errortype = errortype;
    }

	public String getErrornum() {
		return errornum;
	}

	public void setErrornum(String errornum) {
		this.errornum = errornum;
	}

  
}