package com.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Utils {
	String key = "Dz3n9oXpcCRXBKnlA3DBZzaL4fZvYjS2";
	String regKey = "64ff7a89d300a969b4686ab7f90874ea";

	/**
	 * MD5加密
	 * 
	 * @return String
	 */
	public String toMD5(String plainText) {
		try {
			// 生成实现指定摘要算法的 MessageDigest 对象。
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组更新摘要。
			md.update(plainText.getBytes());
			// 通过执行诸如填充之类的最终操作完成哈希计算。
			byte b[] = md.digest();
			// 生成具体的md5密码到buf数组
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString().toUpperCase();// 32位的加密

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 传入一个字符串()和手机串码 字符串的规则是： 登录的是 Status + Phone + time+ POPMessage 首页的字符串是
	 * phone +time+imsi 传入参数（字符串,手机串号，md5值）
	 */
	public String toKeyMD5(String plainText, String imsi) {

		return toMD5(plainText + imsi + key);

	}

	/*
	 * md5校验 字符串的规则是： 登录的是 Status + Phone + time+ POPMessage 首页的字符串是 phone
	 * +time+imsi 传入参数（字符串,手机串号，md5值）
	 * 
	 */
	public boolean equalsMD5(String plainText, String imsi, String md5) {
		return md5.equalsIgnoreCase(toKeyMD5(plainText, imsi));
	}

	/*
	 * md5加密 字符串的规则是： 注册加密的是 Phone 传入参数（手机号）
	 * 
	 */
	public String addMD5(String phone) {
		return toMD5(phone + regKey);
	}

	// 调用接口
	public String findByCardBank(String urls, String cs) {
		try {
			// 获取服务器验证
			HttpsURLConnection.setDefaultHostnameVerifier(new TrustAnyVerifier());
			// 转换编码
			byte[] data = cs.getBytes("gb2312");
			// 创建url
			URL url = new URL(urls);
			// 将url 以 open方法返回的urlConnection 连接强转为HttpURLConnection连接
			// (标识一个url所引用的远程对象连接)
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
			conn.setDoOutput(true);
			// 设置请求方式为post
			conn.setRequestMethod("POST");
			// post请求缓存设为false
			conn.setUseCaches(false);
			// 设置该HttpURLConnection实例是否自动执行重定向
			conn.setInstanceFollowRedirects(true);
			// 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 建立连接
			// (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
			conn.connect();
			// 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
			DataOutputStream dataout = new DataOutputStream(conn.getOutputStream());
			// 将参数输出到连接
			dataout.write(data);
			// 输出完成后刷新并关闭流
			dataout.flush();
			dataout.close();
			// 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			String line;
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {// 循环读取流

				sb.append(line);
			}
			br.close();// 关闭流
			conn.disconnect();// 断开连接
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 解析开卡验证的xml
	 */
	public String readStringXml(String xml) {
		Document doc = null;
		try {

			// 读取并解析XML文档

			// SAXReader就是一个管道，用一个流的方式，把xml文件读出来

			// SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档

			// Document document = reader.read(new File("User.hbm.xml"));

			// 下面的是通过解析xml字符串的

			doc = DocumentHelper.parseText(xml); // 将字符串转为XML

			Element rootElt = doc.getRootElement(); // 获取根节点

			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator<?> iter;
			iter = rootElt.elementIterator("Errors"); // 获取根节点下的子节点head

			// 遍历head节点

			while (iter.hasNext()) {

				Element recordEle = (Element) iter.next();
				String errorid = recordEle.elementTextTrim("ErrorID"); // 拿到head节点下的子节点title值

				if (errorid != null && !errorid.equalsIgnoreCase("")) {
					return errorid;
				}

			}

		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	/*
	 * 解析用户未使用点数的xml
	 */
	public String readUsercardXml(String xml) throws DocumentException {
		Document doc = null;
		String cardMonry = null;
		try {

			// 读取并解析XML文档

			// SAXReader就是一个管道，用一个流的方式，把xml文件读出来

			// SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档

			// Document document = reader.read(new File("User.hbm.xml"));

			// 下面的是通过解析xml字符串的

			doc = DocumentHelper.parseText(xml); // 将字符串转为XML

			Element rootElt = doc.getRootElement(); // 获取根节点

			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			Iterator<?> iter;
			iter = rootElt.elementIterator("Datas"); // 获取根节点下的子节点head

			// 遍历head节点

			while (iter.hasNext()) {

				Element recordEle = (Element) iter.next();
				Iterator<?> itrs = recordEle.elementIterator("CardRemainCount");
				while (itrs.hasNext()) {
					Element recordEles = (Element) itrs.next();
					cardMonry = recordEles.elementTextTrim("RemainCount");
				}

			}

		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return cardMonry;
	}

	public String tenrodem() {
		Random random = new java.util.Random();

		StringBuffer buffer = new StringBuffer();
		int result;
		for (int i = 0; i < 10; i++) {

			if (i == 0) {
				result = random.nextInt(9);
				buffer.append(result + 1);
				continue;
			}
			result = random.nextInt(10);
			buffer.append(result);

		}
		return buffer.toString();
	}

	// 调用接口
	public String gettingCode(String urls, String phone, String result) {
		try {
			// 获取服务器验证
			HttpsURLConnection.setDefaultHostnameVerifier(new TrustAnyVerifier());
			// 转换编码
			// 创建url
			URL url = new URL(urls);
			// 将url 以 open方法返回的urlConnection 连接强转为HttpURLConnection连接
			// (标识一个url所引用的远程对象连接)
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
			conn.setDoOutput(true);
			// 设置请求方式为post
			conn.setRequestMethod("POST");
			// post请求缓存设为false
			conn.setUseCaches(false);
			// 设置该HttpURLConnection实例是否自动执行重定向
			conn.setInstanceFollowRedirects(true);
			// 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 建立连接
			// (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
			conn.connect();
			// 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
			DataOutputStream dataout = new DataOutputStream(conn.getOutputStream());
			// 将参数输出到连接
			String data = "UserName=kangou&UserPass=770310&Mobile=" + phone + "&Content="
					+ URLEncoder.encode("验证码" + result, "UTF-8");
			dataout.writeBytes(data);
			// 输出完成后刷新并关闭流
			dataout.flush();
			dataout.close();
			// 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			String line;
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {// 循环读取流

				sb.append(line);
			}
			br.close();// 关闭流
			conn.disconnect();// 断开连接
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String rollback(String url) {
		return sendGet(url);
	}

	public String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性

			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
			connection.setRequestProperty("accept-language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setUseCaches(false);

			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			StringBuffer content = new StringBuffer();
			String tempStr = "";
			while ((tempStr = in.readLine()) != null) {
				System.out.println("返回的流" + tempStr);
				content.append(tempStr);
			}
			result = content.toString();
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	
}
