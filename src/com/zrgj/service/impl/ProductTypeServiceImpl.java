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
	
	// 添加分类,分类名称是不能重复的
	public void addProductType(ProductType productType) {
		
		// 为true表示的是正常状态
		productType.setPtdeleted(true);
		productTypeDao.insert(productType);
	}

	// 查询一级分类
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<ProductType> queryFirstLevel() {
		return productTypeDao.getFirstLevel();
	}

	// 根据分类id查询分类
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public ProductType queryProductTypeById(int id) {
		return productTypeDao.getById(id);
	}

	// 根据父分类的id查询下面的子分类
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<ProductType> querySubProductTypes(int parentId) {
		List<ProductType> subProductTypes = productTypeDao.getSubProductTypes(parentId);
		return subProductTypes;
	}

	// 根据分类id删除此分类下面的id
	public void deleteSubTypeByParentId(int parentId) throws ProductTypeContainsSubTypeException {
		
		// 1、首先去查询这个分类下面是否有子分类
		long count = productTypeDao.getSubTypeCount(parentId);
		if(count > 0){
			throw new ProductTypeContainsSubTypeException("要删除的分类下面存在子分类不能删除");
		}
		// 2、如果没有子分类，就删除
		productTypeDao.deleteSubTypeByParentId(parentId);
	}

	// 递归查询所有的商品分类
	public List<ProductType> queryProductsByDeep() {
		
		// 1、首先要查询所有的一级分类
		List<ProductType> productTypes = this.queryFirstLevel();
		if(productTypes != null && productTypes.size() > 0){
			// 总的结果集
			List<ProductType> allProductTypes = new ArrayList<ProductType>();
			allProductTypes.addAll(productTypes);
			deep(allProductTypes,productTypes);
			return allProductTypes;
		}
		return null;
	}
	
	// 递归查询
	private void deep(List<ProductType> allProductTypes,List<ProductType> productTypes){
		
		// 循环遍历一级分类
		for(ProductType pt : productTypes){
			// 拿着主分类去查询此分类下面的子分类
			List<ProductType> subProductTypes = this.productTypeDao.getSubProductTypes(pt.getId());
			if(subProductTypes != null && subProductTypes.size() > 0){
				deep(allProductTypes, subProductTypes);
				pt.setSubTypes(subProductTypes);
			}
		}
	}
}

/**
 * 	我在去遍历这5个一级分类记录的时候,是定义了一个大集合allProductTypes
 * 		
 * 		第一步：
 * 			allProductTypes.add(pt);
 * 
 * 		第二步：
 * 			去拿着这个一级分类去查找此分类下面是否有二级分类
 * 			List<Product> subTypes = pt.getId();
 * 			if(subType > 0){
 * 				deept();	
 * 			}
*/


