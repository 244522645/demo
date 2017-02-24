package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunQrcode;
import com.ybt.service.base.IBaseService;

/**   
 * 推广二维码 逻辑
 * @author AndyBao  
 * @version 4.0, 2016年11月3日 下午4:08:21   
 */   
@Component
public interface QrcodeService extends IBaseService<SunQrcode, String> {
	
	/**  
	 * 永久二维码
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:04:33 
	 */
	public List<SunQrcode> getQrcodeListByFinal();
	
	/**  临时二维码
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:04:57 
	 */
	public List<SunQrcode> getQrcodeListByTemp();
	
	
	/**  
	 * 通过value 查询
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:04:57 
	 */
	public SunQrcode getQrcodeByValue(Long value);
	
	/**  
	 * 通过id 查询
	 * @return  
	 * @author lhq
	 * @version
	 */
	public SunQrcode getQrcodeById(String id);
	
	
	/**  
	 * 生成临时二维码  过期需要重新生成
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:05:42 
	 */
	public Result<SunQrcode> createTempQrcode(String content, Long value, String name);
	
}
