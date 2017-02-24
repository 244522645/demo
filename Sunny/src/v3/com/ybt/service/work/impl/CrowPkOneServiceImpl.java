package com.ybt.service.work.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wechat.util.WXUtil;

import com.ybt.common.uitl.SendWeixinMessage;
import com.ybt.common.util.DateUtil;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.CrowPkOneDao;
import com.ybt.model.work.CrowPkMe;
import com.ybt.model.work.CrowPkOne;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.CrowPkMeService;
import com.ybt.service.work.CrowPkOneService;

@Service
public class CrowPkOneServiceImpl extends BaseServiceImpl<CrowPkOne, String> implements CrowPkOneService {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired(required = false)
	private CrowPkOneDao crowPkOneDao;
	@Autowired
	private  CrowPkMeService crowPkMeService;
	@SuppressWarnings("rawtypes")
	@Autowired
	public HashMap constant;
	
	@Override
	public BaseDao<CrowPkOne, String> getDao() {
		return crowPkOneDao;
	}

	@Override
	public List<CrowPkOne> findByuserid(String userid) {

		return crowPkOneDao.findByuserid(userid);
	}

	@Override
	public List<CrowPkOne> findCrowUserWin(String userid) {
		return crowPkOneDao.findCrowUserWin(userid);
	}
	
	@Override
	public List<CrowPkOne> findCrowUserLose(String userid) {
		return crowPkOneDao.findCrowUserlose(userid);
	}

	@Override
	public List<CrowPkOne> findCrowPkMeListByPkTypeAndPkStatusAndTop(int pktype, int status, int top) {
		// TODO Auto-generated method stub
		return crowPkOneDao.findCrowPkMeListByPkTypeAndPkStatusAndTop(pktype, status, top);
	}

	@Override
	public List<CrowPkOne> isFirstAcceptChallenge(String userId,
			String otherUserId) {
		// TODO Auto-generated method stub
		return crowPkOneDao.isFirstAcceptChallenge(userId, otherUserId);
	}

	@Override
	public String creatBothrecordsAndSendMessage(SunWechatUser yingzhan,
			SunWechatUser leizhu ,String orderNo) {
		try {
			//生成挑战的： 发起者和接受者记录
			//1.接受挑战者记录
			CrowPkOne cpo = new CrowPkOne();
			cpo.setCreateTime(new Date());
			cpo.setDay(5);
			cpo.setDeleted(0);
			cpo.setIs_rec("0"); // 是否接受挑战者  (0:是，1：不是)
			cpo.setMoney(new BigDecimal("5.00"));
			cpo.setOtherUserPkCompleteDay(0);
			cpo.setOtherUserPkStatus(2); //未开始
			cpo.setPkResult(5);  // 挑战结果 1.发起人win 2.应战win 3.双赢 4.双输 5.进行中
			cpo.setPkStatus(0);  // 挑战状态 0进行中 2是挑战结束
			cpo.setPkType(1);  // 1v1
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
	        cpo.setStartDate(start);
	        cpo.setEndDate(end);
			cpo.setTouser(leizhu);
			cpo.setUser(yingzhan);
			cpo.setUserPkCompleteDay(0);
			cpo.setUserPkStatus(2); // 用户挑战结果  0代表失败 1代表成功 2代表挑战中 3 未开始
			crowPkOneDao.save(cpo);
			//查看是否开启个人模式
			
			List<CrowPkMe> mePkList = crowPkMeService.findByUserId(yingzhan.getId());
			if(mePkList.isEmpty()){
				crowPkMeService.createFristPkme(yingzhan.getId(), orderNo,0.00);
			}
			//2.发起挑战者记录
			CrowPkOne other = new CrowPkOne();
			other.setCreateTime(new Date());
			other.setDay(5);
			other.setDeleted(0);
			other.setIs_rec("1");  // 是否接受挑战者  (0:是，1：不是)
			other.setMoney(new BigDecimal("5.00"));
			other.setOtherUserPkCompleteDay(0);
			other.setOtherUserPkStatus(2); //未开始
			other.setPkResult(5);  // 挑战结果 1.发起人win 2.应战win 3.双赢 4.双输 5.进行中
			other.setPkStatus(0);  // 挑战状态 0进行中 2是挑战结束
			other.setPkType(1);  // 1v1
			other.setStartDate(start);
			other.setEndDate(end);
			other.setTouser(yingzhan);
			other.setUser(leizhu);
			other.setUserPkCompleteDay(0);
			other.setUserPkStatus(2); // 用户挑战结果  0代表失败 1代表成功 2代表挑战中 3 未开始
			crowPkOneDao.save(other);
			
			System.out.println("=========================accept记录所有信息之后============================");
			List<CrowPkMe> mePk2List = crowPkMeService.findByUserId(leizhu.getId());
			if(mePk2List.isEmpty()){
				crowPkMeService.createFristPkme(leizhu.getId(), orderNo,0.00);
			}
			SendWeixinMessage.sendMessage(
					"通知",
					"恭喜您，应战成功！",
					"应战成功", DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm"), "查看对战详情",
					WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/crow/pkone/show?id="+cpo.getId(),  false), yingzhan.getId());
			SendWeixinMessage.sendMessage(
					"通知",
					"您在与“"+yingzhan.getWechatNickname()+"”的对战将于明日开启，请按时打卡。",
					"挑战开启", DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm"), "查看对战详情",
					WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/crow/pkone/show?id="+other.getId(),  false),
					leizhu.getId()
					);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("异常信息:",e);
			return "fail";
		}
		return "success";
	}

}
