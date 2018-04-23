package com.zrgj.dao;

import java.util.List;

import com.zrgj.bean.Product;
import com.zrgj.bean.result.ProductResult;

/**
 *	商品Dao 
*/
public interface ProductDao {

	// 增加商品
	public void insert(Product product);

	// 查询所有的商品
	public List<Product> getProducts();

	// 根据商品id删除商品
	public void deleteById(int productId);
	
	// 根据商品id查询商品
	public Product getById(int productId);
	
	// 根据商品id查询商品(这个方法是区别于上述方法的,这个方法包含了这个商品所有的信息,就包括了分类)
	public ProductResult getByIdAllInfo(int productId);
	
	// 查询出来总记录数
	public long getCount();
	
	// 查询分页数据
	public List<Product> getPageDataNoWhere(int startIndex,int endIndex);
	
	//-------------------------------------------
	
	// 带条件的分页
	public List<Product> getPageDataWithWhere(int startIndex,int endIndex,String whereSQL,List<Object> params);
	
	// 查询出来总记录数
	public long getCount(String where,List<Object> params);

}
