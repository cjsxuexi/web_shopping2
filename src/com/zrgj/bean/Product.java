package com.zrgj.bean;

import java.math.BigDecimal;

/**
 * 	商品实体
*/
public class Product {

	// 商品对象id
	private int id;
	
	// 商品名称
	private String pname;
	
	// 商品的详情
	private String pdesc;
	
	// 商品的价格
	private BigDecimal pprice;
	
	// 商品的作者
	private String pauthor;
	
	// 商品的出版社
	private String paddress;
	
	// 商品的图片
	private String pimageUrl;
	
	// 逻辑状态
	private boolean pdeleted;
	
	// 商品的分类
	private ProductType productType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public BigDecimal getPprice() {
		return pprice;
	}

	public void setPprice(BigDecimal pprice) {
		this.pprice = pprice;
	}

	public String getPauthor() {
		return pauthor;
	}

	public void setPauthor(String pauthor) {
		this.pauthor = pauthor;
	}


	public String getPaddress() {
		return paddress;
	}

	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}

	public String getPimageUrl() {
		return pimageUrl;
	}

	public void setPimageUrl(String pimageUrl) {
		this.pimageUrl = pimageUrl;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public boolean isPdeleted() {
		return pdeleted;
	}

	public void setPdeleted(boolean pdeleted) {
		this.pdeleted = pdeleted;
	}

	

	// ---------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
