package com.model;

import java.util.Date;

public class PosCinemas {
    private String poscinemaid;

    private String cid;

    private String posid;

    private String poscode;

    private Integer posstatus;

    private Integer posshowstatus;

    private String terminalmd5;

    private String deskey;

    private String desiv;

    private Integer currentsoftwareversion;

    private Integer updatesoftwareversion;

    private Date lastaccesstime;

    public String getPoscinemaid() {
        return poscinemaid;
    }

    public void setPoscinemaid(String poscinemaid) {
        this.poscinemaid = poscinemaid == null ? null : poscinemaid.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid == null ? null : posid.trim();
    }

    public String getPoscode() {
        return poscode;
    }

    public void setPoscode(String poscode) {
        this.poscode = poscode == null ? null : poscode.trim();
    }

    public Integer getPosstatus() {
        return posstatus;
    }

    public void setPosstatus(Integer posstatus) {
        this.posstatus = posstatus;
    }

    public Integer getPosshowstatus() {
        return posshowstatus;
    }

    public void setPosshowstatus(Integer posshowstatus) {
        this.posshowstatus = posshowstatus;
    }

    public String getTerminalmd5() {
        return terminalmd5;
    }

    public void setTerminalmd5(String terminalmd5) {
        this.terminalmd5 = terminalmd5 == null ? null : terminalmd5.trim();
    }

    public String getDeskey() {
        return deskey;
    }

    public void setDeskey(String deskey) {
        this.deskey = deskey == null ? null : deskey.trim();
    }

    public String getDesiv() {
        return desiv;
    }

    public void setDesiv(String desiv) {
        this.desiv = desiv == null ? null : desiv.trim();
    }

    public Integer getCurrentsoftwareversion() {
        return currentsoftwareversion;
    }

    public void setCurrentsoftwareversion(Integer currentsoftwareversion) {
        this.currentsoftwareversion = currentsoftwareversion;
    }

    public Integer getUpdatesoftwareversion() {
        return updatesoftwareversion;
    }

    public void setUpdatesoftwareversion(Integer updatesoftwareversion) {
        this.updatesoftwareversion = updatesoftwareversion;
    }

    public Date getLastaccesstime() {
        return lastaccesstime;
    }

    public void setLastaccesstime(Date lastaccesstime) {
        this.lastaccesstime = lastaccesstime;
    }
}