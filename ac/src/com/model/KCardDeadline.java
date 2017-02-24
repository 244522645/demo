package com.model;

import java.sql.Timestamp;


/**
 * KCardDeadline entity. @author MyEclipse Persistence Tools
 */

public class KCardDeadline  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private String startCardNum;
     private String endCardNum;
     private Integer cardKindsId;
     private Boolean isKt;
     private Timestamp beforeDate;
     private Timestamp afterDate;
     private Timestamp submitDate;
     private String userID;

    // Constructors

    /** default constructor */
    public KCardDeadline() {
    }

    
    /** full constructor */

    // Property accessors

   


	public void setUserID(String userID) {
		this.userID = userID;
	}








	public KCardDeadline(Integer id, String startCardNum, String endCardNum, Integer cardKindsId, Boolean isKt,
			Timestamp beforeDate, Timestamp afterDate, Timestamp submitDate, String userID) {
		super();
		this.id = id;
		this.startCardNum = startCardNum;
		this.endCardNum = endCardNum;
		this.cardKindsId = cardKindsId;
		this.isKt = isKt;
		this.beforeDate = beforeDate;
		this.afterDate = afterDate;
		this.submitDate = submitDate;
		this.userID = userID;
	}


	public String getUserID() {
		return userID;
	}


	public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartCardNum() {
        return this.startCardNum;
    }
    
    public void setStartCardNum(String startCardNum) {
        this.startCardNum = startCardNum;
    }

    public String getEndCardNum() {
        return this.endCardNum;
    }
    
    public void setEndCardNum(String endCardNum) {
        this.endCardNum = endCardNum;
    }

    public Integer getCardKindsId() {
        return this.cardKindsId;
    }
    
    public void setCardKindsId(Integer cardKindsId) {
        this.cardKindsId = cardKindsId;
    }

    public Boolean getIsKt() {
        return this.isKt;
    }
    
    public void setIsKt(Boolean isKt) {
        this.isKt = isKt;
    }


	public Timestamp getBeforeDate() {
		return beforeDate;
	}


	public void setBeforeDate(Timestamp beforeDate) {
		this.beforeDate = beforeDate;
	}


	public Timestamp getAfterDate() {
		return afterDate;
	}


	public void setAfterDate(Timestamp afterDate) {
		this.afterDate = afterDate;
	}


	public Timestamp getSubmitDate() {
		return submitDate;
	}


	public void setSubmitDate(Timestamp submitDate) {
		this.submitDate = submitDate;
	}


   
   








}