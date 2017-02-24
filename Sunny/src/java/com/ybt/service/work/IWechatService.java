package com.ybt.service.work;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.ybt.common.util.Page;
import com.ybt.common.util.PropertyFilter;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.IBaseService;

/**
 * @file BBusinessService.java 创建时间:2015年7月23日上午9:03:59
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @module 模块: 模块名称
 * @author   作者
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
@Component

public interface IWechatService   extends IBaseService<SunWechatUser,String> {
	/**
	 * 分页获取全部
	 * */
	public Page<SunWechatUser> findAllBBusiness(List<PropertyFilter> filters,Page<SunWechatUser> page);
	
	
	/**
	 *@name    保存信息
	 *@time    创建时间:2015年8月7日上午11:13:47
	 *@param wechat
	 *@return  wechat
	 *@author   bqy
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public SunWechatUser saveSunWechatUser(SunWechatUser wechat);
	
	/**
	 *@name    保存信息
	 *@description 通过OpenId 查询
	 *@time    创建时间:2015年8月7日上午11:13:47
	 *@param openid
	 **@param isSave 是否没关注也保存
	 *@return
	 *@author   bqy
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public SunWechatUser saveSunWechatUser(String openid,boolean isSave);
	
	/**
	 *@name    保存信息
	 *@description 通过OpenId 查询
	 *@time    创建时间:2015年8月7日上午11:13:47
	 *@param openid
	 *@return
	 *@author   bqy
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public SunWechatUser saveSunWechatUserLocation(String openid,String latitude,String longitude);

	/**
	 *@name    查询是否被扫描，临时扫描 数据
	 *@description 相关说明
	 *@time    创建时间:2015年8月19日下午3:43:20
	 *@param phone
	 *@param time
	 *@return
	 *@author   bqy
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public SunWechatUser findByPhone(String phone,Date time);
	
	/**
	 *@name    根据主键（微信加密账号）查询用户微信信息
	 *@description 相关说明
	 *@time    创建时间:2015年9月8日上午9:59:06
	 *@param id
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public SunWechatUser findById(String id);

	
	/**
	 *@name    
	 *@description 网页授权获取用户信息
	 *@time    创建时间:2015年12月16日上午9:59:06
	 *@param id
	 *@param isSave 是否没关注也保存
	 *@return
	 *@author   zhz
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public SunWechatUser saveSNSSunWechatUser(HttpServletRequest req, boolean isSave);


}
