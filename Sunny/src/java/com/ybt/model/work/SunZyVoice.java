package com.ybt.model.work;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ybt.common.util.CustomPropertyConfigurer;
import com.ybt.model.BaseEntity;


/**
 * @file SunZyVoice.java 创建时间:2015年7月24日上午9:28:45
 * @description 语音库
 * @module 模块:语音库处理
 * @author   jsj
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
@Entity
@Table(name = "sun_zy_voice")
public class SunZyVoice extends BaseEntity{
	@Column(name = "folder",length=50)	
	protected String folder;
	@Column(name = "moduleId",length=50)	
	protected String moduleId;
	@Column(name = "name",length=50)	
	protected String name;
	@Column(name = "suffix",length=10)	
	protected String suffix;
	@Column(name = "filesize")	
	protected Long filesize;
	@Column(name = "file_path",length=100)	
	protected String filePath;
	@Column(name = "file_name",length=50)	
	protected String fileName;
	@Column(name = "type",length=50)	
	protected String type;
	@Column(name = "data")	
	protected byte[] data;                		//数据
	@Column(name = "time")	
	private int time=0;    //是否云存
	@Column(name = "oss")	
	private int oss=0;    //是否云存

	@Transient
	private String url;  	// 照片地址
	public String getUrl() {
			Map<String,String> properties = CustomPropertyConfigurer.getProperties();
			if(folder!=null&&!"".equals(folder))
				this.url =properties.get("imgDomainName")+"/"+folder+"/"+name;
			else
				this.url = properties.get("imgDomainName")+"/"+name;
			return url;
	}
	public void setUrl(String url){
		this.url=url;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Long getFilesize() {
		return filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getOss() {
		return oss;
	}

	public void setOss(int oss) {
		this.oss = oss;
	}
	
}
