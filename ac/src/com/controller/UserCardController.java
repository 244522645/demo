package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.KUser;
import com.model.KUserRanges;
import com.service.UserCardService;
import com.util.EasyUiDataGridJson;
import com.util.KUserRanges_util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/UserCard")
public class UserCardController {
	
	@Autowired(required=false)
	private UserCardService userCardService;
	
	public UserCardService getUserCardService() {
		return userCardService;
	}

	public void setUserCardService(UserCardService userCardService) {
		this.userCardService = userCardService;
	}

	@RequestMapping("/findByPage")
	public void findByPage(int page, int rows, String userid, String cardnum, HttpServletResponse response) {
		List<Map<String,String>> list = userCardService.findByPage((page - 1) * rows, rows, userid, cardnum);
		int totle = userCardService.countCard(userid, cardnum);
		EasyUiDataGridJson<Map<String, String>> json = new EasyUiDataGridJson<Map<String, String>>();
		json.setTotal(totle);
		json.setRows(list);

		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(JSONObject.fromObject(json).toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 添加用户卡
	 */
	@RequestMapping("/addCard")
	private void addCard(HttpServletResponse response, KUserRanges kUserRanges) {
		System.out.println(kUserRanges.getUserRangeName());
		int i = 0;
		try {
			getUserCardService().addCard(kUserRanges);
		} catch (Exception e) {
			i = 1;
			e.printStackTrace();
		}
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(i);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 修改用户卡
	 */
	@RequestMapping("/updateCard")
	public void updateCard(HttpServletResponse response, KUserRanges_util kUserRanges) {

		int i = 0;
		try {
			userCardService.updateCard(kUserRanges);
		} catch (Exception e) {
			i = 1;
			e.printStackTrace();
		}
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(i);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 删除用户卡
	 */
	@RequestMapping("delCard")
	public void delCard(@RequestParam("array[]") List<Integer> array, HttpServletResponse response) {
		int i = 0;
		try {
			userCardService.delCard(array);
		} catch (Exception e) {
			i = 1;
			e.printStackTrace();
		}
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.getWriter().print(i);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 查询当前用户下面的卡
	 */
	@RequestMapping("/findByUser")
	@ResponseBody
    public List<Map<String, String>> findByUser(HttpServletRequest request){
    	KUser user=(KUser)request.getSession().getAttribute("username");
    	if(user!=null&&!user.equals("")){
    		String id=user.getId();
    		List<Map<String, String>> list=userCardService.findByUser(id);
    	 return list;
    	}
		return null;
    	
    }
	
	/*
	 * 查询当前用户下面的号段
	 */
	@RequestMapping("/findRangsByUser")
	@ResponseBody
	public List<KUserRanges> findRangsByUser(HttpServletRequest request){
		KUser user=(KUser)request.getSession().getAttribute("username");
    	if(user!=null&&!user.equals("")){
    		String id=user.getId();
    		List<KUserRanges> list=userCardService.findRangsByUser(id);
    	 return list;
    	}
		return null;
		
	}
}
