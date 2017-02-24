package com.ybt.web.wechat;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.Wechat;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunGySms;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyTree;
import com.ybt.service.base.SunSmsService;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunZyTreeService;

/**
 * 微信  活动
 * */
@Controller
@RequestMapping(value = "/wechat/activity")
public class ActivityController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(ActivityController.class);
	
	@Autowired
	private SunZyTreeService treeService;
	@Autowired
	public IWechatService wechatService;
	
	@Autowired
	public SunSmsService sunSmsService;
	@Autowired
	public SunCardService sunCardService;
	private String baseView() {
		return "/work/wechat/activity";
	}
	
	/*
	 *我为党来赞棵树
	 */
	@RequestMapping
	public String index(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "city", defaultValue = "") String city,
			@RequestParam(value = "date", defaultValue = "") String date) {
		String openId = (String)request.getSession().getAttribute(Wechat.WECATOPENID);
		String parent = request.getParameter("parent");
		
		if (openId != null){
			SunWechatUser wechat = wechatService.findOne(openId);
			SunZyTree tree = treeService.findSunZyTreeByOpenid(openId);
			
			if (wechat  == null || (wechat.getSubscribe()!= null && wechat.getSubscribe() == 1))
				model.addAttribute("wx", false);
			
			if (wechat  == null || (wechat.getSubscribe()!= null && wechat.getSubscribe() == 1))
				model.addAttribute("tree", tree);
		}
		
		if ( parent != null)
		model.addAttribute("parent", parent);
		
		return baseView()+"/dang20160701";
		
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
	@RequestMapping(method = RequestMethod.POST,value = "getTree")
	public Result<SunZyTree> saveOrder(Model model,HttpServletRequest request,
			SunDdOrder order){
		try {
			String openId = (String)request.getSession().getAttribute(Wechat.WECATOPENID);
			String phone = request.getParameter("phone");
			String name = request.getParameter("name");
			//验证码
			String code = request.getParameter("code");
			String parent = request.getParameter("parent");
			
			if (openId == null)
				new Result<SunZyTree>("请先关注",null);
			SunWechatUser wechat = wechatService.findOne(openId);
			
			if (wechat  == null || (wechat.getSubscribe()!= null && wechat.getSubscribe() == 1))
				new Result<SunZyTree>("请先关注",null);
			
			SunZyTree old = treeService.findSunZyTreeByUserPhone(phone);
			
			if(old != null){
				new Result<SunZyTree>("已领取",null);
			}
			
			SunGySms sms =sunSmsService.getSmsByPhoneAndCodeAndCreateTime(phone, code, new Date(), "");
			
			if (sms == null)
				new Result<SunZyTree>("验证码错误",null);
			
			SunZyTree news = treeService.findOneNewTree();
			
			if(old != null){
				new Result<SunZyTree>("已经领取完了",null);
			}
			
			news.setBindingTime(new Date());
			news.setUserName(name);
			news.setUserPhone(phone);
			news.setOpenid(openId);
			news.setStatus(1);
			if(parent != null)
			news.setParentId(parent);
			
			treeService.save(news);
			
			return new Result<SunZyTree>(null,news);
		
		} catch (Exception e) {
			return new Result<SunZyTree>("服务异常",null);
		}
	}
	
	/**  
	 * 免费领取阳光卡
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月8日 下午4:52:05 
	 */
	@RequestMapping(value = "freeSunCard")
	public String freeSunCard(Model model,HttpServletRequest request) {
		//token验证
		String s = UUID.randomUUID().toString();
		String chance = (String) request.getSession().getAttribute("chance");
		if(chance==null){
			request.getSession().setAttribute("cardToken",s);
			request.getSession().setAttribute("chance","1");
		}
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() == 2)){
			request.setAttribute("login", "no");
	   	}else{
	   		String wphone = w.getPhone();
			if(wphone==null){
				return baseView()+"/getFreeTCardbandding";
			}
			
			Result<SunCard> r = sunCardService.sunTCardBandding(w.getId(),w.getId());
			
			if(r.getState()==1){
				model.addAttribute("tcard", r.getT());
			}else{
				model.addAttribute("message", r.getMessage());
			}
	   	}
		
		return baseView()+"/freeSunCard";
	}
	/**  
	 * 绑定手机
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月18日 下午3:08:53 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "phoneBandding")
	public Result<String> phoneBandding(Model model,HttpServletRequest request,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "code") String code){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() == 2)){
			request.setAttribute("login", "no");
			return new Result<String>("请先登录","");
	   	}

		String wphone = w.getPhone();
		if(wphone!=null){
			
			return new Result<String>("","已经绑定");
		}
			//判断验证码 是否有效
		SunGySms sms=sunSmsService.getSmsByPhoneAndCodeAndCreateTime(phone,code,new Date(new Date().getTime() - 1000*60*30),"1" );
		if(sms==null){
			return new Result<String>("验证码无效","");
		}
		w = wechatService.findOne(w.getId());
		w.setPhone(phone);
		w.setUpdateTime(new Date());
		wechatService.save(w);
		
		return new Result<String>("","绑定成功");
	}
	
	
}