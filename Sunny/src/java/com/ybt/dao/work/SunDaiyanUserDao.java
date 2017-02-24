package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunDaiyanUser;

@Component
public interface SunDaiyanUserDao extends BaseDao<SunDaiyanUser, String> {
	
	/**
	 *  通过 daiyanId
	 * */
	@Query("select a from SunDaiyanUser a where a.daiyan.id = ?")
	public List<SunDaiyanUser> findSunDaiyanUserListByDanyanId(String daiyanId);
	
	
	/**
	 *  通过 userId
	 * */
	@Query("select a from SunDaiyanUser a where a.userId.id=?")
	public List<SunDaiyanUser> findSunDaiyanUserListByUserId(String userId);
	
	/**
	 *  通过 代言 和 user 查询
	 * */
	@Query("select a from SunDaiyanUser a where a.daiyan.id = ? and a.userId.id=?")
	public List<SunDaiyanUser> findSunDaiyanUserByDanyanIdAndUserId(String daiyanId,String userId);

}