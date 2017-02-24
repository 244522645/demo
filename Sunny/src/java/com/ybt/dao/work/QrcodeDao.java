package com.ybt.dao.work;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.MemberPhotoCard;
import com.ybt.model.work.SunQrcode;

@Component
public interface QrcodeDao extends BaseDao<SunQrcode, String> {
	
	/**
	 * 查询 最大值
	 * */
	@Query("select Max(value) from SunQrcode a")
	public Long findMaxValue();
	
	/**  
	 * 通过值 查询SunQrcode
	 * @param value
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年11月4日 上午9:22:58 
	 */
	public List<SunQrcode> findQrcodeByValue(Long value) ;
	
	
	/**  
	 * 通过值 查询SunQrcode
	 * @param id
	 * @return  
	 * @author lhq
	 * @version  2017年1月16日 
	 */
	@Query(value="select * from sun_qrcode a where a.id=:id", nativeQuery = true)
	public SunQrcode findQrcodeById(String id) ;
}
