package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.UserError;

public interface UserErrorMapper {
	int deleteByPrimaryKey(Integer errorid);

	int insert(UserError record);

	int insertSelective(UserError record);

	UserError selectByPrimaryKey(Integer errorid);

	int updateByPrimaryKeySelective(UserError record);

	int updateByPrimaryKey(UserError record);

	/*
	 * 根据用户的id和错误类型查询当前用户下面的登录错误
	 */
	List<UserError> findByID(String userID, String errorType, String time);

	/*
	 * 根据用户的手机号，删除指定类型的错误。
	 */
	int deleteByUsername(String userID, Integer errorType);

	/*
	 * 查询用户当前错误次数
	 */
	int countByerror(String erroruserid, String errortype, String errortime);

	/*
	 * 查询当前用户当前卡的输入错误次数
	 */
	int countByCard(@Param("userID") String userID, @Param("errorType") String errorType, @Param("time") String time,
			@Param("errornum") String errornum);

	/*
	 * 用户登录成功后把修改错误的登陆记录改为正常
	 */
	int updateUserError(String startTime, String endTime, String userID);

}