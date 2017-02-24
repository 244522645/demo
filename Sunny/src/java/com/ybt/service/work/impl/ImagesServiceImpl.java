package com.ybt.service.work.impl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.MessageConstant;
import com.ybt.common.plugin.PicBucket;
import com.ybt.common.plugin.UpYun;
import com.ybt.common.util.CustomPropertyConfigurer;
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.ImgDraw;
import com.ybt.common.util.ImgUtil;
import com.ybt.common.util.UploadImgUtil;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.ImagesDao;
import com.ybt.model.work.SunZyImages;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.ImagesService;

@Component
public class ImagesServiceImpl extends BaseServiceImpl<SunZyImages,String> implements ImagesService{
	
	
	@Autowired
	private ImagesDao imagesDao;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	public HashMap constant;
	
	public BaseDao<SunZyImages, String> getDao() {
		// TODO Auto-generated method stub
		return imagesDao;
	}


	@SuppressWarnings("static-access")
	public Result<SunZyImages> downImgByte(byte[] bytes,String type) {
		
		if(bytes==null||bytes.length==0){
			return new Result<SunZyImages>(MessageConstant.IMG_SAVE_FAILURE,null);
		}

		SunZyImages uImg = new SunZyImages();
		String name =  UploadImgUtil.createUUIDName();
		String fileName = name+".jpg";
		uImg.setName(fileName);
		uImg.setSuffix("jpg");
		String datepath = new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
		uImg.setFolder(type+ datepath);
		uImg.setType(type);
		String path =  uImg.path+"/" +uImg.getFolder(); 
		File file = UploadImgUtil.createFolder(fileName, path);
		boolean flag = UploadImgUtil.byte2image(bytes,file);
		if(flag){
			flag = new ImgUtil().compressPic(path+"/", path+"/s/", fileName, fileName,3000,3000,true);
			if(!flag){
				return new Result<SunZyImages>(MessageConstant.IMG_SAVE_FAILURE,null);
			}
			 try {
	        	 PicBucket.initUpYun(file);
	        	 String upyunpath="/"+uImg.getFolder()+"/"+uImg.getName();
	        	 UpYun yun=PicBucket.upYunWriteFile(upyunpath, file);
	 			if(yun!=null){
	 				uImg.setWidth(yun.getPicWidth());
	 				uImg.setHeight(yun.getPicHeight());
	 				uImg.setSize(file.length());
	 			}
			} catch (IOException e) {
				e.printStackTrace();
				return new Result<SunZyImages>(MessageConstant.IMG_SAVE_FAILURE,null);
			}
			imagesDao.save(uImg);
			//保存手机显示的缩略图
			return new Result<SunZyImages>(null,uImg);
		}else{
			return new Result<SunZyImages>(MessageConstant.IMG_SAVE_FAILURE,null);
		}
	}
	
	/**
	 *@name   删除图片信息
	 *@description 相关说明
	 *@time    创建时间:2016年2月14日上午10:11:17
	 *@param imagesId
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public void deleteImg(String[] imagesId) {
		if (imagesId != null) {
			for (String id : imagesId) {
				//删除
				SunZyImages oldimg =imagesDao.findOne(id);
				oldimg.setUpdateTime(new Date());
				oldimg.setDeleted(1);
				imagesDao.save(oldimg);
			}
		}
	}

	@SuppressWarnings("static-access")
	public Result<SunZyImages> saveImg(SunZyImages uImg, File file, boolean b) {
		String path =  uImg.path+"/"+uImg.getFolder() ; 
		//如果扩展名属于允许上传的类型，则创建文件  
		if(!UploadImgUtil.fileTypes.contains(uImg.getSuffix()))
			return new Result<SunZyImages>(MessageConstant.IMG_DOES_NOT_SUPPORT_EXTENSION,null);
		//判断图片尺寸
		/*if(minImgWH(file,uImg.getFolder())){
			return new Result<SunZyImages>("图像尺寸太小",null);
		}
		*/
		//判断图片大小 4M
		if(file.length()>=100*1024*1024){
				return new Result<SunZyImages>("文件太大",null);
		}
		
