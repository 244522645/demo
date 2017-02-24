package com.ybt.dao.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.WxToken;

@Component
public interface WxTokenDao extends BaseDao<WxToken, String> {
	public List<WxToken> findWxTokenByType(String type) ;
}
