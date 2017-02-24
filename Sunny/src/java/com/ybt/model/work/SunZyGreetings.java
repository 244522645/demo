package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;


/**
 *
 * @project 云备胎微信版
 * @package com.ybt.model.work
 * @file SunZyGreetings.java 创建时间:2016年6月22日上午9:18:29
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2016 云讯科技有限公司
 * @company 云讯科技有限公司
 * @module 模块: 祝福语
 * @author   高艳花
 * @reviewer 金双江
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@Entity
@Table(name = "sun_zy_greetings")
public class SunZyGreetings extends BaseEntity{
	private String greetings;   // 祝福语
	private String tags; 		// 标签
	private int released=0;  	// 发布标志(0未发布，1已发布)

	@Column(name = "tags")
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@Column(name = "released", length = 1)
	public int getReleased() {
		return released;
	}
	public void setReleased(int released) {
		this.released = released;
	}
	
	@Column(name = "greetings", length = 500)
	public String getGreetings() {
		return greetings;
	}
	public void setGreetings(String greetings) {
		this.greetings = greetings;
	}
}
