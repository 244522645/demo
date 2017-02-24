package com.ybt.service.work.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.ImagesDao;
import com.ybt.dao.work.SunScGoodDao;
import com.ybt.dao.work.SunScPropertyValueDao;
import com.ybt.dao.work.SunZyGreetingsDao;
import com.ybt.model.work.SunGoods;
import com.ybt.model.work.SunGoodsPropertyValue;
import com.ybt.model.work.SunZyGreetings;
import com.ybt.model.work.SunZyImages;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.SunScGoodService;

@Component
@Transactional
public class SunScGoodServiceImpl extends BaseServiceImpl<SunGoods, String>  implements  SunScGoodService{

	@Autowired
	private SunScGoodDao sunScGoodDao;
	@Autowired
	private ImagesDao imagesDao;
	@Autowired
	private SunScPropertyValueDao sunScProValueDao;
	@Autowired
	private SunZyGreetingsDao sunZyGreetingsDao;
	
	@Override
	public BaseDao<SunGoods, String> getDao() {
		return sunScGoodDao;
	}
	
	@Override
	public BaseDao<SunGoodsPropertyValue, String> getProValueDao() {
		return sunScProValueDao;
	}
	
	@Override
	public BaseDao<SunZyGreetings, String> getGreetDao() {
		return sunZyGreetingsDao;
	}

	/**
	 *@name    根据商品种类，获取商品属性值列表
	 *@description 相关说明
	 *@time    创建时间:2016年6月22日上午10:53:57
	 *@param classId
	 *@return
	 *@throws Exception
	 * @author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> getScGoodPropertyValue(String classId) throws Exception{
		String sql ="select d.id,d.pro_value,d.im_id,d.data_type,d.father_id from sun_goods a,"
				+ "sun_goods_class b,sun_goods_property c,sun_goods_property_value d "
				+ "where a.class_id=b.id and b.id=c.class_id and c.id=d.type_id "
				+ "and a.class_id='"+classId+"'";
	
		 List<Map<String,Object>>goodProList= (List<Map<String,Object>>)sunScGoodDao.findBySql4MapResult(sql, null);
		 
		
		 List<Map<String,Object>>proList = new ArrayList<Map<String,Object>>();
		 Map<String,List<Map<String,Object>>> childrenMap = new HashMap<String,List<Map<String,Object>>>();
		 if(goodProList!=null&&goodProList.size()>0){
			 String imgIds="";
			 for(Map<String,Object>proMap:goodProList){
				 int dataType=(int)proMap.get("data_type");
				 if(dataType==1){
					 String imgId=(String)proMap.get("im_id");
					 imgIds+=","+imgId;
				 }
				 //将子属性放入主属性中（问候语为贺卡种类的子属性）
				 String fatherId=(String)proMap.get("father_id");
				 if(fatherId==null||"".equals(fatherId)){
					 proList.add(proMap);
				 }else{
					 List<Map<String,Object>>childrenList = (List<Map<String,Object>>)childrenMap.get(fatherId);
					 if(childrenList==null)childrenList=new ArrayList<Map<String,Object>>();
					 childrenList.add(proMap);
					 childrenMap.put(fatherId, childrenList);
				 }
			 }
			 //将问候语与贺卡种类对应
			 for(Map<String,Object>proMap:proList){
				String id=(String)proMap.get("id");
				proMap.put("childrenList", childrenMap.get(id));
			 }
			 
			 //查询图片,将图片放回属性值列表中
			if(imgIds.length()>0){
				imgIds=imgIds.substring(1);
				String[] ids= imgIds.split(",");
				List<SunZyImages> imgList = imagesDao.findByIds(ids);
				if(imgList!=null&&imgList.size()>0){
					Map<String,SunZyImages> imgMap = new HashMap<String,SunZyImages>();
					for(SunZyImages image:imgList){
						imgMap.put(image.getId(), image);
					}
					for(Map<String,Object>proMap:proList){
						String imgId=(String)proMap.get("img_id");
						proMap.put("proImage", imgMap.get(imgId));
					}
				}
			}
		 }
		return proList;
	}
	
	public List<SunZyGreetings> getGreetList(String tags){
		return null;
	}
}


