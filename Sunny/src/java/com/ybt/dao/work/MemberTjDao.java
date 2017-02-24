package com.ybt.dao.work;

import org.springframework.data.jpa.repository.Query;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunMemberTj;

/**
 * @module 空间数据
 * @author bqy  @time 下午12:23:59
 */
public interface MemberTjDao extends BaseDao<SunMemberTj, String>{
	/**
	 * mid 获取会员信息
	 * @param mid
	 * @return HzMemberTj
	 * */
	@Query("select a from SunMemberTj a where a.id.id = ? ")
	public SunMemberTj findById(String mid);
	
	
	/**
	 *  收到数
	 * */
	@Query("select count(a)  from SunDdOrder a where  a.sendeeId=? and  a.deleted = 0 and a.sellDeleted=0 order by a.createTime desc")
	public Long getMyReceiveCount(String mid);
	
	/**
	 *  送出数
	 * */
	@Query("select count(a) from SunDdOrder a where  a.buyerId=? and  a.deleted = 0 and a.buyDeleted=0 order by a.createTime desc")
	public Long getMySendCount(String mid);
	
	/**
	 *  收到letter数
	 * */
	@Query("select count(a) from SunLetter a where a.toUserId.id=? and  a.deleted = 0")
	public Long getMyLetterReceiveCount(String mid);
	
	/**
	 *  送出letter数
	 * */
	@Query("select count(a) from SunLetter a where a.userId.id=? and  a.deleted = 0")
	public Long getMyLetterSendCount(String mid);
	
	
	/**
	 *  Card数
	 * */
	@Query("select count(a) from SunCard a where a.userId=? and a.status=1 and  a.deleted = 0")
	public Long getMyCardCount(String mid);
}
