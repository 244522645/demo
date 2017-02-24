package com.service;

import java.util.List;
import java.util.Map;

import com.model.UserInfo;

public interface PosOrdersService {
	public List<Map<String, String>> fingByTime(String starttime, String endtime, String page, String pagesize,String parentID);
	public List<List<Map<String,String>>> countBytime(String starttime, String endtime,String parentID,String userid) ;
	List<Map<String, String>> findByserialnum(String serialnum,String id,String posid,UserInfo userInfo);
}
