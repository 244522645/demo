package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.CrowUserTree;
@Component
public interface CrowUserTreeDao extends BaseDao<CrowUserTree, String> {
	
	 /**
	 * 订单商品
	 */
	 @Query("select a from CrowUserTree a where  a.descendant=? and  a.deleted = 0")
	 public List<CrowUserTree> findCrowUserTreeUserId(String userId);
}
