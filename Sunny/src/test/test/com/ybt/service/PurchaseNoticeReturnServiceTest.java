package test.com.ybt.service;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

import test.com.ybt.base.TestCase;

public class PurchaseNoticeReturnServiceTest extends TestCase {

	/**
	 * 商品详情查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void putASNDataTest() throws Exception {
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		long time = 0l;
		JUnitCore junit = new JUnitCore();
		Result r;
		r = junit.run(Request.method(PurchaseNoticeReturnServiceTest.class, "putASNDataTest"));
		time += r.getRunTime();
		System.out.println("执行时间：" + r.getRunTime() + "毫秒。");
		System.out.println("执行数：" + r.getRunCount());
		System.out.println("忽略数：" + r.getIgnoreCount());
		System.out.println("失败数：" + r.getFailureCount());
			
	}
	
}
