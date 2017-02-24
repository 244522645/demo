package com.ybt.service.work.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.SystemConstant;
import com.ybt.common.constant.Wechat;
import com.ybt.common.util.DateUtil;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.SunDaiyanDao;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunDaiyan;
import com.ybt.model.work.SunDaiyanUser;
import com.ybt.model.work.SunQrcode;
import com.ybt.model.work.SunWechatUser;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.IWechatService;
import com.ybt.service.work.QrcodeService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunDaiyanService;
import com.ybt.service.work.SunDaiyanUserService;

import wechat.api.MessageAPI;
import wechat.bean.templatemessage.TemplateMessage;
import wechat.bean.templatemessage.TemplateMessageItem;
import wechat.support.TokenManager;
import wechat.util.WXUtil;

@Component
public class SunDaiyanServiceImpl extends BaseServiceImpl<SunDaiyan, String> implements SunDaiyanService{
	
	@Autowired
	private SunDaiyanDao daiyanDao;
	@Autowired
	private QrcodeService qrcodeService;
	@Autowired
	private SunCardService cardService;
	@Autowired
	private SunDaiyanUserService daiyanUserService;
	@Autowired
	private IWechatService userService;
	@SuppressWarnings("rawtypes")
	@Autowired
	public HashMap constant;
	@Override
	public BaseDao<SunDaiyan, String> getDao() {
		return daiyanDao;
	}
	/* 
	 * 创建代言人
	 * 1. 是否已创建 ，是：跳出，否 ：创建
	 * 2. 创建二维码  成功：继续
	 * 3. 是否送卡
	 */
	@Override
	public Result<SunDaiyan> createDaiyan(SunWechatUser user) {
		String themes[] = {"theme1","theme2","theme3","theme4"};
		Random rd = new Random();
	     int x = rd.nextInt(4);
		// TODO Auto-generated method stub
		SunDaiyan daiyan = getSunDaiyanByUser(user.getId());
		if(daiyan!=null){
			return new  Result<SunDaiyan>("",daiyan);
		}
		daiyan = new SunDaiyan();
		Result<SunQrcode> qrcode = qrcodeService.createTempQrcode("", 0L, "daiyan_"+user.getId());
		if(qrcode.getState()==0)
			return new  Result<SunDaiyan>("二维码生成失败",null);
		daiyan.setUserId(user);
		daiyan.setCreateTime(new Date());
		daiyan.setQrcode(qrcode.getT());
		daiyan.setStatus(1);
		daiyan.setTheme(themes[x]);
		this.save(daiyan);
		
		/*if(SystemConstant.CODE_CARD_E_GIFT_DAIYAN_OPEN){
			Result<SunCard> result =cardService.giftSunECardForActivity(SystemConstant.CODE_CARD_E_GIFT_DAIYAN, SystemConstant.CODE_CARD_E_GIFT_DAIYAN_NUM, user.getId());
			if(result.getState()==1){
				LinkedHashMap<String, TemplateMessageItem> map = new LinkedHashMap<String, TemplateMessageItem>();
				map.put("first", new TemplateMessageItem("你已成功参加本次活动,并获得了一张阳光卡 \n","#ff6200"));
				map.put("keyword1", new TemplateMessageItem("阳光代言","#000000"));
				map.put("keyword2", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
				map.put("remark", new TemplateMessageItem("\n感谢你参加本次活动！\n 点击\"详情\" 前往个人中心查看阳光卡。","#000000"));
				TemplateMessage tm= new TemplateMessage();
				//tm.setTemplate_id("Vm3WBldQcnbCAwslg7d3NxgOgWY8EUY4t63AhmQoG-Y");
				tm.setTemplate_id(SystemConstant.TEMPLATE_HUODONG);
				tm.setTouser(user.getId());
				tm.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false));
				tm.setData(map);
				MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm);
			}
		}*/
		
		return new  Result<SunDaiyan>("",daiyan);
	}
	
