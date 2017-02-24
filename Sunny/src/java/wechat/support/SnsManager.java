package wechat.support;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import wechat.api.SnsAPI;
import wechat.bean.SnsToken;
import wechat.bean.User;

import com.ybt.common.constant.Wechat;

/**
 * @file SnsManager.java 创建时间:2015年7月23日上午10:11:52
 * @description 获取 微信OpenId  和 微信基本信息
 * @module 	微信网页授权管理
 * @author   包青友
 * @version 1.0
 * @history 
 */
public class SnsManager{
	
	private static Logger logger = Logger.getLogger(SnsManager.class);
	/**
	 *@name    获取加密微信号
	 *@description 获取加密微信号 , 第一步，获取code 第二部 获取openid 
	 *@time    创建时间:2015年7月23日上午10:16:35
	 *@param request
	 *@param appId
	 *@param appSecret
	 *@return String openid
	 *@author   包青友
	 *@history 
	 */
	public static String getOpenId(HttpServletRequest request , String appId,String appSecret){
		String openId = (String)request.getSession(true).getAttribute(Wechat.WECATOPENID);
		request.getSession(true).setAttribute(Wechat.WECATOPENID, openId);
		if(openId==null){
			String code = request.getParameter(Wechat.WECHATCODE);
			if(code==null){
				logger.error("code null,获取code失败");
				return null;
			}
			//通过code 获取 SnsToken openId
			SnsToken snstoken =  SnsAPI.oauth2AccessToken(appId,appSecret, code);
			if(snstoken.getOpenid()==null){
				logger.error("openid null ，获取OpenId失败:"+ snstoken.getErrcode()+"/"+snstoken.getErrmsg());
				return null;
			}
			openId=snstoken.getOpenid();
			request.getSession(true).setAttribute(Wechat.WECATOPENID, openId);
			return snstoken.getOpenid();
		}
		return openId;
	}
	
	public static User getUserInfo(String access_token,String openid){
		return SnsAPI.userinfo(access_token, openid, "zh_CN");
	};
}
