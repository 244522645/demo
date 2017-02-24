package com.ybt.web.console.zy;



import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.model.work.SunZyImages;
import com.ybt.model.work.SunZyPhotoGrapher;
import com.ybt.service.work.ImagesService;
import com.ybt.service.work.PhotoGrapherService;


/**
 * 摄影师管理
 */
@Controller
@RequestMapping(value = "/console/zy/photoGrapher")
public class PhotoGrapherController {
	
	@Autowired
	private PhotoGrapherService photoGrapherService;
	@Autowired
	private ImagesService imagesService;
	
	private String baseView() {
		return "/console/zy/grapher/";
	}
	
	/**
	 *  显示摄影师列表
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String photoView(Model model,@RequestParam(value = "page", defaultValue = "1") int pageNumber,HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, PropertyFilter.FILTER_PREFIX));
		com.ybt.common.util.Page<SunZyPhotoGrapher> pageUtil = new com.ybt.common.util.Page<SunZyPhotoGrapher>();
		pageUtil.setPageNo(pageNumber);
		pageUtil = photoGrapherService.findAll(filters, pageUtil);
		model.addAttribute("page", pageUtil);
		return baseView() +"list";
	}
	

//跳转到用户修改和查看页面
	@RequestMapping(value = "/edit/{imgId:.*}")
	public String editVile(@PathVariable("imgId") String imgId,Model model,HttpServletRequest request){
		SunZyPhotoGrapher photo = photoGrapherService.findOne(imgId);
		model.addAttribute("photo",photo);
		return baseView() +"edit";
	}
	

	/**
	 * 跳转到新增/编辑 页面
	 * */
	@RequestMapping(value = "/jump")
	public String jump(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String page = request.getParameter("page");
		model.addAttribute("page",page);
		if (id != null && !id.equals("")) {
			SunZyPhotoGrapher photo = photoGrapherService.findOne(id);
			model.addAttribute("photo",photo);
		}
		return baseView() +"edit";
	}
	
	/**
	 * 新增
	 * */
	@RequestMapping(value = "/put", method = RequestMethod.POST)	
	public String add(HttpServletRequest request,Model model, RedirectAttributes attr
			,@RequestParam(value = "name",required=false) String name,
			@RequestParam(value = "tags", required=false) String tags,
			@RequestParam(value = "phone",required=false) String phone,
			@RequestParam(value = "email",required=false) String email,
			@RequestParam(value = "head", required=false) String head,
			@RequestParam(value = "work", required=false) String work,
			@RequestParam(value = "introduce", required=false) String introduce,
			@RequestParam(value = "id", defaultValue="") String id
			){
		
		attr.addFlashAttribute("msg", false);
		SunZyPhotoGrapher photo = null;
		if(id != null && !"".equals(id)){
			photo=  photoGrapherService.findOne(id);
			if(photo!=null){
				
			}else{
				photo= new SunZyPhotoGrapher();
				photo.setCreateTime(new Date());
			}
		}else{
			photo= new SunZyPhotoGrapher();
			photo.setCreateTime(new Date());
		}
		if(head!=null){
			SunZyImages hea=imagesService.findOne(head);
			photo.setHead(hea);
		}
		if(head!=null){
			SunZyImages wor=imagesService.findOne(work);
			photo.setWork(wor);
		}
		//修改失败
		
		photo.setUpdateTime(new Date());
		photo.setTags(tags);
		photo.setName(name);
		photo.setEmail(email);
		photo.setPhone(phone);
		photo.setIntroduce(introduce);
		photoGrapherService.save(photo);
		
		return "redirect:/console/zy/photoGrapher?page=1";
	}
	//批量删除用户
	@RequestMapping(method = RequestMethod.POST,value = "/batchDel")
	public String batchDel(Model model,HttpServletRequest request){
		String[] photoIds = request.getParameterValues("photoIds");
		return "redirect:/console/zy/photo";
	}
	
}
