package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunLetterOrders;
import com.ybt.service.base.IBaseService;

@Component
public interface SunLetterOrdersService extends IBaseService<SunLetterOrders, String> {
	
	/**  
	 * 保存信件 -明星片
	 * @param letterid
	 * @param orders
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月19日 下午2:33:56 
	 */
	public boolean saveLetterOrders(SunLetter letterid,String[] orders );
	
	
	/**  
	 * @param letterId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月20日 上午10:07:15 
	 */
	public List<SunLetterOrders> findByLetterId(String letterId);
}
