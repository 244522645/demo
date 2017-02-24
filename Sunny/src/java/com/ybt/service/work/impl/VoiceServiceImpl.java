package com.ybt.service.work.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.common.constant.MessageConstant;
import com.ybt.common.plugin.PicBucket;
import com.ybt.common.plugin.UpYunConfig;
import com.ybt.common.util.UploadImgUtil;
import com.ybt.dao.base.BaseDao;
import com.ybt.dao.work.VoiceDao;
import com.ybt.model.work.SunZyVoice;
import com.ybt.service.base.impl.BaseServiceImpl;
import com.ybt.service.work.VoiceService;

@Component
public class VoiceServiceImpl extends BaseServiceImpl<SunZyVoice,String> implements  VoiceService{
	
	@Autowired
	private VoiceDao mediaVoiceDao;
	
	public BaseDao<SunZyVoice, String> getDao() {
		return mediaVoiceDao;
	}

	public Result<SunZyVoice> downVoiceByte(byte[] bytes,int longtime,String type) {
		
		if(bytes==null||bytes.length==0){
			return new Result<SunZyVoice>(MessageConstant.IMG_SAVE_FAILURE,null);
		}

		SunZyVoice media = new SunZyVoice();
		String name =  UploadImgUtil.createUUIDName();
		String fileName = name+".wav";
		media.setName(fileName);
		media.setSuffix("wav");
		media.setFilesize((long)(bytes.length));
		media.setTime(longtime);
		//media.setData(bytes);
		String datepath = new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
		media.setFolder(type+ datepath);
		media.setType(type);
		try {
			 UpYunConfig.initUpYun();
        	 final String upyunpath="/"+media.getFolder()+"/"+media.getName();
 			 if(UpYunConfig.writeFile(upyunpath, bytes)){
 				System.out.println("上传成功"+upyunpath);
 				//音频处理 回调地址 没实现
 				new Thread(new Runnable(){
 					public void run(){
 						try {
 							UpYunConfig.voiceMediaProcess(upyunpath);
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
 						UpYunConfig.voiceMediaProcess(upyunpath);
 						}
 					}).start();
 				
 			 }
		} catch (IOException e) {
			e.printStackTrace();
			return new Result<SunZyVoice>(MessageConstant.IMG_SAVE_FAILURE,null);
		}
		mediaVoiceDao.save(media);
		return new Result<SunZyVoice>(null,media);
	}
	
}
