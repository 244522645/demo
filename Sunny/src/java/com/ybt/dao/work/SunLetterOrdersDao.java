package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunFeedback;
import com.ybt.model.work.SunLetterOrders;
import com.ybt.model.work.SunLetterPhotos;

@Component
public interface SunLetterOrdersDao extends BaseDao<SunLetterOrders, String> {
	
	@Query("select a from SunLetterOrders a where a.letterId.id = ? ")
	public List<SunLetterOrders> findByLetterId(String letterId);

}
