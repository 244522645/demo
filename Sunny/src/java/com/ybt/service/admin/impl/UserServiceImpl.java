package com.ybt.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.ybt.common.util.Digests;
import com.ybt.common.util.Encodes;
import com.ybt.common.util.PropertyFilter;
import com.ybt.dao.admin.UserDao;
import com.ybt.model.admin.Role;
import com.ybt.model.admin.User;
import com.ybt.service.admin.UserService;

@Component
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	public static final String HASH_ALGORITHM = "SHA-1";
	private static final int SALT_SIZE = 8;
	public static final int HASH_INTERATIONS = 1024;
	
	
	public boolean addUser(User user) {
		try{
			user.setDeleted("0");
			user.setDisable("0");
			entryptPassword(user);
			userDao.save(user);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 生成安全的密码
	 */
	private boolean getPassword(User user,String password) {
		byte[] hashPassword = Digests.sha1(password.getBytes(), Encodes.decodeHex(user.getSalt()), HASH_INTERATIONS);
		if(Encodes.encodeHex(hashPassword).equals(user.getPassword())){
			return true;
		}else{
			return false;
		}
	}

	public boolean batchAddUser(List<User> user) {
		try{
			userDao.save(user);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean batchDelUser(List<String> userId) {
		try{
			List<User> users = (List<User>) userDao.findAll(userId);
			for(User user : users){
				user.setDeleted("1");
			}
			userDao.save(users);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delUser(User user) {
		try{
			userDao.delete(user);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delUserByUserId(String userId) {
		try{
			User user = userDao.getUserByUserId(userId);
			user.setDeleted("1");
			userDao.save(user);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Page<User> findUserByProperty(final List<PropertyFilter> filters,Pageable pageable) {
		Page<User>  page = userDao.findAll(
		new Specification<User>(){
			@SuppressWarnings("incomplete-switch")
			public Predicate toPredicate(Root<User> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				 List<Predicate> list = new ArrayList<Predicate>();  
				 	for(PropertyFilter pf:filters){
				 		switch (pf.getMatchType()) {
						case EQ:
						    list.add(cb.equal(root.get(pf.getPropertyName()).as(pf.getPropertyClass()), pf.getMatchValue()));  
							break;
						case LIKE:
							list.add(cb.like(root.get(pf.getPropertyName()).as(String.class), "%"+pf.getMatchValue()+"%")); 
							break;
						}
				 	}
				    list.add(cb.equal(root.get("deleted").as(String.class), "0")); 
				    list.add(cb.equal(root.get("disable").as(String.class), "0")); 
				    Predicate[] p = new Predicate[list.size()];  
				    return cb.and(list.toArray(p));  
			}}, pageable); 
		return page;
		
	}
	
	

	public List<Role> getRoleByUserId(String userId) {
		return userDao.getRoleByUserId(userId);
	}

	public User getUserByUserId(String userId) {
		return userDao.getUserByUserId(userId);
	}

	public User getUserByUserIdAndPassword(String userId,String password){
		User user=userDao.getUserByUserId(userId);
		if(getPassword(user,password)){
			return user;
		}else{
			return null;
		}
	}

	public User modifyUser(User user) {
		entryptPassword(user);
		userDao.save(user);
		return userDao.getUserByUserId(user.getUserId());
	}



}
