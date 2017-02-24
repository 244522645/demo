package com.ybt.web.wechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.exception.MobileException;
import com.ybt.common.util.PropertiesUtil;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.common.util.StringUtil;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.PhotoService;
import com.ybt.service.work.SunBlessService;
import com.ybt.service.work.SunScGoodService;

import wechat.util.WXUtil;

/**
 * 微信
 * */
@Controller
@RequestMapping(value = "/wechat/index")
public class IndexController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(IndexController.class);
	
	@Autowired
	@Qualifier("photoService")
	private PhotoService photoService;
	@Autowired
	@Qualifier("sunBlessService")
	private SunBlessService blessService;
	@Autowired
	private SunScGoodService sunScGoodService;
	@Autowired
	public IWechatService wechatService;
	
	private String baseView() {
		return "/work/wechat";
	}
	
	/*
	 *首页 列表
	 */
	@RequestMapping
	public String index(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "city", defaultValue = "") String city,
			@RequestParam(value = "date", defaultValue = "") String date) {
		
		try{
	
			//日出
	    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
	    	if(!"".equals(city))
	    	searchParams.put("EQS_city",city);
	    	if(!"".equals(date))
	    	searchParams.put("EQS_shootingTime",date);
	    	searchParams.put("EQS_subject", "日出");
	/*    	searchParams.put("EQS_released", 1);//发布
	*/    	com.ybt.common.util.Page<SunZyPhoto> pageUtil = new com.ybt.common.util.Page<SunZyPhoto>();
			pageUtil.setPageNo(pageNumber);
	    	
			List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
			Pageable pager =new PageRequest(pageUtil.getPageNo()-1, pageUtil.getPageSize()); 
			
			Page<SunZyPhoto> page = photoService.findSunZyPhotoByProperty(filters, pager);
			pageUtil.setTotalCount(page.getTotalElements());
			pageUtil.setResult(page.getContent());
			pageUtil.setOrderBy("shootingTime");
			pageUtil.setOrder("desc");
			model.addAttribute("page", pageUtil);
			
			//升旗
			Map<String, Object> searchParams1 = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
	    	if(!"".equals(city))
	    		searchParams1.put("EQS_city",city);
	    	if(!"".equals(date))
	    		searchParams1.put("EQS_shootingTime",date);
	    	searchParams1.put("EQS_subject", "升旗");
	/*    	searchParams.put("EQS_released", 1);//发布
	*/    	com.ybt.common.util.Page<SunZyPhoto> pageUtil1 = new com.ybt.common.util.Page<SunZyPhoto>();
			pageUtil1.setPageNo(pageNumber);
	    	
			List<PropertyFilter> filters1 = PropertyFilter.buildFrom(searchParams1);
			Pageable pager1 =new PageRequest(pageUtil1.getPageNo()-1, pageUtil1.getPageSize()); 
			
			Page<SunZyPhoto> page1 = photoService.findSunZyPhotoByProperty(filters1, pager1);
			pageUtil1.setTotalCount(page1.getTotalElements());
			pageUtil1.setResult(page1.getContent());
			pageUtil1.setOrderBy("shootingTime");
			pageUtil1.setOrder("desc");
			model.addAttribute("page1", pageUtil1);
			
			
			String url = PropertiesUtil.getProperty("domainName")+ "/wechat/me";
			//分享链接   微信允许获取用户信息的链接  非静默 （snsapi_userinfo）
			String shareUrl = WXUtil.getOAuthUrl(url, false);
			model.addAttribute("shareUrl", shareUrl);
			
			//首页推荐 滚动
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			List<SunBless> blesslist = blessService.getAllBlessListByRec(1, 5);
			//model.addAttribute("blessRec", blessService.getAllBlessListByRec(1, 5));
			for (SunBless bless : blesslist) {
				Map<String,String> recmap = new HashMap<String,String>();
				recmap.put("title", StringUtil.copyCat(bless.getSender()) +"** 为 "+StringUtil.copyCat(bless.getReceiver()) +"** 送祝福");
				recmap.put("content", bless.getBless());
				list.add(recmap);
			}
			model.addAttribute("blessRec", list);
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		return baseView()+"/v2.0/index";
		
	}
	
	/**
	 * ajax加载列表
	 * */
	@RequestMapping(value = "/photoPages", method = RequestMethod.GET)
	@ResponseBody
	public Page<SunZyPhoto> getPhoto(Model model,HttpServletRequest request, HttpServletResponse response,
						   @RequestParam(value ="subject" ,defaultValue="") String subject,
						   @RequestParam(value ="page",defaultValue="2") int page,
						   @RequestParam(value ="pageSize",defaultValue="10") int pageSize,
						   @RequestParam(value ="city",required = false) String city,
						   @RequestParam(value ="date",required = false) String date) {
		Map<String, Object> searchParams1 = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		/*if(!"".equals(city)&&city!=null)
    		searchParams1.put("EQS_city",city);
    	if(!"".equals(date))
    		searchParams1.put("EQS_shootingTime",date);*/
		if(!"".equals(subject)){
    		searchParams1.put("EQS_subject", subject);
    	}
/*    	searchParams.put("EQS_released", 1);//发布
*/    	com.ybt.common.util.Page<SunZyPhoto> pageUtil1 = new com.ybt.common.util.Page<SunZyPhoto>();
		pageUtil1.setOrderBy("shootingTime");
		pageUtil1.setOrder("desc");
		pageUtil1.setPageNo(page);
		pageUtil1.setPageSize(pageSize);

		List<PropertyFilter> filters1 = PropertyFilter.buildFrom(searchParams1);
		Pageable pager1 =new PageRequest(pageUtil1.getPageNo()-1, pageUtil1.getPageSize()); 
		
		Page<SunZyPhoto> page1 = photoService.findSunZyPhotoByProperty(filters1, pager1);
		return page1;
	}
	
	/*
	 *查看照片
	 */
	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	public String myCar(Model model,HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) {
		
		SunZyPhoto p = photoService.findOne(id);
		model.addAttribute("photo", p);
//		带出 上一条 下一条 id
		//分享链接
	//	String url = PropertiesUtil.getProperty("domainName")+ "/wechat/index/photo?id="+id;
		//String WXConfig = WXUtil.WXConfig(request,null,url);
		//model.addAttribute("WXConfig",WXConfig);//微信JSSDK
		
		String url = PropertiesUtil.getProperty("domainName")+ "/wechat/index/greetingCard?id="+id+"&classId=T001";
		//分享链接   微信允许获取用户信息的链接  非静默 （snsapi_userinfo）
		String shareUrl = WXUtil.getOAuthUrl(url, false);
		model.addAttribute("shareUrl", shareUrl);
		
		return "work/wechat/show";
	}
	
	/*
	 *查看照片
	 */
	@RequestMapping(value = "greetingCard", method = RequestMethod.GET)
	public String greetingCard(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
		String classId ="T001";
		String id =request.getParameter("id");
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null){
			model.addAttribute("content", "");
			return baseView()+"/order/wechat";//关注给点儿阳光
	   	}
		w = wechatService.findOne(w.getId());
		if( w.getSubscribe() != 1){
//			return baseView()+"/order/wechat";//关注给点儿阳光
			request.setAttribute("login", "no");
		}
		
		List<Map<String, Object>> proList = null;
		try {
			proList = sunScGoodService.getScGoodPropertyValue(classId);
		} catch (Exception e) {
			throw new MobileException("贺卡生成失败");
		}

		String orderUrl =PropertiesUtil.getProperty("domainName")+"/wechat/pay/order";
		model.addAttribute("orderUrl", orderUrl);
		model.addAttribute("channel", "'wx_pub'");
		model.addAttribute("openId",w.getId());
				
		model.addAttribute("user", w);
		model.addAttribute("proList", proList);
		SunZyPhoto photo = photoService.findOne(id);
		model.addAttribute("photo", photoService.findOne(id));
		if(photo!=null){
			String story = photo.getStory();
			if(story!=null)
				model.addAttribute("story", story.replaceAll("[\\t\\n\\r]", ""));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "work/wechat/card_polaroid";
	}
	
}