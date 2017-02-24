package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.PhotoDao;
import com.ybt.dao.work.SunLetterPhotosDao;
import com.ybt.model.work.SunLetter;
import com.ybt.model.work.SunLetterPhotos;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunLetterPhotosService;

@Component
public class SunLetterPhotosServiceImpl extends BaseServiceImpl<SunLetterPhotos, String> implements SunLetterPhotosService{
	
	@Autowired
	private SunLetterPhotosDao letterPhotosDao;
	
	@Autowired
	private PhotoDao photoDao;
	
	@Override
	public BaseDao<SunLetterPhotos, String> getDao() {
		return letterPhotosDao;
	}

	@Override
	public boolean saveLetterPhotos(SunLetter letterid, String[] photos) {
		// TODO Auto-generated method stub
		boolean f= false;
		for (String pid : photos) {
			SunZyPhoto p = photoDao.findOne(pid);
			if(p!=null){
				SunLetterPhotos lp= new SunLetterPhotos();
				lp.setLetterId(letterid);
				lp.setPhotoId(p);
				lp.setCreateTime(new Date());
				letterPhotosDao.save(lp);
				f=true;
			}
		}
		return f;
	}

	@Override
	public List<SunLetterPhotos> findByLetterId(String letterId) {
		// TODO Auto-generated method stub
		return letterPhotosDao.findByLetterId(letterId);
	}


}
