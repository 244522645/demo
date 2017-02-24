package com.ybt.web.console.zy;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.model.work.CrowPunch;
import com.ybt.service.base.IBaseService;
import com.ybt.service.work.CrowPunchService;
import com.ybt.web.base.BaseController;

/*
 * 打卡审核
 */
@Controller
@RequestMapping(value = "/console/punch")
public class PunchCheckController extends BaseController<CrowPunch,String> {
	
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	public CrowPunchService crowPunchService;
	@Resource
	public Map<String,String> constant;

	public IBaseService<CrowPunch, String> getBaseService() {
		return crowPunchService;
	}

	public String baseView() {
		logger.info("console/work");
		return "/console/work/crow";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize
			){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		searchParams.put("EQS_status",0);
		searchParams.put("EQS_iscompute",0);
		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, PropertyFilter.FILTER_PREFIX));
		com.ybt.common.util.Page<CrowPunch> pageUtil = new com.ybt.common.util.Page<CrowPunch>();
		pageUtil.setPageNo(pageNumber);
		pageUtil.setPageSize(pageSize);
		pageUtil.orderBy("createTime");
		pageUtil.order("desc");
		crowPunchService.findAll(filters, pageUtil);
		model.addAttribute("page",pageUtil);
		return baseView()+"/list";
	}
	
	//打卡审核
	@RequestMapping(value = "/pass",method=RequestMethod.POST)
	@ResponseBody
	public Result<String> upImg(Model model,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="pid") String pid,
			@RequestParam(value="pass") Boolean pass) {

		Result<CrowPunch>  resilt = crowPunchService.checkPunch(pid, pass);
		
		if(resilt.getState()==1)
			return new Result<String>("","通过");
		
		return new Result<String>("失败","");
	}
}
