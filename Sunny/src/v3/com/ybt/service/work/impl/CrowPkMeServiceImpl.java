package com.ybt.service.work.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.CrowPkMeDao;
import com.ybt.model.work.CrowPkMe;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.CrowPkMeService;

@Component
public class CrowPkMeServiceImpl extends BaseServiceImpl<CrowPkMe,String> implements CrowPkMeService{
	
	
	@Autowired
	private CrowPkMeDao crowPkMeDao;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	public HashMap constant;
	
	public BaseDao<CrowPkMe, String> getDao() {
		// TODO Auto-generated method stub
		return crowPkMeDao;
	}

	@Override
	public List<CrowPkMe> findByUserId(String userId) {
		// TODO Auto-generated method stub
		return crowPkMeDao.findByUserId(userId);
	}

	@Override
	public Result<CrowPkMe> createFristPkme(String userId ,String orderId,Double money) {
		// TODO Auto-generated method stub
		CrowPkMe pkMe = null ; 
		List<CrowPkMe> pkmeList = crowPkMeDao.findByUserIdAndPkTypeAndPkLevel(userId, 1,1);
		//List<CrowPkMe> pkmeList = crowPkMeDao.findByUserId(userId);
		for (CrowPkMe crowPkMe : pkmeList) {
			pkMe = crowPkMe;
			if(pkMe.getPkStatus()==1){
				return new Result<>("已开启挑战",null);
			}else{
				Date d = new Date();
				Calendar calendar = Calendar.getInstance();  
				calendar.add(Calendar.DATE,1);
		        calendar.set(Calendar.HOUR_OF_DAY, 0); 
		        calendar.set(Calendar.MINUTE, 0);  
		        calendar.set(Calendar.SECOND, 0);  
		        Date start=calendar.getTime(); 
		        calendar.add(Calendar.DATE,4);
		        calendar.set(Calendar.HOUR_OF_DAY, 23);  
		        calendar.set(Calendar.MINUTE, 59);  
		        calendar.set(Calendar.SECOND, 59);  
		        Date end=calendar.getTime();  
		        pkMe.setStartDate(start);
		        pkMe.setEndDate(end);
		        pkMe.setOrderId(orderId);
		        
				pkMe.setCreateTime(d);
				pkMe.setUpdateTime(d);
				pkMe.setDeleted(0);
				pkMe.setCompleteDay(0);
				pkMe.setDay(5);
				pkMe.setMoney(BigDecimal.valueOf(money));
				pkMe.setPkLevel(1);
				pkMe.setPkType(1);
				pkMe.setPkStatus(1);
				pkMe.setPkResult(2);
				pkMe.setUserId(userId);
				break;
			}
		}
		if(pkMe ==null){
			pkMe = new CrowPkMe();
			Date d = new Date();
			Calendar calendar = Calendar.getInstance();  
			calendar.add(Calendar.DATE,1);
	        calendar.set(Calendar.HOUR_OF_DAY, 0); 
	        calendar.set(Calendar.MINUTE, 0);  
	        calendar.set(Calendar.SECOND, 0);  
	        Date start=calendar.getTime(); 
	        calendar.add(Calendar.DATE,4);
	        calendar.set(Calendar.HOUR_OF_DAY, 23);  
	        calendar.set(Calendar.MINUTE, 59);  
	        calendar.set(Calendar.SECOND, 59);  
	        Date end=calendar.getTime();  
	        pkMe.setStartDate(start);
	        pkMe.setEndDate(end);
	        pkMe.setOrderId(orderId);
	        
			pkMe.setCreateTime(d);
			pkMe.setUpdateTime(d);
			pkMe.setDeleted(0);
			pkMe.setCompleteDay(0);
			pkMe.setDay(5);
			pkMe.setMoney(BigDecimal.valueOf(money));
			pkMe.setPkLevel(1);
			pkMe.setPkType(1);
			pkMe.setPkStatus(1);
			pkMe.setPkResult(2);
			pkMe.setUserId(userId);
		}
		crowPkMeDao.save(pkMe);
		
		return new Result<>("",pkMe);
	}

