package com.ybt.dao.work;

import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.WxMessageText;

@Component
public interface MessageTextDao extends BaseDao<WxMessageText, String> {

}
