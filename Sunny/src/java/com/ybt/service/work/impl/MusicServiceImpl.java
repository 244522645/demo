package com.ybt.service.work.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunZyMusicDao;
import com.ybt.model.work.SunZyMusic;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.MusicService;

@Component
public class MusicServiceImpl extends BaseServiceImpl<SunZyMusic, String> implements MusicService{
	
	@Autowired
	private SunZyMusicDao musicDao;
	@Override
	public BaseDao<SunZyMusic, String> getDao() {
		return musicDao;
	}


}
