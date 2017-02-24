package com.ybt.web.console.zy;



import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.ybt.model.work.SunZyPhoto;
import com.ybt.model.work.SunZyPhotoGrapher;
import com.ybt.service.admin.UserService;
import com.ybt.service.work.ImagesService;
import com.ybt.service.work.PhotoGrapherService;
import com.ybt.service.work.PhotoService;


/**
 * 用户管理处理类，用户的增 删 改 查
 * 
 */
@Controller
@RequestMapping(value = "/console/zy/photo")
public class PhotoController {
	
	@Autowired
	@Qualifier("photoService")
	private PhotoService photoService;
	@Autowired
	private PhotoGrapherService photoGrapherService;	
	@Autowired
	private ImagesService imagesService;
	@Autowired
	private UserService userService;
	
	private String baseView() {
		return "/console/zy/";
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
		com.ybt.common.util.Page<SunZyPhoto> pageUtil = new com.ybt.common.util.Page<SunZyPhoto>();
		pageUtil.setPageNo(pageNumber);
		Pageable pager =new PageRequest(pageUtil.getPageNo()-1, pageUtil.getPageSize()); 
		Page<SunZyPhoto> page = photoService.findSunZyPhotoByProperty(filters, pager);
		pageUtil.setTotalCount(page.getTotalElements());
		pageUtil.setResult(page.getContent());
		model.addAttribute("page", pageUtil);
		return baseView() +"photoList";
	}
	

//跳转到用户修改和查看页面
	@RequestMapping(value = "/edit/{imgId:.*}")
	public String editVile(@PathVariable("imgId") String imgId,Model model,HttpServletRequest request){
		SunZyPhoto photo = photoService.getOnePhoto(imgId);
		model.addAttribute("photo",photo);
		return baseView() +"photoEdit";
	}
	
	//修改用户
	@RequestMapping(method = RequestMethod.POST,value = "/editUser")
	public String editUser(SunZyPhoto photo,Model model,HttpServletRequest request){
		model.addAttribute("photo",photoService.savePhoto(photo));
		return baseView() +"photoEdit";
	}
	

	/**
	 * 跳转到新增/编辑 页面
	 * */
	@RequestMapping(value = "/jump")
	public String jump(HttpServletRequest request, Model model) {
		
		try{
			
			String imgId = request.getParameter("imgId");
			String page = request.getParameter("page");
			model.addAttribute("page",page);
			if (imgId != null && !imgId.equals("")) {
				SunZyPhoto photo = photoService.getOnePhoto(imgId);
				model.addAttribute("photo",photo);
				
					String[] tags = null;
					if(photo!=null){
						if(photo.getTags()!=null){
							tags=photo.getTags().split(",");
							model.addAttribute("tags", tags);
						}
					}
					
				String exStrs = photo.getExplainIds();
				String exList[] = null;
				if (exStrs != null){
					exList = exStrs.split(",");
				}
				model.addAttribute("explainList",imagesService.findByIds(exList));
				
				//查询上传人员名称
				photo.getWorkerId();
				if(photo.getWorkerId()!=null&&!"".equals(photo.getWorkerId())){
					User user=	userService.getUserByUserId(photo.getWorkerId());
					if(user!=null)
					model.addAttribute("workerName",user.getName());
				}
			}
			model.addAttribute("cameristList", photoGrapherService.findAll());
			
		}catch(Exception e){
			e.printStackTrace();
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
			@RequestParam(value = "tags",required=false) String tags,
			@RequestParam(value = "province",required=false) String province,
			@RequestParam(value = "city",required=false) String city,
			@RequestParam(value = "address",required=true) String address,
			@RequestParam(value = "story",required=false) String story,
			@RequestParam(value = "subject",required=false) String subject,
			@RequestParam(value = "imgId", required=true) String imgId,
			@RequestParam(value = "explainIds",required=false) String[] explainIds) {
		
			attr.addFlashAttribute("msg", false); 
			String id = request.getParameter("id");
			SunZyPhoto photo;
			if(id != null && !"".equals(id)){
				photo=  photoService.getOnePhoto(id);
				if(photo==null){
					//修改失败
				}
			}else{
				
				photo=new SunZyPhoto();
				photo.setCreateTime(new Date());
				photo.setWorkerId("");
				User user = (User)SecurityUtils.getSubject().getPrincipal();
				String userId =user.getUserId();
				if(userId != null)
				photo.setWorkerId(userId);
				
			}
			photo.setTitle(title);
			photo.setSubject(subject);
			photo.setShootingTime(DateUtil.StringToDate(shootingTime,"yyyy-MM-dd"));
			photo.setTags(tags);
			photo.setTitle(title);
			photo.setProvince(province);
			photo.setCity(city);
			photo.setAddress(address);
			photo.setStory(story);
			photo.setImgId(imagesService.findOne(imgId));
			photo.setExplainIds(Arrays.toString(explainIds).replace("[", "").replace("]", ""));
			photo.setUpdateTime(new Date());
			
			//摄影师
			String camerist = request.getParameter("camerist");
			if(camerist != null){
				SunZyPhotoGrapher cg = photoGrapherService.findOne(camerist);
				photo.setGrapher(cg);
			}
			String cameristId = request.getParameter("cameristId");
			String cameristName = request.getParameter("cameristName");
			String price = request.getParameter("price");		
			if( price !=null && !"".equals(price)){
				 double pricea=Double.parseDouble(price);
					photo.setPrice(pricea);
			}
			photo.setCameristId(cameristId);
			photo.setCameristName(cameristName);
			String localName = request.getParameter("localName");		
			if( localName !=null && !"".equals(localName)){
					photo.setLocalName(localName);
			}
			
			photoService.save(photo);
			
		String page = request.getParameter("page");
		
		return "redirect:/console/zy/photo?page="+(page==null||"".equals(page) ? 1 : page);
	}
	
	
	//批量删除用户
	@RequestMapping(method = RequestMethod.POST,value = "/batchDel")
	public String batchDel(Model model,HttpServletRequest request){
		String[] photoIds = request.getParameterValues("photoIds");
		
		if(photoIds!=null&&photoIds.length!=0)
			for(int i=0;i<photoIds.length;i++){
				photoService.delPhoto(photoIds[i]);
			}
		
		return "redirect:/console/zy/photo";
	}
	
}
