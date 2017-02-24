package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunLetterBless;

@Component
public interface SunLetterBlessDao extends BaseDao<SunLetterBless, String> {
	
	@Query("select a from SunLetterBless a where a.letterId.id = ? ")
	public List<SunLetterBless> findByLetterId(String letterId);

}
