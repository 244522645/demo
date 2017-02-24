package com.ybt.web.wechat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.model.work.SunAttraction;
import com.ybt.service.work.AttractionService;

/**
 *   地址管理
 * */
@Controller
@RequestMapping(value = "/wechat/v2/attraction")
public class AttractionController {

	
	@Autowired
	private AttractionService attractionService;
	
	private String baseView(String v) {
		return "/work/wechat/v2.0/citys/"+v;
	}
	/*
	 *城市列表
	 */
	@RequestMapping(value="data",method = RequestMethod.GET)
	@ResponseBody
	public List<SunAttraction> city(Model model,HttpServletRequest request) {
		
		
		return attractionService.getCityListByGroupByCity();
	}
	
	/*
	 *城市列表 页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {
		
		 model.addAttribute("citys", attractionService.getCityListByGroupByCity());
		return baseView("select");
	}
}