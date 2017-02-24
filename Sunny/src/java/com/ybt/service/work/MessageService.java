package com.ybt.service.work;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunWXMessage;
import com.ybt.model.work.WxMessage;
import com.ybt.model.work.WxMessageImage;
import com.ybt.model.work.WxMessageText;
import com.ybt.model.work.WxMessageVoice;
import com.ybt.service.base.IBaseService;

@Component
public interface MessageService extends IBaseService<WxMessage, String> {
	
	/**  
	 * 保存
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月18日 上午10:34:51 
	 */
	public Result<WxMessage> saveMessage(WxMessage message);
	
	/**  
	 * 保存
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月18日 上午10:34:51 
	 */
	public Result<WxMessageText> saveMessageText(String userid,String message);
	
	/**  
	 * 保存
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月18日 上午10:34:51 
	 */
	public Result<WxMessageImage> saveMessageImage(String userid,String image);
	
	/**  
	 * 保存
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月18日 上午10:34:51 
	 */
	public Result<WxMessageVoice> saveMessageVoice(String userid,String voice);

}
