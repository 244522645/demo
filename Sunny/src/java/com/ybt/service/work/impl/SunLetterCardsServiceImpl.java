package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunCardDao;
import com.ybt.dao.work.SunCardTradeDao;
import com.ybt.dao.work.SunLetterCardsDao;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunCardTrade;
import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunLetterCards;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunCardTradeService;
import com.ybt.service.work.SunLetterCardsService;

@Component
public class SunLetterCardsServiceImpl extends BaseServiceImpl<SunLetterCards, String> implements SunLetterCardsService{
	
	@Autowired
	private SunLetterCardsDao letterCardsDao;
	
	@Autowired
	private SunCardDao cardDao;
	
	@Autowired
	private SunCardTradeDao cardTradeDao;
	
	
	@Autowired
	private SunCardTradeService cardTradeService;
	
	@Override
	public BaseDao<SunLetterCards, String> getDao() {
		return letterCardsDao;
	}

	@Override
	public boolean saveLetterCards(SunLetter letterid, String[] cards) {
		boolean f= false;
		for (String pid : cards) {
			SunCard p = cardDao.findOne(pid);
			if(p!=null){
				SunLetterCards lp= new SunLetterCards();
				lp.setLetterId(letterid);
				lp.setCard(p);
				lp.setCreateTime(new Date());
				letterCardsDao.save(lp);
				f=true;
				
				p.setStatus(5);
				p.setUpdateTime(new Date());
				cardDao.save(p);
				
				//保存明细
				cardTradeService.saveLetterCardTrade(letterid, p);
			}
		}
		return f;
	}

	@Override
	public List<SunLetterCards> findByLetterId(String letterId) {
		return letterCardsDao.findByLetterId(letterId);
	}

}
