/*   
 * Copyright (c) 2015-2020 *** Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * ***. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with ***.   
 *   
 */     
package com.ybt.service.work.handler;

import javax.servlet.ServletOutputStream;

import wechat.bean.EventMessage;
public interface MessageHandler {
	public String onMsg(EventMessage eventMessage,ServletOutputStream outputStream);
}
  