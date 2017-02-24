package com.ybt.service.work;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunWXMessage;
import com.ybt.service.base.IBaseService;

@Component
public interface WXMessageService extends IBaseService<SunWXMessage, String> {
	
	/**  
	 * 保存
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月18日 上午10:34:51 
	 */
	public Result<SunWXMessage> sendMessage(SunWXMessage message);

}
