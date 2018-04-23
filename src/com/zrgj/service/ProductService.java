package com.zrgj.service;

import java.util.List;

import com.zrgj.bean.PageView;
import com.zrgj.bean.Product;
import com.zrgj.bean.result.ProductResult;

// 商品Service
public interface ProductService {

	// 增加商品
	public void addProduct(Product product);
	
	// 查询所有的图书
	public List<Product> queryProducts();

	// 根据商品id删除商品
	public void deleteProductById(int productId);
	
	// 根据商品id商品
	public Product queryProductById(int productId);

	// 根据商品id查询商品,(包含了全部信息)
	public ProductResult queryProductAllInfoById(int parseInt);
	
	// 分页图书列表
	public PageView<Product> queryPageDataNoWhere(Integer pageNum);
	
	// （带条件的）分页查询图书列表
	public PageView<Product> queryPageDataWithWhere(Integer pageNum,String whereSQL,List<Object> whereParams);
	
	
}




