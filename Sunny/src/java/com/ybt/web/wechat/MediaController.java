package com.ybt.web.wechat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.Wechat;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyVoice;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.VoiceService;

import wechat.api.MediaAPI;
import wechat.support.TokenManager;

/**
 *   媒体 管理
 * */
@Controller
@RequestMapping(value = "/wechat/media")
public class MediaController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(MediaController.class);
	
	@Autowired
	private VoiceService voiceService;
	@Autowired
	public IWechatService wechatService;
	
	
	private String baseView() {
		return "/work/wechat/media";
	}
	
	/*
	 * 提交
	 */
	@RequestMapping(value ="upVoice",method = RequestMethod.POST)
	@ResponseBody
	public Result<SunZyVoice> indexPut(Model model,HttpServletRequest request,
			@RequestParam(value = "token",defaultValue="") String token,
			@RequestParam(value = "longTime",defaultValue="0") int longTime,
			@RequestParam(value = "serverId",defaultValue="") String serviecId) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			return new Result<SunZyVoice>("请先关注",null);
	   	}
		
		if(!"".equals(serviecId)){
			byte[] byt = MediaAPI.mediaGet(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), serviecId);
			
			if(byt != null&& byt.length>120){
				
				Result<SunZyVoice> result = voiceService.downVoiceByte(byt,longTime,"voice");
				return result;
			}
		}
		
		return new Result<SunZyVoice>("请先关注",null);
	}
	
}