package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunFeedback;
import com.ybt.model.work.SunLetterPhotos;

@Component
public interface SunLetterPhotosDao extends BaseDao<SunLetterPhotos, String> {
	
	@Query("select a from SunLetterPhotos a where a.letterId.id = ? ")
	public List<SunLetterPhotos> findByLetterId(String letterId);

}
