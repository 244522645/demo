package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunBlessDao;
import com.ybt.dao.work.SunLetterBlessDao;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunLetterBless;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunLetterBlessService;

@Component
public class SunLetterBlessServiceImpl extends BaseServiceImpl<SunLetterBless, String> implements SunLetterBlessService{
	
	@Autowired
	private SunLetterBlessDao letterBlessDao;
	
	@Autowired
	private SunBlessDao blessDao;
	
	@Override
	public BaseDao<SunLetterBless, String> getDao() {
		return letterBlessDao;
	}

	@Override
	public boolean saveLetterBless(SunLetter letterid, String[] orders) {
		// TODO Auto-generated method stub
		boolean f= false;
		for (String pid : orders) {
			SunBless p = blessDao.findOne(pid);
			if(p!=null){
				SunLetterBless lp= new SunLetterBless();
				lp.setLetterId(letterid);
				lp.setBless(p);
				lp.setCreateTime(new Date());
				letterBlessDao.save(lp);
				f=true;
			}
		}
		return f;
	}

	@Override
	public List<SunLetterBless> findByLetterId(String letterId) {
		// TODO Auto-generated method stub
		return letterBlessDao.findByLetterId(letterId);
	}


}
