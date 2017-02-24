package wechat.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import wechat.bean.Media;
import wechat.bean.Menu;
import wechat.client.LocalHttpClient;

public class MediaAPI extends BaseAPI{

	/**
	 * 上传媒体文件
	 * 媒体文件在后台保存时间为3天，即3天后media_id失效。
	 * @param access_token
	 * @param mediaType
	 * @param media  	多媒体文件有格式和大小限制，如下：
						图片（image）: 128K，支持JPG格式
						语音（voice）：256K，播放长度不超过60s，支持AMR\MP3格式
						视频（video）：1MB，支持MP4格式
						缩略图（thumb）：64KB，支持JPG格式
	 * @return
	 */
	public static Media mediaUpload(String access_token,MediaType mediaType,File media){
		HttpPost httpPost = new HttpPost(MEDIA_URI+"/cgi-bin/media/upload");
		FileBody bin = new FileBody(media);
        HttpEntity reqEntity = MultipartEntityBuilder.create()
        		 .addPart("media", bin)
                 .addTextBody("access_token", access_token)
                 .addTextBody("type",mediaType.uploadType())
                 .build();
        httpPost.setEntity(reqEntity);
		return LocalHttpClient.executeJsonResult(httpPost,Media.class);
	}

	/**
	 * 上传媒体文件
	 * 媒体文件在后台保存时间为3天，即3天后media_id失效。
	 * @param access_token
	 * @param mediaType
	 * @param inputStream  	多媒体文件有格式和大小限制，如下：
						图片（image）: 128K，支持JPG格式
						语音（voice）：256K，播放长度不超过60s，支持AMR\MP3格式
						视频（video）：1MB，支持MP4格式
						缩略图（thumb）：64KB，支持JPG格式
	 * @return
	 */
	public static Media mediaUpload(String access_token,MediaType mediaType,InputStream inputStream){
		HttpPost httpPost = new HttpPost(MEDIA_URI+"/cgi-bin/media/upload");
        @SuppressWarnings("deprecation")
		InputStreamBody inputStreamBody = new InputStreamBody(inputStream, mediaType.mimeType(),"temp."+mediaType.fileSuffix());
		HttpEntity reqEntity = MultipartEntityBuilder.create()
        		 .addPart("media",inputStreamBody)
                 .addTextBody("access_token", access_token)
                 .addTextBody("type",mediaType.uploadType())
                 .build();
        httpPost.setEntity(reqEntity);
		return LocalHttpClient.executeJsonResult(httpPost,Media.class);
	}


	/**
	 * 上传媒体文件
	 * 媒体文件在后台保存时间为3天，即3天后media_id失效。
	 * @param access_token
	 * @param mediaType
	 * @param uri  	多媒体文件有格式和大小限制，如下：
						图片（image）: 128K，支持JPG格式
						语音（voice）：256K，播放长度不超过60s，支持AMR\MP3格式
						视频（video）：1MB，支持MP4格式
						缩略图（thumb）：64KB，支持JPG格式
	 * @return
	 */
	public static Media mediaUpload(String access_token,MediaType mediaType,URI uri){
		HttpPost httpPost = new HttpPost(MEDIA_URI+"/cgi-bin/media/upload");
		CloseableHttpClient tempHttpClient = HttpClients.createDefault();
		try {
			HttpEntity entity = tempHttpClient.execute(RequestBuilder.get().setUri(uri).build()).getEntity();
			HttpEntity reqEntity = MultipartEntityBuilder.create()
					 .addBinaryBody("media",EntityUtils.toByteArray(entity),ContentType.get(entity),"temp."+mediaType.fileSuffix())
			         .addTextBody("access_token", access_token)
			         .addTextBody("type",mediaType.uploadType())
			         .build();
			httpPost.setEntity(reqEntity);
			return LocalHttpClient.executeJsonResult(httpPost,Media.class);
		} catch (UnsupportedCharsetException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				tempHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 下载多媒体
	 * 视频文件不支持下载
	 * @param access_token
	 * @param media_id
	 * @return
	 */
	public static byte[] mediaGet(String access_token,String media_id){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
					.setUri(MEDIA_URI+"/cgi-bin/media/get")
					//?access_token="+access_token+"&media_id="+media_id
					.addParameter("access_token", access_token)
					.addParameter("media_id", media_id)
					.build();
		HttpResponse httpResponse = LocalHttpClient.execute(httpUriRequest);
		try {
			/*HttpEntity httpEntity = httpResponse.getEntity();
			
			 * if (httpEntity != null){

				
				InputStream instreams = httpEntity.getContent(); 
				BufferedReader reader = new BufferedReader(new InputStreamReader(instreams));      

		        StringBuilder sb = new StringBuilder();      

				String line = null;      
		
				        try {      
		
				            while ((line = reader.readLine()) != null) {  
		
				                sb.append(line + "\n");      
		
				            }      
		
				        } catch (IOException e) {      
		
				            System.out.println("Error=" + e.toString());      
		
				        } finally {      
		
				            try {      
		
				            	instreams.close();      
		
				            } catch (IOException e) {      
		
				            	System.out.println("Error=" + e.toString());      
		
				            }      
		
				        }      
		
				        System.out.println(sb.toString());
					}*/
			return EntityUtils.toByteArray(httpResponse.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	public enum MediaType{
		image {
			@Override
			String mimeType() {
				return "image/jpeg";
			}

			@Override
			String fileSuffix() {
				return "jpg";
			}

			@Override
			String uploadType() {
				return "image";
			}
		},voice_mp3 {
			@Override
			String mimeType() {
				return "audio/mpeg";
			}

			@Override
			String fileSuffix() {
				return "mp3";
			}

			@Override
			String uploadType() {
				return "voice";
			}
		},voice_arm {
			@Override
			String mimeType() {
				return "audio/amr";
			}

			@Override
			String fileSuffix() {
				return "amr";
			}

			@Override
			String uploadType() {
				return "voice";
			}
		},video {
			@Override
			String mimeType() {
				return "video/mp4";
			}

			@Override
			String fileSuffix() {
				return "mp4";
			}

			@Override
			String uploadType() {
				return "video";
			}
		},thumb {
			@Override
			String mimeType() {
				return "image/jpeg";
			}

			@Override
			String fileSuffix() {
				return "jpg";
			}

			@Override
			String uploadType() {
				return "thumb";
			}
		};

		abstract String mimeType();

		abstract String fileSuffix();

		/**
		 * 上传类型
		 * @return
		 */
		abstract String uploadType();



	}
	
	
	public static void main(String args[]){
		
		//String token = TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET);
		String token = "p6tn-ld6COG-2hYNbs3G9qMWb09hNBOcW-ASsEnIP37ioOJwVuUs8qXm1klb9ABRnwl_vytlZYsq5D67rsYDXa7Gh8NJXko3DLMnmNJp8HpjZJSKbc87P0FruWJ90hJDCCFcAJAMMP";
		MediaAPI.mediaGet(token, "F-11SowzBiQtSf62vdMahDosMWCWFgcQE-BWg1Sgcno4spUK4b2L1_HgOiPmchKf");
	
		//wechat.bean.User user = SnsAPI.userinfo(token, "oWL5RuJoTvOo2ZyHwLfafhE3B3-M", "zh_CN");
		Menu m=MenuAPI.menuGet(token);
		//System.out.println(m.getMenu());
	}
}
