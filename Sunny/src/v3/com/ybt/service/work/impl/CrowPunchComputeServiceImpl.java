package com.ybt.service.work.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.SendWeixinMessage;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.CrowPunchDao;
import com.ybt.model.work.CrowPkMe;
import com.ybt.model.work.CrowPkOne;
import com.ybt.model.work.CrowPunch;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.CrowAccountService;
import com.ybt.service.work.CrowPkMeService;
import com.ybt.service.work.CrowPkOneService;
import com.ybt.service.work.CrowPunchComputeService;
import com.ybt.service.work.CrowPunchService;
import com.ybt.service.work.CrowUserInfoService;

import wechat.util.WXUtil;

@Component
public class CrowPunchComputeServiceImpl extends BaseServiceImpl<CrowPunch,String> implements CrowPunchComputeService{
	
	
	@Autowired
	private CrowPunchDao crowPunchDao;
	@Autowired
	private CrowPunchService crowPunchService;
	@Autowired 
	private CrowPkMeService crowPkMeService;
	@Autowired 
	private CrowPkOneService crowPkOneServece;
	@Autowired  
	private CrowAccountService crowAccountService;
	@Autowired  
	private CrowUserInfoService  crowUserInfoService;
	@SuppressWarnings("rawtypes")
	@Autowired
	public HashMap constant;
	
	public BaseDao<CrowPunch, String> getDao() {
		// TODO Auto-generated method stub
		return crowPunchDao;
	}

