package com.ybt.service.work.handler;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.service.work.IWechatService;

import wechat.bean.EventMessage;
@Component
public class EventUnSubHandler  implements MessageHandler{

	@Autowired
	IWechatService wechatService;
	@Override
	public String onMsg(EventMessage eventMessage, ServletOutputStream outputStream) {
		System.out.println("取消订阅事件");
		String openId = eventMessage.getFromUserName();
		wechatService.saveSunWechatUser(openId,false);
		return null;
	}

}
