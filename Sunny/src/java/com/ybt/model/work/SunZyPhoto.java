package com.ybt.model.work;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ybt.common.util.CustomPropertyConfigurer;
import com.ybt.common.util.DateUtil;
import com.ybt.common.util.TodayDateFormat;
import com.ybt.model.BaseEntity;

/**
 *
 * @project 云备胎微信版
 * @package com.ybt.model.work
 * @file SunZyPhoto.java 创建时间:2016年6月15日下午3:53:52
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2016 云讯科技有限公司
 * @company 云讯科技有限公司
 * @module 模块: 照片商品表
 * @author   高艳花
 * @reviewer 金双江
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@Entity
@Table(name = "sun_zy_photo")
public class SunZyPhoto extends BaseEntity{
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "img_id")
	private SunZyImages imgId;   		// 图片ID
	private Long imgNO;   		// 图片编号
	private String localName;   	//	本地名
	private String cameristId; 		// 摄影师ID
	private String cameristName;  	// 摄影师名称
	private String province;  	// 省
	private String city;  		// 市
	private String area;  		// 区
	private String longitude;  	// 经度
	private String latitude;  	// 纬度
	private String explainIds;  // 证明图片ID集合
	private Double price;  		// 价格
	private String title;   //标题
	private Date shootingTime;  // 摄影时间
	private String address;  	//摄影地点
	private String subject;   //题材 主题
	private String tags;	//标签
	private String story;  			// 照片故事
	
	private String workerId;  	// 审核人ID
	private int released=0;  	// 发布标志(0未发布，1已发布)
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "grapher_id")
	private SunZyPhotoGrapher grapher;  //摄影师
	
	@Column(name = "show_count")
	protected int showCount;  //	预览次数
	
	@Column(name = "good_count")
	protected int goodCount;  //	赞数
	
	@Column(name = "love_count")
	protected int loveCount;  //	喜欢数
	
	@Column(name = "comment_count")
	protected int commentCount;  //	评论量数
	
	@Column(name = "share_count")
	protected int shareCount;  //	分享次数
	
	@Column(name = "buy_count")
	protected int buyCount;  //	购买次数
	
	private String photoNo;
	@SuppressWarnings("unused")
	private String shootingTimeF;
	protected String imgUrl;  	// 照片地址
	static Map<String,String> properties = CustomPropertyConfigurer.getProperties();
	
	public SunZyPhotoGrapher getGrapher() {
		return grapher;
	}
	public void setGrapher(SunZyPhotoGrapher grapher) {
		this.grapher = grapher;
	}
	@Transient
	public String getImgUrl() {
		if(imgId!=null){
			String folder=imgId.getFolder();
			String name=imgId.getName();
			if(folder!=null&&!"".equals(folder))
				this.imgUrl =properties.get("imgDomainName")+"/"+folder+"/"+name;
			else
				this.imgUrl = properties.get("imgDomainName")+"/"+name;
			return this.imgUrl;
		}
		return this.imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	@Transient
	private String provinceF;  	// 省
	@Transient
	public String getProvinceF() {
		if(province!=null){
			int index=province.indexOf("_");
			if(index>0){
				provinceF=province.substring(0, index);
			}else{
				provinceF=province;
			}
			provinceF=provinceF.replaceAll("省", "")	;
			provinceF=provinceF.replaceAll("市", "")	;
			provinceF=provinceF.replaceAll("自治区", "")	;
		}else{
			provinceF="";
		}
			
		return provinceF;
	}
	public void setProvinceF(String provinceF) {
		this.provinceF = provinceF;
	}
	@Transient
	public String getPhotoNo() {
		String d= id.replaceAll("\\D", "");//去除非数字
		this.photoNo = "YG"+ DateUtil.getDateFormat(super.createTime, "yy"+imgNO+"Md"+d.substring(d.length()-1,d.length()));
		return this.photoNo;
	}
	public void setPhotoNo(String photoNo) {
		this.photoNo = photoNo;
	}
	
	@Transient
	public String getShootingTimeF() {
		return TodayDateFormat.format(shootingTime);
	}
	public void setShootingTimeF(String shootingTimeF) {
		this.shootingTimeF = shootingTimeF;
	}
	
	@Column(name = "local_name")
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public SunZyImages getImgId() {
		return this.imgId;
	}
	public void setImgId(SunZyImages imgId) {
		this.imgId = imgId;
	}
	@Column(name = "img_no")
	public Long getImgNO() {
		return this.imgNO;
	}
	public void setImgNO(Long imgNO) {
		this.imgNO = imgNO;
	}
	@Column(name = "camerist_id", length = 32)
	public String getCameristId() {
		return cameristId;
	}
	public void setCameristId(String cameristId) {
		this.cameristId = cameristId;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name = "tags")
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@Column(name = "camerist_name", length = 50)
	public String getCameristName() {
		return cameristName;
	}
	public void setCameristName(String cameristName) {
		this.cameristName = cameristName;
	}
	@Column(name = "story", length = 500)
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	@Column(name = "shooting_time")
	public Date getShootingTime() {
		return shootingTime;
	}
	public void setShootingTime(Date shootingTime) {
		this.shootingTime = shootingTime;
	}
	@Column(name = "address", length = 200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "longitude", length = 20)
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name = "latitude", length = 20)
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Column(name = "explain_ids", length = 32)
	public String getExplainIds() {
		return explainIds;
	}
	public void setExplainIds(String explainIds) {
		this.explainIds = explainIds;
	}
	@Column(name = "released", length = 1)
	public int getReleased() {
		return released;
	}
	public void setReleased(int released) {
		this.released = released;
	}
	
	@Column(name = "worker_id", length = 32)
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
