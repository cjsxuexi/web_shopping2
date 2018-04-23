package com.zrgj.service;

import java.util.List;

import com.zrgj.bean.ProductType;
import com.zrgj.exception.service.ProductTypeContainsSubTypeException;

public interface ProductTypeService {
	
	// ��ӷ���
	public void addProductType(ProductType productType);
	
	// ��ѯһ������
	public List<ProductType> queryFirstLevel();
	
	// �ݹ��ѯ���еķ���
	public List<ProductType> queryProductsByDeep();
	
	// ���ݷ���id��ѯ����
	public ProductType queryProductTypeById(int id);

	// ���ݸ������id��ѯ������ӷ���
	public List<ProductType> querySubProductTypes(int parentId);

	// ���ݷ����idȥɾ���˷���������ӷ���
	public void deleteSubTypeByParentId(int id) throws ProductTypeContainsSubTypeException;
}
