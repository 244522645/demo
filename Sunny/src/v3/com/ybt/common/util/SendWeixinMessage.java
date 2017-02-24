/*   
 * Copyright (c) 2015-2020 *** Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * ***. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with ***.   
 *   
 */     
package com.ybt.common.util;

import com.ybt.common.constant.Wechat;

import wechat.api.MessageAPI;
import wechat.bean.message.TextMessage;
import wechat.support.TokenManager;

/**   
 * 微信消息  
 * @author AndyBao  
 * @version 4.0, 2017年2月14日 下午4:27:01   
 */   
public class SendWeixinMessage {
	
	
	/*
	 * 闻鸡起伍挑战通知
	 * 恭喜您，您在与“逆风飞翔”的对战中挑战成功 
	 * 内容：挑战成功 
	 * 时间：2017-02-01 09:09:09
	 * 点击详情 查看对战详情
	 * 
	 * @param title
	 * @param frist
	 * @param context
	 * @param date
	 * @param last
	 * @param url
	 * @param userId  
	 * @author AndyBao
	 * @version V4.0, 2017年2月14日 下午4:40:18 
	 */
	public static void sendMessage(String title,String frist,String context,String date,String last,String url,String userId){
		
		Thread t = new Thread(  
                new Thread(){  
                    @Override  
                    public void run() {  
                      
                    	StringBuffer tostr = new StringBuffer();
                		tostr.append(title+":\n");
                		tostr.append(" "+frist+"\n");
                		tostr.append("事件类型："+context+"\n");
                		tostr.append("事件时间："+date+"\n");
                		tostr.append("<a href='"+url+"'>点击这里</a>"+last+"\n");
                		TextMessage message = new TextMessage(userId, tostr.toString());
                		MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), message);                
                          
                    }  
                }  
        );  
        t.start();  
	}
}
  