package com.ybt.service.work;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunZyVoice;
import com.ybt.service.base.IBaseService;

/**   
 * 文件管理
 * @author AndyBao  
 * @version 4.0, 2016年8月2日 下午4:10:01   
 */   
public interface VoiceService  extends IBaseService<SunZyVoice,String> {
	
	/**  
	 * 上传语音
	 * @param bytes
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年8月3日 下午5:10:36 
	 */
	public Result<SunZyVoice> downVoiceByte(byte[] bytes,int longtime,String type);
}