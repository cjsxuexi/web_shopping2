package com.zrgj.bean;

/*
 * 	����״̬ö�ٳ���
*/
public interface OrderState {

	// �����(0)
	public static final Integer	WAIT_CHECKED = 0;
	
	// ȡ��(1)
	public static final Integer CANCLE = 1;
	
	// �ر�(2)
	public static final Integer CLOSED = 2;
	
	// ������(3)
	public static final Integer WAIT_PAYMENT = 3;
	
	// ������(4)
	public static final Integer WAIT_DELIVER = 4;
	
	// ���ջ�(5)
	public static final Integer WAIT_RECEIVE = 5;
	
	// ���ջ�(6)
	public static final Integer RECEIVED = 6;
}
