package com.zrgj.service;

import java.util.List;

import com.zrgj.bean.PageView;
import com.zrgj.bean.Product;
import com.zrgj.bean.result.ProductResult;

// ��ƷService
public interface ProductService {

	// ������Ʒ
	public void addProduct(Product product);
	
	// ��ѯ���е�ͼ��
	public List<Product> queryProducts();

	// ������Ʒidɾ����Ʒ
	public void deleteProductById(int productId);
	
	// ������Ʒid��Ʒ
	public Product queryProductById(int productId);

	// ������Ʒid��ѯ��Ʒ,(������ȫ����Ϣ)
	public ProductResult queryProductAllInfoById(int parseInt);
	
	// ��ҳͼ���б�
	public PageView<Product> queryPageDataNoWhere(Integer pageNum);
	
	// ���������ģ���ҳ��ѯͼ���б�
	public PageView<Product> queryPageDataWithWhere(Integer pageNum,String whereSQL,List<Object> whereParams);
	
	
}




