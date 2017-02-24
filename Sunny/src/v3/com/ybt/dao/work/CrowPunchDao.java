package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.CrowPunch;

public interface CrowPunchDao extends BaseDao<CrowPunch, String> {
	/*
	 * 查询用户打卡
	 */
	@Query(value="select a from CrowPunch  a where a.userId=:userId")
	public List<CrowPunch> findByUserId(String userId);
	/*
	 * 查询用户一天打卡
	 */
	@Query(value="select * from crow_punch a where a.user_id=? and date_format( a.create_time,'%Y-%m-%d') =?  order by a.create_time desc limit 0,1", nativeQuery = true)
	public  CrowPunch findByUserIdAndToday(String userId,String today);
	
	
	 /**
	 * 查询签到情况
	 */
	@Query(value="select * from crow_punch p where p.iscompute=1 and p.create_time >= :startDate and p.create_time <= :endDate and p.deleted='0' and p.user_id = :userId order by p.create_time asc", nativeQuery = true)
 	public List<CrowPunch> findCrowPunchCalendar(@Param("userId") String userId, @Param("startDate") String startDate,  @Param("endDate") String endDate);

	/**
	 * 查询未计算 top 100
	 */
	@Query(value="select * from crow_punch p where p.deleted='0' and p.ispass!=0  and p.iscompute=0 order by p.create_time asc limit 0,:top", nativeQuery = true)
 	public List<CrowPunch> findCrowPunchByTop(@Param("top") int top);

}
