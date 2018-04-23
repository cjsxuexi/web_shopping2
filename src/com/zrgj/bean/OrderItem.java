package com.zrgj.bean;

import java.math.BigDecimal;

/**
 * 	订单明细
*/
public class OrderItem {
		
	// id
	private Integer id;
	
	// 商品名称
	private String productName;
	
	// 商品的价格
	private BigDecimal productPrice;
	
	// 数量
	private Integer qunantity;
	
	// 小计
	private BigDecimal orderItemPrice;
	
	// 订单
	private Order order;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getQunantity() {
		return qunantity;
	}

	public void setQunantity(Integer qunantity) {
		this.qunantity = qunantity;
	}

	public BigDecimal getOrderItemPrice() {
		return orderItemPrice;
	}

	public void setOrderItemPrice(BigDecimal orderItemPrice) {
		this.orderItemPrice = orderItemPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
