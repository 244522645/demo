package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.model.UserInfo;

public class CommonInterceptor extends HandlerInterceptorAdapter {
	Logger log = Logger.getRootLogger();

	/**
	 * ��ҵ��������������֮ǰ������ �������false �ӵ�ǰ������������ִ��������������afterCompletion(),���˳���������
	 * �������true ִ����һ��������,ֱ�����е���������ִ����� ��ִ�б����ص�Controller Ȼ�������������,
	 * �����һ������������ִ�����е�postHandle() �����ٴ����һ������������ִ�����е�afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("==============ִ��˳��: 1��preHandle================");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		String ip = request.getRemoteAddr();
		log.info(request.getSession().getId());
		log.info("requestUri:" + requestUri);
		log.info("contextPath:" + contextPath);
		log.info("url:" + url);
		log.info("���ʵ�ַ��IP"+ip);
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute("currentUser");
		if (userinfo == null||"".equals(userinfo)) {
			log.info("Interceptor����ת��loginҳ�棡");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return false;
		} else
			return true;
	}
}
