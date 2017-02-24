package test.com.ybt.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.ybt.common.util.DateUtil;
import com.ybt.common.util.Page;
import com.ybt.model.work.SunBless;
import com.ybt.model.work.SunCard;
import com.ybt.model.work.SunCardTrade;
import com.ybt.service.work.ImagesService;
import com.ybt.service.work.SunCardService;
import com.ybt.service.work.SunCardTradeService;

import test.com.ybt.base.TestCase;

public class SunCardServiceTest extends TestCase {
	
	@Autowired
	public SunCardService cardService;
	
	@Autowired
	public SunCardTradeService cardTradeService;
	
	public ImagesService imageService;

	@Test
	public void tests() throws Exception {
		System.out.println("s");
		System.out.println("s");
		System.out.println("s");
		Page<SunCard> page = new Page<SunCard>();
		page.setPageNo(0);
		cardService.findSunCardByUserIdAndState("oGrGWs91_m2P9TZXcC72hojTmhd8", 1, page);
		List<SunCard> list = page.getResult();
		for (SunCard sunZyPhoto : list) {
			System.out.println(sunZyPhoto.getNumber());
		}
	}
	@Test
	public void testEcard() throws Exception {
		System.out.println("s");
		System.out.println("s");
		System.out.println("s");
		Page<SunCard> page = new Page<SunCard>();
		page.setPageNo(0);
		com.ybt.common.bean.Result<SunCard>  result = cardService.sunECardBandding("123", "123");
		if(result.getState()==1){
			 
		 }
		List<SunCard> list = page.getResult();
		for (SunCard sunZyPhoto : list) {
			System.out.println(sunZyPhoto.getNumber());
		}
	}
	@Test
	public void testTcard() throws Exception {
		System.out.println("T");
		System.out.println("T");
		System.out.println("T");
		//cardService.sunTCardBandding("", "");
		cardService.getSunTCard("123",DateUtil.StringToDate(DateUtil.getDateFormat(new Date(), "yyyyMMdd"),  "yyyyMMdd"));
	}
	
	@Test
	public void testCardTrade() throws Exception {
		
		com.ybt.common.util.Page<SunCardTrade> page = new com.ybt.common.util.Page<SunCardTrade>();
		page.setPageNo(1);
		page.setPageSize(10);
		cardTradeService.getSunCardTradeListByUserId("oWL5RuJoTvOo2ZyHwLfafhE3B3-M", page);
		System.out.println(page.getResult().size());
	}
	@Test
	public void testCardUsed() throws Exception {
		
		com.ybt.common.util.Page<SunCard> page = new com.ybt.common.util.Page<SunCard>();
		page.setPageNo(1);
		page.setPageSize(10);
		cardService.findSunCardByUsedAndUserId("oWL5RuJoTvOo2ZyHwLfafhE3B3-M", page);
		System.out.println(page.getResult().size());
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		long time = 0l;
		JUnitCore junit = new JUnitCore();
		Result r;
		r = junit.run(Request.method(SunCardServiceTest.class, "testCardUsed"));
		time += r.getRunTime();
		System.out.println("执行时间：" + r.getRunTime() + "毫秒。");
		System.out.println("执行数：" + r.getRunCount());
		System.out.println("忽略数：" + r.getIgnoreCount());
		System.out.println("失败数：" + r.getFailureCount());
			
	}
	
}
