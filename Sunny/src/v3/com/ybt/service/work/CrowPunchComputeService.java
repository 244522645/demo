package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.CrowPunch;
import com.ybt.service.base.IBaseService;

/**   
 * 打卡计算
 * @author AndyBao  
 * @version 4.0, 2016年12月12日 下午2:22:29   
 */   
@Component
public interface CrowPunchComputeService extends IBaseService<CrowPunch, String> {
	
	/**  
	 * 打卡 计算
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月18日 上午10:34:51 
	 */
	public Result<CrowPunch> punchCompute();
	
	/**  
	 * 打卡 计算
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年10月18日 上午10:34:51 
	 */
	public Result<CrowPunch> punchCompute(CrowPunch crowPunch);
	
	/**  
	 * 查询打卡
	 * @param top
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2017年2月14日 上午9:01:07 
	 */
	public List<CrowPunch> findCrowPunchByTop(int top);
}
