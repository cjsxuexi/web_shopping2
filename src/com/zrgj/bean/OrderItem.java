package com.zrgj.bean;

import java.math.BigDecimal;

/**
 * 	������ϸ
*/
public class OrderItem {
		
	// id
	private Integer id;
	
	// ��Ʒ����
	private String productName;
	
	// ��Ʒ�ļ۸�
	private BigDecimal productPrice;
	
	// ����
	private Integer qunantity;
	
	// С��
	private BigDecimal orderItemPrice;
	
	// ����
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
