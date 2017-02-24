package com.ybt.web.wechat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *   预订提交 -管理
 * */
@Controller
@RequestMapping(value = "/wechat/v2/relation")
public class RelationController {

	private String baseView(String v) {
		return "/work/wechat/v2.0/relation/"+v;
	}
	
	/*
	 *城市列表 页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {
		
		return baseView("index");
	}
	
}