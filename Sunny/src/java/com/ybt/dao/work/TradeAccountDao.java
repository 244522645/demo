package com.ybt.dao.work;

import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunZhAccount;

@Component
public interface TradeAccountDao extends BaseDao<SunZhAccount, String> {
	
}
