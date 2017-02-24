package com.ybt.web.wechat;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.ybt.common.uitl.SendWeixinMessage;
import com.ybt.common.util.CommonUtil;
import com.ybt.common.util.DateUtil;
import com.ybt.model.work.CrowBill;
import com.ybt.model.work.CrowPkMe;
import com.ybt.model.work.CrowPkOne;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.work.CrowAccountService;
import com.ybt.service.work.CrowBillService;
import com.ybt.service.work.CrowPkMeService;
import com.ybt.service.work.CrowPkOneService;
import com.ybt.service.work.CrowUserInfoService;
import com.ybt.service.work.IPaymentService;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.OrderService;

import wechat.util.WXUtil;

/**
 *  个人进度
 * */
@Controller
@RequestMapping(value = "/wechat/v3/acceptChallenge")
public class AcceptChallengeController {

	
	@Autowired
	private CrowBillService crowBillService;
	@Autowired
	private CrowUserInfoService crowUserInfoService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CrowAccountService crowAccountService;
	@Autowired
	private IPaymentService payService;
	@Autowired
	private IWechatService wechatService;
	@Autowired
	private CrowPkOneService crowPkOneService;
	@SuppressWarnings("rawtypes")
	@Autowired
	public HashMap constant;
	
	@Autowired
	private  CrowPkMeService crowPkMeService;
	
	private String baseView(String v) {
		return "/work/wechat/v3/acceptChallenge/"+v;
	}
	/*
	 * 跳转接受挑战页面（因微信限制，将此跳转方法放到了CrowPayController中）
	 */
	@RequestMapping(method = RequestMethod.GET,value="不用了")
	public String acceptChallenge(Model model,HttpServletRequest request,
			@RequestParam(value ="otherUserId",defaultValue="" ) String otherUserId  
			) {
		//otherUserId = "oGrGWs8aFoXsjdcoGbAf1F4sXxiQ";  //测试数据
		SunWechatUser otherUser = wechatService.findById(otherUserId);
		
		SunWechatUser user = (SunWechatUser) request.getSession().getAttribute("userInfo");
		String myUserId = user.getId();
		
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
		return baseView("index");
	}
	
	
	/**
	 *   前台回调方法
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "payResult")
	public Result<CrowBill> payResult(Model model,HttpServletRequest request,
			@RequestParam(value ="payType" ) String payType,    //付款方式（weixin：微信支付 和  yue：余额抵扣）
			//@RequestParam(value ="deduct" ) String deduct,    //抵扣（暂时没用）
			@RequestParam(value ="actualPay" ) String actualPay,    // 实付
			@RequestParam(value ="orderNo" ) String orderNo,    //
			@RequestParam(value ="weixinPayResult",defaultValue="" ) String weixinPayResult,    
			@RequestParam(value ="otherUserId",defaultValue="" ) String otherUserId    //发起挑战人ID
			){
		try {
			SunWechatUser user = (SunWechatUser) request.getSession().getAttribute("userInfo");  //我的信息，也就是接受挑战方
			SunWechatUser otherUser = wechatService.findById(otherUserId);  //对方
			
			CrowBill cb = crowBillService.findCrowBillByOrderId(orderNo);
			CrowUserInfo userInfo1 = crowUserInfoService.findByUserID(user.getId());
			CrowUserInfo userInfo2 = crowUserInfoService.findByUserID(otherUserId);
			System.out.println("扣款方式payType：::::"+payType);
			if("yue".equals(payType) ){//余额扣款（需要扣除双方的余额）
				
				if((CommonUtil.compare(userInfo1.getBalance().toString(), "5")>-1)&&(CommonUtil.compare(userInfo2.getBalance().toString(), "5")>-1)){
					Result<BigDecimal> r2=crowAccountService.minusBalance("挑战金抵扣", 5.00, otherUserId);
				}else{
					 return new Result<CrowBill>("余额不足",null);
				}
				if(cb.getStatus() == 0){
					//扣除用户余额
					cb.setUpdateTime(new Date());
					cb.setStatus(1);
					crowBillService.save(cb);
					crowUserInfoService.updateUserBalance(user.getId(), "subtract", cb.getDeduct().toString());
				}
			}else if("weixin".equals(payType) ){  //应战方采用微信支付，需要扣除发起挑战方的余额
				if((CommonUtil.compare(userInfo2.getBalance().toString(), "5")>-1)){
					Result<BigDecimal> r2=crowAccountService.minusBalance("挑战金抵扣", 5.00, otherUserId);
				}else{
					 return new Result<CrowBill>("发起挑战方余额不足",null);
				}
			}
			
			List<CrowPkOne> list = crowPkOneService.isFirstAcceptChallenge(user.getId(), otherUserId);
			if(list.size() < 1){
				//记录应战方和擂主方再CrowPkOne表的记录,并发送通知消息
				crowPkOneService.creatBothrecordsAndSendMessage(user, otherUser, orderNo);
			}
			
			System.out.println("=========================accept发送消息之后============================");
			Result<CrowBill> result = new Result<CrowBill>();
			result.setT(cb);
			result.setMessage("success");
			result.setState(1);
			System.out.println("=========accept=========return之前：==============result =="+result.toString());
			return result;
				
		} catch (Exception e) {
			e.printStackTrace();
			 return new Result<CrowBill>("提交失败",null);
		}
	}
	
}