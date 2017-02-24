package com.ybt.dao.work;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunCard;

/**
 * 阳光卡绑定
 * @author momo
 *@param cardNum
 *@param cardPass
 */
@Component
public interface SunCardDao extends BaseDao<SunCard,String>{
	
	//阳光卡绑定
    // public int blind(String cardNum,String cardPass);
	//通过卡号查询
	public com.ybt.common.util.Page<SunCard> findSunCardByNumber(String number,com.ybt.common.util.Page<SunCard> page);
	    

	/**
	 *  验证卡
	 * */
	@Query("select a from SunCard a where a.number = ? and a.cardPassword=?")
	public SunCard findByNumberAndPassWord(String number,String password);
	
	/**
	 * 根据卡号 服务卡
	 * */
	@Query("select a from SunCard a where a.number = ?")
	public SunCard findByNumber(String number);
	/**
	 * 生成T卡号
	 * */
	public  Long createCardNumber();
	
	/**
	 * 根据卡号 服务卡
	 * */
	@Query("select a from SunCard a where a.bindingTime >= ? and a.userId=?")
	public List<SunCard> findSunCardBybindingTimeAndUserId(Date date,String userid);
	
	/**
	 * 获取有效卡列表
	 * */
	@Query(value="select * from sun_card a where a.status=1 and a.user_id=?  AND NOT EXISTS (SELECT id from sun_letter_card WHERE deleted=0 and  card_id=a.id)", nativeQuery = true)
	public List<SunCard> findSunCardByValidAndUserId(String userid);
	
	/**
	 * 获取有效的卡列表
	 * */
	@Query(value="select * from sun_card a where a.status=1 and a.user_id=:userId order by a.create_time desc limit :i,:m", nativeQuery = true)
	public List<SunCard> findSunCardByStatusAndUserId(@Param("userId") String userId,@Param("i") int i,@Param("m") int m);
	
	
	/**
	 * 获取已使用卡列表
	 * */
	@Query(value="select * from sun_card a where a.status=2 and a.user_id=:userId order by a.create_time desc limit :i,:m", nativeQuery = true)
	public List<SunCard> findSunCardByUsedAndUserId(@Param("userId") String userId,@Param("i") int i,@Param("m") int m);
	/**
	 * 获取已使用卡列表
	 * */
	@Query(value="select * from sun_card a where a.status=2 and a.user_id=:userId order by a.create_time desc", nativeQuery = true)
	public List<SunCard> findSunCardByUsedAndUserId(@Param("userId") String userId);
	
	/**
	 *  Card数
	 * */
	@Query("select count(a) from SunCard a where a.userId=? and a.status=1 and  a.deleted = 0")
	public Long getMyCardCount(String mid);
	
}
