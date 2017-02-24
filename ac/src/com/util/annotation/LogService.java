package com.util.annotation;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.model.KUser;

//声明这是一个切面Bean
@Aspect
@Component
public class LogService {
	SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	Date date = new Date();

	private final static Log log = LogFactory.getLog(LogService.class);

	// 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution (* com.controller.UserinfoController.login(..))")
	public void aspect() {
	}

	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@After("aspect()")
	public ModelAndView after(JoinPoint joinPoint) {

		// 读取session中的用户
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		KUser user = (KUser) session.getAttribute("username");
		// 获取请求ip
		String ip = request.getRemoteAddr();
		System.out.println(ip);
		if (user != null && !user.equals("")) {
			log.warn(user.getUsername() + "查询,ip:" + ip + "，时间" + dateFormater.format(date) + "，执行方法"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		} else {
			log.warn("ip:" + ip + "，时间" + dateFormater.format(date) + "，执行方法"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		}
		return null;
	}

	@Pointcut("execution (* com.controller.*Controller.find*(..))")
	public void find() {
	}

	@After("find()")
	public void findafter(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		KUser user = (KUser) session.getAttribute("username");
		String ip = request.getRemoteAddr();

		if (user != null && !user.equals("")) {
			log.warn(user.getUsername() + "查询,ip:" + ip + "，时间" + dateFormater.format(date) + "，执行方法"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		} else {
			log.warn("ip:" + ip + "，时间" + dateFormater.format(date) + "，执行方法"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		}
	}

	@Pointcut("execution (* com.controller.*Controller.add*(..))")
	public void add() {
	}

	@After("add()")
	public void addafter(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		KUser user = (KUser) session.getAttribute("username");
		String ip = request.getRemoteAddr();
		if (user != null && !user.equals("")) {
			log.warn(user.getUsername() + "添加,ip:" + ip + "，时间" + dateFormater.format(date) + "，执行方法"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		} else {
			log.warn("ip:" + ip + "，时间" + dateFormater.format(date) + "，执行方法"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		}
	}

	@Pointcut("execution (* com.controller.*Controller.update*(..))")
	public void update() {
	}

	@After("update()")
	public void updateafter(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		KUser user = (KUser) session.getAttribute("username");
		String ip = request.getRemoteAddr();
		if (user != null && !user.equals("")) {
			log.warn(user.getUsername() + "修改,ip:" + ip + "，时间" + dateFormater.format(date) + "，执行方法"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		} else {
			log.warn("ip:" + ip + "，时间" + dateFormater.format(date) + "，执行方法"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());

		}
	}

	@Pointcut("execution (* com.controller.*Controller.delete*(..))")
	public void delete() {
	}

	@After("delete()")
	public void deleteafter(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		KUser user = (KUser) session.getAttribute("username");
		String ip = request.getRemoteAddr();
		if (user != null && !user.equals("")) {
			log.warn(user.getUsername() + "删除,ip:" + ip + "，时间" + dateFormater.format(date) + "，执行方法"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		}else{
			log.warn("ip:" + ip + "，时间" + dateFormater.format(date) + "，执行方法"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		}
		
	}

}
