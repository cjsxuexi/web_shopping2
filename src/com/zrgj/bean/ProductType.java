package com.zrgj.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 	��Ʒ�ķ���ʵ��
*/
public class ProductType{

	// �����id
	private int id;
	
	// ���������
	private String ptname;
	
	// ���������
	private String ptdesc;
	
	// �߼�״̬
	private boolean ptdeleted;

	// ��ʾ����ProductType�����кܶ���ӷ���
	private List<ProductType> subTypes = new ArrayList<ProductType>();
	
	// ����ͱ�ʾ������Ʒ����ĸ�������˭
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
