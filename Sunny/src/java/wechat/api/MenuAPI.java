package wechat.api;

import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import wechat.bean.BaseResult;
import wechat.bean.Menu;
import wechat.bean.MenuButtons;
import wechat.client.LocalHttpClient;
import wechat.util.JsonUtil;

public class MenuAPI extends BaseAPI{

	/**
	 * 创建菜单
	 * @param access_token
	 * @param menuJson 菜单json 数据 例如{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"type\":\"click\",\"name\":\"歌手简介\",\"key\":\"V1001_TODAY_SINGER\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}
	 * @return
	 */
	public static BaseResult menuCreate(String access_token,String menuJson){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
										.setHeader(jsonHeader)
										.setUri(BASE_URI+"/cgi-bin/menu/create")
										.addParameter("access_token", access_token)
										.setEntity(new StringEntity(menuJson,Charset.forName("utf-8")))
										.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}

	/**
	 * 创建菜单
	 * @param access_token
	 * @param menuButtons
	 * @return
	 */
	public static BaseResult menuCreate(String access_token,MenuButtons menuButtons){
		String str = JsonUtil.toJSONString(menuButtons);
		return menuCreate(access_token,str);
	}

	/**
	 * 获取菜单
	 * @param access_token
	 * @return
	 */
	public static Menu menuGet(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
					.setUri(BASE_URI+"/cgi-bin/menu/get")
					.addParameter("access_token", access_token)
					.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Menu.class);
	}

	/**
	 * 删除菜单
	 * @param access_token
	 * @return
	 */
	public static BaseResult menuDelete(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI+"/cgi-bin/menu/delete")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}

}