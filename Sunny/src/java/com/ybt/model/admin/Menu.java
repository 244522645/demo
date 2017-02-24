package com.ybt.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "ybt_web_menu")
public class Menu {
	
	private String menuId;
	private String name;
	private String url;
	private int sortnum;
	private int isdisplay;
	private String parentId;
	
	private String parentName;
	@Transient
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	@Id
	@GenericGenerator(name = "menuid", strategy = "uuid")
	@GeneratedValue(generator = "menuid") 
	@Column(name = "menuid" ,length=100)
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
	@Column(name = "name" ,length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "url" ,length=200)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name = "sortnum" ,length=3)
	public int getSortnum() {
		return sortnum;
	}
	public void setSortnum(int sortnum) {
		this.sortnum = sortnum;
	}
	
	@Column(name = "isdisplay" ,length=3)
	public int getIsdisplay() {
		return isdisplay;
	}
	public void setIsdisplay(int isdisplay) {
		this.isdisplay = isdisplay;
	}
	
	@Column(name = "parentid" ,length=100)
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
