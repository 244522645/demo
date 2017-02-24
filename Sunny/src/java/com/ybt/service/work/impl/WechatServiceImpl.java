package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ybt.common.constant.Wechat;
import com.ybt.common.util.EmojiFilter;
import com.ybt.common.util.Page;
import com.ybt.common.util.PropertyFilter;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.IWechatDao;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.IWechatService;

import wechat.api.SnsAPI;
import wechat.api.UserAPI;
import wechat.bean.SnsToken;
import wechat.bean.User;
import wechat.support.TokenManager;

@Component
public class WechatServiceImpl extends BaseServiceImpl<SunWechatUser,String> implements  IWechatService{
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private IWechatDao wechatDao;
	

	
	public BaseDao<SunWechatUser, String> getDao() {
		// TODO Auto-generated method stub
		return wechatDao;
	}

	public Page<SunWechatUser> findAllBBusiness(List<PropertyFilter> filters, Page<SunWechatUser> page) {
		return null;
	}
	

	@Transactional
	public SunWechatUser saveSunWechatUser(String openid,boolean isSave) {
		
		User user = UserAPI.userInfo(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), openid);
		SunWechatUser bw= wechatDao.findOne(openid);
		if(bw==null){
			bw=new SunWechatUser();
			bw.setId(openid);
			bw.setSubscribe(2);
			bw.setCreateTime(new Date());
		}
		if(user!=null&&user.getErrcode()==null){
			bw.setId(openid);
			if(user.getSubscribe()==0 && !isSave){
				bw.setSubscribe(0);
				bw.setDeletedTime(new Date());
				bw.setUpdateTime(new Date());
				logger.info("!!!!!!!!!!========================weiguanzu de bu save ++=======================" );
				return wechatDao.save(bw);
			}
			bw.setSubscribe(1);
			bw.setCity(user.getCity());
			bw.setLanguage(user.getLanguage());
			bw.setProvince(user.getProvince());
			bw.setSex(user.getSex());
			bw.setUnionid(user.getUnionid());
			bw.setWechatHeadUrl(user.getHeadimgurl());
			String nickname=user.getNickname();
			if(EmojiFilter.containsEmoji(nickname)){
			 nickname=EmojiFilter.filterEmoji(nickname);
			}
			bw.setWechatNickname(nickname);
			bw.setUpdateTime(new Date());
			wechatDao.save(bw);
		}else{
			logger.error("微信用户信息获取失败:xxxxxx"+user.getErrcode()+"/"+Wechat.APPID+"/"+Wechat.APPSECRET);
		}
		return bw;
	}
	
	@Transactional
	public SunWechatUser saveSNSSunWechatUser(HttpServletRequest request,boolean isSave) {
		String openId = (String)request.getSession(true).getAttribute(Wechat.WECATOPENID);
		if(openId!=null && !"".equals(openId)){
			SunWechatUser bw = wechatDao.findOne(openId);
			if(bw!=null&&bw.getWechatNickname()!=null&&bw.getSubscribe()!=2){
				return bw;
			}
		}
		
		String code = request.getParameter(Wechat.WECHATCODE);
		if(code==null){
			logger.error("网页授权获取用户授权信息=----  code null,获取code失败");
			return null;
		}
		//通过code 获取 SnsToken
		SnsToken snstoken =  SnsAPI.oauth2AccessToken(Wechat.APPID, Wechat.APPSECRET, code);
		if(snstoken==null || snstoken.getErrcode()!=null || snstoken.getAccess_token()==null||snstoken.getOpenid()==null){
			logger.error("saveSunWechatUser--SnsAPI.oauth2AccessToken--------getAccess_token " + snstoken.getErrcode()+"/"+snstoken.getErrmsg());
			return null;
		}
		String openid = snstoken.getOpenid();
		String scope = snstoken.getScope();
		SunWechatUser bw= wechatDao.findOne(snstoken.getOpenid());
		
		//无授权登录 时 直接返回信息不获取不更新
		if(!"snsapi_userinfo".equals(scope)){

			if(bw==null){
				bw=new SunWechatUser();
				bw.setId(openid);
				bw.setCreateTime(new Date());
				bw.setSubscribe(2);
				wechatDao.save(bw);
			}
			return bw;
		}
		
		//授权登录 时 获取用户信息更新
		User user  = SnsAPI.userinfo(snstoken.getAccess_token(), openid, "zh_CN");
		
		if(bw==null){
			bw=new SunWechatUser();
			bw.setCreateTime(new Date());
		}
		if(user!=null&&user.getErrcode()==null){
			bw.setId(openid);
			int subscrobe = 0 ;
			if (user.getSubscribe() != null){
				subscrobe = user.getSubscribe();
				bw.setSubscribe(subscrobe);//网页授权没有Subscribe字段的信息
			}	
			bw.setSubscribe(bw.getSubscribe()==1?1:0);
			bw.setCity(user.getCity());
			bw.setLanguage(user.getLanguage());
			bw.setProvince(user.getProvince());
			bw.setSex(user.getSex());
			bw.setUnionid(user.getUnionid());
			bw.setWechatHeadUrl(user.getHeadimgurl());
			String nickname=user.getNickname();
			if(EmojiFilter.containsEmoji(nickname)){
			 nickname=EmojiFilter.filterEmoji(nickname);
			}
			bw.setWechatNickname(nickname);
			bw.setUpdateTime(new Date());
			wechatDao.save(bw);
			
		}else{
			logger.error("微信用户信息获取失败:saveSNSSunWechatUser xxxxxx"+user.getErrcode()+"/"+Wechat.APPID+"/"+Wechat.APPSECRET);
		}
		return bw;
	}

	public SunWechatUser saveSunWechatUserLocation(String openid,String latitude, String longitude) {
		
		SunWechatUser bw= wechatDao.findOne(openid);
		if(bw!=null&&latitude!=null&&longitude!=null){
			bw.setCreateTime(new Date());
			bw.setLatitude(latitude);
			bw.setLongitude(longitude);
			wechatDao.save(bw);
		}
		return bw;
	}

	public SunWechatUser saveSunWechatUser(SunWechatUser wechat) {
		return wechatDao.save(wechat);
	}

	public SunWechatUser findByPhone(String phone, Date time) {
		List<SunWechatUser> list=wechatDao.findByPhone(phone, time);
		if(list!=null&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 *@name    根据主键（微信加密账号）查询用户微信信息
	 *@description 相关说明
	 *@time    创建时间:2015年9月8日上午9:59:06
	 *@param id
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public SunWechatUser findById(String id) {
		SunWechatUser a = wechatDao.findById(id);
		return a;
	}

}
