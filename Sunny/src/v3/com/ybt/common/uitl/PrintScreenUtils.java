/*   
 * Copyright (c) 2015-2020 *** Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * ***. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with ***.   
 *   
 */     
package com.ybt.common.uitl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.constant.Wechat;
import com.ybt.common.util.UploadImgUtil;
import com.ybt.model.work.SunZyImages;
import com.ybt.service.work.ImagesService;
import com.ybt.service.work.impl.ImagesServiceImpl;

import chrriis.dj.nativeswing.swtimpl.NativeComponent;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import wechat.api.MediaAPI;
import wechat.api.MediaAPI.MediaType;
import wechat.api.MessageAPI;
import wechat.bean.Media;
import wechat.bean.message.ImageMessage;
import wechat.support.TokenManager;

/**   
 * 模拟游览器 截屏 工具
 * @author AndyBao  
 * @version 4.0, 2016年9月28日 下午3:58:46   
 */   
public class PrintScreenUtils extends JPanel {

	private static final long serialVersionUID = 1L;  
    // 行分隔符  
    final static public String LS = System.getProperty("line.separator", "/n");  
    // 文件分割符  
    final static public String FS = System.getProperty("file.separator", "//");  
    // 当网页超出目标大小时  截取
    final static public int maxWidth = 2000;  
    final static public int maxHeight = 1400;  
    public  PrintScreenUtils(){}
	/**  
	 * @param file  预生成的图片全路径
	 * @param url   网页地址
	 * @param width  打开网页宽度 ，0 = 全屏
	 * @param height 打开网页高度 ，0 = 全屏
	 * @return  boolean
	 * @author AndyBao
	 * @version V4.0, 2016年9月28日 下午3:55:52 
	 */
	public  PrintScreenUtils(final String file,final String url,final String userid,final String WithResult){
		  super(new BorderLayout());  
	        JPanel webBrowserPanel = new JPanel(new BorderLayout());  
	        final JWebBrowser webBrowser = new JWebBrowser(null);  
	        webBrowser.setBarsVisible(false);  
	        webBrowser.navigate(url);  
	        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);  
	        add(webBrowserPanel, BorderLayout.CENTER);  
	        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));  
	        webBrowser.addWebBrowserListener(new WebBrowserAdapter() {  
	            // 监听加载进度  
	            public void loadingProgressChanged(WebBrowserEvent e) {  
	                // 当加载完毕时  
	                if (e.getWebBrowser().getLoadingProgress() == 100) {  
	                    String result = (String) webBrowser.executeJavascriptWithResult(WithResult);  
	                    int index = result == null ? -1 : result.indexOf(":");  
	                    NativeComponent nativeComponent = webBrowser  
	                            .getNativeComponent();  
	                    Dimension originalSize = nativeComponent.getSize();  
	                    Dimension imageSize = new Dimension(Integer.parseInt(result  
	                            .substring(0, index)), Integer.parseInt(result  
	                            .substring(index + 1)));  
	                    imageSize.width = Math.max(originalSize.width,  
	                            imageSize.width + 50);  
	                    imageSize.height = Math.max(originalSize.height,  
	                            imageSize.height + 50);  
	                    nativeComponent.setSize(imageSize);  
	                    BufferedImage image = new BufferedImage(imageSize.width,  
	                            imageSize.height, BufferedImage.TYPE_INT_RGB);  
	                    nativeComponent.paintComponent(image);  
	                    nativeComponent.setSize(originalSize);  
	                    // 当网页超出目标大小时  
	                    if (imageSize.width > maxWidth  
	                            || imageSize.height > maxHeight) {  
	                        //截图部分图形  
	                        image = image.getSubimage(0, 0, maxWidth, maxHeight);  
	                      //  此部分为使用缩略图 
	                       /* int width = image.getWidth(), height = image 
	                            .getHeight(); 
	                         AffineTransform tx = new AffineTransform(); 
	                        tx.scale((double) maxWidth / width, (double) maxHeight 
	                                / height); 
	                        AffineTransformOp op = new AffineTransformOp(tx, 
	                                AffineTransformOp.TYPE_NEAREST_NEIGHBOR); 
	                        //缩小 
	                        image = op.filter(image, null);  */
	                    }  
	                   /* try{
	                    	 byte data[]=  imageToBytes(image,"jpg");
	                    	 System.out.println(1);
	                         InputStream sbs = new ByteArrayInputStream(data); 
	                         System.out.println(data);
	                 		Media media = MediaAPI.mediaUpload(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET) , MediaType.image, sbs);
	                 		 System.out.println(3);
	                 		if(media.getMedia_id()!=null){
	                 			ImageMessage message= new ImageMessage(userid, media.getMedia_id());
	                 			 System.out.println(4);
	                 			MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), message);
	                 			 System.out.println(5);
	                 		}
	                    }catch(Exception ee){
	                    	ee.printStackTrace();
	                    }*/
	                    System.out.println(6);
	                    //iamgeSercie = new ImagesServiceImpl();
	                    //iamgeSercie.downImgByte(imageToBytes(image,"jpg"), "jpg");
	                    saveFile(image,userid);
	                    /*try {  
	                        // 输出图像  
	                    	File files =new File(file);
	                      
	                    	  Media media = MediaAPI.mediaUpload(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET) , MediaType.image, files);
		                 		 System.out.println(3);
		                 		if(media.getMedia_id()!=null){
		                 			ImageMessage message= new ImageMessage(userid, media.getMedia_id());
		                 			 System.out.println(4);
		                 			MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), message);
		                 			 System.out.println(5);
		                 		}
	                     
	                    } catch (IOException ex) {  
	                        ex.printStackTrace();  
	                    }  */
	                    // 退出操作  
	                    System.exit(0);  
	                }  
	            }  
	        }  
	        );  
	        add(panel, BorderLayout.SOUTH);  
		
	}
	public void saveFile(BufferedImage image,String userid ){
		SunZyImages uImg = new SunZyImages();
		String name = "";
			name =  UploadImgUtil.createUUIDName();
		String fileName = name+".jpg";
		uImg.setName(fileName);
		uImg.setSuffix(".jpg");
		String datepath = new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
		uImg.setFolder("wjqw/share/"+ datepath);
		uImg.setType("wjqw/share/");
		String path =  uImg.path+"/" +uImg.getFolder(); 
		File destFile = UploadImgUtil.createFolder(fileName,path);
		 try {
			ImageIO.write(image, "jpg", destFile);
			 Media media = MediaAPI.mediaUpload(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET) , MediaType.image, destFile);
     		 System.out.println(3);
     		if(media.getMedia_id()!=null){
     			ImageMessage message= new ImageMessage(userid, media.getMedia_id());
     			 System.out.println(4);
     			MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), message);
     			 System.out.println(5);
     		}
     		
     		 if (destFile.isFile() && destFile.exists()) {  
     			destFile.delete();  
     	    }  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	 //以javascript脚本获得网页全屏后大小  
	public static String getScreenWidthHeight(){
		 
	    	StringBuffer  jsDimension = new StringBuffer();  
	        jsDimension.append("var width = 0;").append(LS);  
	        jsDimension.append("var height = 0;").append(LS);  
	        jsDimension.append("if(document.documentElement) {").append(LS);  
	        jsDimension.append(  
	                        "  width = Math.max(width, document.documentElement.scrollWidth);")  
	                .append(LS);  
	        jsDimension.append(  
	                        "  height = Math.max(height, document.documentElement.scrollHeight);")  
	                .append(LS);  
	        jsDimension.append("}").append(LS);  
	        jsDimension.append("if(self.innerWidth) {").append(LS);  
	        jsDimension.append("  width = Math.max(width, self.innerWidth);")  
	                .append(LS);  
	        jsDimension.append("  height = Math.max(height, self.innerHeight);")  
	                .append(LS);  
	        jsDimension.append("}").append(LS);  
	        jsDimension.append("if(document.body.scrollWidth) {").append(LS);  
	        jsDimension.append(  
	                "  width = Math.max(width, document.body.scrollWidth);")  
	                .append(LS);  
	        jsDimension.append(  
	                "  height = Math.max(height, document.body.scrollHeight);")  
	                .append(LS);  
	        jsDimension.append("}").append(LS);  
	        jsDimension.append("return width + ':' + height;");  
	        
	       return jsDimension.toString();
	}
	
	public static  boolean printUrlScreen2jpg(final String file,final String url,String userid, final int width,final int height){
		
		 NativeInterface.open();  
	        SwingUtilities.invokeLater(new Runnable() {  
	            public void run() {  
	            	String withResult="var width = "+width+";var height = "+height+";return width +':' + height;";
	        		if(width==0||height==0)
	        			withResult=getScreenWidthHeight();
	        			
	                // SWT组件转Swing组件，不初始化父窗体将无法启动webBrowser  
	                JFrame frame = new JFrame("网页截图");  
	                // 加载指定页面，最大保存为640x480的截图  
	                frame.getContentPane().add(  
	                        new PrintScreenUtils(file,url,userid, withResult),  
	                        BorderLayout.CENTER);  
	                frame.setSize(640, 480);  
	                // 仅初始化，但不显示  
	                frame.invalidate();  
	                
	                frame.pack();  
	                frame.setVisible(false);  
	            }  
	        });  
	        NativeInterface.runEventPump();  
	        
		return true;
	}
	public  byte[] imageToBytes(BufferedImage bImage, String format) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		ImageIO.write(bImage, format, out);
		} catch (IOException e) {
		e.printStackTrace();
		}
		return out.toByteArray();
		}
	/*public static void main(String[] args) {  
		 
	boolean aaa= 	PrintScreen4DJNativeSwingUtils.printUrlScreen2jpg("ddd.jpg", "http://1533v3393q.iask.in/Sunny/wechat/v3/crow/share?userid=oWL5RuJoTvOo2ZyHwLfafhE3B3-M","oWL5RuJoTvOo2ZyHwLfafhE3B3-M", 414, 736);
	} */ 
}
  