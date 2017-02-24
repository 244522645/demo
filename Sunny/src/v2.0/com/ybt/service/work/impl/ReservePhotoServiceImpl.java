package com.ybt.service.work.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import wechat.api.MessageAPI;
import wechat.bean.templatemessage.TemplateMessage;
import wechat.bean.templatemessage.TemplateMessageItem;
import wechat.support.TokenManager;
import wechat.util.WXUtil;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.SystemConstant;
import com.ybt.common.constant.Wechat;
import com.ybt.common.util.DateUtil;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.work.ReserveBlessService;
import com.ybt.service.work.ReservePhotoService;
import com.ybt.service.work.SunCardService;

@Component("reservePhotoService")
public class ReservePhotoServiceImpl  extends PhotoServiceImpl implements ReservePhotoService{
	
	@PersistenceContext  
	private EntityManager em;
	@SuppressWarnings("rawtypes")
	@Autowired
	private HashMap constant;
	@Autowired
	private SunCardService cardService; 
	@Autowired
	@Qualifier("reserveBlessService")
	private ReserveBlessService reserveBlessService;
	@SuppressWarnings("unchecked")
	@Override
	public List<SunZyPhoto> getPhotoListByCitys(String citys,String date) {
		
		 if(citys==null)return null;
		 if(citys.equals(""))return null;
		 String[] cityArray= citys.split(",");
		 String citysql="";
		 for (String str : cityArray) {
			 citysql=citysql +" or a.city like '%"+str+"%'";
		 }
		 
		 if(citysql.equals("")){
			 return null;
		 }
		 
		 citysql=citysql.trim().substring(2);
		 citysql = " ( "+citysql+" ) ";
		 String sql = "SELECT * FROM sun_zy_photo a where a.deleted=0 and  shooting_time like '%"+date+"%'  and  "+citysql+"  order by shooting_time desc,city;";
		  
		 System.out.println(sql);
		 
		 Query query = em.createNativeQuery(sql,SunZyPhoto.class);
		 
				 
		return (List<SunZyPhoto>)query.getResultList();
	}

	@Override
	public List<SunZyPhoto> getPhotoListByOther(String date) {
		 
		 String sql = "SELECT a FROM SunZyPhoto a where a.deleted=0 and  date_format(a.shootingTime,'yyyy-MM-dd') = :shootingTime  order by shootingTime desc,city";
		  
		 System.out.println(sql);
		 
		 Query query = em.createQuery(sql,SunZyPhoto.class);
		 query.setParameter("shootingTime", date);
		 
				 
		return (List<SunZyPhoto>)query.getResultList();
	}

