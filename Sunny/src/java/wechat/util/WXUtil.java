package wechat.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ybt.common.constant.Wechat;
import com.ybt.common.util.PropertiesUtil;
import com.ybt.web.wechat.WechatController;

import wechat.api.BaseAPI;
import wechat.support.TicketManager;

public class WXUtil {
private static Logger logger = Logger.getLogger(WXUtil.class);
	
	public static String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "GBK");
	}

	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
	public static int random(int max,int min) {
		Random random = new Random(); 
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		
		return randomNumber;
	}
	
	/**  
	 * 获得 授权网址
	 * @param url
	 * @param sns
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年6月25日 下午11:41:10 
	 */
	public static String getOAuthUrl(String url,boolean sns){
		int index=url.indexOf("&code");
		if(index>4){
			url=url.substring(0, index);
		}
		 index=url.indexOf("code");
		if(index>4){
			url=url.substring(0, index);
		}
		if (sns)
		return BaseAPI.OAUTH2_URI + "/connect/oauth2/authorize?appid="+ Wechat.APPID
		+ "&redirect_uri="+urlEncodeUTF8(url)+"&response_type=code&scope=snsapi_userinfo&state=4#wechat_redirect";
		else
			return BaseAPI.OAUTH2_URI + "/connect/oauth2/authorize?appid="+ Wechat.APPID
					+ "&redirect_uri="+urlEncodeUTF8(url)+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	}

    /**  
     *  URL编码（utf-8）
     * @param source
     * @return  
     * @author AndyBao
     * @version V4.0, 2016年6月25日 下午11:40:56 
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
  //微信JSSDK
    public static String WXConfig(HttpServletRequest req,String param,String viewUrl){
  			if(viewUrl!=null && !"".equals(viewUrl)){
  				
  			}else{
  				String paramStr = req.getParameter(param);
  				String code = req.getParameter("code");
  				String state = req.getParameter("state");
  				String winning = req.getParameter("winning");
  				//-微信JSSDK
  			//	viewUrl = PropertiesUtil.getProperty("domainName")+req.getRequestURI();
  				if(paramStr!=null){
  					paramStr="?"+param+"="+paramStr;
  					viewUrl=viewUrl+paramStr;
  					if(code!=null){
  						code="&code="+code;
  						viewUrl=viewUrl+code;
  					}
  					if(state!=null){
  						state="&state="+state;
  						viewUrl=viewUrl+state;
  					}
  					if(winning!=null){
  						winning="&winning="+winning;
  						viewUrl=viewUrl+winning;
  					}
  				}else{
  					if(code!=null){
  						code="?code="+code;
  						viewUrl=viewUrl+code;
  					}
  					if(state!=null){
  						state="&state="+state;
  						viewUrl=viewUrl+state;
  					}
  					if(winning!=null){
  						winning="&winning="+winning;
  						viewUrl=viewUrl+winning;
  					}
  				}
  			}
  			
  			logger.info("-----------微信JSSDK  sudoku  viewUrl=========== "+viewUrl);
  			String token = TicketManager.getTicket(Wechat.APPID,Wechat.APPSECRET);
  			logger.info("-----------微信JSSDK  sudoku  viewUrl=========== "+token);
  			/*,"onMenuShareQQ","onMenuShareWeibo","onMenuShareQZone"*/
  			String config = JsUtil.generateConfigJson(token, 
  					false, Wechat.APPID, viewUrl,"checkJsApi","onMenuShareTimeline","onMenuShareAppMessage","openLocation","getLocation","startRecord","stopRecord","onVoiceRecordEnd","playVoice","pauseVoice","stopVoice","onVoicePlayEnd","uploadVoice","downloadVoice","uploadImage","downloadImage","previewImage","chooseImage","hideOptionMenu","hideMenuItems","hideAllNonBaseMenuItem","showMenuItems","showAllNonBaseMenuItem");
  			
  			return config;
  		}
    
	public static void main(String[] args) {
//		System.out.println(random(100));
		/*for(int i = 0;i<1000;i++){
			System.out.println("system===" + System.currentTimeMillis());
			System.out.println(random(1000000000,Integer.MAX_VALUE));
		}*/
		//System.out.println(urlEncodeUTF8("http://sunny.51vip.biz/qc/wechat/recording"));
		//System.out.println(getOAuthUrl("http://sunny.51vip.biz/qc/wechat/song/recording?songId=1&voiceId=40287ca556b040650156b04e3da30006", false));
	
		
		String aaa=  "http://huazan.org/Change/wechat/song/recording?songId=1&voiceId=ff8080815702bfec0157032ed807000d&code=021TILRd02btqG1w85Rd0mIORd0TILRU&state=4&from=singlemessage&isappinstalled=0&code=011b1uPf00nTsE1ZsePf06PsPf0b1uPb&state=4&code=011fedMg0W7O5D1vIuNg0P29Mg0fedMM&state=4&code=021STbFv0Omquo1DKLGv0fK9Fv0STbF6&state=4";
	    System.out.println(aaa.indexOf("&code"));  
	    System.out.println(aaa.substring(0, aaa.indexOf("code")));  
	}
	 
}
