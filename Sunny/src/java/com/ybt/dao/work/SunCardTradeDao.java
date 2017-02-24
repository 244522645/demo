package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunCardTrade;

@Component
public interface SunCardTradeDao extends BaseDao<SunCardTrade, String> {
	
	/**
	 *  通过userid 和 cardid 查询
	 * */
	@Query("select a from SunCardTrade a where  a.userId.id = ? and a.card.id = ? and a.deleted=0")
	public List<SunCardTrade> findByUserIdAndCardId(String userId,String cardId);
	
	
	/**
	 *  查询 阳光卡送出 纪录(逻辑改变此方法作废)
	 * */
	@Query(value="select * from sun_card_trade a where  a.deleted = 0    and   a.user_id=:userId  order by a.update_time desc limit :i,:m", nativeQuery = true)
	public List<SunCardTrade> findSunCardTradeListByUserId(@Param("userId") String userId,@Param("i") int i,@Param("m") int m);
	
	/**
	 *  查询 阳光卡收到 纪录(逻辑改变此方法作废)
	 * */
	@Query(value="select * from sun_card_trade a where  a.deleted = 0    and   a.to_user_id=:userId  order by a.create_time desc limit :i,:m", nativeQuery = true)
	public List<SunCardTrade> findSunCardTradeListByToUserId(@Param("userId") String userId,@Param("i") int i,@Param("m") int m);
	
	
	/**
	 *  通过userid查询收到和送出记录
	 * */
	@Query(value="select * from sun_card_trade a where a.deleted=0 and a.user_id = :userId ORDER BY a.create_time DESC limit 20", nativeQuery = true)
	public List<SunCardTrade> findRecvAndSendSunCardTradeList(@Param("userId") String userId);
}
