package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.util.Page;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunCardTrade;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunLetter;
import com.ybt.service.base.IBaseService;

@Component
public interface SunCardTradeService extends IBaseService<SunCardTrade, String> {
	
	
	/**  
	 * 保存明细
	 * @param SunDdOrder
	 * @param card
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月19日 下午2:33:56 
	 */
	public boolean saveOrderCardTrade(SunDdOrder order,String card );
	/**  
	 * 保存明细
	 * @param letterid
	 * @param card
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月19日 下午2:33:56 
	 */
	public boolean saveLetterCardTrade(SunLetter letterid,SunCard card );
	
	
	/**
	 *  查询 阳光卡送出 纪录
	 * */
	public Page<SunCardTrade> getSunCardTradeListByUserId(String userId, Page<SunCardTrade> page);
	
	/**
	 *  查询 阳光卡收到 纪录
	 * */
	public Page<SunCardTrade> getSunCardTradeListByToUserId(String toUserId, Page<SunCardTrade> page);
	
	/**
	 *  查询 阳光卡使用记录(送出和收到的总记录)
	 * */
	public List<SunCardTrade> getRecvAndSendSunCardTradeList(String userId);
	
}
