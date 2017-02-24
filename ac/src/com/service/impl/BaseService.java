package com.service.impl;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mapper.KCardDeadlineMapper;
import com.mapper.KCardKindsMapper;
import com.mapper.KCardOrdersMapper;
import com.mapper.KUserMapper;
import com.mapper.KUserMoneyChangesMapper;
import com.mapper.UserCardMapper;
import com.util.Utils;

public class BaseService {
	 Logger log=Logger.getRootLogger();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired(required=false)
	private KCardKindsMapper kCardKindsMapper;
	@Autowired(required=false)
	private UserCardMapper userCardMapper;
	@Autowired(required=false)
	private KCardOrdersMapper  cardOrdersMapper;
	@Autowired(required=false)
	private UserCardMapper cardMapper;
	@Autowired(required=false)
	private KUserMoneyChangesMapper kUserMoneyChangesMapper;
	@Autowired(required=false)
	private KUserMapper kUserMapper;
	@Autowired(required=false)
	private KCardDeadlineMapper cardDeadlineMapper;
	Utils utils=new Utils();
	

	public KCardKindsMapper getkCardKindsMapper() {
		return kCardKindsMapper;
	}

	public void setkCardKindsMapper(KCardKindsMapper kCardKindsMapper) {
		this.kCardKindsMapper = kCardKindsMapper;
	}
	
	
	public UserCardMapper getUserCardMapper() {
		return userCardMapper;
	}

	public void setUserCardMapper(UserCardMapper userCardMapper) {
		this.userCardMapper = userCardMapper;
	}

	

	public KCardOrdersMapper getCardOrdersMapper() {
		return cardOrdersMapper;
	}

	public void setCardOrdersMapper(KCardOrdersMapper cardOrdersMapper) {
		this.cardOrdersMapper = cardOrdersMapper;
	}

	
	public UserCardMapper getCardMapper() {
		return cardMapper;
	}

	public void setCardMapper(UserCardMapper cardMapper) {
		this.cardMapper = cardMapper;
	}
	

	public KUserMoneyChangesMapper getkUserMoneyChangesMapper() {
		return kUserMoneyChangesMapper;
	}

	public void setkUserMoneyChangesMapper(KUserMoneyChangesMapper kUserMoneyChangesMapper) {
		this.kUserMoneyChangesMapper = kUserMoneyChangesMapper;
	}
	
	
	
	public KUserMapper getkUserMapper() {
		return kUserMapper;
	}

	public void setkUserMapper(KUserMapper kUserMapper) {
		this.kUserMapper = kUserMapper;
	}
	
	
	public KCardDeadlineMapper getCardDeadlineMapper() {
		return cardDeadlineMapper;
	}

	public void setCardDeadlineMapper(KCardDeadlineMapper cardDeadlineMapper) {
		this.cardDeadlineMapper = cardDeadlineMapper;
	}
	
	
}
