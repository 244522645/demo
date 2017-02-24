package com.model;

import java.io.Serializable;
import java.util.Date;

public class Version implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer vid;

    private String versionnum;

    private String url;

    private Date addtime;

    private String mobiletype;

    private String message;

    private Boolean forced;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVersionnum() {
        return versionnum;
    }

    public void setVersionnum(String versionnum) {
        this.versionnum = versionnum == null ? null : versionnum.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getMobiletype() {
        return mobiletype;
    }

    public void setMobiletype(String mobiletype) {
        this.mobiletype = mobiletype == null ? null : mobiletype.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Boolean getForced() {
        return forced;
    }

    public void setForced(Boolean forced) {
        this.forced = forced;
    }
}