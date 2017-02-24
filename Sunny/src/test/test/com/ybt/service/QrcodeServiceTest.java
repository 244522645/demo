package test.com.ybt.service;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.ybt.model.work.SunQrcode;
import com.ybt.service.work.QrcodeService;

import test.com.ybt.base.TestCase;

public class QrcodeServiceTest extends TestCase {
	
	@Autowired
	public QrcodeService qrcodeService;
	
	@Test
	public void tests() throws Exception {
		com.ybt.common.bean.Result<SunQrcode> result = qrcodeService.createTempQrcode("", 0L, "daiyan");
	
		System.out.println("");
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		long time = 0l;
		JUnitCore junit = new JUnitCore();
		Result r;
		r = junit.run(Request.method(QrcodeServiceTest.class, "tests"));
		time += r.getRunTime();
		System.out.println("执行时间：" + r.getRunTime() + "毫秒。");
		System.out.println("执行数：" + r.getRunCount());
		System.out.println("忽略数：" + r.getIgnoreCount());
		System.out.println("失败数：" + r.getFailureCount());
			
	}
	
}
