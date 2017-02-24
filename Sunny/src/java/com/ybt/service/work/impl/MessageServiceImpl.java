package com.ybt.service.work.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.Wechat;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.MessageDao;
import com.ybt.dao.work.MessageImageDao;
import com.ybt.dao.work.MessageTextDao;
import com.ybt.dao.work.MessageVoiceDao;
import com.ybt.model.work.SunZyImages;
import com.ybt.model.work.SunZyVoice;
import com.ybt.model.work.WxMessage;
import com.ybt.model.work.WxMessageImage;
import com.ybt.model.work.WxMessageText;
import com.ybt.model.work.WxMessageVoice;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.ImagesService;
import com.ybt.service.work.MessageService;
import com.ybt.service.work.VoiceService;

import wechat.api.MediaAPI;
import wechat.support.TokenManager;

@Component
public class MessageServiceImpl extends BaseServiceImpl<WxMessage, String> implements MessageService{
	
	@Autowired
	private MessageDao messageDao;

	@Autowired
	private MessageTextDao messageTextDao;

	@Autowired
	private MessageImageDao messageImageDao;

	@Autowired
	private MessageVoiceDao messageVoiceDao;
	
	@Autowired
	private VoiceService voiceService;
	
	@Autowired
	private ImagesService imageService;
	@Override
	public BaseDao<WxMessage, String> getDao() {
		return messageDao;
	}
	@Override
	public Result<WxMessage> saveMessage(WxMessage message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Result<WxMessageText> saveMessageText(String userid, String message) {
		WxMessageText text = new WxMessageText(userid,message);
		messageTextDao.save(text);
		return new Result<WxMessageText>("",text);
	}
	@Override
	public Result<WxMessageImage> saveMessageImage(String userid, String image) {

		byte[] byt = MediaAPI.mediaGet(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), image);
		
		if(byt != null&& byt.length>120){
			
			Result<SunZyImages> result = imageService.downImgByte(byt, "message/images");
			if(result.getState()==1){
				WxMessageImage text = new WxMessageImage(userid,result.getT().getId());
				messageImageDao.save(text);
				return new Result<WxMessageImage>("",text);
			}
		}
		return new Result<WxMessageImage>("保存失败",null);
	}
	@Override
	public Result<WxMessageVoice> saveMessageVoice(String userid, String voice) {
		byte[] byt = MediaAPI.mediaGet(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), voice);
		try{
			
		
			if(byt != null&& byt.length>120){
				
				Result<SunZyVoice> result = voiceService.downVoiceByte(byt,0,"message/voice");
				if(result.getState()==1){
					WxMessageVoice text = new WxMessageVoice(userid,result.getT().getId());
					messageVoiceDao.save(text);
					return new Result<WxMessageVoice>("",text);
				}
			}
			return new Result<WxMessageVoice>("保存失败",null);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new Result<WxMessageVoice>("保存失败",null);
	}
	
}
