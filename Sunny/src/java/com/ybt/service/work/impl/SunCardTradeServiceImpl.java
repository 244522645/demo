package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.util.Page;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunCardTradeDao;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunCardTrade;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunCardTradeService;

@Component
public class SunCardTradeServiceImpl extends BaseServiceImpl<SunCardTrade, String> implements SunCardTradeService{
	
	@Autowired
	private SunCardTradeDao cardTradeDao;
	@Autowired
	private  SunCardService cardService;
	@Autowired
	private IWechatService userService;
	@Override
	public BaseDao<SunCardTrade, String> getDao() {
		return cardTradeDao;
	}
	@Override
	public boolean saveLetterCardTrade(SunLetter letterid, SunCard card) {
		
		if(letterid==null || card == null){
			return false;
		}
		
		//需要判断是否唯一
		SunCardTrade tarde = new SunCardTrade();
		List<SunCardTrade> list  = cardTradeDao.findByUserIdAndCardId(letterid.getUserId().getId(), card.getId());
		if(list!=null&&!list.isEmpty()){
			tarde = list.get(0);
		}else{
			tarde.setCreateTime(new Date());
		}
	
		tarde.setCard(card);
		tarde.setType(0);
		tarde.setStatus(0);
		tarde.setInout(1);
		tarde.setInoutType(1);
		tarde.setUserId(letterid.getUserId());
		tarde.setFromUserId(letterid.getUserId());
		tarde.setToUserId(letterid.getToUserId());
		tarde.setToUserName(letterid.getReceiver());
		tarde.setUpdateTime(new Date());
		cardTradeDao.save(tarde);
		
		tarde.setCard(card);
		tarde.setType(0);
		tarde.setStatus(0);
		tarde.setInout(0);
		tarde.setInoutType(1);
		tarde.setUserId(letterid.getToUserId());
		tarde.setFromUserId(letterid.getToUserId());
		tarde.setToUserId(letterid.getUserId());
		tarde.setToUserName(letterid.getSender());
		tarde.setUpdateTime(new Date());
		cardTradeDao.save(tarde);
		
		return true;
	}
	@Override
	public Page<SunCardTrade> getSunCardTradeListByUserId(String userId, Page<SunCardTrade> page) {
		page.setResult(cardTradeDao.findSunCardTradeListByUserId(userId, page.getPageSize()*(page.getPageNo()-1), page.getPageSize()));
		return page;
	}
	@Override
	public Page<SunCardTrade> getSunCardTradeListByToUserId(String toUserId, Page<SunCardTrade> page) {
		page.setResult(cardTradeDao.findSunCardTradeListByToUserId(toUserId, page.getPageSize()*(page.getPageNo()-1), page.getPageSize()));
		return page;
	}
	@Override
	public List<SunCardTrade> getRecvAndSendSunCardTradeList(String userId) {
		List<SunCardTrade> result = cardTradeDao.findRecvAndSendSunCardTradeList(userId);
		return result;
	}
	@Override
	public boolean saveOrderCardTrade(SunDdOrder order, String cardId) {
		try{
			if(order==null || cardId == null){
				return false;
			}
			SunCard card  = cardService.getOneCard(cardId);
			//需要判断是否唯一
			SunCardTrade tarde = new SunCardTrade();
			List<SunCardTrade> list  = cardTradeDao.findByUserIdAndCardId(order.getBuyerId(), card.getId());
			if(list!=null&&!list.isEmpty()){
				tarde = list.get(0);
			}else{
				tarde.setCreateTime(new Date());
			}
		
			SunWechatUser user = userService.findOne(order.getBuyerId());
			tarde.setCard(card);
			tarde.setType(1);
			tarde.setStatus(0);
			tarde.setInout(1);
			tarde.setInoutType(0);
			tarde.setUserId(user);
			tarde.setFromUserId(user);
			tarde.setToUserName(order.getSendeeName());
			tarde.setUpdateTime(new Date());
			cardTradeDao.save(tarde);
		}catch(Exception e){
			return false;
		}
		return true;
	}


}
