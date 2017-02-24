package com.ybt.dao.work;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunLetter;

@Component
public interface SunLetterDao extends BaseDao<SunLetter, String> {
	/**
	 * 我的所有简信列表
	 */
	 @Query(value="select * from sun_letter a where  a.deleted = 0 and a.status = 1   and  ( a.user_id=:mid  or a.to_user_id=:mid )  order by a.create_time desc limit :i,:m", nativeQuery = true)
	 public List<SunLetter> getMyAllLetterList(@Param("mid") String mid,@Param("i") int i,@Param("m") int m);
	 
	 
	 /**
	 * 我收到的简信列表
	 */
	 @Query(value="select * from sun_letter a where  a.deleted = 0 and a.status = 1   and  a.to_user_id=:mid order by a.create_time desc limit :i,:m", nativeQuery = true)
	 public List<SunLetter> findMyReceivedLetterList(@Param("mid") String mid,@Param("i") int i,@Param("m") int m);
	 
	 /**
	 * 我送出的简信列表
	 */
	 @Query(value="select * from sun_letter a where  a.deleted = 0 and a.status = 1 and a.issend=1   and  a.user_id=:mid  order by a.create_time desc limit :i,:m", nativeQuery = true)
	 public List<SunLetter> findMySendLetterList(@Param("mid") String mid,@Param("i") int i,@Param("m") int m);
		 
	 /**
	 * 我未送出的简信列表（草稿列表）
	 */
	 @Query(value="select * from sun_letter a where  a.deleted = 0 and a.status = 1 and a.issend=0   and  a.user_id=:mid  order by a.create_time desc limit :i,:m", nativeQuery = true)
	 public List<SunLetter> findMyNoSendLetterList(@Param("mid") String mid,@Param("i") int i,@Param("m") int m);
	 
	 
	 /**
	 * 我收到的简信总数
	 */
	 @Query(value="select count(1) from sun_letter a where  a.deleted = 0 and a.status = 1  and  a.to_user_id=:mid ", nativeQuery = true)
	 public BigInteger findMyReceivedLetterCount(@Param("mid") String mid);
	 
	 /**
	 * 我送出的简信总数
	 */
	 @Query(value="select count(1) from sun_letter a where  a.deleted = 0 and a.status = 1 and  a.issend=1 and  a.user_id=:mid  ", nativeQuery = true)
	 public BigInteger findMySendLetterCount(@Param("mid") String mid);
	 
	 /**
	 * 我未送出的简信列表（草稿列表）
	 */
	 @Query(value="select count(1) from sun_letter a where  a.deleted = 0 and a.status = 1 and a.issend=0 and  a.user_id=:mid", nativeQuery = true)
	 public BigInteger findMyNoSendLetterCount(@Param("mid") String mid);
}
