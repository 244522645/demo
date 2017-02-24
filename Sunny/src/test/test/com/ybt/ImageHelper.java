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
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
 
public class ImageHelper {
 
	
	
	
        /**
         * 生成缩略图 <br/>保存:ImageIO.write(BufferedImage, imgType[jpg/png/...], File);
         * 
         * @param source
         *            原图片
         * @param width
         *            缩略图宽
         * @param height
         *            缩略图高
         * @param b
         *            是否等比缩放
         * */
        public static BufferedImage Thumb(BufferedImage source, int width,
                        int height, boolean b) {
                // targetW，targetH分别表示目标长和宽
                int type = source.getType();
                BufferedImage target = null;
                double sx = (double) width / source.getWidth();
                double sy = (double) height / source.getHeight();
 
                if (b) {
                        if (sx > sy) {
                                sx = sy;
                                width = (int) (sx * source.getWidth());
                        } else {
                                sy = sx;
                                height = (int) (sy * source.getHeight());
                        }
                }
                 
                if (type == BufferedImage.TYPE_CUSTOM) { // handmade
                        ColorModel cm = source.getColorModel();
                        WritableRaster raster = cm.createCompatibleWritableRaster(width,
                                        height);
                        boolean alphaPremultiplied = cm.isAlphaPremultiplied();
                        target = new BufferedImage(cm, raster, alphaPremultiplied, null);
                } else
                        target = new BufferedImage(width, height, type);
                Graphics2D g = target.createGraphics();
                // smoother than exlax:
                g.setRenderingHint(RenderingHints.KEY_RENDERING,
                                RenderingHints.VALUE_RENDER_QUALITY);
                g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
                g.dispose();
                return target;
        }
 
        /**
         * 图片水印
         * 
         * @param imgPath
         *            待处理图片
         * @param markPath
         *            水印图片
         * @param x
         *            水印位于图片左上角的 x 坐标值
         * @param y
         *            水印位于图片左上角的 y 坐标值
         * @param alpha
         *            水印透明度 0.1f ~ 1.0f
         * */
        public static void waterMark(String imgPath, String markPath, int x, int y,
                        float alpha) {
                try {
                        // 加载待处理图片文件
                        Image img = ImageIO.read(new File(imgPath));
 
                        BufferedImage image = new BufferedImage(img.getWidth(null)+200, img
                                        .getHeight(null)+200, BufferedImage.TYPE_INT_RGB);
                        Graphics2D g = image.createGraphics();
                        
                        g.setBackground(Color.WHITE);   
                        g.clearRect(0, 0, img.getWidth(null)+200, img.getHeight(null)+200);   
                        g.setPaint(Color.RED);   
                        
                        g.drawImage(img, 0, 0, null);
 
                        // 加载水印图片文件
                        Image src_biao = ImageIO.read(new File(markPath));
                        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                                        alpha));
                        g.drawImage(src_biao, x, y, null);
                        g.dispose();
 
                        // 保存处理后的文件
                        FileOutputStream out = new FileOutputStream(imgPath);
                        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                        encoder.encode(image);
                        out.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
 
        /**
         * 文字水印
         * 
         * @param imgPath
         *            待处理图片
         * @param text
         *            水印文字
         * @param font
         *            水印字体信息
         * @param color
         *            水印字体颜色
         * @param x
         *            水印位于图片左上角的 x 坐标值
         * @param y
         *            水印位于图片左上角的 y 坐标值
         * @param alpha
         *            水印透明度 0.1f ~ 1.0f
         */
 
