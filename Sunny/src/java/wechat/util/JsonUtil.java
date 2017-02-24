package wechat.util;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {

	private JsonUtil(){}
/*
	public static <T> T parseObject(String json,Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}

	public static String toJSONString(Object object){
		return JSON.toJSONString(object);
	}*/

	public static <T> T parseObject(String json,Class<T> clazz){
		return new Gson().fromJson(json, clazz);
	}

	public static String toJSONString(Object object){
		return  new GsonBuilder().disableHtmlEscaping().create().toJson(object);
	}

	public static String getJsonValue(String resContent, String aCCESS_TOKEN) {
		Map<String, String> map = new Gson().fromJson(resContent,
				new TypeToken<Map<String, String>>() {
				}.getType());
		return map.get(aCCESS_TOKEN);
	}
}
