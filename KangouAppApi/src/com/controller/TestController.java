package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.TestService;

@Controller
public class TestController {
	@Autowired(required = false)
	private TestService testService;

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	@RequestMapping("test")
	@ResponseBody
	public String test() {
		
		return getTestService().test();
	}
}
