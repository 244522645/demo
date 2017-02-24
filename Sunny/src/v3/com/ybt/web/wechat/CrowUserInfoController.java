package com.ybt.web.wechat;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.util.PrintScreenUtils;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.aop.Authorization;
import com.ybt.service.work.CrowPkOneService;
import com.ybt.service.work.CrowPunchComputeService;
import com.ybt.service.work.CrowUserInfoService;

import wechat.util.WXUtil;

@Controller
@RequestMapping("/wechat/v3/crow")
public class CrowUserInfoController {  
	@Autowired(required = false)
	private CrowUserInfoService crowUserInfoService;
	
	@Autowired(required = false)
	private CrowPunchComputeService crowPunchComputeService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private HashMap constant;
	public CrowUserInfoService getCrowUserInfoService() {
		return crowUserInfoService;
	}

	public void setCrowUserInfoService(CrowUserInfoService crowUserInfoService) {
		this.crowUserInfoService = crowUserInfoService;
	}

	@Autowired(required = false)
	private CrowPkOneService crowPkOneService;

	public CrowPkOneService getCrowPkOneService() {
		return crowPkOneService;
	}

	public void setCrowPkOneService(CrowPkOneService crowPkOneService) {
		this.crowPkOneService = crowPkOneService;
	}

	private String baseView(String v) {
		return "/work/wechat/v3/index/" + v;
	}

	@RequestMapping(method = RequestMethod.GET, value = "findAll")
	@ResponseBody
	public String findAll(Model model, HttpServletRequest request, HttpServletResponse response) {
		List<CrowUserInfo> list = getCrowUserInfoService().findAll();
		System.out.println(list.get(0).getUser().getLanguage());
		return "1";
	}

	@RequestMapping(method = RequestMethod.GET, value = "xxxxx")
	@ResponseBody
	public String xxxxxx(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		crowPunchComputeService.punchCompute();
		
		return "1";
	}

	/*
	 * 主页
	 */
	@RequestMapping(method = RequestMethod.GET, value = "index")
	@Authorization
	public String addUser(HttpServletRequest request, HttpServletResponse response) {

		 SunWechatUser w = (SunWechatUser)
		 request.getSession().getAttribute("userInfo");

		 CrowUserInfo crowUserInfo =
		 getCrowUserInfoService().findByUserID(w.getId());
		//CrowUserInfo crowUserInfo = getCrowUserInfoService().findByUserID("oGrGWs9BqgeuG2dimMn6y8cyeRPw");
		int count = crowUserInfo.getBalance().intValue() / 5;
		int winsize = getCrowPkOneService().findCrowUserWin(crowUserInfo.getUser().getId()).size();
		int nowuser = getCrowPkOneService().findByuserid(crowUserInfo.getUser().getId()).size();
		request.setAttribute("count", count);
		request.setAttribute("winsize", winsize);
		request.setAttribute("nowuser", nowuser);
		request.setAttribute("crowUserInfo", crowUserInfo);
		String shareindex = request.getParameter("shareindex");
		request.setAttribute("shareindex", shareindex==null?"":shareindex);
		
		request.setAttribute("share", WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/crow/share?userid="+w.getId(), false));
		if(crowUserInfo.getLevel()==0){
			return baseView("main");
		}
		    return baseView("index");
	}

	/**
	 * 分享页
	 * 
	 * @param userid
	 * @param request
	 * @param response
	 * @return 0代表成功，1代表失败，2代表下载时间间隔太短
	 * @author AndyBao
	 * @version V4.0, 2017年2月14日 下午7:01:07
	 */
	@RequestMapping(method = RequestMethod.GET, value = "shareQRcode")
	@ResponseBody
	public int shareQRcode(HttpServletRequest request, HttpServletResponse response) {
		int i = 0;
		try {
			SunWechatUser w = (SunWechatUser) request.getSession().getAttribute("userInfo");
			/*PrintScreenUtils.printUrlScreen2jpg("aaa.jpg", constant.get("domainName") + "/wechat/v3/crow/share?userid=" + w.getId(),
					w.getId(), 414, 736);*/
			PrintScreenUtils.printUrlScreen2jpg("aaa.jpg", "http://www.baidu.com",
					w.getId(), 414, 736);
		} catch (Exception e) {
			i = 1;
			e.printStackTrace();
		}

		return i;
	}

	/**
	 * 分享页
	 * 
	 * @param userid
	 * @param request
	 * @param response
	 * @return
	 * @author AndyBao
	 * @version V4.0, 2017年2月14日 下午7:01:07
	 */
	@RequestMapping(method = RequestMethod.GET, value = "share")
	
	public String share(String userid,HttpServletRequest request, HttpServletResponse response) {
		

		CrowUserInfo crowUserInfo = getCrowUserInfoService().createTempQrcode(userid);
		int count = crowUserInfo.getBalance().intValue() / 5;
		int winsize = getCrowPkOneService().findCrowUserWin(crowUserInfo.getUser().getId()).size();
		int nowuser = getCrowPkOneService().findByuserid(crowUserInfo.getUser().getId()).size();
		request.setAttribute("count", count);
		request.setAttribute("winsize", winsize);
		request.setAttribute("nowuser", nowuser);
		request.setAttribute("crowUserInfo", crowUserInfo);
		return baseView("share");
	}

	/*
	 * 获取微信图片并转化为base64 public String httpRequestIO(String requestUrl) {
	 * InputStream inputStream = null; byte[] data = null; try { URL url = new
	 * URL(requestUrl); HttpURLConnection httpUrlConn = (HttpURLConnection)
	 * url.openConnection(); httpUrlConn.setDoInput(true);
	 * httpUrlConn.setRequestMethod("GET"); httpUrlConn.connect(); // 获得返回的输入流
	 * inputStream = httpUrlConn.getInputStream(); data = new
	 * byte[inputStream.available()]; inputStream.read(data); BASE64Encoder
	 * encoder = new BASE64Encoder(); return encoder.encode(data); } catch
	 * (Exception e) { e.printStackTrace(); } return "";
	 * 
	 * }
	 */

}
