package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.Product;
import com.zrgj.bean.result.ProductResult;

/**
 *	��ƷDao 
*/
public interface ProductDao {

	// ������Ʒ
	public void insert(Product product);

	// ��ѯ���е���Ʒ
	public List<Product> getProducts();

	// ������Ʒidɾ����Ʒ
	public void deleteById(int productId);
	
	// ������Ʒid��ѯ��Ʒ
	public Product getById(int productId);
	
	// ������Ʒid��ѯ��Ʒ(�������������������������,������������������Ʒ���е���Ϣ,�Ͱ����˷���)
	public ProductResult getByIdAllInfo(int productId);
	
	// ��ѯ�����ܼ�¼��
	public long getCount();
	
	// ��ѯ��ҳ����
	public List<Product> getPageDataNoWhere(int startIndex,int endIndex);
	
	//-------------------------------------------
	
	// �������ķ�ҳ
	public List<Product> getPageDataWithWhere(int startIndex,int endIndex,String whereSQL,List<Object> params);
	
	// ��ѯ�����ܼ�¼��
	public long getCount(String where,List<Object> params);

}
