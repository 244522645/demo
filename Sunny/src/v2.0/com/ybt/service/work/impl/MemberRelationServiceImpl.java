package com.ybt.service.work.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.MemberRelationBean;
import com.ybt.common.bean.Result;
import com.ybt.common.util.DateTimeHelper;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.MemberRelationDao;
import com.ybt.model.work.MemberRelation;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.MemberRelationService;

@Component
public class MemberRelationServiceImpl extends BaseServiceImpl<MemberRelation, String> implements MemberRelationService{
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private MemberRelationDao relationDao;
	@Override
	public BaseDao<MemberRelation, String> getDao() {
		return relationDao;
	}
	
	@Override
	public List<MemberRelationBean> findMemberRelationsById(String userId) {
		List<MemberRelationBean> listBean = new ArrayList<>();
		try{
			List<MemberRelation> list = relationDao.findByUserId(userId);
			for (MemberRelation mb : list) {
				listBean.add(new MemberRelationBean(mb));
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("异常信息:",e);
		}
		return listBean;
	}

	@Override
	public Result<MemberRelation> saveRelation(SunWechatUser user,String id,String name, String birthday,
			String mobileNo, String relation) {
		try{
			MemberRelation mb = new MemberRelation();
			if(!StringUtils.isEmpty(id)){
				mb = relationDao.findOne(id);
				mb.setName(name);
				mb.setBirthday(DateTimeHelper.strToDatetime(birthday, DateTimeHelper.DEFAULT_DATE_FORMATE));
				mb.setRelation(relation);
				mb.setMobileNo(mobileNo);
			}else{
				mb.setCreateTime(new Date());
				mb.setDeleted(0);
				mb.setName(name);
				mb.setBirthday(DateTimeHelper.strToDatetime(birthday, DateTimeHelper.DEFAULT_DATE_FORMATE));
				mb.setRelation(relation);
				mb.setMobileNo(mobileNo);
				mb.setUser(user);
			}
			MemberRelation save = relationDao.save(mb);
			if(null == save){
				return new Result<MemberRelation>("false",null);
			}
			return new Result<>("success",save);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("异常信息:",e);
		}
		return new Result<MemberRelation>("false", null);
	}

	@Override
	public MemberRelationBean findOneById(String id) {
		MemberRelation mb = relationDao.findOne(id);
		MemberRelationBean mbBean= new MemberRelationBean(mb);
		return mbBean;
	}

	@Override
	public String deleteRelation(String id) {
		MemberRelation mb = relationDao.findOne(id);
		if(null == mb){
			return "false";
		}
		mb.setDeleted(1);
		mb.setUpdateTime(new Date());
		this.save(mb);
		return "success";
	}



}
