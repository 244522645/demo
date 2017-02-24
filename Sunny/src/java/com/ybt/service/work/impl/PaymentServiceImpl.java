package com.ybt.service.work.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import com.ybt.common.constant.MyPingPP;
import com.ybt.common.plugin.alipay.AlipayConfig;
import com.ybt.common.plugin.alipay.util.AlipaySubmit;
import com.ybt.common.util.CommonUtil;
import com.ybt.common.util.DateTimeHelper;
import com.ybt.common.util.PropertiesUtil;
import com.ybt.dao.base.BaseDao;
import com.ybt.model.work.SunDdOrder;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.CrowUserInfoService;
import com.ybt.service.work.IPaymentService;

@Component
public class PaymentServiceImpl  extends BaseServiceImpl<SunDdOrder,String> implements IPaymentService{
	@Autowired
	private CrowUserInfoService crowUserInfoService;
	
	public String walletPay(String tradeNo,String subject,Double totalFee,String attach) {
		return null;
	}

	public String aliPay(String tradeNo,String subject,Double totalFee,String attach) {
		
		  Map<String, String> sParaTemp = new HashMap<String, String>();  
		    // 支付类型  
		    // 必填，不能修改  
		    String payment_type = "1";  
		    // 服务器异步通知页面路径  
		    // 需http://格式的完整路径，不能加?id=123这类自定义参数  
		    String notify_url = "http://www.yunbeitai.org/payment/alipay/notify";  
		    // 页面跳转同步通知页面路径  
		    // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/  
		    String return_url = "http://www.yunbeitai.org/payment/alipay/return_url";  
		    // 商户订单号.  
		    // 商户网站订单系统中唯一订单号，必填  
		    //String out_trade_no = date.getTime() + "";  
		    // 订单名称  
		    // 必填  
		    // 防钓鱼时间戳  
		    // 若要使用请调用类文件submit中的query_timestamp函数  
		  //  String anti_phishing_key = "";  
		    // 客户端的IP地址  
		    // 非局域网的外网IP地址，如：221.0.0.1  
		    //String exter_invoke_ip = "";  
		    String seller_id = AlipayConfig.seller_email;
	        //必填  
	        //商户订单号  
	       // String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");  
	        //商户网站订单系统中唯一订单号，必填  
	        //订单名称  
	        //String subject=reward.getSubject();
	        //必填  
	        //付款金额  
	      //  String total_fee = new String(request.getParameter("amount").getBytes("ISO-8859-1"),"UTF-8");  
		    String trade_no=tradeNo;  
		    Double total_fee = totalFee;
		    sParaTemp.put("extra_common_param", attach);  
		    sParaTemp.put("total_fee", total_fee.toString());  
		    sParaTemp.put("service", "create_direct_pay_by_user");  
		    sParaTemp.put("partner", AlipayConfig.partner);  
		    sParaTemp.put("_input_charset", AlipayConfig.input_charset);  
		    sParaTemp.put("payment_type", payment_type);  
		    sParaTemp.put("notify_url", notify_url);  
		    sParaTemp.put("return_url", return_url);  
		    sParaTemp.put("seller_email", seller_id);  
		    sParaTemp.put("out_trade_no", trade_no);  
		    sParaTemp.put("subject", subject);  
		    //sParaTemp.put("anti_phishing_key", anti_phishing_key);  
		    //sParaTemp.put("exter_invoke_ip", exter_invoke_ip);  
		    //sParaTemp.put("key", AlipayConfig.key);  
		    System.out.println("------------------1:"+attach+"------2:"+trade_no+"-totalFee:"+totalFee);
		      
		    String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");  
		return sHtmlText;
	}

	public String wechatPay(String tradeNo,String subject,Double totalFee,String attach) {
		/*Unifiedorder order =new Unifiedorder();
		order.setAppid(Wechat.APP_ID);
		order.setMch_id(Wechat.PARTNER);
		order.setDevice_info("WEB");
		//待生成------
		order.setNonce_str(UUID.randomUUID().toString().replaceAll("-", ""));
		order.setAttach(attach);
		order.setBody(subject);
		order.setNotify_url(Wechat.NOTIFYURL);
		order.setSpbill_create_ip("123.57.5.25");
		order.setTrade_type("NATIVE");
		order.setTotal_fee(((int)(totalFee*100))+"");
		
		order.setOut_trade_no(tradeNo);
			Map<String, String> mapS = MapUtil.objectToMap(order,"sign");
			System.out.println("/////"+ MapUtil.mapJoin(MapUtil.order(mapS), false, true));
		order.setSign(SignatureUtil.generateSign(MapUtil.order(mapS), Wechat.PARTNER_KEY));
		UnifiedorderResult reslt=PayMchAPI.payUnifiedorder(order);
		System.out.println("-------------------------order.setSign:"+order.getSign() );
		System.out.println("-------------------------reslt.getResult_code():"+reslt.getResult_code()+"/"+reslt.getReturn_code()+"/"+reslt.getReturn_msg());
		if("SUCCESS".equals(reslt.getReturn_code())){
			return reslt.getCode_url();
		}*/
		return null;
	}

	public BaseDao<SunDdOrder, String> getDao() {
		return null;
	}

	@Override
	public String pingxxGetCharge(Map<String, Object> jo) throws Exception {
		
		Pingpp.apiKey = MyPingPP.Ping_ApiKey_Live;
		Map<String, Object> chargeMap = new HashMap<String, Object>();
		Double amount = (Double) jo.get("amount");
        chargeMap.put("amount", (int)(amount*100));
        chargeMap.put("currency", "cny");
        chargeMap.put("subject", jo.get("subject"));
        chargeMap.put("body", jo.get("body"));
        //订单号：订单号+当前时间是第几秒+点击次数
        Object order_no = jo.get("order_no");
        if(order_no!=null&&!"".equals(order_no)){
        	Long s = DateTimeHelper.getTimeSortSecond(new Date());
        	Object metering = jo.get("metering");
    	    if(metering!=null&&!"".equals(metering))
    	    	chargeMap.put("order_no", order_no.toString()+(s+(Integer)metering));
    	    else
    	    	chargeMap.put("order_no", order_no.toString()+s);
        }
        chargeMap.put("channel", jo.get("channel"));
        chargeMap.put("client_ip", "123.56.118.53");
        Map<String, String> app = new HashMap<String, String>();
        app.put("id",MyPingPP.Ping_AppId);
        chargeMap.put("app", app);
        Map<String, String> ex = new HashMap<String, String>();
        System.out.println("===jo.get('open_id')="+jo.get("open_id"));
        if(jo.get("service_type")!=null&&!jo.get("service_type").equals("")){
        	Map<String, String> metadata  = new HashMap<String, String>();
        	metadata.put("service_type", jo.get("service_type").toString());
        	 chargeMap.put("metadata", metadata);
        }
        if(jo.get("open_id")!=null&&!jo.get("open_id").equals("")){
        	ex.put("open_id",jo.get("open_id").toString());
        }else{
        	String alipayWapCancel =PropertiesUtil.getProperty("domainName")+"/wechat/pay/alipayWapCancel";
	        String alipayWapSuccess =PropertiesUtil.getProperty("domainName")+"/wechat/pay/alipayWapSuccess";
            ex.put("success_url",alipayWapSuccess);//alipay_wap 
            ex.put("cancel_url",alipayWapCancel);//alipay_wap 
        }
        chargeMap.put("extra", ex);
       
        System.out.println("chargeMap="+chargeMap);
        String charge = Charge.create(chargeMap).toString();
        return charge;
	}
	

}
