package com.ybt.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

public class UploadImgUtil {
	
	public static List<String> fileTypes; //可上传的图片后缀
	
	static{
		fileTypes = new ArrayList<String>();
		fileTypes.add("jpg");
		fileTypes.add("jpeg");
		fileTypes.add("png");
		fileTypes.add("gif");
		fileTypes.add("blob");//在分割上传图片时使用这个后缀
	}
	
	//生成uuid随机的名称加后缀
	public static String createUUIDNameAddSuffix(String fileName){
		if(fileName==null||fileName.equals(""))
			return "";
		try{
			String suffix = getSuffix(fileName);
			String f_Name = UUID.randomUUID().toString().replaceAll("-", "");
			return f_Name +="."+suffix;  
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	//生成uuid随机的名称
	public static String createUUIDName(){
			return  UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	//获取后缀
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
	
	public static File createFolder(String fileName,String path){
		 File file = null;  
		 File firstFolder = new File(path);
		 if (!firstFolder.exists()) {
			 firstFolder.mkdirs();
			}
		 if(firstFolder.exists()) {                             
			 file = new File(firstFolder,fileName);
         }else {                                                
             firstFolder.mkdir(); 
             file = new File(firstFolder,fileName);
         }  
         return file; 
	}
	
	/** 
     * 检测与创建一级、二级文件夹、文件名 
            这里我通过传入的两个字符串来做一级文件夹和二级文件夹名称 
           通过此种办法我们可以做到根据用户的选择保存到相应的文件夹下 
            
     */  
    public static File createFolderByTypeName(String typeName,String brandName,String fileName,String path) {  
         File file = null;  
         typeName = typeName.replaceAll("/", "");               //去掉"/"  
         typeName = typeName.replaceAll(" ", "");               //替换半角空格  
         typeName = typeName.replaceAll(" ", "");               //替换全角空格  
          
         brandName = brandName.replaceAll("/", "");             //去掉"/"  
         brandName = brandName.replaceAll(" ", "");             //替换半角空格  
         brandName = brandName.replaceAll(" ", "");             //替换全角空格  
          
         File firstFolder = new File(path+"/" + typeName);         //一级文件夹  
         if(firstFolder.exists()) {                             //如果一级文件夹存在，则检测二级文件夹  
             File secondFolder = new File(firstFolder,brandName);  
             if(secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件  
                 file = new File(secondFolder,fileName);  
             }else {                                            //如果二级文件夹不存在，则创建二级文件夹  
                 secondFolder.mkdir();  
                 file = new File(secondFolder,fileName);        //创建完二级文件夹后，再合建文件  
             }  
         }else {                                                //如果一级不存在，则创建一级文件夹  
             firstFolder.mkdir();  
             File secondFolder = new File(firstFolder,brandName);  
             if(secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件  
                 file = new File(secondFolder,fileName);  
             }else {                                            //如果二级文件夹不存在，则创建二级文件夹  
                 secondFolder.mkdir();  
                 file = new File(secondFolder,fileName);  
             }  
         }  
         return file;  
    }
	
    /**
     * byte[] 数组保存到图片
     * @throws IOException 
     * @throws FileNotFoundException 
     * */
	public static boolean byte2image(byte[] data, File file)  {
		if (data.length < 3 || file == null)
			return false;
		FileImageOutputStream imageOutput = null;
		try {
			imageOutput = new FileImageOutputStream(file);
			imageOutput.write(data, 0, data.length);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			if(imageOutput!=null)
				try {
					imageOutput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
	//转图片
	public static BufferedImage getImage(File file){
		 BufferedImage sourceImg = null;
			try {
				sourceImg = ImageIO.read(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return sourceImg;
	}
}
