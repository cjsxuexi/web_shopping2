package com.zrgj.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 	购物车
*/
public class Cart {

	// 购物车里面保存是一个一个的条目
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	
	// 购物车的总价格
	private BigDecimal totalPrice;

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	// 获取购物车的总价格
	public BigDecimal getTotalPrice() {
		
		totalPrice = new BigDecimal("0");
		if(cartItems.size() > 0){
			for(CartItem cartItem : cartItems){
				BigDecimal cartItemPrice = cartItem.getSubPrice();
				totalPrice = totalPrice.add(cartItemPrice);
			}
		}
		return totalPrice;
	}
	
	//------------------------------
	
	// 添加商品
	public void addProduct(Product product){
		
		// 1、去构造一个商品条目
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		
		// 2、注意点：构造好的商品条目有可能已经在购物车中存在了,也有可能是不存在的
		
		// 2.1 判断此购物条目是否在购物车中存在
		if(cartItems.contains(cartItem)){
			// 表示存在,那么就要遍历购物条目,修饰对应的条目的数量
			for(CartItem item : cartItems){
				if(item.equals(cartItem)){
					// 表示我们找到条目了
					item.setQuantity(item.getQuantity() + 1);
					// 找到条目就break;
					break;
				}
			}
		}else{
			// 表示不存在
			cartItem.setQuantity(1);
			cartItems.add(cartItem);
		}
	}
	
	/**
	 * 	清空购物车
	*/
	public void clearAll(){
		this.cartItems.clear();
	}

	/**
	 * 	删除商品
			方式一
	public void deleteProduct(int productId) {
		if(cartItems.size() > 0){
			ListIterator<CartItem> iters = cartItems.listIterator();
			while(iters.hasNext()){
				CartItem item = iters.next();
				if(item.getProduct().getId() == productId){
					iters.remove();
					break;
				}
			}
		}
	}*/
	
	/* 删除商品
	 * 		方式二
	public void deleteProduct(int productId) {
		System.out.println("xxxxxxxxxxxx");
		if(cartItems.size() > 0){
			for(int i = 0 ; i < cartItems.size() ; i++){
				CartItem item = cartItems.get(i);
				if(item.getProduct().getId() == productId){
					cartItems.remove(item);
					break;
				}
			}
		}
	}*/
	
	/**
	 * 	删除商品
	 * 		方式三
	*/
	public void deleteProduct(Product product) {
		
		if(cartItems.size() > 0){
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItems.remove(cartItem);
		}
	}
}
