package com.ybt.common.util;


import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifDirectory;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
 
/******************************************************************************* 
* 缩略图类（通用） 本java类能将jpg、bmp、png、gif图片文件，进行等比或非等比的大小转换。 具体使用方法 
* compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true)) 
*/  
public class ImgUtil {   
    private File file = null; // 文件对象   
    private String inputDir; // 输入图路径  
    private String outputDir; // 输出图路径  
    private String inputFileName; // 输入图文件名  
    private String outputFileName; // 输出图文件名  
    private int outputWidth = 100; // 默认输出图片宽  
    private int outputHeight = 100; // 默认输出图片高  
    private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)  
    public ImgUtil() { // 初始化变量  
        inputDir = "";   
        outputDir = "";   
        inputFileName = "";   
        outputFileName = "";   
        outputWidth = 100;   
        outputHeight = 100;   
    }   
    public void setInputDir(String inputDir) {   
        this.inputDir = inputDir;   
    }   
    public void setOutputDir(String outputDir) {   
        this.outputDir = outputDir;   
    }   
    public void setInputFileName(String inputFileName) {   
        this.inputFileName = inputFileName;  
    }   
    public void setOutputFileName(String outputFileName) {   
        this.outputFileName = outputFileName;   
    }   
    public void setOutputWidth(int outputWidth) {  
        this.outputWidth = outputWidth;   
    }   
    public void setOutputHeight(int outputHeight) {   
        this.outputHeight = outputHeight;   
    }   
    public void setWidthAndHeight(int width, int height) {   
        this.outputWidth = width;  
        this.outputHeight = height;   
    }   
      
    /*  
     * 获得图片大小  
     * 传入参数 String path ：图片路径  
     */   
    public long getPicSize(String path) {   
        file = new File(path);   
        return file.length();   
    }  
      
    // 图片处理   
	public boolean compressPic() {   
    	FileOutputStream out = null;
        try {   
            //获得源文件   
            file = new File(inputDir + inputFileName);   
            if (!file.exists()) {   
                return false;   
            }   
            Image img =   null;
            try{
  			  img =  ImageIO.read(file);
            }catch(Exception e){
	  			e.printStackTrace(); 
	  			//cmyk 转 rgb
	  			try{
		                 CMYK cm = new CMYK();
		                 cm.readImage(file.getPath());
		                 img = ImageIO.read(file);
		                 
	  			}catch (Exception e1){ 
				     e1.printStackTrace(); 
				     return false;   
				}
            }
            // 判断图片格式是否正确   
            if (img.getWidth(null) == -1) {  
                System.out.println(" can't read,retry!" + "<BR>");   
                return false;   
            } else {   
                int newWidth; int newHeight;   
                // 判断是否是等比缩放   
                if (this.proportion == true) {   
                    // 为等比缩放计算输出的图片宽度及高度   
                    double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;   
                    double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;   
                    // 根据缩放比率大的进行缩放控制   
                    double rate = rate1 > rate2 ? rate1 : rate2;   
                    newWidth = (int) (((double) img.getWidth(null)) / rate);   
                    newHeight = (int) (((double) img.getHeight(null)) / rate);   
                } else {   
                    newWidth = outputWidth; // 输出的图片宽度   
                    newHeight = outputHeight; // 输出的图片高度   
                }   
               BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);   
                 
               /* 
                * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 
                * 优先级比速度高 生成的图片质量比较好 但速度慢 
                */   
               tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);  
               File firstFolder = new File(outputDir); //如果没有文件夹，创建一个
               if(!firstFolder.exists()){
            	   firstFolder.mkdir();  
               }
               out = new FileOutputStream(outputDir + outputFileName);  
               //JPEGImageEncoder可适用于其他图片类型的转换   
               JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
               encoder.encode(tag);  
               //此处用 ImageIO.write 替换 JPEGCodec.createJPEGEncoder 解决 liunx 下报错 
               //ImageIO.write(tag,outputDir + outputFileName, out);
            }   
            
        } catch (IOException ex) {   
            ex.printStackTrace();  
            return false;  
        }finally{
        	try {
        		if(out != null)
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
        }   
        return true;   
   }   
   public boolean compressPic (String inputDir, String outputDir, String inputFileName, String outputFileName) {
	   //判断图片旋转信息
	   int i = getRotateAngleForPhoto(inputDir+inputFileName);
	   if(i==0){ //如果需要旋转 旋转一下 本图
		   String[] suffix = inputFileName.split("\\.");
		   rotatePhonePhoto(inputDir+inputFileName,suffix[1],i);
	   }
       // 输入图路径   
       this.inputDir = inputDir;   
       // 输出图路径   
       this.outputDir = outputDir;   
       // 输入图文件名   
       this.inputFileName = inputFileName;   
       // 输出图文件名  
       this.outputFileName = outputFileName;   
       return compressPic();   
   }   
   
   public boolean compressPic(String inputDir, String outputDir, String inputFileName, String outputFileName, int width, int height, boolean gp) {  
	   //判断图片旋转信息
	  /* int i = getRotateAngleForPhoto(inputDir+inputFileName);
	   if(i!=0){ //如果需要旋转 旋转一下 本图
		   String[] suffix = inputFileName.split("\\.");
		   rotatePhonePhoto(inputDir+inputFileName,suffix[1],i);
	   }*/
       // 输入图路径   
       this.inputDir = inputDir;   
       // 输出图路径   
       this.outputDir = outputDir;   
       // 输入图文件名   
       this.inputFileName = inputFileName;   
       // 输出图文件名   
       this.outputFileName = outputFileName;   
       // 设置图片长宽  
       setWidthAndHeight(width, height);   
       // 是否是等比缩放 标记   
       this.proportion = gp;   
       return compressPic();   
   }   
   
   /**
    * 获取图片正确显示需要旋转的角度（顺时针）
    * @return
    */
   public static int getRotateAngleForPhoto(String filePath){
       
       File file = new File(filePath);
       
       int angle = 0;
       
       Metadata metadata;
       try {
           metadata = JpegMetadataReader.readMetadata(file);
           Directory directory = metadata.getDirectory(ExifDirectory.class);
               if(directory.containsTag(ExifDirectory.TAG_ORIENTATION)){ 
               
                 // Exif信息中方向　　
                  int orientation = directory.getInt(ExifDirectory.TAG_ORIENTATION); 
                  
                  // 原图片的方向信息
                  if(6 == orientation ){
                      //6旋转90
                      angle = 90;
                  }else if( 3 == orientation){
                     //3旋转180
                      angle = 180;
                  }else if( 8 == orientation){
                     //8旋转90
                      angle = 270;
                  }
               }  
       } catch (JpegProcessingException e) {
           e.printStackTrace();
       } catch (MetadataException e) {
           e.printStackTrace();
       }
      
       return angle;
   }


   /**
    * 旋转手机照片
    * @return
    */
   public static String rotatePhonePhoto(String fullPath,String suffix, int angel){
       
       BufferedImage src;
       try {
           src = ImageIO.read(new File(fullPath));
           
           int src_width = src.getWidth(null);
           int src_height = src.getHeight(null);
           
           Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);

           BufferedImage res = new BufferedImage(rect_des.width, rect_des.height,BufferedImage.TYPE_INT_RGB);
           Graphics2D g2 = res.createGraphics();

           g2.translate((rect_des.width - src_width) / 2,
                   (rect_des.height - src_height) / 2);
           g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

           g2.drawImage(src, null, null);
           
           ImageIO.write(res, suffix, new File(fullPath));
           
       } catch (IOException e) {
           
           e.printStackTrace();
       }  
       
       return fullPath;
       
   }
   
   public static Rectangle CalcRotatedSize(Rectangle src, int angel) {  
       // if angel is greater than 90 degree, we need to do some conversion  
       if (angel >= 90) {  
           if(angel / 90 % 2 == 1){  
               int temp = src.height;  
               src.height = src.width;  
               src.width = temp;  
           }  
           angel = angel % 90;  
       }  
 
       double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;  
       double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;  
       double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;  
       double angel_dalta_width = Math.atan((double) src.height / src.width);  
       double angel_dalta_height = Math.atan((double) src.width / src.height);  
 
       int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha  
               - angel_dalta_width));  
       int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha  
               - angel_dalta_height));  
       int des_width = src.width + len_dalta_width * 2;  
       int des_height = src.height + len_dalta_height * 2;  
       return new java.awt.Rectangle(new Dimension(des_width, des_height));  
   }  
     
   // main测试   
   // compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))  
   public static void main(String[] arg) {   
	   ImgUtil mypic = new ImgUtil();   
       System.out.println("输入的图片大小：" + mypic.getPicSize("C:/mywork/union/1.jpg")/1024 + "KB");   
       mypic.compressPic("C:/mywork/union/", "C:/mywork/union/s/", "1.jpg", "1-s1.jpg", 252, 189, false);  
       System.out.println("输出的图片大小：" + mypic.getPicSize("C:/mywork/union/s/1-s1.jpg")/1024 + "KB");   
   }   
}  