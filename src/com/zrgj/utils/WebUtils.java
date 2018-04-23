package com.zrgj.utils;

import javax.servlet.http.HttpServletRequest;

import com.zrgj.bean.Cart;
import com.zrgj.bean.User;

// web��Ĺ�����
public final class WebUtils {

	private static final String CART_NAME = "cart";
	
	private static final String USER_NAME = "user";
	
	/**
	 * 	��ȡ���ﳵ
	*/
	public static Cart getCart(HttpServletRequest request){
		return (Cart)request.getSession().getAttribute(CART_NAME);
	}
	
	/**
	 * 	���ù��ﳵ
	*/
	public static void setCart(HttpServletRequest request,Cart cart){
		request.getSession().setAttribute(CART_NAME, cart);
	}
	
	/**
	 * 	��ȡweb��Ӧ������
	*/
	public static String getContextPath(HttpServletRequest request){
		return request.getContextPath();
	}
	
	/**
	 * 	�����û�
	*/
	public static void setUser(HttpServletRequest request,User user){
		request.getSession().setAttribute(USER_NAME,user);
	}
	
	/**
	 * 	��ȡ�û�
	*/
	public static User getUser(HttpServletRequest request){
		return (User)request.getSession().getAttribute(USER_NAME);
	}
	
	/**
	 * 	�û�ע��
	*/
	public static void sessionValidate(HttpServletRequest request){
		request.getSession().removeAttribute(USER_NAME);
	}
	
	/**
	 * 	��չ��ﳵ
	*/
	public static void sessionValidateCart(HttpServletRequest request){
		request.getSession().removeAttribute(CART_NAME);
	}
	
}
