package wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

/**
 * XML 数据接收对象转换工具类
 * 
 * @author LiYi
 * 
 */
public class XMLConverUtil {
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><rescueServiceOrderReqResponse>"
				+ "<Head>"
				+ "<Sid>消息流水号</Sid>"
				+ "<Timestamp>时间戳</Timestamp>"
				+ "<Serviceid>业务应用标识</Serviceid>"
				+ "<Version>版本标识</Version>"
				+ "</Head>"
				+ "<body>加密后的消息体</body>"
				+ "<Hash>消息摘要</Hash>"
				+ "</rescueServiceOrderReqResponse>";

		/*
		 * RescueServiceOrderReqResponse r=new RescueServiceOrderReqResponse();
		 * Head h=new Head(); h.setServiceid("44444444"); h.setSid("2");
		 * h.setTimestamp("201504152014"); //h.setVersion("V2"); r.setHead(h);
		 * r.setBody("544445454545"); r.setHash("4545454");
		 * 
		 * System.out.println(convertToXML(r));
		 */
		/*
		 * OrderReqBody b=new OrderReqBody();
		 * //EncryptUtil.getInstance().AESencode(res,key); String
		 * aes=convertToXML(b); //aes=aes.substring(6);
		 * //System.out.println(aes); String sub=stringToSubXML(aes,"body");
		 * System.out.println("sub:"+sub); String
		 * jiami=EncryptUtil.getInstance().AESencode(sub, "123456");
		 * System.out.println("加密："+jiami);
		 * 
		 * System.out.println("解密："+EncryptUtil.getInstance().AESdecode(jiami,
		 * "123456"));
		 */

	}

	private static Map<Class<?>, Unmarshaller> uMap = new HashMap<Class<?>, Unmarshaller>();
	private static Map<Class<?>, Marshaller> mMap = new HashMap<Class<?>, Marshaller>();

	/**
	 * XML to Object
	 * 
	 * @param <T>
	 * @param clazz
	 * @param xml
	 * @return
	 */
	public static <T> T convertToObject(Class<T> clazz, String xml) {
		return convertToObject(clazz, new StringReader(xml));
	}

	/**
	 * XML to Object
	 * 
	 * @param <T>
	 * @param clazz
	 * @param inputStream
	 * @return
	 */
	public static <T> T convertToObject(Class<T> clazz, InputStream inputStream) {
		try {
			return convertToObject(clazz,new InputStreamReader(inputStream,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertToObject(clazz,inputStream);
	}

	/**
	 * XML to Object
	 * 
	 * @param <T>
	 * @param clazz
	 * @param reader
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertToObject(Class<T> clazz, Reader reader) {
		try {
			if (!uMap.containsKey(clazz)) {
				JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				uMap.put(clazz, unmarshaller);
			}
			return (T) uMap.get(clazz).unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Object to XML
	 * 
	 * @param object
	 * @return
	 */
	public static String convertToXML(Object object) {
		try {
			if (!mMap.containsKey(object.getClass())) {
				JAXBContext jaxbContext = JAXBContext.newInstance(object
						.getClass());
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
				marshaller.setProperty(CharacterEscapeHandler.class.getName(),
						new CharacterEscapeHandler() {
							public void escape(char[] ac, int i, int j,
									boolean flag, Writer writer)
									throws IOException {
								writer.write(ac, i, j);
							}
						});
				mMap.put(object.getClass(), marshaller);
			}
			StringWriter stringWriter = new StringWriter();
			mMap.get(object.getClass()).marshal(object, stringWriter);
			return stringWriter.getBuffer().toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Object to XML
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String stringToSubXML(String object, String sub) {
		InputStream in = HttpClientUtil.String2Inputstream(object);
		Document doc = Jsoup.parse(object);
		Elements roots = doc.select(sub);

		for (Element root : roots) {
			// System.out.println(root.html());
		}
		;
		return roots.html();
	}
}
