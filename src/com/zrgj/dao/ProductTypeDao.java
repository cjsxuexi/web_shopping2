package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.ProductType;

/**
 * 	商品分类Dao
*/
public interface ProductTypeDao {

	// 添加分类
	public void insert(ProductType productType);
	
	// 查询一级分类
	public List<ProductType> getFirstLevel();

	// 根据分类id查询分类
	public ProductType getById(int id);

	// 根据父分类的id查询下面的子分类
	public List<ProductType> getSubProductTypes(int parentId);

	// 根据子分类查询分类
	public long getSubTypeCount(int parentId);

	// 根据id删除下面的子分类
	public void deleteSubTypeByParentId(int parentId);
	
	
}
