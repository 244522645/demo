package com.ybt.dao.work;

import org.springframework.data.jpa.repository.Query;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunFeedback;

public interface SunFeedbackDao extends BaseDao<SunFeedback, String>{
	@Query("select a from SunFeedback a where a.id.id = ? ")
	public SunFeedback findById(String mid);
}
