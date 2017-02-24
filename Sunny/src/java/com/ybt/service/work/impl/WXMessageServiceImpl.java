package com.ybt.service.work.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.Wechat;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.WXMessageDao;
import com.ybt.model.work.SunWXMessage;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.WXMessageService;

import wechat.api.MessageAPI;
import wechat.bean.BaseResult;
import wechat.bean.message.Message;
import wechat.bean.message.TextMessage;
import wechat.support.TokenManager;

@Component
public class WXMessageServiceImpl extends BaseServiceImpl<SunWXMessage, String> implements WXMessageService{
	
	@Autowired
	private WXMessageDao messageDao;
	@Override
	public BaseDao<SunWXMessage, String> getDao() {
		return messageDao;
	}
	@Override
	public Result<SunWXMessage> sendMessage(SunWXMessage message) {
		Message m = new TextMessage(message.getTouser(),message.getMessage());
		BaseResult result = MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), m);
		
		if(result.getErrmsg()==null){
			message.setStates(1);
		}
		messageDao.save(message);
		return new Result<SunWXMessage> ("",message);
	}
}
