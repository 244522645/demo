package com.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.model.UserError;
import com.model.UserInfo;
import com.service.PosOrdersService;

@Service
public class PosOrdersServicesImpl extends BaseService implements PosOrdersService {

	@Override
	public List<Map<String, String>> fingByTime(String starttime, String endtime, String page, String pagesize,
			String parentID) {
		System.out.println(starttime+endtime+page+pagesize+parentID);
		return getPosOrdersMapper().	fingByTime(starttime, endtime, page, pagesize, parentID);

	}

	@Override
	public List<List<Map<String,String>>> countBytime(String starttime, String endtime, String parentID,String userid) {
		UserError userError=new UserError();
		userError.setErroruserid(userid);
		userError.setErrortime(new Date());
		userError.setErrortype(5);
		String serialnumber= utils.tenrodem();
		userError.setErrornum(serialnumber);
		getUserErrorMapper().insertSelective(userError);
		List<Map<String,String>> map=getPosOrdersMapper().countBytime(starttime, endtime, parentID);
		System.out.println(map.size());
		if(0>=map.size()){
			return null;
		}
		List<List<Map<String,String>>> list=new ArrayList<List<Map<String,String>>>();
		list.add(map);
		Map<String,String> sermap=new HashMap<String,String>();
		
		List<Map<String, String>> resultList = getPosCinemasMapper().selectPosid(parentID);
		Map<String, String> resultMap = resultList.get(0);
		String serialnumberString = resultMap.get("serialnumber");
		int serialnumberint = Integer.parseInt(serialnumberString);
		serialnumberint = serialnumberint + 1;
		DecimalFormat countFormat = new DecimalFormat("000000");
		getPosCinemasMapper().updateSerialnumber(resultMap.get("poscinemaid"), countFormat.format(serialnumberint) + "");
		sermap.put("baoxian", serialnumber);
		sermap.put("serialnumber", countFormat.format(serialnumberint));
		List<Map<String,String>> serlist=new ArrayList<Map<String,String>>();
		serlist.add(sermap);
		
		list.add(serlist);
		return list;
	}

	@Override
	public List<Map<String, String>> findByserialnum(String serialnum,String id,String posid,UserInfo userInfo) {
		List<Map<String,String>> list=getPosOrdersMapper().findByserialNum(serialnum,id,posid);
		if(list.size()>0){
			String posorderid=list.get(0).get("posorderid");
			UserError userError = new UserError();
			userError.setErrortime(new Date());
			userError.setErrornum(posorderid);
			userError.setErrortype(6);
			userError.setErroruserid(userInfo.getId());
			getUserErrorMapper().insertSelective(userError);
		}
		return list;
	}

}
