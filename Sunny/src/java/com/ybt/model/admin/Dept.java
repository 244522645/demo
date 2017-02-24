package com.ybt.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "ybt_web_dept")
public class Dept  {
	
	private String deptId;
	private String name;
	private String parentId;
	private String roleId;
	private String remarks;
	private String deleted;
	
	
	private String parentName;
	
	
	
	@Id
	@GenericGenerator(name = "deptid", strategy = "uuid")
	@GeneratedValue(generator = "deptid") 
	@Column(name = "deptid" ,length=100)
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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
	
	@Column(name = "remarks" ,length=500)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name = "deleted" ,length=2)
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	@Column(name = "roleid" ,length=100)
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@Transient
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	
}
