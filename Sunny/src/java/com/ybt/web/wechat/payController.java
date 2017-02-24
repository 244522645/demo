package com.ybt.web.wechat;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;
import com.pingplusplus.util.WxpubOAuth;
import com.ybt.common.bean.Result;
import com.ybt.common.bean.ResultTBean;
import com.ybt.common.constant.MyPingPP;
import com.ybt.common.constant.Wechat;
import com.ybt.common.constant.chxtConstant;
import com.ybt.common.util.DateTimeHelper;
import com.ybt.common.util.PropertiesUtil;
import com.ybt.common.util.StringUtil;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZhAccount;
import com.ybt.service.work.IPaymentService;
import com.ybt.service.work.OrderService;
import com.ybt.service.work.SunBlessService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.TradeAccountService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/wechat/pay/")
public class payController {
	@Autowired
	private SunCardService sunCardService;
	@Autowired
	@Qualifier("sunBlessService")
	private SunBlessService blessService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private TradeAccountService tradeAccountSer;
	@Autowired
	private IPaymentService payService;
	
	private String baseView(){
		return "/wechat/work/order/";
	}
	
	//ping++ 
	@RequestMapping(method = RequestMethod.POST,value = "order")
	@ResponseBody
	public String pay(Model model,HttpServletRequest request) {
		String acceptjson = "";
	    try{
			BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			acceptjson = sb.toString();
	    	if (acceptjson != "") {
	    		JSONObject jo = JSONObject.fromObject(acceptjson);
	    		System.out.println(jo);
	    		Pingpp.apiKey = MyPingPP.Ping_ApiKey_Live;
	    		Map<String, Object> chargeMap = new HashMap<String, Object>();
	            chargeMap.put("amount", jo.get("amount"));
	            chargeMap.put("currency", "cny");
	            chargeMap.put("subject", jo.get("subject"));
	            chargeMap.put("body", jo.get("body"));
	            //订单号：订单号+当前时间是第几秒+点击次数
	            Object order_no = jo.get("order_no");
	            if(order_no!=null&&!"".equals(order_no)){
	            	Long s = DateTimeHelper.getTimeSortSecond(new Date());
	            	Object metering = jo.get("metering");
            	    if(metering!=null&&!"".equals(metering))
            	    	chargeMap.put("order_no", order_no.toString()+(s+(Integer)metering));
            	    else
            	    	chargeMap.put("order_no", order_no.toString()+s);
	            }
	            chargeMap.put("channel", jo.get("channel"));
	            chargeMap.put("client_ip", "123.56.118.53");
	            Map<String, String> app = new HashMap<String, String>();
	            app.put("id",MyPingPP.Ping_AppId);
	            chargeMap.put("app", app);
	            Map<String, String> ex = new HashMap<String, String>();
	            System.out.println("===jo.get('open_id')="+jo.get("open_id"));
	            if(jo.get("open_id")!=null&&!jo.get("open_id").equals("")){
	            	ex.put("open_id",jo.get("open_id").toString());
	            }else{
	            	//发起 alipay_wap 的支付请求需要额外两个参数
//		            String alipayWapCancel = "http://192.168.8.12:8080/ybt3/pay/alipayWapCancel";
//		            String alipayWapSuccess = "http://192.168.8.12:8080/ybt3/pay/alipayWapSuccess";
	            	String alipayWapCancel =PropertiesUtil.getProperty("domainName")+"/wechat/pay/alipayWapCancel";
			        String alipayWapSuccess =PropertiesUtil.getProperty("domainName")+"/wechat/pay/alipayWapSuccess";
		            ex.put("success_url",alipayWapSuccess);//alipay_wap 
		            ex.put("cancel_url",alipayWapCancel);//alipay_wap 
	            }
	            chargeMap.put("extra", ex);
	            System.out.println("chargeMap="+chargeMap);
	            String charge = Charge.create(chargeMap).toString();
	            System.out.println("charge="+charge);
	            return charge;
	    	}
	    } catch (Exception e) {
		 	e.printStackTrace();
	    }
	    return null;
	}
	//ping++  微信统一下单
	@RequestMapping(method = RequestMethod.POST,value = "payCharge")
	@ResponseBody
	public String wxpayCharge(Model model,HttpServletRequest request,
		@RequestParam(value ="subject") String subject,
		@RequestParam(value ="body") String body,
		@RequestParam(value ="amount") Double amount,
		@RequestParam(value ="orderNo") String orderNo
		) {
			
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null ){
			return "";
	   	}
		//获取 Ping++ change
		int metering = 0;
		Map<String,Object> jo = new HashMap<String,Object>();
		
