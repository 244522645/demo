package com.ybt.model.work;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ybt.model.BaseEntity;

/**   
 * 评论
 * @author AndyBao  
 * @version 4.0, 2016年7月24日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_comment")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Comment extends BaseEntity implements java.io.Serializable {

	protected static final long serialVersionUID = 1L;

	@Column(name = "work_id",length=100)
	protected String wokrId;            //	id
	
	
	@ManyToOne(fetch = FetchType.EAGER,optional=true)
	@JoinColumn(name = "user_id")
	protected SunWechatUser user;       //	用户id
	
	@Column(name = "comment_body")
	protected String commentBody ;  //	评论内容
	
	@Column(name = "type", length = 10)
	protected int type;  //     0=直接评论or 1=回复评论

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getWokrId() {
		return wokrId;
	}

	public void setWokrId(String wokrId) {
		this.wokrId = wokrId;
	}

	public SunWechatUser getUser() {
		return user;
	}

	public void setUser(SunWechatUser user) {
		this.user = user;
	}

	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}
	
}