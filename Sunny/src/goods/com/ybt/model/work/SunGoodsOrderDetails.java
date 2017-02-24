package com.ybt.model.work;

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
 * 订单详情（  订单信息 收货信息 物流信息 商品信息）
 */
@Entity
@Table(name = "sun_goods_order_details")
public class SunGoodsOrderDetails extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "order_id")
	protected  SunGoodsOrder order;            //	订单
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "sku_id")
	protected  SunGoodsSku orderSku;            //	订单
	
	@Column(name = "goods_num")
	protected int goodsNum;            	// 数量
	@Column(name = "price")	
	private Double price;  			// 价格
	
	@Column(name = "order_num")
	protected String orderNum;            	// 
	
	
	//	定制id
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "made_id")
	protected  SunGoodsMade made;            //	定制id
	
}
