package com.ybt.web.wechat;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.common.util.CommonUtil;
import com.ybt.model.work.CrowPkOne;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.aop.Authorization;
import com.ybt.service.work.CrowAccountService;
import com.ybt.service.work.CrowPkMeService;
import com.ybt.service.work.CrowPkOneService;
import com.ybt.service.work.CrowUserInfoService;
import com.ybt.service.work.IWechatService;

/**
 *  支付
 * */
@Controller
@RequestMapping(value = "/wechat/v3")
public class CrowPayController {

	@Autowired
	private CrowAccountService crowAccountService;
	@Autowired
	private CrowUserInfoService crowUserInfoService;
	@Autowired
	private IWechatService wechatService;
	@Autowired
	private CrowPkOneService crowPkOneService;
	@Autowired
	private CrowPkMeService crowPkMeService;
	
	
	
	private String baseView(String v) {
		return "/work/wechat/v3/pkme/"+v;
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
		if(userInfo.getLevel()==1){
			 money=21.00;
			 shifu=21.00;
			 dikou=0.00;
		}
		
		int iRet = userInfo.getBalance().compareTo(new BigDecimal(money));
		if(iRet<0){
			shifu=0.00;
			BigDecimal shi=	CommonUtil.subtract(new BigDecimal(money),  userInfo.getBalance());
			shifu = shi.doubleValue();
			dikou=userInfo.getBalance().doubleValue();
		}else{
			shifu=0.00;
			dikou=5.00;
			if(userInfo.getLevel()==1){
				 dikou=21.00;
			}
		}
		
		model.addAttribute("money", money.intValue());
		model.addAttribute("shifu", shifu.intValue());
		model.addAttribute("dikou", dikou.intValue());
		if(userInfo.getLevel()==0){
			return baseView("pk1zhifu");
		}
		if(userInfo.getLevel()==1){
			return baseView("pk2zhifu");
		}
		return baseView("pk1zhifu");
	}
	
	
	/*
	 * 跳转发起pk挑战的充值页面
	 */
	@Authorization
	@RequestMapping(value="pkChongZhi",method = RequestMethod.GET)
	public String tiaozhanchongzhi(Model model,HttpServletRequest request) {
		
		SunWechatUser  user  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(user==null || (user!=null && user.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		CrowUserInfo userInfo = crowUserInfoService.findByUserID(user.getId());
		model.addAttribute("balance", userInfo.getBalance().intValue());
		return "/work/wechat/v3/myAccount/pkChongZhi";
	}
	
	/*
	 * 跳转接受挑战页面
	 */
	@Authorization
	@RequestMapping(method = RequestMethod.GET ,value = "acceptChallenge")
	public String acceptChallenge(Model model,HttpServletRequest request,
			@RequestParam(value ="otherUserId",defaultValue="" ) String otherUserId  
			) {
//		otherUserId = "oGrGWs8aFoXsjdcoGbAf1F4sXxiQ";  //测试数据
		SunWechatUser otherUser = wechatService.findById(otherUserId);
		
		SunWechatUser user = (SunWechatUser) request.getSession().getAttribute("userInfo");
		String myUserId = user.getId();
		
		//开启个人挑战就不能接受其他挑战，接受其他挑战就不能开启个人挑战
		
		/*List<CrowPkOne> pkOneList = crowPkOneService.findByuserid(myUserId);
		List<CrowPkMe> pkMeList = crowPkMeService.findByUserId(myUserId);*/
		
		
		
		//获取对方账户的余额
		CrowUserInfo otherInfo = crowUserInfoService.findByUserID(otherUserId);
		BigDecimal otherBalance = otherInfo.getBalance();
		//获取我的账户余额
		CrowUserInfo myInfo = crowUserInfoService.findByUserID(myUserId);
		BigDecimal myBalance = myInfo.getBalance();
		
		model.addAttribute("otherBalance", otherBalance.intValue());
		model.addAttribute("myBalance", myBalance.intValue());
		model.addAttribute("myLevel", myInfo.getLevel());
		model.addAttribute("otherUser",otherUser);
		/*model.addAttribute("pkOneListSize",pkOneList.size());
		model.addAttribute("pkMeListSize",pkMeList.size());*/
		return "/work/wechat/v3/acceptChallenge/index";
	}
	
	/**
	 * 检查:
	 * 1.应战方和发起挑战方：是否是同一人；
	 * 2.应战方是否第二次接受发起方的挑战
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value="checkIsFirstChallenge")
	public Result<CrowPkOne>  check(Model model,HttpServletRequest request,
			@RequestParam(value ="userId",defaultValue="" ) String userId, //应战人id 
			@RequestParam(value ="otherUserId",defaultValue="" ) String otherUserId   //发起挑战人id
			) {
		
		if(!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(otherUserId)){
			if(userId.equals(otherUserId)){
				 return new Result<CrowPkOne>("不能挑战自己哦~",null);
			}
		}else{
			 return new Result<CrowPkOne>("错误：双方的userid参数不全",null);
		}
		
		List<CrowPkOne> list = crowPkOneService.isFirstAcceptChallenge(userId, otherUserId);
		if(list.size() >= 1){
			return new Result<CrowPkOne>("您和对方正在进行挑战，不能够重复应战！",null);
		}
		Result<CrowPkOne> result = new Result<CrowPkOne>();
		result.setState(1);
		return result;
	}
	
	
}