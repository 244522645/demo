package com.ybt.web.wechat;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.model.work.SunCelebrityBirthday;
import com.ybt.service.work.SunCelebrityBirthdayService;

/**
 * 名人生日
 * 
 */
@Controller
@RequestMapping(value = "/wechat/cbirthday")
public class CelebrityBirthdayController {

	@Autowired
	private SunCelebrityBirthdayService celebrityBirthdayService;
	
	
	@RequestMapping(value="/day",method = RequestMethod.POST)
	@ResponseBody
	public SunCelebrityBirthday index(Model model,HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		SunCelebrityBirthday cb = celebrityBirthdayService.findByBirthday("%"+sdf.format(new Date())+"%");
		return cb;
	}
}
