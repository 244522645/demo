package com.ybt.service.work.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.MD5Util;
import com.ybt.common.util.PropertyFilter;
import com.ybt.common.util.StringUtil;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunCardDao;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunTempRecord;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunTempRecordService;

@Component
public class SunCardServiceImpl extends BaseServiceImpl<SunCard, String> implements SunCardService {
    
	@Autowired
	private SunCardDao  sunCardDao;
	@Autowired
	private SunTempRecordService tempRecordService;
	@Resource
	public Map<String,String> constant;
	@Override
	public BaseDao<SunCard, String> getDao() {
		// TODO Auto-generated method stub
		return sunCardDao;
	}

	
	/**
	 *   分页
	 */
	@Override
	public Page<SunCard> findSunCardByProperty(final List<PropertyFilter> filters,Pageable pageable) {
		Page<SunCard>  page = sunCardDao.findAll(
		new Specification<SunCard>(){
			@SuppressWarnings("incomplete-switch")
			public Predicate toPredicate(Root<SunCard> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				 List<Predicate> list = new ArrayList<Predicate>();  
				 	for(PropertyFilter pf:filters){
				 		switch (pf.getMatchType()) {
						case EQ:
						    list.add(cb.equal(root.get(pf.getPropertyName()).as(pf.getPropertyClass()), pf.getMatchValue()));  
							break;
						case LIKE:
							list.add(cb.like(root.get(pf.getPropertyName()).as(String.class), "%"+pf.getMatchValue()+"%")); 
							break;
						}
				 	}
				    list.add(cb.equal(root.get("deleted").as(String.class), "0")); 
				    Predicate[] p = new Predicate[list.size()];  
				    return cb.and(list.toArray(p));  
			}}, (Pageable) new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),new Sort(Direction.DESC, new String[] {"createTime"})));
		return page;
	}
	
