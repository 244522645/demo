/*   
 * Copyright (c) 2015-2020 *** Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * ***. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with ***.   
 *   
 */     
package test.com.ybt;    
    
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
    
/**   
 * 图片加水印，设置透明度 
 * http://blog.csdn.net/hfmbook 
 * @author Gary  
 * 创建日期：2014年12月16日 22:45:17 
 */    
public class ImageMarkLogoByIcon {     
    
    /**   
     * @param args   
     */    
    public static void main(String[] args) {     
        String srcImgPath = "D:/1/001.jpg";     
        String iconPath = "D:/1/002.jpg";     
        String targerPath = "D:/1/c.png" ;   
         // 给图片添加水印     
       // ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath , -45);    
        
        try {
        int width = 300;
        int height = 400;
        Font font = new Font("宋体", Font.BOLD, 90);
        
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setBackground(new Color(255, 255, 255));
        g2.clearRect(0, 0, width, height);//
        g2.setFont(font); // 
        g2.setPaint( new Color(0,0,0)); //
        FontRenderContext context = g2.getFontRenderContext(); // 
        Rectangle2D bounds = font.getStringBounds("ss是的发生时打发", context);// 
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);//
        g2.dispose();
        FileOutputStream out;
		
			out = new FileOutputStream( new File("D:/1/3/1.jpg") );
		
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
	        encoder.encode(bi);   
	        out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        
        
        
    }     
    /**   
     * 给图片添加水印   
     * @param iconPath 水印图片路径   
     * @param srcImgPath 源图片路径   
     * @param targerPath 目标图片路径   
     */    
    public static void markImageByIcon(String iconPath, String srcImgPath,     
            String targerPath) {     
        markImageByIcon(iconPath, srcImgPath, targerPath, null) ;   
    }     
    /**   
     * 给图片添加水印、可设置水印图片旋转角度   
     * @param iconPath 水印图片路径   
     * @param srcImgPath 源图片路径   
     * @param targerPath 目标图片路径   
     * @param degree 水印图片旋转角度 
     */    
    public static void markImageByIcon(String iconPath, String srcImgPath,     
            String targerPath, Integer degree) {     
        OutputStream os = null;     
        try {     
            Image srcImg = ImageIO.read(new File(srcImgPath));   
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),     
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);   
            // 得到画笔对象     
            // Graphics g= buffImg.getGraphics();     
            Graphics2D g = buffImg.createGraphics();     
    
            // 设置对线段的锯齿状边缘处理     
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,     
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);     
    
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg     
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);     
    
            if (null != degree) {     
                // 设置水印旋转     
                g.rotate(Math.toRadians(degree),     
                        (double) buffImg.getWidth() / 2, (double) buffImg     
                                .getHeight() / 2);     
            }     
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度    
            ImageIcon imgIcon = new ImageIcon(iconPath);     
            // 得到Image对象。     
            Image img = imgIcon.getImage();     
            float alpha = 0.2f; // 透明度     
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,     
                    alpha));     
            // 表示水印图片的位置     
            g.drawImage(img, 150, 300, null);     
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));     
            g.dispose();     
            os = new FileOutputStream(targerPath);     
            // 生成图片     
            ImageIO.write(buffImg, "JPG", os);     
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if (null != os)     
                    os.close();     
            } catch (Exception e) {     
                e.printStackTrace();     
            }     
        }     
    }     
}   