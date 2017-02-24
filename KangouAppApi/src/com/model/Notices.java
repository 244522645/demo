package com.model;

import java.io.Serializable;
import java.util.Date;

public class Notices implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer nid;

    private String nauthor;

    private String ntitle;

    private String ncontent;

    private Date releasetime;

    private Boolean isremove;

    private Date removetime;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getNauthor() {
        return nauthor;
    }

    public void setNauthor(String nauthor) {
        this.nauthor = nauthor == null ? null : nauthor.trim();
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle == null ? null : ntitle.trim();
    }

    public String getNcontent() {
        return ncontent;
    }

    public void setNcontent(String ncontent) {
        this.ncontent = ncontent == null ? null : ncontent.trim();
    }

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    public Boolean getIsremove() {
        return isremove;
    }

    public void setIsremove(Boolean isremove) {
        this.isremove = isremove;
    }

    public Date getRemovetime() {
        return removetime;
    }

    public void setRemovetime(Date removetime) {
        this.removetime = removetime;
    }
}