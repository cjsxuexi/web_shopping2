package com.zrgj.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 	����ʵ��
*/
public class Order {
	
	// ���������
	private Integer id;
	
	// �µ�ʱ��
	private Date submitTime;
	
	// ������״̬(�����(0)��ȡ��(1)���ر�(2)��������(3),������(4)�����ջ�(5)�����ջ�(6))
	private Integer	orderState;
	
	//	������
	private String orderNo;
	
	// �����ļ۸�
	private BigDecimal orderPrice;

	// ����ʱ��
	private Date paymentTime;
		
	// ����
	private String message;
	
	// ���ʽ(��������(0) , ����֧��(1))
	private Integer paymentType;
	
	// �����������û�
	private User user;
	
	// ����������Ķ�����ϸ
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
