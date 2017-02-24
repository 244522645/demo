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
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.PropertyFilter;
import com.ybt.model.work.SunDaiyan;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.PhotoService;
import com.ybt.service.work.QrcodeService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunDaiyanService;

/**
 * 微信  活动
 * */
@Controller
@RequestMapping(value = "/wechat/activity")
public class ActivityDaiyanController {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(ActivityDaiyanController.class);
	
	@Autowired
	public IWechatService wechatService;
	@Autowired
	public SunCardService sunCardService;
	@Autowired
	public QrcodeService qrcodeService;
	@Autowired
	@Qualifier("photoService")
	private PhotoService photoService;
	@Autowired
	public SunDaiyanService daiyanService;
	private String baseView() {
		return "/work/wechat/daiyan";
	}
	
	/**  
	 * 代言
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月8日 下午4:52:05 
	 */
	@RequestMapping(value = "daiyan")
	public String daiyan(Model model,HttpServletRequest request,
			@RequestParam(value = "dyid" ,defaultValue="") String dyid) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null ){
			request.setAttribute("login", "no");
			return baseView()+"/index";
	   	}
		
		SunDaiyan daiyan = null;
		if(!dyid.equals("")){
			// TODO Auto-generated method stub
			 daiyan = daiyanService.findOne(dyid);
			if(daiyan!=null){
				request.setAttribute("daiyan", daiyan);
				if(daiyan.getUserId().getId().equals(w.getId()))
					request.setAttribute("daiyan_me", "ok");
			}
		}else{
			 daiyan = daiyanService.getSunDaiyanByUser(w.getId());
			if(daiyan==null){
				Result<SunDaiyan> r = daiyanService.createDaiyan(w);
				if(r.getState()==1){
					daiyan =  r.getT();
					// 第一次打开
					//request.setAttribute("firstdy","first");
					
				}
			}
			request.setAttribute("daiyan",daiyan );
			if(daiyan.getUserId().getId().equals(w.getId()))
				request.setAttribute("daiyan_me", "ok");
		}
		
		//今日日出
		Map<String, Object> searchParams1 = new HashMap<String, Object>();
    	String today = DateUtil.getDateFormat(new Date(), "yyyy-MM-dd");	
    	//today="2016-09-14";
		searchParams1.put("LIKES_shootingTime",today);
		searchParams1.put("EQS_subject", "日出");
    	com.ybt.common.util.Page<SunZyPhoto> pageUtil1 = new com.ybt.common.util.Page<SunZyPhoto>();
		pageUtil1.setOrderBy("shootingTime");
		pageUtil1.setOrder("desc");
		pageUtil1.setPageNo(1);
		pageUtil1.setPageSize(20);
		List<PropertyFilter> filters1 = PropertyFilter.buildFrom(searchParams1);
		Pageable pager1 =new PageRequest(pageUtil1.getPageNo()-1, pageUtil1.getPageSize()); 
		Page<SunZyPhoto> page1 = photoService.findSunZyPhotoByProperty("",filters1, pager1);
		request.setAttribute("photos",page1.getContent() );
		
		return baseView()+"/index";
	}
	
	/**  
	 * 代言 分享
	 * @param model
	 * @param request
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月8日 下午4:52:05 
	 */
	@RequestMapping(value = "daiyanShare" ,method = RequestMethod.POST)
	@ResponseBody
	public Result<String> daiyanShare(Model model,HttpServletRequest request,
			@RequestParam(value = "dyid" ,defaultValue="") String dyid) {
		
		SunWechatUser  w  = (SunWechatUser) request.getSession().getAttribute("userInfo");
		if(w==null ){
			request.setAttribute("login", "no");
			return new Result<String>("","");
	   	}
		
		SunDaiyan daiyan = null;
		if(!dyid.equals("")){
			// TODO Auto-generated method stub
			 daiyan = daiyanService.findOne(dyid);
		}
		if(daiyan==null){
			return new Result<String>("","");
		}
		
		Result<SunDaiyan> result = daiyanService.shareDaiyan(daiyan, w);
		
		if(result.getState()==0){
			if(result.getMessage()!=null&&!result.getMessage().equals("")){
				return new Result<String>("",result.getMessage());
			}
		}
		
		return new Result<String>("","");
	}
}