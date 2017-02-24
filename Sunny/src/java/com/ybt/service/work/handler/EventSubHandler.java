package com.ybt.service.work.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.SystemConstant;
import com.ybt.common.constant.Wechat;
import com.ybt.common.util.DateUtil;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.OrderService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunLetterService;

import wechat.api.MessageAPI;
import wechat.bean.EventMessage;
import wechat.bean.templatemessage.TemplateMessage;
import wechat.bean.templatemessage.TemplateMessageItem;
import wechat.bean.xmlmessage.XMLTextMessage;
import wechat.support.TokenManager;
import wechat.util.WXUtil;
@Component
public class EventSubHandler  implements MessageHandler{

	@Autowired
	private IWechatService wechatService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private HashMap constant;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SunCardService cardService;
	@Autowired
	private EventScanHandler eventScanHandler;
	@Autowired
	private SunLetterService letterService;
	public String onMsg(EventMessage eventMessage, ServletOutputStream outputStream) {
		System.out.println("订阅事件");
		StringBuffer toUserString=new StringBuffer("");
		String openId = eventMessage.getFromUserName();
		String event = eventMessage.getEvent();
		SunWechatUser wxUser = wechatService.saveSunWechatUser(openId,true);
		
		/*
		toUserString.append( "亲~等你关注很久了，终于等到你啦，欢迎关注给点儿阳光！/:sun。");
		toUserString .append( "\n\n在这里，每天都能欣赏到来自世界各地当天日出照片，同时送给朋友一份有意义的祝福；传递爱，用光影记录生活\n");
		toUserString .append( "阳光小编带你  <a href='"+WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/index", false)+"'>逛一下</a>");
		toUserString .append( "\n\n参与  <a href='"+WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/activity/daiyan", false)+"'>“我为阳光代言”</a>活动获赠阳光卡");
    	com.ybt.common.util.Page<SunDdOrder> pageUtil = new com.ybt.common.util.Page<SunDdOrder>();
		pageUtil.setPageNo(1);
		try {
			orderService.findOrderSendByOpenId(openId, pageUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<SunDdOrder> mylist = pageUtil.getResult();
		for (SunDdOrder sunDdOrder : mylist) {
			
			if(sunDdOrder.getBuyerId()==null){
				toUserString.append( "\n\n你的朋友为你祝福：\n");
				toUserString.append("<a href='"+WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false)+"'>立即查看</a>");
				break;
			}
			SunWechatUser buyer = wechatService.findOne(sunDdOrder.getBuyerId());
			if(buyer!=null){
			    toUserString.append("\n\n你的朋友“"+buyer.getWechatNickname()+"”为你祝福：");
			    toUserString .append( "<a href='"+WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false)+"'>立即查看</a>");
				
			}else{
				toUserString.append( "\n\n你的朋友为你祝福：\n");
				toUserString.append("<a href='"+WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false)+"'>立即查看</a>");
				
			}
			
			break;
		}
		com.ybt.common.util.Page<SunLetter> pageLetter = new com.ybt.common.util.Page<SunLetter>();
		pageLetter.setPageNo(1);
		pageLetter.setPageSize(2);
		letterService.findMyReceivedLetterList(openId, pageLetter);
		List<SunLetter> letterlist = pageLetter.getResult();
		for (SunLetter sunLetter : letterlist) {
			toUserString.append( "\n\n"+sunLetter.getSender()+"寄来一封简信\n");
			toUserString.append("<a href='"+WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me/letterList?status=1", false)+"'>立即查看</a>");
			break;
		}
		*/
		
		
		
		toUserString.append( "一年之计在于春，一日之计在于晨！！/:sun。");
		toUserString .append( "\n欢迎参加“闻鸡起伍”挑战！");
		toUserString.append("<a href='https://mp.weixin.qq.com/s/OJ37PA3-iJdaq90q8_KhaQ'>闻鸡起伍挑战规则</a>");
		
		//eventScan( toUserString, wxUser, openId , event,eventMessage.getEventKey(),true);
		// 创建回复
		XMLTextMessage xmlTextMessage = new XMLTextMessage(
				openId, eventMessage.getToUserName(), toUserString.toString());
		xmlTextMessage.outputStreamWrite(outputStream);
		
		//送礼  关注
		Result<SunCard> result =cardService.giftSunECardForActivity(SystemConstant.CODE_CARD_E_GIFT_GUANZHU, 1, wxUser.getId());
		if(result.getState()==1){
			if(SystemConstant.CODE_CARD_E_GIFT_GUANZHU_OPEN){
					// 创建回复
					LinkedHashMap<String, TemplateMessageItem> map2 = new LinkedHashMap<String, TemplateMessageItem>();
					map2.put("first", new TemplateMessageItem("“关注送大礼”，获赠一张阳光卡 \n","#ff6200"));
					map2.put("keyword1", new TemplateMessageItem("阳光卡","#000000"));
					map2.put("keyword2", new TemplateMessageItem("1份","#000000"));
					map2.put("keyword3", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
					map2.put("remark", new TemplateMessageItem("点击\"详情\" 前往个人中心查看阳光卡。 ","#000000"));
					TemplateMessage tm2= new TemplateMessage();
					tm2.setTemplate_id(SystemConstant.TEMPLATE_LIPIN);
					tm2.setTouser(wxUser.getId());
					tm2.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false));
					tm2.setData(map2);
					MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm2);
				}
		}
		 
		return null;
	}

}
