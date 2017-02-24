package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.model.work.CrowPkMe;
import com.ybt.model.work.CrowPkOne;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.IBaseService;
@Component
public interface CrowPkOneService extends IBaseService<CrowPkOne, String> {
	/*
	 * 查询挑战中的用户
	 */
	public List<CrowPkOne> findByuserid(String userid);
	/*
	 * 查询挑战胜利的用户
	 */
	public List<CrowPkOne> findCrowUserWin(String userid);
	/*
	 * 查询挑战失败的用户
	 */
	public List<CrowPkOne> findCrowUserLose(String userid);
	
	public List<CrowPkOne> findCrowPkMeListByPkTypeAndPkStatusAndTop( int pktype, int status, int top);
	
	/**
	 *  查询应战方是否第一次接受发起方的挑战
	 * @param userId    应战人id
	 * @param OtherUserId   发起挑战人id
	 * @param pkStatus    // 挑战状态 0：未开始，1：进行中    ，2：挑战结束
	 * @return
	 */
	public List<CrowPkOne> isFirstAcceptChallenge(String userId,String otherUserId);
	
	/**
	 * 
	 * @param yingzhan  应战人
	 * @param leizhu    擂主
	 * @param orderNo   我们的订单号
	 * @return
	 */
	public String creatBothrecordsAndSendMessage(SunWechatUser yingzhan,SunWechatUser leizhu,String orderNo);

}
