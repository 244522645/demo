package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunLetterBless;
import com.ybt.model.work.SunLetterCards;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.IBaseService;

/**   
 * This class is used for ...   
 * @author AndyBao  
 * @version 4.0, 2016年10月27日 上午9:09:24   
 */   
@Component
public interface SunLetterService extends IBaseService<SunLetter, String> {
	
	/**  
	 * 保存信件
	 * @param userId
	 * @param sender
	 * @param receiver
	 * @param music
	 * @param stamp
	 * @param bless
	 * @param orders
	 * @param cards
	 * @param voiceId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月18日 上午10:34:51 
	 */
	public Result<SunLetter> saveLetter(String id,String userId,String sender,String receiver,String music,String stamp,String bless,String[] orders,String[] cards,String voiceId);

	

	/**  
	 * 领卡
	 * @param letter
	 * @param cards
	 * @param voiceId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月27日 上午9:09:29 
	 */
	public Result<SunLetter> pullLetter(SunLetter letter, List<SunLetterCards> cards,List<SunLetterBless> orders, SunWechatUser w) ;

	
	/**  
	 * 发送
	 * @param letterId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月27日 上午9:09:29 
	 */
	public Result<SunLetter> sendLetter(String letterId) ;


	/**  
	 * 我信件列表
	 * @param mid
	 * @param i
	 * @param m
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月27日 上午10:25:35 
	 */
	public com.ybt.common.util.Page<SunLetter>  getMyAllLetterList(String mid,com.ybt.common.util.Page<SunLetter> page);
	
	
	/**  
	 * 我收到的简信列表
	 * @param mid
	 * @return  page<SunLetter>
	 * @author jsj
	 * @version V4.0, 2016-11-1
	 */
	public com.ybt.common.util.Page<SunLetter>  findMyReceivedLetterList(String mid,com.ybt.common.util.Page<SunLetter> page);
	
	/**  
	 * 我送出的简信列表
	 * @param mid
	 * @return  page<SunLetter>
	 * @author jsj
	 * @version V4.0, 2016-11-1
	 */
	public com.ybt.common.util.Page<SunLetter>  findMySendLetterList(String mid,com.ybt.common.util.Page<SunLetter> page);
	
	/**  
	 * 我未送出的简信列表（草稿列表）
	 * @param mid
	 * @return  page<SunLetter>
	 * @author jsj
	 * @version V4.0, 2016-11-1
	 */
	public com.ybt.common.util.Page<SunLetter>  findMyNoSendLetterList(String mid,com.ybt.common.util.Page<SunLetter> page);
	
	
	 /**
	 * 我收到的简信总数
	 * @param mid
	 * @return  int
	 * @author jsj
	 * @version V4.0, 2016-11-1
	 */
	 public int findMyReceivedLetterCount(String mid);
	 
	 /**
	 * 我送出的简信总数
	 *@param mid
	 * @return  int
	 * @author jsj
	 * @version V4.0, 2016-11-1 
	 */
	 public int findMySendLetterCount(String mid);
	 
	 /**
	 * 我未送出的简信总数
	 * @param mid
	 * @return  int
	 * @author jsj
	 * @version V4.0, 2016-11-1
	 */
	 public int findMyNoSendLetterCount( String mid);

}
