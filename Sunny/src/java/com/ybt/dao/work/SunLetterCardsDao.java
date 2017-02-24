package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunFeedback;
import com.ybt.model.work.SunLetterCards;
import com.ybt.model.work.SunLetterPhotos;

@Component
public interface SunLetterCardsDao extends BaseDao<SunLetterCards, String> {
	
	@Query("select a from SunLetterCards a where a.letterId.id = ? ")
	public List<SunLetterCards> findByLetterId(String letterId);

}
