package com.ybt.web.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.common.bean.Result;
import com.ybt.common.bean.VerCodeBean;
import com.ybt.common.constant.chxtConstant;
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.RandomCode;
import com.ybt.dao.work.SunCakeShopDao;
import com.ybt.model.work.SunCakeOrder;
import com.ybt.model.work.SunCakeShop;
import com.ybt.model.work.SunWXMessage;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.PhotoGrapherService;
import com.ybt.service.work.PhotoService;
import com.ybt.service.work.SmsService;
import com.ybt.service.work.SunCakeOrderService;
import com.ybt.service.work.WXMessageService;

/**
 * 微信  蛋糕店测试
 * */
@Controller
@RequestMapping(value = "/wechat/cake")
public class CakeController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(CakeController.class);
	
	@Autowired
	public IWechatService wechatService;
	@Autowired
	@Qualifier("photoService")
	private PhotoService photoService;
	@Autowired
	private PhotoGrapherService photoGrapherService;
	@Autowired
	private SunCakeOrderService sunCakeService;
	@Autowired
	private SmsService   smsService;
	@Autowired 
	private WXMessageService wxMessageService;
	
	@Autowired
	private SunCakeShopDao sunCakeShopDao;
	private String baseView() {
		return "/work/wechat/cake";
	}
	
	/**  
	 * 蛋糕店测试
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月8日 下午4:52:05 
	 */
	@RequestMapping(value = "index")
	public String index(Model model,HttpServletRequest request,
			@RequestParam(value = "shopid" ,defaultValue="") String shopid) {
		
		//今日日出
		Map<String, Object> searchParams1 = new HashMap<String, Object>();
    	String today = DateUtil.getDateFormat(new Date(), "yyyy-MM-dd");
//    	today="2016-09-14";
		searchParams1.put("LIKES_shootingTime",today);
		searchParams1.put("EQS_subject", "日出");
    	com.ybt.common.util.Page<SunZyPhoto> pageUtil1 = new com.ybt.common.util.Page<SunZyPhoto>();
		pageUtil1.setOrderBy("shootingTime");
		pageUtil1.setOrder("desc");
		pageUtil1.setPageNo(1);
		pageUtil1.setPageSize(50);
		List<PropertyFilter> filters1 = PropertyFilter.buildFrom(searchParams1);
		Pageable pager1 =new PageRequest(pageUtil1.getPageNo()-1, pageUtil1.getPageSize()); 
		Page<SunZyPhoto> page1 = photoService.findSunZyPhotoByProperty("",filters1, pager1);
		request.setAttribute("photos",page1.getContent() );
		request.setAttribute("shopid",shopid );
		if(!"".equals(shopid)){
			SunCakeShop shop = sunCakeShopDao.findOne(shopid);
			model.addAttribute("shop", shop);
		}else{
			model.addAttribute("shopList", sunCakeShopDao.findAll());
		}
		
		request.setAttribute("graphers",photoGrapherService.findAll() );
		return baseView()+"/index";
	}
	
	/**  
	 * 蛋糕店测试
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月8日 下午4:52:05 
	 */
	@RequestMapping(value = "show")
	public String show(Model model,HttpServletRequest request,
			@RequestParam(value = "phone" ,defaultValue="") String phone) {
		
		if(!"".equals(phone)){
			List<SunCakeOrder> list2 = sunCakeService.findByPhone(phone);
			model.addAttribute("orders", list2);
			
			return baseView()+"/show";
		}
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w!=null ){
			List<SunCakeOrder> list = sunCakeService.findByUser(w.getId());
			model.addAttribute("orders", list);
	   	}
		
		return baseView()+"/show";

	}
	
	/**  
	 * 提交订单
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月8日 下午4:52:05 
	 */
	@RequestMapping(value = "put" ,method = RequestMethod.POST)
	@ResponseBody
	public Result<SunCakeOrder> daiyanShare(Model model,HttpServletRequest request,
			@RequestParam(value = "shop" ,defaultValue="") String shop,
			@RequestParam(value = "photo" ,defaultValue="") String photo,
			@RequestParam(value = "photoInfo" ,defaultValue="") String photoInfo,
			@RequestParam(value = "name" ,defaultValue="") String name,
			@RequestParam(value = "phone" ,defaultValue="") String phone,
			@RequestParam(value = "address" ,defaultValue="") String address,
			@RequestParam(value = "note" ,defaultValue="") String note
			) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null ){
			request.setAttribute("login", "no");
			return new Result<SunCakeOrder>("有误",null);
	   	}
		if("".equals(phone)){
			return new Result<SunCakeOrder>("有误",null);
		}
		
		SunZyPhoto p = photoService.findOne(photo);
		
		
		
		return sunCakeService.saveSunCakeOrder(w, shop, name, phone, address, note, p,photoInfo);
	}
	
	/**  
	 * 提交订单 更新
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月8日 下午4:52:05 
	 */
	@RequestMapping(value = "setStatus" ,method = RequestMethod.POST)
	@ResponseBody
	public Result<SunCakeOrder> setStatus(Model model,HttpServletRequest request,
			@RequestParam(value = "id" ,defaultValue="") String id
			) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null ){
			request.setAttribute("login", "no");
			return new Result<SunCakeOrder>("有误",null);
	   	}
		if("".equals(id)){
			return new Result<SunCakeOrder>("有误",null);
		}
		SunCakeOrder p = sunCakeService.findOne(id);
		if(p==null){
			return new Result<SunCakeOrder>("有误",null);
		}
		
		p.setIspay(1);
		p.setUpdateTime(new Date());
		sunCakeService.save(p);
		
		//临时 发短信提醒
			VerCodeBean verCodeBean = new VerCodeBean();
			String code = RandomCode.getCode();//验证码
			verCodeBean.setCode(p.getNumber()+"");
			verCodeBean.setType(chxtConstant.SMS_BDYZM);//注册绑定手机号
			StringBuffer content= new StringBuffer();
			content.append("【给点儿阳光】有新订单");
			content.append(code);
			content.append("，尽快处理。如非本人操作，请忽略本短信");
			
			verCodeBean.setPhone("13581566690");//双江
			smsService.sendOrderWarn(verCodeBean,content.toString());
			verCodeBean.setPhone("18600825086");//继祥
			smsService.sendOrderWarn(verCodeBean,content.toString());
		//发送微信消息
			StringBuffer wxcontent= new StringBuffer();
			wxcontent.append("有新订单:\n");
			wxcontent.append(p.getPhotoInfo()+"\n");
			wxcontent.append("收货人:"+p.getName()+"\n");
			wxcontent.append("地址:"+p.getAddress()+"\n");
			wxcontent.append("电话："+p.getPhone()+"\n");
			wxcontent.append(p.getBless());
			//继祥微信
			SunWXMessage wxMessage= new SunWXMessage("o3qhbv0KstBJQiQvQp8sTJrZ6-V8",wxcontent.toString());
			wxMessageService.sendMessage(wxMessage);
		
		return new Result<SunCakeOrder>("",p);
	}
	
	
}