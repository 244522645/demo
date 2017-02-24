package com.ybt.model.work;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.ybt.common.util.TodayDateFormat;
import com.ybt.model.BaseEntity;

/**
 * @file YbtDmPattern.java 创建时间:2016年1月27日上午10:34:02
 * @description 订单信息表（ybt_order）		
 * @module 模块: 商品基础
 * @author zhz
 * @version 3.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
@Entity
@Table(name = "sun_dd_order")
public class SunDdOrder extends BaseEntity{
	private String sellerId;       //	卖家ID（默认生日阳光官网）
	private String buyerId;        //	买家ID
	private String buyerName;      //	收货人名称
	private String sendeeId;        //	收货人ID
	private String sendeeAddress;   //	收货地址
	private String sendeeName;    	//	收货人姓名
	private String sendeePhone;     //	收货人联系电话
	private String sendeeEmail;     //	收货人邮箱
	private String message;     	//	贺卡留言
	private String title;     		//	标题
	private String orderNo;       	//  订单号
	private String courierNo; 	  	 //	快递单号
	private Integer payment=0;       //	支付方式（0点数换购、1支付、2卡兑换）
	private Integer delivery=0;  	//	配送方式（0在线，1下载，2邮件，3邮寄）
	private Double totalGoodPrice;     //商品总金额	double
	private Double sendPrice;          //运费金额	double
	private Double totalPrice;         //应付总金额	double
	private Double payTotalPrice;      //实际支付总金额	double
	private Double payPoints;     	   //支付点数	double
	private Integer sellDeleted=0;     //卖家删除标志（0正常，1删除）	int（1）
	private Integer buyDeleted=0;      //买家删除标志（0正常，1删除）	int（1）
	private String payInfoId;	    //支付信息id
	private String status;	    //状态
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "card_img_id")
	private SunZyImages cardImage;	    //贺卡图片ID
	
/*	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "status", referencedColumnName = "id")
	@NotFound(action=NotFoundAction.IGNORE)
	private SunDdStatus status;//状态
*/	
	@OneToMany(cascade = { CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE },mappedBy = "orderId", fetch = FetchType.EAGER)
	@Where(clause="deleted!=1")
	private List<SunDdGood> orderGoods;//商品信息
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "photo_id")
	private SunZyPhoto photo;	    //贺卡图片ID
	
	@Transient
	public String getCreateTimeF() {
		return TodayDateFormat.format(createTime);
	}

	//芸备胎补贴
/*	private Double buyerSubsidyMoney;//买方补贴金额
	private String buyerSubsidyIntr;//买方补贴介绍
	private Double sellerSubsidyMoney;//卖方补贴金额
	private String sellerSubsidyIntr;//卖方补贴介绍
*/
	@Column(name = "seller_id", length = 32)
	public String getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name = "buyer_id", length = 32)
	public String getBuyerId() {
		return this.buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "buyer_name", length = 100)
	public String getBuyerName() {
		return this.buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	@Column(name = "sendee_address", length = 200)
	public String getSendeeAddress() {
		return this.sendeeAddress;
	}

	public void setSendeeAddress(String sendeeAddress) {
		this.sendeeAddress = sendeeAddress;
	}

	@Column(name = "sendee_name", length = 20)
	public String getSendeeName() {
		return this.sendeeName;
	}

	public void setSendeeName(String sendeeName) {
		this.sendeeName = sendeeName;
	}

	@Column(name = "sendee_phone", length = 50)
	public String getSendeePhone() {
		return this.sendeePhone;
	}

	public void setSendeePhone(String sendeePhone) {
		this.sendeePhone = sendeePhone;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String value) {
		this.status = value;
	}
	@Column(name = "order_No")
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "courier_No")
	public String getCourierNo() {
		return this.courierNo;
	}

	public void setCourierNo(String courierNo) {
		this.courierNo = courierNo;
	}

	@Column(name = "payment")
	public Integer getPayment() {
		return this.payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	@Column(name = "total_price", precision = 22, scale = 0)
	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "delivery")
	public Integer getDelivery() {
		return this.delivery;
	}

	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}

	@Column(name = "pay_total_price")
	public Double getPayTotalPrice() {
		return payTotalPrice;
	}

	public void setPayTotalPrice(Double payTotalPrice) {
		this.payTotalPrice = payTotalPrice;
	}
	
	@Column(name = "pay_Info_id")
	public String getPayInfoId() {
		return payInfoId;
	}

	public void setPayInfoId(String payInfoId) {
		this.payInfoId = payInfoId;
	}
	
	@Column(name = "total_good_price")
	public Double getTotalGoodPrice() {
		return totalGoodPrice;
	}

	public void setTotalGoodPrice(Double totalGoodPrice) {
		this.totalGoodPrice = totalGoodPrice;
	}

	@Column(name = "send_price")
	public Double getSendPrice() {
		return sendPrice;
	}

	public void setSendPrice(Double sendPrice) {
		this.sendPrice = sendPrice;
	}
	
	public List<SunDdGood> getOrderGoods() {
		if(orderGoods!=null){
			for(SunDdGood good:orderGoods){
				good.setOrderId(this.id);
			}
		}
		return orderGoods;
	}

	public void setOrderGoods(List<SunDdGood> orderGoods) {
		this.orderGoods = orderGoods;
	}

	@Column(name = "sell_deleted",nullable=false, columnDefinition="INT default 0")
	public Integer getSellDeleted() {
		return sellDeleted;
	}

	public void setSellDeleted(Integer sellDeleted) {
		this.sellDeleted = sellDeleted;
	}

	@Column(name = "buy_deleted",nullable=false, columnDefinition="INT default 0")
	public Integer getBuyDeleted() {
		return buyDeleted;
	}

	public void setBuyDeleted(Integer buyDeleted) {
		this.buyDeleted = buyDeleted;
	}
	@Column(name = "sendee_id")
	public String getSendeeId() {
		return sendeeId;
	}

	public void setSendeeId(String sendeeId) {
		this.sendeeId = sendeeId;
	}
	@Column(name = "sendee_email")
	public String getSendeeEmail() {
		return sendeeEmail;
	}

	public void setSendeeEmail(String sendeeEmail) {
		this.sendeeEmail = sendeeEmail;
	}
	@Column(name = "pay_points")
	public Double getPayPoints() {
		return payPoints;
	}

	public void setPayPoints(Double payPoints) {
		this.payPoints = payPoints;
	}
	@Column(name = "message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public SunZyImages getCardImage() {
		return cardImage;
	}

	public void setCardImage(SunZyImages cardImgId) {
		this.cardImage = cardImgId;
	}

	public SunZyPhoto getPhoto() {
		return photo;
	}

	public void setPhoto(SunZyPhoto photo) {
		this.photo = photo;
	}
}
