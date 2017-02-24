package com.ybt.web.console.zy;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.model.work.SunDownRecord;
import com.ybt.service.base.IBaseService;
import com.ybt.service.work.SunDownRecordService;
import com.ybt.web.base.BaseController;

/*
 * 下载原图 纪录
 */
@Controller
@RequestMapping(value = "/console/downRecord")
public class DownRecordController extends BaseController<SunDownRecord,String> {
	
	private Logger logger = Logger.getLogger(getClass());
	private static final int BUFFER_SIZE = 100 * 1024;
	@Autowired
	public SunDownRecordService baseService;
	@Resource
	public Map<String,String> constant;

	public IBaseService<SunDownRecord, String> getBaseService() {
		return baseService;
	}

	public String baseView() {
		logger.info("console/work");
		return "/console/work/";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize
			){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, PropertyFilter.FILTER_PREFIX));
		com.ybt.common.util.Page<SunDownRecord> pageUtil = new com.ybt.common.util.Page<SunDownRecord>();
		pageUtil.setPageNo(pageNumber);
		pageUtil.setPageSize(pageSize);
		pageUtil.orderBy("createTime");
		pageUtil.order("desc");
		baseService.findAll(filters, pageUtil);
		model.addAttribute("page",pageUtil);
		return baseView()+"downRecordList";
	}
}
