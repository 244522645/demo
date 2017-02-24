package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunAttraction;

public interface AttractionDao extends BaseDao<SunAttraction, String> {
	@Query(value="select * from sun_attraction  a where a.deleted=0  GROUP BY a.city ORDER BY a.abc", nativeQuery = true)
	public List<SunAttraction> findByGroupByCity();
}
