package com.zrgj.bean;

import java.math.BigDecimal;

/**
 * 	�������Ŀ
*/
public class CartItem {

	// ��Ʒ
	private Product product;
	
	// ����
	private Integer quantity;
	
	// С��
	private BigDecimal subPrice;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		
		// ����С��
		this.subPrice = this.product.getPprice().multiply(new BigDecimal(this.quantity));
	}

	public BigDecimal getSubPrice() {
		return subPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}
