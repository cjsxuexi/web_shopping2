package com.zrgj.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 	订单实体
*/
public class Order {
	
	// 对象的主键
	private Integer id;
	
	// 下单时间
	private Date submitTime;
	
	// 订单的状态(待审核(0)、取消(1)、关闭(2)、待付款(3),待发货(4)、待收货(5)、已收货(6))
	private Integer	orderState;
	
	//	订单号
	private String orderNo;
	
	// 订单的价格
	private BigDecimal orderPrice;

	// 付款时间
	private Date paymentTime;
		
	// 留言
	private String message;
	
	// 付款方式(货到付款(0) , 在线支付(1))
	private Integer paymentType;
	
	// 订单所属的用户
	private User user;
	
	// 订单所管理的订单明细
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
