package test.com.ybt.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

import com.ybt.common.util.CustomPropertyConfigurer;
import com.ybt.dao.work.OrderDao;
import com.ybt.model.work.SunDdOrder;

import test.com.ybt.base.TestCase;

public class OrderDaoTest extends TestCase {
	
	@Resource
	public OrderDao orderDao;

	@Test
	public void tests() throws Exception {
		List<SunDdOrder> list=orderDao.getMyOrderList("oGrGWs91_m2P9TZXcC72hojTmhd8",1,10);
		for (SunDdOrder sunDdOrder : list) {
			System.out.println(sunDdOrder.getMessage());
		}
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		long time = 0l;
		JUnitCore junit = new JUnitCore();
		Result r;
		r = junit.run(Request.method(OrderDaoTest.class, "tests"));
		time += r.getRunTime();
		System.out.println("执行时间：" + r.getRunTime() + "毫秒。");
		System.out.println("执行数：" + r.getRunCount());
		System.out.println("忽略数：" + r.getIgnoreCount());
		System.out.println("失败数：" + r.getFailureCount());
			
	}
	
}
