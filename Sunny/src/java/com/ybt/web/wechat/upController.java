package com.ybt.web.wechat;

import java.util.Date;
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
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyImages;
import com.ybt.model.work.SunZyPhotoCover;
import com.ybt.service.work.IMemberTjService;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.ImagesService;
import com.ybt.service.work.PhotoCovorService;

import wechat.api.MediaAPI;
import wechat.support.TokenManager;

/**   
 * 投稿 模块
 * @author AndyBao  
 * @version 4.0, 2016年7月25日 下午3:59:01   
 */   
@Controller
@RequestMapping(value = "/wechat/my/up")
public class upController {
	
	@Autowired
	public ImagesService imagesService;
	@Autowired
	public PhotoCovorService photoCovorService;
	@Autowired
	public IWechatService wechatService;
	@Autowired
	public IMemberTjService tjService;
	private String baseView(){
		return "/work/wechat/my/";
	}
	
	/**  
	 * 投稿页面
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月21日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String up(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		
		return baseView()+"select";
	}
	
	/**  
	 * 投稿页面-提交 
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月21日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.GET,value = "input")
	public String input(Model model,HttpServletRequest request,@RequestParam(value = "localIds") String localIds) {
			model.addAttribute("localIds", localIds);
		return baseView()+"upload";
	}
	
	/**  
	 * 投稿页面-提交 
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月22日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.POST,value = "input")
	@ResponseBody
	public Result<SunZyImages> inputPost(Model model,HttpServletRequest request,
			@RequestParam(value = "serverId") String serverId,
			@RequestParam(value = "imgDate") String imgDate,
			@RequestParam(value = "imgAddress") String imgAddress,
			@RequestParam(value = "imgStory") String imgStory) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			return new Result<SunZyImages>("投稿失败,请先关注",null);
	   	}
		
		Result<SunZyImages> result = imagesService.downImgByte(MediaAPI.mediaGet(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), serverId),"cover");
		
		if(result.getState()==1){
			SunZyPhotoCover photo = new SunZyPhotoCover();
			photo.setCreateTime(new Date());
			photo.setWorkerId("");
			photo.setTitle(imgAddress);
			photo.setShootingTime(DateUtil.StringToDate(imgDate,"yyyy-MM-dd"));
			photo.setAddress(imgAddress);
			photo.setStory(imgStory);
			photo.setImgId(result.getT());
			photo.setUpdateTime(new Date());
			photo.setCameristId(w.getId());
			
			photoCovorService.save(photo);
			
			//计算统计
			tjService.addTougao(w.getId(), 0);
		}
		
		return result;
	}
	
	/**  
	 * 下载微信图片
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月21日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.GET,value = "down")
	public String down(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		
		return baseView()+"upload";
	}
	
	/**  
	 * 投稿列表
	 * @param model
	 * @param request
	 * @param pageNumber
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年7月21日 下午2:15:57 
	 */
	@RequestMapping(method = RequestMethod.GET,value = "list")
	public String myList(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			return baseView()+"upList";
	   	}
		
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
    	searchParams.put("EQS_cameristId", w.getId());//发布
 		com.ybt.common.util.Page<SunZyPhotoCover> pageUtil = new com.ybt.common.util.Page<SunZyPhotoCover>();
		pageUtil.setPageNo(pageNumber);
    	
		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		
		pageUtil.setOrderBy("shootingTime");
		pageUtil.setOrder("desc");
		Pageable pager =new PageRequest(pageUtil.getPageNo()-1, pageUtil.getPageSize()); 
		
		Page<SunZyPhotoCover> page = photoCovorService.findSunZyPhotoCoverByProperty(filters, pager);
		pageUtil.setTotalCount(page.getTotalElements());
		pageUtil.setResult(page.getContent());
		model.addAttribute("page", pageUtil);
		
		
		return baseView()+"upList";
	}
	
	/**
	 * ajax加载列表
	 * */
	@RequestMapping(value = "/myListPage", method = RequestMethod.GET)
	@ResponseBody
	public com.ybt.common.util.Page<SunZyPhotoCover> myListPage(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value ="page") int page) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			return null;
	   	}
		
    	Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
    	searchParams.put("EQS_cameristId", w.getId());//发布
    	com.ybt.common.util.Page<SunZyPhotoCover> pageUtil1 = new com.ybt.common.util.Page<SunZyPhotoCover>();
		pageUtil1.setOrderBy("shootingTime");
		pageUtil1.setOrder("desc");
		pageUtil1.setPageNo(page);

		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		Pageable pager =new PageRequest(pageUtil1.getPageNo()-1, pageUtil1.getPageSize()); 
		
		Page<SunZyPhotoCover> page1 = photoCovorService.findSunZyPhotoCoverByProperty(filters, pager);
		pageUtil1.setResult(page1.getContent());
		pageUtil1.setTotalCount(page1.getTotalElements());
		return pageUtil1;
	}
	
}