package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.model.work.CrowBill;
import com.ybt.model.work.CrowPunch;
import com.ybt.service.base.IBaseService;

/**   
 * 景点  管理
 * @author AndyBao  
 * @version 4.0, 2016年12月12日 下午2:22:29   
 */   
@Component
public interface CrowBillService extends IBaseService<CrowBill, String> {
	
	public List<CrowBill> findByUserIdAndDate(String userId , String date);
	/*
	 * 通过orderId查询当前账户
	 */
	public CrowBill findCrowBillByOrderId (String orderid);
	
}
