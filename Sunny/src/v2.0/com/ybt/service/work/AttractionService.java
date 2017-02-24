package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunAttraction;
import com.ybt.service.base.IBaseService;

/**   
 * 景点  管理
 * @author AndyBao  
 * @version 4.0, 2016年12月12日 下午2:22:29   
 */   
@Component
public interface AttractionService extends IBaseService<SunAttraction, String> {
	
	/**  
	 * 查询 所有城市
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2017年1月5日 上午10:46:16 
	 */
	public List<SunAttraction> getCityListByGroupByCity();
}
