package com.ybt.service.work;

import org.springframework.stereotype.Service;

import com.ybt.common.bean.Result;
import com.ybt.model.work.CrowUserInfo;
import com.ybt.service.base.IBaseService;
/**
 * @author zwh
 *
 */
@Service
public interface CrowUserInfoService extends IBaseService<CrowUserInfo, String> {
	/*
	 * 根据用户ID查询数据
	 */
	public CrowUserInfo findByUserID(String userid);
	/*
	 * 根据用户ID查询数据
	 */
	public CrowUserInfo findCrowUserInfoByQrcodeId(String userid);
	/*
	 * 查询最大的用户编号
	 */
	public int findCrowUserByUserNumber();
	/**
	 * 
	 * @param userid
	 * @param addOrSubtract   加法或者是减法
	 * @param deduct    抵扣金额
	 * @return
	 */
	public CrowUserInfo updateUserBalance(String userid,String addOrSubtract , String deduct);
	
	/*
	 * 生成二维码
	 */
	public CrowUserInfo createTempQrcode(String userid);
	
	/*
	 * 生成图片 并发送用户
	 */
	public Result<String> sendUserImage(String userid, String url, byte[] data) ;
}
