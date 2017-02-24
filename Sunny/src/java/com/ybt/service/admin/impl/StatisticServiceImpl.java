package com.ybt.service.admin.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.util.ExcelUtil;
import com.ybt.common.util.Page;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.StatisticSqlDao;
import com.ybt.model.work.SunGlStatisticSql;
import com.ybt.service.admin.StatisticService;
import com.ybt.service.base.impl.BaseServiceImpl;

@Component
public class StatisticServiceImpl extends BaseServiceImpl<SunGlStatisticSql, String> implements StatisticService{
	@Autowired
	private StatisticSqlDao sqlDao;

	@Override
	public BaseDao<SunGlStatisticSql, String> getDao() {
		return sqlDao;
	}
	/**
	 *@name    获取所有查询统计sql
	 *@description 相关说明
	 *@time    创建时间:2016年4月29日上午11:56:16
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public List<SunGlStatisticSql> getAllStatisticSql(){
		List<SunGlStatisticSql> sqlList = sqlDao.getAllStatisticSql();
		return sqlList;
	}
	
	/**
	 *@name    根据ID获取查询统计sql的信息
	 *@description 相关说明
	 *@time    创建时间:2016年4月29日上午11:56:40
	 *@param id
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public SunGlStatisticSql getStatisticSqlById(String id){
		SunGlStatisticSql sql = sqlDao.findOne(id);
		return sql;
	}
	
	/**
	 *@name    根据ID获取查询统计sql的信息
	 *@description 相关说明
	 *@time    创建时间:2016年4月29日上午11:56:40
	 *@param id
	 *@return
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	//@Override
	public List<Map<String,Object>> queryStatistic(String id,Page<Map<String,Object>> page){
		return null;
	}
	
	public SunGlStatisticSql getSqlBySqlCode(String code) {
		return sqlDao.getSqlBySqlCode(code);
	}

	@SuppressWarnings({ "unused", "unchecked" })
	public List<?> findBySqlName4MapResult(String sqlName,  Map<String, ?> map, Page<T> page,Map<String,String> header) throws Exception {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (null == sqlName)
			return list;
		if (null == map)
			map = new HashMap<String, Object>();
		
		 SunGlStatisticSql ybtStatistic = sqlDao.getSqlBySqlCode(sqlName);
		 if(ybtStatistic == null)
			return null;
		 
//		 设置header
		 String arrayEN[] =null;
		 if(ybtStatistic.getHeaderCHS() != null && ybtStatistic.getHeaderEN()!=null){
			 String arrayCHS[]=ybtStatistic.getHeaderCHS().split(",");
			  arrayEN=ybtStatistic.getHeaderEN().split(",");
//			 for (int i =  arrayEN.length-1; i >= 0; i--) {
//				header.put(arrayEN[i], arrayCHS[i]);
//			}
			 for (int i = 0; i < arrayEN.length; i++) {
					header.put(arrayEN[i], arrayCHS[i]);
				}
		 }
		 
		 String sql=ybtStatistic.getSqlBody();
		 
		 
		 StringBuffer bsql = new StringBuffer(sql);
		 
		 //排序
		 if(page.getOrderBy()!=null&& !page.getOrderBy().isEmpty()){
			 bsql.append(" order by " + page.getOrderBy() + " " + page.getOrder());
		 }else{
			 bsql.append(" order by " + arrayEN[arrayEN.length-1] + " desc");
		 }
		 //设置总数
		 page.setTotalCount(sqlDao.findIntegerBySql(bsql.toString(),map));
		 bsql.append(" limit :i,:e");
		 if(map == null)
			 map = new HashMap<String, Object>();
		 
		((Map<String, Object>)map).put("i", (page.getPageNo()-1)*page.getPageSize());
		((Map<String, Object>)map).put("e", page.getPageSize());
		return sqlDao.findBySql4MapResult(bsql.toString(), map);
	}

	@SuppressWarnings("unchecked")
	public void exportExcel(String sqlName, Map<String, ?> map,HttpServletResponse response) throws Exception {
		
		if (null == sqlName)
			return ;
		if (null == map)
			map = new HashMap<String, Object>();
		
		 SunGlStatisticSql ybtStatistic = sqlDao.getSqlBySqlCode(sqlName);
		 if(ybtStatistic == null)
			return ;
		 
//		 设置header
		 //列名
	    String columnNames[]= null; 
	    String keys[] =  null;
		 Map<String, Object> header = new HashMap<String, Object>();
		 if(ybtStatistic.getHeaderCHS() != null && ybtStatistic.getHeaderEN()!=null){
			 String arrayCHS[]=ybtStatistic.getHeaderCHS().split(",");
			 String arrayEN[]=ybtStatistic.getHeaderEN().split(",");
			 columnNames = arrayCHS;
			 keys = arrayEN;
			 for (int i = 0; i < arrayEN.length; i++) {
				header.put(arrayEN[i], arrayCHS[i]);
			}
		 }
		 
		 String sql=ybtStatistic.getSqlBody();
		
		 List<Map<String,Object>> listData = (List<Map<String, Object>>) sqlDao.findBySql4MapResult(sql, map);
		 
	   
		
		List<Map<String,Object>> exportlist = createExcelRecord(keys,listData);
	    
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    try {
	         ExcelUtil.createWorkBook(exportlist,keys,columnNames).write(os);
	    } catch (IOException e) {
	            e.printStackTrace();
	    }
	    
	    byte[] content = os.toByteArray();
	    InputStream is = new ByteArrayInputStream(content);
	    // 设置response参数，可以打开下载页面
	    response.reset();
	    response.setContentType("application/vnd.ms-excel;charset=utf-8");
	    response.setHeader("Content-Disposition", "attachment;filename="+ new String((ybtStatistic.getTitle() + ".xls").getBytes(), "iso-8859-1"));
	    ServletOutputStream out = response.getOutputStream();
	    BufferedInputStream bis = null;
	    BufferedOutputStream bos = null;
	    try {
	         bis = new BufferedInputStream(is);
	         bos = new BufferedOutputStream(out);
	         byte[] buff = new byte[2048];
	         int bytesRead;
	         // Simple read/write loop.
	         while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	             bos.write(buff, 0, bytesRead);
	         }
	    } catch (final IOException e) {
	         throw e;
	    } finally {
	        if (bis != null)
	             bis.close();
	        if (bos != null)
	             bos.close();
	    }
	}

	/**
	 *@description
	 *@time    创建时间:2016年5月5日下午2:05:05
	 *@author   zyb
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@SuppressWarnings("hiding")
	private <T> List<Map<String, Object>> createExcelRecord( String[] header,List<Map<String, Object>> resultPoints) {
	        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("sheetName", "sheet1");
	        listmap.add(map);
	        Map<String, Object> resultPoint=null;
	        Map<String, Object> newPoint=null;
	        for (int j = 0; j < resultPoints.size(); j++) {
	        	resultPoint= resultPoints.get(j);
	        	newPoint= new HashMap<String, Object>();
	        	for (String th : header) {
	        		if(resultPoint.containsKey(th))
	        			newPoint.put(th, resultPoint.get(th));
	        		else
	        			newPoint.put(th, "");
				}
	        	listmap.add(newPoint);
	        	/*
	    		Set<String> keyset = map.keySet();
	    		Iterator<String> it = keyset.iterator();
	    		while (it.hasNext()) {
	    			// 获取键
	    			String key = it.next();
	    			if(Arrays.binarySearch(header, key) <= 0)
	    				resultPoints.remove(key);
	    		}
	            listmap.add(resultPoint);*/
	        }
	        return listmap;
	    }

}
