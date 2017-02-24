package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.CrowPkMe;
import com.ybt.service.base.IBaseService;

/**   
 * 个人挑战 和挑战详情
 * @author AndyBao  
 * @version 4.0, 2016年12月12日 下午2:22:29   
 */   
@Component
public interface CrowPkMeService extends IBaseService<CrowPkMe, String> {
	
	/**  
	 * @param userId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2017年2月9日 下午4:40:43 
	 */
	public List<CrowPkMe> findByUserId(String userId);
	
	public CrowPkMe findByUserIdAndPkTypeAndPkLevelAndPkStatus(String userId, int pktype, int pklevel,int status);
	public CrowPkMe findByUserIdAndPkTypeAndPkStatus(String userId, int pktype, int status);
	public CrowPkMe findByUserIdAndPkTypeAndPkLevel(String userId, int pktype, int pklevel);
	public List<CrowPkMe> findByAllUserAndPkTypeAndPkStatus( int pktype, int status, int top);
	/**  
	 * 个人模式  创建
	 * @param userId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2017年2月9日 下午4:40:43 
	 * @param orderId 
	 */
	public Result<CrowPkMe> createFristPkme(String userId, String orderId,Double money);
	/**  
	 * 个人模式  创建21天
	 * @param userId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2017年2月9日 下午4:40:43 
	 * @param orderId 
	 */
	public Result<CrowPkMe> createSecondPkme(String id, String orderId,Double money);

}
