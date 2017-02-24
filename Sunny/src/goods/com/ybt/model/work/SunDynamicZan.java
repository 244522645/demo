package com.ybt.model.work;
// default package

import com.ybt.model.BaseEntity;

/**   
 * 动态 点赞 关联
 * @author AndyBao  
 * @version 4.0, 2016年7月24日 下午4:06:25   
 */   
public class SunDynamicZan extends BaseEntity implements java.io.Serializable {

	protected static final long serialVersionUID = 1L;

	protected String workId;            //	id
	
	protected SunWechatUser user;       //	用户id
	

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public SunWechatUser getUser() {
		return user;
	}

	public void setUser(SunWechatUser user) {
		this.user = user;
	}

}