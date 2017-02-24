package com.ybt.service.work;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunWechatUser;

/**   
 * 预订 管理
 * @author AndyBao  
 * @version 4.0, 2016年12月12日 下午2:22:29   
 */   
@Component("reserveBlessService")
public interface ReserveBlessService extends SunBlessService{
	
	
	public SunDdOrder  saveOrder(SunWechatUser  w,
			                  String relationId,
			                  String relationName,
			                  String relationRelation,
			                  String relationPhone,
			                  String relationBirth,
			                  String message,
			                  Double price,
			                  String sunPayType);
	
	/**  
	 *  保存预订贺卡
	 * @param user
	 * @param sender
	 * @param relation
	 * @param bless
	 * @param citys
	 * @param order
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2017年1月4日 下午3:12:17 
	 */
	public Result<SunBless> saveReserveBless(SunWechatUser user,String sender,String relation,String bless,String[] citys,SunDdOrder order);

}
