package com.ybt.dao.base.impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.hql.internal.ast.QueryTranslatorImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.Assert;

import com.ybt.common.exception.SqlIsNotFindException;
import com.ybt.common.util.Page;
import com.ybt.common.util.ReflectUtils;
import com.ybt.dao.base.BaseDao;

/**
 *
 * @project 云备胎
 * @package com.ybt.dao.base.impl
 * @file BaseDaoImp.java 
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description 注意：BaseDaoImp 不能写成  BaseDaoImpl 否则无法启动，可能与配置文件 repository-impl-postfix="Impl" 冲突
 * @module 模块: 模块名称
 * @author  Jiang 
 * @param <T>
 * @param <ID>
 * @version 1.3
 * @history AndyBao 创建时间:2016年5月10日下午5:22:25 增加sql 执行通用方法
 *
 */
@NoRepositoryBean
public   class BaseDaoImp<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseDao<T, ID> {

	 private EntityManager em;
	 
	 private Class<T> domainClass;

	 protected Map<String, String> sqlMap = new HashMap<String, String>();

	 protected Map<String, String> hqlMap = new HashMap<String, String>();
	 
	 public BaseDaoImp(Class<T> domainClass, EntityManager entityManager) {
	    super(domainClass, entityManager);
	    this.em = entityManager;
	    this.domainClass = domainClass;
	 }

	 public void deletion(ID id) {
		 String hql ="update "+domainClass.getName()+" a set a.deleted=1 where a.id =:id";
		 Query query = em.createQuery(hql);
		 query.setParameter("id", id);
		 query.executeUpdate();
	 }
	 
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		String hql = "select a from "+domainClass.getName()+" a  ";
		//判断是否有deleted字段，如果有加入到查询条件
		if(ReflectUtils.equalsParam(domainClass, "deleted")){ 
			hql += " where deleted != 1";
		}
		return em.createQuery(hql).getResultList();
	 }
