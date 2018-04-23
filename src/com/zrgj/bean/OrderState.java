package com.zrgj.bean;

/*
 * 	订单状态枚举常量
*/
public interface OrderState {

	// 待审核(0)
	public static final Integer	WAIT_CHECKED = 0;
	
	// 取消(1)
	public static final Integer CANCLE = 1;
	
	// 关闭(2)
	public static final Integer CLOSED = 2;
	
	// 待付款(3)
	public static final Integer WAIT_PAYMENT = 3;
	
	// 待发货(4)
	public static final Integer WAIT_DELIVER = 4;
	
	// 待收货(5)
	public static final Integer WAIT_RECEIVE = 5;
	
	// 已收货(6)
	public static final Integer RECEIVED = 6;
}
