package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;


/**
 * @description 音乐库
 * @module 模块:语音库处理
 * @author   AndyBao
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
@Entity
@Table(name = "sun_zy_music")
public class SunZyMusic extends BaseEntity{
	@Column(name = "name",length=50)	
	protected String name;
	@Column(name = "url",length=100)	
	protected String url;
	@Column(name = "type",length=10)	
	protected String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
