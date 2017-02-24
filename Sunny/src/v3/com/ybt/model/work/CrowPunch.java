/*   
 * Copyright (c) 2015-2020 *** Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * ***. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with ***.   
 *   
 */     
package com.ybt.model.work;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ybt.model.BaseEntity;

/**   
 *  个人进度表（打卡） 
 * @author AndyBao  
 * @version 4.0, 2017年2月9日 下午3:37:40   
 */   
@Entity
@Table(name = "crow_punch") 
public class CrowPunch extends BaseEntity{
	
	//   用户id
	@Column(name = "user_id")
	protected String userId;
	
	//	打卡日期
	@Column(name = "p_date",length=100)
	protected String punchDate;
	
	//  打卡截屏
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "img_id")
	protected SunZyImages images;
	
	//  公里
	@Column(name = "km", precision = 22, scale = 0)
	protected Double km;
	
	//  时长
	@Column(name = "time",length=100)
	protected int time;
	
	@Column(name = "money", precision = 22, scale = 2)
	protected BigDecimal bonus;   //契约金
	//审核状态  0 提交  1 已审核
	@Column(name = "status",columnDefinition="INT default 0")
	protected Integer status;
	
	//打卡状态  0 未通过  1通过   
	@Column(name = "ispass",columnDefinition="INT default 0")
	protected Integer ispass;
	
	//是否已计算
	@Column(name = "iscompute",columnDefinition="INT default 0")
	protected Integer iscompute;


	public Integer getIspass() {
		return ispass;
	}

	public void setIspass(Integer ispass) {
		this.ispass = ispass;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPunchDate() {
		return punchDate;
	}

	public void setPunchDate(String punchDate) {
		this.punchDate = punchDate;
	}

	public SunZyImages getImages() {
		return images;
	}

	public void setImages(SunZyImages images) {
		this.images = images;
	}

	public Double getKm() {
		return km;
	}

	public void setKm(Double km) {
		this.km = km;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIscompute() {
		return iscompute;
	}

	public void setIscompute(Integer iscompute) {
		this.iscompute = iscompute;
	}
		
}
  