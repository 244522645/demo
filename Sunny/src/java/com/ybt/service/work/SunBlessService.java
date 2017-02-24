package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyImages;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.base.IBaseService;

@Component("sunBlessService")
public interface SunBlessService extends IBaseService<SunBless, String> {
	
	/**  
	 * 保存贺卡
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
	public Result<SunBless> saveBless(SunWechatUser user,String sender,String receiver,String bless,SunZyImages images,SunZyPhoto photo,SunDdOrder order);
	
	/**  通过订单查询
	 * @param orderId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月26日 上午11:45:12 
	 */
	public SunBless findByOrder(String orderId);
	
	
	/**
	 *@name   jpa qurey navice分页加载 我的所有订单
	 *@description 相关说明
	 *@time    创建时间:2016年6月26日下午4:32:35
	 *@param filters
	 *@param pageable
	 *@return
	 *@author   高艳花
	 * @throws Exception 
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 **/
	public com.ybt.common.util.Page<SunBless> getMyAllBlessList(String openid,com.ybt.common.util.Page<SunBless> page);
	 
	/**  查询 可用简信发送的贺卡
	 * @param userid
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月31日 下午5:20:33 
	 */
	public List<SunBless> getSunBlessByValidAndUserId(String userid);
	
	/**
	 *@name   我收到的日出明信片
	 *@description 相关说明
	 *@time    创建时间:2016-11-1
	 *@param openid ，page
	 *@return  page<SunBless>
	 *@author   jsj
	 **/
	public com.ybt.common.util.Page<SunBless> findMyReceivedBlessList(String openid,com.ybt.common.util.Page<SunBless> page);
	
	/**
	 *@name   我送出的日出明信片
	 *@description 相关说明
	 *@time    创建时间:2016-11-1
	 *@param openid ，page
	 *@return  page<SunBless>
	 *@author   jsj
	 **/
	public com.ybt.common.util.Page<SunBless> findMySendBlessList(String openid,com.ybt.common.util.Page<SunBless> page);
	
	/**
	 *@name   我未送出的日出明信片
	 *@description 相关说明
	 *@time    创建时间:2016-11-1
	 *@param openid ，page
	 *@return  page<SunBless>
	 *@author   jsj
	 **/
	public com.ybt.common.util.Page<SunBless> findMyNoSendBlessList(String openid,com.ybt.common.util.Page<SunBless> page);
	
	 /**
	 * 我收到的日出明信片总数
	 * @param mid
	 * @return  int
	 * @author jsj
	 * @version V4.0, 2016-11-1
	 */
	 public int findMyReceivedBlessCount(String mid);
	 
	 /**
	 * 我送出的日出明信片总数
	 *@param mid
	 * @return  int
	 * @author jsj
	 * @version V4.0, 2016-11-1 
	 */
	 public int findMySendBlessCount(String mid);
	 
	 /**
	 * 我未送出的日出明信片总数
	 * @param mid
	 * @return  int
	 * @author jsj
	 * @version V4.0, 2016-11-1
	 */
	 public int findMyNoSendBlessCount( String mid);
	 
	 
	 /**
	 * 所有推荐 订单
	 */
	 public List<SunBless> getAllBlessListByRec( int i, int m);

	 /**
	 * 通过二维码ID查询SunBless信息
	 * @param qrcodeId
	 * @return
	 */
	public SunBless getSunBlessByQrcodeId(String qrcodeId);
}
