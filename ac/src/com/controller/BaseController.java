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

	// ��ʽ��ʱ��
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
}
