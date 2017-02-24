package com.ybt.service.work;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ybt.common.bean.Result;
import com.ybt.model.work.SunZyImages;
import com.ybt.model.work.SunZyPhoto;
import com.ybt.service.base.IBaseService;

@Component
public interface ImagesService  extends IBaseService<SunZyImages,String> {
	/**
	 * 保存一张图片信息
	 * @param uImg 图片文件对象
	 * @param file 文件
	 * @param file 是否保留原图
	 * */
	public Result<SunZyImages> saveImg(SunZyImages uImg,File file,boolean b); 

	/**
	 *@name   删除图片信息
	 *@description 相关说明
	 *@time    创建时间:2016年2月14日上午10:11:17
	 *@param imagesId
	 *@author   高艳花
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	void deleteImg(String[] imagesId);
	
	/**
	 *@description 查询图片集
	 *@return
	 */
	public List<SunZyImages> findByIds(String[] ids);
	
	/**
	 *@name  下载图片
	 *@description 相关说明
	 *@time    创建时间:2016年2月14日上午10:11:17
	 *@param bytes ,type 图片分类
	 *@author   AndyBao
	 *@history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	public Result<SunZyImages> downImgByte(byte[] bytes,String type) ;
	
	/**
	 * 生成祝福图片   （）
	 *@time    创建时间:2016年6月24日上午10:11:17
	 * @param photo  照片
	 * @param title 祝福标题
	 * @param content 祝福内容
	 * @author AndyBao
	 * @version V4.0, 2016年6月24日 下午4:38:36 
	 */
	public Result<SunZyImages> createBlessImage(SunZyPhoto photo,String title,String content) throws Exception; 

}
