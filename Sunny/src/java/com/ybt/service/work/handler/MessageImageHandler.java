package com.ybt.service.work.handler;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.WxMessageImage;
import com.ybt.service.work.CrowPunchService;
import com.ybt.service.work.MessageService;

import wechat.bean.EventMessage;
import wechat.bean.xmlmessage.XMLTextMessage;
import wechat.util.WXUtil;
@Component
public class MessageImageHandler  implements MessageHandler{

	@Autowired
	private MessageService  messageService;
	@Autowired
	private CrowPunchService crowPunchService;
	@SuppressWarnings("rawtypes")
	@Autowired
	private HashMap constant;
	@Override
	public String onMsg(EventMessage eventMessage, ServletOutputStream outputStream) {
		String openId = eventMessage.getFromUserName();
		//保存
		String mediaId = eventMessage.getMediaId();
		if(!"".equals(mediaId)){
			Result<WxMessageImage> rmt=messageService.saveMessageImage(openId, mediaId);
			
			//判断 每日 5点-9点  是打卡时间 ，其他时间图片 不处理或提示 闻鸡起五 打卡时间是5点-9点。
			
			Date d = new Date();
			Calendar calendar = Calendar.getInstance();  
	        calendar.set(Calendar.HOUR_OF_DAY, 5); //凌晨5点  
	        calendar.set(Calendar.MINUTE, 0);  
	        calendar.set(Calendar.SECOND, 0);  
	        Date start=calendar.getTime(); 
	        calendar.set(Calendar.HOUR_OF_DAY, 9); //凌晨5点  
	        calendar.set(Calendar.MINUTE, 0);  
	        calendar.set(Calendar.SECOND, 0);  
	        Date end=calendar.getTime();  
	        //如果第一次执行定时任务的时间 小于当前的时间  
	        //此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。  
	        if (d.after(start)&&d.before(end)) {  
	        	if(rmt.getState()==1){
	        		crowPunchService.savePunch(openId, rmt.getT());
		        	
		        	// 创建回复
		        	String toUserString="恭喜！闻鸡起伍打卡提交成功，系统将进行审核。\n\n 小提示：每天9点前打卡，公里数至少2.5km 才会审核通过。";
					XMLTextMessage xmlTextMessage = new XMLTextMessage(
							openId, eventMessage.getToUserName(), toUserString.toString());
					xmlTextMessage.outputStreamWrite(outputStream);
	        	}
					
				
	        }else{
	        	
	        	// 创建回复http://image.yunbeitai.com/Sunny
	        	String toUserString="抱歉！打卡无效。闻鸡起伍打卡时间为早5点~9点。"+"<a href='https://mp.weixin.qq.com/s/OJ37PA3-iJdaq90q8_KhaQ'>闻鸡起伍挑战规则</a>";
				XMLTextMessage xmlTextMessage = new XMLTextMessage(
						openId, eventMessage.getToUserName(), toUserString.toString());
				xmlTextMessage.outputStreamWrite(outputStream);
	        }  
		}
		// TODO Auto-generated method stub
		return null;
	}

}