	@Override
	public Result<CrowPunch> punchCompute() {
		System.out.println("@punch start ----- 打卡计算 --开始------"+DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
		computePunch(20);//打卡计算  3分钟 算一次 10小时 200次  200*20=4000人
		System.out.println("@punch end----- 打卡计算 --结束------");
		System.out.println("@PKMe start ----- 个人挑战--开始------");
		computeMe(20);//个人挑战计算
		System.out.println("@PKMe end----- 个人挑战 --结束------");
		System.out.println("@PKOne start----- 对战计算 --开始-----");
		computePKOne(20);//一对一挑战计算
		System.out.println("PKOne end----- 对战计算 --结束------"+DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return null;
	}
	public void saveCrowPunchComputea(CrowPunch crowPunch) {
		crowPunch.setIscompute(1);
		crowPunch.setUpdateTime(new Date());
		crowPunchDao.save(crowPunch);
	}
	@Override
	public Result<CrowPunch> punchCompute(CrowPunch crowPunch) {
		if(crowPunch==null){
			return  new Result<CrowPunch>("null",null);
		}
		if(crowPunch.getIscompute()==1){
			return  new Result<CrowPunch>("null",null);
		}
		
		CrowUserInfo userInfo = crowUserInfoService.findByUserID(crowPunch.getUserId());
		if(crowPunch.getIspass()==0){
			userInfo.setContinuousDay(0);
			crowUserInfoService.save(userInfo);
			saveCrowPunchComputea(crowPunch);
			return  new Result<CrowPunch>("",crowPunch);
		}
		
		//总天数
		userInfo.setAlldays(userInfo.getAlldays()+1);
		
		//第一次打卡时间
		if(userInfo.getFirstPunchDate()==null){
			userInfo.setFirstPunchDate(new Date());
		}
		if(userInfo.getLastPunchDate()==null){
			userInfo.setLastPunchDate(new Date());
		}
		
		//最近持续天
		Date last = userInfo.getLastPunchDate(); 
		String last2=DateUtil.getBeforDay(1);
		if(last2.equals(DateUtil.getDateFormat(last, "yyyy-MM-dd"))){
			userInfo.setContinuousDay(userInfo.getContinuousDay()+1);
		}else{
			userInfo.setContinuousDay(1);
		}
		//最后打卡时间
		userInfo.setLastPunchDate(new Date());
		userInfo.setUpdateTime(new Date());
		crowUserInfoService.save(userInfo);
		saveCrowPunchComputea(crowPunch);
		return  new Result<CrowPunch>("",crowPunch);
		
	}

	//打卡计算
	public void  computePunch(int count) {
		//查询当前 所有打卡
		List<CrowPunch> punchList =findCrowPunchByTop(count);
		for (CrowPunch crowPunch : punchList) {
			punchCompute( crowPunch);
		}
		
		return;
	}
	//计算个人模式
	public void  computeMe(int count) {
		// TODO Auto-generated method stub
		//查询当前 所有个人模式挑战
		List<CrowPkMe> pkmeList =crowPkMeService.findByAllUserAndPkTypeAndPkStatus( 1, 1,count);
		for (CrowPkMe crowPkMe : pkmeList) {
			CrowPunch punch = crowPunchService.findByUserIdAndToday(crowPkMe.getUserId(), DateUtil.getDateFormat(new Date(), "yyyy-MM-dd"));
			//如果未打卡  则挑战失败
			if(punch==null){
				if(DateUtil.compareDate(new Date(), crowPkMe.getStartDate())){
					crowPkMe.setUpdateTime(new Date());
					crowPkMe.setStopDate(new Date());
					crowPkMe.setPkStatus(0);//挑战关闭
					crowPkMe.setPkResult(0);//挑战失败
					
				}
				crowPkMe.setComputeTime(new Date());//添加计算时间 下次不查询
				crowPkMeService.save(crowPkMe);
				
			}else{
				
				
				if(crowPkMe.getStopDate()==null&&DateUtil.compareDate(new Date(), crowPkMe.getStartDate())){
					crowPkMe.setUpdateTime(new Date());
					crowPkMe.setCompleteDay(crowPkMe.getCompleteDay()+1);
					crowPkMe.setStopDate(new Date());
				}
				//是否 pk里签到
				if(crowPkMe.getStopDate()!=null&&!DateUtil.getDateTime("yyyy-MM-dd", punch.getCreateTime()).equals(DateUtil.getDateTime("yyyy-MM-dd",crowPkMe.getStopDate()))){
					crowPkMe.setCompleteDay(crowPkMe.getCompleteDay()+1);
					crowPkMe.setStopDate(new Date());
					crowPkMe.setUpdateTime(new Date());
				}
				//挑战结束日
				if(crowPkMe.getPkResult()==2&&DateUtil.getDateTime("yyyy-MM-dd", punch.getCreateTime()).equals(DateUtil.getDateTime("yyyy-MM-dd",crowPkMe.getEndDate()))){
					if(crowPkMe.getCompleteDay()==crowPkMe.getDay()){
						crowPkMe.setPkResult(1);//挑战完成
						crowPkMe.setPkStatus(0);
					}
					else{
						crowPkMe.setPkResult(0);//挑战失败
						crowPkMe.setPkStatus(0);
					}
						
					
				}
				
				if(crowPkMe.getPkLevel()==2){
						//修改用户信息
						CrowUserInfo userInfo = crowUserInfoService.findByUserID(crowPkMe.getUserId());
						userInfo.setLevel(1);
						userInfo.setSublevel(crowPkMe.getCompleteDay());
						crowUserInfoService.save(userInfo);
				}
				crowPkMe.setComputeTime(new Date()); //添加计算时间 下次不查询
				crowPkMeService.save(crowPkMe);
			}
			
			
			
			
			//********挑战结果处理************
			//挑战成功时
			if(crowPkMe.getPkResult()==1){
				//契约金 返还 发消息
				crowAccountService.addBalance("契约金返还",crowPkMe.getMoney().doubleValue(),crowPkMe.getUserId());
				
				//修改用户信息
				CrowUserInfo userInfo = crowUserInfoService.findByUserID(crowPkMe.getUserId());
				userInfo.setWin(userInfo.getWin()+1);
				userInfo.setUpdateTime(new Date());
				int l=userInfo.getLevel();
				if(l<1){
					userInfo.setLevel(1);
					SendWeixinMessage.sendMessage(
							"挑战结果通知",
							"恭喜你！个人挑战成功，获得一枚精美的鸡蛋",
							"挑战成功", DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm"), "查看个人主页",
							WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/crow/index",  false),
							crowPkMe.getUserId()
							);
				}
				
				if(crowPkMe.getPkLevel()==2){
					userInfo.setLevel(2);
					userInfo.setSublevel(crowPkMe.getCompleteDay());
					SendWeixinMessage.sendMessage(
							"挑战结果通知",
							"恭喜你！个人挑战成功，鸡蛋孵化成小鸡了",
							"挑战成功", DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm"), "查看个人主页",
							WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/crow/index",  false),
							crowPkMe.getUserId()
							);
				}
				if(crowPkMe.getPkLevel()==3){
					userInfo.setLevel(3);
					userInfo.setSublevel(crowPkMe.getCompleteDay());
				}
				crowUserInfoService.save(userInfo);
			}
			//挑战失败时
			if(crowPkMe.getPkResult()==0){
				//契约金 返还 ，发消息
				//契约金 返还 发消息
				//，发消息
				SendWeixinMessage.sendMessage(
						"挑战结果通知",
						"抱歉！个人挑战失败",
						"挑战失败", DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm"), "查看个人进度",
						WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/crow/index",  false),
						crowPkMe.getUserId()
						);
				
				//修改用户信息
				CrowUserInfo userInfo = crowUserInfoService.findByUserID(crowPkMe.getUserId());
				userInfo.setLose(userInfo.getLose()+1);
				if(crowPkMe.getPkLevel()==2){
					userInfo.setLevel(1);
					userInfo.setSublevel(-1);
				}
				if(crowPkMe.getPkLevel()==3){
					userInfo.setLevel(2);
					userInfo.setSublevel(-1);
				}
				userInfo.setUpdateTime(new Date());
				crowUserInfoService.save(userInfo);
			}
		}
		
			return;
		}
	//2. 一对一 挑战 start
		public void  computePKOne(int count) {
			//所有 一对一挑战
			List<CrowPkOne> pklist = crowPkOneServece.findCrowPkMeListByPkTypeAndPkStatusAndTop(1, 0, count);
			for (CrowPkOne crowPkOne : pklist) {
				//双方打卡情况
				CrowPunch punch = crowPunchService.findByUserIdAndToday(crowPkOne.getUser().getId(), DateUtil.getDateFormat(new Date(), "yyyy-MM-dd"));
				CrowPunch touserpunch = crowPunchService.findByUserIdAndToday(crowPkOne.getTouser().getId(), DateUtil.getDateFormat(new Date(), "yyyy-MM-dd"));
				
				//打卡日期 是 结束日期
				if(DateUtil.getDateTime("yyyy-MM-dd", new Date()).equals(DateUtil.getDateTime("yyyy-MM-dd",crowPkOne.getEndDate()))){
					
					if(punch != null){
						crowPkOne.setUserPkCompleteDay(crowPkOne.getUserPkCompleteDay()+1);
//						saveCrowPunchCompute(punch);
						
					}
					if(touserpunch != null){
						crowPkOne.setOtherUserPkCompleteDay(crowPkOne.getOtherUserPkCompleteDay()+1);
					}
					
					//##1. 双赢  ##
					if((crowPkOne.getUserPkCompleteDay()==crowPkOne.getDay())&&(crowPkOne.getOtherUserPkCompleteDay()==crowPkOne.getDay())){
						crowPkOne.setUserPkStatus(1);
						crowPkOne.setOtherUserPkStatus(1);
						crowPkOne.setPkStatus(2);
						crowPkOne.setPkResult(3);
						
					}else if((crowPkOne.getUserPkCompleteDay()<crowPkOne.getDay())&&(crowPkOne.getOtherUserPkCompleteDay()<crowPkOne.getDay())){
						//##2. 双输  ##
						crowPkOne.setUserPkStatus(0);
						crowPkOne.setOtherUserPkStatus(0);
						crowPkOne.setPkStatus(2);
						crowPkOne.setPkResult(4);
					}else if((crowPkOne.getUserPkCompleteDay()==crowPkOne.getDay())&&(crowPkOne.getOtherUserPkCompleteDay()<crowPkOne.getDay())){
						//##3. 我赢  ##
						crowPkOne.setUserPkStatus(1);
						crowPkOne.setOtherUserPkStatus(0);
						crowPkOne.setPkStatus(2);
						crowPkOne.setPkResult(1);
					}else if((crowPkOne.getUserPkCompleteDay()<crowPkOne.getDay())&&(crowPkOne.getOtherUserPkCompleteDay()==crowPkOne.getDay())){
						//##4. 他赢  ##
						crowPkOne.setUserPkStatus(0);
						crowPkOne.setOtherUserPkStatus(1);
						crowPkOne.setPkStatus(2);
						crowPkOne.setPkResult(2);
					}
					crowPkOne.setUpdateTime(new Date());
					crowPkOne.setComputeTime(new Date());//添加计算时间 下次不查询
					crowPkOneServece.save(crowPkOne);
					
					//挑战结果 处理 通知
					if(crowPkOne.getPkStatus()==2){
						StringBuffer sbstr=new StringBuffer("");
						sbstr.append("挑战结果通知：\n");
						//sbstr.append("<a href='"+WXUtil.getOAuthUrl(url, false)+"'>点击查看礼物</a>");
						if(crowPkOne.getPkResult()==1){
							crowAccountService.addBalance("恭喜挑战成功，获得挑战金。",crowPkOne.getMoney().doubleValue()+crowPkOne.getMoney().doubleValue(), crowPkOne.getUser().getId());
							
							//修改用户信息
							CrowUserInfo userInfo = crowUserInfoService.findByUserID(crowPkOne.getUser().getId());
							userInfo.setWin(userInfo.getWin()+1);
							String eng= "";
							if(userInfo.getLevel()<1){
								userInfo.setLevel(1);
								 eng= "，获得一枚精美的鸡蛋";
							}
							userInfo.setUpdateTime(new Date());
							crowUserInfoService.save(userInfo);
							
							SendWeixinMessage.sendMessage(
									"挑战结果通知",
									"您在与“"+crowPkOne.getTouser().getWechatNickname()+"”的对战中挑战挑战成功"+eng,
									"挑战结果", DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm"), "查看对战详情",
									WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/crow/pkone/show?id="+crowPkOne.getId(),  false),
									crowPkOne.getUser().getId()
									);
						}else if(crowPkOne.getPkResult()==2){
							SendWeixinMessage.sendMessage(
									"挑战结果通知",
									"您在与“"+crowPkOne.getTouser().getWechatNickname()+"”的对战中挑战挑战失败",
									"挑战结果", DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm"), "查看对战详情",
									WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/crow/pkone/show?id="+crowPkOne.getId(),  false),
									crowPkOne.getUser().getId()
									);
							//修改用户信息
							CrowUserInfo userInfo = crowUserInfoService.findByUserID(crowPkOne.getUser().getId());
							userInfo.setLose(userInfo.getLose()+1);
							userInfo.setUpdateTime(new Date());
							crowUserInfoService.save(userInfo);
						}else if(crowPkOne.getPkResult()==3){
							crowAccountService.addBalance("恭喜挑战成功，挑战金已返还",crowPkOne.getMoney().doubleValue(), crowPkOne.getUser().getId());
							//修改用户信息
							CrowUserInfo userInfo = crowUserInfoService.findByUserID(crowPkOne.getUser().getId());
							userInfo.setWin(userInfo.getWin()+1);
							String eng= "";
							if(userInfo.getLevel()<1){
								userInfo.setLevel(1);
								 eng= "，获得一枚精美的鸡蛋";
							}
							userInfo.setUpdateTime(new Date());
							crowUserInfoService.save(userInfo);
							
							SendWeixinMessage.sendMessage(
									"挑战结果通知",
									"您在与“"+crowPkOne.getTouser().getWechatNickname()+"”的对战中挑战成功"+eng,
									"挑战结果", DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm"), "查看对战详情",
									WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/crow/pkone/show?id="+crowPkOne.getId(),  false),
									crowPkOne.getUser().getId()
									);
						}else if(crowPkOne.getPkResult()==4){
							SendWeixinMessage.sendMessage(
									"挑战结果通知",
									"您在与“"+crowPkOne.getTouser().getWechatNickname()+"”的对战中挑战失败",
									"挑战结果", DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm"), "查看对战详情",
									WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/v3/crow/pkone/show?id="+crowPkOne.getId(),  false),
									crowPkOne.getUser().getId()
									);
							//修改用户信息
							CrowUserInfo userInfo = crowUserInfoService.findByUserID(crowPkOne.getUser().getId());
							userInfo.setLose(userInfo.getLose()+1);
							userInfo.setUpdateTime(new Date());
							crowUserInfoService.save(userInfo);
						}
						//MessageAPI.messageCustomSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), new TextMessage(crowPkOne.getUser().getId(),sbstr.toString()));
						
						
					}
				}else{
						
					if(punch != null){
						//第一次打卡
						if(crowPkOne.getStopDate()==null&&DateUtil.compareDate(new Date(), crowPkOne.getStartDate())){
							crowPkOne.setUpdateTime(new Date());
							crowPkOne.setUserPkCompleteDay(crowPkOne.getUserPkCompleteDay()+1);
							crowPkOne.setStopDate(new Date());
						}
						//是否 pk里签到
						if(crowPkOne.getStopDate()!=null&&!DateUtil.getDateTime("yyyy-MM-dd", punch.getCreateTime()).equals(DateUtil.getDateTime("yyyy-MM-dd",crowPkOne.getStopDate()))){
							crowPkOne.setUserPkCompleteDay(crowPkOne.getUserPkCompleteDay()+1);
							crowPkOne.setStopDate(new Date());
							crowPkOne.setUpdateTime(new Date());
						}
						
					}
					if(touserpunch != null){
						Date o =crowPkOne.getOtherStopDate();
						if(  o==null &&  DateUtil.compareDate(new Date(), crowPkOne.getStartDate())){
							crowPkOne.setUpdateTime(new Date());
							crowPkOne.setOtherUserPkCompleteDay(crowPkOne.getOtherUserPkCompleteDay()+1);
							crowPkOne.setOtherStopDate(new Date());
						}else if(o !=null){
							if(!DateUtil.getDateTime("yyyy-MM-dd", touserpunch.getCreateTime()).equals(DateUtil.getDateTime("yyyy-MM-dd",o ))){
								crowPkOne.setOtherUserPkCompleteDay(crowPkOne.getOtherUserPkCompleteDay()+1);
								crowPkOne.setOtherStopDate(new Date());
								crowPkOne.setUpdateTime(new Date());
							}
						}else{
							
						}
					}else{
//						crowPkOne.setOtherStopDate(new Date());
//						crowPkOne.setOtherUserPkStatus(0);
//						crowPkOne.setUpdateTime(new Date());
					}
					crowPkOne.setComputeTime(new Date());//添加计算时间 下次不查询
					crowPkOneServece.save(crowPkOne);
				}
				
				
			}
			return;
	}

		@Override
		public List<CrowPunch> findCrowPunchByTop(int top) {
			// TODO Auto-generated method stub
			return crowPunchDao.findCrowPunchByTop(top);
		}

}
