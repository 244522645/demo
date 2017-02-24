package com.ybt.web.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.util.Page;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.work.SunBlessService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunLetterService;
/**   
 * 我的-个人中心 control
 *  * @author jsj  
 * @version 4.0, 2016年7月28日 上午10:41:12   
 */   
@Controller
@RequestMapping(value = "/wechat/me")
public class MeController {
	
	@Autowired
	@Qualifier("sunBlessService")
	private SunBlessService blessService;
	
	@Autowired
	private SunLetterService letterService;
	
	@Autowired
	private SunCardService cardService;
	
	
	private String baseView(){
		return "/work/wechat/me/";
	}
	
	//个人中心首页
	@RequestMapping( method = RequestMethod.GET)
	public String mySunB(Model model,HttpServletRequest request, HttpServletResponse response) {
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		/*try{
			model.addAttribute("brcount", blessService.findMyReceivedBlessCount(w.getId()));
			model.addAttribute("bscount", blessService.findMySendBlessCount(w.getId()));
			model.addAttribute("bnscount", blessService.findMyNoSendBlessCount(w.getId()));
			
			model.addAttribute("lrcount", letterService.findMyReceivedLetterCount(w.getId()));
			model.addAttribute("lscount", letterService.findMySendLetterCount(w.getId()));
			model.addAttribute("lnscount", letterService.findMyNoSendLetterCount(w.getId()));
			
			model.addAttribute("ccount", cardService.getCardCountByUserId(w.getId()));
		}catch(Exception e){
			e.printStackTrace();
		}*/
//		return baseView()+"index";
		
		return "/work/wechat/v2.0/me/index";
	}
	
	//跳转到明信片列表页面
	@RequestMapping( method = RequestMethod.GET,value="blessList")
	public String blessList(Model model,HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value = "status", defaultValue = "1") int status) {
		model.addAttribute("status", status);
		return baseView()+"blessList";
	}
	
	//跳转到简信列表页面
	@RequestMapping( method = RequestMethod.GET,value="letterList")
	public String letterList(Model model,HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value = "status", defaultValue = "1") int status) {
		model.addAttribute("status", status);
		return baseView()+"letterList";
	}
	
	//查询明信片数据
	@RequestMapping( method = RequestMethod.POST,value="blessList")
	@ResponseBody
	public Page<SunBless> blessList(Model model,HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value = "page", defaultValue = "1") int pageNumber,
							@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
							@RequestParam(value = "status", defaultValue = "1") int status) {
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		com.ybt.common.util.Page<SunBless> pageUtil = new com.ybt.common.util.Page<SunBless>();
		pageUtil.setPageNo(pageNumber);
		pageUtil.setPageSize(pageSize);
		if(status==1){
			blessService.findMyReceivedBlessList(w.getId(), pageUtil);
		}else if(status==2){
			blessService.findMySendBlessList(w.getId(), pageUtil);
		}else if(status==3){
			blessService.findMyNoSendBlessList(w.getId(), pageUtil);
		}
		return pageUtil;
	}
	
	//查询简信数据
	@RequestMapping( method = RequestMethod.POST,value="letterList")
	@ResponseBody
	public Page<SunLetter> letterList(Model model,HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value = "page", defaultValue = "1") int pageNumber,
							@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
							@RequestParam(value = "status", defaultValue = "1") int status) {
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		com.ybt.common.util.Page<SunLetter> pageUtil = new com.ybt.common.util.Page<SunLetter>();
		pageUtil.setPageNo(pageNumber);
		pageUtil.setPageSize(pageSize);
		if(status==1){
			letterService.findMyReceivedLetterList(w.getId(), pageUtil);
		}else if(status==2){
			letterService.findMySendLetterList(w.getId(), pageUtil);
		}else if(status==3){
			letterService.findMyNoSendLetterList(w.getId(), pageUtil);
		}
		return pageUtil;
	}
	
	//跳转到阳光卡列表
	@RequestMapping( method = RequestMethod.GET,value="cardList")
	public String cardList(Model model,HttpServletRequest request, HttpServletResponse response) {
		return "/work/wechat/v2.0/card/cardList";
//		return baseView()+"cardList";
	}
}