        public static void textMark(String imgPath, String text, Font font,
                        Color color, int x, int y, float alpha) {
                try {
                        Font Dfont = (font == null) ? new Font("宋体", 20, 13) : font;
 
                        Image img = ImageIO.read(new File(imgPath));
 
                        BufferedImage image = new BufferedImage(img.getWidth(null), img
                                        .getHeight(null), BufferedImage.TYPE_INT_RGB);
                        Graphics2D g = image.createGraphics();
 
                        g.drawImage(img, 0, 0, null);
                        g.setColor(color);
                        g.setFont(Dfont);
                        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                                        alpha));
                        g.drawString(text, x, y);
                        g.dispose();
                        FileOutputStream out = new FileOutputStream(imgPath);
                        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                        encoder.encode(image);
                        out.close();
                } catch (Exception e) {
                        System.out.println(e);
                }
        }
        
        
        /**
         * 文字水印
         * 
         * @param imgPath
         *            待处理图片
         * @param text
         *            水印文字
         * @param font
         *            水印字体信息
         * @param color
         *            水印字体颜色
         * @param x
         *            水印位于图片左上角的 x 坐标值
         * @param y
         *            水印位于图片左上角的 y 坐标值
         * @param alpha
         *            水印透明度 0.1f ~ 1.0f
         */
 
        public static void bgMark(String imgPath,Color color,String s, int width, int height,  int tw, int th,float alpha) {
                try {
 
                       
                    File file = new File(imgPath);   
                       
                    Font font = new Font("Serif", Font.BOLD, 100);   
                    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   
                    Graphics2D g2 = (Graphics2D)bi.getGraphics();   
                    g2.setBackground(Color.WHITE);   
                    g2.clearRect(0, 0, width, height);   
                    g2.setPaint(Color.RED);   
                    // 加载待处理图片文件
                    Image img = ImageIO.read(file);
                    g2.drawImage(img, 0, 0, null);
                    // 加载水印图片文件
                    Image src_biao = ImageIO.read(new File("D:/1/002.jpg"));
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                                    alpha));
                    g2.drawImage(src_biao, tw, th, null);
                    g2.dispose();
                    
                       
                    FontRenderContext context = g2.getFontRenderContext();   
                    Rectangle2D bounds = font.getStringBounds(s, context);   
                    double x = tw;   
                    double y = th;   
                    double ascent = -bounds.getY();   
                    double baseY = y + ascent;   
                       
                    g2.drawString(s, (int)x, (int)baseY);   
                       
                    ImageIO.write(bi, "jpg", file);  
                } catch (Exception e) {
                        System.out.println(e);
                }
        }
        
        public static void inntMark(String imgPath,Color color,String s, int width, int height,  int tw, int th,float alpha) {
            try {

            	  // 加载待处理图片文件
                Image img = ImageIO.read(new File(imgPath));

                BufferedImage image = new BufferedImage(img.getWidth(null)+200, img
                                .getHeight(null)+800, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                
                //画背景
                g.setBackground(Color.WHITE);   
                g.clearRect(0, 0, img.getWidth(null)+200, img.getHeight(null)+800);   
                g.setPaint(Color.RED);   
                g.drawImage(img, 100, 100, null);

                // 加载水印图片文件
                Image src_biao = ImageIO.read(new File("D:/1/002.jpg"));
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                                alpha));
                // 加载待处理图片文件
                g.drawImage(src_biao,  width-1000, height-300, null);
                
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                        0.5f));
                g.setColor(color);
                g.setFont(new Font("宋体", Font.BOLD, 90));
                g.drawString("给点阳光", 150, height-150);
                
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.5f));
                g.setColor(color);
                g.setFont(new Font("宋体", Font.BOLD, 90));
//                g.drawString(pressText, (width - (getLength(pressText) * fontSize)) / 2 + x, (height - fontSize) / 2 + y);
                g.drawString("给点阳光", 150, height-150);
                
                //开启抗锯齿
                RenderingHints renderingHints=new RenderingHints(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                //使用高质量压缩
                renderingHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_RENDER_QUALITY);
                
                g.dispose();

                
                // 保存处理后的文件
                FileOutputStream out = new FileOutputStream(imgPath);
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                encoder.encode(image);
                out.close();
                 
            } catch (Exception e) {
                    System.out.println(e);
            }
    }
        
        
        public static void main(String args[]){
  		  String srcImgPath = "D:/1/001.jpg";     
  	        String iconPath = "D:/1/002.jpg";     
  	        String targerPath = "D:/1/c.png" ;   
//  		textMark(srcImgPath, "给点儿阳光就灿烂", new Font("宋体", 20, 13),new Color(0,0,0), 200, 300,0.5f);
  	       
  	        File imgfile = new File("D:/1/2/1.jpg"); 
  	        int w = 1000;
  	        int h = 1000;
              try { 
                  FileInputStream fis = new FileInputStream(imgfile); 
  		        BufferedImage buff = ImageIO.read(imgfile); 
  		         w = buff.getWidth() ;
  		         h = buff.getHeight() ;
  		        System.out.println(w);
              }catch(Exception e){
              	e.printStackTrace(); 
  			}
//              waterMark("D:/1/2/1.jpg", "D:/1/2/a.jpg", w-1000, h-300, 0.9f);
//              textMark("D:/1/2/1.jpg", "2016年7月1日", new Font("宋体", Font.BOLD, 90),new Color(225,225,225), w-650, h-150,1f);
             // textMark("D:/1/2/1.jpg", "给点阳光", new Font("宋体", Font.BOLD, 90),new Color(225,225,225), 50, h-150,0.5f);
             // bgMark("D:/1/2/image.jpg", new Color(0,0,0), "给点儿阳光就灿烂", w+100, h+500, w+100, h+500, 0.5f);
              inntMark("D:/1/2/1.jpg", new Color(0,0,0), "给点儿阳光就灿烂", w, h, w-300, h-300, 1);
              
              System.out.println("00000000000");
  		
  	}
}