			//保存到UPYUN 云存储
		if (b){
			 //String upyunpath="/"+uImg.getFolder()+"/"+uImg.getName();
			//保存手机显示的缩略图
			boolean flag = new ImgUtil().compressPic( uImg.path+"/"+uImg.getFolder()+"/",  uImg.path+"/"+uImg.getFolder()+"/s/",uImg.getName(),uImg.getName(),3000,3000,true);
			if(flag){
				//upyunpath = "/"+uImg.getFolder()+"/s/"+uImg.getName();
				 //获得源文件   
				File  file1 = new File( uImg.path+"/"+uImg.getFolder()+"/s/"+uImg.getName());   
	            if (file1.exists()) 
	            	file = file1;
			
			}else{
				return new Result<SunZyImages>("制作缩略图失败！照片类型或格式有误",null);
			}
			String upyunpath="/"+uImg.getFolder()+"/"+uImg.getName();
	         try {
	        	 PicBucket.initUpYun(file);
	        	 UpYun yun=PicBucket.upYunWriteFile(upyunpath, file);
	 			if(yun!=null){
	 				uImg.setWidth(yun.getPicWidth());
	 				uImg.setHeight(yun.getPicHeight());
	 				uImg.setSize(file.length());
	 			}
			} catch (IOException e) {
				e.printStackTrace();
				return new Result<SunZyImages>(MessageConstant.IMG_SAVE_FAILURE,null);
			}
		}
		uImg.setCreateTime(new Date());
		uImg.setFilePath(path+"/"+uImg.getName()+"."+uImg.getSuffix());
		uImg=imagesDao.save(uImg);
		
		
		return new Result<SunZyImages>(null,uImg);
	}
	
	public boolean minImgWH(File file,String folder){
		 BufferedImage sourceImg = null;
			try {
				sourceImg = ImageIO.read(file);
				int width= sourceImg.getWidth();
				int height=sourceImg.getHeight();
				if("works".equals(folder)){
					if(width<600||height<500){
						return true;
					}
				}
				if("head".equals(folder)){
					if(width<180||height<180){
						return true;
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return false;
	}



	public List<SunZyImages> findByIds(String[] ids) {
		return imagesDao.findByIds(ids);
	}



	public  Result<SunZyImages>  createBlessImage(SunZyPhoto photo, String title, String context) throws Exception {
		
		Map<String,String> properties = CustomPropertyConfigurer.getProperties();
		String imgPath = properties.get("imgPath")+"/"+photo.getImgId().getFolder()+"/s/"+photo.getImgId().getName();
		
		File photoFile = new File(imgPath);  
        if (!photoFile.isFile()) { 
        	 imgPath = properties.get("imgPath")+"/"+photo.getImgId().getFolder()+"/"+photo.getImgId().getName();
        	 photoFile = new File(imgPath); 
        	 if (!photoFile.isFile()) 
        	 return new Result<SunZyImages>("服务器文件未找到",null);  
        }  
		
		String fileName =  UploadImgUtil.createUUIDName()+".jpg";
		String iconPath = properties.get("imgPath")+"/bless/"+photo.getImgId().getFolder()+"/b/"+fileName;
		
		File directory = new File(properties.get("imgPath")+"/bless/"+photo.getImgId().getFolder()+"/b/");  
        directory.mkdirs();  
        if (!directory.exists()) {  
        	 return new Result<SunZyImages>("服务器文件路径错误",null);  
        }  
        directory = new File(iconPath);  
        try {
			directory.createNewFile();
		} catch (IOException e) {
			return new Result<SunZyImages>("服务器文件创建失败",null);  
		}  
        if (!directory.isFile()) {  
        	 return new Result<SunZyImages>("服务器文件未找到",null);  
        }  
        Result<SunZyImages> result ;
        try {
			//生成祝福图片
        	ImgDraw imgDraw=new ImgDraw();
			ImgDraw.textMark(imgPath, iconPath, "@\u7ed9\u70b9\u513f\u9633\u5149", "Heiti SC Light",  0.04, new Color(200,200,200),0.05,0.05, 0.5f);
			imgDraw.textMarkStroke(iconPath, iconPath,title ,  "Heiti SC Light",0.05, 0.05,0.05);
			imgDraw.textMarkStroke(iconPath, iconPath, "今日" + photo.getTitle()+photo.getSubject()+DateUtil.getDateFormat(photo.getShootingTime(), "yyyy年MM月dd日"), "Heiti SC Light", 0.025, 0.05,0.12);
	//	        new ImgDraw().textMarkStroke(iconPath, iconPath, "今天是你生日，很想和你一起过。水仙花开了，等你回来。祝生日快乐，开心永远。","宋体", 0.035, 0.05,0.05*4);
	        String[] ss = context.split("\n");
	        for (int i = 0; i < ss.length; i++) {
	        	imgDraw.textMarkStroke(iconPath, iconPath, ss[i],"Heiti SC Light", 0.035, 0.05,0.05*4+0.05*i);
			}
	        
	        //上传到图片库
	        SunZyImages uImg = new SunZyImages();
			
			uImg.setName(fileName);
			uImg.setSuffix("jpg");
			uImg.setType("bless");
			uImg.setFolder("bless/"+photo.getImgId().getFolder()+"/b");
	        String newPath=properties.get("imgPath")+"/bless/"+photo.getImgId().getFolder()+"/b/"+fileName;
	        result = saveImg(uImg, new File(newPath), true);
        } catch (Exception e) {
        	e.printStackTrace();
			return new Result<SunZyImages>("贺卡制作失败",null);  
		}  
        return result;
	}
	
}
