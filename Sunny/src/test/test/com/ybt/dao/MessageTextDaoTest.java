package test.com.ybt.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

import com.ybt.dao.work.MessageTextDao;
import com.ybt.model.work.WxMessageText;

import test.com.ybt.base.TestCase;

public class MessageTextDaoTest extends TestCase {
	
	@Resource
	public MessageTextDao textDao;

	@Test
	public void tests() throws Exception {
		
		WxMessageText text = new WxMessageText("123", "abc");
		textDao.save(text);
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		long time = 0l;
		JUnitCore junit = new JUnitCore();
		Result r;
		r = junit.run(Request.method(MessageTextDaoTest.class, "tests"));
		time += r.getRunTime();
		System.out.println("执行时间：" + r.getRunTime() + "毫秒。");
		System.out.println("执行数：" + r.getRunCount());
		System.out.println("忽略数：" + r.getIgnoreCount());
		System.out.println("失败数：" + r.getFailureCount());
			
	}
	
}
