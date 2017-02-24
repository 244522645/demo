package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunLetterPhotos;
import com.ybt.service.base.IBaseService;

@Component
public interface SunLetterPhotosService extends IBaseService<SunLetterPhotos, String> {
	
	/**  
	 * 保存信件照片
	 * @param letterid
	 * @param photos
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月19日 下午2:33:56 
	 */
	public boolean saveLetterPhotos(SunLetter letterid,String[] photos );
	
	
	/**  
	 * @param letterId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月20日 上午10:07:15 
	 */
	public List<SunLetterPhotos> findByLetterId(String letterId);
}
