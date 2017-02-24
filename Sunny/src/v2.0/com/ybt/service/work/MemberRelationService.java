package com.ybt.service.work;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.MemberRelationBean;
import com.ybt.common.bean.Result;
import com.ybt.model.work.MemberRelation;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.IBaseService;

/**   
 * 关系管理
 * @author AndyBao  
 * @version 4.0, 2016年12月12日 下午2:22:29   
 */   
@Component
public interface MemberRelationService extends IBaseService<MemberRelation, String> {

	
	/**
	 * 通过userId查找所有关系列表
	 * @param userId  用户id
	 * @return  
	 */
	public List<MemberRelationBean> findMemberRelationsById(String userId); 
	
	/**
	 * 新增\修改关系
	 * @param userId  用户id
	 * @return  
	 */
	public Result<MemberRelation> saveRelation(SunWechatUser user,String id ,String name,String birthday,String mobileNo,String relation);
	
	/**
	 * 根据ID得到一个memberRelation
	 * @param id  
	 * @return  
	 */
	public MemberRelationBean findOneById(String id);
	
	/**
	 * 新增\修改关系
	 * @param 主键id
	 * @return  
	 */
	public String deleteRelation(String id);
}
