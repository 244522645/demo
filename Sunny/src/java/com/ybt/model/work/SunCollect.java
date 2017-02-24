package com.ybt.model.work;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ybt.model.BaseEntity;

/**   
 * 收集祝福
 * @author AndyBao  
 * @version 4.0, 2016年7月24日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_collect")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SunCollect extends BaseEntity implements java.io.Serializable {

	protected static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	protected  SunWechatUser userId;            //	会员

	@Column(name = "name",length=50)
	protected String name;            //	歌名
	
	@Column(name = "type",length=50)
	protected String type;       //类别  ,1生日2其他
	
	@Column(name = "remark",length=50)
	protected String remark;       // 备注
	
	@Column(name = "release")
	protected int release=0;  //	发布标志， 收集结束后 状态为  1 
	
	@Column(name = "good")
	protected long good;  //	集赞
	
	@Column(name = "pvw")
	protected long pvw;  //	预览次数

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getRelease() {
		return release;
	}

	public void setRelease(int release) {
		this.release = release;
	}

	public long getGood() {
		return good;
	}

	public void setGood(long good) {
		this.good = good;
	}

	public long getPvw() {
		return pvw;
	}

	public void setPvw(long pvw) {
		this.pvw = pvw;
	}

	public SunWechatUser getUserId() {
		return userId;
	}

	public void setUserId(SunWechatUser userId) {
		this.userId = userId;
	}
	
}