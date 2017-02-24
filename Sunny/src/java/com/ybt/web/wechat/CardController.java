package com.ybt.web.wechat;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.common.util.StringUtil;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunCardTrade;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.SmsService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunCardTradeService;



/**
 * 客户端 阳光卡 管理
 * 
 */
@Controller
@RequestMapping(value = "/wechat/my/card")
public class CardController {
	
	
	@Autowired
	public SunCardService sunCardService;
	@Autowired
	public SunCardTradeService sunCardTradeService;
	@Autowired
	public IWechatService wechatService;
	@Autowired
	public SmsService smsService;
	@Resource
	public Map<String,String> constant;
	
	private String baseView() {
		return "/work/wechat";
	}
	
	/**  
	 * 绑定卡
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月18日 下午3:08:53 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "sunECardBandding")
	public Result<SunCard> sunECardBandding(Model model,HttpServletRequest request,
			@RequestParam(value = "token") String token){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		//token验证
		String accessToken =(String)request.getSession().getAttribute("cardToken");
		if(!token.equals(accessToken)){
			return new Result<SunCard>("验证无效",null);
		}
		
		Result<SunCard>  cardresult = sunCardService.sunECardBandding(accessToken, w.getId());
		
		if(cardresult.state==1){
			request.getSession().setAttribute("cardToken", null);
		}
		
		return cardresult;
	}
	/**  
	 * 验证是否 绑定卡
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月18日 下午3:08:53 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "verifySunCard")
	public Result<String> verifySunCard(Model model,HttpServletRequest request){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		
		com.ybt.common.util.Page<SunCard> page = new com.ybt.common.util.Page<SunCard>();
		page.setPageNo(1);
		page.setPageSize(10);
		try {
			sunCardService.findSunCardByUserId(w.getId(), page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sunCard = constant.get("sunCard");
		int count=StringUtil.getInteger(sunCard);
		if(page.getResult().size()>=count){
			return new Result<String>("绑定失败，卡绑定绑数量上限",null);
		}
		
		String token =request.getParameter("token");
		String cardToken = (String) request.getSession().getAttribute("cardToken");
		if(token==null||cardToken==null){
			return new Result<String>("","可以绑定");
		}
		if(token.equals(cardToken)){
			return new Result<String>("",token);
		}
		
		return new Result<String>("","可以绑定");
	}
	
	/**  
	 * 验证票据 token
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月18日 下午3:08:53 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "getToken")
	public Result<String> getToken(Model model,HttpServletRequest request,
			@RequestParam(value="token")String token){
		String cardToken = (String) request.getSession().getAttribute("cardToken");
		if(token==null||cardToken==null){
			return new Result<String>("token无效",null);
		}
		if(!token.equals(cardToken)){
			return new Result<String>("token无效",null);
		}
		return new Result<String>("",cardToken);
	}
	
	/**  
	 * 刮刮卡 抽奖
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月18日 下午3:08:53 
	 */
	@RequestMapping(method = RequestMethod.GET,value = "guaguaka")
	public String guaguaka(Model model,HttpServletRequest request){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		
		return baseView()+"/activity/guaguaka";
	}
	/**  
	 * 获取卡列表
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月18日 下午3:08:53 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "getSunCard")
	public com.ybt.common.util.Page<SunCard> getSunCard(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		
		com.ybt.common.util.Page<SunCard> page = new com.ybt.common.util.Page<SunCard>();
		page.setPageNo(pageNumber);
		page.setPageSize(pageSize);
		try {
			sunCardService.findSunCardByUserId(w.getId(), page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	/**  
	 * 获取卡
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月18日 下午3:08:53 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "getOneSunCard")
	public Result<SunCard> getOneSunCard(Model model,HttpServletRequest request){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		
		com.ybt.common.util.Page<SunCard> page = new com.ybt.common.util.Page<SunCard>();
		page.setPageNo(1);
		page.setPageSize(10);
		try {
			sunCardService.findSunCardByUserIdAndState(w.getId(),1, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(page.getResult().isEmpty())
			return new Result<SunCard>("没有绑定卡",null);
			
		return new Result<SunCard>(null,page.getResult().get(0));
	}
	
	/**  
	 * 阳光卡绑定
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月18日 下午3:08:53 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "sunCardBandding")
	public Result<SunCard> sunCardBandding(Model model,HttpServletRequest request,
			@RequestParam(value = "number") String number,
			@RequestParam(value = "pwd") String pwd){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			return new Result<SunCard>("请登录",null);
	   	}
		
		Result<SunCard> result = sunCardService.sunCardBandding(number, pwd, w.getId());
		
		return result;
	}
	

	/**  
	 * 绑定T卡
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月18日 下午3:08:53 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "sunTCardBandding")
	public Result<SunCard> sunTCardBandding(Model model,HttpServletRequest request,
			@RequestParam(value = "token") String token){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null || (w!=null && w.getSubscribe() != 1)){
			request.setAttribute("login", "no");
	   	}
		//token验证
		String accessToken =(String)request.getSession().getAttribute("cardToken");
		if(!token.equals(accessToken)){
			return new Result<SunCard>("验证无效",null);
		}
		
		Result<SunCard>  cardresult = sunCardService.sunTCardBandding(accessToken, w.getId());
		
		if(cardresult.state==1){
			request.getSession().setAttribute("cardToken", null);
		}
		
		return cardresult;
	}
	
	/**  
	 * 获取有效卡列表
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月18日 下午3:08:53 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value = "getSunCardByValid")
	public List<SunCard> getSunCardByValid(Model model,HttpServletRequest request){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		
		return sunCardService.findSunCardByValidAndUserId(w.getId());
	}
	
	/**  
	 * 获取已送纪录
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月18日 下午3:08:53 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "getSunCardByMySend")
	public List<SunCardTrade> getSunCardByMySend(Model model,HttpServletRequest request){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		
		com.ybt.common.util.Page<SunCardTrade> page = new com.ybt.common.util.Page<SunCardTrade>();
		page.setPageNo(1);
		page.setPageSize(10);
		sunCardTradeService.getSunCardTradeListByUserId(w.getId(), page);
		
		return page.getResult();
	}
	
	/**  
	 * 获取已使用列表
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月18日 下午3:08:53 
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST,value = "getSunCardByUsed")
	public List<SunCard> getSunCardByUsed(Model model,HttpServletRequest request){
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		
		com.ybt.common.util.Page<SunCard> page = new com.ybt.common.util.Page<SunCard>();
		page.setPageNo(1);
		page.setPageSize(10);
		sunCardService.findSunCardByUsedAndUserId(w.getId(), page);
		
		return page.getResult();
	}
}
