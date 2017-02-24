package wechat.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import wechat.bean.Token;
import wechat.client.LocalHttpClient;

public class TokenAPI extends BaseAPI{

	/**
	 * 获取access_token
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static Token token(String appid,String secret){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret)
				//.addParameter("grant_type","client_credential")
				//.addParameter("appid", appid)
				//.addParameter("secret", secret)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Token.class);
	}

}
