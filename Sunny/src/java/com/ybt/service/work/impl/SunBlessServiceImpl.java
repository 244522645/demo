package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.util.Page;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunBlessDao;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyImages;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.MemberRelationService;
import com.ybt.service.work.SunBlessService;

@Component("sunBlessService")
public class SunBlessServiceImpl extends BaseServiceImpl<SunBless, String> implements SunBlessService{
	
	@Autowired
	private SunBlessDao blessDao;
	@Autowired
	private MemberRelationService relationService;
	@Override
	public BaseDao<SunBless, String> getDao() {
		return blessDao;
	}


	@Override
	public Result<SunBless> saveBless(SunWechatUser user, String sender, String receiver, String bless, SunZyImages images,
			SunZyPhoto photo, SunDdOrder order) {
		// TODO Auto-generated method stub
		SunBless sbless =new SunBless();
		if(order!=null){
			SunBless sblesss=findByOrder(order.getId());
			if( sblesss!=null)
				sbless =sblesss;
		}
		sbless.setBless(bless);
		sbless.setCardImage(images);
		sbless.setPhoto(photo);
		sbless.setCreateTime(new Date());
		sbless.setIsread(0);
		sbless.setOrder(order);
		sbless.setReceiver(receiver);
		sbless.setSender(sender);
		sbless.setStatus(0);
		sbless.setUserId(user);
		this.save(sbless);
		
		return new Result<SunBless>("",sbless);
	}

	@Override
	public SunBless findByOrder(String orderId) {
		List<SunBless> list=blessDao.findByOrder(orderId);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}


	@Override
	public Page<SunBless> getMyAllBlessList(String openid, Page<SunBless> page) {
		page.setResult(blessDao.getMyAllBlessList(openid, page.getPageSize()*(page.getPageNo()-1), page.getPageSize()));
		return page;
	}


	@Override
	public List<SunBless> getSunBlessByValidAndUserId(String userid) {
		return blessDao.findSunBlessByValidAndUserId(userid);
	}


	public Page<SunBless> findMyReceivedBlessList(String openid,Page<SunBless> page) {
		page.setResult(blessDao.findMyReceivedBlessList(openid, page.getPageSize()*(page.getPageNo()-1), page.getPageSize()));
		return page;
	}


	public Page<SunBless> findMySendBlessList(String openid, Page<SunBless> page) {
		page.setResult(blessDao.findMySendBlessList(openid, page.getPageSize()*(page.getPageNo()-1), page.getPageSize()));
		return page;
	}


	public Page<SunBless> findMyNoSendBlessList(String openid,Page<SunBless> page) {
		page.setResult(blessDao.findMyNoSendBlessList(openid, page.getPageSize()*(page.getPageNo()-1), page.getPageSize()));
		return page;
	}


	public int findMyReceivedBlessCount(String mid) {
		return blessDao.findMyReceivedBlessCount(mid).intValue();
	}


	public int findMySendBlessCount(String mid) {
		return blessDao.findMySendBlessCount(mid).intValue();
	}


	public int findMyNoSendBlessCount(String mid) {
		return blessDao.findMyNoSendBlessCount(mid).intValue();
	}



	@Override
	public List<SunBless> getAllBlessListByRec(int i, int m) {
		return blessDao.getAllBlessListByRec(i, m);
	}


	@Override
	public SunBless getSunBlessByQrcodeId(String qrcodeId) {
		return blessDao.findSunBlessByQrcodeId(qrcodeId);
	}

}
