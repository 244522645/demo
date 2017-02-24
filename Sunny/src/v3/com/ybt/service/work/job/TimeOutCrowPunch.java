package com.ybt.service.work.job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.util.DateUtil;
import com.ybt.service.work.CrowPunchComputeService;

@Component("timeOutCrowPunch")
public class TimeOutCrowPunch {
	
	@Autowired
	private CrowPunchComputeService crowPunchComputeService;
	/**
	 * 处理微信微信打卡计算
	 * 3.0 数据库保存
	 * */
	public void TaskRun(){
		System.out.println(":"+DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
		crowPunchComputeService.punchCompute();
		
	}
}
