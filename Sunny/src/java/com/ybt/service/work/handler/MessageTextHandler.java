package com.ybt.service.work.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletOutputStream;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.SystemConstant;
import com.ybt.common.constant.Wechat;
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.SendWeixinMessage;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.WxMessageText;
import com.ybt.service.work.MessageService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunTempRecordService;

import wechat.api.MessageAPI;
import wechat.bean.EventMessage;
import wechat.bean.templatemessage.TemplateMessage;
import wechat.bean.templatemessage.TemplateMessageItem;
import wechat.bean.xmlmessage.XMLTextMessage;
import wechat.support.TokenManager;
import wechat.util.WXUtil;
@Component
public class MessageTextHandler  implements MessageHandler{
	@Autowired
	private SunTempRecordService tempRecordService;
	@SuppressWarnings("rawtypes")
	@Autowired
	private HashMap constant;
	@Autowired
	private MessageService  messageService;
	@Autowired
	private SunCardService cardService;
	
	@Override
	public String onMsg(EventMessage eventMessage, ServletOutputStream outputStream) {
			StringBuffer toUserString=new StringBuffer("");
			String openId = eventMessage.getFromUserName();
			String content=eventMessage.getContent();
			//保存
			Result<WxMessageText> rmt=messageService.saveMessageText(openId, content);
			
			if(content.equals("##123")){
				SendWeixinMessage.sendMessage("闻鸡起伍事件提醒", "恭喜打卡成功", "打卡提醒", "2017-2-14 16:44", "查看个人进度", "", openId);
			}
			if(content.equals("你好")){
				toUserString.append( "你好");
				// 创建回复
				XMLTextMessage xmlTextMessage = new XMLTextMessage(
						openId, eventMessage.getToUserName(), toUserString.toString());
				xmlTextMessage.outputStreamWrite(outputStream);
				if(rmt.getState()==1){
					WxMessageText message= rmt.getT();
					message.setReply(1);
					message.setUpdateTime(new Date());
					messageService.save(message);
				}
				
			}
			
			if(content.equals(SystemConstant.CODE_CARD_E_GIFT_20170101_MSG)){
				//2017年01月01日
				Result<SunCard> result =cardService.giftSunECardForActivity(SystemConstant.CODE_CARD_E_GIFT_20170101_CODE, 1, openId);
				if(result.getState()==1){
					if(SystemConstant.CODE_CARD_E_GIFT_20170101_OPEN){
							// 创建回复
							LinkedHashMap<String, TemplateMessageItem> map2 = new LinkedHashMap<String, TemplateMessageItem>();
							map2.put("first", new TemplateMessageItem("恭喜您，获赠一张阳光卡 \n","#ff6200"));
							map2.put("keyword1", new TemplateMessageItem("阳光卡","#000000"));
							map2.put("keyword2", new TemplateMessageItem("1份","#000000"));
							map2.put("keyword3", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
							map2.put("remark", new TemplateMessageItem("点击\"详情\" 前往个人中心查看阳光卡。 ","#000000"));
							TemplateMessage tm2= new TemplateMessage();
							//tm2.setTemplate_id("Vm3WBldQcnbCAwslg7d3NxgOgWY8EUY4t63AhmQoG-Y");
							tm2.setTemplate_id(SystemConstant.TEMPLATE_LIPIN);
							tm2.setTouser(openId);
							tm2.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false));
							tm2.setData(map2);
							MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm2);
						}
				}
				
			}
			//游戏 A-******** 活动
			if(content.length()==10&&content.substring(0, 2).equals("A-")&&StringUtil.isNumeric(content.substring(2, 10))){
				//2017年01月10日
				Result<SunCard> result =cardService.giftSunECardForActivity(SystemConstant.CODE_CARD_E_GIFT_YOUCI_A_8_CODE, 1, openId);
				if(result.getState()==1){
					if(SystemConstant.CODE_CARD_E_GIFT_YOUCI_A_8_OPEN){
							// 创建回复
							LinkedHashMap<String, TemplateMessageItem> map2 = new LinkedHashMap<String, TemplateMessageItem>();
							map2.put("first", new TemplateMessageItem("恭喜您，获赠一张阳光卡 \n","#ff6200"));
							map2.put("keyword1", new TemplateMessageItem("阳光卡","#000000"));
							map2.put("keyword2", new TemplateMessageItem("1份","#000000"));
							map2.put("keyword3", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
							map2.put("remark", new TemplateMessageItem("点击\"详情\" 前往个人中心查看阳光卡。 ","#000000"));
							TemplateMessage tm2= new TemplateMessage();
							//tm2.setTemplate_id("Vm3WBldQcnbCAwslg7d3NxgOgWY8EUY4t63AhmQoG-Y");
							tm2.setTemplate_id(SystemConstant.TEMPLATE_LIPIN);
							tm2.setTouser(openId);
							tm2.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false));
							tm2.setData(map2);
							MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm2);
						}
				}
			}
			/*if(content.equals("生日阳光")){
				toUserString.append( " <a href='"+WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/cake/show", false)+"'>点击查看 生日阳光 订单信息</a>");
				// 创建回复
				XMLTextMessage xmlTextMessage = new XMLTextMessage(
						openId, eventMessage.getToUserName(), toUserString.toString());
				xmlTextMessage.outputStreamWrite(outputStream);
				if(rmt.getState()==1){
					WxMessageText message= rmt.getT();
					message.setReply(1);
					message.setUpdateTime(new Date());
					messageService.save(message);
				}
				
			}*/
			//十位数 活动
			if(SystemConstant.CODE_CARD_E_GIFT_YOUXI_10WEI_OPEN){
			if(content.length()==10){
				//2017年01月10日
				Result<SunCard> result =cardService.giftSunECardForActivity(SystemConstant.CODE_CARD_E_GIFT_YOUXI_10WEI_CODE, 1, openId);
				if(result.getState()==1){
					
							// 创建回复
							LinkedHashMap<String, TemplateMessageItem> map2 = new LinkedHashMap<String, TemplateMessageItem>();
							map2.put("first", new TemplateMessageItem("恭喜您，获赠一张阳光卡 \n","#ff6200"));
							map2.put("keyword1", new TemplateMessageItem("阳光卡","#000000"));
							map2.put("keyword2", new TemplateMessageItem("1份","#000000"));
							map2.put("keyword3", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
							map2.put("remark", new TemplateMessageItem("点击\"详情\" 前往个人中心查看阳光卡。 ","#000000"));
							TemplateMessage tm2= new TemplateMessage();
							//tm2.setTemplate_id("Vm3WBldQcnbCAwslg7d3NxgOgWY8EUY4t63AhmQoG-Y");
							tm2.setTemplate_id(SystemConstant.TEMPLATE_LIPIN);
							tm2.setTouser(openId);
							tm2.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false));
							tm2.setData(map2);
							MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm2);
						}
				}
			}
			//输入 新年快乐 送5张 活动
			if(SystemConstant.CODE_CARD_E_GIFT_CHUNJIE_OPEN){
				if(content.equals(SystemConstant.CODE_CARD_E_GIFT_CHUNJIE_MSG)){
					Result<SunCard> result =cardService.giftSunECardForActivity(SystemConstant.CODE_CARD_E_GIFT_CHUNJIE_CODE, 3, openId);
					result =cardService.giftSunECardForActivity(SystemConstant.CODE_CARD_E_GIFT_CHUNJIE_CODE, 3, openId);
					result =cardService.giftSunECardForActivity(SystemConstant.CODE_CARD_E_GIFT_CHUNJIE_CODE, 3, openId);
					if(result.getState()==1){
						// 创建回复
						LinkedHashMap<String, TemplateMessageItem> map2 = new LinkedHashMap<String, TemplateMessageItem>();
						map2.put("first", new TemplateMessageItem("恭喜您，获赠三张阳光卡 \n","#ff6200"));
						map2.put("keyword1", new TemplateMessageItem("阳光卡","#000000"));
						map2.put("keyword2", new TemplateMessageItem("3份","#000000"));
						map2.put("keyword3", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
						map2.put("remark", new TemplateMessageItem("点击\"详情\" 前往个人中心查看阳光卡。 ","#000000"));
						TemplateMessage tm2= new TemplateMessage();
						//tm2.setTemplate_id("Vm3WBldQcnbCAwslg7d3NxgOgWY8EUY4t63AhmQoG-Y");
						tm2.setTemplate_id(SystemConstant.TEMPLATE_LIPIN);
						tm2.setTouser(openId);
						tm2.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v2/me", false));
						tm2.setData(map2);
						MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm2);
					}
				}
			}
		return null;
	}

}
