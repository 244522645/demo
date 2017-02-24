package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.OrderDao;
import com.ybt.dao.work.SunLetterOrdersDao;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunLetterOrders;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunLetterOrdersService;

@Component
public class SunLetterOrdersServiceImpl extends BaseServiceImpl<SunLetterOrders, String> implements SunLetterOrdersService{
	
	@Autowired
	private SunLetterOrdersDao letterOrdersDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public BaseDao<SunLetterOrders, String> getDao() {
		return letterOrdersDao;
	}

	@Override
	public boolean saveLetterOrders(SunLetter letterid, String[] orders) {
		// TODO Auto-generated method stub
		boolean f= false;
		for (String pid : orders) {
			SunDdOrder p = orderDao.findOne(pid);
			if(p!=null){
				SunLetterOrders lp= new SunLetterOrders();
				lp.setLetterId(letterid);
				lp.setOrder(p);
				lp.setCreateTime(new Date());
				letterOrdersDao.save(lp);
				f=true;
			}
		}
		return f;
	}

	@Override
	public List<SunLetterOrders> findByLetterId(String letterId) {
		// TODO Auto-generated method stub
		return letterOrdersDao.findByLetterId(letterId);
	}


}
