package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.KCardKinds;

@Controller
@RequestMapping("/KCardKinds")
public class KCardKindsController extends BaseController {
	// 查询所有卡的类型
	@RequestMapping("findall")
	@ResponseBody
	private List<KCardKinds> findall() {
		System.out.println(getCardKindsService().findall().size());
		return getCardKindsService().findall();
	}
}
