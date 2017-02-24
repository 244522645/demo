package com.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.service.LoginService;
import com.service.NoticesService;
import com.service.PosOrdersService;
import com.service.QRcodeService;
import com.util.Utils;

public class BaseController {
	Utils utils = new Utils();
	Logger log = Logger.getRootLogger();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired(required = false)
	private LoginService loginService;

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Autowired(required = false)
	private QRcodeService qRcodeService;

	public QRcodeService getqRcodeService() {
		return qRcodeService;
	}

	public void setqRcodeService(QRcodeService qRcodeService) {
		this.qRcodeService = qRcodeService;
	}

	@Autowired(required = false)
	private NoticesService noticesService;

	public NoticesService getNoticesService() {
		return noticesService;
	}

	public void setNoticesService(NoticesService noticesService) {
		this.noticesService = noticesService;
	}

	@Autowired(required = false)
	private PosOrdersService posOrdersService;

	public PosOrdersService getPosOrdersService() {
		return posOrdersService;
	}

	public void setPosOrdersService(PosOrdersService posOrdersService) {
		this.posOrdersService = posOrdersService;
	}

}
