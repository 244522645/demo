package com.ybt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * entity基类.
 * 
 * @author AndyBao
 * 
 */
@MappedSuperclass
public class BaseEntity {
	
	@Id
	@GenericGenerator(name = "id", strategy = "uuid")
	@GeneratedValue(generator = "id")
	@Column(name = "id", length = 32)
	protected String id; // 数据库主键
	
	@Column(name = "deleted")
	private int deleted=0;//是否删除 默认0,1标示删除
	
	@Column(name = "create_time",updatable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date createTime = new Date();//新建时间
	
	@Column(name = "update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;//修改时间


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
