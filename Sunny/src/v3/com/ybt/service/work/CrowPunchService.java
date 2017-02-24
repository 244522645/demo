package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.CrowPunch;
import com.ybt.model.work.WxMessageImage;
import com.ybt.service.base.IBaseService;

/**   
 * 景点  管理
 * @author AndyBao  
 * @version 4.0, 2016年12月12日 下午2:22:29   
 */   
@Component
public interface CrowPunchService extends IBaseService<CrowPunch, String> {
	
	/**  
	 * @param userId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2017年2月9日 下午4:40:43 
	 */
	public List<CrowPunch> findByUserId(String userId);
	/**  
	 * 通过日期查新 打卡
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月18日 上午10:34:51 
	 */
	public  CrowPunch findByUserIdAndToday(String userId,String today);
	
	/**  
	 * 保存打卡
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月18日 上午10:34:51 
	 */
	public Result<CrowPunch> savePunch(String openId,WxMessageImage message);
	
	/**  
	 * 审核打卡
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月18日 上午10:34:51 
	 */
	public Result<CrowPunch> checkPunch(String pId,Boolean pass);
	

	/**
	 * 查看个人打卡
	 * @param cameristId  摄影师id
	 * @param startDate   开始时间
	 * @param endDate     结束时间
	 * @return
	 */
	public Result<List<String>> getCalendarPunch(String userId , String startDate, String endDate);

}
