package com.ybt.web.console.zy;



import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ybt.common.util.DateUtil;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.model.admin.User;
import com.ybt.model.work.SunBill;
import com.ybt.model.work.SunMemberTj;
import com.ybt.model.work.SunZyPhotoCover;
import com.ybt.service.admin.UserService;
import com.ybt.service.work.IMemberTjService;
import com.ybt.service.work.PhotoCovorService;
import com.ybt.service.work.SunBillService;


/**
 * 用户管理处理类，用户的增 删 改 查
 * 
 */
@Controller
@RequestMapping(value = "/console/zy/photoCover")
public class PhotoCoverController {
	
	@Autowired
	private PhotoCovorService photoCovorService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private  IMemberTjService memberTjService;
	
	@Autowired
	private  SunBillService billService;
	
	private String baseView() {
		return "/console/zy/cover/";
	}
	
	/**
	 *@name    显示照片列表
	 *@description 相关说明
	 *@time    创建时间:2016年6月16日上午11:15:11
	 *@param model
	 *@param pageNumber
	 *@param request
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String photoView(Model model,@RequestParam(value = "page", defaultValue = "1") int pageNumber,HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, PropertyFilter.FILTER_PREFIX));
		com.ybt.common.util.Page<SunZyPhotoCover> pageUtil = new com.ybt.common.util.Page<SunZyPhotoCover>();
		pageUtil.setPageNo(pageNumber);
		Pageable pager =new PageRequest(pageUtil.getPageNo()-1, pageUtil.getPageSize()); 
		Page<SunZyPhotoCover> page = photoCovorService.findSunZyPhotoCoverByProperty(filters, pager);
		pageUtil.setTotalCount(page.getTotalElements());
		pageUtil.setResult(page.getContent());
		model.addAttribute("page", pageUtil);
		return baseView() +"photoList";
	}
	

//跳转到用户修改和查看页面
	@RequestMapping(value = "/edit/{imgId:.*}")
	public String editVile(@PathVariable("imgId") String imgId,Model model,HttpServletRequest request){
		SunZyPhotoCover photo = photoCovorService.getOnePhoto(imgId);
		model.addAttribute("photo",photo);
		return baseView() +"photoEdit";
	}
	
	//修改用户
	@RequestMapping(method = RequestMethod.POST,value = "/editUser")
	public String editUser(SunZyPhotoCover photo,Model model,HttpServletRequest request){
		model.addAttribute("photo",photoCovorService.savePhoto(photo));
		return baseView() +"photoEdit";
	}
	

	/**
	 * 跳转到新增/编辑 页面
	 * */
	@RequestMapping(value = "/jump")
	public String jump(HttpServletRequest request, Model model) {
		String imgId = request.getParameter("imgId");
		String page = request.getParameter("page");
		model.addAttribute("page",page);
		if (imgId != null && !imgId.equals("")) {
			SunZyPhotoCover photo = photoCovorService.getOnePhoto(imgId);
			model.addAttribute("photo",photo);
			
			//查询上传人员名称
			photo.getWorkerId();
			if(photo.getWorkerId()!=null&&!"".equals(photo.getWorkerId())){
				User user=	userService.getUserByUserId(photo.getWorkerId());
				model.addAttribute("workerName",user.getName());
			}
		}
		return baseView() +"photoEdit";
	}
	
	/**
	 * 新增
	 * */
	@RequestMapping(value = "/put", method = RequestMethod.POST)	
	public String add(HttpServletRequest request,Model model, RedirectAttributes attr,
			@RequestParam(value = "title",required=false) String title,
			@RequestParam(value = "shootingTime", required=true) String shootingTime,
			@RequestParam(value = "address",required=true) String address,
			@RequestParam(value = "story",required=false) String story,
			@RequestParam(value = "imgId", required=true) String imgId,
			@RequestParam(value = "released", required=true) Integer released){
		
		attr.addFlashAttribute("msg", false);
		String id = request.getParameter("id");
		SunZyPhotoCover photo;
		if(id != null && !"".equals(id)){
			photo=  photoCovorService.getOnePhoto(id);
			if(photo!=null){
				//修改失败
				photo.setTitle(title);
				photo.setShootingTime(DateUtil.StringToDate(shootingTime,"yyyy-MM-dd"));
				photo.setAddress(address);
				photo.setStory(story);
				photo.setUpdateTime(new Date());
				photo.setReleased(released);
				photoCovorService.save(photo);
				
				String cameristId = photo.getCameristId();
				
				SunMemberTj tj =null;
				if(cameristId != null){
					tj = memberTjService.addSunb(cameristId, 0);
				}
				if(photo.getReleased()==1){
					SunBill bill =new SunBill();
					bill.setMessage("您上传的一张“"+photo.getAddress()+"”的照片，审核通过");
					bill.setMid(photo.getCameristId());
					bill.setCreateTime(new Date());
					bill.setCurrency("SUNB");
					bill.setMoney(1);
					bill.setPaytype("SYSTEM");
					bill.setPid(photo.getId());
					bill.setStatus(0);
					bill.setType(1);
					bill.setBalance(tj != null ? tj.getSunb() : null);
					billService.save(bill);
				}
			}
		}
		
		String page = request.getParameter("page");
		return "redirect:/console/zy/photoCover?page="+page;
	}
	//批量删除用户
	@RequestMapping(method = RequestMethod.POST,value = "/batchDel")
	public String batchDel(Model model,HttpServletRequest request){
		String[] photoIds = request.getParameterValues("photoIds");
		
		if(photoIds!=null&&photoIds.length!=0)
			for(int i=0;i<photoIds.length;i++){
				photoCovorService.delPhoto(photoIds[i]);
			}
		
		return "redirect:/console/zy/photo";
	}
	
}
