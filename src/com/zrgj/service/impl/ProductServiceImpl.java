package com.zrgj.service.impl;

import java.util.List;

import com.zrgj.bean.PageView;
import com.zrgj.bean.Product;
import com.zrgj.bean.result.ProductResult;
import com.zrgj.dao.ProductDao;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.ProductService;

/**
 * 商品Service
 */
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao = BeanFactory.getBean(ProductDao.class);

	// 增加商品
	public void addProduct(Product product) {
		product.setPdeleted(true);
		productDao.insert(product);
	}

	// 查询全部商品
	public List<Product> queryProducts() {
		return productDao.getProducts();
	}

	// 根据商品id删除商品
	public void deleteProductById(int productId) {
		productDao.deleteById(productId);
	}

	// 根据商品id查询商品
	public Product queryProductById(int productId) {
		return productDao.getById(productId);
	}

	// 根据商品id查询此商品的所有信息
	public ProductResult queryProductAllInfoById(int productId) {
		return productDao.getByIdAllInfo(productId);
	}

	// 分页查询数据,不带条件参数 pageNum表示的是当前第几页
	public PageView<Product> queryPageDataNoWhere(Integer pageNum) {
		return this.queryPageDataWithWhere(pageNum, null, null);
	}

	// 分页查询数据,带条件参数 	pageNum表示的是当前第几页
	public PageView<Product> queryPageDataWithWhere(Integer pageNum,String whereSQL,List<Object> whereParams) {

		// 判断当前页是否为空
		Integer currentPageNum = 1;
		if (pageNum != null && pageNum > 0) {
			currentPageNum = pageNum;
		}

		// 获取总记录树
		int count = (int) productDao.getCount(whereSQL,whereParams);
		PageView<Product> pageView = new PageView<>(count, currentPageNum, 2);
		List<Product> products = productDao.getPageDataWithWhere(pageView.getStartIndex(), pageView.getPageSize(),whereSQL,whereParams);
		pageView.setDatas(products);

		return pageView;
	}

}
