package com.ybt.web.wechat;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.model.work.SunSearchKeywords;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.PhotoService;
import com.ybt.service.work.SearchKeywordsService;

/**
 * 微信
 * */
@Controller
@RequestMapping(value = "/wechat/index")
public class SearchController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(SearchController.class);
	
	@Autowired
	@Qualifier("photoService")
	private PhotoService photoService;
	@Autowired
	public IWechatService wechatService;
	@Autowired
	public SearchKeywordsService searchKeywordsService;
	
	private String baseView() {
		return "/work/wechat";
	}
	/*
	 *首页 搜索
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request,
			@RequestParam(value = "search", defaultValue = "") String search) {
		model.addAttribute("search", search);
		return baseView()+"/search";
	}
	/*
	 *首页 搜索
	 */
	@RequestMapping(value = "/search/put", method = RequestMethod.GET)
	public String search(Model model,HttpServletRequest request,
			@RequestParam(value = "search", defaultValue = "") String search) {
		
		model.addAttribute("search", search);
		String selectDate = request.getParameter("selectDate");
		
		if(selectDate!=null){
			model.addAttribute("selectDate", selectDate);
		}
		SunSearchKeywords keywords = new SunSearchKeywords();
		keywords.setCreateTime(new Date());
		keywords.setDeleted(0);
		keywords.setKeywords(search);
		keywords.setSelectDate(selectDate);
		keywords.setType(0);
		searchKeywordsService.save(keywords);
		
		return baseView()+"/searchResult";
	}
	/**
	 * ajax加载列表
	 * */
	@RequestMapping(value = "/search/photoPages", method = RequestMethod.GET)
	@ResponseBody
	public Page<SunZyPhoto> getPhoto(Model model,HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value ="page",defaultValue="1") int page,
							@RequestParam(value ="pageSize",defaultValue="10") int pageSize,
						   @RequestParam(value ="search",defaultValue="") String search,@RequestParam(value ="date",required = false) String date) {
		Map<String, Object> searchParams1 = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
    	if(!"".equals(search)){
    		String[] searchArray=search.split(" ");
    		search = searchArray[0];
    	}
    	if(!"".equals(date)){
    		String[] arrayD = date.split("-");
    		if(arrayD.length==3){
    			if(arrayD[1].length()<2)
    				arrayD[1] = '0'+arrayD[1];
    			if(arrayD[2].length()<2)
    				arrayD[2] = '0'+arrayD[2];
    			date = arrayD[0]+'-'+arrayD[1]+'-'+arrayD[2];
    		}
    		
    		searchParams1.put("LIKES_shootingTime",date);
    	}
//    	//分类 升旗时 不要搜索关键字
//    	if(subject!=null&&subject.equals("1"))
//    		search="";
    	//searchParams1.put("EQS_subject", subject);
    	com.ybt.common.util.Page<SunZyPhoto> pageUtil1 = new com.ybt.common.util.Page<SunZyPhoto>();
		pageUtil1.setOrderBy("shootingTime");
		pageUtil1.setOrder("desc");
		pageUtil1.setPageNo(page);
		pageUtil1.setPageSize(pageSize);


		List<PropertyFilter> filters1 = PropertyFilter.buildFrom(searchParams1);
		Pageable pager1 =new PageRequest(pageUtil1.getPageNo()-1, pageUtil1.getPageSize()); 
		
		Page<SunZyPhoto> page1 = photoService.findSunZyPhotoByProperty(search,filters1, pager1);
		return page1;
	}
	
}