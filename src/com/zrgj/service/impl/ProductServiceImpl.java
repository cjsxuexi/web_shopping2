package com.zrgj.service.impl;

import java.util.List;

import com.zrgj.bean.PageView;
import com.zrgj.bean.Product;
import com.zrgj.bean.result.ProductResult;
import com.zrgj.dao.ProductDao;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.ProductService;

/**
 * ��ƷService
 */
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao = BeanFactory.getBean(ProductDao.class);

	// ������Ʒ
	public void addProduct(Product product) {
		product.setPdeleted(true);
		productDao.insert(product);
	}

	// ��ѯȫ����Ʒ
	public List<Product> queryProducts() {
		return productDao.getProducts();
	}

	// ������Ʒidɾ����Ʒ
	public void deleteProductById(int productId) {
		productDao.deleteById(productId);
	}

	// ������Ʒid��ѯ��Ʒ
	public Product queryProductById(int productId) {
		return productDao.getById(productId);
	}

	// ������Ʒid��ѯ����Ʒ��������Ϣ
	public ProductResult queryProductAllInfoById(int productId) {
		return productDao.getByIdAllInfo(productId);
	}

	// ��ҳ��ѯ����,������������ pageNum��ʾ���ǵ�ǰ�ڼ�ҳ
	public PageView<Product> queryPageDataNoWhere(Integer pageNum) {
		return this.queryPageDataWithWhere(pageNum, null, null);
	}

	// ��ҳ��ѯ����,���������� 	pageNum��ʾ���ǵ�ǰ�ڼ�ҳ
	public PageView<Product> queryPageDataWithWhere(Integer pageNum,String whereSQL,List<Object> whereParams) {

		// �жϵ�ǰҳ�Ƿ�Ϊ��
		Integer currentPageNum = 1;
		if (pageNum != null && pageNum > 0) {
			currentPageNum = pageNum;
		}

		// ��ȡ�ܼ�¼��
		int count = (int) productDao.getCount(whereSQL,whereParams);
		PageView<Product> pageView = new PageView<>(count, currentPageNum, 2);
		List<Product> products = productDao.getPageDataWithWhere(pageView.getStartIndex(), pageView.getPageSize(),whereSQL,whereParams);
		pageView.setDatas(products);

		return pageView;
	}

}
