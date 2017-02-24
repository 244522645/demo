package com.ybt.dao.work;

import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.WxMessageVoice;

@Component
public interface MessageVoiceDao extends BaseDao<WxMessageVoice, String> {

}
