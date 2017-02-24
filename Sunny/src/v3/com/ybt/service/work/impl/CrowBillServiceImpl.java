package com.ybt.service.work.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.CrowBillDao;
import com.ybt.model.work.CrowBill;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.CrowBillService;

@Component
public class CrowBillServiceImpl extends BaseServiceImpl<CrowBill,String> implements CrowBillService{
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private CrowBillDao crowBillDao;
	
	
	public BaseDao<CrowBill, String> getDao() {
		return crowBillDao;
	}


	@Override
	public List<CrowBill> findByUserIdAndDate(String userId, String date) {
		List<CrowBill> list = null;
		try{
			list = crowBillDao.findByUserIdAndDate(userId, date);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("异常信息:",e);
		}
		return list;
	}


	@Override
	public CrowBill findCrowBillByOrderId(String OrderId) {
		return crowBillDao.findByOrderId(OrderId);
	}

}
