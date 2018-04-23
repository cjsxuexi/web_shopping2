package com.zrgj.bean.result;

import java.math.BigDecimal;

/**
 * 商品的结果实体
 */
public class ProductResult {

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
