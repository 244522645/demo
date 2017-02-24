package com.ybt.service.work;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ybt.common.util.PropertyFilter;
import com.ybt.model.work.SunBill;
import com.ybt.model.work.SunZhDaybook;
import com.ybt.service.base.IBaseService;


@Component
public interface SunBillService extends IBaseService<SunBill, String>{

	List<SunZhDaybook> findBillByMid(String mid);
	
	/**
	 * 根据用户属性值获取
	 * @return User
	 */
	public Page<SunBill> findSunBillByProperty(List<PropertyFilter> filters,Pageable pageable);

}
