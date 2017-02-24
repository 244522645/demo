package com.model;

import java.sql.Timestamp;

/**
 * KUserRoles entity. @author MyEclipse Persistence Tools
 */

public class KUserRoles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -109722792255972324L;
	/**
	 * 
	 */
	
	private String roleId;
	private Integer roleKind;
	private String roleName;
	private String roleDescription;
	private Timestamp roleAddDate;
	private String roleLogPic;
	private String roleIndexPic;
	private String roleParameter;

	// Constructors

	/** default constructor */
	public KUserRoles() {
	}

	/** full constructor */
	public KUserRoles(Integer roleKind, String roleName,
			String roleDescription, Timestamp roleAddDate, String roleLogPic,
			String roleIndexPic, String roleParameter) {
		this.roleKind = roleKind;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.roleAddDate = roleAddDate;
		this.roleLogPic = roleLogPic;
		this.roleIndexPic = roleIndexPic;
		this.roleParameter = roleParameter;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleKind() {
		return this.roleKind;
	}

	public void setRoleKind(Integer roleKind) {
		this.roleKind = roleKind;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Timestamp getRoleAddDate() {
		return this.roleAddDate;
	}

	public void setRoleAddDate(Timestamp roleAddDate) {
		this.roleAddDate = roleAddDate;
	}

	public String getRoleLogPic() {
		return this.roleLogPic;
	}

	public void setRoleLogPic(String roleLogPic) {
		this.roleLogPic = roleLogPic;
	}

	public String getRoleIndexPic() {
		return this.roleIndexPic;
	}

	public void setRoleIndexPic(String roleIndexPic) {
		this.roleIndexPic = roleIndexPic;
	}

	public String getRoleParameter() {
		return this.roleParameter;
	}

	public void setRoleParameter(String roleParameter) {
		this.roleParameter = roleParameter;
	}

}