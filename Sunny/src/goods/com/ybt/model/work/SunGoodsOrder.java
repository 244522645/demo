package com.ybt.model.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.ybt.model.BaseEntity;


/**
 *订单信息
 *
 */
@Entity
@Table(name = "sun_goods_order")
public class SunGoodsOrder extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "user_id")
	protected  SunWechatUser userId;            //	会员
	@Column(name = "order_no")
	protected String orderNo;            	// 订单号
	
	//收货信息
	@Column(name = "name")
	protected String name;            	// 收货人
	@Column(name = "phone")
	protected String phone;            	//	手机
	@Column(name = "address")
	protected String address;           //地址
	@Column(name = "waybill")
	protected String waybill ;           //	运单信息
	
	//状态
	protected String payId;	    //支付信息id
	protected String payType;	    //支付方式
	protected String status;	    //状态
	
	//价格
	protected Double goodPrice;     //商品总金额	double
	protected Double sendPrice;          //运费金额	double
	protected Double totalPrice;         //应付总金额	double
	protected Double payPrice;      //实际支付总金额	double
	
	//	时间
	@Column(name = "pay_time",updatable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date payTime = new Date();//付款时间
	@Column(name = "send_time",updatable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date sendTime = new Date();//发货时间
	@Column(name = "end_time",updatable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date endTime = new Date();//成交时间
	
}
