package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunDaiyan;
import com.ybt.model.work.SunDaiyanUser;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.IBaseService;

@Component
public interface SunDaiyanUserService extends IBaseService<SunDaiyanUser, String> {
	
	/**  
	 * 通过代言id 查询次代言的所有用户
	 * @param daiyanId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:45:17 
	 */
	public List<SunDaiyanUser> getSunDaiyanUserListByDanyanId(String daiyanId);
	
	/**  
	 * 通过userId 查询代言
	 * @param daiyanId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:45:17 
	 */
	public List<SunDaiyanUser> getSunDaiyanUserListByUserId(String userId);
	
	/**  
	 * 通过代言id 查询次代言的所有用户
	 * @param daiyanId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:45:17 
	 */
	public SunDaiyanUser getSunDaiyanUserByDanyanIdAndUserId(String daiyanId,String userId);

	
	/**  
	 * 保存 代言流量
	 * @param user
	 * @param daiyan
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月3日 下午4:44:39 
	 */
	public Result<SunDaiyanUser> saveDaiyanUser(SunWechatUser user,SunDaiyan daiyan);
	
}
