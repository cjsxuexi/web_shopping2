package com.zrgj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zrgj.bean.ProductType;
import com.zrgj.dao.ProductTypeDao;
import com.zrgj.exception.service.ProductTypeContainsSubTypeException;
import com.zrgj.service.ProductTypeService;

@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	private ProductTypeDao productTypeDao ;
	
	// ��ӷ���,���������ǲ����ظ���
	public void addProductType(ProductType productType) {
		
		// Ϊtrue��ʾ��������״̬
		productType.setPtdeleted(true);
		productTypeDao.insert(productType);
	}

	// ��ѯһ������
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<ProductType> queryFirstLevel() {
		return productTypeDao.getFirstLevel();
	}

	// ���ݷ���id��ѯ����
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public ProductType queryProductTypeById(int id) {
		return productTypeDao.getById(id);
	}

	// ���ݸ������id��ѯ������ӷ���
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<ProductType> querySubProductTypes(int parentId) {
		List<ProductType> subProductTypes = productTypeDao.getSubProductTypes(parentId);
		return subProductTypes;
	}

	// ���ݷ���idɾ���˷��������id
	public void deleteSubTypeByParentId(int parentId) throws ProductTypeContainsSubTypeException {
		
		// 1������ȥ��ѯ������������Ƿ����ӷ���
		long count = productTypeDao.getSubTypeCount(parentId);
		if(count > 0){
			throw new ProductTypeContainsSubTypeException("Ҫɾ���ķ�����������ӷ��಻��ɾ��");
		}
		// 2�����û���ӷ��࣬��ɾ��
		productTypeDao.deleteSubTypeByParentId(parentId);
	}

	// �ݹ��ѯ���е���Ʒ����
	public List<ProductType> queryProductsByDeep() {
		
		// 1������Ҫ��ѯ���е�һ������
		List<ProductType> productTypes = this.queryFirstLevel();
		if(productTypes != null && productTypes.size() > 0){
			// �ܵĽ����
			List<ProductType> allProductTypes = new ArrayList<ProductType>();
			allProductTypes.addAll(productTypes);
			deep(allProductTypes,productTypes);
			return allProductTypes;
		}
		return null;
	}
	
	// �ݹ��ѯ
	private void deep(List<ProductType> allProductTypes,List<ProductType> productTypes){
		
		// ѭ������һ������
		for(ProductType pt : productTypes){
			// ����������ȥ��ѯ�˷���������ӷ���
			List<ProductType> subProductTypes = this.productTypeDao.getSubProductTypes(pt.getId());
			if(subProductTypes != null && subProductTypes.size() > 0){
				deep(allProductTypes, subProductTypes);
				pt.setSubTypes(subProductTypes);
			}
		}
	}
}

/**
 * 	����ȥ������5��һ�������¼��ʱ��,�Ƕ�����һ���󼯺�allProductTypes
 * 		
 * 		��һ����
 * 			allProductTypes.add(pt);
 * 
 * 		�ڶ�����
 * 			ȥ�������һ������ȥ���Ҵ˷��������Ƿ��ж�������
 * 			List<Product> subTypes = pt.getId();
 * 			if(subType > 0){
 * 				deept();	
 * 			}
*/


