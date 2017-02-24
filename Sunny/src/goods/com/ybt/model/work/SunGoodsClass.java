package com.ybt.model.work;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ybt.model.BaseEntity;


/**
 * 商品类型
 */


@Entity
@Table(name = "sun_goods_class")
public class SunGoodsClass extends BaseEntity{

	private String fatherId;   		// 父类编号
	private String name; 			// 种类名称
	private Integer sort;  			// 排序
	private Integer xzbz;  			// 选择标志（一般情况下大类不能选，小类可以选）
	
	@Column(name = "father_id", length = 32)
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "sort", length = 10)
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Column(name = "xzbz", length = 1)
	public Integer getXzbz() {
		return xzbz;
	}
	public void setXzbz(Integer xzbz) {
		this.xzbz = xzbz;
	}
}
