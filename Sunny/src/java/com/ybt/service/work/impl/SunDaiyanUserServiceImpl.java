package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunDaiyanUserDao;
import com.ybt.model.work.SunDaiyan;
import com.ybt.model.work.SunDaiyanUser;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunDaiyanUserService;

@Component
public class SunDaiyanUserServiceImpl extends BaseServiceImpl<SunDaiyanUser, String> implements SunDaiyanUserService{
	
	@Autowired
	private SunDaiyanUserDao daiyanUserDao;
	@Override
	public BaseDao<SunDaiyanUser, String> getDao() {
		return daiyanUserDao;
	}
	@Override
	public List<SunDaiyanUser> getSunDaiyanUserListByDanyanId(String daiyanId) {
		// TODO Auto-generated method stub
		return daiyanUserDao.findSunDaiyanUserListByDanyanId(daiyanId);
	}
	@Override
	public List<SunDaiyanUser> getSunDaiyanUserListByUserId(String userId) {
		// TODO Auto-generated method stub
		return daiyanUserDao.findSunDaiyanUserListByUserId(userId);
	}

	@Override
	public SunDaiyanUser getSunDaiyanUserByDanyanIdAndUserId(String daiyanId, String userId) {
		SunDaiyanUser daiyan = null;
		List<SunDaiyanUser>  list= daiyanUserDao.findSunDaiyanUserByDanyanIdAndUserId(daiyanId, userId);
		if(list!=null&&!list.isEmpty()){
			daiyan =list.get(0);
			return daiyan;
		}
		return null;
	}
	@Override
	public Result<SunDaiyanUser> saveDaiyanUser(SunWechatUser user, SunDaiyan daiyan) {
		
		SunDaiyanUser du =  getSunDaiyanUserByDanyanIdAndUserId(daiyan.getId(), user.getId());
		if(du!=null)
			return new Result<SunDaiyanUser>("已加入",null);
		
		du = new SunDaiyanUser();
		du.setCreateTime(new Date());
		du.setDaiyan(daiyan);
		du.setUserId(user);
		du.setStatus(1);
		this.save(du);
		
		return new Result<SunDaiyanUser>("",du);
	}


}
