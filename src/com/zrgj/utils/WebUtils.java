package com.zrgj.utils;

import javax.servlet.http.HttpServletRequest;

import com.zrgj.bean.Cart;
import com.zrgj.bean.User;

// web层的工具类
public final class WebUtils {

	private static final String CART_NAME = "cart";
	
	private static final String USER_NAME = "user";
	
	/**
	 * 	获取购物车
	*/
	public static Cart getCart(HttpServletRequest request){
		return (Cart)request.getSession().getAttribute(CART_NAME);
	}
	
	/**
	 * 	放置购物车
	*/
	public static void setCart(HttpServletRequest request,Cart cart){
		request.getSession().setAttribute(CART_NAME, cart);
	}
	
	/**
	 * 	获取web的应用名称
	*/
	public static String getContextPath(HttpServletRequest request){
		return request.getContextPath();
	}
	
	/**
	 * 	保存用户
	*/
	public static void setUser(HttpServletRequest request,User user){
		request.getSession().setAttribute(USER_NAME,user);
	}
	
	/**
	 * 	获取用户
	*/
	public static User getUser(HttpServletRequest request){
		return (User)request.getSession().getAttribute(USER_NAME);
	}
	
	/**
	 * 	用户注销
	*/
	public static void sessionValidate(HttpServletRequest request){
		request.getSession().removeAttribute(USER_NAME);
	}
	
	/**
	 * 	清空购物车
	*/
	public static void sessionValidateCart(HttpServletRequest request){
		request.getSession().removeAttribute(CART_NAME);
	}
	
}
