package com.ybt.web.wechat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.OrderService;
import com.ybt.service.work.SunBlessService;

/**
 *   祝福 - 贺卡 - 管理
 * */
@Controller
@RequestMapping(value = "/wechat/bless")
public class BlessController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(BlessController.class);
	
	@Autowired
	@Qualifier("sunBlessService")
	private SunBlessService blessService;
	@Autowired
	private OrderService orderService;
	
	@Autowired
	public IWechatService wechatService;
	
	
	private String baseView() {
		return "/work/wechat/bless";
	}
	
	/*
	 *祝福
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {
		
		return baseView()+"/index";
	}
	
	/*
	 *查看祝福
	 */
	@RequestMapping(value ="show")
	public String shou(Model model,HttpServletRequest request,
			@RequestParam(value="blessId" )String blessId) {
		
		SunBless bless = blessService.findOne(blessId);
		if(bless==null){
			return baseView()+"/show";
		}
		model.addAttribute("bless", bless);
		
		return baseView()+"/show";
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
}