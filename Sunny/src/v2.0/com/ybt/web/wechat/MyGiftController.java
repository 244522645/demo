package com.ybt.web.wechat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ybt.common.util.Page;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.SunBlessService;

/**
 * 生日纪念卡（我的礼物管理）
 * */
@Controller
@RequestMapping(value = "/wechat/v2/me")
public class MyGiftController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(BlessController.class);
	
	@Autowired
	@Qualifier("sunBlessService")
	private SunBlessService blessService;
	
	@Autowired
	public IWechatService wechatService;
	
	private String baseView(String view) {
		return "/work/wechat/v2.0/myGift/"+view;
	}
	
	/*
	 *我的礼物首页
	 */
	@RequestMapping(method = RequestMethod.GET,value ="myGiftList")
	public String myGiftIndex(Model model,HttpServletRequest request) {
		
		return baseView("myGiftList");
	}

	//我收到的礼物
	@RequestMapping( method = RequestMethod.POST,value="myGiftList/data")
//	@ResponseBody
	public  ResponseEntity<Page<SunBless>>  blessList(Model model,HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value = "page", defaultValue = "1") int pageNumber,
							@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
							@RequestParam(value = "status", defaultValue = "1") int status) {
	
		try{
			SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
			com.ybt.common.util.Page<SunBless> pageUtil = new com.ybt.common.util.Page<SunBless>();
			pageUtil.setPageNo(pageNumber);
			pageUtil.setPageSize(pageSize);
			if(status==1){
				blessService.findMyReceivedBlessList(w.getId(), pageUtil);
			}
			if(status==2){
				pageUtil=	blessService.findMySendBlessList(w.getId(), pageUtil);
			}
			
//			else if(status==3){
//				blessService.findMyNoSendBlessList(w.getId(), pageUtil);
//			}
//			
			 return new ResponseEntity<>( pageUtil, HttpStatus.OK);
				
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		}
}