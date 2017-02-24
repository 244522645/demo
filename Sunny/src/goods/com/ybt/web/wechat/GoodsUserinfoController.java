package com.ybt.web.wechat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ybt.service.work.IWechatService;

/**
 *   用户收货信息 - 管理
 * */
@Controller
@RequestMapping(value = "/wechat/goods/userinfo")
public class GoodsUserinfoController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(GoodsUserinfoController.class);
	@Autowired
	public IWechatService wechatService;
	private String baseView() {
		return "/work/wechat/bless";
	}
}