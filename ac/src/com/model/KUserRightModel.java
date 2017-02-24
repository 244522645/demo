package com.model;

/**
 * KUserRightModel entity. @author MyEclipse Persistence Tools
 */

public class KUserRightModel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3248680253121576241L;
	/**
	 * 
	 */
	
	private String rightId;
	private String rightName;
	private String rightDescription;
	private String rightPage;
	private String rightConfig;
	private String rightPic;
	private String rightascription;

	// Constructors

	/** default constructor */
	public KUserRightModel() {
	}

	/** full constructor */
	public KUserRightModel(String rightName, String rightDescription,
			String rightPage, String rightConfig, String rightPic,
			String rightascription) {
		this.rightName = rightName;
		this.rightDescription = rightDescription;
		this.rightPage = rightPage;
		this.rightConfig = rightConfig;
		this.rightPic = rightPic;
		this.rightascription = rightascription;
	}

	// Property accessors

	public String getRightId() {
		return this.rightId;
	}

	public void setRightId(String rightId) {
		this.rightId = rightId;
	}

	public String getRightName() {
		return this.rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightDescription() {
		return this.rightDescription;
	}

	public void setRightDescription(String rightDescription) {
		this.rightDescription = rightDescription;
	}

	public String getRightPage() {
		return this.rightPage;
	}

	public void setRightPage(String rightPage) {
		this.rightPage = rightPage;
	}

	public String getRightConfig() {
		return this.rightConfig;
	}

	public void setRightConfig(String rightConfig) {
		this.rightConfig = rightConfig;
	}

	public String getRightPic() {
		return this.rightPic;
	}

	public void setRightPic(String rightPic) {
		this.rightPic = rightPic;
	}

	public String getRightascription() {
		return this.rightascription;
	}

	public void setRightascription(String rightascription) {
		this.rightascription = rightascription;
	}

}