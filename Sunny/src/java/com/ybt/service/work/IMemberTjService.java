package com.ybt.service.work;

import org.springframework.stereotype.Component;

import com.ybt.model.work.SunMemberTj;
import com.ybt.service.base.IBaseService;

/**
 * @module  空间数据
 * @author bqy  @time 下午12:31:22
 */
@Component
public interface IMemberTjService extends IBaseService<SunMemberTj,String>{
	/**
	 * @module +收到
	 * @author bqy  @time 2015年11月7日 下午11:28:07
	 */
	public SunMemberTj addReceive(String f,int c);
	/**
	 * @module +送出
	 * @author bqy  @time 2015年11月7日 下午11:28:07
	 */
	public SunMemberTj addSend(String f,int c);
	
	/**
	 * @module +阳光B
	 * @author bqy  @time 2015年11月7日 下午11:28:07
	 */
	public SunMemberTj addSunb(String id,int c);
	
	
	/**
	 * @module +投稿
	 * @author bqy  @time 2015年11月7日 下午11:28:07
	 */
	public SunMemberTj addTougao(String id,int c);
	/**
	 * @module +树
	 * @author bqy  @time 2015年11月7日 下午11:28:07
	 */
	public SunMemberTj addTree(String f,int c);
	/**
	 * mid 获取会员信息
	 * @param mid
	 * @return SunMemberTj
	 * */
	public SunMemberTj findById(String mid);
	
	/**
	 * mid 获取会员信息
	 * @param mid
	 * @return SunMemberTj
	 * */
	public SunMemberTj getSunMemberTj(String mid);
}
