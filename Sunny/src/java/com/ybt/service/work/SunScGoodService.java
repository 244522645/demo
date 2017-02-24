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
public interface SunScGoodService extends IBaseService<SunGoods, String> {

	List<Map<String, Object>> getScGoodPropertyValue(String classId) throws Exception;

	BaseDao<SunGoodsPropertyValue, String> getProValueDao();

	BaseDao<SunZyGreetings, String> getGreetDao();
}
