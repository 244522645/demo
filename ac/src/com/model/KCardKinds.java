package com.model;

/**
 * KCardKinds entity. @author MyEclipse Persistence Tools
 */

public class KCardKinds implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 9183990774315560295L;
	private Integer id;
	private String cardKindsName;
	private Double cardKindsDefault;

	// Constructors

	/** default constructor */
	public KCardKinds() {
	}

	/** full constructor */
	public KCardKinds(String cardKindsName, Double cardKindsDefault) {
		this.cardKindsName = cardKindsName;
		this.cardKindsDefault = cardKindsDefault;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardKindsName() {
		return this.cardKindsName;
	}

	public void setCardKindsName(String cardKindsName) {
		this.cardKindsName = cardKindsName;
	}

	public Double getCardKindsDefault() {
		return this.cardKindsDefault;
	}

	public void setCardKindsDefault(Double cardKindsDefault) {
		this.cardKindsDefault = cardKindsDefault;
	}

}