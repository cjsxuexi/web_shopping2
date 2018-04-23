package com.zrgj.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zrgj.bean.OrderItem;
import com.zrgj.dao.OrderItemDao;
import com.zrgj.exception.DBException;
import com.zrgj.utils.DataSouceUtils;

/**
 *	订单明细 
*/
public class OrderItemDaoImpl implements OrderItemDao {

	private QueryRunner qr = new QueryRunner(DataSouceUtils.getDataSource());
	
	// 批量保存订单明细
	public void insertBatch(List<OrderItem> orderItems) {

		try {
			String sql = "insert into t_order_item(productName,productPrice,qunantity,orderItemPrice,order_id) values(?,?,?,?,?)";
			
			Object[][] params = new Object[orderItems.size()][5];
			for(int i = 0 ; i < orderItems.size() ; i++){
				OrderItem orderItem = orderItems.get(i);
				params[i] = new Object[]{orderItem.getProductName(),orderItem.getProductPrice(),
										orderItem.getQunantity(),orderItem.getOrderItemPrice(),
										orderItem.getOrder().getId()};
			}
			qr.batch(sql, params);
		} catch (Exception e) {
			throw new DBException(e);
		}
	}

	// 根据订单的id去查询订单明细
	public List<OrderItem> getOrderItemByOrderId(Integer orderId) {
		List<OrderItem> orderItems = null;
		try {
			String sql = "select productName,productPrice,qunantity,orderItemPrice,order_id from t_order_item where order_id = ?";
			orderItems = qr.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), orderId);
		} catch (Exception e) {
			throw new DBException(e);
		}
		return orderItems;
	}
}

