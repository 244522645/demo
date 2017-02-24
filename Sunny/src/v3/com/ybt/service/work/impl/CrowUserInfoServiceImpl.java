package com.ybt.service.work.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.util.CommonUtil;
import com.ybt.common.constant.Wechat;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.CrowUserInfoDao;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.SunQrcode;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.CrowUserInfoService;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.QrcodeService;

import wechat.api.MediaAPI;
import wechat.api.MediaAPI.MediaType;
import wechat.api.MessageAPI;
import wechat.bean.Media;
import wechat.bean.message.ImageMessage;
import wechat.support.TokenManager;
@Component
public class CrowUserInfoServiceImpl extends BaseServiceImpl<CrowUserInfo, String> implements CrowUserInfoService {
	@Autowired
	private CrowUserInfoDao crowUserInfoDao;
	@Autowired
	private IWechatService wechatService;
	 @Autowired
	 private QrcodeService qrcodeService;
	@Override
	public BaseDao<CrowUserInfo, String> getDao() {
		return crowUserInfoDao;
	}
	@Override
	public CrowUserInfo findByUserID(String userid){
		CrowUserInfo info = null;
		info = crowUserInfoDao.findByUserId(userid);
		if(info==null){
			//第一次访问生成数据
			SunWechatUser user = wechatService.findById(userid);
			if(user==null)return null;
			info= new CrowUserInfo();
			info.setUser(user);
			info.setBalance(new BigDecimal(0.00));
			info.setUserNumber(findCrowUserByUserNumber());
			info.setCreateTime(new Date());
			info.setUpdateTime(new Date());
			
			crowUserInfoDao.save(info);
		}
		
		if(info.getLevel()>0&&info.getSunQrcode()==null){
			createTempQrcode(userid);
		}
		
	  return info;
	}
	@Override
	public int findCrowUserByUserNumber() {
		Integer i = crowUserInfoDao.findCrowUserByUserNumber();
		if(i==null)i=0;
		i++;
		return i;
	}
	
	@Override
	public CrowUserInfo updateUserBalance(String userid,String addOrsubtract , String deduct) {
		CrowUserInfo user = crowUserInfoDao.findByUserId(userid);
		String newbalance = "";
		if(addOrsubtract == "add"){
			newbalance = CommonUtil.add(user.getBalance().toString(), deduct);
		}else{
			newbalance = CommonUtil.subtract(user.getBalance().toString(), deduct);
		}
		user.setBalance(new BigDecimal(newbalance));
		user.setUpdateTime(new Date());
		return crowUserInfoDao.save(user);
	}
	
	@Override
	public CrowUserInfo createTempQrcode(String userid) {
		// TODO Auto-generated method stub
		CrowUserInfo userInfo = crowUserInfoDao.findByUserId(userid);
		if(userInfo==null)
			return null;
		Result<SunQrcode> result = qrcodeService.createTempQrcode("", 0L, "wjqw");
		if(null != result && 1 == result.getState()){
			userInfo.setSunQrcode(result.getT());
		}
		userInfo.setUpdateTime(new Date());
		crowUserInfoDao.save(userInfo);
		return userInfo;
	}
	@Override
	public  Result<String>  sendUserImage(String userid, String url, byte[] data) {
		InputStream sbs = new ByteArrayInputStream(data); 
		Media media = MediaAPI.mediaUpload(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET) , MediaType.image, sbs);
		if(media.getMedia_id()!=null){
			ImageMessage message= new ImageMessage(userid, media.getMedia_id());
			MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), message);
			return new Result<String>("","发送成功");
		}else{
			return new Result<String>("发送失败","");
		}
	}
	@Override
	public CrowUserInfo findCrowUserInfoByQrcodeId(String userid) {
		// TODO Auto-generated method stub
		return crowUserInfoDao.findCrowUserInfoByQrcodeId(userid);
	};

}
