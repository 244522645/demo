package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.CrowUserTreeDao;
import com.ybt.dao.work.IWechatDao;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.CrowUserTree;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.CrowUserInfoService;
import com.ybt.service.work.CrowUserTreeService;

@Component
public class CrowUserTreeServiceImpl extends BaseServiceImpl<CrowUserTree,String> implements CrowUserTreeService{
	
	@Autowired
	private CrowUserTreeDao crowUserTreeDao;
	@Autowired
	private CrowUserInfoService crowUserInfoService;
	@Autowired
	private IWechatDao iWechatDao;
	
	public BaseDao<CrowUserTree, String> getDao() {
		return crowUserTreeDao;
	}

	@Override
	public Result<CrowUserTree> saveUserTree(String userId, SunWechatUser v) {
		
		List<CrowUserTree> mylist  = crowUserTreeDao.findCrowUserTreeUserId(v.getId());
		if(!mylist.isEmpty()){
			return new Result<CrowUserTree>("已加入",null);
		}
		
		//增加感召
		CrowUserInfo userInfo = crowUserInfoService.findByUserID(userId);
		if(userInfo!=null){
			userInfo.setImpel(userInfo.getImpel()+1);
			userInfo.setUpdateTime(new Date());
			crowUserInfoService.save(userInfo);
		}
		int index=0;
		CrowUserTree t = new CrowUserTree();
		t.setAncestor(v);
		t.setDescendant(v);
		t.setCreateTime(new Date());
		t.setDepth(index);
		crowUserTreeDao.save(t);
		
		if(userId!=null && !"".equals(userId)){
			List<SunWechatUser> list=(List<SunWechatUser>) iWechatDao.getPrarentListByUserId(userId);
			for (int i = list.size()-1; i >= 0; i--) {
				index++;
				  t = new CrowUserTree();
					t.setAncestor(list.get(i));
					t.setDescendant(v);
					t.setCreateTime(new Date());
					t.setDepth(index);
					crowUserTreeDao.save(t);
			}
		}
		
		return new Result<CrowUserTree>("",t);
	}

}
