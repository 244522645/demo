package com.model;

/**
 * KUserRanges entity. @author MyEclipse Persistence Tools
 */

public class KUserRanges implements java.io.Serializable {

	
	
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userRangeId;
	private Integer userId;
	private String cardKindID;
	private Boolean userRangeStatus;
	private String userRangeName;
	private String userRangeBegin;
	private String userRangeEnd;
	private String cardKindsPrice;//扣款比例
	private String cardKindsCost; //产品成本比例
	public KUserRanges() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KUserRanges(Integer userRangeId, Integer userId, String cardKindID, Boolean userRangeStatus,
			String userRangeName, String userRangeBegin, String userRangeEnd, String cardKindsPrice,
			String cardKindsCost) {
		super();
		this.userRangeId = userRangeId;
		this.userId = userId;
		this.cardKindID = cardKindID;
		this.userRangeStatus = userRangeStatus;
		this.userRangeName = userRangeName;
		this.userRangeBegin = userRangeBegin;
		this.userRangeEnd = userRangeEnd;
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
	public String getCardKindID() {
		return cardKindID;
	}
	public void setCardKindID(String cardKindID) {
		this.cardKindID = cardKindID;
	}
	public Boolean getUserRangeStatus() {
		return userRangeStatus;
	}
	public void setUserRangeStatus(Boolean userRangeStatus) {
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