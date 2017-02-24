package com.ybt.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
public class HttpConnectionUtil {
	
	public static CloseableHttpClient httpclient;  
    static {  
        httpclient = HttpClients.createDefault(); 
//        httpclient.getParams().setParameter(arg0, arg1)
//        httpclient.getDefaultParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
//        httpclient.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
    }  
	
	static HttpURLConnection connection ;
	
	static URL url ;
	
	public static String readContentFromGet(String GET_URL ,String content) throws IOException {
		String getURL = GET_URL + "?"+content;
		url = new URL(getURL);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urluncoded");
	    connection.setRequestProperty("Accept-Charset", "UTF-8");
	    connection.setRequestProperty("contentType", "UTF-8");
		connection.setReadTimeout(30000);
		connection.setConnectTimeout(30000);
		connection.setRequestProperty("User-agent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36");
        connection.connect();
        BufferedReader reader = null;
        String res = "";
        try{
	        reader = new BufferedReader(new InputStreamReader( connection.getInputStream(),"UTF-8"));
	        String lines;
	        while ((lines = reader.readLine()) != null) {
	        	res += lines;
	        }
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	 if(reader!=null)
     		    reader.close();
     	     if(connection!=null)
     	        connection.disconnect();
        }
        return res;
    }

    public static String readContentFromPost(String POST_URL,String content) throws IOException {
    	
    	url = new URL(POST_URL);
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urluncoded");
	    connection.setRequestProperty("Accept-Charset", "UTF-8");
	    connection.setRequestProperty("contentType", "UTF-8");
		connection.setReadTimeout(30000);
		connection.setConnectTimeout(30000);
		connection.setRequestProperty("User-agent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36");
        connection.connect();
        String res = "";
        BufferedReader reader = null ;
	    try{
	        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
	        out.writeBytes(content); 
	        out.flush();
	        out.close(); // flush and close
	        reader = new BufferedReader(new InputStreamReader( connection.getInputStream(),"UTF-8"));
	        String line;
	       
	        while ((line = reader.readLine()) != null) {
	        	res += line;
	        }
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    	    if(reader!=null)
    		    reader.close();
    	    if(connection!=null)
    	        connection.disconnect();
    	}
        return res;
    }
    /**
     * 
     * @param urlAll:请求接口
     * @param charset:字符编码
     * @return 返回json结果
     */
    public static String get(String urlAll,String charset){
 	   BufferedReader reader = null;
 	   String result = null;
 	   StringBuffer sbf = new StringBuffer();
 	   String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";//模拟浏览器
 	  try {
		   url = new URL(urlAll);
		   connection = (HttpURLConnection)url.openConnection();
		   connection.setRequestMethod("GET");
	       connection.setRequestProperty("Content-type", "text/html");
	       connection.setRequestProperty("Accept-Charset", "utf-8");
	       connection.setRequestProperty("contentType", "utf-8");
		   connection.setReadTimeout(30000);
		   connection.setConnectTimeout(30000);
		   connection.setRequestProperty("User-agent",userAgent);
		   connection.connect();
		   InputStream is = connection.getInputStream();
 		   reader = new BufferedReader(new InputStreamReader(is, charset));
 		   String strRead = null;
 		   while ((strRead = reader.readLine()) != null) {
 				sbf.append(strRead);
 				sbf.append("\r\n");
 		   }
 		   result = sbf.toString();
	   } catch (Exception e) {
		   e.printStackTrace();
	   }finally{
			try {
				if(reader!=null){
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
			if(connection!=null)
				connection.disconnect();
 			
	   }
 	   return result;
    }

    


/**
 *@description httpclient httppost 实现post请求
 *@time    创建时间:2015年11月27日下午2:01:53
 *@param url  
 *@param paramsMap 参数集
 *@return
 *@author   张洪征
 *@history 修订历史（历次修订内容、修订人、修订时间等）
 */
@SuppressWarnings("rawtypes")
public static String  httpPost(String url,Map<String, String> paramsMap,String charset) {  
    try {  
        HttpPost httpost = new HttpPost(url);
//        httpost.setHeader("Accept",  
//        		"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
//        httpost.setHeader("Accept-Encoding", "gzip, deflate");  
//        httpost.setHeader("Accept-Language", "en-US,en;q=0.5");  
//        httpost.setHeader("Cache-Control", "max-age=0");  
//        httpost.setHeader("Connection", "keep-alive");  
//        httpost.setHeader("Content-Type", "application/x-www-form-urlencoded");  
//        httpost.setHeader(  
//        		"User-Agent",  
//        		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:28.0) Gecko/20100101 Firefox/28.0");
        List<NameValuePair> params = new ArrayList<NameValuePair>();  
        // 参数
        Iterator it = paramsMap.entrySet().iterator();  
        while (it.hasNext()) {  
            Map.Entry element = (Map.Entry) it.next();
            String key = (String) element.getKey();
            String value = (String) element.getValue();
            params.add(new BasicNameValuePair(key,value)); 
        }  
       
        httpost.setEntity(new UrlEncodedFormEntity(params,  charset));  
        HttpResponse response = httpclient.execute(httpost);  
        String jsonStr = EntityUtils  
                .toString(response.getEntity(), charset);  
        EntityUtils.consume(response.getEntity());// //消耗掉response
        return jsonStr;  
    } catch (Exception e) {  
        e.printStackTrace();  
        return "";  
    }
}  


    public static void main(String[] args) throws IOException {
       /* try {
        	//readContentFromGet("http://op.juhe.cn/onebox/weather/query","cityname=北京&key=d785d8819d18a3e8dc667983cb491b23");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
//    	String content = "key="+LbsMap.key+"&tableid="+LbsMap.tableid+"&keywords=北京&city=全国";
//    	String sres = HttpConnectionUtil.readContentFromGet(LbsMap.localUrl, content);
//    	String url = java.net.URLEncoder.encode("http://www.yunbeitai.com?haha=dd&dadsfs", "UTF-8");
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("url", "http://www.yunbeitai.com?haha=dd&dadsfs");
    	httpPost("http://dwz.cn/create.php",params,"UTF-8");
//    	String content = "long_url="+url+"&alias_url=yunbeitai.com";
//    	String sres = HttpConnectionUtil.post("http://dwz.cn/create.php", "url=http://www.yunbeitai.com?haha=dd&dadsfs");
    }

}
