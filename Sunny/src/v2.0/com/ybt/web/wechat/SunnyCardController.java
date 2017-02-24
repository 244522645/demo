package com.ybt.web.wechat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.util.Page;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunCardTrade;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunCardTradeService;
/**   
 * 我的-个人中心 control
 *  * @author lhq  
 * @version 
 */   
@Controller
@RequestMapping(value = "/wechat/v2/sunnyCard")
public class SunnyCardController {
	
	@Autowired
	private SunCardService cardService;
	@Autowired
	private SunCardTradeService cardTradeService;
	
	private String baseView(){
		return "/work/wechat/v2.0/card/";
	}
	
	//跳转到个人中心--阳光卡列表页
	@RequestMapping( method = RequestMethod.GET,value="cardList")
	public String cardList(Model model,HttpServletRequest request, HttpServletResponse response) {
		return baseView()+"cardList";
	}
	//查询阳光卡列表数据
	@RequestMapping( method = RequestMethod.POST,value="cardListDetail")
	@ResponseBody
	public Page<SunCard> cardListDetail(Model model,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		SunWechatUser  user  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		com.ybt.common.util.Page<SunCard> pageUtil = new com.ybt.common.util.Page<SunCard>();
		pageUtil.setPageNo(pageNumber);
		pageUtil.setPageSize(pageSize);
		Page<SunCard> result = cardService.findSunCardByStatusAndUserId(user.getId(),pageUtil);
		return cardService.findSunCardByStatusAndUserId(user.getId(),pageUtil);
	}
	
	//跳转到个人中心--阳光卡使用说明页
	@RequestMapping( method = RequestMethod.GET,value="cardInstructions")
	public String cardInstructions(Model model,HttpServletRequest request, HttpServletResponse response) {
		return baseView()+"cardInstructions";
	}
	
	//查询卡使用明细  
	@RequestMapping( method = RequestMethod.POST,value="usedDetails")
	@ResponseBody
	public List<SunCardTrade> consumerDetails(Model model,HttpServletRequest request, HttpServletResponse response) {
		SunWechatUser  user  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		List<SunCardTrade> result = cardTradeService.getRecvAndSendSunCardTradeList(user.getId());
		return result;
	}
	
	//跳转到--阳光卡绑定页
	@RequestMapping( method = RequestMethod.GET,value="cardBinding")
	public String cardBinding(Model model,HttpServletRequest request, HttpServletResponse response) {
		return baseView()+"cardBinding";
	}
	
	
	
}