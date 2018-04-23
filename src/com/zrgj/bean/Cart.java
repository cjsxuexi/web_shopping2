package com.zrgj.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 	���ﳵ
*/
public class Cart {

	// ���ﳵ���汣����һ��һ������Ŀ
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	
	// ���ﳵ���ܼ۸�
	private BigDecimal totalPrice;

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	// ��ȡ���ﳵ���ܼ۸�
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
	
	// �����Ʒ
	public void addProduct(Product product){
		
		// 1��ȥ����һ����Ʒ��Ŀ
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		
		// 2��ע��㣺����õ���Ʒ��Ŀ�п����Ѿ��ڹ��ﳵ�д�����,Ҳ�п����ǲ����ڵ�
		
		// 2.1 �жϴ˹�����Ŀ�Ƿ��ڹ��ﳵ�д���
		if(cartItems.contains(cartItem)){
			// ��ʾ����,��ô��Ҫ����������Ŀ,���ζ�Ӧ����Ŀ������
			for(CartItem item : cartItems){
				if(item.equals(cartItem)){
					// ��ʾ�����ҵ���Ŀ��
					item.setQuantity(item.getQuantity() + 1);
					// �ҵ���Ŀ��break;
					break;
				}
			}
		}else{
			// ��ʾ������
			cartItem.setQuantity(1);
			cartItems.add(cartItem);
		}
	}
	
	/**
	 * 	��չ��ﳵ
	*/
	public void clearAll(){
		this.cartItems.clear();
	}

	/**
	 * 	ɾ����Ʒ
			��ʽһ
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
	
	/* ɾ����Ʒ
	 * 		��ʽ��
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
	 * 	ɾ����Ʒ
	 * 		��ʽ��
	*/
	public void deleteProduct(Product product) {
		
		if(cartItems.size() > 0){
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItems.remove(cartItem);
		}
	}
}
