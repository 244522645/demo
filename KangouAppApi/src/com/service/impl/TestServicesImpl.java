package com.service.impl;

import org.springframework.stereotype.Service;

import com.service.TestService;
@Service
public class TestServicesImpl extends BaseService implements TestService {
	// 测试用接口
	public String test() {
		System.out.println("这是测试用的service接口");
		String exe;

		try {
			String url;
			url = "http://192.168.1.16:4066/lookangocom/KangouAppConsume.ashx?posid=10000001&batchnumber=000000&serialnumber=000185&cardid=86030000001:000001741307&posordercount=1&unitprice=40&userid=15935114842&md5key=kangouApp3lsderi&type=KG_App_PosPurchase";
				   http://192.168.1.16:4066/lookangocom/KangouAppConsume.ashx?posid=10000001&batchnumber=000000&serialnumber=000009&cardid=10000000001:000001741307&posordercount=1&unitprice=30&userid=15935114842&md5key=kangouApp3lsderi&type=KG_App_PosPurchase
			
			return utils.sendGet(url);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			exe = e.toString();
			e.printStackTrace();
		}
		return exe;
	}

}
