package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.CrowPkMe;
import com.ybt.model.work.CrowPkOne;

@Component
public interface CrowPkOneDao extends BaseDao<CrowPkOne, String> {
	/*
	 * zwh 传入用户id，挑战状态和挑战结果 查询出来符合条件的结果
	 */
	@Query(" FROM CrowPkOne WHERE  pkType = 1 and  userPkStatus = 2 and user.id = :userid ")
	public List<CrowPkOne> findByuserid(@Param("userid") String userid);

	@Query(" FROM CrowPkOne WHERE  pkType = 1  and  userPkStatus = 1 and user.id = :userid ")
	public List<CrowPkOne> findCrowUserWin(@Param("userid") String userid);

	@Query(" FROM CrowPkOne WHERE  pkType = 1  and  userPkStatus = 0 and user.id = :userid ")
	public List<CrowPkOne> findCrowUserlose(@Param("userid") String userid);

	
	/*
	 * 查询所有用户 挑战纪录
	 */
	@Query(value="select * from crow_pk_one  a where ( to_days(a.compute_time) != to_days(NOW()) OR  a.compute_time is NULL ) and a.start_date <= NOW() and a.end_date>=NOW() and a.pk_type=?  and a.pk_status=?  order by a.create_time asc limit 0,?", nativeQuery = true)
	public List<CrowPkOne> findCrowPkMeListByPkTypeAndPkStatusAndTop( int pktype, int status,int top);
	
	
	
	/**
	 * 查询应战方是否第一次接受发起方的挑战
	 * @param userId      应战人id
	 * @param OtherUserId 发起挑战人id
	 * @param pkStatus    // 挑战状态 0：进行中    ，2：挑战结束
	 * @return
	 */
	@Query(value="SELECT * FROM `crow_pk_one` a where a.user_id =:userId and a.other_user_id= :otherUserId and a.pk_status='0' and a.pk_type='1' and a.deleted = '0' and a.is_rec='0'", nativeQuery = true)
	public List<CrowPkOne> isFirstAcceptChallenge(@Param("userId") String userId, @Param("otherUserId") String otherUserId);

}
