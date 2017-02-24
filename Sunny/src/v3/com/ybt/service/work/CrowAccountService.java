package com.ybt.service.work;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ybt.common.bean.Result;
import com.ybt.model.work.CrowBill;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.service.base.IBaseService;
/**
 * 账户余额管理
 */
@Service
public interface CrowAccountService extends IBaseService<CrowUserInfo, String> {
	/*
	 * 查询当前账户余额
	 */
	public CrowUserInfo findCrowUserBalance (String userid);
	
	
	/*
	 * 查询所有账户余额
	 */
	public double findCrowAllUserBalance ();
	
	/*
	 * 通过orderId查询当前账户
	 */
	public CrowBill findCrowBillByOrderId (String orderid);
	/*
	 * 生成订单编号 
	 */
	public String getNewOrder ();

	/*
	 * 申请 支入支出。  充值 和 提现 
	 * 
	 * userid:我的id
	 * 
	 * otherUserId ： 发起挑战人ID
	 */
	public Result<CrowBill> applyBill (int type,Double money,Double deduct,String title,String userid,String otherUserId);
	/*
	 * 抵扣 和 提现 
	 */
	public Result<BigDecimal> minusBalance (String title,Double deduct,String userid);
	
	/*
	 * 返还 和 充值 
	 */
	public Result<BigDecimal> addBalance (String title,Double money,String userid);
}
