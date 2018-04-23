package com.zrgj.service;

import java.util.List;

import com.zrgj.bean.ProductType;
import com.zrgj.exception.service.ProductTypeContainsSubTypeException;

public interface ProductTypeService {
	
	// 添加分类
	public void addProductType(ProductType productType);
	
	// 查询一级分类
	public List<ProductType> queryFirstLevel();
	
	// 递归查询所有的分类
	public List<ProductType> queryProductsByDeep();
	
	// 根据分类id查询分类
	public ProductType queryProductTypeById(int id);

	// 根据父分类的id查询下面的子分类
	public List<ProductType> querySubProductTypes(int parentId);

	// 根据分类的id去删除此分类下面的子分类
	public void deleteSubTypeByParentId(int id) throws ProductTypeContainsSubTypeException;
}
