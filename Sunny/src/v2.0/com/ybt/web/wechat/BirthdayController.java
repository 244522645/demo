package com.ybt.web.wechat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.MemberRelationBean;
import com.ybt.common.bean.Result;
import com.ybt.common.bean.ResultTBean;
import com.ybt.model.work.MemberRelation;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.work.MemberRelationService;
import com.ybt.service.work.SunBlessService;
/**
 * 生日管理
 * @author lhq
 */
@Controller
@RequestMapping(value = "/wechat/v2/relation")
public class BirthdayController {
	@Autowired
	private MemberRelationService memberRelationService;
	
	private String baseView(){
		return "/work/wechat/v2.0/memberRelation/";
	}
	
	
	//跳转到生日关系列表
	@RequestMapping( method = RequestMethod.GET,value="relationList")
	public String relicationList(Model model,HttpServletRequest request, HttpServletResponse response) {
		return baseView()+"relationList";
	}
	
	//查询生日关系数据
	@RequestMapping( method = RequestMethod.POST,value="relationListDetail")
	@ResponseBody
	public List<MemberRelationBean> relicationListDetail(Model model,HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value = "page", defaultValue = "1") int pageNumber,
							@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
							@RequestParam(value = "status", defaultValue = "1") int status) {
		SunWechatUser  user  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		List<MemberRelationBean> result = memberRelationService.findMemberRelationsById(user.getId());
		
		return result;
	}
	
	//跳转到新增页面
	@RequestMapping( method = RequestMethod.GET,value="addRelation")
	public String addRelication(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id",required=false) String id) {
		if(!StringUtils.isEmpty(id)){
			MemberRelation mb = memberRelationService.findOne(id);
			model.addAttribute("mb", mb);
		}
		return baseView()+"addRelation";
	}
	
	//新增生日关系
	@RequestMapping( method = RequestMethod.POST,value="addRelationSave")
	@ResponseBody
	public Result<MemberRelation> addRelicationDetail(Model model,HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value = "id") String id,
							@RequestParam(value = "name") String name,
							@RequestParam(value = "birthday") String birthday,
							@RequestParam(value = "mobileNo",defaultValue="") String mobileNo,
							@RequestParam(value = "relation") String relation) {
		Result<MemberRelation> result = new Result<>();
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(birthday) ||StringUtils.isEmpty(relation)){
			result.setMessage("empty");
			return result;
		}
		SunWechatUser  user  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		result = memberRelationService.saveRelation(user,id,name, birthday, mobileNo, relation);
		return result;
	}
	//逻辑删除生日关系(只是在关系列表中不显示)
	@RequestMapping( method = RequestMethod.POST,value="deleteRelication")
	@ResponseBody
	public String deleteRelication(Model model,HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value = "id") String id) {
		String result = memberRelationService.deleteRelation(id);
		return result;
	}
}
