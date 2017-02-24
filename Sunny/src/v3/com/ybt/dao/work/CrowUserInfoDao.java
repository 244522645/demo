package com.ybt.dao.work;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.SunBless;
@Component
public interface CrowUserInfoDao extends BaseDao<CrowUserInfo, String> {
	/* zwh
	 * 根据用户ID查询数据
	 */
	@Query(value="select * from crow_user_info as a  where a.user_id = :userID", nativeQuery = true)
	 CrowUserInfo findByUserId(@Param("userID")String userID);
	
	/*
	 * 查询最大的用户编号
	 */
	@Query("select max(a.userNumber) from CrowUserInfo as a")
	public Integer findCrowUserByUserNumber();
	
	 /**
	 * 通过二维码ID查询SunBless信息
	 */
	 @Query(value="select * from crow_user_info b where b.qrcode_id =(select a.id from sun_qrcode a where a.id=:qrcodeId)", nativeQuery = true)
	 public CrowUserInfo findCrowUserInfoByQrcodeId(@Param("qrcodeId") String qrcodeId);
}
