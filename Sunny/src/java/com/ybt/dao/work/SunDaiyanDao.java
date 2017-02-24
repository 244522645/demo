package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunDaiyan;

@Component
public interface SunDaiyanDao extends BaseDao<SunDaiyan, String> {
	
	/**  
	 * 通用户id 获取SunDaiyan
	 * @param userId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月4日 下午2:51:19 
	 */
	@Query("select a from SunDaiyan a where a.userId.id = ? and a.status=1")
	public List<SunDaiyan> findSunDaiyanByUser(String userId);
	
	@Query("select a from SunDaiyan a where a.qrcode.id = ? and a.status=1")
	public List<SunDaiyan> findSunDaiyanByQrcode(String qrcodeId);
	
}
