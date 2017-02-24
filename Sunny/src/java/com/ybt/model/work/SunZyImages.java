package com.ybt.model.work;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ybt.common.util.CustomPropertyConfigurer;
import com.ybt.common.util.PropertiesUtil;
import com.ybt.model.BaseEntity;


/**
 * @file YbtImages.java 创建时间:2015年7月24日上午9:28:45
 * @description 图片公共模块
 * @module 模块:图片处理
 * @author   jsj
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
@Entity
@Table(name = "sun_zy_images")
public class SunZyImages extends BaseEntity{

	private String name;      					//图片名称
	private String localName;   	//			本地名
	private String relationId;	 				//关联ID
	private String suffix;	 					//图片后缀
    private String folder;						//目录
    private String filePath;
    private Long size;	 						//图片大小
	protected String type;						//图片类型  0 照片 1 福卡 2 投稿 3其他
	private String height;	 					//规格-长
	private String width;	 					//规格-宽
	private String base; 					//图片64位数据
	private byte[] data;                		//图片数据
	public static String path; 	    	//图片保存根路径（绝对路径）
	public static  String apacheImgPath;	//apache 图片访问地址
	private String imgWebPath;           		//图片web访问地址 
	private String titleImgWebPath;              //缩略图访问地址
	public static  String NOIMG;  	 		//无图片时显示的提示图片
	 
	private String localPath;
	
	static{
		Map<String,String> properties = CustomPropertyConfigurer.getProperties();
		path =    properties.get("imgPath");
		NOIMG = properties.get("noImg");
		apacheImgPath = !properties.get("running.mode").equals("production") ? "http://localhost:8080/Sunny/upload" : properties.get("imgDomainName")+properties.get("apacheImgPath");
		
	}
	
	@Column(name = "type",length=50)	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "local_name")
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	@Column(name = "file_path")
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "folder", length = 50)
	public String getFolder() {
		return this.folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
	@Column(name = "suffix", length = 5)
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	@Column(name = "size")
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	@Column(name = "height")
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	@Column(name = "width")
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	@Column(name = "relation_id")
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	@Transient
	public String getTitleImgWebPath() {
		if(folder!=null&&!"".equals(folder))
			this.titleImgWebPath =  apacheImgPath+"/"+folder+"/s/"+name;
		else
			this.titleImgWebPath =  apacheImgPath+"/s/"+name;
		return this.titleImgWebPath;
	}
	
	public void setTitleImgWebPath(String titleImgWebPath) {
		this.titleImgWebPath = titleImgWebPath;
	}
	
	@Transient
	public String getImgWebPath() {
		if(folder!=null&&!"".equals(folder))
			this.imgWebPath = apacheImgPath+"/"+folder+"/"+name;
		else
			this.imgWebPath = apacheImgPath+"/"+name;
		return this.imgWebPath;
	}
	
	public void setImgWebPath(String imgWebPath) {
		this.imgWebPath = imgWebPath;
	}
	
	@Transient
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	@Transient
	public String getLocalPath() {
		if(folder!=null&&!"".equals(folder))
			this.localPath = PropertiesUtil.getProperty("imgDomainName")+"/"+folder+"/"+name;
		else
			this.localPath = PropertiesUtil.getProperty("imgDomainName")+"/"+name;
		return this.localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	
	@Transient
	public String getBase(){
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
}
