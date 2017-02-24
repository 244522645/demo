package com.ybt.dao.work;

import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.WxMessage;

@Component
public interface MessageDao extends BaseDao<WxMessage, String> {

}
