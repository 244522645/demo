package test.com.ybt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws Exception {
		/*String[] paths = new String[2];
		paths[0] = "C:/Users/Jiang/Documents/HBuilderProject/qc/audio/test.mp3";
		paths[1] = "C:/Users/Jiang/Documents/HBuilderProject/qc/audio/1.mp3";
		uniteWavFile(paths,"C:/Users/Jiang/Documents/HBuilderProject/qc/audio/hecheng.mp3");*/
		//and();
		
		testBirthday();
	}
	
	
	public static void testBirthday(){
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
        String clidate = "19780104";
        Calendar cToday = Calendar.getInstance(); // 存今天
        Calendar cBirth = Calendar.getInstance(); // 存生日
        try {
			cBirth.setTime(myFormatter.parse(clidate)); // 设置生日
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)); // 修改为本年
        int days; 
        if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
            // 生日已经过了，要算明年的了
            days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            days += cBirth.get(Calendar.DAY_OF_YEAR);
        } else {
            // 生日还没过
            days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
        }
        // 输出结果
        if (days == 0) {
            System.out.println("今天生日");
        } else {
            System.out.println("距离生日还有：" + days + "天");
        }
	}
	
	
	public static void and() throws Exception{

		BufferedInputStream input1 = new BufferedInputStream(new FileInputStream(new File("C:/Users/Jiang/Documents/HBuilderProject/qc/audio/1.mp3")));  //路径格式：D://java//1.MP3
		BufferedInputStream input2 = new BufferedInputStream(new FileInputStream(new File("C:/Users/Jiang/Documents/HBuilderProject/qc/audio/test.mp3")));
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("C:/Users/Jiang/Documents/HBuilderProject/qc/audio/hecheng.mp3")));
		byte[] byt = new byte[1024];
		int len;
		while ((len=input1.read(byt)) != -1)
		{
		out.write(byt,0,byt.length);
		out.flush();

		}
		while ((len=input2.read(byt)) != -1)
		{
		out.write(byt,0,byt.length);
		out.flush();
		}
		input1.close();
		input2.close();
		out.close();
	}
	
	/** 
	 *  
	 * @param partsPaths 要合成的音频路径数组 
	 * @param unitedFilePath 输入合并结果数组 
	 */  
	public static void uniteWavFile(String[] partsPaths, String unitedFilePath) {  
	  
	        byte byte1[] = getByte(partsPaths[0]);  
	        byte byte2[] = getByte(partsPaths[1]);  
	  
	        byte[] out = new byte[byte1.length];  
	        for (int i = 0; i < byte1.length; i++)  
	            out[i] = (byte) ((byte1[i] + byte2[i]) >> 1);  
	          
	        try {  
	            FileOutputStream fos = new FileOutputStream(new File(unitedFilePath));  
	            fos.write(out);  
	            fos.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	}  
	  
	private static byte[] getByte(String path){  
	    File f = new File(path);  
	    InputStream in;  
	    byte bytes[] = null;  
	    try {  
	        in = new FileInputStream(f);  
	        bytes = new byte[(int) f.length()];  
	        in.read(bytes);  
	        in.close();  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	      
	    return bytes;  
	}  

}
