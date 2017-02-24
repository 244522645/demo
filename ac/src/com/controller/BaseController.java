package com.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.service.KCardDeadlineService;
import com.service.KCardKindsService;
import com.service.KCardOrdersService;
import com.service.KUserService;
import com.util.TrustAnyVerifier;
import com.util.Utils;

import sun.misc.BASE64Encoder;

public class BaseController {

	// 格式化时间
	Date currentTime = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String dateString = formatter.format(currentTime);
	Utils util = new Utils();
	BASE64Encoder base64en = new BASE64Encoder();
	@Autowired(required = false)
	private KCardOrdersService cardOrdersService;

	public KCardOrdersService getCardOrdersService() {
		return cardOrdersService;
	}

	public void setCardOrdersService(KCardOrdersService cardOrdersService) {
		this.cardOrdersService = cardOrdersService;
	}

	@Autowired(required = false)
	private KCardKindsService cardKindsService;

	public KCardKindsService getCardKindsService() {
		return cardKindsService;
	}

	public void setCardKindsService(KCardKindsService cardKindsService) {
		this.cardKindsService = cardKindsService;
	}

	@Autowired(required = false)
	private KUserService kuserService;

	public KUserService getKuserService() {
		return kuserService;
	}

	public void setKuserService(KUserService kuserService) {
		this.kuserService = kuserService;
	}

	@Autowired(required = false)
	private KCardDeadlineService cardDeadlineService;

	public KCardDeadlineService getCardDeadlineService() {
		return cardDeadlineService;
	}

	public void setCardDeadlineService(KCardDeadlineService cardDeadlineService) {
		this.cardDeadlineService = cardDeadlineService;
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
}
