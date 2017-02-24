package com.ybt.service.work;

import org.springframework.stereotype.Service;

import com.ybt.common.bean.Result;
import com.ybt.model.work.CrowUserTree;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.IBaseService;
/**
 * 用户树
 */
@Service
public interface CrowUserTreeService extends IBaseService<CrowUserTree, String> {
	
	/*
	 * 保存关心  - 感召人+1
	 */
	public Result<CrowUserTree> saveUserTree (String userId, SunWechatUser v);
}
