package com.ybt.model.work;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;


/**
 *  景点
 */
@Entity
@Table(name = "sun_attraction")
public class SunAttraction extends BaseEntity{

	protected String province;   		// 省份
	protected String city; 			// 城市
	protected String attraction;  		// address
	protected int sort;  	// 排序
	protected int hot;  		// 热门
	protected int rec;  		// 推荐
	protected String abc;  		// 首字母
	
	public int getRec() {
		return rec;
	}
	public void setRec(int rec) {
		this.rec = rec;
	}
	public String getAbc() {
		return abc;
	}
	public void setAbc(String abc) {
		this.abc = abc;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAttraction() {
		return attraction;
	}
	public void setAttraction(String attraction) {
		this.attraction = attraction;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	
	
}
