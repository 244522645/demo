package com.ybt.web.wechat;

import java.util.Date;

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
import com.ybt.model.work.SunCollect;
import com.ybt.model.work.SunCollectBless;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyImages;
import com.ybt.model.work.SunZyVoice;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.ImagesService;
import com.ybt.service.work.SunCollectBlessService;
import com.ybt.service.work.SunCollectService;
import com.ybt.service.work.VoiceService;

import wechat.api.MediaAPI;
import wechat.support.TokenManager;

/**
 *   收集祝福
 * */
@Controller
@RequestMapping(value = "/wechat/collect")
public class CollectController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(CollectController.class);
	
	@Autowired
	private SunCollectService collectService;
	@Autowired
	private VoiceService voiceService;
	@Autowired
	private ImagesService imagesService;
	@Autowired
	private SunCollectBlessService collectBlessService;
	@Autowired
	public IWechatService wechatService;
	
	
	private String baseView() {
		return "/work/wechat/collect";
	}
	
	/*
	 *收集编辑
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {

		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			return baseView()+"/guanzhu";
	   	}
		
		return baseView()+"/index";
	}
	
	/*
	 *收集编辑 提交
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Result<SunCollect> indexPut(Model model,HttpServletRequest request,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "birthday") String birthday,
			@RequestParam(value = "remark") String remark) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			return new Result<SunCollect>("请先关注",null);
	   	}
		
		SunCollect collect =new SunCollect();
		collect.setUserId(w);
		collect.setCreateTime(new Date());
		collect.setName(name);
		collect.setRelease(0);
		collect.setRemark(remark);
		
		collectService.save(collect);
		
		return new Result<SunCollect>(null,collect);
	}
	
	/*
	 *收集页-收集
	 */
	@RequestMapping(value ="thanks")
	public String shou(Model model,HttpServletRequest request,
			@RequestParam(value="collectId" , defaultValue="")String collectId) {
		
		SunCollect collect = collectService.findOne(collectId);
		model.addAttribute("collect", collect);
		return baseView()+"/thanks";
	}
	
	/*
	 *收集页-写祝福
	 */
	@RequestMapping(value ="write",method=RequestMethod.GET)
	public String write(Model model,HttpServletRequest request,
			@RequestParam(value="collectId" , defaultValue="")String collectId) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			return baseView()+"/guanzhu";
	   	}
		SunCollect collect = collectService.findOne(collectId);
		model.addAttribute("collect", collect);
		return baseView()+"/write";
	}
	
	/*
	 *收集页-写祝福 -提交
	 */
	@RequestMapping(value ="write",method=RequestMethod.POST)
	@ResponseBody
	public Result<SunCollectBless> writePut(Model model,HttpServletRequest request,
			@RequestParam(value = "collectId") String collectId,
			@RequestParam(value = "bless",defaultValue="") String bless,
			@RequestParam(value = "image",defaultValue="") String image,
			@RequestParam(value = "voice",defaultValue="") String voice) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			return new Result<SunCollectBless>("请先关注",null);
	   	}
		
		if(collectId==null)
			return new Result<SunCollectBless>("请求无效",null);
		
		if("".equals(collectId))
			return new Result<SunCollectBless>("请求无效",null);
		
		
		
		SunCollectBless collectBless = new SunCollectBless();
		collectBless.setBless(bless);
		collectBless.setCollectId(collectId);
		collectBless.setUserId(w);
		
		if(!"".equals(voice)){
			byte[] byt = MediaAPI.mediaGet(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), voice);
			
			if(byt != null&& byt.length>100){
				
				Result<SunZyVoice> result = voiceService.downVoiceByte(byt,0,"voice");
				if(result.getState() == 1){
					collectBless.setVoice(result.getT());
				}
			}
		}
		
		if(!"".equals(image)){
			Result<SunZyImages> result = imagesService.downImgByte(MediaAPI.mediaGet(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), image),"collect");
			if(result.getState()==1)
				collectBless.setImage(result.getT());
		}
		collectBless.setCreateTime(new Date());
		collectBlessService.save(collectBless);
		
		return new Result<SunCollectBless>(null,collectBless);
	}
}