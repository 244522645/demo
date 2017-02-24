package com.model;

/**
 * KUserRights entity. @author MyEclipse Persistence Tools
 */

public class KUserRights implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4469076660496783301L;
	/**
	 * 
	 */
	
	private String id;
	private String roleId;
	private String rightId;

	// Constructors

	/** default constructor */
	public KUserRights() {
	}

	/** full constructor */
	public KUserRights(String roleId, String rightId) {
		this.roleId = roleId;
		this.rightId = rightId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRightId() {
		return this.rightId;
	}

	public void setRightId(String rightId) {
		this.rightId = rightId;
	}

}