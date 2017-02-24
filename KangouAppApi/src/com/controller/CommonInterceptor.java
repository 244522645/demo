package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.model.UserInfo;

public class CommonInterceptor extends HandlerInterceptorAdapter {
	Logger log = Logger.getRootLogger();

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("==============执行顺序: 1、preHandle================");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		String ip = request.getRemoteAddr();
		log.info(request.getSession().getId());
		log.info("requestUri:" + requestUri);
		log.info("contextPath:" + contextPath);
		log.info("url:" + url);
		log.info("访问地址的IP"+ip);
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute("currentUser");
		if (userinfo == null||"".equals(userinfo)) {
			log.info("Interceptor：跳转到login页面！");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return false;
		} else
			return true;
	}
}