public com.ybt.common.util.Page<SunCard> findSunCardByNumber(String number,com.ybt.common.util.Page<SunCard> page) throws Exception{
	sunCardDao.findSunCardByNumber(number, page);
		return page;
	}

	
	
	@Override
	public SunCard getOneCard(String cardId) {
		// TODO Auto-generated method stub
		return sunCardDao.findOne(cardId);
	}
   /**
    * 修改
    */
	@Override
	public SunCard saveCard(SunCard cardId) {
		String id = cardId.getId();
		//ID为空则不修改
		if(id==null||"".equals(id)){
			return null;
		}
		SunCard scd =  sunCardDao.findOne(id);
		scd.setNumber(scd.getNumber());
		return sunCardDao.save(scd);
	}

	
	/**
	 * 删除
	 */
	@Override
	public SunCard delCard(String cardId) {
		SunCard sc = sunCardDao.findOne(cardId);
		sc.setDeleted(1);
		sunCardDao.save(sc);
		return sc;
	}


	@Override
	public com.ybt.common.util.Page<SunCard> findSunCardByUserId(final String userId, com.ybt.common.util.Page<SunCard> page)
			throws Exception {
		Page<SunCard>  jpapage = sunCardDao.findAll(
				new Specification<SunCard>(){
					public Predicate toPredicate(Root<SunCard> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
						 List<Predicate> list = new ArrayList<Predicate>();  
						    list.add(cb.equal(root.get("deleted"), "0")); 
						    list.add(cb.equal(root.get("userId"), userId)); 
						    list.add(cb.equal(root.get("status"), "1")); 
						    Predicate[] p = new Predicate[list.size()];  
						    return cb.and(list.toArray(p));  
					}}, (Pageable) new PageRequest(page.getPageNo()-1,page.getPageSize(),new Sort(Direction.DESC, new String[] {"bindingTime"})));
		page.setResult(jpapage.getContent());
		page.setTotalCount(jpapage.getTotalElements());
		
		List<SunCard> list = page.getResult();
		List<SunCard> yc_t = new ArrayList<SunCard>();
		for(SunCard card : list){ //在查询时设定过期时间
			if(card.getStatus()!=2){
				if(new Date().after(card.getFailureTime())&&card.getStatus()!=3){
					card.setStatus(3);
					card.setUpdateTime(new Date());
					sunCardDao.save(card);
				}
			}
			yc_t.add(card);
		}
		page.setResult(yc_t);
		return page;
	}


	@Override
	public Result<SunCard> sunCardBandding(String number, String pwd,String userId) {
		
		if(number==null||pwd==null)
			return new Result<SunCard>("卡号密码不能为空",null);
		
		SunCard card=sunCardDao.findByNumberAndPassWord(number,MD5Util.MD5(pwd));
		if(card==null){
			return new Result<SunCard>("绑定失败！卡号或密码错误",null);
		}
		
		if(card.getStatus()!=0){
			return new Result<SunCard>("绑定失败！阳光卡状态错误",null);
		}
		if(card.getDeleted()!=0){
			return new Result<SunCard>("绑定失败！阳光卡已被禁用",null);
		}
		
		if(card.getUserId()!=null&&!"".equals(card.getUserId())){
			return new Result<SunCard>("绑定失败！此卡已被绑定",null);
		}
		
		com.ybt.common.util.Page<SunCard> page = new com.ybt.common.util.Page<SunCard>();
		page.setPageNo(1);
		page.setPageSize(10);
		try {
			this.findSunCardByUserId(userId, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sunCard = constant.get("sunCard");
		int count=StringUtil.getInteger(sunCard);
		if(page.getResult().size()>=count){
			return new Result<SunCard>("绑定失败！阳光卡绑数量已上限",null);
		}
		
		card.setUserId(userId);
		card.setStatus(1);
		card.setBindingTime(new Date());
		sunCardDao.save(card);
		
		return new Result<SunCard>("",card);
	}

	@Override
	public Result<SunCard> sunECardBandding(String accessToken, String userId) {
		// TODO Auto-generated method stub
		
		com.ybt.common.util.Page<SunCard> page = new com.ybt.common.util.Page<SunCard>();
		page.setPageNo(1);
		page.setPageSize(1);
		try {
			this.findSunCardByUserId(userId, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sunCard = constant.get("sunCard");
		int count=StringUtil.getInteger(sunCard);
		if(page.getResult().size()>=count){
			return new Result<SunCard>("阳光卡绑定失败，绑数量已上限",null);
		}
		
		page = new com.ybt.common.util.Page<SunCard>();
		try {
			findSunCardByTypeAndState("E",0,page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(page.getResult().size()>0){
			SunCard card = page.getResult().get(0);
			card.setBindingTime(new Date());
			card.setUserId(userId);
			card.setUpdateTime(new Date());
			card.setStatus(1);
			this.save(card);
			
			return new Result<SunCard>("",card);
		}
		return null;
	}
	

	@Override
	public com.ybt.common.util.Page<SunCard> findSunCardByUserIdAndState(final String userId,final Integer state,
			com.ybt.common.util.Page<SunCard> page) throws Exception {
		Page<SunCard>  jpapage = sunCardDao.findAll(
				new Specification<SunCard>(){
					public Predicate toPredicate(Root<SunCard> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
						 List<Predicate> list = new ArrayList<Predicate>();  
						    list.add(cb.equal(root.get("deleted"), "0")); 
						    list.add(cb.equal(root.get("userId"), userId));
						    if(state!=null){
						    	 list.add(cb.equal(root.get("status"), state)); 
						    }
						    Predicate[] p = new Predicate[list.size()];  
						    return cb.and(list.toArray(p));  
					}}, (Pageable) new PageRequest(page.getPageNo()-1,page.getPageSize(),new Sort(Direction.DESC, new String[] {"bindingTime"})));
		page.setResult(jpapage.getContent());
		page.setTotalCount(jpapage.getTotalElements());
		
		//更新过期的卡
		List<SunCard> list = page.getResult();
		List<SunCard> yc_t = new ArrayList<SunCard>();
		for(SunCard card : list){ //在查询时设定过期时间
				if(new Date().after(card.getFailureTime())&&card.getStatus()!=3){
					card.setStatus(3);
					card.setUpdateTime(new Date());
					sunCardDao.save(card);
				}
				else{
					yc_t.add(card);
				}
		}
		page.setResult(yc_t);
		
		return page;
	}


	@Override
	public com.ybt.common.util.Page<SunCard> findSunCardByTypeAndState( String type,final Integer state,
			com.ybt.common.util.Page<SunCard> page) throws Exception {
		Page<SunCard>  jpapage = sunCardDao.findAll(
				new Specification<SunCard>(){
					public Predicate toPredicate(Root<SunCard> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
						 List<Predicate> list = new ArrayList<Predicate>();  
						    list.add(cb.equal(root.get("deleted").as(String.class), "0")); 
						    list.add(cb.equal(root.get("type").as(String.class), "E")); 
//						    list.add(cb.equal(root.get("userId").as(String.class), userId));
						    if(state!=null){
						    	 list.add(cb.equal(root.get("status").as(String.class), state)); 
						    }
						    Predicate[] p = new Predicate[list.size()];  
						    return cb.and(list.toArray(p));  
					}}, (Pageable) new PageRequest(page.getPageNo()-1,page.getPageSize(),new Sort(Direction.ASC, new String[] {"number"})));
		page.setResult(jpapage.getContent());
		page.setTotalCount(jpapage.getTotalElements());
		
		//更新过期的卡
		List<SunCard> list = page.getResult();
		List<SunCard> yc_t = new ArrayList<SunCard>();
		for(SunCard card : list){ //在查询时设定过期时间
				if(new Date().after(card.getFailureTime())&&card.getStatus()!=3){
					card.setStatus(3);
					card.setUpdateTime(new Date());
					sunCardDao.save(card);
				}
				else{
					yc_t.add(card);
				}
		}
		page.setResult(yc_t);
				
		return page;
	}


	@Override
	public boolean verifySunCard(String accessToken, String userId) {
		com.ybt.common.util.Page<SunCard> page = new com.ybt.common.util.Page<SunCard>();
		page.setPageNo(1);
		page.setPageSize(10);
		try {
			this.findSunCardByUserId(userId, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sunCard = constant.get("sunCard");
		int count=StringUtil.getInteger(sunCard);
		if(page.getResult().size()>=count){
			return false;
		}
		
		return true;
	}


	@Override
	public Result<SunCard> sunTCardBandding(String accessToken, String userId) {
		Result<SunCard> tcard = getSunTCard(userId,new Date());
		if(tcard.getState()==1){
			return new Result<SunCard>("已领取",tcard.getT());
		}
		Long num = sunCardDao.createCardNumber();
		Long first = 90052000000l;
		if(num<first)
			num=first;
		Date d = DateUtil.StringToDate(DateUtil.getDateFormat(new Date(), "yyyy-MM-dd")+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		System.out.println(num);
		System.out.println(DateUtil.getDateFormat(d, "yyyy-MM-dd HH:mm:ss"));
		
		SunCard newCard = new SunCard();
		newCard.setBindingTime(new Date());
		newCard.setNumber(num+"");
		newCard.setCardPassword(num+"");
		newCard.setDeleted(0);
		newCard.setCreateTime(new Date());
		newCard.setFailureTime(d);
		newCard.setPrefix("900");
		newCard.setStatus(0);
		newCard.setType("T");
		newCard.setUserId(userId);
		
		sunCardDao.save(newCard);
		return new Result<SunCard>("",newCard);
	}


	@Override
	public Result<SunCard> getSunTCard(String userId,Date today) {
		// TODO Auto-generated method stub
		List<SunCard> list = sunCardDao.findSunCardBybindingTimeAndUserId(DateUtil.StringToDate(DateUtil.getDateFormat(today, "yyyyMMdd"),  "yyyyMMdd"), userId);
		for (SunCard sunCard : list) {
			System.out.println(sunCard.getNumber());
			return new Result<SunCard>("",sunCard);
		}
		return new Result<SunCard>("未领取",null);
	}


	@Override
	public Result<SunCard> giftSunECardFor20161015(String accessToken, String userId) {
		
		com.ybt.common.util.Page<SunCard> page = new com.ybt.common.util.Page<SunCard>();
		page.setPageNo(1);
		page.setPageSize(1);
		try {
			this.findSunCardByUserId(userId, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//判断是否 已赠送
		List<SunTempRecord> tempList = tempRecordService.findByUserIdAndType(userId, "GIFT_ECARD_20161015");
		
		if (tempList!=null&&!tempList.isEmpty()) {
			return new Result<SunCard>("抱歉,您已经领取过,不能重复领取!",null);
		}
		
		page = new com.ybt.common.util.Page<SunCard>();
		try {
			findSunCardByTypeAndState("E",0,page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(page.getResult().size()>0){
			SunCard card = page.getResult().get(0);
			card.setBindingTime(new Date());
			card.setUserId(userId);
			card.setUpdateTime(new Date());
			card.setStatus(1);
			this.save(card);
			
			//纪录
			SunTempRecord record = new SunTempRecord();
			record.setContent(card.getNumber());
			record.setCreateTime(new Date());
			record.setDeleted(0);
			record.setUserId(userId);
			record.setLevel(0);
			record.setType("GIFT_ECARD_20161015");
			tempRecordService.save(record);
			
			return new Result<SunCard>("",card);
		}
		
		return new Result<SunCard>("抱歉,已领取完!",null);
	}


	public List<SunCard> findSunCardByValidAndUserId(String userid) {
		return sunCardDao.findSunCardByValidAndUserId(userid);
	}


	public com.ybt.common.util.Page<SunCard> findSunCardByUsedAndUserId(String userid, com.ybt.common.util.Page<SunCard> page) {
		page.setResult(sunCardDao.findSunCardByUsedAndUserId(userid, page.getPageSize()*(page.getPageNo()-1), page.getPageSize()));
		return page;
	}


	public int getCardCountByUserId(String userid) {
		return  new Long(sunCardDao.getMyCardCount(userid)).intValue();
	}


	@Override
	public Result<SunCard> giftSunECardForActivity(String code, int max,String userId) {
		
		
		//判断是否 已赠送
		List<SunTempRecord> tempList = tempRecordService.findByUserIdAndType(userId, code);
		
		if (tempList!=null&&tempList.size()>=max) {
			return new Result<SunCard>("抱歉,不能在领取了!",null);
		}
		com.ybt.common.util.Page<SunCard> page = new com.ybt.common.util.Page<SunCard>();
		page.setPageNo(1);
		page.setPageSize(1);
		try {
			findSunCardByTypeAndState("E",0,page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(page.getResult().size()>0){
			SunCard card = page.getResult().get(0);
			card.setBindingTime(new Date());
			card.setUserId(userId);
			card.setUpdateTime(new Date());
			card.setStatus(1);
			this.save(card);
			
			//纪录
			SunTempRecord record = new SunTempRecord();
			record.setContent(card.getNumber());
			record.setCreateTime(new Date());
			record.setDeleted(0);
			record.setUserId(userId);
			record.setLevel(0);
			record.setType(code);
			tempRecordService.save(record);
			
			return new Result<SunCard>("",card);
		}
		
		return new Result<SunCard>("抱歉,已领完!",null);
	}


	@Override
	public List<SunCard> findSunCardByUsedAndUserId(String userid) {
		return sunCardDao.findSunCardByUsedAndUserId(userid);
	}


	@Override
	public com.ybt.common.util.Page<SunCard> findSunCardByStatusAndUserId(
			String userid, com.ybt.common.util.Page<SunCard> page) {
		page.setResult(sunCardDao.findSunCardByStatusAndUserId(userid, page.getPageSize()*(page.getPageNo()-1), page.getPageSize()));
		return page;
	}

}
