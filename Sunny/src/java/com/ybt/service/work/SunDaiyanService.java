package com.ybt.service.work;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunDaiyan;
import com.ybt.model.work.SunDaiyanUser;
import com.ybt.model.work.SunQrcode;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.IBaseService;

@Component
public interface SunDaiyanService extends IBaseService<SunDaiyan, String> {
	
	/* 
	 * 创建代言人
	 * 1. 是否已创建 ，是：跳出，否 ：创建
	 * 2. 创建二维码  成功：继续
	 * 3. 是否送卡
	 * @param user
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:18:58 
	 */
	public Result<SunDaiyan> createDaiyan(SunWechatUser user); 
	
	/* 
	 * 分享 即获得一张阳光卡
	 * @param user
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:18:58 
	 */
	public Result<SunDaiyan> shareDaiyan(SunDaiyan daiyan,SunWechatUser user); 
	
	/* 
	 * 创建代言人粉丝
	 * 1. 通过二维码 进来 ，查看二维码是否有效
	 * 2. 通过二维码 查询代言
	 * 3. 此代言 是否以保存 ，否 则 创建
	 * 4. 成功 是否 送卡  送卡
	 * 5. 是否给代言人送卡
	 * @param user
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:18:58 
	 */
	public Result<SunDaiyanUser> createDaiyanUser(String userId,SunQrcode qr); 
   
   /**  
    * 查询代言
	 * @param userId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:19:19 
	 */
	public SunDaiyan getSunDaiyanByUser(String userId);
	
   /**  
    * 查询代言
	 * @param userId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:19:19 
	 */
	public SunDaiyan getSunDaiyanByQrcode(String qrcodeId);
	
}