//	-----------------sql  hql------------------------------------------------------
	

	/*-
	 * hql语句转换成sql语句
	 * @param hql
	 * @return sql
	 */
	public String hql2sql(String hql) throws Exception {
		Session session = (Session) em.getDelegate();
		SessionFactoryImpl sessionFactory = (SessionFactoryImpl) session.getSessionFactory();
		QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(hql, hql, Collections.EMPTY_MAP, sessionFactory);
		queryTranslator.compile(Collections.EMPTY_MAP, false);
		return queryTranslator.getSQLString();
	}
	/**
	 * 分页查询.
	 * 
	 * @throws SqlIsNotFindException
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findByHqlName(Page<T>  page, String sqlName, Map<String, ?> map) throws Exception {
		Assert.notNull(page, "pageData不能为空");
		if (null == sqlName)
			return page;
		String sql = this.getSqlByName(sqlName, false);
		Query query = em.createQuery(sql);
		page.setTotalCount(getTotalByHql(sql,map));
		
		//排序
		/* if(page.getOrderBy()!=null&& !page.getOrderBy().isEmpty()){
			 sql += " order by " + page.getOrderBy() + " " + page.getOrder();
		 }*/
		 //分页
		/* sql += " limit :i,:e";
		 if(map == null)
			 map = new HashMap<String, Object>();
		 ((Map<String, Object>)map).put("i", (page.getPageNo()-1)*page.getPageSize());
		 ((Map<String, Object>)map).put("e", page.getPageSize());
		 */
		 if (null != map){
			 setParameter(query,map);
			}
		 query.setFirstResult((page.getPageNo()-1)*page.getPageSize()).setMaxResults( page.getPageSize());
		
		 
		page.setResult(query.getResultList());
		return page;
	}
	/**
	 * 分页查询.
	 * 
	 * @throws SqlIsNotFindException
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findBySqlName(Page<T>  page, String sqlName, Map<String, ?> map) throws Exception {
		Assert.notNull(page, "pageData不能为空");
		if (null == sqlName)
			return page;
		String sql = this.getSqlByName(sqlName, false);
		Query query = em.createNativeQuery(sql, domainClass);
		page.setTotalCount(getTotalBySql(sql));
		
		//排序
		 if(page.getOrderBy()!=null&& !page.getOrderBy().isEmpty()){
			 sql += " order by " + page.getOrderBy() + " " + page.getOrder();
		 }
		 //分页
		 sql += " limit :i,:e";
		 if(map == null)
			 map = new HashMap<String, Object>();
		 ((Map<String, Object>)map).put("i", (page.getPageNo()-1)*page.getPageSize());
		 ((Map<String, Object>)map).put("e", page.getPageSize());
		
		 if (null != map){
			 setParameter(query,map);
			}
		 
		page.setResult(query.getResultList());
		return page;
	}
	
	/**
	 *@description  执行查询 sql文件中的sql语句，返回结果集
	 *@time    创建时间:2016年5月4日上午10:29:56
	 *@author   bqy
	 * @return 结果集
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public List<?> findBySql4MapResult(String sqlName, Map<String, ?> map) throws Exception {
		List<?> list = new ArrayList<Object>();
		if (null == sqlName)
			return list;
		Query query = em.createNativeQuery(sqlName);
		if (null != map){
			setParameter(query,map);
		}
		 query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		return query.getResultList();
	}
	
	/**
	 *@description  执行查询 sql文件中的sql语句，返回结果集
	 *@time    创建时间:2016年5月4日上午10:29:56
	 *@author   bqy
	 * @return 结果集
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public List<?> findBySqlName4MapResult(String sqlName, Map<String, ?> map) throws Exception {
		List<?> list = new ArrayList<Object>();
		if (null == sqlName)
			return list;
		Query query = em.createNativeQuery(getSqlByName(sqlName));
		if (null != map){
			setParameter(query,map);
		}
		 query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		return query.getResultList();
	}
	
	/*-
	 * 返回sql语句结果集的长度
	 * @return
	 */
	public int getTotalBySql(String sqls) throws Exception {

		StringBuffer sql = new StringBuffer("select count(*) as counter from (");
		sql.append(sqls);
		sql.append(") t ");
		return this.findIntegerBySql(sql.toString());
	}
	
	/*-
	 * 返回sql语句结果集的长度
	 * @return
	 */
	public int getTotalByHql(String sqls,Map<String,?> map) throws Exception {
		Query querySql = em.createQuery(sqls.toString());
		if(map!=null){
			setParameter(querySql,map);
		}
		List<?> list = querySql.getResultList();
		if(list!=null)
			return list.size();
		return 0;
	}


	public Integer findIntegerBySql(String sql,Map<String,?> map){
		
		StringBuffer  countSql = new StringBuffer();
		countSql.append("SELECT COUNT(1) AS COUNTER FROM ( ");
		countSql.append(sql);
		countSql.append(" ) COLIN ");
			
		Query querySql = em.createNativeQuery(countSql.toString());
		if(map!=null){
			setParameter(querySql,map);
		}
		List<?> list = querySql.getResultList();
		for (Object o : list) {
			if (null == o)
				continue;
			if (o instanceof Object[] && ((Object[]) o).length > 0 && NumberUtils.isNumber(((Object[]) o).toString()))
				return Integer.valueOf(((Object[]) o).toString());
			if (NumberUtils.isNumber(o.toString())) {
				return Integer.valueOf(o.toString());
			}
		}
		return null;
		
	}
	
	public Integer findIntegerBySqlName(String sqlName,Map<String,?> map) throws Exception{
		
		StringBuffer  countSql = new StringBuffer();
		countSql.append("SELECT COUNT(1) AS COUNTER FROM ( ");
		countSql.append(getSqlByName(sqlName));
		countSql.append(" ) COLIN ");
			
		Query querySql = em.createNativeQuery(countSql.toString());
		if(map!=null){
			setParameter(querySql,map);
		}
		List<?> list = querySql.getResultList();
		for (Object o : list) {
			if (null == o)
				continue;
			if (o instanceof Object[] && ((Object[]) o).length > 0 && NumberUtils.isNumber(((Object[]) o).toString()))
				return Integer.valueOf(((Object[]) o).toString());
			if (NumberUtils.isNumber(o.toString())) {
				return Integer.valueOf(o.toString());
			}
		}
		return null;
		
	}

	/**
	 * 查询sql语句返回的数字
	 * 
	 * @param sql
	 * @return 数字
	 * @throws Exception
	 */
	public Integer findIntegerBySql(String sql) throws Exception {
		Query querySql = em.createNativeQuery(sql.toString());
		List<?> list = querySql.getResultList();
		for (Object o : list) {
			if (null == o)
				continue;
			if (o instanceof Object[] && ((Object[]) o).length > 0 && NumberUtils.isNumber(((Object[]) o).toString()))
				return Integer.valueOf(((Object[]) o).toString());
			if (NumberUtils.isNumber(o.toString())) {
				return Integer.valueOf(o.toString());
			}
		}
		return null;
	}
	
	/**
	 * 执行查询 sql文件中的sql语句，返回结果集
	 * 
	 * @param sqlName
	 * @param map
	 * @return 结果集
	 * @throws SqlIsNotFindException
	 */
	public List<?> findBySqlName(String sqlName, Map<String, ?> map) throws Exception {
		List<?> list = new ArrayList<Object>();
		if (null == sqlName)
			return list;
		if (null == map)
			map = new HashMap<String, Object>();
		Query query = em.createNativeQuery(getSqlByName(sqlName));
		setParameter(query,map);
		return query.getResultList();
	}

	/**
	 * 执行查询 hql文件中的hql语句
	 * 
	 * @param hqlName
	 *            hql名称
	 * @param map
	 *            参数
	 * @return 结果集
	 * @throws Exception
	 */
	public List<?> findByHqlName(String hqlName, Map<String, ?> map) throws Exception {
		List<?> list = new ArrayList<Object>();
		if (null == hqlName)
			return list;
		if (null == map)
			map = new HashMap<String, Object>();
		Query query = em.createQuery(getSqlByName(hqlName));
		setParameter(query,map);
		return query.getResultList();
	}

	public Query setParameter(Query query,Map<String, ?> map){
		
		Set<String> set = map.keySet();
		for (String key : set) {
			Object value = map.get(key);
			if (value instanceof List)
				query.setParameter(key, (ArrayList<?>) value);
			else
				query.setParameter(key, value);
		}
		return query;
	}

	public String getSqlByName(String sqlName) throws Exception {
		return getSqlByName(sqlName, true);
	}

	public String getSqlByName(String sqlName, Boolean isSql) throws Exception {
		if (null == sqlName)
			return null;
		sqlName = sqlName.trim();
		String simpleName = this.domainClass.getSimpleName();
		String type = null;
		String ql = null;
		if (isSql) {
			type = "sql";
			ql = sqlMap.get(simpleName.concat(".").concat(sqlName));
		} else {
			type = "hql";
			ql = hqlMap.get(simpleName.concat(".").concat(sqlName));
		}
		if (null == ql) {
			URL url = this.domainClass.getResource("../../dao/sql/".concat(simpleName).concat(".").concat(type));
			File f = new File(url.getFile());
			if (!f.exists())
				throw new SqlIsNotFindException(type + "文件没有找到！");
			String sqls = "";
			try {
				sqls = FileUtils.readFileToString(f, "utf-8");
			} catch (IOException e) {
				throw new SqlIsNotFindException(type + "文件没有找到！");
			}
			String[] sqlarr = sqls.split(";");
			for (String s : sqlarr) {
				s = s.trim();
				if (s == "" || 0 == s.length())
					continue;
				if (!s.startsWith("-- "))
					continue;
				s = s.replaceFirst("-- ", "");
				s = s.replace("\r", "\n");
				String sn = s.substring(0, s.indexOf("\n")).trim();
				String sq = s.replaceFirst(sn, "").trim();
				hqlMap.put(simpleName.concat(".").concat(sn), sq);
				if (sn.equalsIgnoreCase(sqlName)) {
					ql = sq;
				}
			}
		}
		if (null == ql)
			throw new SqlIsNotFindException(type + "语句没有找到！");
		return ql;
	}

	/**
	 * 返回hql文件中的hql语句
	 * 
	 * @param hqlName
	 * @return hql语句
	 * @throws SqlIsNotFindException
	 */
	public String loadHql(String hqlName) throws Exception {
		return this.getSqlByName(hqlName, false);
	}

	/**
	 * 返回sql文件中的sql语句
	 * 
	 * @param sqlName
	 * @return sql语句
	 * @throws SqlIsNotFindException
	 */
	public String loadSql(String sqlName) throws Exception {
		return this.getSqlByName(sqlName);
	}
	
	/**
	 * 执行sql
	 * 
	 * @param sql
	 */
	public int executeSql(StringBuffer sql) throws Exception {
		if (null == sql || sql.equals(""))
			return 0;
		Query q = em.createNativeQuery(sql.toString());
		System.err.println("[" + System.nanoTime() + "]==" + sql);
		return (q.executeUpdate());
	}

	/**
	 * 執行sql返回結果集
	 * 
	 * @param sql
	 * @return 結果集
	 */
	public List<?> executeSql(String sql) throws Exception {
		if (null == sql || sql.equals(""))
			return null;
		Query q = em.createNativeQuery(sql.toString());
		System.err.println("[" + System.nanoTime() + "]==" + sql);
		return (q.getResultList());
	}
	
	/**
	 * 按SQL查询唯一对象.
	 * 
	 * @param sqlName
	 *            sql名称
	 * @param param
	 *            参数
	 * @return 唯一对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public T findEntityBySqlName(String sqlName, Map<String, Object> param) throws Exception {
		Query q = em.createNativeQuery(this.getSqlByName(sqlName),this.domainClass);
		setParameter(q,param);
		List<T> list =  q.getResultList();
		if (null != list && list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 按SQL查询实体列表.
	 * 
	 * @param sqlName
	 *            sql名称
	 * 
	 * @param param
	 *            参数
	 * @return 实体列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<T> findEntityListBySqlName(String sqlName, Map<String, Object> param) throws Exception {
		Query q = em.createNativeQuery(this.getSqlByName(sqlName),this.domainClass);
		setParameter(q,param);
		List<T> list =  q.getResultList();
		return list;
	}

}
