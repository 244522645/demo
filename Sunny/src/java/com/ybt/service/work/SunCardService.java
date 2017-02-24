package com.ybt.service.work;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.util.PropertyFilter;
import com.ybt.model.work.SunCard;
import com.ybt.service.base.IBaseService;
@Component
public interface SunCardService extends IBaseService<SunCard, String> {
	public Page<SunCard> findSunCardByProperty(List<PropertyFilter> filters,Pageable pageable); //分页查询

	public SunCard getOneCard(String cardId); 

	public com.ybt.common.util.Page<SunCard> findSunCardByNumber(String number,com.ybt.common.util.Page<SunCard> page) throws Exception;//按卡号查询
	
	SunCard saveCard(SunCard cardId);  //添加

	SunCard delCard(String cardId);  //删除

	//SunCard releaseCard(String cardId); //发布
	
	//public int blind(String cardNum,String cardPass);
	
	/**  
	 * 查询 用户卡列表
	 * @param userId
	 * @param page
	 * @return
	 * @throws Exception  
	 * @author AndyBao
	 * @version V4.0, 2016年8月22日 下午2:05:26 
	 */
	public com.ybt.common.util.Page<SunCard> findSunCardByUserId(String userId,com.ybt.common.util.Page<SunCard> page) throws Exception;
	
	/**  查询 用户卡列表
	 * @param userId
	 * @param state
	 * @param page
	 * @return
	 * @throws Exception  
	 * @author AndyBao
	 * @version V4.0, 2016年8月22日 下午2:05:00 
	 */
	public com.ybt.common.util.Page<SunCard> findSunCardByUserIdAndState(String userId,Integer state,com.ybt.common.util.Page<SunCard> page) throws Exception;
	
	/**  
	 * 绑定卡
	 * @param number
	 * @param password
	 * @param userId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月22日 下午2:04:45 
	 */
	public Result<SunCard> sunCardBandding(String number,String password,String userId) ;
	
	/**  
	 * 电子卡绑定
	 * @param accessToken  授权码
	 * @param userId  用户id
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月22日 下午2:04:45 
	 */
	public Result<SunCard> sunECardBandding(String accessToken,String userId) ;
	
	/**  
	 * 20161015徒步活动送电子卡  每人限得一张 
	 * @param accessToken  授权码
	 * @param userId  用户id
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月22日 下午2:04:45 
	 */
	public Result<SunCard> giftSunECardFor20161015(String accessToken,String userId) ;
	
	/**  
	 * 送卡活动
	 * @param accessToken  授权码
	 * @param userId  用户id
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月22日 下午2:04:45 
	 */
	public Result<SunCard> giftSunECardForActivity(String code,int max,String userId) ;
	
	/**  
	 * 验证是否还能绑定 卡
	 * @param accessToken  授权码
	 * @param userId  用户id
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月22日 下午2:04:45 
	 */
	public boolean verifySunCard(String accessToken,String userId) ;
	
	/**  
	 * 通过卡类型 和状态查询
	 * @param type
	 * @param state
	 * @param page
	 * @return
	 * @throws Exception  
	 * @author AndyBao
	 * @version V4.0, 2016年8月22日 下午2:02:54 
	 */
	public com.ybt.common.util.Page<SunCard> findSunCardByTypeAndState(String type,Integer state,com.ybt.common.util.Page<SunCard> page) throws Exception;

	
	/**  
	 * 今日领卡-T卡  （T卡 有效期为当天）
	 * @param accessToken
	 * @param userId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月22日 下午2:04:45 
	 */
	public Result<SunCard> sunTCardBandding(String accessToken,String userId) ;
	
	/**  
	 * 获取T卡  （T卡 有效期为当天）
	 * @param userId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月22日 下午2:04:45 
	 */
	public Result<SunCard> getSunTCard(String userId,Date today) ;
	/**  
	 * 获取有效卡  
	 * @param userId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月22日 下午2:04:45 
	 */
	public List<SunCard> findSunCardByValidAndUserId(String userid);
	
	/**  
	 * 获取有效卡  
	 * @param userId
	 * @return  Page<SunCard>
	 * @author lhq
	 */
	public com.ybt.common.util.Page<SunCard> findSunCardByStatusAndUserId(String userid,com.ybt.common.util.Page<SunCard> page);
	
	/**  
	 * 获取已使用卡  
	 * @param userId
	 * @return  com.ybt.common.util.Page<SunCard>
	 * @author AndyBao
	 * @version V4.0, 2016年8月22日 下午2:04:45 
	 */
	public com.ybt.common.util.Page<SunCard> findSunCardByUsedAndUserId(String userid,com.ybt.common.util.Page<SunCard> page);
	

	/**  
	 * 获取已使用卡  
	 * @param userId
	 * @return   List<SunCard>
	 * @author lhq
	 * @version  
	 */
	public List<SunCard> findSunCardByUsedAndUserId(String userid);
	
	/**  
	 * 根据userid查询卡总数
	 * @param userId
	 * @return   int
	 * @author jsj
	 * @version V4.0, 2016-11-2
	 */
	public int getCardCountByUserId(String userid);

}
