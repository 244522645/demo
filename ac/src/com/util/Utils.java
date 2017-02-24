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
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Utils {

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
			return buf.toString();// 32λ�ļ���

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	 * ��֤�û���������
	 * 
	 */
	public String getProportion() {
		// UUid
		UUID uuid = UUID.randomUUID();
		String hashCodes = "ebae4cc720589685f517ed6ab95bac36" + "88888888-3333-3333-6666-888888888888" + uuid
				+ "95370000000-95379999999" + "GetCardRemainCount";

		String password = toMD5(hashCodes);
		String cs = "HashCode=" + password + "&UserId=88888888-3333-3333-6666-888888888888&OrderId=" + uuid
				+ "&FunctionID=GetCardRemainCount&HaoDuan=" + "95370000000-95379999999";
		String urls = "https://passport.kangou.cn/Service/CardAPI.aspx";

		return findByCardBank(urls, cs);

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
				Iterator<?> itrs= recordEle.elementIterator("CardRemainCount");
				while(itrs.hasNext()){
					Element recordEles = (Element) itrs.next();
					cardMonry=recordEles.elementTextTrim("RemainCount");
				}
				
			}

		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return cardMonry;
	}



}
