package com.ybt.service.work;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import com.pingplusplus.model.Transfer;
import com.ybt.common.util.Page;
import com.ybt.model.work.SunZhAccount;
import com.ybt.model.work.SunZhDaybook;
import com.ybt.service.base.IBaseService;

@Component
public interface TradeAccountService extends IBaseService<SunZhAccount, String>{
	
	
	/**
	 *@description 闻鸡起"伍" 提现
	 *@time    创建时间:2016年3月17日上午9:16:24
	 *@param transfer
	 *@author   lhq
	 */
	public void wjqwTixian(String event);
	
	
	/**
	 *@description 提现--插入商户账户流水记录，b,芸备胎官方出账记录,c,减商户可提现金额，d,减 芸备胎总额
	 *@time    创建时间:2016年3月17日上午9:16:24
	 *@param bid
	 *@param transfer
	 *@param isYpay 是否芸备胎余额支付
	 *@author   zhz
	 */
	public void saveTransfer(String bid, Transfer transfer,boolean isYpay);
	/**
	 *@description 提现成功--插入商户账户流水记录，b,芸备胎官方出账记录,c,减商户可提现金额，d,减 芸备胎总额
	 *@time    创建时间:2016年3月17日上午9:16:24
	 *@param bid
	 *@param transfer
	 *@param isYpay 是否芸备胎余额支付
	 *@author   zhz
	 */
	void transferSuccess(Transfer transfer);

	/**
	 *@description 芸备胎提现
	 *@time    创建时间:2016年3月17日上午9:16:24
	 *@param record
	 *@param handerMoney 手续费
	 *@author   zhz
	 */
	public void ybtTixian(SunZhDaybook book, Double handerMoney);
	
	
	/**
	 * 查询流水
	 * @param page
	 */
	List<SunZhDaybook> queryDaybook(Page<T> page);

	/**
	 * 查询流水总数
	 * @param page
	 */
	int queryDaybookCount(Page<T> page);
	
}
