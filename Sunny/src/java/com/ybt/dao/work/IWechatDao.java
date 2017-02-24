package com.ybt.dao.work;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunWechatUser;

/**
 * @file IWechatDao.java 创建时间:2015年9月7日上午10:57:16
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @module 模块: 用户公众微信用户信息
 * @author   bqy
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
public interface IWechatDao  extends BaseDao<SunWechatUser, String>{
	/**
	 *@name    通过手机号和时间 查询是否扫描带参二维码
	 *@description 通过帐号 和 时间
	 *@time    创建时间:2015年9月19日下午2:39:46
	 *@param phone 
	 *@author   bqy
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Query("select a from SunWechatUser a where a.deleted = 0 and a.phone=? and a.updateTime>?")
	public List<SunWechatUser> findByPhone(String phone,Date time);
	
	@Query(value="SELECT a.* FROM sun_wechat AS a INNER JOIN crow_user_tree t on a.id = t.ancestor WHERE t.descendant = ? ORDER BY t.depth desc;", nativeQuery = true)
	public List<SunWechatUser> getPrarentListByUserId(String userId);
	/**
	 *@name    根据主键（微信加密账号）查询用户微信信息
	 *@description 相关说明
	 *@time    创建时间:2015年9月8日上午9:59:06
	 *@param id
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Query("select a from SunWechatUser a where a.id = ? ")
	public SunWechatUser findById(String id);
}

