package com.ybt.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ybt_web_menu_role")
public class MenuRole {
	
	private String id;
	private String menuId;
	private String roleId;
	
	@Id
	@GenericGenerator(name = "id", strategy = "uuid")
	@GeneratedValue(generator = "id") 
	@Column(name = "id" ,length=100)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	@Column(name = "menuid" ,length=100)
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	@Column(name = "roleid" ,length=100)
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