	@Override
	public Result<List<SunZyPhoto>> getPhotoListByOrderId(String blessId) {
		
		
	 SunBless  bless =	reserveBlessService.findOne(blessId);
	 if(null != bless){
		 String citys = bless.getCitys();
		 String userId = bless.getUserId().getId();
		 String toUserId = bless.getToUserId().getId();
		 String toUserBirthday = DateUtil.getDateFormat(bless.getBirthday(), "yyyy-MM-dd");
		 
	     String[] cityArray= citys.split(",");
	     String citysql="";
	     for (String str : cityArray) {
	         citysql=citysql +" or a.city like '%"+str+"%'";
	     }
	     if(citysql.equals("")){
	    	 return new Result<List<SunZyPhoto>>("没有查询到预选城市信息，请联系商家", null);
	     }

	     citysql=citysql.trim().substring(2);
	     citysql = " ( "+citysql+" ) ";
	     String sql_5 = "SELECT * FROM sun_zy_photo a where a.deleted=0 and  shooting_time like '%"+toUserBirthday+"%'  and  "+citysql+"  order by shooting_time desc,city;";
	     Query query_5 = em.createNativeQuery(sql_5,SunZyPhoto.class);
	     List<SunZyPhoto> resultList_5 = query_5.getResultList();
	     //已选择的五个城市没有照片，就随机从其他城市挑选10张照片
	     if(resultList_5.size() <= 0){
	         String sql_10 = "SELECT * FROM sun_zy_photo a where a.deleted=0 and  shooting_time like '%"+toUserBirthday+"%'   order by shooting_time desc,city limit 10;";
	         Query query_10 = em.createNativeQuery(sql_10,SunZyPhoto.class);
	         List<SunZyPhoto> resultList_10 = query_10.getResultList();
	         //其他城市也没有照片的话，就送卡
	         if(resultList_10.size() <= 0){
	        	 //送给预定方
	             Result<SunCard> result_user =cardService.giftSunECardForActivity(
	            		 SystemConstant.CODE_CARD_E_GIFT_NOPHOTO_CODE+blessId,
	            		 1, 
	            		 userId);
	                if(result_user.getState()==1){
	                    if(SystemConstant.CODE_CARD_E_GIFT_20170101_OPEN){
	                            // 创建回复
	                            LinkedHashMap<String, TemplateMessageItem> map2 = new LinkedHashMap<String, TemplateMessageItem>();
	                            map2.put("first", new TemplateMessageItem("恭喜您，获赠一张阳光卡 \n","#ff6200"));
	                            map2.put("keyword1", new TemplateMessageItem("阳光卡","#000000"));
	                            map2.put("keyword2", new TemplateMessageItem("1份","#000000"));
	                            map2.put("keyword3", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
	                            map2.put("remark", new TemplateMessageItem("点击\"详情\" 前往个人中心查看阳光卡。 ","#000000"));
	                            TemplateMessage tm2= new TemplateMessage();
	                            //tm2.setTemplate_id("Vm3WBldQcnbCAwslg7d3NxgOgWY8EUY4t63AhmQoG-Y");
	                            tm2.setTemplate_id(SystemConstant.TEMPLATE_LIPIN);
	                            tm2.setTouser(userId);
	                            tm2.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false));
	                            tm2.setData(map2);
	                            MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm2);
	                        }
	                }
	                
	              //送给接受方toUserId
	             Result<SunCard> result_toUser =cardService.giftSunECardForActivity(
	            		 SystemConstant.CODE_CARD_E_GIFT_NOPHOTO_CODE+blessId,
	            		 1,
	            		 toUserId);
	                if(result_toUser.getState()==1){
	                    if(SystemConstant.CODE_CARD_E_GIFT_20170101_OPEN){
	                            // 创建回复
	                            LinkedHashMap<String, TemplateMessageItem> map_toUser = new LinkedHashMap<String, TemplateMessageItem>();
	                            map_toUser.put("first", new TemplateMessageItem("恭喜您，获赠一张阳光卡 \n","#ff6200"));
	                            map_toUser.put("keyword1", new TemplateMessageItem("阳光卡","#000000"));
	                            map_toUser.put("keyword2", new TemplateMessageItem("1份","#000000"));
	                            map_toUser.put("keyword3", new TemplateMessageItem(""+DateUtil.getDateFormat(new Date(), "yyyy年MM月dd号"),"#000000"));
	                            map_toUser.put("remark", new TemplateMessageItem("点击\"详情\" 前往个人中心查看阳光卡。 ","#000000"));
	                            TemplateMessage tm_toUser= new TemplateMessage();
	                            //tm2.setTemplate_id("Vm3WBldQcnbCAwslg7d3NxgOgWY8EUY4t63AhmQoG-Y");
	                            tm_toUser.setTemplate_id(SystemConstant.TEMPLATE_LIPIN);
	                            tm_toUser.setTouser(toUserId);
	                            tm_toUser.setUrl(WXUtil.getOAuthUrl(constant.get("domainName")+"/wechat/me", false));
	                            tm_toUser.setData(map_toUser);
	                            MessageAPI.messageTemplateSend(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), tm_toUser);
	                        }
	                }
	                return new Result<List<SunZyPhoto>>("noPhoto", null);
	         }else{
		    	 return new Result<List<SunZyPhoto>>("10", resultList_10);
		     }
	     }else{
	    	 return new Result<List<SunZyPhoto>>("5", resultList_5);
	     }
	 }else{
		 return new Result<List<SunZyPhoto>>("没有查询到订单信息，请联系商家", null);
	 }
	}
	
}
