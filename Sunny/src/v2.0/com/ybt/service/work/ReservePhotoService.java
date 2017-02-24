package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunWechatUser;
import com.ybt.model.work.SunZyPhoto;

/**   
 * 预订照片 管理
 * @author AndyBao  
 * @version 4.0, 2016年12月12日 下午2:22:29   
 */   
@Component("reservePhotoService")
public interface ReservePhotoService extends PhotoService{
	/**  
	 * 查询 城市 照片
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2017年1月5日 上午10:46:16 
	 */
	public List<SunZyPhoto> getPhotoListByCitys(String citys,String date);
	
	/**  
	 * 查询 当天其他 照片
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2017年1月5日 上午10:46:16 
	 */
	public List<SunZyPhoto> getPhotoListByOther(String date);
	
	
	
	/**  
	 * 通过订单ID查询 城市 照片
	 * @return  
	 * @author lhq
	 * @version V4.0, 2017年1月5日 上午10:46:16 
	 */
	public Result<List<SunZyPhoto>> getPhotoListByOrderId(String orderId);
	
	
}
