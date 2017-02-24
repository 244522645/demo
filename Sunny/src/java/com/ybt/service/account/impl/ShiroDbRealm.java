/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.ybt.service.account.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.ybt.common.util.Encodes;
import com.ybt.model.admin.Role;
import com.ybt.model.admin.User;
import com.ybt.service.admin.UserService;
import com.ybt.service.admin.impl.UserServiceImpl;


public class ShiroDbRealm extends AuthorizingRealm {
	
	protected UserService userService;

	/**
	 * 认证     回调函数,登录时调用.
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.getUserByUserId(token.getUsername());
		if(user!=null){
			byte[] salt = Encodes.decodeHex(user.getSalt());
			return new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(salt), getName());
		}
		return null;
	}
	
	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(UserServiceImpl.HASH_ALGORITHM);
		matcher.setHashIterations(UserServiceImpl.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}
	
	
	
	/**
	 * 权限
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<String> list = new ArrayList<String>();
		User user = (User) principals.getPrimaryPrincipal();
		List<Role> roleList =  userService.getRoleByUserId(user.getUserId());
		for(Role role : roleList){
			list.add(role.getCode());
		}
		info.addRoles(list);
		return info;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
