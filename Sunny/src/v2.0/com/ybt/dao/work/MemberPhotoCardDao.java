package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.MemberPhotoCard;

@Component
public interface MemberPhotoCardDao extends BaseDao<MemberPhotoCard, String>{

	/**
	 * sql 分页查询照片列表
	 * 
	 * deleted   0: 正常    1：（逻辑）已删除
	 */
	 @Query(value="select * from member_photo_card a where  a.deleted = 0 order by a.create_time asc limit :i,:m", nativeQuery = true)
	 public List<MemberPhotoCard> findMemberPhotoCardList(@Param("i") int i,@Param("m") int m);
	 
}
