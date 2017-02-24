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
 * 关系表
 * @author AndyBao  
 * @version 4.0, 2016年7月24日 下午4:06:25   
 */   
@Entity
@Table(name = "crow_user_tree")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CrowUserTree extends BaseEntity implements java.io.Serializable {

	protected static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "ancestor")
	protected SunWechatUser ancestor;   //祖先
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "descendant")
	protected SunWechatUser descendant; //后代
	@Column(name="depth")
	protected int depth;
	
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public SunWechatUser getAncestor() {
		return ancestor;
	}
	public void setAncestor(SunWechatUser ancestor) {
		this.ancestor = ancestor;
	}
	public SunWechatUser getDescendant() {
		return descendant;
	}
	public void setDescendant(SunWechatUser descendant) {
		this.descendant = descendant;
	}
	
	

}