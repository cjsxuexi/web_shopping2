package com.zrgj.bean.result;

import java.math.BigDecimal;

/**
 * ��Ʒ�Ľ��ʵ��
 */
public class ProductResult {

	// ��Ʒ����id
	private int id;

	// ��Ʒ����
	private String pname;

	// ��Ʒ������
	private String pdesc;

	// ��Ʒ�ļ۸�
	private BigDecimal pprice;

	// ��Ʒ������
	private String pauthor;

	// ��Ʒ�ĳ�����
	private String paddress;

	// ��Ʒ��ͼƬ
	private String pimageUrl;

	// �߼�״̬
	private boolean pdeleted;

	private Integer firstLevelId;

	private String firsetLevelName;

	private Integer secondLevelId;

	private String secondLevelName;


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

	public boolean isPdeleted() {
		return pdeleted;
	}

	public void setPdeleted(boolean pdeleted) {
		this.pdeleted = pdeleted;
	}

	public Integer getFirstLevelId() {
		return firstLevelId;
	}

	public void setFirstLevelId(Integer firstLevelId) {
		this.firstLevelId = firstLevelId;
	}

	public String getFirsetLevelName() {
		return firsetLevelName;
	}

	public void setFirsetLevelName(String firsetLevelName) {
		this.firsetLevelName = firsetLevelName;
	}

	public Integer getSecondLevelId() {
		return secondLevelId;
	}

	public void setSecondLevelId(Integer secondLevelId) {
		this.secondLevelId = secondLevelId;
	}

	public String getSecondLevelName() {
		return secondLevelName;
	}

	public void setSecondLevelName(String secondLevelName) {
		this.secondLevelName = secondLevelName;
	}
}
