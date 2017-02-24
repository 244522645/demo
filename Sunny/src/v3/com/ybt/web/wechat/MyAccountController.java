package com.ybt.web.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import wechat.util.WXUtil;

import com.pingplusplus.model.Transfer;
import com.ybt.common.bean.Result;
import com.ybt.common.uitl.SendWeixinMessage;
import com.ybt.common.util.CommonUtil;
import com.ybt.common.util.DateUtil;
import com.ybt.model.work.CrowBill;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.aop.Authorization;
import com.ybt.service.work.CrowAccountService;
import com.ybt.service.work.CrowBillService;
import com.ybt.service.work.CrowUserInfoService;
import com.ybt.service.work.IPaymentService;
import com.ybt.service.work.OrderService;


@Controller
@RequestMapping(value = "/wechat/v3/myAccount")
public class MyAccountController {
	
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
	@SuppressWarnings("rawtypes")
	@Autowired
	public HashMap constant;
	private String baseView(String v) {
		return "/work/wechat/v3/myAccount/"+v;
	}
	
	
	/*
	 * 跳转发起pk挑战的充值页面（因微信限制，将此跳转方法放到了CrowPayController中）
	 */
	@Authorization
	@RequestMapping(value="pkChongZhi_",method = RequestMethod.GET)
	public String tiaozhanchongzhi(Model model,HttpServletRequest request) {
		
		SunWechatUser  user  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(user==null || (user!=null && user.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		CrowUserInfo userInfo = crowUserInfoService.findByUserID(user.getId());
		model.addAttribute("balance", userInfo.getBalance().intValue());
		return baseView("pkChongZhi");
	}
	
	
	/*
	 * 跳转我的账户页面
	 */
	@Authorization
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {
		
		SunWechatUser  user  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		CrowUserInfo userInfo = crowUserInfoService.findByUserID(user.getId());
		model.addAttribute("balance", userInfo.getBalance().intValue());
		return baseView("index");
	}
	
	/*
	 * 我的收支记录
	 */
	@RequestMapping(value="detail",method = RequestMethod.POST)
	@ResponseBody
	public List<CrowBill> detail(Model model,HttpServletRequest request) {
		SunWechatUser  user  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		System.out.println("账户详情 的 userid ==》"+user.getId());
		String nowDate = DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
		System.out.println("账户详情 的 nowDate ==》"+nowDate);
		List<CrowBill> result = crowBillService.findByUserIdAndDate(user.getId(), nowDate);
		System.out.println("账户详情 的 List<CrowBill>的size ==》"+result.size());
		return result;
	}
	
	/*
	 * 跳转“提现”页面
	 */

	@RequestMapping(value="withdrawCashPage",method = RequestMethod.GET)
	public String withdrawCashPage(Model model,HttpServletRequest request) {
		
		SunWechatUser  user  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(user==null || (user!=null && user.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		CrowUserInfo userInfo = crowUserInfoService.findByUserID(user.getId());
		model.addAttribute("balance", userInfo.getBalance().intValue());
		return baseView("withdrawCash");
	}
	/*
	 * “提现”
	 */

	@RequestMapping(value="withdrawCash",method = RequestMethod.POST)
	@ResponseBody
	public String withdrawCash(Model model,HttpServletRequest request,
			@RequestParam(value = "withdrawCash") String withdrawCash) throws Exception {
		try{
			SunWechatUser  user  = (SunWechatUser) request.getSession().getAttribute("userInfo");
			CrowUserInfo userInfo = crowUserInfoService.findByUserID(user.getId());
			String userBalance = CommonUtil.objToString(userInfo.getBalance());
			String nowBalance = CommonUtil.subtract(userBalance, withdrawCash);  //用户当前余额 = 用户余额 - 提现金额
			int compare = CommonUtil.compare(nowBalance, "0");
			System.out.println("===========userBalance:"+userBalance+"===================nowBalance:="+nowBalance+"================");
			if(compare == -1){
				return "余额不足";
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userOpenId", user.getId());
			paramMap.put("withdrawCash", withdrawCash);  //提现金额
			paramMap.put("description","闻鸡起伍提现");
			paramMap.put("nowBalance",nowBalance);  //用户当前余额
			Transfer transfer = orderService.EntPayToPerPingPP(paramMap);
			System.out.println("==============================提现申请执行完毕========================");
			System.out.println("===申请提现的transfer：======》"+transfer);
			if( null != transfer){
				return "success";
			}else{
				return "failed";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
		
	}
	
	/**
	 *   支付申请
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "applyPay")
	public Result<CrowBill> applyPay(Model model,HttpServletRequest request,
			@RequestParam(value ="title" ) String title,    //
			@RequestParam(value ="payChallengeMoney",defaultValue="" ) Double payChallengeMoney,    //应付金额  = 实付金额 + 抵扣金额
			@RequestParam(value ="actualPayment",defaultValue="" ) Double actualPayment,    //实付金额
			@RequestParam(value ="accountDeduc",defaultValue="" ) Double deduct,  //抵扣金额
			@RequestParam(value ="otherUserId",defaultValue="" ) String otherUserId,    //发起挑战人ID
			@RequestParam(value ="pay_type",defaultValue="") String pay_type){  //pkChongZhi:pk充值 ；yzZhiFu:应战支付 ;grZhiFu:个人挑战支付

		try {
			SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
			if(w==null || (w!=null && w.getSubscribe() != 1)){
				request.setAttribute("login", "no");
				return new Result<CrowBill>("未登陆",null);
		   	}
			
			if(payChallengeMoney == null || actualPayment == null || deduct == null || payChallengeMoney == 0){
				return new Result<CrowBill>("金额错误",null);
			}
			
			CrowUserInfo userInfo = crowUserInfoService.findByUserID(w.getId());
			int compare = CommonUtil.compare(userInfo.getBalance().toString(), deduct+"");
			if(actualPayment == 0 && compare == -1){
				return new Result<CrowBill>("余额不足",null);
			}
			Result<CrowBill> result = null;
			if("pkChongZhi".equals(pay_type)){   //pkChongZhi:pk充值  
				//pk充值时，用户余额增加
				result = crowAccountService.applyBill(1, actualPayment, deduct, title, w.getId() ,otherUserId);    //0 支出 1收入
			}else{
				//yzZhiFu:应战支付 ;grZhiFu:个人挑战支付，用户余额减少
				result = crowAccountService.applyBill(0, actualPayment, deduct, title, w.getId() ,otherUserId);    //0 支出 1收入
			}
			
			
			if(result.getState()==0){  //失败
				 return new Result<CrowBill>(result.getMessage(),null);
			}
			if(actualPayment==0){
				//crowUserInfoService.updateUserBalance(w.getId(), "subtract", ""+deduct);
				return new Result<CrowBill>("",result.getT());
			}
			//获取 Ping++ change
			String subject =title;
			int metering = 0;
			String body=title;
		
			Map<String,Object> jo = new HashMap<String,Object>();
			jo.put("deduct", deduct);
			jo.put("amount", actualPayment);
			jo.put("subject", subject);
			jo.put("body", body);
			jo.put("order_no", result.getT().getOrderId());
			jo.put("metering", metering);
			jo.put("channel", "wx_pub");
			jo.put("open_id", w.getId());
			jo.put("service_type", "wjqw");//添加业务
			jo.put("pay_type", pay_type);
			
			String change = payService.pingxxGetCharge(jo);
			
			System.out.println("------------change:"+ change);
			Result<CrowBill> re = new Result<CrowBill>();
			re.setT(result.getT());
			re.setMessage(change);
			re.setState(1);
			
   			 return re;
		} catch (Exception e) {
			e.printStackTrace();
			 return new Result<CrowBill>("提交失败",null);
		}
	}
	
	/**
	 *   前台回调方法
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "payResult")
	public Result<CrowBill> payResult(Model model,HttpServletRequest request,
			@RequestParam(value ="orderNo" ) String orderNo,    //
			//@RequestParam(value ="deduct" ) String deduct,    //抵扣
			@RequestParam(value ="actualPay" ) String actualPay,    //实付
			@RequestParam(value ="weixinPayResult",defaultValue="" ) String weixinPayResult    
			){
		System.out.println(orderNo+"=================="+actualPay+"==========="+weixinPayResult);
		try {
			SunWechatUser user = (SunWechatUser) request.getSession().getAttribute("userInfo");
			CrowBill cb = crowBillService.findCrowBillByOrderId(orderNo);
			
			if("success".equals(weixinPayResult)){//微信扣款成功
				if(cb.getStatus() == 0){
					//增加用户余额
					crowUserInfoService.updateUserBalance(user.getId(), "add", ""+actualPay);
					cb.setUpdateTime(new Date());
					cb.setStatus(1);
					crowBillService.save(cb);
				}
				System.out.println("=========================增加用户余额更新bill之后============================");
				SendWeixinMessage.sendMessage(
						"发起挑战通知",
						"恭喜您已成功发起对战。",
						"发起对战", DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm"), "邀请朋友参加对战",
						WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/crow/index",  false), user.getId());
				System.out.println("=========================发送消息之后============================");
				Result<CrowBill> result = new Result<CrowBill>();
				result.setT(cb);
				result.setMessage("success");
				result.setState(1);
				
				System.out.println("===================return之前：==================result =="+result.toString());
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			 return new Result<CrowBill>("提交失败",null);
		}
		return null;
	}
	
	/**
	 *   个人挑战前台回调方法
	 */
	/**
	 *   前台回调方法
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "payMeResult")
	public Result<CrowBill> payMeResult(Model model,HttpServletRequest request,
			@RequestParam(value ="orderNo" ) String orderNo,    //
			//@RequestParam(value ="deduct" ) String deduct,    //抵扣
			@RequestParam(value ="actualPay" ) String actualPay,    //实付
			@RequestParam(value ="weixinPayResult",defaultValue="" ) String weixinPayResult    
			){
		System.out.println(orderNo+"=================="+actualPay+"==========="+weixinPayResult);
		try {
			SunWechatUser user = (SunWechatUser) request.getSession().getAttribute("userInfo");
			CrowBill cb = crowBillService.findCrowBillByOrderId(orderNo);
			
			if("success".equals(weixinPayResult)){//微信扣款成功
				if(cb.getStatus() == 0){
					//减用户余额
					crowUserInfoService.updateUserBalance(user.getId(), "subtract", ""+actualPay);
					cb.setUpdateTime(new Date());
					cb.setStatus(1);
					crowBillService.save(cb);
				}
				Result<CrowBill> result = new Result<CrowBill>();
				result.setT(cb);
				result.setMessage("success");
				result.setState(1);
				
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			 return new Result<CrowBill>("提交失败",null);
		}
		return null;
	}
}