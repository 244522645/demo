package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunGoodsOrder;
import com.ybt.service.base.IBaseService;

@Component
public interface GoodsOrderService extends IBaseService<SunGoodsOrder, String> {
	
	/**  
	 * 生成订单-保存订单信息
	 * @param order
	 * @param openId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年12月12日 下午2:01:18 
	 */
	public SunDdOrder createOrder(SunGoodsOrder order, String openId);

	/**  
	 * 完善订单 - 收货信息
	 * @param order
	 * @param openId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年12月12日 下午2:01:18 
	 */
	public SunDdOrder updateOrder(SunGoodsOrder order, String openId);
	
	/**  
	 * 我的订单列表
	 * @param order
	 * @param openId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年12月12日 下午2:01:18 
	 */
	public List<SunDdOrder> getOrderListByUserId(String openId);
	
	/**  
	 * 我的订单详情
	 * @param order
	 * @param openId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年12月12日 下午2:01:18 
	 */
	public SunDdOrder getOrderDetailsByUserId(String openId);
}
