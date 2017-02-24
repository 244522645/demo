package com.ybt.service.work.handler;

import java.util.HashMap;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.service.work.IWechatService;

import wechat.bean.EventMessage;
import wechat.bean.xmlmessage.XMLTextMessage;
import wechat.util.WXUtil;
@Component
public class EventClickHandler  implements MessageHandler{

	@Autowired
	private IWechatService wechatServices;
	@Autowired
	private HashMap<String,Object> constant;
	@Override
	public String onMsg(EventMessage eventMessage, ServletOutputStream outputStream) {
			String eventKey = eventMessage.getEventKey();
			if (eventKey == null || eventKey.length() == 0) {
				return null;
			}
			String openId = eventMessage.getFromUserName();
			if (eventKey.equals("SUN_DAIYAN_BTN")) {
				StringBuffer sbstr=new StringBuffer("");
				sbstr.append("参与“我为阳光代言”活动，把今天的阳光打包，送给朋友，让阳光传递正能量。\n\n");
				sbstr.append("同时您将获赠一张“阳光卡”（阳光卡可以制作明信片哦）。\n");
				sbstr.append("您的朋友关注我们之后，您将再次获赠一张阳光卡，次数累加，上不封顶。\n\n");
				sbstr.append("时光静美，阳光正暖！\n\n");
				sbstr.append("<a href='"+WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/activity/daiyan", false)+"'>点击开始代言</a>");
				XMLTextMessage xmlTextMessage = new XMLTextMessage(
						openId,
							eventMessage.getToUserName(), sbstr.toString());
					xmlTextMessage.outputStreamWrite(outputStream);
					return "";
			}
			
			if (eventKey.equals("CROW_MORNING_BTN")) {
				StringBuffer sbstr=new StringBuffer("");
				sbstr.append("【发图打卡】请发送今日跑步截图。");
				sbstr.append("\n（截图需包含日期、公里数、配速、头像。推荐跑步APP“咕咚”截图）\n");
				sbstr.append("\n<a href='https://mp.weixin.qq.com/s/OJ37PA3-iJdaq90q8_KhaQ'>闻鸡起伍挑战规则</a>");
				XMLTextMessage xmlTextMessage = new XMLTextMessage(
						openId,
							eventMessage.getToUserName(), sbstr.toString());
					xmlTextMessage.outputStreamWrite(outputStream);
					return null;
			}
			
		return null;
	}

}
