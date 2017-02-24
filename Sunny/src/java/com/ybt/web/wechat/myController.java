package com.ybt.web.wechat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.Wechat;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.model.work.SunBill;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunDownRecord;
import com.ybt.model.work.SunFeedback;
import com.ybt.model.work.SunMemberTj;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.work.IMemberTjService;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.ImagesService;
import com.ybt.service.work.OrderService;
import com.ybt.service.work.PhotoCovorService;
import com.ybt.service.work.SunBillService;
import com.ybt.service.work.SunDownRecordService;
import com.ybt.service.work.SunFeedbackService;
/**   
 * 我的-个人中心 control
 *  * @author AndyBao  
 * @version 4.0, 2016年7月28日 上午10:41:12   
 */   
@Controller
@RequestMapping(value = "/wechat/my")
public class myController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	public ImagesService imagesService;
	@Autowired
	private IMemberTjService memberTjService;
	@Autowired
	public IWechatService wechatService;
	@Autowired
	public SunFeedbackService sunFeedbackService;
	@Autowired
	public PhotoCovorService photoCovorService;
	@Autowired
	public SunBillService billService;
	@Autowired
	public SunDownRecordService downRecordlService;
	
	private String baseView(){
		return "/work/wechat/my/";
	}
	
	@RequestMapping(method = RequestMethod.GET,value="getMemberTj")
	@ResponseBody
	public Result<SunMemberTj> getMemberTj(Model model,HttpServletRequest request) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null ){
			return new Result<>("查询失败",null);
	   	}
		
		SunMemberTj tj = memberTjService.getSunMemberTj(w.getId());
		if(tj!=null){
			return new Result<>("",tj);
		}
		
		return new Result<>("查询失败",null);
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
	@RequestMapping(method = RequestMethod.GET,value="myList")
	public String my(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		
		
	/*	SunMemberTj tj =memberTjService.findOne(w.getId());
		request.setAttribute("tj", tj);
		
		
		Map<String, Object> searchParams= Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
    	searchParams.put("EQS_deleted", 0);//发布
    	List<String> myList = new ArrayList<String> ();
    	myList.add("20");
    	myList.add("100");
    	searchParams.put("GES_status",myList);//发布
    	searchParams.put("EQS_sendeeId", openId);//收卡人ID为当前微信ID
    	com.ybt.common.util.Page<SunDdOrder> pageUtil = new com.ybt.common.util.Page<SunDdOrder>();
		pageUtil.setPageNo(pageNumber);
		//List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		//Pageable pager =new PageRequest(pageUtil.getPageNo()-1, pageUtil.getPageSize()); 
		//Page<SunDdOrder> page = orderService.findOrderGetByOpenId(filters, pager);	
		pageUtil.setOrderBy("createTime");
		pageUtil.setOrder("desc");
		//orderService.findAll(filters, pageUtil);
		
		try {
			orderService.findOrderSendByOpenId(openId, pageUtil);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//pageUtil.setTotalCount(pageUtil.getTotalElements());
		//pageUtil.setResult(pageUtil.getContent());
		
		
		model.addAttribute("page", pageUtil);
		
		
		Map<String, Object> searchParams1= Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		searchParams1.put("EQS_deleted", 0);//发布
    	searchParams1.put("GES_status",myList);//发布
		searchParams1.put("EQS_buyerId", openId);//收卡人ID为当前微信ID
		com.ybt.common.util.Page<SunDdOrder> pageUtil1 = new com.ybt.common.util.Page<SunDdOrder>();
		pageUtil1.setPageNo(pageNumber);
		//List<PropertyFilter> filters1 = PropertyFilter.buildFrom(searchParams1);
		//Page<SunDdOrder> page1 = orderService.findOrderGetByOpenId(filters1, pager1);
//			pageUtil1.setTotalCount(page1.getTotalElements());
//			pageUtil1.setResult(page1.getContent());
		pageUtil1.setOrderBy("createTime");
		pageUtil1.setOrder("desc");
		
		pageUtil.setOrderBy("createTime");
		pageUtil.setOrder("desc");
		//orderService.findAll(filters1, pageUtil);
		
		try {
			orderService.findOrderbyerByOpenId(openId, pageUtil1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("page1", pageUtil1);
		
		//计算收到
		memberTjService.addReceive(openId,  (int) pageUtil.getTotalCount());

		//计算送出
		memberTjService.addSend(openId, (int)pageUtil1.getTotalCount());
		
		
		
		List<SunZyPhotoCover> covorList = photoCovorService.findSunZyPhotoCoverByCameristId(w.getId());
		if(covorList!=null && !covorList.isEmpty())
			model.addAttribute("fengmian", covorList.get(0));
			*/
		
		return baseView()+"index";
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
	@RequestMapping(method = RequestMethod.GET,value="zhufu")
	public String listZhufu(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			
	   	}
		com.ybt.common.util.Page<SunDdOrder> pageUtil1 = new com.ybt.common.util.Page<SunDdOrder>();
		pageUtil1.setPageNo(pageNumber);
		orderService.getMyOrderList(w.getId(), pageUtil1);
		
		return baseView()+"zhufuList";
	}
	
	/**  
	 * 可发送祝福列表
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月21日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.GET,value="zhufu/sendable")
	@ResponseBody
	public List<SunDdOrder> sendable(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		
		com.ybt.common.util.Page<SunDdOrder> pageUtil1 = new com.ybt.common.util.Page<SunDdOrder>();
		pageUtil1.setPageNo(pageNumber);
		pageUtil1.setPageSize(pageSize);
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			return new ArrayList<SunDdOrder>();
			
	   	}
		
		orderService.getMyOrderListByState(w.getId(),"20", pageUtil1);
		
		return pageUtil1.getResult();
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
	@RequestMapping(method = RequestMethod.GET,value="cards")
	public String cardList(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		
		
		return baseView()+"cardList";
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
	@RequestMapping(method = RequestMethod.GET,value="letters")
	public String letterList(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		
		
		return baseView()+"letterList";
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
	/*
	@RequestMapping(method = RequestMethod.GET,value = "myList")
	public String myList(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		Map<String, Object> searchParams= Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		String openId = (String)request.getSession().getAttribute(Wechat.WECATOPENID);
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
    	searchParams.put("EQS_deleted", 0);//发布
    	List<String> myList = new ArrayList<String> ();
    	myList.add("20");
    	myList.add("100");
    	searchParams.put("GES_status",myList);//发布
    	searchParams.put("EQS_sendeeId", openId);//收卡人ID为当前微信ID
    	com.ybt.common.util.Page<SunDdOrder> pageUtil = new com.ybt.common.util.Page<SunDdOrder>();
		pageUtil.setPageNo(pageNumber);
		//List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		//Pageable pager =new PageRequest(pageUtil.getPageNo()-1, pageUtil.getPageSize()); 
		//Page<SunDdOrder> page = orderService.findOrderGetByOpenId(filters, pager);	
		pageUtil.setOrderBy("createTime");
		pageUtil.setOrder("desc");
		//orderService.findAll(filters, pageUtil);
		
		try {
			orderService.findOrderSendByOpenId(openId, pageUtil);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//pageUtil.setTotalCount(pageUtil.getTotalElements());
		//pageUtil.setResult(pageUtil.getContent());
		
		
		model.addAttribute("page", pageUtil);
		
		
		Map<String, Object> searchParams1= Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		searchParams1.put("EQS_deleted", 0);//发布
    	searchParams1.put("GES_status",myList);//发布
		searchParams1.put("EQS_buyerId", openId);//收卡人ID为当前微信ID
		com.ybt.common.util.Page<SunDdOrder> pageUtil1 = new com.ybt.common.util.Page<SunDdOrder>();
		pageUtil1.setPageNo(pageNumber);
		//List<PropertyFilter> filters1 = PropertyFilter.buildFrom(searchParams1);
		//Page<SunDdOrder> page1 = orderService.findOrderGetByOpenId(filters1, pager1);
//			pageUtil1.setTotalCount(page1.getTotalElements());
//			pageUtil1.setResult(page1.getContent());
		pageUtil1.setOrderBy("createTime");
		pageUtil1.setOrder("desc");
		
		pageUtil.setOrderBy("createTime");
		pageUtil.setOrder("desc");
		//orderService.findAll(filters1, pageUtil);
		
		try {
			orderService.findOrderbyerByOpenId(openId, pageUtil1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("page1", pageUtil1);
		
		//计算收到
		memberTjService.addReceive(openId,  (int) pageUtil.getTotalCount());

		//计算送出
		memberTjService.addSend(openId, (int)pageUtil1.getTotalCount());
		
		return baseView()+"myList";
	}*/
	
	/**
	 * ajax加载列表
	 * */
	@RequestMapping(value = "/myListPage", method = RequestMethod.GET)
	@ResponseBody
	public com.ybt.common.util.Page<SunDdOrder> myListPage(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value ="subject") String subject,@RequestParam(value ="page") int page) {
		Map<String, Object> searchParams= Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		String openId = (String)request.getSession().getAttribute(Wechat.WECATOPENID);
		
		com.ybt.common.util.Page<SunDdOrder> pageUtil1 = new com.ybt.common.util.Page<SunDdOrder>();
		
		pageUtil1.setPageNo(page);
		if("0".equals(subject)){
			searchParams.put("EQS_sendeeId", openId);//收到
			
			try {
				orderService.findOrderbyerByOpenId(openId, pageUtil1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			searchParams.put("EQS_buyerId", openId);//送出
			try {
				orderService.findOrderbyerByOpenId(openId, pageUtil1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		searchParams.put("EQS_deleted", 0);//发布
		List<String> myList = new ArrayList<String> ();
    	myList.add("20");
    	myList.add("100");
		searchParams.put("GES_status",myList);//发布
		//List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		
		
		//Pageable pager1 =new PageRequest(pageUtil1.getPageNo()-1, pageUtil1.getPageSize()); 
		//Page<SunDdOrder> page1 = orderService.findOrderGetByOpenId(filters, pager1);
		//orderService.findAll(filters, pageUtil1);
		return pageUtil1;
	}
	
	
	
	/**
	 * feedback 意见反馈
	 * */
	@RequestMapping(value = "/feedback", method = RequestMethod.GET)
	public String feedback(Model model,
			HttpServletRequest request, HttpServletResponse response) {

		
		return baseView()+"feedback";
	}
	
	/**
	 * feedback 意见反馈
	 * */
	@RequestMapping(value = "/feedback", method = RequestMethod.PUT)
	@ResponseBody
	public String feedbackPut(Model model,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value ="contact") String contact,
			@RequestParam(value ="type") String type,
			@RequestParam(value ="body") String body) {

		SunFeedback feedback = new SunFeedback();
		feedback.setBody(body);
		feedback.setContact(contact);
		feedback.setType(type);
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w!=null){
			feedback.setOpenid(w.getId());
	   	}
		
		sunFeedbackService.save(feedback);
		
		return "";
	}
	
	
	/**
	 * 阳光比明细 页面
	 * */
	@RequestMapping(value = "/mySunB", method = RequestMethod.GET)
	public String mySunB(Model model,HttpServletRequest request, HttpServletResponse response) {

		return baseView()+"sunbillList";
	}
	
	/**
	 * 阳光比明细 -ajax加载列表
	 * */
	@RequestMapping(value = "/mySunB/list", method = RequestMethod.GET)
	@ResponseBody
	public com.ybt.common.util.Page<SunBill> mySunBList(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value ="page") int page) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			return null;
	   	}
		
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
    	searchParams.put("EQS_mid", w.getId());//发布
    	com.ybt.common.util.Page<SunBill> pageUtil1 = new com.ybt.common.util.Page<SunBill>();
		pageUtil1.setOrder("desc");
		pageUtil1.setPageNo(page);

		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		Pageable pager =new PageRequest(pageUtil1.getPageNo()-1, pageUtil1.getPageSize()); 
		
		Page<SunBill> page1 = billService.findSunBillByProperty(filters, pager);
		pageUtil1.setResult(page1.getContent());
		pageUtil1.setTotalCount(page1.getTotalElements());
		return pageUtil1;
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
			@RequestParam(value="orderId")String orderId,
			@RequestParam(value="email")String email){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			return new Result<SunDownRecord>("请先登录",null);
	   	}
		
		return downRecordlService.saveSunDownRecord(orderId, w, email);
	}
}