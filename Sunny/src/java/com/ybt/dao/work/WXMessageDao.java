package com.ybt.dao.work;

import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunWXMessage;

@Component
public interface WXMessageDao extends BaseDao<SunWXMessage, String> {

}
