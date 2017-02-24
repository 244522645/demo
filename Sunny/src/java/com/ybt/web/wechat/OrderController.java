package com.ybt.web.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingplusplus.util.WxpubOAuth;
import com.ybt.common.bean.Result;
import com.ybt.common.bean.ResultTBean;
import com.ybt.common.constant.Wechat;
import com.ybt.common.util.EmojiFilter;
import com.ybt.common.util.PropertiesUtil;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.common.util.ShortenUrl;
import com.ybt.common.util.StringUtil;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunDdStatus;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyImages;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.work.IMemberTjService;
import com.ybt.service.work.IPaymentService;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.ImagesService;
import com.ybt.service.work.OrderService;
import com.ybt.service.work.PhotoService;
import com.ybt.service.work.SunBlessService;
import com.ybt.service.work.SunCardService;

import wechat.support.TicketManager;
import wechat.util.JsUtil;
import wechat.util.WXUtil;
/**
 *
 * @project 云备胎微信版
 * @package com.ybt.web.work.business
 * @file OrderController.java 创建时间:2016年4月14日下午2:41:03
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2016 云讯科技有限公司
 * @company 云讯科技有限公司
 * @module 模块: 模块名称
 * @author   高艳花
 * @reviewer 金双江
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@Controller
@RequestMapping(value = "/wechat/order")
public class OrderController {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private OrderService orderService;
	@Autowired
	@Qualifier("sunBlessService")
	private SunBlessService blessService;
	@Autowired
	private SunCardService cardService;
	@Autowired
	private IPaymentService payService;
	@Autowired
	public ImagesService imagesService;
	@Autowired
	@Qualifier("photoService")
	private PhotoService photoService;
	@Autowired
	private IMemberTjService memberTjService;
	
	@Resource
	public Map<String,String> constant;
	
	@Autowired
	public IWechatService wechatService;
	private String baseView(){
		return "/work/wechat/order/";
	}
	
	

	/**
	 *@name   保存新订单信息
	 *@description 相关说明
	 *@time    创建时间:2016年1月28日下午1:27:51
	 *@param model
	 *@param request
	 *@param order
	 *@return
	 *@author   高艳花
	 * @throws Exception 
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "saveOrder")
	public ResultTBean<SunDdOrder> saveOrder(Model model,HttpServletRequest request,
			SunDdOrder order){
		ResultTBean<SunDdOrder> resultBean = new ResultTBean<>();
		try {
			String openId =(String)request.getSession().getAttribute(Wechat.WECATOPENID);
			SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
			if(w==null || (w!=null && w.getSubscribe() != 1)){
				model.addAttribute("content", "");
				resultBean.setS(0);
				resultBean.setM("保存失败,请先关注给点儿阳光公众号！");
				return resultBean;
		   	}
			String sunPayType = request.getParameter("sunPayType");
			String photoId = request.getParameter("photoId");
			SunZyPhoto photo = photoService.findOne(photoId);
			//String cardImgId = request.getParameter("cardImgId");//新图片ID
			String message = request.getParameter("message");//祝福语
			String sendeeName = request.getParameter("sendeeName");//收卡人名称
			String buyerName = request.getParameter("buyerName");//送卡人名称
			
			//过滤客户端表情
			 message = EmojiFilter.emojiEmpty(message,"");
			 sendeeName = EmojiFilter.emojiEmpty(sendeeName,"");
			 buyerName = EmojiFilter.emojiEmpty(buyerName,"");
			
			
			/*if(EmojiFilter.containsEmoji(message)||EmojiFilter.containsEmoji(sendeeName)||EmojiFilter.containsEmoji(buyerName)){
				resultBean.setS(0);
				resultBean.setM("抱歉！不支持表情");
				return resultBean;
			}*/
			
			//生成贺卡
			Result<SunZyImages> r = imagesService.createBlessImage(photo, buyerName+"祝："+sendeeName, message);
			if(r.state != 1){
					resultBean.setS(0);
					System.out.println("----------"+r.getMessage());
					resultBean.setM("生成贺卡失败");
					
				return resultBean;
			}
			String sunprice = constant.get("sunPrice");
			Double price=StringUtil.getDouble(sunprice);
			order.setCardImage(r.getT());
			order.setTitle(buyerName+" 祝：" +sendeeName);
			
			if(price>0){
				if(sunPayType.equals("SUNCARD")){
					order.setPayment(2);//在线卡支付
				}else{
					order.setPayment(1);//在线支付
				}
				
				order.setDelivery(0);//在线浏览
			}else{
				order.setPayment(0);//无需支付
				order.setDelivery(0);//在线浏览
			}
			order.setBuyerId(openId);//买家ID
			
			order.setBuyerName(buyerName);
			order.setTotalPrice(price);//应付总价
			order.setPayTotalPrice(price);//实付总价
			order.setTotalGoodPrice(price);//商品总价
			order.setMessage(message);
			order.setSendeeName(sendeeName);
			order.setSendPrice(0.00);//物流费用
			order.setPhoto(photo);//保存原照片
			SunDdOrder neworder = orderService.saveOrder(order,openId);
			if(neworder==null){
				resultBean.setS(0);
				resultBean.setM("保存失败");
			}else{
				resultBean.setS(1);
				// 保存 贺卡信息
				blessService.saveBless(w, neworder.getBuyerName(), neworder.getSendeeName(), neworder.getMessage(), neworder.getCardImage(), neworder.getPhoto(), neworder);
				
			}
			resultBean.setB(order);
			//方便起见  开发模式  使用本地路径
			if(!constant.get("runMode").equals("production")){
				return resultBean;
			}
			
			//获取 Ping++ change
			String subject =order.getPhoto().getTitle()+'-'+order.getPhoto().getSubject()+"-贺卡";
   			int metering = 0;
   			String body=order.getPhoto().getTitle()+'-'+order.getPhoto().getSubject()+"-贺卡";
			
   			if(sunPayType!=null&&order.getPayment()==2){
   				resultBean.setM(order.getId());
   			}
   			if(order.getPayment()==1){
   				Map<String,Object> jo = new HashMap<String,Object>();
   				jo.put("amount", order.getPayTotalPrice());
   				jo.put("subject", subject);
   				jo.put("body", body);
   				jo.put("order_no", order.getOrderNo());
   				jo.put("metering", metering);
   				jo.put("channel", "wx_pub");
   				jo.put("open_id", w.getId());
   				String change = payService.pingxxGetCharge(jo);
   				resultBean.setM(change);
   			}
   			
			
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setS(0);
			resultBean.setM("保存失败");
			return resultBean;
		}
		return resultBean;
	}
	
	//跳转到下订单成功界面
	@RequestMapping(method = RequestMethod.GET,value = "orderSucess")
	public String orderSucess(Model model,HttpServletRequest request,String orderId ) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		
		SunDdOrder order =orderService.findOne(orderId);
		SunZyImages image=  order.getCardImage();//新贺卡图片
		//分享链接
		String url = PropertiesUtil.getProperty("domainName")+ "/wechat/order/sendeeinfo?orderId="+orderId;
		//分享链接   微信允许获取用户信息的链接  非静默 （snsapi_userinfo）
		String shareUrl = WXUtil.getOAuthUrl(url, false);
	//	String WXConfig = WXConfig(request,null,PropertiesUtil.getProperty("domainName")+ "/wechat/order/orderSucess?orderId="+orderId);
		//model.addAttribute("WXConfig",WXConfig);//微信JSSDK
		model.addAttribute("shareUrl",  PropertiesUtil.getProperty("domainName")+"/wechat/index?userCode=");//朋友分享链接
		model.addAttribute("shareAppMessageUrl", shareUrl);//分享链接
		model.addAttribute("order", order);
		String meg= order.getMessage() .replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "");

		//微信支付链接
		String WXPayUrl = PropertiesUtil.getProperty("domainName")+request.getContextPath()+"/wechat/pay/payWeixinOrder?orderId="+orderId+"&timestamp="+System.currentTimeMillis();
		try {
			WXPayUrl = WxpubOAuth.createOauthUrlForCode(Wechat.APPID, WXPayUrl, false);
		} catch (Exception e) {
			WXPayUrl="";
			e.printStackTrace();
		}finally{
			model.addAttribute("WXPayUrl",ShortenUrl.getShortenUrl(WXPayUrl, null));
			System.out.println("buyorderinfo====WXPayUrl ==="+WXPayUrl);
		}
		
		model.addAttribute("payType","微信");
		model.addAttribute("message", meg);
		model.addAttribute("image", image);
		
		//计算送出
		memberTjService.addSend(order.getBuyerId(), 0);
		
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			return baseView()+"orderSucess";
	   	}
		boolean addcard = cardService.verifySunCard("",w.getId());
		
		if(addcard&&order.getPayment()==2){
			//token验证
			String s = UUID.randomUUID().toString();
			String chance = (String) request.getSession().getAttribute("chance");
			if(chance==null){
				request.getSession().setAttribute("cardToken",s);
				request.getSession().setAttribute("chance","1");
			}
		}
		
		return baseView()+"orderSucess";
	}
	
	//微信JSSDK
	@SuppressWarnings("unused")
	private String WXConfig(HttpServletRequest req,String param,String viewUrl){
		if(viewUrl!=null && !"".equals(viewUrl)){
			
		}else{
			String paramStr = req.getParameter(param);
			String code = req.getParameter("code");
			String state = req.getParameter("state");
			String winning = req.getParameter("winning");
			//-微信JSSDK
			viewUrl = PropertiesUtil.getProperty("domainName")+req.getRequestURI();
			if(paramStr!=null){
				paramStr="?"+param+"="+paramStr;
				viewUrl=viewUrl+paramStr;
				if(code!=null){
					code="&code="+code;
					viewUrl=viewUrl+code;
				}
				if(state!=null){
					state="&state="+state;
					viewUrl=viewUrl+state;
				}
				if(winning!=null){
					winning="&winning="+winning;
					viewUrl=viewUrl+winning;
				}
			}else{
				if(code!=null){
					code="?code="+code;
					viewUrl=viewUrl+code;
				}
				if(state!=null){
					state="&state="+state;
					viewUrl=viewUrl+state;
				}
				if(winning!=null){
					winning="&winning="+winning;
					viewUrl=viewUrl+winning;
				}
			}
		}
		logger.info("-----------微信JSSDK  sudoku  viewUrl=========== "+viewUrl);
		String config = JsUtil.generateConfigJson(TicketManager.getTicket(Wechat.APPID,Wechat.APPSECRET), 
				true, Wechat.APPID, viewUrl,"onMenuShareTimeline","onMenuShareAppMessage","onMenuShareQQ","onMenuShareWeibo","onMenuShareQZone","openLocation","getLocation");
		
		logger.info("-----------=========sudoku   sudoku ===============----------微信JSSDK config=========== "+config);
		return config;
	}
	
	
	//跳转到购买订单详情界面
	@RequestMapping(method = RequestMethod.GET,value = "buyorderinfo")
	public String buyorderinfo(Model model,HttpServletRequest request,
			String orderId ) {
		SunDdOrder order =orderService.findOne(orderId);
		//不是买家，打开买家访问订单地址，返回空
		String openId = (String)request.getSession().getAttribute(Wechat.WECATOPENID);
		if(!openId.equals(order.getBuyerId())){
			return null;
		}
		List<SunDdStatus> statusList= orderService.getLcxx(order.getPayment(), order.getDelivery());
		
		//微信支付链接
		String WXPayUrl = PropertiesUtil.getProperty("path")+request.getContextPath()+"/wechat/pay/payWeixinOrder?orderId="+orderId+"&timestamp="+System.currentTimeMillis();
//		String WXPayUrl = "http://192.168.8.17:8080/ybt3/pay/payWeixinOrder?orderId="+orderId+"&timestamp="+System.currentTimeMillis();
		try {
			WXPayUrl = WxpubOAuth.createOauthUrlForCode(Wechat.APPID, WXPayUrl, false);
		} catch (Exception e) {
			WXPayUrl="";
			e.printStackTrace();
		}finally{
			model.addAttribute("WXPayUrl",ShortenUrl.getShortenUrl(WXPayUrl, null));
			System.out.println("buyorderinfo====WXPayUrl ==="+WXPayUrl);
		}
		//支付宝支付链接
		String aliPayUrl = PropertiesUtil.getProperty("domainName")+"/wechat/pay/payWeixinOrder?orderId="+orderId+"&timestamp="+System.currentTimeMillis();
//		String aliPayUrl = "http://192.168.8.17:8080/ybt3/pay/payWeixinOrder?orderId="+orderId+"&timestamp="+System.currentTimeMillis();
		model.addAttribute("aliPayUrl",aliPayUrl);
		System.out.println("buyorderinfo====aliPayUrl ==="+aliPayUrl);
		
		model.addAttribute("statusList", statusList);
		model.addAttribute("order", order);
		return baseView()+"buyorderinfo";
	}
	
	
	/**
	 *@name    接收方打开连接
	 *@description 相关说明
	 *@time    创建时间:2016年6月24日上午9:15:06
	 *@param model
	 *@param request
	 *@param orderId
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@RequestMapping(method = RequestMethod.GET,value = "sendeeinfo")
	public String sendeeinfo(Model model,HttpServletRequest request,
			String orderId ) {
		
		SunDdOrder order =orderService.findOne(orderId);
		
		String openId = (String)request.getSession().getAttribute(Wechat.WECATOPENID);
		//保存微信信息
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		//需要支付未支付
		if( "10".equals(order.getStatus())){
			return "redirect:/wechat/index";
		}
		String baseView="/work/wechat/order/";
		if(!StringUtil.notNull(order.getSendeeId())&&w!=null&&!w.getId().equals(order.getBuyerId())){
			//如果接收方ID为空，且当前人不是发送人，则将当前人保存为收件人
			order.setSendeeId(w.getId());
			order.setUpdateTime(new Date());
			orderService.save(order);
			//订单贺卡分离
			SunBless bless =blessService.findByOrder(orderId);
			bless.setIsread(1);
			  w = wechatService.findOne(w.getId());
			bless.setToUserId(w);
			bless.setUpdateTime(new Date());
			blessService.save(bless);
			//计算收到
			memberTjService.addReceive(w.getId(), 0);
		}
		
		if(w==null || (w!=null && w.getSubscribe()!= 1)){
			SunWechatUser  bw  = wechatService.findOne(order.getBuyerId());
			if(bw != null)
				model.addAttribute("wximg", bw.getWechatHeadUrl());
			model.addAttribute("order", order);
			model.addAttribute("title", "来自"+order.getBuyerName()+"的贺卡");
			model.addAttribute("order", order);
			model.addAttribute("content", "查看好友  "+order.getBuyerName()+" 送来的一缕阳光~");
			return baseView+"wechat";//关注芸备胎
	   	}
		
		//我查看我送出的贺卡,没有收件人的，还可以分享
		if(openId.equals(order.getBuyerId())&&!StringUtil.notNull(order.getSendeeId())){
			//分享链接
			String url = PropertiesUtil.getProperty("domainName")+ "/wechat/order/sendeeinfo?orderId="+orderId;
			//分享链接   微信允许获取用户信息的链接  非静默 （snsapi_userinfo）
			String shareUrl = WXUtil.getOAuthUrl(url, false);
			/*String WXConfig = WXConfig(request,null,PropertiesUtil.getProperty("domainName")+ "/wechat/order/showCard?orderId="+orderId);
			model.addAttribute("WXConfig",WXConfig);//微信JSSDK
	*/		model.addAttribute("shareUrl",  PropertiesUtil.getProperty("domainName")+"/wechat/index?userCode=");//朋友分享链接
			model.addAttribute("shareAppMessageUrl", shareUrl);//分享链接
			String message = order.getMessage();
			if(message!=null)
			message = message.replaceAll("[\\t\\n\\r]", "");
			model.addAttribute("orderMessage", message);//分享链接
			model.addAttribute("order", order);
			return baseView+"showcard";
		}
		
		if(openId.equals(order.getSendeeId())){
			//修改订单状态
			if("20".equals(order.getStatus())){
				orderService.saveOrder(order, openId);
			}
			model.addAttribute("title", "来自"+order.getBuyerName()+"的贺卡");
			model.addAttribute("order", order);
			return baseView+"sendeeinfo";
		}
		model.addAttribute("title", order.getBuyerName()+"送"+order.getSendeeName());
		model.addAttribute("order", order);
		return baseView+"sendeeinfo";
	}
	
	
	
	/**
	 *@name   修改订单状态信息（改为付款，改为已读）
	 *@description 相关说明
	 *@time    创建时间:2016年1月28日下午1:27:51
	 *@param model
	 *@param request
	 *@param userName
	 *@param password
	 *@return
	 *@author   高艳花
	 * @throws Exception 
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "updateOrder")
	public ResultTBean<SunDdOrder> updateOrder(Model model,HttpServletRequest request,
			String id) throws Exception {
		String openId = (String)request.getSession().getAttribute(Wechat.WECATOPENID);
		SunDdOrder order=null;
		ResultTBean<SunDdOrder> resultBean = new ResultTBean<>();
		if(StringUtil.notNull(id)){
			order = new SunDdOrder();
			order.setId(id);
			order = orderService.saveOrder(order, openId);
			resultBean.setB(order);
		}
		if(order==null){
			resultBean.setS(0);
			resultBean.setM("保存失败");
		}else{
			resultBean.setS(1);
			resultBean.setM("保存成功");
		}
		return resultBean;
	}

	/**
	 * ajax加载列表
	 * */
	@RequestMapping(value = "/myget", method = RequestMethod.GET)
	@ResponseBody
	public Page<SunDdOrder> myget(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value ="subject") String subject,@RequestParam(value ="page") int page) {
		Map<String, Object> searchParams= Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		String openId = (String)request.getSession().getAttribute(Wechat.WECATOPENID);
    	searchParams.put("EQS_deleted", 0);//发布
    	searchParams.put("EQS_sendeeId", openId);//收卡人ID为当前微信ID
    	com.ybt.common.util.Page<SunZyPhoto> pageUtil1 = new com.ybt.common.util.Page<SunZyPhoto>();
		pageUtil1.setPageNo(page);
		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		Pageable pager1 =new PageRequest(pageUtil1.getPageNo()-1, pageUtil1.getPageSize()); 
		Page<SunDdOrder> page1 = orderService.findOrderGetByOpenId(filters, pager1);
		return page1;
	}
	/**
	 * ajax加载列表
	 * */
	@RequestMapping(value = "/mysend", method = RequestMethod.GET)
	@ResponseBody
	public Page<SunDdOrder> mysend(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value ="subject") String subject,@RequestParam(value ="page") int page) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		String openId = (String)request.getSession().getAttribute(Wechat.WECATOPENID);
		searchParams.put("EQS_deleted", 0);//发布
		searchParams.put("EQS_buyerId", openId);//微信ID
    	com.ybt.common.util.Page<SunZyPhoto> pageUtil1 = new com.ybt.common.util.Page<SunZyPhoto>();
		pageUtil1.setPageNo(page);
		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		Pageable pager1 =new PageRequest(pageUtil1.getPageNo()-1, pageUtil1.getPageSize()); 
		Page<SunDdOrder> page1 = orderService.findOrderSendByOpenId(filters, pager1);
		return page1;
	}
	
	
	//从列表中查看
	@RequestMapping(method = RequestMethod.GET,value = "showCard")
	public String showCard(Model model,HttpServletRequest request,String orderId ) {
	/*	SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			model.addAttribute("content", "");
			return "/work/wechat/order/wechat";//关注给点儿阳光
	   	}*/
		
		SunDdOrder order =orderService.findOne(orderId);
		//分享链接
		/*String url = PropertiesUtil.getProperty("domainName")+ "/wechat/order/sendeeinfo?orderId="+orderId;
		//分享链接   微信允许获取用户信息的链接  非静默 （snsapi_userinfo）
		String shareUrl = WXUtil.getOAuthUrl(url, true);
		String WXConfig = WXConfig(request,null,PropertiesUtil.getProperty("domainName")+ "/wechat/order/showCard?orderId="+orderId);
		model.addAttribute("WXConfig",WXConfig);//微信JSSDK
		model.addAttribute("shareUrl",  PropertiesUtil.getProperty("domainName")+"/wechat/index?userCode=");//朋友分享链接
		model.addAttribute("shareAppMessageUrl", shareUrl);//分享链接
	
		String meg= order.getMessage() .replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "");
		model.addAttribute("message", meg);
		String openId = (String)request.getSession().getAttribute(Wechat.WECATOPENID);
		//修改订单状态
		if(!"100".equals(order.getStatus().getId())&&openId.equals(order.getSendeeId())){
			orderService.saveOrder(order, openId);
		}*/
		model.addAttribute("order", order);
		return baseView()+"showcard";
	}
	
}