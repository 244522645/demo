package com.ybt.service.work.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.Wechat;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.QrcodeDao;
import com.ybt.model.work.SunQrcode;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.QrcodeService;

import wechat.api.QrcodeAPI;
import wechat.bean.QrcodeTicket;
import wechat.support.TokenManager;

@Component
public class QrcodeServiceImpl extends BaseServiceImpl<SunQrcode, String> implements QrcodeService{
	
	@Autowired
	private QrcodeDao qrcodeDao;
	@Override
	public BaseDao<SunQrcode, String> getDao() {
		return qrcodeDao;
	}
	@Override
	public List<SunQrcode> getQrcodeListByFinal() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SunQrcode> getQrcodeListByTemp() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SunQrcode getQrcodeByValue(Long value) {
		// TODO Auto-generated method stub
		List<SunQrcode> list = qrcodeDao.findQrcodeByValue(value);
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public SunQrcode getQrcodeById(String id) {
		SunQrcode qrcodeById = qrcodeDao.findQrcodeById(id);
		return qrcodeById;
	}
	
	
	@Override
	public Result<SunQrcode> createTempQrcode(String content, Long value, String name) {
		// TODO Auto-generated method stub
		content=content==null?"":content;
		if(value==0){
			Long max = qrcodeDao.findMaxValue();
			max = max==null?0:max;
			max = max<1000000L?1000000L:max;
			value=max+1L;
			
		}
		
		SunQrcode qrcode = getQrcodeByValue(value);
		if(qrcode==null){
			qrcode= new SunQrcode();
		}
		int expire = 2592000;
		Date expireTime = new Date(System.currentTimeMillis()+1000L*expire);
		QrcodeTicket tickent = QrcodeAPI.qrcodeCreateTemp(TokenManager.getToken(Wechat.APPID, Wechat.APPSECRET), expire, value);
		if(tickent.getErrcode()!=null){
			//错误日志
			System.out.println(tickent.getErrcode()+":"+tickent.getErrmsg());
			return new Result<SunQrcode>("二维码生成失败",null);
		}
		byte[] data = QrcodeAPI.showqrcode(tickent.getTicket());
		
		if(data==null){
			//错误日志
			System.out.println("二维码下载失败");
			return new Result<SunQrcode>("二维码生成失败",null);
		}
		content = "".equals(content)?tickent.getUrl() : content;
		qrcode.setContent(content);
		qrcode.setTicket(tickent.getTicket());
		qrcode.setCreateTime(new Date());
		qrcode.setExpireTime(expireTime);
		qrcode.setDeleted(0);
		qrcode.setName(name);
		qrcode.setQrcode(data);
		qrcode.setValue(value);
		qrcode.setType(0);
		qrcode.setStatus(1);
		this.save(qrcode);
		
		return new Result<SunQrcode>("",qrcode);
	}

}
