package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunZyGreetings;

public interface SunZyGreetingsDao extends BaseDao<SunZyGreetings, String> {
	 /**
	 * 卖家全部订单
	 */
	 @Query("select a from SunZyGreetings a where  a.tags like ? and  a.deleted = 0 ")
	 public List<SunZyGreetings> getGreetingsByTags(String tags);
}
