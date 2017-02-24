package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunTempRecord;
import com.ybt.service.base.IBaseService;
@Component
public interface SunTempRecordService extends IBaseService<SunTempRecord, String> {

	
	/**  
	 * 查询纪录
	 * @param userId
	 * @param type
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月12日 下午3:22:09 
	 */
	public List<SunTempRecord> findByUserIdAndType(String userId,String type);

}
