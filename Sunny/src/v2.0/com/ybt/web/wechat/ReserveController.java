package com.ybt.web.wechat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.common.bean.ResultTBean;
import com.ybt.common.constant.Wechat;
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.EmojiFilter;
import com.ybt.common.util.PropertiesUtil;
import com.ybt.common.util.StringUtil;
import com.ybt.model.work.MemberRelation;
import com.ybt.model.work.SunAttraction;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.aop.Authorization;
import com.ybt.service.base.SunSmsService;
import com.ybt.service.work.AttractionService;
import com.ybt.service.work.IPaymentService;
import com.ybt.service.work.ImagesService;
import com.ybt.service.work.ReserveBlessService;
import com.ybt.service.work.ReservePhotoService;

import wechat.api.ShorturlAPI;
import wechat.bean.Shorturl;
import wechat.support.TokenManager;
import wechat.util.WXUtil;

/**
 *   预订提交 -管理
 * */
@Controller
@RequestMapping(value = "/wechat/v2/reserve")
public class ReserveController {

	@Autowired
	private IPaymentService payService;
	@Autowired
	public ImagesService imagesService;
	@Autowired
	@Qualifier("reserveBlessService")
	private ReserveBlessService reserveBlessService;
	@Autowired
	@Qualifier("reservePhotoService")
	private ReservePhotoService reservePhotoService;
	@Autowired
	private SunSmsService smsService;
	
	@Resource
	public Map<String,String> constant;

	
	@Autowired
	private AttractionService attractionService;
	private String baseView(String v) {
		return "/work/wechat/v2.0/reserve/"+v;
	}
	
	/*
	 *预订 页
	 */
	@Authorization
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {
		
		return baseView("index");
	}
	/*
	 *当前时间大于活动时间跳转到活动结束页面
	 */
	@RequestMapping(value="ifEndPage",method = RequestMethod.GET)
	public String endPage(Model model,HttpServletRequest request) {
		Date endDate = DateUtil.StringToDate("2017-01-28 02:00:00", "yyyy-MM-dd HH:mm:ss");
		if(DateUtil.compareDate(new Date(), endDate)){//当前时间大于活动时间跳转到结束页面
			return "/work/wechat/v2.0/activityEnd";
		}
		return "redirect:/wechat/v2/reserve#add-friend";
	}
	
	
	/*
	 *城市列表
	 */
	@RequestMapping(value="data", method = RequestMethod.GET)
	@ResponseBody
	public List<SunAttraction> relation(Model model,HttpServletRequest request) {
		
		return attractionService.getCityListByGroupByCity();
	}
	
