package com.ybt.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "ybt_web_role")
public class Role {
	private String roleId;
	private String name;
	private String parentId;
	private String code;
	private String remarks;
	
	private String parentName;
	
	
	@Transient
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	
	@Id
	@GenericGenerator(name = "roleid", strategy = "uuid")
	@GeneratedValue(generator = "roleid") 
	@Column(name = "roleid" ,length=100)
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@Column(name = "name" ,length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "parentid" ,length=100)
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Column(name = "code" ,length=100)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	@Column(name = "remarks" ,length=500)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
