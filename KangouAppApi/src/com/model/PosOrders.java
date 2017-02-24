package com.model;

import java.util.Date;

public class PosOrders {
    private String cid;

    private String cardtid;

    private String name;

    private String cardtnumber;

    private Integer cardtmoney;

    private Integer cardtstatus;

    private Date cardtdatebegin;

    private Date cardtdateend;

    private Integer cardtkind;

    private String cardtname;

    private Integer ticketcount;

    private Integer ticketremaincount;

    private Date posordertime;

    private Integer posordercount;

    private Integer posorderstatus;

    private String serialnumber;

    private String batch;

    private String posid;

    private Boolean cardtest;

    private Integer cinemaprice;

    private String posuserid;

    private String cardtidcard;

    private Integer posorderpurchasecount;

    private String posorderpurchasekind;

    private String cityzone;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getCardtid() {
        return cardtid;
    }

    public void setCardtid(String cardtid) {
        this.cardtid = cardtid == null ? null : cardtid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCardtnumber() {
        return cardtnumber;
    }

    public void setCardtnumber(String cardtnumber) {
        this.cardtnumber = cardtnumber == null ? null : cardtnumber.trim();
    }

    public Integer getCardtmoney() {
        return cardtmoney;
    }

    public void setCardtmoney(Integer cardtmoney) {
        this.cardtmoney = cardtmoney;
    }

    public Integer getCardtstatus() {
        return cardtstatus;
    }

    public void setCardtstatus(Integer cardtstatus) {
        this.cardtstatus = cardtstatus;
    }

    public Date getCardtdatebegin() {
        return cardtdatebegin;
    }

    public void setCardtdatebegin(Date cardtdatebegin) {
        this.cardtdatebegin = cardtdatebegin;
    }

    public Date getCardtdateend() {
        return cardtdateend;
    }

    public void setCardtdateend(Date cardtdateend) {
        this.cardtdateend = cardtdateend;
    }

    public Integer getCardtkind() {
        return cardtkind;
    }

    public void setCardtkind(Integer cardtkind) {
        this.cardtkind = cardtkind;
    }

    public String getCardtname() {
        return cardtname;
    }

    public void setCardtname(String cardtname) {
        this.cardtname = cardtname == null ? null : cardtname.trim();
    }

    public Integer getTicketcount() {
        return ticketcount;
    }

    public void setTicketcount(Integer ticketcount) {
        this.ticketcount = ticketcount;
    }

    public Integer getTicketremaincount() {
        return ticketremaincount;
    }

    public void setTicketremaincount(Integer ticketremaincount) {
        this.ticketremaincount = ticketremaincount;
    }

    public Date getPosordertime() {
        return posordertime;
    }

    public void setPosordertime(Date posordertime) {
        this.posordertime = posordertime;
    }

    public Integer getPosordercount() {
        return posordercount;
    }

    public void setPosordercount(Integer posordercount) {
        this.posordercount = posordercount;
    }

    public Integer getPosorderstatus() {
        return posorderstatus;
    }

    public void setPosorderstatus(Integer posorderstatus) {
        this.posorderstatus = posorderstatus;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid == null ? null : posid.trim();
    }

    public Boolean getCardtest() {
        return cardtest;
    }

    public void setCardtest(Boolean cardtest) {
        this.cardtest = cardtest;
    }

    public Integer getCinemaprice() {
        return cinemaprice;
    }

    public void setCinemaprice(Integer cinemaprice) {
        this.cinemaprice = cinemaprice;
    }

    public String getPosuserid() {
        return posuserid;
    }

    public void setPosuserid(String posuserid) {
        this.posuserid = posuserid == null ? null : posuserid.trim();
    }

    public String getCardtidcard() {
        return cardtidcard;
    }

    public void setCardtidcard(String cardtidcard) {
        this.cardtidcard = cardtidcard == null ? null : cardtidcard.trim();
    }

    public Integer getPosorderpurchasecount() {
        return posorderpurchasecount;
    }

    public void setPosorderpurchasecount(Integer posorderpurchasecount) {
        this.posorderpurchasecount = posorderpurchasecount;
    }

    public String getPosorderpurchasekind() {
        return posorderpurchasekind;
    }

    public void setPosorderpurchasekind(String posorderpurchasekind) {
        this.posorderpurchasekind = posorderpurchasekind == null ? null : posorderpurchasekind.trim();
    }

    public String getCityzone() {
        return cityzone;
    }

    public void setCityzone(String cityzone) {
        this.cityzone = cityzone == null ? null : cityzone.trim();
    }
}