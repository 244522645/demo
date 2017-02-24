package com.ybt.web.console.sistatistic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ybt.common.util.Page;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.model.work.SunGlStatisticSql;
import com.ybt.service.admin.StatisticService;


@Controller
@RequestMapping(value = "/console/statistic")
public class SistatisticController {
	
	@Autowired
	private StatisticService statisticService;

	@RequestMapping(method = RequestMethod.GET)
	public String statisticView(Model model, HttpServletRequest request) {
		List<SunGlStatisticSql> sqlList= statisticService.getAllStatisticSql();
		model.addAttribute("sqlList", sqlList);
		return "/console/statistic/statistic";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET,value="getStatistic")
	public String getStatistic(Model model, HttpServletRequest request,
			@RequestParam(value = "sortType", defaultValue = "") String sortType,
			@RequestParam(value = "order", defaultValue = "") String order,
			@RequestParam(value = "page", defaultValue = "1") String pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") String pageSize) throws Exception {
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, PropertyFilter.FILTER_PREFIX));
		String sqlCode=(String) searchParams.get("EQS_sqlCode");
		List<SunGlStatisticSql> sqlList = statisticService.getAllStatisticSql();
		if(sqlList == null)
			return "/console/statistic/statisticList";
		 sqlCode =  sqlCode == null ? sqlList.get(0).getSqlCode() : sqlCode;
		 
		Page<T> page = new Page<T>();
		if (pageNo != null && !pageNo.isEmpty())
			page.setPageNo(Integer.parseInt(pageNo));
		if (pageSize != null && !pageSize.isEmpty())
			page.setPageSize(Integer.parseInt(pageSize));
		page.setOrder(order);
		page.setOrderBy(sortType);
		model.addAttribute("order", order);
		model.addAttribute("sortType", sortType);
		Map<String,String> header = (Map<String,String>) new ListOrderedMap();
		page.setResult((List<T>) statisticService.findBySqlName4MapResult(sqlCode, null,page,header));
		
		model.addAttribute("sqlList", sqlList);
		model.addAttribute("tableHeader", header);
		model.addAttribute("page", page);
		return "/console/statistic/statisticList";
	}
	
	/**
	 *@description  导出
	 *@time    创建时间:2016年5月5日下午2:43:22
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@RequestMapping(method = RequestMethod.GET,value="exportExcel")
	public String exportExcel( HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		String sqlCode=(String) searchParams.get("EQS_sqlCode");
		if(sqlCode == null)
			return null;
		 
		this.statisticService.exportExcel(sqlCode, null, response);
		return null;
	}
}
