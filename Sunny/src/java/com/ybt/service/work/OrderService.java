package com.ybt.service.work;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Transfer;
import com.ybt.common.util.PropertyFilter;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunDdStatus;
import com.ybt.service.base.IBaseService;

@Component
public interface OrderService extends IBaseService<SunDdOrder, String> {
	/**
	 * 
	 *@description  ping++企业付款
	 *@time    创建时间:2017年2月8日
	 *@param charge
	 *@author   lhq
	 * @throws Exception 
	 */
	Transfer EntPayToPerPingPP(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 
	 *@description  ping++推送事件通过charge对象修改支付流程状态
	 *@time    创建时间:2016年3月14日上午10:50:43
	 *@param charge
	 *@author   zhz
	 */
	SunDdOrder payPingPP(Charge charge);
	/**
	 * 
	 *@description 阳光卡推送
	 *@time    创建时间:2016年3月14日上午10:50:43
	 *@param charge
	 *@author   zhz
	 */
	SunDdOrder paySunCard(SunDdOrder order, SunCard card);
	
	/**
	 *@name    支付订单后修改状态，保存支付记录
	 *@description 相关说明
	 *@time    创建时间:2016年2月23日下午3:16:34
	 *@param order 订单
	 *@param accountId 操作员ID
	 *@param bid 操作修改状态的商户id
	 *@param payType ping++支付方式
	 *@param pingppId ping++支付id
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	SunDdOrder payOrderOnLine(String id, String accountId, String bid,
			String payType, String pingppId);
	
	/**
	 *@description 交易流程--账户变更
	 *@time    创建时间:2016年3月14日上午10:50:04
	 *@author   zhz
	 */
	public int saveTradeForOrder(SunDdOrder order, String payType,
			String pingppId, String string);
	
	/* 根据订单号orderNo查询
	 */
	 public SunDdOrder getOrderByOrderNo(String orderNo);
		/**
		 *@name    获取流程
		 *@description 相关说明
		 *@time    创建时间:2016年2月26日下午5:24:05
		 *@param payment
		 *@param delivery
		 *@return
		 *@author   高艳花
		 *@history 修订历史（历次修订内容、修订人、修订时间等）
		 */
		List<SunDdStatus> getLcxx(int payment, int delivery);

		/**
		 *@name    保存订单信息
		 *@description 相关说明
		 *@time    创建时间:2016年6月26日下午4:33:02
		 *@param order
		 *@param openId
		 *@return
		 *@author   高艳花
		 *@history 修订历史（历次修订内容、修订人、修订时间等）
		 */
		SunDdOrder saveOrder(SunDdOrder order, String openId);
		/**
		 *@name   分页加载收到贺卡信息
		 *@description 相关说明
		 *@time    创建时间:2016年6月26日下午4:32:35
		 *@param filters
		 *@param pageable
		 *@return
		 *@author   高艳花
		 *@history 修订历史（历次修订内容、修订人、修订时间等）
		 */
		Page<SunDdOrder> findOrderGetByOpenId(List<PropertyFilter> filters,
				Pageable pageable);

		/**
		 *@name    分页加载送出贺卡信息
		 *@description 相关说明
		 *@time    创建时间:2016年6月26日下午4:32:17
		 *@param filters
		 *@param pageable
		 *@return
		 *@author   高艳花
		 *@history 修订历史（历次修订内容、修订人、修订时间等）
		 */
		Page<SunDdOrder> findOrderSendByOpenId(List<PropertyFilter> filters,
				Pageable pageable);
		
		/**
		 *@name   分页加载收到贺卡信息
		 *@description 相关说明
		 *@time    创建时间:2016年6月26日下午4:32:35
		 *@param filters
		 *@param pageable
		 *@return
		 *@author   高艳花
		 * @throws Exception 
		 *@history 修订历史（历次修订内容、修订人、修订时间等）
		 */
		com.ybt.common.util.Page<SunDdOrder> findOrderSendByOpenId(String openid,
				com.ybt.common.util.Page<SunDdOrder> page) throws Exception;
		/**
		 *@name   分页加载收到贺卡信息
		 *@description 相关说明
		 *@time    创建时间:2016年6月26日下午4:32:35
		 *@param filters
		 *@param pageable
		 *@return
		 *@author   高艳花
		 * @throws Exception 
		 *@history 修订历史（历次修订内容、修订人、修订时间等）
		 */
		com.ybt.common.util.Page<SunDdOrder> findOrderbyerByOpenId(String openid,
				com.ybt.common.util.Page<SunDdOrder> page) throws Exception;

		/**
		 *@name   jpa qurey navice分页加载 我的所有订单
		 *@description 相关说明
		 *@time    创建时间:2016年6月26日下午4:32:35
		 *@param filters
		 *@param pageable
		 *@return
		 *@author   高艳花
		 * @throws Exception 
		 *@history 修订历史（历次修订内容、修订人、修订时间等）
		 **/
		public com.ybt.common.util.Page<SunDdOrder> getMyOrderList(String openid,com.ybt.common.util.Page<SunDdOrder> page);
		 
		/**
		 *@name   jpa qurey navice分页加载  通过状态查询 我的订单
		 *@description 相关说明
		 *@time    创建时间:2016年6月26日下午4:32:35
		 *@param filters
		 *@param pageable
		 *@return
		 *@author   高艳花
		 * @throws Exception 
		 *@history 修订历史（历次修订内容、修订人、修订时间等）
		 **/
		public com.ybt.common.util.Page<SunDdOrder> getMyOrderListByState(String openid,String state,com.ybt.common.util.Page<SunDdOrder> page);
		 

		
	/**
	 * 卖家全部订单
	 *//*
	public List<SunDdOrder> getOrderBySellerId(String sellerId);

	*//**
	 * 买家全部订单
	 *//*
	public List<SunDdOrder> getOrderByBuyerId(String buyerId);

	*//**
	 * 卖家某状态订单
	 *//*
	public List<SunDdOrder> getStatusOrderBySellerId(String sellerId, int s);

	*//**
	 * 买家某状态订单
	 *//*
	public List<SunDdOrder> getStatusOrderByBuyerId(String buyerId, int s);

	*//**
	 * 买家订单查询
	 *//*
	public List<SunDdOrder> queryBuyerOrder();

	*//**
	 * 卖家订单查询
	 *//*
    public List<SunDdOrder> querySellerOrder();

	*//**
	 * @name 保存修改订单状态
	 * @description 相关说明
	 * @time 创建时间:2016年2月23日下午3:16:34
	 * @param order 订单
	 * @param accountId  操作员ID
	 * @param bid  当前商户ID
	 * @return
	 * @author 高艳花
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 *//*
	SunDdOrder saveOrder(SunDdOrder order, String accountId,String bid,String processId);

	*//**
	 * @name 商户修改订单价格
	 * @description 相关说明
	 * @time 创建时间:2016年2月23日下午3:18:31
	 * @param id
	 * @param payTotalPrice
	 * @return
	 * @author 高艳花
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 *//*
	SunDdOrder updateOrderPrize(String id, Double payTotalPrice);

	*//**
	 *@name    获取流程
	 *@description 相关说明
	 *@time    创建时间:2016年2月26日下午5:24:05
	 *@param payment
	 *@param delivery
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 *//*
	List<SunDdStatus> getLcxx(int payment, int delivery);

	*//**
	 *@name    取消订单
	 *@description 相关说明
	 *@time    创建时间:2016年2月23日下午3:16:34
	 *@param order 订单
	 *@param accountId 操作员ID
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 *//*
	SunDdOrder cancelOrder(String id, String accountId, String bid);
	*//**
	 *@name    删除订单
	 *@description 相关说明
	 *@time    创建时间:2016年2月23日下午3:16:34
	 *@param order 订单
	 *@param accountId 操作员ID
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 *//*
	SunDdOrder delOrder(String id, String accountId, String bid);
	
	*//**
	 *@name    支付订单后修改状态，保存支付记录
	 *@description 相关说明
	 *@time    创建时间:2016年2月23日下午3:16:34
	 *@param order 订单
	 *@param accountId 操作员ID
	 *@param bid 操作修改状态的商户id
	 *@param payType ping++支付方式
	 *@param pingppId ping++支付id
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 *//*
	SunDdOrder payOrderOnLine(String id, String accountId, String bid,
			String payType, String pingppId);
	
	*//**
	 * 
	 *@description  ping++推送事件通过charge对象修改支付流程状态
	 *@time    创建时间:2016年3月14日上午10:50:43
	 *@param charge
	 *@author   zhz
	 *//*
	SunDdOrder payPingPP(Charge charge);

	*//**
	 *@description 更新库存数量
	 *@time    创建时间:2016年3月14日上午10:50:04
	 *@author   zhz
	 *//*
	boolean updateStockNum(SunDdOrder order, String string);

	*//**
	 *@description 交易流程--账户变更
	 *@time    创建时间:2016年3月14日上午10:50:04
	 *@author   zhz
	 *//*
	public int saveTradeForOrder(SunDdOrder order, String payType,
			String pingppId, String string);
	
	*//**
	 *@description 订单成功，交易额划入卖家可提现金额
	 *@time    创建时间:2016年3月14日下午2:01:38
	 *@author   zhz
	 *//*
	void orderSuccess(SunDdOrder order);
	
	 *//**
	 * 根据订单号orderNo查询
	 *//*
	 public SunDdOrder getOrderByOrderNo(String orderNo);
	 *//**
	 * 获取买家补贴订单
	 *//*
	 public List<SunDdOrder> getSubsidyOrderByBuyerId(String buyerId);
	 *//**
	 * 获取卖家家补贴订单
	 *//*
	 public List<SunDdOrder> getSubsidyOrderBySellerId(String sellerId);

	*//**
	 *@name    获取订单更新提示信息
	 *@description 相关说明
	 *@time    创建时间:2016年3月25日下午1:40:59
	 *@param accountId
	 *@param bid
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 *//*
	Object[] getNewOrderMessage(String bid);

	*//**
	 *@name   更新消息提示时间
	 *@description 相关说明
	 *@time    创建时间:2016年3月25日下午5:29:48
	 *@param bid
	 *@param type 0购买记录，1销售记录
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 *//*
	void setNewTimeReminder(String bid, int type);

	*//**
	 *@name    修改订单中商品的库存的销售量
	 *@description 相关说明
	 *@time    创建时间:2016年3月9日下午4:08:38
	 *@param order 订单
	 *@param type -下单时减库存  +取消订单时增加库存
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 *//*
	boolean updateStockSalesNum(SunDdOrder order);
	
	*/
}
