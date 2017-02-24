package com.ybt.common.util;

import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.ybt.common.constant.chxtConstant;



public class StringUtil {
	
	public static boolean notNull(String str){
		if(str==null||"".equals(str))
			return false;
		else
			return true;
	}
	public boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
	 public static int  getInteger(String value) {
		 int i=1;
		  try {
		   i=Integer.parseInt(value);
		  } catch (NumberFormatException e) {
			  i=1;
		  }
		  return i;
		 }
	 public static Double  getDouble(String value) {
		 Double i=0.00;
		 try {
			  i=Double.parseDouble(value);
		  } catch (Exception e) {
			  i=0.00;
		  }
		  return i;
	 }
	 /**
	  * 验证 个性域名
	  */
	 public static boolean isDomin(String domain){
		 if(domain==null){
			 return false;
		 }
		 if(domain.length()<4||domain.length()>12){
			 return false;
		 }
		 String pipeRegex = "^[a-zA-Z].*";
		 Pattern pattern2 = Pattern.compile(pipeRegex);
		 Matcher match2=pattern2.matcher(domain);
		 boolean b=match2.matches();
		 if(b){
			 String regex="^[a-zA-Z0-9]+$";
			 Pattern pattern = Pattern.compile(regex);
			 Matcher match=pattern.matcher(domain);
			  b=match.matches();
		 }
		return b;
	 }
	 /**
	  * 字符串转换unicode
	  */
	 public static String string2Unicode(String string) {
	  
	     StringBuffer unicode = new StringBuffer();
	  
	     for (int i = 0; i < string.length(); i++) {
	  
	         // 取出每一个字符
	         char c = string.charAt(i);
	  
	         // 转换为unicode
	         unicode.append("\\u" + Integer.toHexString(c));
	     }
	  
	     return unicode.toString();
	 }
	 
	//订单号生成
	/**
	 * type 业务标识  count 订单数 uid 用户id
	 * @module
	 * @author bqy  @time 2016年1月15日 下午2:51:27
	 */
	public static String getOrderNum(String type,int count, String uid) {
				Date date = new Date();
				String orderNumber="";
				String dateT=(""+date.getTime()).substring(3);
				String index="1"+count;
				orderNumber=index+""+new Random().nextInt(90)+10+dateT+new Random().nextInt(90)+10+uid;
				return orderNumber;
	}	
	

	/**
	 *@name    密码加密
	 *@description 相关说明
	 *@time    创建时间:2016年1月28日上午9:41:59
	 *@param password 未加密密码
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public static String encryptPassword(String salt, String password) {
		byte[] hashPassword = Digests.sha1(password.getBytes(), Encodes.decodeHex(salt), chxtConstant.HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword);
	}
	
	
    /** 
     * 获取汉字串拼音首字母，英文字符不变 
     * 
     * @param chinese 汉字串 
     * @return 汉语拼音首字母 
     */ 
    public static String cn2FirstSpell(String chinese) { 
            StringBuffer pybf = new StringBuffer(); 
            char[] arr = chinese.toCharArray(); 
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
            for (int i = 0; i < arr.length; i++) { 
                    if (arr[i] > 128) { 
                            try { 
                                    String[] _t = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat); 
                                    if (_t != null) { 
                                            pybf.append(_t[0].charAt(0)); 
                                    } 
                            } catch (BadHanyuPinyinOutputFormatCombination e) { 
                              return "";
                            } 
                    } else { 
                            pybf.append(arr[i]); 
                    } 
            } 
            return pybf.toString().replaceAll("\\W", "").trim(); 
    } 

    /** 
     * 获取汉字串拼音，英文字符不变 
     * 
     * @param chinese 汉字串 
     * @return 汉语拼音 
     */ 
    public static String cn2Spell(String chinese) { 
     if(chinese==null || chinese.equals("")){
     return "";
     }else{
     StringBuffer pybf = new StringBuffer(); 
            char[] arr = chinese.toCharArray(); 
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
            for (int i = 0; i < arr.length; i++) { 
                    if (arr[i] > 128) { 
                            try { 
                                 String[] str=PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                                 if(str==null || str.length==0){
                                  break;
                                 }
                                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]); 
                            } catch (BadHanyuPinyinOutputFormatCombination e) { 
                             break;
                            } 
                    } else { 
                            pybf.append(arr[i]); 
                    } 
            } 
            return pybf.toString(); 
     }    
    } 
    
    /**
      * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
      * @param text
      * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
     */
        public static int getLength(String text) {
            int textLength = text.length();
           int length = textLength;
           for (int i = 0; i < textLength; i++) {
                if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                   length++;
                }
            }
            return (length % 2 == 0) ? length / 2 : length / 2 + 1;
         }
    
    	public static String copyCat(String str) {
    		// TODO A public static void main ( String[] args )
    		String reg = "[^\u4e00-\u9fa5]";
    		String han = "";
    		han = str.replaceAll(reg, "");
    		String result = "";
    		if(han==null&&!han.equals("")){
    			result= str.substring(0, 1);
    			return result;
    		}
    		if(!str.equals("")&&str.length()>1){
    			result= str.substring(0, 1);
    			return result;
    		}
    		
			return "";
    	}
}
