package com.ybt.web.wechat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.Wechat;
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.PropertiesUtil;
import com.ybt.common.util.StringUtil;
import com.ybt.model.work.MemberRelation;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunDownRecord;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.work.IMemberTjService;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.OrderService;
import com.ybt.service.work.ReserveBlessService;
import com.ybt.service.work.ReservePhotoService;
import com.ybt.service.work.SunBlessService;
import com.ybt.service.work.SunDownRecordService;

import wechat.util.WXUtil;

/**
 *  祝福 - 贺卡 - 管理
 * */
@Controller
@RequestMapping(value = "/wechat/v2/bless")
public class ReserveBlessController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(BlessController.class);
	
	@Autowired
	@Qualifier("sunBlessService")
	private SunBlessService blessService;
	@Autowired
	@Qualifier("reserveBlessService")
	private ReserveBlessService reserveBlessService;
	@Autowired
	@Qualifier("reservePhotoService")
	private ReservePhotoService reservePhotoService;
	@Autowired
	private OrderService orderService;
	@Autowired
	public SunDownRecordService downRecordlService;
	@Autowired
	public IWechatService wechatService;
	@Autowired
	private IMemberTjService memberTjService;
	
	private String baseView(String view) {
		return "/work/wechat/v2.0/bless/"+view;
	}
	
	/*
	 *祝福
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {
		
		return baseView("index");
	}
	
	/*
	 *查看祝福
	 */
	@RequestMapping(value ="show")
	public String shou(Model model,HttpServletRequest request,
			@RequestParam(value="blessId" )String blessId) {
		
		SunBless bless = blessService.findOne(blessId);
		if(bless==null){
			return baseView("show");
		}
		model.addAttribute("bless", bless);
		
		return baseView("show");
	}
	
	/**  
	 * 祝福列表
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月21日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.GET,value="data")
	@ResponseBody
	public com.ybt.common.util.Page<SunBless> data(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		
		com.ybt.common.util.Page<SunBless> pageUtil1 = new com.ybt.common.util.Page<SunBless>();
		pageUtil1.setPageNo(pageNumber);
		pageUtil1.setPageSize(pageSize);
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			return pageUtil1;
	   	}
		
		blessService.getMyAllBlessList(w.getId(), pageUtil1);
		List<SunBless> newlist = new ArrayList<SunBless>();
		List<SunBless> blist = pageUtil1.getResult();
		for (SunBless sunBless : blist) {
			if(sunBless.getUserId().getId().equals(w.getId())){
				sunBless.setRole(0);
			}else{
				sunBless.setRole(1);
			};
			newlist.add(sunBless);
		}
		pageUtil1.setResult(newlist);
		return pageUtil1;
	}
	
	/**  
	 * 祝福列表
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月21日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.GET,value="sendable")
	@ResponseBody
	public com.ybt.common.util.Page<SunBless> sendable(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		
		com.ybt.common.util.Page<SunBless> pageUtil1 = new com.ybt.common.util.Page<SunBless>();
		pageUtil1.setPageNo(pageNumber);
		pageUtil1.setPageSize(pageSize);
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			return pageUtil1;
	   	}
		blessService.findMyNoSendBlessList(w.getId(), pageUtil1);
		return pageUtil1;
	}
	
	/**  
	 * 发送
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月21日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.POST,value="send")
	@ResponseBody
	public Result<SunBless> send(Model model,HttpServletRequest request,
			@RequestParam(value = "orderId", defaultValue = "") String orderId) {
		if("".equals(orderId)){
			return new Result<SunBless>("发送失败，无效订单",null);
		}
		SunDdOrder order = new SunDdOrder();
		order = orderService.findOne(orderId);
		
		if(order==null){
			return new Result<SunBless>("发送失败，无效订单",null);
		}
		SunBless bless = blessService.findByOrder(orderId);
		
		if(bless!=null){
			bless.setIssend(1);
			bless.setUpdateTime(new Date());
			blessService.save(bless);
			return new Result<SunBless>("",bless);
		}
		
		return new Result<SunBless>("发送失败，无效订单",null);
	}
	/**  
	 * 通过订单查询 bless
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月21日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.GET,value="getBlessByorder")
	@ResponseBody
	public Result<SunBless> getBlessByorder(Model model,HttpServletRequest request,
			@RequestParam(value = "orderId", defaultValue = "") String orderId) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null ){
			return new Result<SunBless>("无数据",null);
	   	}
		
		if("".equals(orderId)){
			return new Result<SunBless>("无效订单id",null);
		}
		SunBless bless = blessService.findByOrder(orderId);
		if(bless!=null && bless.getUserId().getId().equals(w.getId())){
			return new Result<SunBless>("",bless);
		}
		
		return new Result<SunBless>("无数据",null);
	}
	
	/**
	 *@name    接收方打开连接
	 */
	@RequestMapping(method = RequestMethod.GET,value = "sendeeinfo")
	public String sendeeinfo(Model model,HttpServletRequest request,
			String orderId ) {
		
		SunDdOrder order =orderService.findOne(orderId);
		
		String openId = (String)request.getSession().getAttribute(Wechat.WECATOPENID);
		//保存微信信息
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		
		SunBless  bless =	reserveBlessService.findByOrder(orderId);
		if(bless == null){
			return "redirect:/wechat/index";
		}
		//照片未领取
		
		model.addAttribute("bless", bless);
		

		MemberRelation relation = bless.getRelation();
		if(relation!=null){
			
			//当前日期
			Date d = new Date();
//			Date d= DateUtil.StringToDate("2018-01-16 05:00", "yyyy-MM-dd HH:mm");
			//生日日期
			Date blessBirth = bless.getBirthday();
			if(bless.getBirthday()==null){
				blessBirth = DateUtil.getBirthDay(relation.getBirthday());
			}
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
			
			//设置时间轴 数据
			if(days<=0){
				setTimeResult(model,d , days);
			}
			model.addAttribute("blessBirth", DateUtil.getDateFormat(blessBirth, "yyyy-MM-dd"));
		}
		//需要支付未支付
		if( "10".equals(order.getStatus())){
			return "redirect:/wechat/index";
		}
		if(!StringUtil.notNull(order.getSendeeId())&&w!=null&&!w.getId().equals(order.getBuyerId())){
			//如果接收方ID为空，且当前人不是发送人，则将当前人保存为收件人
			order.setSendeeId(w.getId());
			order.setUpdateTime(new Date());
			orderService.save(order);
			//订单贺卡分离
			bless.setIsread(1);
			  w = wechatService.findOne(w.getId());
			bless.setToUserId(w);
			bless.setUpdateTime(new Date());
			blessService.save(bless);
			//计算收到
			memberTjService.addReceive(w.getId(), 0);
		}
		
		if(w==null || (w!=null && w.getSubscribe()!= 1)){
			/*SunWechatUser  bw  = wechatService.findOne(order.getBuyerId());
			if(bw != null)
				model.addAttribute("wximg", bw.getWechatHeadUrl());
			model.addAttribute("order", order);
			model.addAttribute("title", "来自"+order.getBuyerName()+"的贺卡");
			model.addAttribute("order", order);
			model.addAttribute("content", "查看好友  "+order.getBuyerName()+" 送来的一缕阳光~");
			return baseView("wechat");//关注芸备胎
*/	   	}
		
		//我查看我送出的贺卡,没有收件人的，还可以分享
		if(openId!=null&&order.getBuyerId().equals(openId)&&bless.getIsread()==0){
			//分享链接
			String url = PropertiesUtil.getProperty("domainName")+ "/wechat/v2/bless/sendeeinfo?orderId="+orderId;
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
			model.addAttribute("showSendBtn", "ok");
			return baseView("sendeeinfo");
		}
		
		if(openId!=null&&order.getSendeeId().equals(openId)){
			if(bless.getPhoto()!=null){
				model.addAttribute("title", order.getBuyerName()+"送"+order.getSendeeName());
				model.addAttribute("order", order);
				return "redirect:showcard?blessId="+bless.getId();
			}
			
			//修改订单状态
			if("20".equals(order.getStatus())){
				orderService.saveOrder(order, openId);
			}
			model.addAttribute("title", "来自"+order.getBuyerName()+"的贺卡");
			model.addAttribute("order", order);
			//显示 领取按钮
			model.addAttribute("showGetGiftBtn", "ok");
			return baseView("sendeeinfo");
		//	return "redirect:showcard?blessId="+bless.getId();
		}
		if(bless.getPhoto()!=null){
			model.addAttribute("title", order.getBuyerName()+"送"+order.getSendeeName());
			model.addAttribute("order", order);
			return "redirect:showcard?blessId="+bless.getId();
		}
		
		
		return baseView("sendeeinfo");
	}
	/**
	 *  接收方打开连接
	 */
	@RequestMapping(method = RequestMethod.GET,value = "showcard")
	public String showcard(Model model,HttpServletRequest request,
			String blessId ) {
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
				
		SunBless  bless =	reserveBlessService.findOne(blessId);
		if(bless == null){
			return "redirect:/wechat/index";
		}
		
		model.addAttribute("bless", bless);

		if(w!=null&&bless.getToUserId()!=null&&bless.getToUserId().getId().equals(w.getId())){
			model.addAttribute("down", "true");
		}
		return baseView("showcard");
	}
	
	/**  
	 * 发送
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月21日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.POST,value="selectPhoto")
	@ResponseBody
	public Result<SunBless> selectPhoto(Model model,HttpServletRequest request,
			@RequestParam(value = "blessId", defaultValue = "") String blessId,
			@RequestParam(value = "photoId", defaultValue = "") String photoId) {
		if("".equals(blessId)){
			return new Result<SunBless>("订单错误",null);
		}
		if("".equals(photoId)){
			return new Result<SunBless>("请选择照片",null);
		}
		SunBless bless = blessService.findOne(blessId);
		
		if(bless==null)return new Result<SunBless>("数据错误",null);
		if(bless.getPhoto()!=null) return new Result<SunBless>("数据错误",null);
		SunZyPhoto photo= reservePhotoService.findOne(photoId);
		if(photo==null) return new Result<SunBless>("数据错误",null);
		
		bless.setPhoto(photo);
		bless.setUpdateTime(new Date());
		blessService.save(bless);
		
		return new Result<SunBless>("",bless);
		
	}
	/**  
	 * 提交  下载原图请求
	 * @param model
	 * @param request
	 * @param response
	 * @param orderId
	 * @param email
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月24日 上午9:14:30 
	 */
	@RequestMapping(value="submitDownRequest",method=RequestMethod.POST)
	@ResponseBody
	public Result<SunDownRecord> submitDownRequest(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="blessId")String blessId,
			@RequestParam(value="photoId")String photoId,
			@RequestParam(value="email")String email){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			return new Result<SunDownRecord>("请先登录",null);
	   	}
		
		return downRecordlService.saveSunDownRecord(blessId,photoId , w, email);
	}
	public void setTimeResult(Model model,Date d,int days){
		boolean past = days<=0 ? true : false;
		
		String time = DateUtil.getDateTime("HH:mm", d);
		String time1 = "06:00";
		String time2 = "06:55";
		String time3 = "07:25";
		String time4 = "07:35";
		String timeOK = "10:00";
		String datestr="2017-12-12";
		List<Map<String,String>> timeList = new ArrayList<Map<String,String>>();
		Map<String,String> timesome= new HashMap<String,String>();
		
		if(days < 0){
			timesome= new HashMap<String,String>();
			timesome.put("time", "06:00");
			timesome.put("message", "摄影师扛着相机，正在奔赴拍摄地点");
			timeList.add(timesome);
		}else{
			if(days == 0 && DateUtil.StringToDate(datestr+" "+time1,"yyyy-MM-dd HH:mm").getTime() < DateUtil.StringToDate(datestr+" "+time,"yyyy-MM-dd HH:mm").getTime()){
				timesome= new HashMap<String,String>();
				timesome.put("time", "06:00");
				timesome.put("message", "摄影师扛着相机，正在奔赴拍摄地点");
				timeList.add(timesome);
			};
		}
		
		if(days < 0){
			timesome= new HashMap<String,String>();
			timesome.put("time", "06:55");
			timesome.put("message", "摄影师已就位，正在调试机位");
			timeList.add(timesome);
		}else{
			if(past && DateUtil.StringToDate(datestr+" "+time2,"yyyy-MM-dd HH:mm").getTime() < DateUtil.StringToDate(datestr+" "+time,"yyyy-MM-dd HH:mm").getTime()){
				timesome= new HashMap<String,String>();
				timesome.put("time", "06:55");
				timesome.put("message", "摄影师已就位，正在调试机位");
				timeList.add(timesome);
			};
		}
		
		if(days < 0){
			timesome= new HashMap<String,String>();
			timesome.put("time", "07:25");
			timesome.put("message", "咔嚓!咔嚓！您的生日阳光已经拍摄完成");
			timeList.add(timesome);
		}else{
			if(days == 0 && DateUtil.StringToDate(datestr+" "+time3,"yyyy-MM-dd HH:mm").getTime() < DateUtil.StringToDate(datestr+" "+time,"yyyy-MM-dd HH:mm").getTime()){
				timesome= new HashMap<String,String>();
				timesome.put("time", "07:25");
				timesome.put("message", "咔嚓!咔嚓！您的生日阳光已经拍摄完成");
				timeList.add(timesome);
			};
		}
		
		if(days < 0){
			timesome= new HashMap<String,String>();
			timesome.put("time", "07:35");
			timesome.put("message", "摄影师正赶回工作室，为您上传照片");
			timeList.add(timesome);
		}else{
			if(days == 0 && DateUtil.StringToDate(datestr+" "+time4,"yyyy-MM-dd HH:mm").getTime() < DateUtil.StringToDate(datestr+" "+time,"yyyy-MM-dd HH:mm").getTime()){
				timesome= new HashMap<String,String>();
				timesome.put("time", "07:35");
				timesome.put("message", "摄影师正赶回工作室，为您上传照片");
				timeList.add(timesome);
			};
		}
		
		model.addAttribute("timelist", timeList);
		
		//领取
		if(days < 0){
			model.addAttribute("isPhoto", "ok");
		}else{
			if(days == 0 && DateUtil.StringToDate(datestr+" "+timeOK,"yyyy-MM-dd HH:mm").getTime() < DateUtil.StringToDate(datestr+" "+time,"yyyy-MM-dd HH:mm").getTime()){
				model.addAttribute("isPhoto", "ok");
			}else{
				model.addAttribute("isPhoto", "xiaoyu_10");
			};
		}
		
		
	}
}