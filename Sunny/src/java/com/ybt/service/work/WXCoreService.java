package com.ybt.service.work;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.WXCheckModel;
@Component
public interface WXCoreService {
	/**
     * 微信开发者验证
     * @param wxAccount
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    public String validate(String wxToken, WXCheckModel tokenModel);
    
    /**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
     * @throws IOException 
	 */
	public  String processRequest(String token,HttpServletRequest request, HttpServletResponse response) throws IOException;
}
