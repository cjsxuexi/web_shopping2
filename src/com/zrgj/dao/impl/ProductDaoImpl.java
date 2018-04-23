package com.zrgj.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zrgj.bean.Product;
import com.zrgj.bean.result.ProductResult;
import com.zrgj.dao.ProductDao;
import com.zrgj.exception.DBException;
import com.zrgj.utils.DataSouceUtils;
import com.zrgj.utils.MyStringBuilder;
import com.zrgj.utils.StringUtils;

/**
 * ��Ʒdao
 */
public class ProductDaoImpl implements ProductDao {

	private QueryRunner qr = new QueryRunner(DataSouceUtils.getDataSource());

	// ������Ʒ
	public void insert(Product product) {

		try {
			String sql = "insert into t_product(id,pname,pdesc,pdeleted,pprice,pauthor,"
					+ "paddress,pimageurl,producttype_id) values(null,?,?,?,?,?,?,?,?)";

			Object[] params = new Object[] { product.getPname(), product.getPdesc(), product.isPdeleted(),
					product.getPprice(), product.getPauthor(), product.getPaddress(), product.getPimageUrl(),
					product.getProductType().getId() };

			qr.update(sql, params);
		} catch (Exception e) {
			throw new DBException(e);
		}
	}

	// ��ѯ���е���Ʒ
	public List<Product> getProducts() {

		List<Product> products = null;
		try {
			String sql = "select id ,pname,pdesc,pdeleted,pprice,pauthor,"
					+ "paddress,pimageurl,producttype_id from t_product";

			products = qr.query(sql, new BeanListHandler<Product>(Product.class));

		} catch (Exception e) {
			throw new DBException(e);
		}
		return products;
	}

	// ������Ʒidɾ����Ʒ
	public void deleteById(int productId) {
		try {
			String sql = "delete from t_product where id = ?";
			qr.update(sql, productId);
		} catch (Exception e) {
			throw new DBException(e);
		}
	}

	// ������Ʒidɾ����Ʒ
	public Product getById(int productId) {
		Product product = null;
		try {
			String sql = "select id ,pname,pdesc,pdeleted,pprice,pauthor,"
					+ "paddress,pimageurl,producttype_id from t_product where id = ?";
			product = qr.query(sql, new BeanHandler<Product>(Product.class), productId);
		} catch (Exception e) {
			throw new DBException(e);
		}
		return product;
	}

	// ������Ʒid��ѯ��Ʒ��������ȫ����Ϣ
	public ProductResult getByIdAllInfo(int productId) {

		ProductResult productResult = null;

		try {
			List<String> list = Arrays.asList("SELECT",
					"p.*,pt1.id secondLevelId,pt1.ptname secondLevelName,pt2.id firstLevelId,pt2.ptname firsetLevelName", "FROM",
					"t_product p , t_producttype pt1 , t_producttype pt2", "WHERE", "p.id = ? and",
					"p.producttype_id = pt1.id AND", "pt1.parent_id = pt2.id");

			String sql = new MyStringBuilder().append(list).toString();

			productResult = qr.query(sql, new BeanHandler<ProductResult>(ProductResult.class), productId);
		} catch (Exception e) {
			throw new DBException(e);
		}
		return productResult;
	}

	// ��ȡ�ܼ�¼����
	public long getCount() {
		long count = 0;
		try {
			String sql = "select count(id) from t_product";
			count = (long)qr.query(sql, new ScalarHandler());
		} catch (Exception e) {
			throw new DBException(e);
		}
		return count;
	}
	
	// ��ѯ��ҳ����
	public List<Product> getPageDataNoWhere(int startIndex, int endIndex) {
		List<Product> products = null;
		try {
			String sql = "select id ,pname,pdesc,pdeleted,pprice,pauthor,"
					+ "paddress,pimageurl,producttype_id from t_product limit ?,?";
			
			Object[] params = new Object[]{startIndex,endIndex};
			products = qr.query(sql, new BeanListHandler<Product>(Product.class),params);
		} catch (Exception e) {
			throw new DBException(e);
		}
		return products;
	}

	// -------------------------------
	
	// ����Ǵ������ķ�ҳ
	@Override
	public List<Product> getPageDataWithWhere(int startIndex, int endIndex, String whereSQL, List<Object> whereParams) {
		List<Product> products = null;
		try {
			// where����
			String where = StringUtils.isEmpty(whereSQL) ? "" : " where " + whereSQL;
			
			// sql���
			String sql = "select id ,pname,pdesc,pdeleted,pprice,pauthor,"
					+ "paddress,pimageurl,producttype_id from t_product "+where+" limit ?,?";
			
			List<Object> params = new ArrayList<Object>();
			if(whereParams != null && whereParams.size() > 0){
				params.addAll(whereParams);
			}
			params.add(startIndex);
			params.add(endIndex);
			products = qr.query(sql, new BeanListHandler<Product>(Product.class),params.toArray());
		} catch (Exception e) {
			throw new DBException(e);
		}
		return products;
	}

	// ����Ǵ������Ĳ�ѯ����
	public long getCount(String whereSQL, List<Object> whereParams) {
		long count = 0;
		try {
			// where����
			String where = StringUtils.isEmpty(whereSQL) ? "" : " where " + whereSQL;
			Object[] params = whereParams == null ? null : whereParams.toArray();
			String sql = "select count(id) from t_product" + where;
			count = (long)qr.query(sql, new ScalarHandler(),params);
		} catch (Exception e) {
			throw new DBException(e);
		}
		return count;
	}
}
