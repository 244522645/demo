package com.ybt.service.work.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.Wechat;
import com.ybt.common.util.DateUtil;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.CrowPunchDao;
import com.ybt.model.work.CrowPunch;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.model.work.WxMessageImage;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.CrowPunchComputeService;
import com.ybt.service.work.CrowPunchService;
import com.ybt.service.work.CrowUserInfoService;

import wechat.api.MessageAPI;
import wechat.bean.message.TextMessage;
import wechat.support.TokenManager;

@Component
public class CrowPunchServiceImpl extends BaseServiceImpl<CrowPunch,String> implements CrowPunchService{
	
	
	@Autowired
	private CrowPunchDao crowPunchDao;
	@Autowired
	private  CrowUserInfoService crowUserInfoService;
	@Autowired
	private CrowPunchComputeService crowPunchComputeService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	public HashMap constant;
	
	public BaseDao<CrowPunch, String> getDao() {
		// TODO Auto-generated method stub
		return crowPunchDao;
	}

	@Override
	public List<CrowPunch> findByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CrowPunch findByUserIdAndToday(String userId, String today) {
		// TODO Auto-generated method stub
		return crowPunchDao.findByUserIdAndToday(userId, today);
	}
	@Override
	public Result<CrowPunch> savePunch(String openId, WxMessageImage message) {
		Date d = new Date();
		String dateSrt= DateUtil.getDateFormat(d, "yyyy-MM-dd");
		CrowPunch punch = this.findByUserIdAndToday(openId, dateSrt);
		if(punch==null){
			punch = new CrowPunch();
		}
		punch.setCreateTime(d );
		punch.setUpdateTime(d);
		punch.setDeleted(0);
		punch.setImages(message.getImage());
		punch.setIscompute(0);
		punch.setIspass(2);
		punch.setPunchDate(dateSrt);
		punch.setUserId(openId);
		punch.setStatus(0);  //计算时 ，查ispass!=0
		crowPunchDao.save(punch);
		return new  Result<CrowPunch>("",punch);
	}

	@Override
	public Result<CrowPunch> checkPunch(String pId, Boolean pass) {
		// TODO Auto-generated method stub
		if(pId ==null){
			return new Result<CrowPunch>("pId is null",null);
		}
		CrowPunch crowPunch = crowPunchDao.findOne(pId) ;
		if(crowPunch ==null){
			return new Result<CrowPunch>("没有打卡纪录",null);
		}
		
		crowPunch.setStatus(1);
		crowPunch.setIspass(pass==true ? 1 : 0);
		crowPunch.setUpdateTime(new Date());
		crowPunchDao.save(crowPunch);
		
		if(pass){
			crowPunchComputeService.punchCompute(crowPunch);
			CrowUserInfo userInfo = crowUserInfoService.findByUserID(crowPunch.getUserId());
			StringBuffer tostr = new StringBuffer();
    		tostr.append("恭喜您！连续第"+userInfo.getContinuousDay()+"天早起打卡成功,打卡时间"+DateUtil.getDateFormat(crowPunch.getCreateTime(), "HH:mm")+"\n");
    		tostr.append("<a href='https://mp.weixin.qq.com/s/OJ37PA3-iJdaq90q8_KhaQ'>闻鸡起伍挑战规则</a>");
    		TextMessage message = new TextMessage(crowPunch.getUserId(), tostr.toString());
    		MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), message);                

		}else{
			crowPunchComputeService.punchCompute(crowPunch);
			StringBuffer tostr = new StringBuffer();
    		tostr.append("抱歉！今日打卡审核未通过。打卡提交时间为，"+DateUtil.getDateFormat(crowPunch.getCreateTime(), "HH:mm")+"\n");
    		tostr.append("<a href='https://mp.weixin.qq.com/s/OJ37PA3-iJdaq90q8_KhaQ'>闻鸡起伍挑战规则</a>");
    		TextMessage message = new TextMessage(crowPunch.getUserId(), tostr.toString());
    		MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), message);       
		}
		return new Result<CrowPunch>("",crowPunch);
	}

	@Override
	public Result<List<String>> getCalendarPunch(String userId, String startDate, String endDate) {
		//List<AppCamerist> acList = new ArrayList<AppCamerist>();
				List<String> dayList = new ArrayList<>();
				Set<String> set = new HashSet<>();
				String day = "";
				try{
					List<CrowPunch> photoList =  crowPunchDao.findCrowPunchCalendar(userId, startDate, endDate);
					for(CrowPunch ph :photoList){
						day = getDay(ph.getCreateTime().toString());
						set.add(day);
						dayList = new ArrayList<>(set);
					}
				}catch(Exception e){
					e.printStackTrace();
					return new Result<List<String>>("服务器异常", null);
				}
				return new Result<List<String>>("", dayList);
	}

	/**
	 * 将mysql的dateTime格式数据转换为 日
	 * @param date  例如：2016-12-01 07:40:46.0  ===》  01
	 * @return
	 */
	public String getDay(String date){
		String[] split = date.split("\\s");
		if(split.length > 0){
			String newDate = split[0];
			String day = newDate.substring(newDate.length() - 2);
			return day;
		}
		return "";
	}

}
