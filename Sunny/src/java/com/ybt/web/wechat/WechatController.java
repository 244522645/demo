package com.ybt.web.wechat;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.WXCheckModel;
import com.ybt.common.constant.Wechat;
import com.ybt.service.work.WXCoreService;

import wechat.api.ShorturlAPI;
import wechat.bean.Shorturl;
import wechat.support.TokenManager;
import wechat.util.WXUtil;

/**
 * 微信
 * */
@Controller
@RequestMapping(value = "/wechat/core")
public class WechatController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(WechatController.class);
	@Autowired
	private WXCoreService coreService;
	/**
	 * 开发者模式token校验
	 * 
	 * @param wxAccount
	 *            开发者url后缀
	 * @param response
	 * @param tokenModel
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "text/plain")
	public @ResponseBody
	String validate(HttpServletRequest request, WXCheckModel tokenModel)
			throws ParseException, IOException {
		System.out.println("core get");

		return coreService.validate(Wechat.TOKEN, tokenModel);
	}

	/**
	 * 开发者模式消息处理
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	public @ResponseBody String core(HttpServletRequest request, HttpServletResponse response)
			 {
		try {
			return coreService.processRequest(Wechat.TOKEN, request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * jsSDK config
	 */
	@RequestMapping(value="/jssdk",method = RequestMethod.POST)
	public @ResponseBody String jssdk(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String viewUrl = request.getParameter("viewUrl");
		return WXUtil.WXConfig(request, null, viewUrl);
	}
		
	/**
	 * 微信短域名
	 */
	@RequestMapping(value="/shortUrl",method = RequestMethod.POST)
	public @ResponseBody String shortUrl(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String url = request.getParameter("url");
		Shorturl viewUrl = ShorturlAPI.shorturl(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), url);
		
		if(viewUrl.getShort_url()!=null){
			return viewUrl.getShort_url();
		}
		return "";
	}
	/**
	 * 微信授权登录url
	 */
	@RequestMapping(value="/OAuthUrl",method = RequestMethod.POST)
	public @ResponseBody String OAuthUrl(HttpServletRequest request, HttpServletResponse response)
			 {
		String url = request.getParameter("url");
		String wxinfoObject =  (String)request.getSession().getAttribute("wxinfo");
		String wxlogin =  (String)request.getSession().getAttribute("wxlogin");
		boolean sns = false;
		if("true".equals(wxlogin)&&"false".equals(wxinfoObject)){
			sns = true;
		}
		String viewUrl = WXUtil.getOAuthUrl(url, sns);
		return viewUrl;
	}
}
