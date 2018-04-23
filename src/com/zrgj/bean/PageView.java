package com.zrgj.bean;

import java.util.List;

/**
 * ���pageView�������ͱ�ʾ�˵�ǰҳ�������
*/
public class PageView<T> {

	// �û�����ʾ�����б�
	private List<T> datas;
	
	// �ܼ�¼��
	private long totalRecored;
	
	// ÿҳ��ʾ��������¼
	private int pageSize = 10;
	
	// Ӧ���ж���ҳ
	private long totalPage;
	
	// ��ʾ�������ݿ���Ӧ�ô��ĸ�λ�ÿ�ʼ
	private int startIndex;
	
	// ��ʾ���ǵ�ǰҳ
	private int currentPage;
	
	// ��ʾ����jspҳ����ʾ��ʱ��Ŀ�ʼҳ��,�������ҳ����ȡ���ڵ�ǰҳ
	private int startPageNo;
	
	// ��ʾ����jspҳ����ʾ��ʱ��Ľ���ҳ��,�������ҳ����ȡ���ڵ�ǰҳ
	private int endPageNo;
	
	// ҳ��Ҫ��ת�ĵ�ַ
	private String uri;
	
	public PageView(long totalRecored,long currentPage,long pageSize){

		this.totalRecored = totalRecored; // �ܼ�¼������
		this.currentPage = (int)currentPage; // ��ǰҳ
		this.pageSize = (int) pageSize;
		
		if(this.totalRecored % this.pageSize == 0){
			this.totalPage = this.totalRecored / this.pageSize;
		}else{
			this.totalPage = this.totalRecored / this.pageSize + 1;
		}
		
		this.startIndex = (int) ((currentPage - 1) * this.pageSize);
		
		/*
		 * 		������һ��ҳ�����ʾ10��ҳ��
		 * 
		 * 			1���ҵ���ҳ������˵����8ҳ
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
