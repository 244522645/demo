package com.ybt.common.util;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;



/**   
 * 祝贺图片生成 
 * @author AndyBao  
 * @version 4.0, 2016年6月24日 下午2:06:25   
 */   
@SuppressWarnings("serial")
public class ImgDraw extends JComponent{


  public  void textDraw( Graphics2D gg ,Font f ,String text ,int x,int y){
		  	GlyphVector v = f.createGlyphVector(getFontMetrics(f).getFontRenderContext(), text);
		    Shape shape = v.getOutline();
		 
		    Rectangle bounds = shape.getBounds();
		    
		    int fx= x - bounds.x;
		    int fy= y - bounds.y;
		    gg.translate(fx,fy);
		    gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    gg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.1f));
		    gg.setColor(Color.WHITE.darker().darker());
		    gg.setStroke(new BasicStroke(8));
		    gg.draw(shape);
		    
		    /*		    
		    Font font = new Font("Serif", Font.BOLD, 100);   
		     
            FontRenderContext context = gg.getFontRenderContext();   

             Rectangle2D bounds2 = font.getStringBounds(text, context); 
            gg.setFont(font);
		    gg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.9f));
		    gg.drawString(text, fx, fy);   
		     */
		    
		    gg.clip(shape);
		    
		    gg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1f));
		    gg.setColor(Color.WHITE);
		    gg.fill(shape);
		    gg.setStroke(new BasicStroke(1));
		    gg.draw(shape);
		    gg.clip(shape);
		   /// gg.dispose();
		    
	  }
  /*
   * 一次性 全部画 效率棒
   */
  protected  void allMark(String imgPath,String newImgPath,String title, String photoTitle ,String content) throws Exception {
		
	  	if(imgPath == null || newImgPath == null)
	  		return;
		  	 Image img = ImageIO.read(new File(imgPath));
		  	 
	         BufferedImage image = new BufferedImage(img.getWidth(null), img
	                         .getHeight(null), BufferedImage.TYPE_INT_RGB);
	
			Graphics2D gg = image.createGraphics();
			 //画背景
	//        gg.setBackground(Color.WHITE);   
	//        gg.clearRect(0, 0, image.getWidth(null), image.getHeight(null));   
	//        gg.setPaint(Color.RED);   
	        gg.drawImage(img, 0, 0, null);
	        
			Font f = new Font("Heiti SC Light", Font.BOLD, 40);
			//,Font f  f = f == null ? new Font("Adobe 黑体 Std", Font.BOLD, 40) : f;
			
			int y=img.getHeight(null);
			textDraw(gg, f, title, y/20, y/20);
			 f = new Font("Heiti SC Light", Font.BOLD, 20);
			 
			 
		 	img = ImageIO.read(new File(newImgPath));
	        image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
	
			gg = image.createGraphics();
	        gg.drawImage(img, 0, 0, null);
			textDraw(gg, f, photoTitle, y/20, (y/20)+(y/20)+(y/20));
			 f = new Font("Heiti SC Light", Font.BOLD, 20);
			 
		 	 img = ImageIO.read(new File(newImgPath));
	         image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
	
			gg = image.createGraphics();
	        gg.drawImage(img, 0, 0, null);
			textDraw(gg, f, content, y/20, (y/20)+(y/20)+(y/20));
			gg.dispose();
		 // 保存处理后的文件
			
			FileOutputStream out  = new FileOutputStream(newImgPath);
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	        encoder.encode(image);
	        out.close();
	        
	  }
  
  /**  
   * 文字阴影描边  
   * （只能画一次，需要改进）
 * @param imgPath
 * @param newImgPath
 * @param text
 * @param x
 * @param y  
 * @author AndyBao
 * @version V4.0, 2016年6月24日 下午1:56:20 
 * @param font 
 */
