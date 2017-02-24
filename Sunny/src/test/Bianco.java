import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class Bianco extends JPanel{
	 
	  public static void main(String[] args) {
	 
	    SwingUtilities.invokeLater(new Runnable() {
	 
	      @Override
	      public void run() {
	 
	        JFrame test = new JFrame("Test");
	 
	        test.setContentPane(new Bianco());
	        test.pack();
	        test.setLocationRelativeTo(null);
	        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        test.setVisible(true);
	      }
	    });
	  }
	 
	  Bianco() {
	 
	    setPreferredSize(new Dimension(1200, 800));
	  }
	 
	  @Override
	  protected void paintComponent(Graphics g) {
	 
	    Font f = new Font("Adobe 黑体 Std", Font.BOLD, 40);
	    GlyphVector v = f.createGlyphVector(getFontMetrics(f).getFontRenderContext(), "祝：某某");
	    Shape shape = v.getOutline();
	 
	    Rectangle bounds = shape.getBounds();
	 
	    Graphics2D gg = (Graphics2D) g;
	    
	    // 加载水印图片文件
        Image src_biao = null;
		try {
			src_biao = ImageIO.read(new File("D:/1/3/1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 加载待处理图片文件
        g.drawImage(src_biao, 0, 0, null);
	    
	    int fx=(getHeight() - bounds.height) / 20 - bounds.x;
	    int fy=(getHeight() - bounds.height) / 20 - bounds.y;
	    gg.translate(
//	            (getWidth() - bounds.width) / 2 - bounds.x,
//	            (getHeight() - bounds.height) / 2 - bounds.y
	    		(getHeight() - bounds.height) / 20 - bounds.x,
	            (getHeight() - bounds.height) / 20 - bounds.y
	    );
	    

        //通过该方法使图形去除锯齿状 
	    gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//	    gg.setColor(Color.WHITE);
	    gg.setColor(Color.black);
	    gg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.3f));
	    //字体颜色
	    gg.fill(shape);
	    gg.draw(shape);
	    //边界发光效果
	    paintBorderGlow(gg, 255);
	    gg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.2f));
	    gg.draw(shape);
	    
	    //再画一次
//	    Shape shape2 = v.getOutline();
//	    shape2.getBounds();
//	    gg.setColor(Color.white);
//	    gg.fill(shape2);
	    gg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1f));
	    
	    gg.setColor(Color.white);
	    gg.setFont(new Font("宋体", Font.BOLD, 40));
        gg.drawString("祝：某某", fx-37, fy-72);
	   
	  //  gg.draw(shape2);
	    
	    
	 // Shadow 
//	    g.setColor(new Color(50， 50， 50)); 
//	    g.drawString("Shadow"， ShiftEast(x， 2)， ShiftSouth(y， 2)); 
//	    g.setColor(new Color(220， 220， 220)); 
//	    g.drawString("Shadow"， x， y); 
	    //	    边线
	   // gg.setColor(Color.WHITE.darker().darker());
	 /*   gg.setColor(Color.WHITE);
	    //gg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.3f));
	    gg.setStroke(new BasicStroke(1));
	    gg.draw(shape);*/
	    
	   // ShadowBorder.newBuilder().shadowSize(5).center().build();

	    
	  

	  }
	  
	  int ShiftNorth(int p, int distance) {
		   return (p - distance);
		   }
		int ShiftSouth(int p, int distance) {
		   return (p + distance);
		   }
		int ShiftEast(int p, int distance) {
		   return (p + distance);
		   }
		int ShiftWest(int p, int distance) {
		   return (p - distance);
		   }
		
		//下面是实现技巧：为了实现发光效果，我们开始使用一种"内部"颜色粗笔
		//和笔划需要的外形。然后，我们不断地把笔变细，
		//并且不断地移向"外部"颜色，
		//并且不断地提高颜色的不透明度以便使其朝向外形的内部看上去暗淡。
		//我们使用已经生成到我们的目的图像上的"修剪外形"，这样以来，
		//SRC_ATOP规则就会修剪在我们的外形外部的笔划部分。
		private void paintBorderGlow(Graphics2D g2, int glowWidth) {
		  int gw = glowWidth*2;
		  for (int i=gw; i >= 2; i-=2) {
		   float pct = (float)(gw - i) / (gw - 1);
		    Color mixHi = getMixedColor(clrGlowInnerHi, pct,clrGlowOuterHi, 1.0f - pct);
		    Color mixLo = getMixedColor(clrGlowInnerLo, pct,clrGlowOuterLo, 1.0f - pct);
		    g2.setPaint(new GradientPaint(0.0f, height*0.25f, mixHi,0.0f, height, mixLo));
		    g2.setColor(Color.black);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, pct));
		    g2.setStroke(new BasicStroke(i));
		 // g2.draw(clipShape);
		  }
		}

		private static Color getMixedColor(Color c1, float pct1, Color c2, float pct2) {
			float[] clr1 = c1.getComponents(null);
			float[] clr2 = c2.getComponents(null);
			for (int i = 0; i < clr1.length; i++) {
			clr1[i] = (clr1[i] * pct1) + (clr2[i] * pct2);
			}
			return new Color(clr1[0], clr1[1], clr1[2], clr1[3]);
		}

private static final Color clrHi = new Color(255, 229, 63);
private static final Color clrLo = new Color(255, 105, 0);
//private static final Color clrGlowInnerHi = new Color(253, 239, 175, 148);
//private static final Color clrGlowInnerLo = new Color(255, 209, 0);
//private static final Color clrGlowOuterHi = new Color(253, 239, 175, 124);
//private static final Color clrGlowOuterLo = new Color(255, 179, 0);
private static final Color clrGlowInnerHi = new Color(253, 255, 255, 148);
private static final Color clrGlowInnerLo = new Color(255, 209, 0);
private static final Color clrGlowOuterHi = new Color(0, 0, 0, 255);
private static final Color clrGlowOuterLo = new Color(0, 0, 0);

private static  int width = 1200;
private static  int height = 800;
}