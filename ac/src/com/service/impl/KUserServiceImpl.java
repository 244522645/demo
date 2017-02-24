package com.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.model.KUser;
import com.service.KUserService;
@Service
public class KUserServiceImpl extends BaseService implements KUserService  {
	
	
	
	public KUser findByName(String username) {
		// TODO Auto-generated method stub
		getkUserMapper().findByName(username);
		return getkUserMapper().findByName(username);
	}


	@Override
	public void updatepassword(String id, String password) {
		getkUserMapper().updatepassword(id, password);	
		}


	@Override
	public List<Map<String,String>> selectUser() {
		return getkUserMapper().selectUser();
	}


	@Override
	public KUser findByUser(HttpServletRequest request) {
		KUser user=(KUser)request.getSession().getAttribute("username");
		return getkUserMapper().findByUser(user.getId());
	}

}
