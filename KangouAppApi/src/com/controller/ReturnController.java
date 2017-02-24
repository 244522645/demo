package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReturnController extends BaseController {
	@RequestMapping("return")
	private String topage(String url, HttpServletRequest request) {
		return url;
	}
}