public  void textMarkStroke(String imgPath,String newImgPath,String text,String font,Double fontSize, Double x,Double y) {
		
	  	if(imgPath == null || newImgPath == null)
	  		return;
	  	try { 
		  	 Image img = ImageIO.read(new File(imgPath));
		  	 Font f =new Font(font, Font.BOLD, (int) (img.getHeight(null)*fontSize));
		  	 
	         BufferedImage image = new BufferedImage(img.getWidth(null), img
	                         .getHeight(null), BufferedImage.TYPE_INT_RGB);
	
			Graphics2D gg = image.createGraphics();
	        gg.drawImage(img, 0, 0, null);
	        
			//Font f = new Font("Adobe 黑体 Std", Font.BOLD, 40);
			//,Font f  f = f == null ? new Font("Adobe 黑体 Std", Font.BOLD, 40) : f;
			
			GlyphVector v = f.createGlyphVector(getFontMetrics(f).getFontRenderContext(), text);
		    Shape shape = v.getOutline();
		 
		    Rectangle bounds = shape.getBounds();
		    
		    int fx= (int) (img.getHeight(null)* x - bounds.x);
		    int fy= (int) (img.getHeight(null)*y - bounds.y);
		    gg.translate(fx,fy);
		    gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    gg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.2f));
		    gg.setColor(Color.WHITE.darker().darker());
		    gg.setStroke(new BasicStroke(8));
		    gg.draw(shape);
		    
		    
		    gg.clip(shape);
		    
		    gg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1f));
		    gg.setColor(Color.WHITE);
		    gg.fill(shape);
		    gg.setStroke(new BasicStroke(1));
		    gg.draw(shape);
		    gg.clip(shape);
			gg.dispose();
		 // 保存处理后的文件
			
			FileOutputStream out  = new FileOutputStream(newImgPath);
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	        encoder.encode(image);
	        out.close();
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	  }
  
	/**
	 * 文字水印
	 * 
	 * @param imgPath
	 *            待处理图片
	  * @param markPath
	 *            生成图片
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
	 * @throws Exception 
	 */
	
	public static void textMark(String imgPath,String markPath, String text, String font,Double fontSize,
            Color color, Double x, Double y, float alpha) throws Exception {
		
		 	Image img =null; 
    		try{
    			  img = ImageIO.read(new File(imgPath));
    		}catch(IIOException e){
    			e.printStackTrace(); 
    			//cmyk 转 rgb
    			try{
	                 CMYK cm = new CMYK();
	                 cm.readImage(imgPath);
	                 img = ImageIO.read(new File(imgPath));
    			}catch (Exception e1){ 
    				e1.printStackTrace(); 
    				throw new Exception();
			    }
    			/* try 
    			    { 
    			     ThumbnailConvert tc = new ThumbnailConvert(); 
    			     tc.setCMYK_COMMAND(imgPath); 
    			     img = Toolkit.getDefaultToolkit().getImage(imgPath); 
    			       MediaTracker mediaTracker = new MediaTracker(new Container()); 
    			     mediaTracker.addImage(img, 0); 
    			     mediaTracker.waitForID(0); 
    			     img.getWidth(null); 
    			     img.getHeight(null); 
    			    }catch (Exception e1){ 
    			     e1.printStackTrace(); 
    			    } */
    		}
            
             Font Dfont = (font == null) ? new Font("Heiti SC Light", 20, 13) : new Font(font, Font.BOLD, (int) (img.getHeight(null)*fontSize));;
              
            BufferedImage image = new BufferedImage(img.getWidth(null), img
                            .getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();

            g.drawImage(img, 0, 0, null);
            g.setColor(color);
            g.setFont(Dfont);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                            alpha));
           // g.drawString(text, (int)(img.getHeight(null)* y),(int)( img.getHeight(null) - (int)img.getHeight(null)*y));
            g.drawString(text, (int)(img.getWidth(null)-img.getHeight(null)* fontSize * 7),(int)( img.getHeight(null) - (int)img.getHeight(null)*y/4));

            // 加载水印图片文件
          /*  Image src_biao = ImageIO.read(new File(markPath));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                            alpha));
            g.drawImage(src_biao, x, y, null);*/
            
//	                markPath = "D:/var/www/html01/static/images/works/2016/06/b/c6f5284b8e984df581e97e52a7ce5171.jpg";
            g.dispose();
           
    			
    			FileOutputStream out = new FileOutputStream(markPath);
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                encoder.encode(image);
                out.close();
          
	}

	
	  public static void main(String args[]){
			  String srcImgPath = "D:/1/3/2.jpg";     
		        String iconPath = "D:/1/3/22.jpg"; 
		        String context = "今天是你生日，\n"
								+"很  想 和  你一起过。\n"
								+ "水仙  花 开了\n"
								+"等你回 来 。祝  生 日快乐，开心永远。";
		        
		        context=context.replaceAll(" ", "");
		        
		        
		        try {
		        	ImgDraw imgDraw=new ImgDraw();
		        	ImgDraw.textMark(srcImgPath, iconPath, "2016/08/04", "Heiti SC Light",  0.04, new Color(200,200,200),0.05,0.05, 0.7f);
				
					//imgDraw.textMarkStroke(iconPath, iconPath, "马化腾 祝：马云", "Heiti SC Light", 0.05, 0.05,0.05);
					//imgDraw.textMarkStroke(iconPath, iconPath, "今日青岛日出 2016年2月2", "Heiti SC Light", 0.025, 0.05,0.12);
	//		        new ImgDraw().textMarkStroke(iconPath, iconPath, "今天是你生日，很想和你一起过。水仙花开了，等你回来。祝生日快乐，开心永远。","宋体", 0.035, 0.05,0.05*4);
			        String[] ss = context.split("\n");
			        for (int i = 0; i < ss.length; i++) {
			        	imgDraw.textMarkStroke(iconPath, iconPath, ss[i],"Heiti SC Light", 0.035, 0.05,0.05*4+0.05*i);
					}
					
		        
		        } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
	  }
	  
}