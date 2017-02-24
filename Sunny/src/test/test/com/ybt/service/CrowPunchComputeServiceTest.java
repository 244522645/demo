package test.com.ybt.service;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.ybt.dao.work.CrowPunchDao;
import com.ybt.service.work.CrowPkMeService;
import com.ybt.service.work.CrowPunchComputeService;

import test.com.ybt.base.TestCase;

public class CrowPunchComputeServiceTest extends TestCase {
	
	@Autowired
	public CrowPunchComputeService computeService;
	@Autowired
	public CrowPkMeService crowPkMeService;
	@Autowired
	public CrowPunchDao crowPunchDao;
	
	/*@Test
	public void tests() throws Exception {
		List<CrowPunch> list = crowPunchDao.findCrowPunchByTop(100);
		
		for (CrowPunch crowPunch : list) {
			System.out.println(crowPunch.getIspass());
		}
	}*/
	@Test
	public void tests2() throws Exception {
		//crowPkMeService.createFristPkme("oWL5RuJoTvOo2ZyHwLfafhE3B3-M");
	//	PrintScreen4DJNativeSwingUtils.printUrlScreen2jpg("12.jpg", "http://1533v3393q.iask.in/Sunny/wechat/v3/crow/share?userid=oWL5RuJoTvOo2ZyHwLfafhE3B3-M","oWL5RuJoTvOo2ZyHwLfafhE3B3-M", 414, 736);

	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		long time = 0l;
		JUnitCore junit = new JUnitCore();
		Result r;
		r = junit.run(Request.method(CrowPunchComputeServiceTest.class, "tests2"));
		time += r.getRunTime();
		System.out.println("执行时间：" + r.getRunTime() + "毫秒。");
		System.out.println("执行数：" + r.getRunCount());
		System.out.println("忽略数：" + r.getIgnoreCount());
		System.out.println("失败数：" + r.getFailureCount());
			
	}
	
}
