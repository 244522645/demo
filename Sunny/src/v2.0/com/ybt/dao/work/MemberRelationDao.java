package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.MemberRelation;

@Component
public interface MemberRelationDao  extends BaseDao<MemberRelation, String>{
	
	@Query(value="select * from member_relation a where a.deleted = 0 and a.user_id = :userId ", nativeQuery = true)
	public List<MemberRelation> findByUserId(@Param("userId") String userId);
}

