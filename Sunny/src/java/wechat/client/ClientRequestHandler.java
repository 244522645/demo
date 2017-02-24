package wechat.client;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientRequestHandler extends PrepayIdRequestHandler {

	public ClientRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	public String getXmlBody() {
		StringBuffer sb = new StringBuffer();
		@SuppressWarnings("rawtypes")
		Set es = super.getAllParameters().entrySet();
		@SuppressWarnings("rawtypes")
		Iterator it = es.iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"appkey".equals(k)) {
				sb.append("<" + k + ">" + v + "<" + k + ">" + "\r\n");
			}
		}
		return sb.toString();
	}
	public String getJsonBody(){
		return "";
	}
}
