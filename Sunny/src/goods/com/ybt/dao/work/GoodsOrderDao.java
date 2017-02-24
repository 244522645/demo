package com.ybt.dao.work;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunGoodsOrder;

public interface GoodsOrderDao extends BaseDao<SunGoodsOrder, String> {
	 /**
	 * 生成订单号
	 */
	 public String getGoodsOrderNo();
}
