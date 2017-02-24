package test.com.ybt.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.work.ImagesService;
import com.ybt.service.work.PhotoService;
import com.ybt.service.work.ReservePhotoService;

import test.com.ybt.base.TestCase;

public class PhotoServiceTest extends TestCase {
	
	@Autowired
	@Qualifier("photoService")
	public PhotoService photoService;
	
	@Autowired
	@Qualifier("reservePhotoService")
	public ReservePhotoService reservePhotoService;
	
	public ImagesService imageService;

	@Test
	public void findSunZyPhotoByCityAndShootingTimeTest() throws Exception {
		List<SunZyPhoto> list = reservePhotoService.getPhotoListByCitys("北京,青岛,厦门", "2016-12-14");
		System.out.println("-----:"+list.size());
		for (SunZyPhoto sunZyPhoto : list) {
			System.out.println("-----:"+sunZyPhoto.getCity()+"-----:"+sunZyPhoto.getShootingTimeF());
		}
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		long time = 0l;
		JUnitCore junit = new JUnitCore();
		Result r;
		r = junit.run(Request.method(PhotoServiceTest.class, "findSunZyPhotoByCityAndShootingTimeTest"));
		time += r.getRunTime();
		System.out.println("执行时间：" + r.getRunTime() + "毫秒。");
		System.out.println("执行数：" + r.getRunCount());
		System.out.println("忽略数：" + r.getIgnoreCount());
		System.out.println("失败数：" + r.getFailureCount());
			
	}
	
}
