package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.CrowPkMe;

public interface CrowPkMeDao extends BaseDao<CrowPkMe, String> {
	/*
	 * 查询用户个人挑战纪录
	 */
	@Query(value="select a from CrowPkMe  a where a.userId=?")
	public List<CrowPkMe> findByUserId(String userId);
	
	/*
	 * 查询用户今日打卡 个人挑战纪录
	 */
	@Query(value="select a from CrowPkMe  a where a.userId=? and a.stopDate=?")
	public List<CrowPkMe> findByUserIdAndPdate(String userId, String pdate);
	
	/*
	 * 通过类型查询用户 个人挑战纪录
	 */
	@Query(value="select a from CrowPkMe  a where a.userId=? and a.pkType=? and a.pkLevel=?")
	public List<CrowPkMe> findByUserIdAndPkTypeAndPkLevel(String userId, int pktype, int pklevel);

	/*
	 * 通过状态查询用户 个人挑战纪录
	 */
	@Query(value="select a from CrowPkMe  a where a.userId=? and a.pkType=? and a.pkLevel=? and a.pkStatus=?")
	public List<CrowPkMe> findByUserIdAndPkTypeAndPkLevelAndPkStatus(String userId, int pktype, int pklevel,int status);

	/*
	 * 通过状态查询用户 个人挑战纪录
	 */
	@Query(value="select a from CrowPkMe  a where a.userId=? and a.pkType=?  and a.pkStatus=?")
	public List<CrowPkMe> findByUserIdAndPkTypeAndPkStatus(String userId, int pktype, int status);

	
	/*
	 * 查询所有用户 个人挑战纪录
	 */
	@Query(value="select * from crow_pk_me  a where  ( to_days(a.compute_time) != to_days(NOW()) OR  a.compute_time is NULL ) and a.start_date <= NOW() and a.end_date>=NOW() and a.pk_type=?  and a.pk_status=? order by a.create_time asc limit 0,?", nativeQuery = true)
	public List<CrowPkMe> findByAllUserAndPkTypeAndPkStatus( int pktype, int status, int top);

}
