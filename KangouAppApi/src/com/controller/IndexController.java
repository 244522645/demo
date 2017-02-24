package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Version;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {
	/*
	 * @author (user) 张卫恒
	 * 
	 * @注释：(cursor) 只写了非空验证，没有做具体判断
	 */
	@RequestMapping(value = "index.do",method=RequestMethod.POST)
	public String toindex(String phone, String time, String imsi, String token, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("sessionID:"+request.getSession().getId());
		log.info("跳转首页" + phone + "时间" + time + "手机串码" + imsi + "md5效验" + token);
		if (token == null || token.equals("")) {
			request.setAttribute("messageid", "1701");
			request.setAttribute("message", "效验错误");

			return "404";
		}
		if (phone == null || phone.equals("")) {
			request.setAttribute("messageid", "1702");
			request.setAttribute("message", "手机号错误");

			return "404";
		}
		if (time == null || time.equals("")) {
			request.setAttribute("messageid", "1703");
			request.setAttribute("message", "时间错误");
			return "404";
		} else {
			Date date = new Date();
			Long min = ((Long.parseLong(date.getTime() + "") - Long.parseLong(time))) / (1000 * 60);
			if (-30 >= min || min >= 30) {
				request.setAttribute("messageid", "1704");
				request.setAttribute("message", "请于北京时间校准");
				return "404";
			}
		}
		if (imsi == null || imsi.equals("")) {
			request.setAttribute("messageid", "1705");
			request.setAttribute("message", "手机串码错误");

			return "404";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(phone);
		buffer.append(time);

		boolean isture = utils.equalsMD5(buffer.toString(), imsi, token);
		if (!isture) {
			request.setAttribute("messageid", "1706");
			request.setAttribute("message", "效验码效验错误");

			return "404";
		}

		return "index";
	}

	@RequestMapping(value="checkVersion")
	@ResponseBody
	public Map<String, String> checkVersion(String mobileType, String version, HttpServletRequest request) {
		log.info("更新"+mobileType+"手机版本号"+version);
		Version versions;
		Map<String, String> map = new HashMap<String, String>();
		if (mobileType != null && !mobileType.equals("")) {
			versions = getLoginService().checkVersion(mobileType);
		} else {
			map.put("status", "2002");
			map.put("message", "手机类型错误");
			map.put("version", "");
			map.put("url", "");
			map.put("forced", "");
			return map;
		}
		double dversion = 0;
		if (version != null && !version.equals("")) {
			try {
				dversion = Double.parseDouble(version);
			} catch (NumberFormatException e) {
				map.put("status", "2003");
				map.put("message", "手机版本错误");
				map.put("version", "");
				map.put("url", "");
				map.put("forced", "");
				e.printStackTrace();
				return map;
			}
		} else {
			map.put("status", "2003");
			map.put("message", "手机版本错误");
			map.put("version", "");
			map.put("url", "");
			map.put("forced", "");
			return map;
		}
		Double versionnum = Double.parseDouble(versions.getVersionnum());
		if (versionnum <= dversion) {
			map.put("status", "2000");
			map.put("message", "手机版本已是最新");
			map.put("version", "");
			map.put("url", "");
			map.put("forced", "");
			return map;
		}
		if (versionnum > dversion) {
			map.put("status", "2001");
			map.put("message", "请更新手机版本");
			map.put("version", versions.getVersionnum());
			map.put("url", versions.getUrl());
			map.put("forced", versions.getForced() + "");
			return map;
		}
		return map;

	}
}
