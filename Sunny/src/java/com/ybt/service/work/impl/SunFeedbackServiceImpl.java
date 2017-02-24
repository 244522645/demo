package com.ybt.service.work.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunFeedbackDao;
import com.ybt.model.work.SunFeedback;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunFeedbackService;

@Component
public class SunFeedbackServiceImpl extends BaseServiceImpl<SunFeedback, String> implements SunFeedbackService{
	
	@Autowired
	private SunFeedbackDao photoDao;
	
	@Override
	public BaseDao<SunFeedback, String> getDao() {
		return photoDao;
	}
	

}
