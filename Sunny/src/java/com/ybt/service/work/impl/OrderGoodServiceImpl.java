package com.ybt.service.work.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.OrderGoodDao;
import com.ybt.model.work.SunDdGood;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.OrderGoodService;

@Component
@Transactional
public class OrderGoodServiceImpl extends BaseServiceImpl<SunDdGood, String>  implements OrderGoodService{

	@Autowired
	private OrderGoodDao orderGoodDao;

	@Override
	public BaseDao<SunDdGood, String> getDao() {
		return orderGoodDao;
	}

	/**
	 *@name    获取订单中的商品信息
	 *@description 相关说明
	 *@time    创建时间:2016年4月13日下午1:35:31
	 *@param orderId
	 *@return
	 * @author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Override
	public List<SunDdGood> getOrderGoodByOrderId(String orderId) {
		return orderGoodDao.getOrderGoodByOrderId(orderId);
	}

/* *//**
	 *@name    根据商品Id查询一个订单多个商品
	 *@description 相关说明
	 *@time    创建时间:2016年4月15日下午5:03:54
	 *@param sqlWhere
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Override
	public List<SunDdGood> getOrderGoodsByGoodsId(String[] goodIdList){
		@SuppressWarnings("unused")
		String sqlWhere="";
		List<SunDdGood> returnList=null;
		if(goodIdList!=null&&goodIdList.length>0){
			for(String id:goodIdList){
				sqlWhere+="'"+id+"',";
			}
			/*returnList = orderGoodDao.getOrderGoodsByGoodsId(sqlWhere.substring(0,sqlWhere.length()-1));*/
		}
		return returnList;
	}
}


