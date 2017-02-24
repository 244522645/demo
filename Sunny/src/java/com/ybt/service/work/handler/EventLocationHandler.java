package com.ybt.service.work.handler;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.service.work.IWechatService;

import wechat.bean.EventMessage;
@Component
public class EventLocationHandler  implements MessageHandler{

	@Autowired
	IWechatService wechatService;
	@Override
	public String onMsg(EventMessage eventMessage, ServletOutputStream outputStream) {
		String openId = eventMessage.getFromUserName();
		//保存 更新 微信信息2015-09-07 11:35:45
		wechatService.saveSunWechatUserLocation(openId, eventMessage.getLatitude(), eventMessage.getLongitude());
		return null;
	}

}
