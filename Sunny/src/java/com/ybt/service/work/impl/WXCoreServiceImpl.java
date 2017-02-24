package com.ybt.service.work.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.WXCheckModel;
import com.ybt.common.constant.Wechat;
import com.ybt.common.util.EncoderHandler;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.WXCoreService;
import com.ybt.service.work.handler.EventClickHandler;
import com.ybt.service.work.handler.EventLocationHandler;
import com.ybt.service.work.handler.EventScanHandler;
import com.ybt.service.work.handler.EventSubHandler;
import com.ybt.service.work.handler.EventUnSubHandler;
import com.ybt.service.work.handler.MessageImageHandler;
import com.ybt.service.work.handler.MessageTextHandler;
import com.ybt.service.work.handler.MessageVoiceHandler;

import wechat.api.MessageAPI;
import wechat.bean.EventMessage;
import wechat.util.XMLConverUtil;

@Component
public class WXCoreServiceImpl implements WXCoreService {
	
	@Autowired
	IWechatService wechatService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	public HashMap constant;

	@Autowired
	private EventClickHandler eventClickHandler;
	@Autowired
	private EventSubHandler eventSubHandler;
	@Autowired
	private EventUnSubHandler eventUnSubHandler;
	@Autowired
	private EventScanHandler eventScanHandler;
	@Autowired
	private EventLocationHandler eventLocationHandler;
	@Autowired
	private MessageTextHandler messageTextHandler;
	@Autowired
	private MessageImageHandler messageImageHandler;
	@Autowired
	private MessageVoiceHandler messageVoiceHandler;
	
	public String validate(String wxToken, WXCheckModel tokenModel) {
		
		String signature = tokenModel.getSignature();
		Long timestamp = tokenModel.getTimestamp();
		Long nonce = tokenModel.getNonce();
		String echostr = tokenModel.getEchostr();
		if (signature != null && timestamp != null & nonce != null) {
			String[] str = { wxToken, timestamp + "", nonce + "" };
			Arrays.sort(str); // 字典序排序
			String bigStr = str[0] + str[1] + str[2];
			// SHA1加密
			String digest = EncoderHandler.encode("SHA1", bigStr).toLowerCase();
			// 确认请求来至微信
			if (digest.equals(signature)) {
				// 最好此处将echostr存起来，以后每次校验消息来源都需要用到
				return echostr;
			}
		}
		return "error";
	}
	
	
	public String processRequest(String token,HttpServletRequest request, HttpServletResponse response) throws IOException {
		// xml格式的消息数据
		String respXml = "";
		ServletInputStream inputStream = request.getInputStream();
		ServletOutputStream outputStream = response.getOutputStream();
		String msgType = "";
		
		if (inputStream != null) {
			// 转换XML
			EventMessage eventMessage = XMLConverUtil.convertToObject(
					EventMessage.class, inputStream);
			String expireKey = eventMessage.getFromUserName() + "__"
					+ eventMessage.getToUserName() + "__"
					+ eventMessage.getMsgId() + "__"
					+ eventMessage.getCreateTime();
			
			if (Wechat.expireSet.contains(expireKey)) {
				// 重复通知不作处理
				return "";
			} else {
				Wechat.expireSet.add(expireKey);
			}
			
			msgType = eventMessage.getMsgType();
	          
			if (msgType.equals("event")||"event"==msgType) {// 如果是事件推送信息
				String event = eventMessage.getEvent();
				if (event == null || event.length() == 0)
					System.out.println("不能获得Even数据");
				return dispatchEvent(eventMessage,outputStream);
			}
			
			return dispatchMessage(eventMessage,outputStream);
		}
		return respXml;
	}
	
	
	 private String dispatchEvent(EventMessage eventMessage,ServletOutputStream outputStream){  
		String eventType = eventMessage.getEvent();
		String result="";
		if (eventType == null || eventType.length() == 0)
			return  "";
		switch(eventType){  
	        case MessageAPI.EVENT_TYPE_SUBSCRIBE:  
	        	result = eventSubHandler.onMsg(eventMessage, outputStream);
	        	String eventKey = eventMessage.getEventKey();
	        	if(eventKey.indexOf("_")>-1){
	    			result =eventScanHandler.onMsg(eventMessage, outputStream);
	    		}
	            break;  
	        case MessageAPI.EVENT_TYPE_UNSUBSCRIBE:  
	        	result = eventUnSubHandler.onMsg(eventMessage, outputStream);
	            break;  
	        case MessageAPI.EVENT_TYPE_CLICK:  
	        	result = eventClickHandler.onMsg(eventMessage, outputStream);
	            break;  
	        case MessageAPI.EVENT_TYPE_SCAN:  
	        	result =eventScanHandler.onMsg(eventMessage, outputStream);
	            break;  
	        case MessageAPI.EVENT_TYPE_LOCATION:  
	        	result = eventLocationHandler.onMsg(eventMessage, outputStream);
	            break;  
	        case  MessageAPI.EVENT_TYPE_VIEW:  
	            break;  
	        }  
	        return result;  
	    }  
	      
	    private String dispatchMessage(EventMessage eventMessage,ServletOutputStream outputStream){  
	    	String result=null;
	    	try{
	    	// 转换XML
			String msgType = eventMessage.getMsgType();
			
	        //根据消息类型，创建不同的处理类  
	        switch(msgType){  
		        case MessageAPI.REQ_MESSAGE_TYPE_TEXT:  
		        	result = messageTextHandler.onMsg(eventMessage, outputStream);
		            break;  
		        case MessageAPI.REQ_MESSAGE_TYPE_IMAGE:  
		        	result = messageImageHandler.onMsg(eventMessage, outputStream);
		            break;  
		        case MessageAPI.REQ_MESSAGE_TYPE_LINK:  
		        	 break;  
		        case MessageAPI.REQ_MESSAGE_TYPE_LOCATION:  
		        	 break;    
		        case MessageAPI.REQ_MESSAGE_TYPE_VIDEO:  
		        	 break;  
		        case MessageAPI.REQ_MESSAGE_TYPE_VOICE: 
	        	result = messageVoiceHandler.onMsg(eventMessage, outputStream);
	        	 break;  
	        }  
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	        return result;  
	    }  
	
}
