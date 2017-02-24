package com.ybt.service.work.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.constant.Wechat;
import com.ybt.dao.work.WxTokenDao;
import com.ybt.model.work.WxToken;

import wechat.bean.Ticket;
import wechat.support.TicketManager;

@Component("timeOutTicketToken")
public class TimeOutTicketToken {
	
	@Autowired
	public WxTokenDao wxTokenDao;
	/**
	 * 处理微信获取access_token任务
	 * */
	public void TaskRun(){
		Ticket token =TicketManager.ticketManager(Wechat.APPID, Wechat.APPSECRET);
		
		List<WxToken> list = wxTokenDao.findWxTokenByType("ticket");
		WxToken tokenBeean = new WxToken();
		if(list!=null&&!list.isEmpty()){
			tokenBeean = list.get(0);
		}else{
			tokenBeean.setCreateTime(new Date());
		}
		tokenBeean.setToken(token.getTicket());
		tokenBeean.setExpires_in(token.getExpires_in());
		tokenBeean.setErro(token.getErrcode()+"|"+token.getErrmsg());
		tokenBeean.setUpdateTime(new Date());
		tokenBeean.setType("ticket");
		wxTokenDao.save(tokenBeean);
	}
}