		jo.put("amount", amount);
		jo.put("subject", subject);
		jo.put("body", body);
		jo.put("order_no", orderNo);
		jo.put("metering", metering);
		jo.put("channel", "wx_pub");
		jo.put("open_id", w.getId());
		String change="";
		try {
			change = payService.pingxxGetCharge(jo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("------------------"+e);
			e.printStackTrace();
		}
		return change;
	}
		
	//跳转到微信订单支付界面
	@RequestMapping(method = RequestMethod.GET,value = "payWeixinOrder")
	public String payWeixinOrder(Model model,HttpServletRequest request,
			String orderId) {
		String timestamp = request.getParameter("timestamp");
		if(timestamp!=null&&!"".equals(timestamp)){
			Date date = new Date(Long.valueOf(timestamp));
			//120秒超时
			if(DateTimeHelper.getDateDiff(date, new Date())>2){
				model.addAttribute("result", "支付链接已失效！请刷新支付页面。");
				return "/work/account/pay_result";
			}
		}
		String openId = "";
	/*	try {
			String code = request.getParameter("code");
			openId = WxpubOAuth.getOpenId(Wechat.APPID,Wechat.APPSECRET, code);
			model.addAttribute("openId", openId);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
//		model.addAttribute("openId", "onh1swcLh-Gv08AxfamcKkwY9BU4");
		SunDdOrder order =orderService.findOne(orderId);
		//短商品名称
		String name = order.getTitle();
		model.addAttribute("name", name);
		model.addAttribute("number",1);
		model.addAttribute("order", order);
		//zhz 20160317
		String orderUrl = PropertiesUtil.getProperty("domainName")+request.getContextPath()+"/wechat/pay/order";
//		String orderUrl = "http://192.168.8.17:8080/ybt3/pay/order";
		model.addAttribute("orderUrl", orderUrl);
//		model.addAttribute("channel", "'wx_pub','alipay_wap'");
		if(openId!=null && !"".equals(openId)){
			model.addAttribute("channel", "'wx_pub'");
		}else{
			model.addAttribute("channel", "'alipay_wap'");
		}
/*		
		YbtBusiness sellBusiness = businessService.findOne(order.getSellerId());//卖家信息
		model.addAttribute("sellBusiness", sellBusiness);
		*/
		return "work/wechat/order/payInfo";
	}
	
