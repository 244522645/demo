package com.ybt.service.work.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletOutputStream;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.SystemConstant;
import com.ybt.common.constant.Wechat;
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.SendWeixinMessage;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunQrcode;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.work.CrowUserInfoService;
import com.ybt.service.work.CrowUserTreeService;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.OrderService;
import com.ybt.service.work.QrcodeService;
import com.ybt.service.work.ReserveBlessService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunDaiyanService;

import wechat.api.MessageAPI;
import wechat.bean.EventMessage;
import wechat.bean.message.TextMessage;
import wechat.bean.templatemessage.TemplateMessage;
import wechat.bean.templatemessage.TemplateMessageItem;
import wechat.bean.xmlmessage.XMLTextMessage;
import wechat.support.TokenManager;
import wechat.util.WXUtil;
@Component
public class EventScanHandler  implements MessageHandler{
	@Autowired
	private IWechatService wechatService;
	@Autowired
	private OrderService orderService;
	@Autowired
	@Qualifier("reserveBlessService")
	private ReserveBlessService reserveBlessService;
	@Autowired
	private HashMap<String,Object> constant;
	@Autowired
	private SunCardService cardService;
	@Autowired
	private SunDaiyanService daiyanService;
	@Autowired
	private QrcodeService qrcodeService;
	
	@Autowired
	private CrowUserInfoService crowUserInfoService;

	@Autowired
	private CrowUserTreeService crowUserTreeService;
	StringBuffer toUserString=new StringBuffer("");
	@Override
	public String onMsg(EventMessage eventMessage, ServletOutputStream outputStream) {
		
		String openId = eventMessage.getFromUserName();
		String eventType = eventMessage.getEvent();
		if(!"".equals(toUserString.toString())){
			XMLTextMessage xmlTextMessage = new XMLTextMessage(
					openId, eventMessage.getToUserName(), toUserString.toString());
			xmlTextMessage.outputStreamWrite(outputStream);
		}
		
		try {
			eventScan( toUserString, null, openId , eventType,eventMessage.getEventKey(),false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	 * 推广二维码
	 */
	@SuppressWarnings("deprecation")
	public String eventScan(StringBuffer toUserString,SunWechatUser wxUser,String userId ,String event,String eventKey ,boolean fans) throws Exception{
		
		System.out.println("带参二维码："+eventKey);
		//扫描商户二维码返回参数
		// 符号  _  后面的是参数 
		if(eventKey.indexOf("_")>-1){
			eventKey=eventKey.replaceAll("qrscene_", "");
		}
		
		wxUser = wechatService.findOne(userId);
		//渠道更新
		if (event.equals(MessageAPI.EVENT_TYPE_SUBSCRIBE)&&wxUser!=null&&eventKey!=null&&!"".equals(eventKey)) {
			wxUser.setChannel(eventKey);
			wechatService.save(wxUser);
		}
		
		if("CARD1_01-02".equals(eventKey)){
			// 创建回复
		}
		
		/*** 2017春节活动[开始] ***/
		if("ARTICLE_KANGOU_20161117".equals(eventKey)){//此二维码为：给点儿阳光二维码-看购20161117
			SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String time="2017-01-28 02:00:00";//活动截止日期  
			Date endTime = format.parse(time); 
			Date now = new Date();
			if(DateUtil.compareDate(endTime,now)){
				Result<SunCard> result_user =cardService.giftSunECardForActivity(SystemConstant.CODE_CARD_E_GIFT_2017NEWYEAR_CODE,
																           		 1, 
																           		 userId);
				if(result_user.getState()==1){
					if(SystemConstant.CODE_CARD_E_GIFT_20170101_OPEN){
						// 创建回复
						LinkedHashMap<String, TemplateMessageItem> map2 = new LinkedHashMap<String, TemplateMessageItem>();
						map2.put("first", new TemplateMessageItem("恭喜您，获赠一张阳光卡 \n","#ff6200"));
						map2.put("keyword1", new TemplateMessageItem("阳光卡","#000000"));
						map2.put("keyword2", new TemplateMessageItem("1张","#000000"));
						map2.put("keyword3", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
						map2.put("remark", new TemplateMessageItem("点击底部  \"详情\" 预约大年初一第一缕阳光","#FF0000"));
						TemplateMessage tm2= new TemplateMessage();
						tm2.setTemplate_id(SystemConstant.TEMPLATE_LIPIN);
						tm2.setTouser(userId);
						tm2.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v2/reserve#add-friend", false));
						tm2.setData(map2);
						MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm2);
					}
				}else{
					String url =constant.get("domainName")+ "/wechat/v2/reserve#add-friend";
					StringBuffer sbstr=new StringBuffer("");
					sbstr.append("您已经领取本活动赠送的阳光卡，不可重复领取！点击<a href='"+WXUtil.getOAuthUrl(url, false)+"'>'预约阳光'</a>");
					MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), new TextMessage(userId,sbstr.toString()));
				}
			}else{
				String url =constant.get("domainName")+ "/wechat/v2/reserve#add-friend";
				StringBuffer sbstr=new StringBuffer("");
				sbstr.append("\n");
				sbstr.append("活动已经结束，您可以点此链接前往<a href='"+WXUtil.getOAuthUrl(url, false)+"'>'预定阳光'</a>");
				sbstr.append("\n");
				MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), new TextMessage(userId,sbstr.toString()));
			}
		}
		/*** 2017春节活动[结束] ***/
		
