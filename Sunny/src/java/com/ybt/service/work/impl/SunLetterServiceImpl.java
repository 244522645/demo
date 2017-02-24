package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.util.EmojiFilter;
import com.ybt.common.util.Page;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunLetterDao;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunLetterBless;
import com.ybt.model.work.SunLetterCards;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyMusic;
import com.ybt.model.work.SunZyVoice;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.MusicService;
import com.ybt.service.work.OrderService;
import com.ybt.service.work.SunBlessService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunCardTradeService;
import com.ybt.service.work.SunLetterBlessService;
import com.ybt.service.work.SunLetterCardsService;
import com.ybt.service.work.SunLetterService;
import com.ybt.service.work.VoiceService;

@Component
public class SunLetterServiceImpl extends BaseServiceImpl<SunLetter, String> implements SunLetterService{
	
	@Autowired
	private SunLetterDao letterDao;
	
	@Autowired
	private IWechatService userService;
	@Autowired
	private MusicService misicService;
	@Autowired
	private VoiceService voiceService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SunCardService cardService;
	@Autowired
	private SunCardTradeService cardTradeService;
	@Autowired
	@Qualifier("sunBlessService")
	private SunBlessService blessService;
	@Autowired
	private SunLetterBlessService letterBlessService;
	@Autowired
	private SunLetterCardsService letterCardsService;
	
	
	@Override
	public BaseDao<SunLetter, String> getDao() {
		return letterDao;
	}

	@Override
	public Result<SunLetter> saveLetter(String id,String userId, String sender, String receiver, String music, String stamp,
			String bless, String[] orders, String[] cards, String voiceId) {
		// TODO Auto-generated method stub
		
		SunWechatUser user = userService.findOne(userId);
		if(user==null)
			return new Result<SunLetter>("失敗",null);
		SunLetter letter = new SunLetter();
		
		//过滤客户端表情
		sender = EmojiFilter.emojiEmpty(sender,"");
		receiver = EmojiFilter.emojiEmpty(receiver,"");
		bless = EmojiFilter.emojiEmpty(bless,"");
		
		if(id!=null&&!"".equals(id))
			letter = letterDao.findOne(id);
		letter.setUserId(user);
		letter.setCreateTime(new Date());
		letter.setSender(sender);
		letter.setReceiver(receiver);
		
		letter.setStamp(stamp);
		letter.setBless(bless);
		if(orders!=null&&orders.length>0){
			letter.setIsorder(1);
		}
		if(cards!=null&&cards.length>0){
			letter.setIscard(1);
		}
		if(!"".equals(voiceId)){
			SunZyVoice voice = voiceService.findOne(voiceId);
			letter.setVoice(voice);
			letter.setIsvoice(1);
		}
		letter.setIsread(0);
		if(music!=null){
			SunZyMusic sunmusic = misicService.findOne(music);
			letter.setMusic(sunmusic);	
		}
		letter.setStatus(1);
		letterDao.save(letter);
		
		//letterOrdersService.saveLetterOrders(letter, orders);
		letterBlessService.saveLetterBless(letter, orders);
		letterCardsService.saveLetterCards(letter, cards);
		
		return new Result<SunLetter>("",letter);
	}

	public Result<SunLetter> pullLetter(SunLetter letter, List<SunLetterCards> cards,List<SunLetterBless> orders, SunWechatUser w) {
		
		if(letter.getToUserId()!=null||letter.getIsread()==1){
			return new Result<SunLetter>("已领取",null);
		}
		if(w==null){
			return new Result<SunLetter>("失败",null);
		}
		
		if(letter.getUserId().getId().equals(w.getId())){
			return new Result<SunLetter>("自己的",null);
		}
		
		SunWechatUser user= userService.findOne(w.getId());
		letter.setToUserId(user);
		letter.setIsread(1);
		
		if(letter.getIscard()==1){
			for (SunLetterCards card : cards) {
				SunCard sc= card.getCard();
				//判断卡类型是否 送人的
				if(sc!=null&&sc.getStatus()==5){
					sc.setUserId(user.getId());
					sc.setStatus(1);
					sc.setUpdateTime(new Date());
					cardService.save(sc);
					//保存明细
					cardTradeService.saveLetterCardTrade(letter, sc);
				}
			}
		}
		
		if(letter.getIsorder()==1){
			for (SunLetterBless sunLetterOrders : orders) {
				SunBless sb= sunLetterOrders.getBless();
				if(sb!=null){
					SunDdOrder sc= sb.getOrder();
					if(sc!=null&&sc.getSendeeId()==null&&sc.getStatus().equals("20")){
						sc.setSellerId(user.getId());
						sc.setUpdateTime(new Date());
						sc.setSendeeId(user.getId());
						sc.setStatus("100");
						orderService.save(sc);
					}
					w = userService.findOne(w.getId());
					sb.setIsread(1);
					sb.setStatus(1);
					sb.setToUserId(w);
					sb.setUpdateTime(new Date());
					blessService.save(sb);
					
				}
				
			}
		}
		this.save(letter);
		return new Result<SunLetter>("",letter);
	}

	@Override
	public Page<SunLetter>  getMyAllLetterList(String mid, Page<SunLetter> page) {
		try{
		List<SunLetter> list = letterDao.getMyAllLetterList(mid, page.getPageSize()*(page.getPageNo()-1), page.getPageSize());
		if(list!=null){
			page.setResult(list);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public Result<SunLetter> sendLetter(String letterId) {
		// TODO Auto-generated method stub
		SunLetter letter = this.findOne(letterId);
		
		if(letter==null){
			return new Result<SunLetter>("发送失败,无效信件",null);
		}
		
		if(letter.getIssend()==1){
			return new Result<SunLetter>("",letter);
		}
		letter.setIssend(1);
		letter.setUpdateTime(new Date());
		this.save(letter);
		
		List<SunLetterBless> blesss = null;
		if(letter.getIsorder()==1){
			blesss = letterBlessService.findByLetterId(letterId);
			for (SunLetterBless sunLetterbless : blesss) {
				SunBless sc= sunLetterbless.getBless();
				if(sc!=null){
					sc.setIssend(1);
					sc.setUpdateTime(new Date());
					blessService.save(sc);
				}
			}
		}
		
		return new Result<SunLetter>("",letter);
	}

	public Page<SunLetter> findMyReceivedLetterList(String mid,Page<SunLetter> page) {
		try{
			List<SunLetter> list = letterDao.findMyReceivedLetterList(mid, page.getPageSize()*(page.getPageNo()-1), page.getPageSize());
		if(list!=null){
			page.setResult(list);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return page;
	}

	public Page<SunLetter> findMySendLetterList(String mid, Page<SunLetter> page) {
		try{
			List<SunLetter> list = letterDao.findMySendLetterList(mid, page.getPageSize()*(page.getPageNo()-1), page.getPageSize());
		if(list!=null){
			page.setResult(list);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return page;
	}

	public Page<SunLetter> findMyNoSendLetterList(String mid,Page<SunLetter> page) {
		try{
			List<SunLetter> list = letterDao.findMyNoSendLetterList(mid, page.getPageSize()*(page.getPageNo()-1), page.getPageSize());
		if(list!=null){
			page.setResult(list);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return page;
	}

	public int findMyReceivedLetterCount(String mid) {
		return letterDao.findMyReceivedLetterCount(mid).intValue();
	}

	public int findMySendLetterCount(String mid) {
		return letterDao.findMySendLetterCount(mid).intValue();
	}

	public int findMyNoSendLetterCount(String mid) {
		return letterDao.findMyNoSendLetterCount(mid).intValue();
	}

}
