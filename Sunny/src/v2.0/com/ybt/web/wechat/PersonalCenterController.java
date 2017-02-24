package com.ybt.web.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ybt.model.work.SunWechatUser;
import com.ybt.service.aop.Authorization;
import com.ybt.service.work.SunBlessService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunLetterService;
/**   
 * 我的-个人中心 control
 *  * @author lhq  
 * @version 
 */   
@Controller
@RequestMapping(value = "/wechat/v2/me")
public class PersonalCenterController {
	
	@Autowired
	@Qualifier("sunBlessService")
	private SunBlessService blessService;
	
	@Autowired
	private SunLetterService letterService;
	
	@Autowired
	private SunCardService cardService;
	
	
	private String baseView(){
		return "/work/wechat/v2.0/me/";
	}
	
	//个人中心首页
	@Authorization
	@RequestMapping( method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request, HttpServletResponse response) {
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		try{
			//model.addAttribute("ccount", cardService.getCardCountByUserId(w.getId()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return baseView()+"index";
	}
	
}