	/*
	 *城市列表
	 */
	@RequestMapping(value="selectCity", method = RequestMethod.POST)
	@ResponseBody
	public List<SunZyPhoto> selectCity(Model model,HttpServletRequest request,
			@RequestParam(value ="citys",defaultValue="") String citys,
			@RequestParam(value ="date",defaultValue="") String date) {
		List<SunZyPhoto> list = null;
		try{
		      list = reservePhotoService.getPhotoListByCitys(citys, date);
			if(list == null){
				return new ArrayList<SunZyPhoto>();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
		
		return list;
	}
	
	
	/*
	 *通过订单id查询预定日出的照片
	 */
	@RequestMapping(value="getPhotosByOrderId", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<SunZyPhoto>> getPhotosByOrderId(HttpServletRequest request,
			@RequestParam(value ="orderId",defaultValue="") String orderId) {
		Result<List<SunZyPhoto>> resultList = null;
		try{
		      resultList = reservePhotoService.getPhotoListByOrderId(orderId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultList;
	}
	
	
	
	/*
	 *发送 
	 */
	@RequestMapping(value="sendNoice", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> sendNoice(Model model,HttpServletRequest request,
			@RequestParam(value ="orderId",defaultValue="") String orderId) {
		try{
			SunBless bless = reserveBlessService.findByOrder(orderId);
			String url =constant.get("domainName")+ "/wechat/v2/bless/sendeeinfo?orderId="+bless.getOrder().getId();
			Shorturl sh =ShorturlAPI.shorturl(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), url);
			if(sh.getShort_url()!=null)
				smsService.sendSmsReserveOrder(bless.getRelation().getMobileNo(), bless.getSender(), sh.getShort_url());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new Result<String>("","");
	}
	
	/**
	 *   保存新订单信息 (预订流程)
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "saveReserveOrder")
	public ResultTBean<SunDdOrder> saveReserveOrder(Model model,HttpServletRequest request,
			@RequestParam(value ="relationId" ) String relationId,
			@RequestParam(value ="citys[]",defaultValue="" ) String[] citys,
			@RequestParam(value ="bless",defaultValue="") String bless,
			@RequestParam(value ="relationName",defaultValue="") String relationName,
			@RequestParam(value ="relationRelation",defaultValue="") String relationRelation,
			@RequestParam(value ="relationPhone",defaultValue="") String relationPhone,
			@RequestParam(value ="relationBirth",defaultValue="") String relationBirth){
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
			String message = bless;//祝福语
			String buyerName = w.getWechatNickname();//送卡人名称
			
			//过滤客户端表情
			 message = EmojiFilter.emojiEmpty(message,"");
			 relationName = EmojiFilter.emojiEmpty(relationName,"");
			 buyerName = EmojiFilter.emojiEmpty(buyerName,"");
			
			
			String sunprice = constant.get("sunPrice");
			Double price=StringUtil.getDouble(sunprice);
//			order.setCardImage(r.getT());
			SunBless blesss = null;
			SunDdOrder neworder = reserveBlessService.saveOrder(w, relationId, relationName, relationRelation, relationPhone, relationBirth, message, price, sunPayType);
			if(neworder==null){
				resultBean.setS(0);
				resultBean.setM("保存失败");
			}else{
				resultBean.setS(1);
				// 保存 贺卡信息
				//blessService.saveBless(w, neworder.getBuyerName(), neworder.getSendeeName(), neworder.getMessage(), neworder.getCardImage(), neworder.getPhoto(), neworder);
				//预订信息
				Result<SunBless> resultBless = reserveBlessService.saveReserveBless(w, neworder.getBuyerName(),relationId, neworder.getMessage(), citys, neworder);
				if(resultBless.getState()==0){
					resultBean.setS(0);
					resultBean.setM("提交失败,预订有误！");
					return resultBean;
				}
				blesss=resultBless.getT();
			}
			resultBean.setB(neworder);
			//方便起见  开发模式  使用本地路径
			if(!constant.get("runMode").equals("production")){
				return resultBean;
			}
			
					
   			if(sunPayType!=null&&neworder.getPayment()==2){
   				resultBean.setM(neworder.getId());
   			}
   			if(neworder.getPayment()==1){
   			    //获取 Ping++ change
   				String subject ="预订生日日出";
				int metering = 0;
   	   			String body="预订"+DateUtil.getDateFormat(blesss.getRelation().getBirthday(), "yyyy-MM-dd")+"的日出";
   		
   				Map<String,Object> jo = new HashMap<String,Object>();
   				jo.put("amount", neworder.getPayTotalPrice());
   				jo.put("subject", subject);
   				jo.put("body", body);
   				jo.put("order_no", neworder.getOrderNo());
   				jo.put("metering", metering);
   				jo.put("channel", "wx_pub");
   				jo.put("open_id", w.getId());
   				
   				String change = payService.pingxxGetCharge(jo);
   				
   				System.out.println("------------change:"+ change);
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
	/*
	 * 查看状态
	 */
	@RequestMapping(value="showOrder",method = RequestMethod.GET)
	public String showOrder(Model model,HttpServletRequest request,
			@RequestParam(value ="orderId" ) String orderId) {
		
		SunBless  bless =	reserveBlessService.findByOrder(orderId);
		model.addAttribute("bless", bless);
		if(bless == null)
			return baseView("show");
		MemberRelation relation = bless.getRelation();
		if(relation!=null){
			Date blessBirth = bless.getBirthday();
			if(bless.getBirthday()==null){
				blessBirth = DateUtil.getBirthDay(relation.getBirthday());
			}
			//当前日期
			Date d = new Date();
			//距离
			int days =DateUtil.getBirthCount(blessBirth, d);
			days = days<0 ? 0:days;
			if(days == 365){
				days = 0;
			}
			model.addAttribute("days", days);
			
			String citys = bless.getCitys();
			if(citys !=null)
				model.addAttribute("citys", citys.split(","));
			
			//分享链接
			String url = PropertiesUtil.getProperty("domainName")+ "/wechat/v2/bless/sendeeinfo?orderId="+orderId;
			String shareUrl = WXUtil.getOAuthUrl(url, false);
			model.addAttribute("shareUrl",  PropertiesUtil.getProperty("domainName")+"/wechat/index?userCode=");//朋友分享链接
			model.addAttribute("shareAppMessageUrl", shareUrl);//分享链接
			String message = bless.getBless();
			if(message!=null)
				message = message.replaceAll("[\\t\\n\\r]", "");
			model.addAttribute("orderMessage", message);//分享链接
		}
		
		return baseView("show");
	}

}