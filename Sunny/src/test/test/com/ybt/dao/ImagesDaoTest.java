package test.com.ybt.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

import com.ybt.common.util.CustomPropertyConfigurer;
import com.ybt.dao.work.ImagesDao;
import com.ybt.model.work.SunZyImages;

import test.com.ybt.base.TestCase;

public class ImagesDaoTest extends TestCase {
	
	@Resource
	public ImagesDao imagesDao;

	@Test
	public void findByIds() throws Exception {
		String ids[] = {"402880f5556d118301556d144fe50002","402880f5556d118301556d1426010001"};
		List<SunZyImages> list=imagesDao.findByIds(ids);
		
		for (SunZyImages sunZyImages : list) {
			System.out.println(sunZyImages.getFilePath());
		}
	}
	
/*	@Test
	public void tests() throws Exception {
		 Map<String,String> properties = CustomPropertyConfigurer.getProperties();
	        
	        System.out.println(properties);
	}*/
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		long time = 0l;
		JUnitCore junit = new JUnitCore();
		Result r;
		r = junit.run(Request.method(ImagesDaoTest.class, "findByIds"));
		time += r.getRunTime();
		System.out.println("执行时间：" + r.getRunTime() + "毫秒。");
		System.out.println("执行数：" + r.getRunCount());
		System.out.println("忽略数：" + r.getIgnoreCount());
		System.out.println("失败数：" + r.getFailureCount());
			
	}
	
}
