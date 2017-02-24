package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunDdOrder;

public interface OrderDao extends BaseDao<SunDdOrder, String> {
	 /**
	 * 卖家全部订单
	 */
	 @Query("select a from SunDdOrder a where  a.sellerId=? and  a.deleted = 0 and a.sellDeleted=0 order by a.createTime desc")
	 public List<SunDdOrder> getOrderBySellerId(String sellerId);
	 /**
	 * 买家全部订单
	 */
	 @Query("select a from SunDdOrder a where  a.buyerId=? and  a.deleted = 0 and a.buyDeleted=0 order by a.createTime desc")
	 public List<SunDdOrder> getOrderByBuyerId(String buyerId);
	 
	 /**
	 * 买家某状态订单
	 */
	 @Query("select a from SunDdOrder a where  a.buyerId=? and  a.deleted = 0 and status = ? order by a.createTime desc")
	 public List<SunDdOrder> getStatusOrderByBuyerId(String buyerId,int s);
	 /**
	 * 卖家某状态订单
	 */
	 @Query("select a from SunDdOrder a where  a.sellerId=? and  a.deleted = 0 and status = ?  order by a.createTime desc")
	 public List<SunDdOrder> getStatusOrderBySellerId(String sellerId,int s);
	 /**
	 * 所有正在进行中的订单
	 */
	 @Query("select a from SunDdOrder a where a.deleted = 0 and (status like '2%' or status like '3%' )order by a.createTime desc")
	 public List<SunDdOrder> getDoingOrder();
	 
	 /**
	 * 我的所有有效订单
	 */
	 @Query(value="select * from sun_dd_order a where ( a.deleted = 0 and a.status != '10' and a.buyer_id=:mid ) or  ( a.deleted = 0 and a.status = '100' and a.seller_id=:mid )  order by a.create_time desc limit :i,:m", nativeQuery = true)
	 public List<SunDdOrder> getMyOrderList(@Param("mid") String mid,@Param("i") int i,@Param("m") int m);
	 
	 /**
	 * 我的所有有效订单
	 */
	 @Query(value="select * from sun_dd_order a where  a.deleted = 0 and a.status = :state and a.buyer_id=:mid   order by a.create_time desc limit :i,:m", nativeQuery = true)
	 public List<SunDdOrder> getMyOrderListByState(@Param("mid") String mid,@Param("state") String state,@Param("i") int i,@Param("m") int m);
	
	 
	 /**
	 * 生成订单号
	 */
	 public String getDDH();
	 
	 /**
	 * 根据订单号orderNo查询
	 */
	 @Query("select a from SunDdOrder a where  a.orderNo=? and  a.deleted = 0")
	 public SunDdOrder getOrderByOrderNo(String orderNo);
	 /**
	 * 获取买家补贴订单
	 */
	 @Query("select a from SunDdOrder a where a.deleted = 0 and status !=0 and payment=1 and buyerSubsidyMoney is not NULL AND buyerSubsidyMoney >0 and buyerId=? ")
	 public List<SunDdOrder> getSubsidyOrderByBuyerId(String buyerId);
	 /**
	 * 获取卖家补贴订单
	 */
	 @Query("select a from SunDdOrder a where a.deleted = 0 and status !=0 and payment=1 and sellerSubsidyMoney is not NULL AND sellerSubsidyMoney >0 and sellerId=? ")
	 public List<SunDdOrder> getSubsidyOrderBySellerId(String sellerId);
	 
	 /**
	 * 判断买方是否可以补贴
	 */
	 @Query("select count(a) from SunDdOrder a where a.deleted = 0 and status !=0 and payment=1 and buyerSubsidyMoney is not NULL AND buyerSubsidyMoney >0 and buyerId=? ")
	 public long getBuySubsidyOrder(String buyerId);
	 
	 /**
	 * 判断卖方是否可以补贴
	 */
	 @Query("select count(a) from SunDdOrder a where a.deleted = 0 and status !=0 and payment=1 and sellerSubsidyMoney is not NULL AND sellerSubsidyMoney >0 and sellerId=? ")
	 public long getSellSubsidyOrder(String sellerId);

}
