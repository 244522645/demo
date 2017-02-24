package com.util;

/**
 * KUserRanges entity. @author MyEclipse Persistence Tools
 */

public class KUserRanges_util implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userRangeId;
	private Integer userId;
	private String userRangeStatus;
	private String userRangeName;
	private String userRangeBegin;
	private String userRangeEnd;
	private String cardKindID;
	private String cardKindsPrice;
	private String cardKindsCost;
	public KUserRanges_util() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KUserRanges_util(Integer userRangeId, Integer userId, String userRangeStatus, String userRangeName,
			String userRangeBegin, String userRangeEnd, String cardKindID, String cardKindsPrice,
			String cardKindsCost) {
		super();
		this.userRangeId = userRangeId;
		this.userId = userId;
		this.userRangeStatus = userRangeStatus;
		this.userRangeName = userRangeName;
		this.userRangeBegin = userRangeBegin;
		this.userRangeEnd = userRangeEnd;
		this.cardKindID = cardKindID;
		this.cardKindsPrice = cardKindsPrice;
		this.cardKindsCost = cardKindsCost;
	}
	public Integer getUserRangeId() {
		return userRangeId;
	}
	public void setUserRangeId(Integer userRangeId) {
		this.userRangeId = userRangeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserRangeStatus() {
		return userRangeStatus;
	}
	public void setUserRangeStatus(String userRangeStatus) {
		this.userRangeStatus = userRangeStatus;
	}
	public String getUserRangeName() {
		return userRangeName;
	}
	public void setUserRangeName(String userRangeName) {
		this.userRangeName = userRangeName;
	}
	public String getUserRangeBegin() {
		return userRangeBegin;
	}
	public void setUserRangeBegin(String userRangeBegin) {
		this.userRangeBegin = userRangeBegin;
	}
	public String getUserRangeEnd() {
		return userRangeEnd;
	}
	public void setUserRangeEnd(String userRangeEnd) {
		this.userRangeEnd = userRangeEnd;
	}
	public String getCardKindID() {
		return cardKindID;
	}
	public void setCardKindID(String cardKindID) {
		this.cardKindID = cardKindID;
	}
	public String getCardKindsPrice() {
		return cardKindsPrice;
	}
	public void setCardKindsPrice(String cardKindsPrice) {
		this.cardKindsPrice = cardKindsPrice;
	}
	public String getCardKindsCost() {
		return cardKindsCost;
	}
	public void setCardKindsCost(String cardKindsCost) {
		this.cardKindsCost = cardKindsCost;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}