		if("CARD_B_20161015".equals(eventKey)){
			
			Result<SunCard> result =cardService.giftSunECardFor20161015("", userId);
			if(result.getState()==1){
				// 创建回复
				LinkedHashMap<String, TemplateMessageItem> map = new LinkedHashMap<String, TemplateMessageItem>();
				map.put("first", new TemplateMessageItem("你好，你已成功领取礼品 \n","#ff6200"));
				map.put("keyword1", new TemplateMessageItem("99元阳光卡","#000000"));
				map.put("keyword2", new TemplateMessageItem("1份","#000000"));
				map.put("keyword3", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
				map.put("remark", new TemplateMessageItem("\n感谢你参加本次活动！\n ","#000000"));
				TemplateMessage tm= new TemplateMessage();
				//tm.setTemplate_id("Vm3WBldQcnbCAwslg7d3NxgOgWY8EUY4t63AhmQoG-Y");
				tm.setTemplate_id("85yxfJoT9Sn5Y3vouCmsb5kui7U-G_B4G42f82eiSk4");
				tm.setTouser(userId);
				tm.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/my/myList?tab=3", false));
				tm.setData(map);
				MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm);
			}
		}
		if(StringUtil.isNumeric(eventKey)){
			int value=Integer.parseInt(eventKey);
			//临时二维码  代言活动
			if(value>1000000){
				try{
					//判断 是否 有记录二维码
					SunQrcode qrcode =qrcodeService.getQrcodeByValue(new Long(value));
					if(qrcode!=null){
						//订单二维码
						if(qrcode.getName().equals("order")){
								
							SunBless bless = reserveBlessService.getSunBlessByQrcodeId(qrcode.getId());
							if(bless==null)
								return "";
							String url =constant.get("domainName")+ "/wechat/v2/bless/sendeeinfo?orderId="+bless.getOrder().getId();
							StringBuffer sbstr=new StringBuffer("");
							sbstr.append("\n");
							sbstr.append("<a href='"+WXUtil.getOAuthUrl(url, false)+"'>点击查看礼物</a>");
							sbstr.append("\n\n");
							MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), new TextMessage(userId,sbstr.toString()));
							
								return "";
						}else if(qrcode.getName().equals("wjqw")){
							
							CrowUserInfo userInfo =crowUserInfoService.findCrowUserInfoByQrcodeId(qrcode.getId());
							if(userInfo==null)
								return "";
							if(event.equals(MessageAPI.EVENT_TYPE_SUBSCRIBE)){
								crowUserTreeService.saveUserTree(userInfo.getUser().getId(), wxUser);
							}
							
							Thread t = new Thread(  
					                new Thread(){  
					                    @Override  
					                    public void run() {  
					                      
					                    	StringBuffer sbstr=new StringBuffer("");
											sbstr.append("<a href='"+WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/acceptChallenge?otherUserId="+userInfo.getUser().getId(),  false)+"'>点击这里接受挑战>> </a>");
											try {
												Thread.sleep(1000);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
											MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), new TextMessage(userId,sbstr.toString()));
											
					                    }  
					                }  
					        );  
					        t.start();  
							
						}else{
							qrcode.setCount(qrcode.getCount()+1);
							qrcode.setFlow(fans ? qrcode.getFlow()+1 : qrcode.getFlow());
							qrcodeService.save(qrcode);
							daiyanService.createDaiyanUser(userId, qrcode);
						}
						
					}
				
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return "error";
	}
}
