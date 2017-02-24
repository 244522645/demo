package com.ybt.service.work;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunGoods;
import com.ybt.model.work.SunGoodsPropertyValue;
import com.ybt.model.work.SunZyGreetings;
import com.ybt.service.base.IBaseService;

@Component
public interface GoodsService extends IBaseService<SunGoods, String> {

	List<Map<String, Object>> getScGoodPropertyValue(String classId) throws Exception;

	BaseDao<SunGoodsPropertyValue, String> getProValueDao();

	BaseDao<SunZyGreetings, String> getGreetDao();
	
	/**  
	 * 通过 类型 查询 商品
	 * @param classId
	 * @return  
	 * @author AndyBao
	 * @version V4.0, 2016年12月7日 下午1:47:19 
	 */
	public List<SunGoods> findGoodListByClass(String classId);
}
