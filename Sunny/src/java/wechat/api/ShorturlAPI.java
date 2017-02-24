package wechat.api;

import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import wechat.bean.Shorturl;
import wechat.client.LocalHttpClient;

public class ShorturlAPI extends BaseAPI{

	/**
	 * 将一条长链接转成短链接
	 * @param access_token
	 * @param action 此处填long2short，代表长链接转短链接
	 * @param long_url 需要转换的长链接，支持http://、https://、weixin://wxpay 格式的url
	 * @return
	 */
	public static Shorturl shorturl(String access_token,String action,String long_url){
		String json = "{\"action\":\""+action+"\",\"long_url\":\""+long_url+"\"}";
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setHeader(jsonHeader)
				.setUri(BASE_URI + "/cgi-bin/shorturl")
				.addParameter("access_token", access_token)
				.setEntity(new StringEntity(json,Charset.forName("utf-8")))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Shorturl.class);
	}

	/**
	 * 将一条长链接转成短链接   action 默认值 long2short
	 * @param access_token
	 * @param long_url
	 * @return
	 */
	public static Shorturl shorturl(String access_token,String long_url){
		return shorturl(access_token,"long2short", long_url);
	}
	
	public static void main(String[] args) {
		String urlstr="http://www.yunbeitai.com/ych/wechat/index?asdfasdfasdfasdfasfdasdfas=123123";
		
				Shorturl sh =shorturl("oh3Z43DAddLQqYAXH6WIdI5IJOtSnOb1UpRYScSQUmB9KKVzFZo2pwZf3nGgM18KLlmL1L4QeTZLvP2NEmZtQoife3mvB7NTWjVap_Nc8L8VTBaAEACAI", urlstr);
	System.out.println(sh.getShort_url());
	}

}
