/*package com.ybt.web.base.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
*//**   
 * url过滤器 
 * @author AndyBao  
 * @version 4.0, 2016年6月13日 上午10:24:01   
 *//*   
public class loginFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 2873394003927196552L;
	private Map<String,String> constant;

	public void doFilter(ServletRequest req, ServletResponse res,FilterChain filter) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(true);
		String userId = (String) session.getAttribute("userId");
		String url = request.getServletPath();
		//加载 项目信息
		// Map<String,String> properties = CustomPropertyConfigurer.getProperties();
		 request.setAttribute("config", constant);
		 
		if ("".equals(url)||"/".equals(url)) {
			if(userId == null || "".equals(userId)) {
				response.sendRedirect(request.getContextPath() + "/console/login");
			}else{
				filter.doFilter(req, res);		
			}
			return ;
		}
		
		filter.doFilter(req, res);
		return ;
	}

	@SuppressWarnings("unchecked")
	public void init(FilterConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		constant = (Map<String,String>) ctx.getBean("constant");
	}

	public Map<String, String> getConstant() {
		return constant;
	}

	public void setConstant(Map<String, String> constant) {
		this.constant = constant;
	}
}
*/