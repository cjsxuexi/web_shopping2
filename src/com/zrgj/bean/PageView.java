package com.zrgj.bean;

import java.util.List;

/**
 * 这个pageView这个对象就表示了当前页面的数据
*/
public class PageView<T> {

	// 用户的显示数据列表
	private List<T> datas;
	
	// 总记录数
	private long totalRecored;
	
	// 每页显示多少条记录
	private int pageSize = 10;
	
	// 应该有多少页
	private long totalPage;
	
	// 表示的是数据库中应该从哪个位置开始
	private int startIndex;
	
	// 表示的是当前页
	private int currentPage;
	
	// 表示的是jsp页面显示的时候的开始页码,这个两个页面是取决于当前页
	private int startPageNo;
	
	// 表示的是jsp页面显示的时候的结束页码,这个两个页面是取决于当前页
	private int endPageNo;
	
	// 页面要跳转的地址
	private String uri;
	
	public PageView(long totalRecored,long currentPage,long pageSize){

		this.totalRecored = totalRecored; // 总记录数有了
		this.currentPage = (int)currentPage; // 当前页
		this.pageSize = (int) pageSize;
		
		if(this.totalRecored % this.pageSize == 0){
			this.totalPage = this.totalRecored / this.pageSize;
		}else{
			this.totalPage = this.totalRecored / this.pageSize + 1;
		}
		
		this.startIndex = (int) ((currentPage - 1) * this.pageSize);
		
		/*
		 * 		我现在一个页面就显示10个页码
		 * 
		 * 			1、我的总页数假如说就是8页
		*/
		if(this.totalPage <= 10){
			this.startPageNo = 1;
			this.endPageNo = (int) this.totalPage;
		}else{
			
			this.startPageNo = this.currentPage - 5;
			this.endPageNo = this.currentPage + 4;
			
			if(this.startPageNo < 1){
				this.startPageNo = 1;
				this.endPageNo = 10;
			}
			
			if(this.endPageNo > this.totalPage){
				this.startPageNo = (int) (this.totalPage - 9);
				this.endPageNo = (int) this.totalPage;
			}
		}
	}
	
	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public long getTotalRecored() {
		return totalRecored;
	}

	public void setTotalRecored(long totalRecored) {
		this.totalRecored = totalRecored;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartPageNo() {
		return startPageNo;
	}

	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
