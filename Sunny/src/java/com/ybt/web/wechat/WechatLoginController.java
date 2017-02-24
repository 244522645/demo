package com.ybt.web.wechat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *微信 登录
 **/
@Controller
@RequestMapping(value = "/wechat/login")
public class WechatLoginController {
	
	/*private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private IYbtUserService ybtUserService;
	
	@Autowired
	private IOrderService orderService;
	@RequestMapping(method = RequestMethod.GET)
	public String defaultView(Model model,HttpServletRequest request) {
		model.addAttribute("openid", request.getParameter("openid"));
		return "wechat/login";
	}
	*/
	/**
	 * 登录绑定
	 * *//*
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	@ResponseBody
	public Object login(@RequestParam(value = "phone") String phone,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "openid") String openid,
			@RequestParam(value = "mobileType") String mobileType, Model model,
			HttpServletRequest request) {
		
		Map<String,String>  map = ybtUserService.wechatLogin(openid, phone);
		if("1".equals(map.get("status"))){
			
			request.setAttribute("phone", phone);
			HttpSession session = request.getSession(true);
			session.setAttribute("phone", phone);
			//return "forward:/console/wechat/rescue/help";
		}
		//model.addAttribute("message", map.get("message"));
		return map;
	}

	*//**
	 * 解绑
	 * *//*
	@RequestMapping(method = RequestMethod.POST, value = "/logout")
	@ResponseBody
	public Object logout(@RequestParam(value = "phone") String phone,
			@RequestParam(value = "openid") String openid, Model model,
			HttpServletRequest request) {
		ErrorBean errorBean = new ErrorBean();
		errorBean.setMessage("注销失败！");
		errorBean.setStatus(0);
		if (phone != null && openid != null) {
			YbtUser user = ybtUserService.getYbtUserByPhone(phone);
			// 已存在用户只修改 token code loginTime
			if (user != null) {
				if(openid.equals(user.getWechat().getId())){
					user.setCode(null);
					user.setWechat(null);
					ybtUserService.saveYbtUser(user);
					errorBean.setMessage("解绑成功！");
					errorBean.setStatus(1);
				}else{
					errorBean.setMessage("已在其他微信客户端上绑定！");
					errorBean.setStatus(1);
				}
				
			}
		} 
		return errorBean;
	}*/

}
