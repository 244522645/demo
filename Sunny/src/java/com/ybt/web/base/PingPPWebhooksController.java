package com.ybt.web.base;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ybt.service.work.PingPPWebhooks;

@Controller
@RequestMapping(value = "/common/open/webhooks")
public  class PingPPWebhooksController {
	@Autowired
	public PingPPWebhooks webhooks;
	
	
	/**Webhooks 支持的事件
		目前 Webhooks 支持八种事件，分别是:
		summary.daily.available\summary.weekly.available\summary.monthly.available\
		charge.succeeded\refund.succeeded\transfer.succeeded\red_envelope.sent\red_envelope.received
		接收 Webhooks 通知
		Webhooks 通知是以 POST 形式发送的 JSON ，放在请求的 body 里，内容是 Event 对象。你需要监听并接收 Webhooks 通知，接收到 Webhooks 后需要返回服务器状态码 200 表示接收成功，否则请返回状态码 500。
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void webhooks(HttpServletRequest request, HttpServletResponse response,Model model,Map<String, Object> map,String id)
			throws IOException {
		webhooks.processWebhooks(request, response);
	}
	
	
	/**
		第三步：验证 Webhooks 签名（可选）
			签名简介
			Ping++ 的 Webhooks 通知包含了签名字段，可以使用该签名验证 Webhooks 通知的合法性。签名放置在 header 的自定义字段 x-pingplusplus-signature 中，签名用 RSA 私钥对 Webhooks 通知使用 RSA-SHA256 算法进行签名，以 base64 格式输出。
			验证签名
			Ping++ 在管理平台中提供了 RSA 公钥，供验证签名，该公钥信息可在公司签约完成后，在管理平台的「公司信息」中获取。验证签名需要以下几步：
			从 header 取出签名字段并对其进行 base64 解码。
			获取 Webhooks 请求的原始数据。
			将获取到的 Webhooks 通知、Ping++ 管理平台提供的 RSA 公钥、和 base64 解码后的签名三者一同放入 RSA 的签名函数中进行非对称的签名运算，来判断签名是否验证通过。历次修订内容、修订人、修订时间等）
	 */
	public String WebHooksVerify(HttpServletRequest request, HttpServletResponse response)
			throws  IOException {

		return null;
	}

}
