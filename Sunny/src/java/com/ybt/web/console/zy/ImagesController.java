package com.ybt.web.console.zy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ybt.common.bean.Result;
import com.ybt.common.util.ImgUtil;
import com.ybt.common.util.JacksonJsonUtil;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.Servlets;
import com.ybt.common.util.UploadImgUtil;
import com.ybt.model.work.SunZyImages;
import com.ybt.service.base.IBaseService;
import com.ybt.service.work.ImagesService;
import com.ybt.web.base.BaseController;

@Controller
@RequestMapping(value = "/console/upload")
public class ImagesController extends BaseController<SunZyImages,String> {
	
	private Logger logger = Logger.getLogger(getClass());
	private static final int BUFFER_SIZE = 100 * 1024;
	@Autowired
	public ImagesService imagesService;
	@Resource
	public Map<String,String> constant;

	public IBaseService<SunZyImages, String> getBaseService() {
		return imagesService;
	}

	public String baseView() {
		logger.info("console/work");
		return "/console/work/imagesUpload";
	}
	
	@RequestMapping(value="/data",method = RequestMethod.GET)
	@ResponseBody
	public com.ybt.common.util.Page<SunZyImages> index(Model model,HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize
			){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, PropertyFilter.FILTER_PREFIX);
		searchParams.put("EQS_type","works"); //获取 0,1,2状态
		List<PropertyFilter> filters = PropertyFilter.buildFrom(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, PropertyFilter.FILTER_PREFIX));
		com.ybt.common.util.Page<SunZyImages> pageUtil = new com.ybt.common.util.Page<SunZyImages>();
		pageUtil.setPageNo(pageNumber);
		pageUtil.setPageSize(pageSize);
		pageUtil.orderBy("createTime");
		pageUtil.order("desc");
		imagesService.findAll(filters, pageUtil);
		return pageUtil;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/upImages" ,method = RequestMethod.POST)
	public void upImages(HttpServletRequest request,HttpServletResponse response,
			Model model,@RequestParam(value="myFile") MultipartFile imgFile,
			@RequestParam(value="folder") String folder,
			@RequestParam(value="id",defaultValue="") String id,
			@RequestParam(value="fileName",defaultValue="") String localfileName){
		
		SunZyImages uImg = new SunZyImages();
		String name = "";
		if(id==null||id.equals(""))
			name =  UploadImgUtil.createUUIDName();
		else
			name = id;
		String suffix = UploadImgUtil.getSuffix(request.getParameter("name"));
		String fileName = name+"."+suffix;
		uImg.setName(fileName);
		uImg.setLocalName(localfileName);
		uImg.setSuffix(suffix);
		//方便起见  开发模式  使用本地路径
		/*if(!constant.get("runMode").equals("production")){
			uImg.path =  request.getSession().getServletContext().getRealPath("/")+"upload";
			uImg.apacheImgPath = request.getContextPath() +File.separator +"upload";
		}*/
		String datepath = new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
		uImg.setFolder(folder+ datepath);
		uImg.setType(folder);
		String path =  uImg.path+"/" +uImg.getFolder(); 
		File destFile = UploadImgUtil.createFolder(fileName,path);
		
		int chunk = Integer.valueOf(request.getParameter("chunk")==null?"0":request.getParameter("chunk"));  //当前分片数
		int chunks = Integer.valueOf(request.getParameter("chunks")==null?"1":request.getParameter("chunks"));//分片总数
		response.setContentType("json");
		try {
			 appendFile(imgFile.getInputStream(), destFile);  
			 //如果上传文件小于分片大小时 ，（一次就上传成功）
			 if(chunks==(chunk+1)){
				 //这里判断手机上传是否需要旋转图片
				 int i = ImgUtil.getRotateAngleForPhoto(destFile.getPath());
				 if(i!=0)
					 ImgUtil.rotatePhonePhoto(destFile.getPath(), suffix, i);
				 Result<SunZyImages> rs =  imagesService.saveImg(uImg,destFile,true);
				 response.getWriter().write(JacksonJsonUtil.beanToJson(rs));
				 return;
			 }
			 //上传文件大于分片大小时，（分片上传多次）
			 if(name.equals(id)&&chunks==(chunk+1)){
				 //这里判断手机上传是否需要旋转图片
				 int i = ImgUtil.getRotateAngleForPhoto(destFile.getPath());
				 if(i!=0)
					 ImgUtil.rotatePhonePhoto(destFile.getPath(), suffix, i);
				 
				 Result<SunZyImages> rs = imagesService.saveImg(uImg,destFile,true);
				 response.getWriter().write(JacksonJsonUtil.beanToJson(rs));
				 return;
			 }
			 response.getWriter().write(name);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 private void appendFile(InputStream in, File destFile) {
	        OutputStream out = null;
	        try {
	            // plupload 配置了chunk的时候新上传的文件append到文件末尾
	            if (destFile.exists()) {
	                out = new BufferedOutputStream(new FileOutputStream(destFile, true), BUFFER_SIZE); 
	            } else {
	                out = new BufferedOutputStream(new FileOutputStream(destFile),BUFFER_SIZE);
	            }
	            in = new BufferedInputStream(in, BUFFER_SIZE);
	             
	            int len = 0;
	            byte[] buffer = new byte[BUFFER_SIZE];          
	            while ((len = in.read(buffer)) > 0) {
	                out.write(buffer, 0, len);
	            }
	        } catch (Exception e) {
	            logger.error(e.getMessage());
	        } finally {     
	            try {
	                if (null != in) {
	                    in.close();
	                }
	                if(null != out){
	                    out.close();
	                }
	            } catch (IOException e) {
	                logger.error(e.getMessage());
	            }
	        }
	    }
}
