package test.com.ybt.service;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.ybt.service.work.IMemberTjService;

import test.com.ybt.base.TestCase;

public class MemberTjServiceTest extends TestCase {
	
	@Autowired
	public IMemberTjService tjService;
	
	@Test
	public void test2() throws Exception {
		tjService.getSunMemberTj("oGrGWs91_m2P9TZXcC72hojTmhd8");
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		long time = 0l;
		JUnitCore junit = new JUnitCore();
		Result r;
		r = junit.run(Request.method(MemberTjServiceTest.class, "test2"));
		time += r.getRunTime();
		System.out.println("执行时间：" + r.getRunTime() + "毫秒。");
		System.out.println("执行数：" + r.getRunCount());
		System.out.println("忽略数：" + r.getIgnoreCount());
		System.out.println("失败数：" + r.getFailureCount());
			
	}
	
}
