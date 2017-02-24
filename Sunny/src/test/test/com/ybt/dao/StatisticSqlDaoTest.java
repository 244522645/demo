package test.com.ybt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

import test.com.ybt.base.TestCase;

import com.ybt.common.util.CustomPropertyConfigurer;
import com.ybt.dao.work.StatisticSqlDao;
import com.ybt.model.work.SunGlStatisticSql;

public class StatisticSqlDaoTest extends TestCase {
	
	@Resource
	public StatisticSqlDao statisticSqlDao;

	/**
	 * 商品报表
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void findGoodsReportByBusinessId() throws Exception {
		/*List<Map<String,Object>> list = (List<Map<String, Object>>) statisticSqlDao.findBySqlName4MapResult("select e.`name` as '点击者',a.business_id,count(a.business_id) as '点击次数',d.`name`  as '被击者',a.create_time"
+" from ybt_browse_records a,ybt_business e,(select b.`name`,c.id from ybt_business b,ybt_stock c where  b.id=c.business_id) d"
+"where a.business_id =e.id and a.good_id=d.id group by  a.business_id ORDER BY count(a.business_id) desc", null);
		*/
		
//		System.out.println(((Map<String,Object>)list.get(0)).toString());
//		for (Map<String, Object> map : list) {
//			System.out.println(map.toString());
//		}
		
		//获取 list 结果
		List<Object[]> list = (List<Object[]>) statisticSqlDao.findBySqlName("getSatisticSqlList", null);
		for (Object[] objects : list) {
			System.out.println("object："+objects[0]+" | "+ objects[1]);
		}
		//获取 List map 结果		
		List<Map<String,Object>> mapList = (List<Map<String,Object>>) statisticSqlDao.findBySqlName4MapResult("getSatisticSqlList", null);
		for (Map<String,Object> map : mapList)
			System.out.println("map："+map.get("type")+" | "+ map.get("title"));
		
		//获取 实体
		List<SunGlStatisticSql> entityList = statisticSqlDao.findEntityListBySqlName("getSatisticSqlListTest", new HashMap<String, Object>(){
			{
				this.put("orderBy", "sql_code");
			}
		});
		for (SunGlStatisticSql ybtStatisticSql : entityList) {
			System.out.println("实体："+ybtStatisticSql.getType()+" | "+ ybtStatisticSql.getTitle());
		}
	}
	
	@Test
	public void tests() throws Exception {
		 Map<String,String> properties = CustomPropertyConfigurer.getProperties();
	        
	        System.out.println(properties);
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		long time = 0l;
		JUnitCore junit = new JUnitCore();
		Result r;
		r = junit.run(Request.method(StatisticSqlDaoTest.class, "tests"));
		time += r.getRunTime();
		System.out.println("执行时间：" + r.getRunTime() + "毫秒。");
		System.out.println("执行数：" + r.getRunCount());
		System.out.println("忽略数：" + r.getIgnoreCount());
		System.out.println("失败数：" + r.getFailureCount());
			
	}
	
}
