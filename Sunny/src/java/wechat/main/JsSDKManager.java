package wechat.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * js管理器类
 */
public class JsSDKManager {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(JsSDKManager.class);
/*
	public static void main(String[] args) {
		Token token = TokenAPI.token(Wechat.APPID, Wechat.AppSecret);
		if (null != token) {
			String access_token = token.getAccess_token();
			Ticket tickent = TicketAPI.ticketGetticket(access_token);
			String jsapi_ticket=tickent.getTicket();
			String nonce_str = create_nonce_str();
	        String timestamp = create_timestamp();
	        String url="http://test.9xdy.com/ybt/console/wechat/card";
	        String string1;
	        String signature=JsUtil.generateConfigSignature(nonce_str, jsapi_ticket, timestamp, url);
	        
	        System.out.println("access_token:"+access_token);
	        System.out.println("jsapi_ticket:"+jsapi_ticket);
	        System.out.println("nonce_str:"+nonce_str);
	        System.out.println("timestamp:"+timestamp);
	        System.out.println("signature:"+signature);
		}
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}*/
}
