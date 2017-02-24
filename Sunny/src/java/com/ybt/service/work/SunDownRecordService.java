package com.ybt.service.work;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunDownRecord;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.IBaseService;


@Component
public interface SunDownRecordService extends IBaseService<SunDownRecord, String>{

	/**  
	 * 保存 下载纪录
	 * @param order
	 * @param user
	 * @param email
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月24日 上午8:47:08 
	 */
	public Result<SunDownRecord> saveSunDownRecord(String orderId,SunWechatUser user,String email);
	/**  
	 * 保存 下载纪录
	 * @param order
	 * @param user
	 * @param email
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月24日 上午8:47:08 
	 */
	public Result<SunDownRecord> saveSunDownRecord(String blessId,String photoId,SunWechatUser user,String email);
	
	/**  
	 * 查询一个订单 是否有下载纪录
	 * @param orderId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月24日 上午8:50:09 
	 */
	public  SunDownRecord getSunDownRecordByOrderId(String orderId);
}
