package com.ybt.service.work.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.MemberTjDao;
import com.ybt.dao.work.OrderDao;
import com.ybt.dao.work.SunLetterDao;
import com.ybt.model.work.SunDdOrder;
import com.ybt.model.work.SunMemberTj;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.IMemberTjService;
import com.ybt.service.work.OrderService;
@Component
public class MemberTjServiceImpl  extends BaseServiceImpl<SunMemberTj,String> implements IMemberTjService{

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private MemberTjDao memberTj;
	public BaseDao<SunMemberTj, String> getDao() {
		return memberTj;
	}
	public SunMemberTj findById(String mid) {
		return memberTj.findById(mid);
	}
	@Override
	public SunMemberTj addReceive(final String f, int c) {
		if(f == null)
			return null;
		SunMemberTj tj= memberTj.findOne(f);
		if(tj == null)
			tj = new SunMemberTj();
		tj.setId(f);
		if(c > 0){
			tj.setReceive(c);
		}else{
			com.ybt.common.util.Page<SunDdOrder> page = new com.ybt.common.util.Page<SunDdOrder>();
			try {
				orderDao.findByHqlName(page, "getMyList1", new HashMap<String,Object>(){
					{
							{
								this.put("SENDEEID", f);
								//this.put("STATUS", new String[]{"20","100"});
								//this.put("STATUS","\"20\",\"100\"");
							}
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			tj.setReceive((int)page.getTotalCount());
			tj.setTree(tj.getReceive()+tj.getSend()/10);
		}
		
		tj=memberTj.save(tj);
		return tj;
	}
	@SuppressWarnings("serial")
	@Override
	public SunMemberTj addSend(final String f, int c) {
		if(f == null)
			return null;
		SunMemberTj tj= memberTj.findOne(f);
		if(tj == null)
			tj = new SunMemberTj();
		tj.setId(f);
		if(c > 0){
			tj.setSend(c);
		}else{
			com.ybt.common.util.Page<SunDdOrder> page = new com.ybt.common.util.Page<SunDdOrder>();
			try {
				 orderDao.findByHqlName(page, "getMyList2", new HashMap<String,Object>(){
						{
								{
									this.put("BUYERID", f);
									//this.put("STATUS","\"20\",\"100\"");
								}
						}
					});
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			tj.setSend((int)page.getTotalCount());
			
			tj.setTree(tj.getReceive()+tj.getSend()/10);
		}
		
		tj=memberTj.save(tj);
		return tj;
	}
	@Override
	public SunMemberTj addSunb( String f, int c) {
		if(f == null)
			return null;
		SunMemberTj tj= memberTj.findOne(f);
		if(tj == null)
			tj = new SunMemberTj();
		tj.setId(f);
		if(c > 0){
			tj.setSunb(c);
		}else{
			tj.setSunb(tj.getSunb()+1);
		}
		
		tj=memberTj.save(tj);
		return tj;
	}
	@Override
	public SunMemberTj addTougao(String f, int c) {
		if(f == null)
			return null;
		SunMemberTj tj= memberTj.findOne(f);
		if(tj == null)
			tj = new SunMemberTj();
		tj.setId(f);
		if(c > 0){
			tj.setTougao(c);
		}else{
			tj.setTougao(tj.getTougao()+1);
		}
		
		tj=memberTj.save(tj);
		return tj;
	}
	@Override
	public SunMemberTj addTree( String f, int c) {
		
		return null;
	}
	@Override
	public SunMemberTj getSunMemberTj(String mid) {
		Long s = memberTj.getMySendCount(mid);
		Long r = memberTj.getMyReceiveCount(mid);
		Long lts = memberTj.getMyLetterSendCount(mid);
		Long ltr = memberTj.getMyLetterReceiveCount(mid);
		Long c = memberTj.getMyCardCount(mid);
		SunMemberTj tj= new SunMemberTj();
		tj.setCard(c.intValue());
		tj.setSend(s.intValue());
		tj.setReceive(r.intValue());
		tj.setLetterReceive(ltr.intValue());
		tj.setLetterSend(lts.intValue());
		return tj;
	}

}
