package com.ybt.web.wechat;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.common.util.DateUtil;
import com.ybt.model.work.CrowPkMe;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.aop.Authorization;
import com.ybt.service.work.CrowPkMeService;
import com.ybt.service.work.CrowPunchService;
import com.ybt.service.work.CrowUserInfoService;

/**
 *  个人进度
 * */
@Controller
@RequestMapping(value = "/wechat/v3/crow/punch")
public class CrowPunchController {

	
	@Autowired
	private CrowPunchService crowPunchService;
	@Autowired
	private CrowUserInfoService crowUserInfoService;
	@Autowired CrowPkMeService crowPkMeService;
	
	private String baseView(String v) {
		return "/work/wechat/v3/crowpunch/"+v;
	}
	/*
	 *个人进度页面
	 */

	@RequestMapping(method = RequestMethod.GET)
	@Authorization
	public String index(Model model,HttpServletRequest request
			) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			return baseView("schedule");
	   	}
		
		try{
			String guiz21="<p>闻鸡起伍，连续5天早晨禅跑，<br>并于早9点之前，将禅跑记录发送到平台，<br>即为挑战成功！并获赠鸡蛋一枚！<br>鸡蛋会带来惊喜哟！</p>";
			String guiz5="<p>闻鸡起伍，连续5天早晨禅跑，<br>并于早9点之前，将禅跑记录发送到平台，<br>即为挑战成功！并获赠鸡蛋一枚！<br>鸡蛋会带来惊喜哟！</p>";
			String title="闻鸡起伍";
			
			CrowPkMe pkme = crowPkMeService.findByUserIdAndPkTypeAndPkLevel(w.getId(), 1, 1);
			CrowPkMe pk2me = crowPkMeService.findByUserIdAndPkTypeAndPkLevel(w.getId(), 1, 2);
			if(pk2me!=null){
				pkme=pk2me;
			}
			NumberFormat numberFormat = NumberFormat.getInstance(); 
			numberFormat.setMaximumFractionDigits(0);  
			model.addAttribute("pkme", pkme);
			model.addAttribute("mode", 0);
			if(pkme==null){
				model.addAttribute("mode", 0);
				model.addAttribute("day", 5);
				model.addAttribute("title", title);
				model.addAttribute("level", 1);
				 model.addAttribute("baifenbi", 0+"%");
				 model.addAttribute("result", 2);
				// return baseView("schedule");
			}
				
			if(pkme!=null&&pkme.getPkResult()==0&&pkme.getPkLevel()==1){
				model.addAttribute("result", 0);
				model.addAttribute("mode", 1);
				model.addAttribute("day", 5);
				model.addAttribute("completeday", pkme.getCompleteDay());
				 String result = numberFormat.format((float) pkme.getCompleteDay() / (float) 5 * 100);  
				 model.addAttribute("baifenbi", result+"%");
				 model.addAttribute("title", title);
				model.addAttribute("level", 1);
			}
				
			if(pkme!=null&&pkme.getPkResult()==0&&pkme.getPkLevel()==2){
				model.addAttribute("result", 0);
				model.addAttribute("mode", 1);
				model.addAttribute("day", 21);
				model.addAttribute("completeday", pkme.getCompleteDay());
				 String result = numberFormat.format((float) pkme.getCompleteDay() / (float) 21 * 100);  
				 model.addAttribute("baifenbi", result+"%");
				model.addAttribute("level", 2);
				model.addAttribute("title", "斗鸡初成");
			}
				
			if(pkme!=null&&pkme.getPkResult()==1&&pkme.getPkLevel()==1){
				model.addAttribute("mode", 1);
				model.addAttribute("result", 1);
				model.addAttribute("day", 5);
				model.addAttribute("completeday", pkme.getCompleteDay());
				 String result = numberFormat.format((float) pkme.getCompleteDay() / (float) 5 * 100);  
				 model.addAttribute("baifenbi", result+"%");
				model.addAttribute("level", 1);
				model.addAttribute("title", title);
			}
				
			if(pkme!=null&&pkme.getPkResult()==1&&pkme.getPkLevel()==2){
				model.addAttribute("result", 1);
				model.addAttribute("mode",1);
				model.addAttribute("level", 2);
				model.addAttribute("day", 5);
				model.addAttribute("completeday", pkme.getCompleteDay());
				 String result = numberFormat.format((float) pkme.getCompleteDay() / (float) 5 * 100);  
				 model.addAttribute("baifenbi", result+"%");
				model.addAttribute("title", "斗鸡初成");
			}
			if(pkme!=null&&pkme.getPkResult()==2&&pkme.getPkLevel()==1){
				model.addAttribute("result", 2);
				model.addAttribute("mode",0);
				model.addAttribute("level", 2);
				model.addAttribute("day", 5);
				model.addAttribute("completeday", pkme.getCompleteDay());
				 String result = numberFormat.format((float) pkme.getCompleteDay() / (float) 5 * 100);  
				 model.addAttribute("baifenbi", result+"%");
				model.addAttribute("title", "闻鸡起伍");
			}
			if(pkme!=null&&pkme.getPkResult()==2&&pkme.getPkLevel()==2){
				model.addAttribute("result", 2);
				model.addAttribute("mode",0);
				model.addAttribute("level", 2);
				model.addAttribute("day", 21);
				model.addAttribute("completeday", pkme.getCompleteDay());
				 String result = numberFormat.format((float) pkme.getCompleteDay() / (float) 21 * 100);  
				 model.addAttribute("baifenbi", result+"%");
				model.addAttribute("title", "斗鸡初成");
			}
		
		CrowUserInfo userInfo = crowUserInfoService.findByUserID(w.getId());
		model.addAttribute("crowUserInfo", userInfo);
		if(userInfo.getLevel()==0){
		//	CrowPkMe pkmes = crowPkMeService.findByUserIdAndPkTypeAndPkLevel(w.getId(), 1, 1);
			if(pkme==null)
				return "/work/wechat/v3/pkme/notpk";
			
			if(pkme.getPkResult()==0&&pkme.getPkLevel()==1)
				return "/work/wechat/v3/pkme/pk1failed";
			if(pkme.getPkResult()==1&&pkme.getPkLevel()==1)
				return "/work/wechat/v3/pkme/pk1success";
		}
		if(userInfo.getLevel()==1){
			
			//return "/work/wechat/v3/pkme/notpk";
		}
		
		/*
		if(pkme==null)
			return "/work/wechat/v3/pkme/notpk";
		if(pkme.getPkResult()==0&&pkme.getPkLevel()==1)
			return "/work/wechat/v3/pkme/pk1failed";
		if(pkme.getPkResult()==0&&pkme.getPkLevel()==2)
			return "/work/wechat/v3/pkme/pk2failed";
		if(pkme.getPkResult()==1&&pkme.getPkLevel()==1)
			return "/work/wechat/v3/pkme/pk1success";
		if(pkme.getPkResult()==1&&pkme.getPkLevel()==2)
			return "/work/wechat/v3/pkme/pk2success";*/
		
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		return baseView("schedule");
	}
	/*
	 *个人进度页面
	 */

	@RequestMapping(value="schedule", method = RequestMethod.GET)
	@Authorization
	public String schedule(Model model,HttpServletRequest request
			) {
		
		String guiz21="<p>闻鸡起伍，连续5天早晨禅跑，<br>并于早9点之前，将禅跑记录发送到平台，<br>即为挑战成功！并获赠鸡蛋一枚！<br>鸡蛋会带来惊喜哟！</p>";
		String guiz5="<p>闻鸡起伍，连续5天早晨禅跑，<br>并于早9点之前，将禅跑记录发送到平台，<br>即为挑战成功！并获赠鸡蛋一枚！<br>鸡蛋会带来惊喜哟！</p>";
		String title="闻鸡起伍";
		
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
			return baseView("schedule");
	   	}
		
		CrowUserInfo userInfo = crowUserInfoService.findByUserID(w.getId());
		model.addAttribute("crowUserInfo", userInfo);	
		
		CrowPkMe pkme = crowPkMeService.findByUserIdAndPkTypeAndPkStatus(w.getId(), 1,  1);
		
		
		NumberFormat numberFormat = NumberFormat.getInstance(); 
		numberFormat.setMaximumFractionDigits(0);  
		model.addAttribute("pkme", pkme);
		model.addAttribute("mode", 0);
		if(pkme==null){
			model.addAttribute("mode", 0);
			model.addAttribute("completeday", 0);
			 model.addAttribute("result", 2);
			model.addAttribute("title", title);
			model.addAttribute("level", 1);
			 model.addAttribute("baifenbi", 0+"%");
			 model.addAttribute("result", 2);
			 return baseView("schedule");
		}
			
		if(pkme.getPkResult()==0&&pkme.getPkLevel()==1){
			model.addAttribute("result", 0);
			model.addAttribute("mode", 1);
			model.addAttribute("day", 5);
			model.addAttribute("completeday", pkme.getCompleteDay());
			 String result = numberFormat.format((float) pkme.getCompleteDay() / (float) 5 * 100);  
			 model.addAttribute("baifenbi", result+"%");
			 model.addAttribute("title", title);
			model.addAttribute("level", 1);
		}
			
		if(pkme.getPkResult()==0&&pkme.getPkLevel()==2){
			model.addAttribute("result", 0);
			model.addAttribute("mode", 1);
			model.addAttribute("day", 21);
			model.addAttribute("completeday", pkme.getCompleteDay());
			 String result = numberFormat.format((float) pkme.getCompleteDay() / (float) 21 * 100);  
			 model.addAttribute("baifenbi", result+"%");
			model.addAttribute("level", 2);
			model.addAttribute("title", "斗鸡初成");
		}
			
		if(pkme.getPkResult()==1&&pkme.getPkLevel()==1){
			model.addAttribute("mode", 1);
			model.addAttribute("result", 1);
			model.addAttribute("day", 5);
			model.addAttribute("completeday", pkme.getCompleteDay());
			 String result = numberFormat.format((float) pkme.getCompleteDay() / (float) 5 * 100);  
			 model.addAttribute("baifenbi", result+"%");
			model.addAttribute("level", 1);
			model.addAttribute("title", title);
		}
			
		if(pkme.getPkResult()==1&&pkme.getPkLevel()==2){
			model.addAttribute("result", 1);
			model.addAttribute("mode",1);
			model.addAttribute("level", 2);
			model.addAttribute("day", 5);
			model.addAttribute("completeday", pkme.getCompleteDay());
			 String result = numberFormat.format((float) pkme.getCompleteDay() / (float) 5 * 100);  
			 model.addAttribute("baifenbi", result+"%");
			model.addAttribute("title", "斗鸡初成");
		}
		if(pkme.getPkResult()==2&&pkme.getPkLevel()==1){
			model.addAttribute("result", 2);
			model.addAttribute("mode",0);
			model.addAttribute("level", 2);
			model.addAttribute("day", 5);
			model.addAttribute("completeday", pkme.getCompleteDay());
			 String result = numberFormat.format((float) pkme.getCompleteDay() / (float) 5 * 100);  
			 model.addAttribute("baifenbi", result+"%");
			model.addAttribute("title", "斗鸡初成");
		}
		if(pkme.getPkResult()==2&&pkme.getPkLevel()==2){
			model.addAttribute("result", 2);
			model.addAttribute("mode",0);
			model.addAttribute("level", 2);
			model.addAttribute("day", 21);
			model.addAttribute("completeday", pkme.getCompleteDay());
			 String result = numberFormat.format((float) pkme.getCompleteDay() / (float) 21 * 100);  
			 model.addAttribute("baifenbi", result+"%");
			model.addAttribute("title", "斗鸡初成");
		}
			
		
		return baseView("schedule");
	}
	/*
	 *个人进度数据
	 */
	@RequestMapping(value="data",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> data(Model model,HttpServletRequest request,
			@RequestParam(value = "newDate", defaultValue = "") String newDate) {
		Map<String,Object> mm= new HashMap<String,Object>();
		try{
			
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		String month = null;
		if("".equals(newDate)){
			month = DateUtil.getDateFormat(new Date(), "yyyy-MM");   
		}else{
			month=newDate;
		}
		Date selectDate= DateUtil.StringToDate(month, "yyyy-MM");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar   cal_1=Calendar.getInstance();//获取当前日期 
		cal_1.setTime(selectDate);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String firstDay = format.format(cal_1.getTime());
        cal_1.add(Calendar.MONTH, 1);    //加一个月
        cal_1.set(Calendar.DATE, 1);        //设置为该月第一天
        cal_1.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String lastDay = format.format(cal_1.getTime());
		String startDate =  firstDay + " 00:00:00";
		String endDate =  lastDay + " 59:59:59";
		
		
		Result<List<String>> r = crowPunchService.getCalendarPunch(w.getId(), startDate, endDate);
		if(r.getState()==0)
			mm.put("state", 0);
		mm.put("state", 1);
		
		mm.put("month", month);
		mm.put("data",r.getT());
		
		cal_1.add(Calendar.MONTH, 1);    //加一个月
		mm.put("xia",  DateUtil.getDateFormat(cal_1.getTime(), "yyyy-MM"));
		cal_1.add(Calendar.MONTH, -2);    //加一个月
		mm.put("shang",  DateUtil.getDateFormat(cal_1.getTime(), "yyyy-MM"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return mm;
	}
	
}