	//支付成功后，修改订单状态,保存支付信息
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "paySuccess")
	public ResultTBean<SunDdOrder> paySuccess(Model model,HttpServletRequest request,
			String id,String payType,String pingppId) {
		System.out.println("**************payCtrl-101"+id);
		String accountId = (String)request.getSession().getAttribute(chxtConstant.ACCOUNT_ID);
		String bid = (String)request.getSession().getAttribute(chxtConstant.BUSINESS_ID);
		ResultTBean<SunDdOrder> resultBean = new ResultTBean<>();
		resultBean.setS(0);
		if(StringUtil.notNull(id)){
			SunDdOrder order = orderService.payOrderOnLine(id, accountId, bid, payType, pingppId);
			resultBean.setB(order);
			//临时修改 贺卡流程V2
			if(order!=null&&order.getStatus()=="20"){
				SunBless b=blessService.findByOrder(order.getId());
				b.setStatus(1);
				blessService.save(b);
			}
			if(order==null){
				resultBean.setS(1);
			}
		}
		return resultBean;
	}
	

	
	//alipay_wap 适用于手机网页支付，需开通支付宝手机网页支付服务，发起 alipay_wap 的支付请求需要额外两个参数：
	//success_url 是指的支付成功后的 Client 同步通知处理地址。
	//cancel_url是指的支付取消后的 Client 同步通知处理地址。
	@RequestMapping(method = RequestMethod.GET,value = "alipayWapSuccess")
	public String alipayWapSuccess(Model model,HttpServletRequest request) {
		String result = request.getParameter("result");
		if(result!=null&&!result.equals(""))
			model.addAttribute("result", result);
		else
			model.addAttribute("result", "支付成功！");
		return "/work/account/pay_result";
	}
	@RequestMapping(method = RequestMethod.GET,value = "alipayWapCancel")
	public String alipayWapCancel(Model model,HttpServletRequest request) {
		model.addAttribute("result", "支付已取消！");
		return "/work/account/pay_result";
	}
	@RequestMapping(method = RequestMethod.GET,value = "pay.htm")
	public String aliPayHtm(Model model,HttpServletRequest request) {
		return baseView()+"pay";
	}
	
	//跳转到微信提现页面
	@RequestMapping(method = RequestMethod.GET,value = "transferWeixin")
	public String transferWeixin(Model model,HttpServletRequest request) {
			String bid = request.getParameter("bid");
			try {
				String code = request.getParameter("code");
				String openId = WxpubOAuth.getOpenId(Wechat.APPID,Wechat.APPSECRET, code);
				model.addAttribute("openId", openId);
				System.out.println("===========WxpubOAuth.getOpenId(Wechat.APPID,Wechat.APPSECRET, code) =openid="+openId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// TODO
//			model.addAttribute("openId", "onh1swcLh-Gv08AxfamcKkwY9BU4");
			//验证openId是不是可提现微信openId TODO
			
			
			String timestamp = request.getParameter("timestamp");
			if(timestamp!=null&&!"".equals(timestamp)){
				Date date = new Date(Long.valueOf(timestamp));
				//120秒失效
				if(DateTimeHelper.getDateDiff(date, new Date())>20){
					model.addAttribute("result", "二维码已失效！请重新申请二维码。");
					return "/work/account/pay_result";
				}
			}
	/*		//可提现金额
			SunTradeBAccount abc = tradeBAccountSer.getBAccountByBid(bid);*/
/*			model.addAttribute("money", abc.getMoney().intValue());*/
			model.addAttribute("bid", bid);
			
		return "/work/account/transferInfo";
	}
	
	
	/**
	 *@description 提现--进入ping++提现，提现申请提交成功返回提现流水信息
	 *				2，webhooks接收到提现成功的推送后，短信通知
	 *@time    创建时间:2016年3月16日上午11:07:18
	 *@param money 提现金额
	 *@author   zhz
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "transferApply")
	public ResultTBean<Transfer> transferApply(Model model,HttpServletRequest request,int money,String openId) {
		//1，ping++提现,money>100，并且小于商户账户可提现余额&小于芸备胎总账户
		//2，transfer 提交成功
		//3，a,插入商户账户流水记录，b,芸备胎官方出账记录,c,减商户可提现金额，d,减 芸备胎总额
		//4，返回提示信息：提现申请已提交成功
		//5,webhooks接受提现到账推送后，成功：a,短信通知到账成功，b,修改商户流水记录-成功，c，修改芸备胎官方出账记录-成功 
								    //失败:a,短信通知到账失败，b,修改商户流水记录-失败，c，修改芸备胎官方出账记录-失败 d,增加商户可提现金额，e， 增加芸备胎总额
				
		ResultTBean<Transfer> bean = new ResultTBean<Transfer>();
		String bid = request.getParameter("bid");
		
/*		//1，ping++提现,money>100，并且小于商户账户可提现余额
		SunTradeBAccount abc = tradeBAccountSer.getBAccountByBid(bid);
		*/
		/*if(money<1||money>abc.getMoney()){
			bean.setS(0);
			bean.setM("您输入的金额有误！（1≤提现金额≤"+abc.getMoney()+"）");
			return bean;
		}*/
		//&小于芸备胎总账户
		SunZhAccount ac = tradeAccountSer.findOne(MyPingPP.YBT_ACCOUNT_ID);
		if(money>ac.getMoney()){
			bean.setS(0);
			bean.setM("账户异常，请联系芸备胎！");
			return bean;
		}
		
		//2，transfer 提交成功
		Pingpp.apiKey = MyPingPP.Ping_ApiKey_Live;
		Map<String, Object> transferMap = new HashMap<String, Object>();
	    transferMap.put("amount", money*100);
	    transferMap.put("currency", "cny");
	    transferMap.put("type",  "b2c");
	    String order_no = System.currentTimeMillis()+"";
	    System.out.println("===System.currentTimeMillis()+"+order_no);
	    transferMap.put("order_no",  order_no);
	    transferMap.put("channel",  "wx_pub");
	    transferMap.put("recipient", openId);
	    transferMap.put("description", "芸备胎串货平台-提现");
	    Map<String, String> app = new HashMap<String, String>();
	    app.put("id", MyPingPP.Ping_AppId);
	    transferMap.put("app", app);
	    System.out.println("====="+transferMap);
		try {
			Transfer transfer = Transfer.create(transferMap);
			System.out.println("========transferApply====transfer="+transfer);
			//付款状态。目前支持 3 种状态（pending: 处理中; paid: 付款成功; failed: 付款失败）。
			if(transfer.getStatus()!=null&&"pending".equals(transfer.getStatus())){
				bean.setS(1);
				//3，a,插入商户账户流水记录，b,芸备胎官方出账记录,c,减商户可提现金额，d,减 芸备胎总额
				tradeAccountSer.saveTransfer(bid,transfer,false);
				
				//4，返回提示信息：提现申请已提交成功
				//提示手机号 
				/*YbtBusiness bus = businessService.findOne(bid);*/
				/*String msg = "";
				if(bus.getPhone()!=null && !bus.getPhone().isEmpty()){
					msg = "提现申请成功！到账成功后会以短信通知："+bus.getPhone();
				}else{
					msg = "提现申请成功！如需短信通知，请到【个人资料】设置手机号！";
				}*/
			/*	bean.setM(msg);*/
				bean.setB(transfer);
				
				//5,webhooks接受提现到账推送后，成功：a,短信通知到账成功，b,修改商户流水记录-成功，c，修改芸备胎官方出账记录-成功 
										    //失败:a,短信通知到账失败，b,修改商户流水记录-失败，c，修改芸备胎官方出账记录-失败 d,增加商户可提现金额，e， 增加芸备胎总额
			}else{
				bean.setS(0);
				bean.setM("提现申请失败，请联系芸备胎！");
			}
			
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | APIException | ChannelException e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	//支付成功后，修改订单状态,保存支付信息
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "tixianWXResult")
	public ResultTBean<SunDdOrder> tixianWXResult(Model model,HttpServletRequest request,
			String bid,String phone,String code) {
		ResultTBean<SunDdOrder> resultBean = new ResultTBean<>();
		resultBean.setS(0);
//		if(!helpUtil.isEmpty(id)){
//			SunDdOrder order = orderService.payOrderOnLine(id, accountId, bid, payType, pingppId);
//			resultBean.setB(order);
//			if(order==null){
//				resultBean.setS(1);
//			}
//		}
		return resultBean;
	}
		
	
	/**  
	 * 阳光卡支付 通知
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月19日 上午11:56:29 
	 */
	@RequestMapping(method = RequestMethod.POST,value = "paySunCardNotice")
	@ResponseBody
	public Result<SunDdOrder> paySunCardNotice(Model model,HttpServletRequest request,
			@RequestParam(value="orderId")String orderId,
			@RequestParam(value="cardNumber")String cardNumber) {
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			return new Result<SunDdOrder>("未授权登录",null);
	   	}
		try{
			SunDdOrder order = orderService.findOne(orderId);
			SunCard card =sunCardService.getOneCard(cardNumber);
			if(order==null||card==null){
				
				return new Result<SunDdOrder>("异常",null);
			}
			if(card.getStatus()!=1||card.getDeleted()!=0){
				return new Result<SunDdOrder>("无效的卡",null);
			}
			if(new Date().after(card.getFailureTime())){
				return new Result<SunDdOrder>("卡已过期",null);
			}
			if(card.getUserId()==null){
				return new Result<SunDdOrder>("卡未绑定",null);
			}
			if(!card.getUserId().equals(w.getId())){
				return new Result<SunDdOrder>("未授权登录",null);
			}
			
			order = orderService.paySunCard(order, card);
			
			//修改卡已使用
			if(order!=null){
				card.setStatus(2);
				card.setUpdateTime(new Date());
				sunCardService.save(card);
				request.getSession().setAttribute("cardToken", "");
				
				//临时修改 贺卡流程V2
				if(order!=null&&order.getStatus()=="20"){
					SunBless b=blessService.findByOrder(order.getId());
					b.setStatus(1);
					blessService.save(b);
					
				}
				 return new Result<SunDdOrder>("",order);
			}
		}catch(Exception e){
			e.printStackTrace();
			return new Result<SunDdOrder>("异常",null);
		}
		return new Result<SunDdOrder>("异常",null);
	}
}
