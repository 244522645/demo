package com.ybt.web.wechat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/wechat/v2/loading")
public class WxLoginController {

	
	private String baseView(String v) {
		return "/work/wechat/v2.0/"+v;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {
		
		return baseView("loading");
	}
}