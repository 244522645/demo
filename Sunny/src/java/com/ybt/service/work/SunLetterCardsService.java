package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunLetterCards;
import com.ybt.service.base.IBaseService;

@Component
public interface SunLetterCardsService extends IBaseService<SunLetterCards, String> {
	
	/**  
	 * 保存信件 -卡
	 * @param letterid
	 * @param photos
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月19日 下午2:33:56 
	 */
	public boolean saveLetterCards(SunLetter letterid,String[] photos );
	
	
	/**  
	 * @param letterId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月20日 上午10:07:15 
	 */
	public List<SunLetterCards> findByLetterId(String letterId);
}
