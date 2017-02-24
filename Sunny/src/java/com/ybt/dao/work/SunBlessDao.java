package com.ybt.dao.work;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunBless;

@Component
public interface SunBlessDao extends BaseDao<SunBless, String> {


	/**
	 *  通过订单查询 贺卡
	 * */
	@Query("select a from SunBless a where a.order.id = ? and a.deleted=0")
	public List<SunBless> findByOrder(String orderId);
	
	
	 /**
	 * 我的所有有效订单
	 */
	 @Query(value="select * from sun_bless a where  a.deleted = 0 and a.status = 1   and  ( a.user_id=:mid  or a.to_user_id=:mid )  order by a.create_time desc limit :i,:m", nativeQuery = true)
	 public List<SunBless> getMyAllBlessList(@Param("mid") String mid,@Param("i") int i,@Param("m") int m);
	 /**
	 * 我的有效信封订单
	 */
	 @Query(value="select * from sun_bless a where  a.deleted = 0 and a.status=1 and a.isread=0 and a.issend=1 and a.user_id=? and NOT EXISTS (SELECT id from sun_letter_bless where bless_id=a.id ) order by create_time desc", nativeQuery = true)
	 public List<SunBless> findSunBlessByValidAndUserId(String userid);
	 
	 /**
	 * 我收到的日出明信片
	 */
	 @Query(value="select * from sun_bless a where  a.deleted = 0 and a.status = 1   and  a.to_user_id = :mid  order by a.create_time desc limit :i,:m", nativeQuery = true)
	 public List<SunBless> findMyReceivedBlessList(@Param("mid") String mid,@Param("i") int i,@Param("m") int m);
	 
	 /**
	 * 我送出的日出明信片
	 */
	 @Query(value="select * from sun_bless a where  a.deleted = 0 and a.status = 1    and  a.user_id = :mid  order by a.create_time desc limit :i,:m", nativeQuery = true)
	 public List<SunBless> findMySendBlessList(@Param("mid") String mid,@Param("i") int i,@Param("m") int m);
	 
	 
	 /**
	 * 我未送出的日出明信片
	 */
	 @Query(value="select * from sun_bless a where  a.deleted = 0 and a.status = 1 and a.issend=0   and  a.user_id = :mid  order by a.create_time desc limit :i,:m", nativeQuery = true)
	 public List<SunBless> findMyNoSendBlessList(@Param("mid") String mid,@Param("i") int i,@Param("m") int m);
	 
	 /**
	 * 我收到的日出明信片总数
	 */
	 @Query(value="select count(1) from sun_bless a where  a.deleted = 0 and a.status = 1  and  a.to_user_id=:mid ", nativeQuery = true)
	 public BigInteger findMyReceivedBlessCount(@Param("mid") String mid);
	 
	 /**
	 * 我送出的日出明信片总数
	 */
	 @Query(value="select count(1) from sun_bless a where  a.deleted = 0 and a.status = 1 and  a.issend=1 and  a.user_id=:mid  ", nativeQuery = true)
	 public BigInteger findMySendBlessCount(@Param("mid") String mid);
	 
	 /**
	 * 我未送出的日出明信片总数
	 */
	 @Query(value="select count(1) from sun_bless a where  a.deleted = 0 and a.status = 1 and a.issend=0 and  a.user_id=:mid", nativeQuery = true)
	 public BigInteger findMyNoSendBlessCount(@Param("mid") String mid);
	 
	 /**
	 * 所有推荐 订单
	 */
	 @Query(value="select * from sun_bless a where  a.rec = 1 and  a.deleted = 0 and a.status = 1  order by a.create_time desc limit :i,:m", nativeQuery = true)
	 public List<SunBless> getAllBlessListByRec(@Param("i") int i,@Param("m") int m);

	 /**
	 * 通过二维码ID查询SunBless信息
	 */
	 @Query(value="select * from sun_bless b where b.qrcode_id =(select a.id from sun_qrcode a where a.id=:qrcodeId)", nativeQuery = true)
	 public SunBless findSunBlessByQrcodeId(@Param("qrcodeId") String qrcodeId);
}
