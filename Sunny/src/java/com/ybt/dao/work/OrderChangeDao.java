package com.ybt.dao.work;

import java.util.Date;
import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunDdChange;

public interface OrderChangeDao extends BaseDao<SunDdChange, String> {
	
	 public int getBuyOrderChange(String buyerId,Date searchTime);

	 public int getSellOrderChange(String sellerId,Date searchTime);

}
