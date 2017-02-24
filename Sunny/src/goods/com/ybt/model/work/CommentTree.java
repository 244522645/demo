package com.ybt.model.work;
// default package

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ybt.model.BaseEntity;

/**   
 * 评论关系表
 * @author AndyBao  
 * @version 4.0, 2016年7月24日 下午4:06:25   
 */   
@Entity
@Table(name = "sun_comment_tree")
public class CommentTree extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "comment_id")
	private Comment comment;   //评论id
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "parent_reply")
	private Comment parentReply;   //祖先被回复评论id
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "reply")
	private Comment reply; //后代  回复评论 id
	
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public Comment getParentReply() {
		return parentReply;
	}
	public void setParentReply(Comment parentReply) {
		this.parentReply = parentReply;
	}
	public Comment getReply() {
		return reply;
	}
	public void setReply(Comment reply) {
		this.reply = reply;
	}
}