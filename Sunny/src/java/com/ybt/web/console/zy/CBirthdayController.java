package com.ybt.web.console.zy;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.model.work.SunCelebrityBirthday;
import com.ybt.service.base.IBaseService;
import com.ybt.service.work.SunCelebrityBirthdayService;
import com.ybt.web.base.BaseController;

@Controller
@RequestMapping(value = "/console/cbirthday")
public class CBirthdayController extends BaseController<SunCelebrityBirthday,String> {

	@Autowired
	private SunCelebrityBirthdayService celebrityBirthdayService;
	
	public IBaseService<SunCelebrityBirthday, String> getBaseService() {
		return celebrityBirthdayService;
	}

	public String baseView() {
		return "/console/work/cbirthday";
	}
	
	@RequestMapping
	public String defaultView(Model model,@RequestParam(value = "page", defaultValue = "1") int pageNumber,
							  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, PropertyFilter.FILTER_PREFIX));
		com.ybt.common.util.Page<SunCelebrityBirthday> pageUtil = new com.ybt.common.util.Page<SunCelebrityBirthday>();
		pageUtil.setPageNo(pageNumber);
		pageUtil.setPageSize(pageSize);
		celebrityBirthdayService.findAll(filters, pageUtil);
		model.addAttribute("page", pageUtil);
		return baseView()+"/list";
	}
	
	@RequestMapping(value = "/edit")
	public String editVile(@RequestParam(value = "id", defaultValue = "") String id,Model model,HttpServletRequest request){
		if(!id.equals(""))
			model.addAttribute("birthday", celebrityBirthdayService.findOne(id));
		return baseView()+"/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/saveCb")
	public String save(SunCelebrityBirthday cb,Model model,HttpServletRequest request){
		model.addAttribute("birthday", celebrityBirthdayService.save(cb));
		return baseView()+"/edit";
	}
	
	@RequestMapping(value = "/del")
	public String del(Model model,@RequestParam(value = "id") String id, HttpServletRequest request){
		celebrityBirthdayService.delete(id);
		return defaultView(model,1,10,request);
	}
	
	//上传图片
	@RequestMapping(value = "/upImg")
	@ResponseBody
	public String upImg(Model model,@RequestParam(value="myFile") MultipartFile imgFile,HttpServletRequest request,HttpServletResponse response) throws IOException, FileUploadException{
		//文件保存目录路径
		String savePath = request.getServletContext().getRealPath("/") + "upload/";
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			return getError("上传目录不存在。").toString();
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			return getError("上传目录没有写权限。").toString();
		}

		if(!imgFile.isEmpty()){
			String fileName = UUID.randomUUID().toString().replaceAll("-", "")+"."+getSuffix(imgFile.getOriginalFilename());
			File file = new File(savePath,fileName);
			imgFile.transferTo(file);
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", request.getContextPath()+"/upload/"+fileName);
			return obj.toString();
		}
		return getError("未知错误").toString();
	}
	
	public static String getSuffix(String fileName){
		if(fileName==null||fileName.equals(""))
			return "";
		try{
			return fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()).toLowerCase();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	private JSONObject getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj;
	}
}
