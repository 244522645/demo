package com.ybt.service.work.handler;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.WxMessageVoice;
import com.ybt.service.work.MessageService;

import wechat.bean.EventMessage;
@Component
public class MessageVoiceHandler  implements MessageHandler{

	@Autowired
	private MessageService  messageService;
	@Override
	public String onMsg(EventMessage eventMessage, ServletOutputStream outputStream) {
		String openId = eventMessage.getFromUserName();
		//保存
		String mediaId = eventMessage.getMediaId();
		if(!"".equals(mediaId)){
			Result<WxMessageVoice> rmt=messageService.saveMessageVoice(openId, mediaId);
		}
		// TODO Auto-generated method stub
		return null;
	}


}
