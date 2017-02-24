
package com.ybt.service.work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.Refund;
import com.pingplusplus.model.Summary;
import com.pingplusplus.model.Transfer;
import com.pingplusplus.model.Webhooks;


@SuppressWarnings("rawtypes")
@Component
public class PingPPWebhooks{
	@Autowired
	private OrderService orderService;
	@Autowired
	private TradeAccountService tradeAccountSer;

	public void processWebhooks(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF8");
		boolean isOk = false;
        //获取头部所有信息
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
        }
        // 获得 http body 内容
        BufferedReader reader = request.getReader();
        StringBuffer buffer = new StringBuffer();
        String string;
        while ((string = reader.readLine()) != null) {
            buffer.append(string);
        }
        reader.close();
        System.out.println("================================PingPPWebhooksServlet=="+ buffer);
        // 解析异步通知数据
        Event event = Webhooks.eventParse(buffer.toString());   
        if ("charge.succeeded".equals(event.getType())) {
            response.setStatus(200);
            isOk = true;
        } else if ("transfer.succeeded".equals(event.getType())) {
            response.setStatus(200);
            isOk = true;
        } else if ("refund.succeeded".equals(event.getType())) {
            response.setStatus(200);
            isOk = true;
        } else if ("refund.succeeded".equals(event.getType())) {
            response.setStatus(500);
        }
        out.close();//提前返回响应，后面的继续执行，否则会超时
        if(isOk){
        	 //进入ping++ 支付业务
            orderHandle(event);
        }
	}
	public void orderHandle(Event event){
		try{
			//解析webhooks 可以采用如下方法
			Object obj = Webhooks.parseEvnet(event.toString());
			if (obj instanceof Charge) {
			    System.out.println("webhooks 发送了 Charge");
			    //进入支付业务层
			    orderService.payPingPP((Charge)obj);
			} else if (obj instanceof Refund) {
			    System.out.println("webhooks 发送了 Refund");
			} else if (obj instanceof Summary) {
			    System.out.println("webhooks 发送了 Summary");
			}
			if("transfer.succeeded".equals(event.getType())){
			   	System.out.println("===========webhooks 发送了 Transfer(微信异步程序调用了我方的后台，进入闻鸡起伍)");
			    //进入提现业务层
			    tradeAccountSer.wjqwTixian(event.toString());
			}   
       }catch(Exception e){
    	   e.printStackTrace();
       }
        
	}
	
	/*public void orderHandle(Event event){
		System.out.println("+++++++++++++++++++++++event ::"+event);
		 //解析webhooks 可以采用如下方法
        Object obj = Webhooks.parseEvnet(event.toString());
        System.out.println("+++++++++++++++++++++++obj instanceof Charge :" + (obj instanceof Charge));
        System.out.println("+++++++++++++++++++++++obj instanceof Transfer :" + (obj instanceof Transfer));
        if (obj instanceof Charge) {
            System.out.println("webhooks 发送了 Charge");
            //进入支付业务层
            orderService.payPingPP((Charge)obj);
        } else if (obj instanceof Transfer) {
            System.out.println("===========webhooks 发送了 Transfer(微信异步程序调用了我方的后台，进入闻鸡起伍)");
            //进入提现业务层
            tradeAccountSer.wjqwTixian((Transfer)obj);
            tradeAccountSer.transferSuccess((Transfer)obj);
            
        } else if (obj instanceof Refund) {
            System.out.println("webhooks 发送了 Refund");
        } else if (obj instanceof Summary) {
            System.out.println("webhooks 发送了 Summary");
        }
	}*/

}
