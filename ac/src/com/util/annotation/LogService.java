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

//��������һ������Bean
@Aspect
@Component
public class LogService {
	SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	Date date = new Date();

	private final static Log log = LogFactory.getLog(LogService.class);

	// ���������,�÷����޷�����,��ҪΪ����ͬ������������ʹ�ô˴����õ������
	@Pointcut("execution (* com.controller.UserinfoController.login(..))")
	public void aspect() {
	}

	/*
	 * ����ǰ��֪ͨ,ʹ���ڷ���aspect()��ע�������� ͬʱ����JoinPoint��������,����û�иò���
	 */
	@After("aspect()")
	public ModelAndView after(JoinPoint joinPoint) {

		// ��ȡsession�е��û�
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		KUser user = (KUser) session.getAttribute("username");
		// ��ȡ����ip
		String ip = request.getRemoteAddr();
		System.out.println(ip);
		if (user != null && !user.equals("")) {
			log.warn(user.getUsername() + "��ѯ,ip:" + ip + "��ʱ��" + dateFormater.format(date) + "��ִ�з���"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		} else {
			log.warn("ip:" + ip + "��ʱ��" + dateFormater.format(date) + "��ִ�з���"
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
			log.warn(user.getUsername() + "��ѯ,ip:" + ip + "��ʱ��" + dateFormater.format(date) + "��ִ�з���"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		} else {
			log.warn("ip:" + ip + "��ʱ��" + dateFormater.format(date) + "��ִ�з���"
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
			log.warn(user.getUsername() + "���,ip:" + ip + "��ʱ��" + dateFormater.format(date) + "��ִ�з���"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		} else {
			log.warn("ip:" + ip + "��ʱ��" + dateFormater.format(date) + "��ִ�з���"
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
			log.warn(user.getUsername() + "�޸�,ip:" + ip + "��ʱ��" + dateFormater.format(date) + "��ִ�з���"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		} else {
			log.warn("ip:" + ip + "��ʱ��" + dateFormater.format(date) + "��ִ�з���"
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
			log.warn(user.getUsername() + "ɾ��,ip:" + ip + "��ʱ��" + dateFormater.format(date) + "��ִ�з���"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		}else{
			log.warn("ip:" + ip + "��ʱ��" + dateFormater.format(date) + "��ִ�з���"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
		}
		
	}

}
