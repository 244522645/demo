package com.ybt.web.wechat;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.common.util.CommonUtil;
import com.ybt.model.work.CrowPkMe;
import com.ybt.model.work.CrowPkOne;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.aop.Authorization;
import com.ybt.service.work.CrowAccountService;
import com.ybt.service.work.CrowPkMeService;
import com.ybt.service.work.CrowPkOneService;
import com.ybt.service.work.CrowUserInfoService;

/**
 *  个人模式挑战
 * */
@Controller
@RequestMapping(value = "/wechat/v3/crow/pkme")
public class CrowPKmeController {

	
	@Autowired
	private CrowPkMeService pkMeService;
	@Autowired
	private CrowAccountService crowAccountService;
	@Autowired
	private CrowUserInfoService crowUserInfoService;
	@Autowired(required = false)
	private CrowPkOneService crowPkOneService;
	
	private String baseView(String v) {
		return "/work/wechat/v3/pkme/"+v;
	}
	
	/*
	 *个人模式挑战
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {
		
		return baseView("select");
	}
	/*
	 *没有开启挑战时
	 */
	@RequestMapping(value="notpk" ,method = RequestMethod.GET)
	public String nopk(Model model,HttpServletRequest request) {
		
		return baseView("notpk");
	}
	/*
	 *闻鸡起伍 开启页面
	 */
	@RequestMapping(value="pkpay" ,method = RequestMethod.GET)
	@Authorization
	public String pkpay(Model model,HttpServletRequest request) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		
		CrowUserInfo userInfo = crowAccountService.findCrowUserBalance(w.getId());
		if(userInfo==null){
			return baseView("pk1zhifu");
		}
		Double money=5.00;
		Double shifu=5.00;
		Double dikou=0.00;
		int iRet = userInfo.getBalance().compareTo(new BigDecimal(money));
		if(iRet<0){
			shifu=0.00;
			BigDecimal shi=	CommonUtil.subtract(new BigDecimal(money),  userInfo.getBalance());
			shifu = shi.doubleValue();
			dikou=userInfo.getBalance().doubleValue();
		}else{
			shifu=0.00;
			dikou=5.00;
		}
		
		model.addAttribute("money", money.intValue());
		model.addAttribute("shifu", shifu.intValue());
		model.addAttribute("dikou", dikou.intValue());
		return baseView("pk1zhifu");
	}
	/*
	 *个人模式挑战   付款后获得 orderId 
	 */
	@RequestMapping(value="createFristPkme",method = RequestMethod.POST)
	@ResponseBody
	public Result<CrowPkMe> createFristPkme(Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "orderId", defaultValue = "") String orderId) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			 return new Result<CrowPkMe>("未登陆",null);
	   	}
		
		return pkMeService.createFristPkme(w.getId(),orderId,5.00);
	}
	/*
	 *查看是否已开启
	 */
	@RequestMapping(value="getFristPkme",method = RequestMethod.POST)
	@ResponseBody
	public Result< CrowPkMe > getFristPkme(Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "orderId", defaultValue = "") String orderId,
			@RequestParam(value = "type", defaultValue = "1") int type) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			 return new Result<CrowPkMe>("未登陆",null);
	   	}
		CrowUserInfo userInfo = crowAccountService.findCrowUserBalance(w.getId());
		/*List<CrowPkOne> pklist =	crowPkOneService.findByuserid(w.getId());
		if(pklist!=null){
			
		}*/
		
		CrowPkMe pkme = pkMeService.findByUserIdAndPkTypeAndPkStatus(w.getId(), 1, 1);
		if(pkme!=null){
				 return new Result<CrowPkMe>("已开启挑战，请按时打卡",null);
		}else{
			return new Result<CrowPkMe>("",new CrowPkMe());
		}
	}
	
	/*
	 *个人模式挑战   付款后获得 orderId 
	 */
	@RequestMapping(value="createSecondPkme",method = RequestMethod.POST)
	@ResponseBody
	public Result<CrowPkMe> createSecondPkme(Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "orderId", defaultValue = "") String orderId) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			 return new Result<CrowPkMe>("未登陆",null);
	   	}
		return pkMeService.createSecondPkme(w.getId(),orderId,21.00);
	}
	
}