	@Override
	public Result<CrowPkMe> createSecondPkme(String userId, String orderId,Double money) {
		// TODO Auto-generated method stub
				CrowPkMe pkMe = null ; 
				List<CrowPkMe> pkmeList = crowPkMeDao.findByUserIdAndPkTypeAndPkLevel(userId, 1,2);
				//List<CrowPkMe> pkmeList = crowPkMeDao.findByUserId(userId);
				for (CrowPkMe crowPkMe : pkmeList) {
					pkMe = crowPkMe;
					if(pkMe.getPkStatus()==1){
						return new Result<>("已开启挑战",null);
					}else{
						Date d = new Date();
						Calendar calendar = Calendar.getInstance();  
						calendar.add(Calendar.DATE,1);
				        calendar.set(Calendar.HOUR_OF_DAY, 0); 
				        calendar.set(Calendar.MINUTE, 0);  
				        calendar.set(Calendar.SECOND, 0);  
				        Date start=calendar.getTime(); 
				        calendar.add(Calendar.DATE,20);
				        calendar.set(Calendar.HOUR_OF_DAY, 23);  
				        calendar.set(Calendar.MINUTE, 59);  
				        calendar.set(Calendar.SECOND, 59);  
				        Date end=calendar.getTime();  
				        pkMe.setStartDate(start);
				        pkMe.setEndDate(end);
				        pkMe.setOrderId(orderId);
				        
						pkMe.setCreateTime(d);
						pkMe.setUpdateTime(d);
						pkMe.setDeleted(0);
						pkMe.setCompleteDay(0);
						pkMe.setDay(21);
						pkMe.setMoney(BigDecimal.valueOf(21));
						pkMe.setPkLevel(2);
						pkMe.setPkType(1);
						pkMe.setPkStatus(1);
						pkMe.setPkResult(2);
						pkMe.setUserId(userId);
						break;
					}
				}
				if(pkMe ==null){
					pkMe = new CrowPkMe();
					Date d = new Date();
					Calendar calendar = Calendar.getInstance();  
					calendar.add(Calendar.DATE,1);
			        calendar.set(Calendar.HOUR_OF_DAY, 0); 
			        calendar.set(Calendar.MINUTE, 0);  
			        calendar.set(Calendar.SECOND, 0);  
			        Date start=calendar.getTime(); 
			        calendar.add(Calendar.DATE,20);
			        calendar.set(Calendar.HOUR_OF_DAY, 23);  
			        calendar.set(Calendar.MINUTE, 59);  
			        calendar.set(Calendar.SECOND, 59);  
			        Date end=calendar.getTime();  
			        pkMe.setStartDate(start);
			        pkMe.setEndDate(end);
			        pkMe.setOrderId(orderId);
			        
					pkMe.setCreateTime(d);
					pkMe.setUpdateTime(d);
					pkMe.setDeleted(0);
					pkMe.setCompleteDay(0);
					pkMe.setDay(21);
					pkMe.setMoney(BigDecimal.valueOf(21));
					pkMe.setPkLevel(2);
					pkMe.setPkType(1);
					pkMe.setPkStatus(1);
					pkMe.setPkResult(2);
					pkMe.setUserId(userId);
				}
				crowPkMeDao.save(pkMe);
				
				return new Result<>("",pkMe);
	}

	@Override
	public CrowPkMe findByUserIdAndPkTypeAndPkLevelAndPkStatus(String userId, int pktype, int pklevel,
			int status) {
		// TODO Auto-generated method stub
		List<CrowPkMe>  list = crowPkMeDao.findByUserIdAndPkTypeAndPkLevelAndPkStatus(userId, pktype, pklevel, status);
		for (CrowPkMe crowPkMe : list) {
			return crowPkMe;
		}
		return null;
	}

	@Override
	public CrowPkMe findByUserIdAndPkTypeAndPkStatus(String userId, int pktype, int status) {
		// TODO Auto-generated method stub
				List<CrowPkMe>  list = crowPkMeDao.findByUserIdAndPkTypeAndPkStatus(userId, pktype,  status);
				for (CrowPkMe crowPkMe : list) {
					return crowPkMe;
				}
				return null;
	}

	@Override
	public List<CrowPkMe> findByAllUserAndPkTypeAndPkStatus(int pktype, int status, int top) {
		// TODO Auto-generated method stub
		return crowPkMeDao.findByAllUserAndPkTypeAndPkStatus(pktype, status, top);
	}

	@Override
	public CrowPkMe findByUserIdAndPkTypeAndPkLevel(String userId, int pktype, int pklevel) {
		// TODO Auto-generated method stub
		List<CrowPkMe>  list = crowPkMeDao.findByUserIdAndPkTypeAndPkLevel(userId, pktype, pklevel);
		for (CrowPkMe crowPkMe : list) {
			return crowPkMe;
		}
		return null;
	}

}
