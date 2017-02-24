package com.ybt.service.work.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SearchKeywordsDao;
import com.ybt.model.work.SunSearchKeywords;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SearchKeywordsService;

@Component
public class SearchKeywordsServiceImpl extends BaseServiceImpl<SunSearchKeywords,String> implements  SearchKeywordsService{
	
	@Autowired
	private SearchKeywordsDao searchKeywordsDao;
	
	public BaseDao<SunSearchKeywords, String> getDao() {
		return searchKeywordsDao;
	}

}
