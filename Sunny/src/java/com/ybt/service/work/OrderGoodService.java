package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunDdGood;
import com.ybt.service.base.IBaseService;

@Component
public interface OrderGoodService extends IBaseService<SunDdGood, String> {

	
	 /**
	 * 订单商品
	 */
	 public List<SunDdGood> getOrderGoodByOrderId(String orderId);


	/**
	 *@name    根据商品Id查询一个订单多个商品
	 *@description 相关说明
	 *@time    创建时间:2016年4月15日下午5:03:54
	 *@param sqlWhere
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	List<SunDdGood> getOrderGoodsByGoodsId(String[] goodIdList);
	

}
