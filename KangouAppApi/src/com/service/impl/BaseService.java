package com.service.impl;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mapper.CardsTongMapper;
import com.mapper.NoticeLogsMapper;
import com.mapper.NoticesMapper;
import com.mapper.PosCinemasMapper;
import com.mapper.PosOrdersMapper;
import com.mapper.UserErrorMapper;
import com.mapper.UserInfoMapper;
import com.mapper.VersionMapper;
import com.util.Utils;

public class BaseService {
	Utils utils = new Utils();
	Logger log = Logger.getRootLogger();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired(required = false)
	private UserInfoMapper userInfoMapper;

	public UserInfoMapper getUserInfoMapper() {
		return userInfoMapper;
	}

	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}

	@Autowired(required = false)
	private UserErrorMapper userErrorMapper;

	public UserErrorMapper getUserErrorMapper() {
		return userErrorMapper;
	}

	public void setUserErrorMapper(UserErrorMapper userErrorMapper) {
		this.userErrorMapper = userErrorMapper;
	}

	@Autowired(required = false)
	private CardsTongMapper cardsTongMapper;

	public CardsTongMapper getCardsTongMapper() {
		return cardsTongMapper;
	}

	public void setCardsTongMapper(CardsTongMapper cardsTongMapper) {
		this.cardsTongMapper = cardsTongMapper;
	}

	@Autowired(required = false)
	private VersionMapper versionMapper;

	public VersionMapper getVersionMapper() {
		return versionMapper;
	}

	public void setVersionMapper(VersionMapper versionMapper) {
		this.versionMapper = versionMapper;
	}

	@Autowired(required = false)
	private NoticesMapper noticesMapper;

	public NoticesMapper getNoticesMapper() {
		return noticesMapper;
	}

	public void setNoticesMapper(NoticesMapper noticesMapper) {
		this.noticesMapper = noticesMapper;
	}

	@Autowired(required = false)
	private NoticeLogsMapper logsMapper;

	public NoticeLogsMapper getLogsMapper() {
		return logsMapper;
	}

	public void setLogsMapper(NoticeLogsMapper logsMapper) {
		this.logsMapper = logsMapper;
	}

	@Autowired(required = false)
	private PosOrdersMapper posOrdersMapper;

	public PosOrdersMapper getPosOrdersMapper() {
		return posOrdersMapper;
	}

	public void setPosOrdersMapper(PosOrdersMapper posOrdersMapper) {
		this.posOrdersMapper = posOrdersMapper;
	}

	@Autowired(required = false)
	private PosCinemasMapper posCinemasMapper;

	public PosCinemasMapper getPosCinemasMapper() {
		return posCinemasMapper;
	}

	public void setPosCinemasMapper(PosCinemasMapper posCinemasMapper) {
		this.posCinemasMapper = posCinemasMapper;
	}

}
