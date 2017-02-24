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
	 * MD5����
	 * 
	 * @return String
	 */
	public String toMD5(String plainText) {
		try {
			// ����ʵ��ָ��ժҪ�㷨�� MessageDigest ����
			MessageDigest md = MessageDigest.getInstance("MD5");
			// ʹ��ָ�����ֽ��������ժҪ��
			md.update(plainText.getBytes());
			// ͨ��ִ���������֮������ղ�����ɹ�ϣ���㡣
			byte b[] = md.digest();
			// ���ɾ����md5���뵽buf����
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
			return buf.toString().toUpperCase();// 32λ�ļ���

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * ����һ���ַ���()���ֻ����� �ַ����Ĺ����ǣ� ��¼���� Status + Phone + time+ POPMessage ��ҳ���ַ�����
	 * phone +time+imsi ����������ַ���,�ֻ����ţ�md5ֵ��
	 */
	public String toKeyMD5(String plainText, String imsi) {

		return toMD5(plainText + imsi + key);

	}

	/*
	 * md5У�� �ַ����Ĺ����ǣ� ��¼���� Status + Phone + time+ POPMessage ��ҳ���ַ����� phone
	 * +time+imsi ����������ַ���,�ֻ����ţ�md5ֵ��
	 * 
	 */
	public boolean equalsMD5(String plainText, String imsi, String md5) {
		return md5.equalsIgnoreCase(toKeyMD5(plainText, imsi));
	}

	/*
	 * md5���� �ַ����Ĺ����ǣ� ע����ܵ��� Phone ����������ֻ��ţ�
	 * 
	 */
	public String addMD5(String phone) {
		return toMD5(phone + regKey);
	}

	// ���ýӿ�
	public String findByCardBank(String urls, String cs) {
		try {
			// ��ȡ��������֤
			HttpsURLConnection.setDefaultHostnameVerifier(new TrustAnyVerifier());
			// ת������
			byte[] data = cs.getBytes("gb2312");
			// ����url
			URL url = new URL(urls);
			// ��url �� open�������ص�urlConnection ����ǿתΪHttpURLConnection����
			// (��ʶһ��url�����õ�Զ�̶�������)
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// �������������Ϊtrue,Ĭ��false (post �����������ķ�ʽ��ʽ�Ĵ��ݲ���)
			conn.setDoOutput(true);
			// ��������ʽΪpost
			conn.setRequestMethod("POST");
			// post���󻺴���Ϊfalse
			conn.setUseCaches(false);
			// ���ø�HttpURLConnectionʵ���Ƿ��Զ�ִ���ض���
			conn.setInstanceFollowRedirects(true);
			// ��������ͷ����ĸ������� (����Ϊ�������ݵ�����,����Ϊ����urlEncoded�������from����)
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// ��������
			// (����δ��ʼ,ֱ��connection.getInputStream()��������ʱ�ŷ���,���ϸ��������������ڴ˷���֮ǰ����)
			conn.connect();
			// �������������,�����������������Я���Ĳ���,(�������Ϊ?���������)
			DataOutputStream dataout = new DataOutputStream(conn.getOutputStream());
			// ���������������
			dataout.write(data);
			// �����ɺ�ˢ�²��ر���
			dataout.flush();
			dataout.close();
			// ���ӷ�������,�����������Ӧ (�����ӻ�ȡ������������װΪbufferedReader)
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			String line;
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {// ѭ����ȡ��

				sb.append(line);
			}
			br.close();// �ر���
			conn.disconnect();// �Ͽ�����
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
	 * ����������֤��xml
	 */
	public String readStringXml(String xml) {
		Document doc = null;
		try {

			// ��ȡ������XML�ĵ�

			// SAXReader����һ���ܵ�����һ�����ķ�ʽ����xml�ļ�������

			// SAXReader reader = new SAXReader(); //User.hbm.xml��ʾ��Ҫ������xml�ĵ�

			// Document document = reader.read(new File("User.hbm.xml"));

			// �������ͨ������xml�ַ�����

			doc = DocumentHelper.parseText(xml); // ���ַ���תΪXML

			Element rootElt = doc.getRootElement(); // ��ȡ���ڵ�

			System.out.println("���ڵ㣺" + rootElt.getName()); // �õ����ڵ������
			Iterator<?> iter;
			iter = rootElt.elementIterator("Errors"); // ��ȡ���ڵ��µ��ӽڵ�head

			// ����head�ڵ�

			while (iter.hasNext()) {

				Element recordEle = (Element) iter.next();
				String errorid = recordEle.elementTextTrim("ErrorID"); // �õ�head�ڵ��µ��ӽڵ�titleֵ

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
	 * �����û�δʹ�õ�����xml
	 */
	public String readUsercardXml(String xml) throws DocumentException {
		Document doc = null;
		String cardMonry = null;
		try {

			// ��ȡ������XML�ĵ�

			// SAXReader����һ���ܵ�����һ�����ķ�ʽ����xml�ļ�������

			// SAXReader reader = new SAXReader(); //User.hbm.xml��ʾ��Ҫ������xml�ĵ�

			// Document document = reader.read(new File("User.hbm.xml"));

			// �������ͨ������xml�ַ�����

			doc = DocumentHelper.parseText(xml); // ���ַ���תΪXML

			Element rootElt = doc.getRootElement(); // ��ȡ���ڵ�

			System.out.println("���ڵ㣺" + rootElt.getName()); // �õ����ڵ������
			Iterator<?> iter;
			iter = rootElt.elementIterator("Datas"); // ��ȡ���ڵ��µ��ӽڵ�head

			// ����head�ڵ�

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

	// ���ýӿ�
	public String gettingCode(String urls, String phone, String result) {
		try {
			// ��ȡ��������֤
			HttpsURLConnection.setDefaultHostnameVerifier(new TrustAnyVerifier());
			// ת������
			// ����url
			URL url = new URL(urls);
			// ��url �� open�������ص�urlConnection ����ǿתΪHttpURLConnection����
			// (��ʶһ��url�����õ�Զ�̶�������)
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// �������������Ϊtrue,Ĭ��false (post �����������ķ�ʽ��ʽ�Ĵ��ݲ���)
			conn.setDoOutput(true);
			// ��������ʽΪpost
			conn.setRequestMethod("POST");
			// post���󻺴���Ϊfalse
			conn.setUseCaches(false);
			// ���ø�HttpURLConnectionʵ���Ƿ��Զ�ִ���ض���
			conn.setInstanceFollowRedirects(true);
			// ��������ͷ����ĸ������� (����Ϊ�������ݵ�����,����Ϊ����urlEncoded�������from����)
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// ��������
			// (����δ��ʼ,ֱ��connection.getInputStream()��������ʱ�ŷ���,���ϸ��������������ڴ˷���֮ǰ����)
			conn.connect();
			// �������������,�����������������Я���Ĳ���,(�������Ϊ?���������)
			DataOutputStream dataout = new DataOutputStream(conn.getOutputStream());
			// ���������������
			String data = "UserName=kangou&UserPass=770310&Mobile=" + phone + "&Content="
					+ URLEncoder.encode("��֤��" + result, "UTF-8");
			dataout.writeBytes(data);
			// �����ɺ�ˢ�²��ر���
			dataout.flush();
			dataout.close();
			// ���ӷ�������,�����������Ӧ (�����ӻ�ȡ������������װΪbufferedReader)
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			String line;
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {// ѭ����ȡ��

				sb.append(line);
			}
			br.close();// �ر���
			conn.disconnect();// �Ͽ�����
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
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������

			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
			connection.setRequestProperty("accept-language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setUseCaches(false);

			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			StringBuffer content = new StringBuffer();
			String tempStr = "";
			while ((tempStr = in.readLine()) != null) {
				System.out.println("���ص���" + tempStr);
				content.append(tempStr);
			}
			result = content.toString();
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
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
