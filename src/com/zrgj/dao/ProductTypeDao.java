package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.ProductType;

/**
 * 	��Ʒ����Dao
*/
public interface ProductTypeDao {

	// ��ӷ���
	public void insert(ProductType productType);
	
	// ��ѯһ������
	public List<ProductType> getFirstLevel();

	// ���ݷ���id��ѯ����
	public ProductType getById(int id);

	// ���ݸ������id��ѯ������ӷ���
	public List<ProductType> getSubProductTypes(int parentId);

	// �����ӷ����ѯ����
	public long getSubTypeCount(int parentId);

	// ����idɾ��������ӷ���
	public void deleteSubTypeByParentId(int parentId);
	
	
}