	/* 
	 * 创建代言人粉丝
	 * 1. 通过二维码 进来 ，查看二维码是否有效
	 * 2. 通过二维码 查询代言
	 * 3. 此代言 是否以保存 ，否 则 创建
	 * 4. 成功 是否 送卡  送卡
	 * 5. 是否给代言人送卡
	 */
	@Override
	public Result<SunDaiyanUser> createDaiyanUser(String userId, SunQrcode qr) {
		
		SunWechatUser user = userService.findOne(userId);
		
		if(user == null ){
			return new Result<SunDaiyanUser>("",null);
		}
		//判断 是否 已有
		List<SunDaiyanUser> list = daiyanUserService.getSunDaiyanUserListByUserId(user.getId());
		if(list!=null&& !list.isEmpty()){
			return new Result<SunDaiyanUser>("",null);
		}
		
		//判断 是否 代言
		SunDaiyan daiyan = this.getSunDaiyanByQrcode(qr.getId());
		if(daiyan==null)
			return new Result<SunDaiyanUser>("",null);
		//判断 是否 本人
		if(userId.equals(daiyan.getUserId().getId()))
			return new Result<SunDaiyanUser>("",null);
		//保存 
		Result<SunDaiyanUser>  du =daiyanUserService.saveDaiyanUser(user, daiyan);
		if(du.getState()!=1){
			return du;
		}
		daiyan.setFlow(daiyan.getFlow()+1);
		this.save(daiyan);
		SunWechatUser dairen= daiyan.getUserId();
		//送礼
		if(SystemConstant.CODE_CARD_E_GIFT_DAIYAN_OPEN){
			
			//送礼  代言人 粉丝
			Result<SunCard> result =cardService.giftSunECardForActivity(SystemConstant.CODE_CARD_E_GIFT_DAIYAN_GUANZHU, SystemConstant.CODE_CARD_E_GIFT_DAIYAN_NUM, user.getId());
			if(result.getState()==1){
				if(SystemConstant.CODE_CARD_E_GIFT_DAIYAN_OPEN){
						// 创建回复
						LinkedHashMap<String, TemplateMessageItem> map2 = new LinkedHashMap<String, TemplateMessageItem>();
						map2.put("first", new TemplateMessageItem("通过朋友代言关注，获赠一张阳光卡 \n","#ff6200"));
						map2.put("keyword1", new TemplateMessageItem("阳光卡","#000000"));
						map2.put("keyword2", new TemplateMessageItem("1份","#000000"));
						map2.put("keyword3", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
						map2.put("remark", new TemplateMessageItem("点击\"详情\" 前往个人中心查看阳光卡。 ","#000000"));
						TemplateMessage tm2= new TemplateMessage();
						//tm.setTemplate_id("Vm3WBldQcnbCAwslg7d3NxgOgWY8EUY4t63AhmQoG-Y");
						tm2.setTemplate_id(SystemConstant.TEMPLATE_LIPIN);
						tm2.setTouser(user.getId());
						tm2.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false));
						tm2.setData(map2);
						MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm2);
					}
			}
			//送礼  代言人
			Result<SunCard> resultFans =cardService.giftSunECardForActivity(SystemConstant.CODE_CARD_E_GIFT_DAIYAN+"_"+user.getId(),SystemConstant.CODE_CARD_E_GIFT_DAIYAN_NUM, dairen.getId());
			if(resultFans.getState()==1){
				if(SystemConstant.CODE_CARD_E_GIFT_DAIYAN_OPEN){
					List<SunDaiyanUser> dlist = daiyanUserService.getSunDaiyanUserListByDanyanId(daiyan.getUserId().getId());
					if(dlist!=null&&dlist.size()>1){
					}else{
						// 创建回复
						LinkedHashMap<String, TemplateMessageItem> map2 = new LinkedHashMap<String, TemplateMessageItem>();
						map2.put("first", new TemplateMessageItem("你的朋友通过你的代言关注，获赠一张阳光卡 \n","#ff6200"));
						map2.put("keyword1", new TemplateMessageItem("阳光卡","#000000"));
						map2.put("keyword2", new TemplateMessageItem("1份","#000000"));
						map2.put("keyword3", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
						map2.put("remark", new TemplateMessageItem("点击\"详情\" 前往个人中心查看阳光卡。 ","#000000"));
						TemplateMessage tm2= new TemplateMessage();
						//tm.setTemplate_id("Vm3WBldQcnbCAwslg7d3NxgOgWY8EUY4t63AhmQoG-Y");
						tm2.setTemplate_id(SystemConstant.TEMPLATE_LIPIN);
						tm2.setTouser(dairen.getId());
						tm2.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false));
						tm2.setData(map2);
						MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm2);
					}
				}
			}
		}
		return du;
	}
	@Override
	public SunDaiyan getSunDaiyanByUser(String userId) {
		// TODO Auto-generated method stub
		SunDaiyan daiyan = null;
		List<SunDaiyan>  list= daiyanDao.findSunDaiyanByUser(userId);
		if(list!=null&&!list.isEmpty()){
			daiyan =list.get(0);
			// 如果过期  需要从新生成qrcode
			return daiyan;
		}
		return null;
	}
	@Override
	public SunDaiyan getSunDaiyanByQrcode(String qrcodeId) {
		// TODO Auto-generated method stub
		SunDaiyan daiyan = null;
		List<SunDaiyan>  list= daiyanDao.findSunDaiyanByQrcode(qrcodeId);
		if(list!=null&&!list.isEmpty()){
			daiyan =list.get(0);
			return daiyan;
		}
		return null;
	}
	@Override
	public Result<SunDaiyan> shareDaiyan(SunDaiyan daiyan, SunWechatUser user) {
		
		if(daiyan.getShare()==0){
			daiyan.setShare(1);
		}else{
			daiyan.setShare(daiyan.getShare()+1);
		}
		
		daiyan.setUpdateTime(new Date());
		this.save(daiyan);
		
		if(daiyan.getUserId().getId().equals(user.getId())){
			
			Result<SunCard> result =cardService.giftSunECardForActivity(SystemConstant.CODE_CARD_E_GIFT_DAIYAN, SystemConstant.CODE_CARD_E_GIFT_DAIYAN_NUM, user.getId());
			if(result.getState()==1){
				/*
				LinkedHashMap<String, TemplateMessageItem> map = new LinkedHashMap<String, TemplateMessageItem>();
				map.put("first", new TemplateMessageItem("你已成功参加本次活动,并获得了一张阳光卡 \n","#ff6200"));
				map.put("keyword1", new TemplateMessageItem("阳光代言","#000000"));
				map.put("keyword2", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
				map.put("remark", new TemplateMessageItem("\n感谢你参加本次活动！\n 点击\"详情\" 前往个人中心查看阳光卡。","#000000"));
				TemplateMessage tm= new TemplateMessage();
				//tm.setTemplate_id("Vm3WBldQcnbCAwslg7d3NxgOgWY8EUY4t63AhmQoG-Y");
				tm.setTemplate_id(SystemConstant.TEMPLATE_HUODONG);
				tm.setTouser(user.getId());
				tm.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false));
				tm.setData(map);
				MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm);
				*/
				return new  Result<SunDaiyan>(result.getT().getNumber(),daiyan);
			}
			
		}
		return new  Result<SunDaiyan>("",daiyan);
	}

}
