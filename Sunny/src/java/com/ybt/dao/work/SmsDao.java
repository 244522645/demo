package com.ybt.dao.work;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunGySms;

@Component
public interface SmsDao  extends BaseDao<SunGySms, String> {


	/**
	 *@name    校验验证码是否正确
	 *@description 相关说明
	 *@time    创建时间:2015年8月20日下午4:18:07
	 *@param phone 手机号
	 *@param code 验证码
	 *@param time 有效时间
	 *@param type 验证码里类型
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Query("select a from SunGySms a where  a.phone = ? and a.code = ? and a.createTime>=?  and a.type =? and a.states=1 order by a.createTime desc")
	public List<SunGySms> findByPhoneAndCodeAndCreateTime(String phone, String code,Date createTime,String type);

	/**
	 *@name    查询短信
	 *@description 相关说明
	 *@time    创建时间:2015年8月20日下午4:18:07
	 *@param phone 手机号
	 *@param time 有效时间
	 *@return
	 *@author   bqy
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Query("select a from SunGySms a where  a.phone = ?  and a.createTime>=? and a.type =? and a.states=1 order by a.createTime desc")
	public List<SunGySms> findByPhonAndCreateTime(String phone,Date createTime,String type);
}
