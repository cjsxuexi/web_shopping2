package com.zrgj.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 	商品的分类实体
*/
public class ProductType{

	// 对象的id
	private int id;
	
	// 分类的名称
	private String ptname;
	
	// 分类的描述
	private String ptdesc;
	
	// 逻辑状态
	private boolean ptdeleted;

	// 表示的是ProductType下面有很多的子分类
	private List<ProductType> subTypes = new ArrayList<ProductType>();
	
	// 这个就表示的是商品分类的父分类是谁
	private ProductType parent;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPtname() {
		return ptname;
	}

	public void setPtname(String ptname) {
		this.ptname = ptname;
	}

	public String getPtdesc() {
		return ptdesc;
	}

	public void setPtdesc(String ptdesc) {
		this.ptdesc = ptdesc;
	}

	public boolean isPtdeleted() {
		return ptdeleted;
	}

	public void setPtdeleted(boolean ptdeleted) {
		this.ptdeleted = ptdeleted;
	}

	public List<ProductType> getSubTypes() {
		return subTypes;
	}

	public void setSubTypes(List<ProductType> subTypes) {
		this.subTypes = subTypes;
	}

	public ProductType getParent() {
		return parent;
	}

	public void setParent(ProductType parent) {
		this.parent = parent;
	}
}
