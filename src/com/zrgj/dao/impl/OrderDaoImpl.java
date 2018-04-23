package com.zrgj.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zrgj.bean.Order;
import com.zrgj.bean.User;
import com.zrgj.dao.OrderDao;
import com.zrgj.exception.DBException;
import com.zrgj.utils.DataSouceUtils;
import com.zrgj.vo.OrderQueryVo;

public class OrderDaoImpl implements OrderDao {

	private QueryRunner qr = new QueryRunner();
	
	// 新增订单
	public void insert(Order order) {
		Connection connection = null;
		try {
			connection = DataSouceUtils.getConnection();
			// 1、保存订单
			String sql = "insert into t_order(submitTime,orderState,orderNo,orderPrice,paymentTime,message,paymentType,user_id) values(?,?,?,?,?,?,?,?)";
			
			qr.update(connection, sql, new Object[]{
					order.getSubmitTime(),order.getOrderState(),order.getOrderNo(),
					order.getOrderPrice(),order.getPaymentTime(),order.getMessage(),
					order.getPaymentType(),order.getUser().getId()
			});
			
			// 2、获取主键的id
			sql = "select last_insert_id()";
			Long id = (Long)qr.query(connection, sql, new ScalarHandler());
			order.setId(id.intValue());
		} catch (Exception e) {
			throw new DBException(e);
		}finally{
			DataSouceUtils.close(connection, null, null);
		}
	}
	
	// 根据用户的id查询订单
	public List<Order> getOrderByOrderQueryVo(OrderQueryVo orderQueryVo){
		List<Order> orders = null;
		Connection connection = null;
		try {
			connection = DataSouceUtils.getConnection();
			
			Order queryOrder = orderQueryVo.getOrder();
			User user = orderQueryVo.getUser();
			List<Object> params = new ArrayList<Object>();
			String whereSQL = "";
			if(user != null && user.getId() != null){
				whereSQL = whereSQL + " and user_id = ?";
				params.add(user.getId());
			}
			if(queryOrder != null && queryOrder.getOrderState() != null){
				whereSQL = whereSQL + " and orderState = ?";
				params.add(queryOrder.getOrderState());
			}
			if(queryOrder != null && queryOrder.getOrderNo() != null){
				whereSQL = whereSQL + " and orderNo = ?";
				params.add(queryOrder.getOrderNo());
			}
			String sql = "select id,submitTime,orderState,orderNo,orderPrice,paymentTime,message,paymentType,user_id "
						 + " from t_order where 1 = 1 " + whereSQL;
			
			orders = qr.query(connection,sql, new BeanListHandler<Order>(Order.class), params.toArray());
		} catch (Exception e) {
			throw new DBException(e);
		}finally{
			DataSouceUtils.close(connection, null, null);
		}
		return orders;
	}

	// 根据订单id查询订单
	@SuppressWarnings("deprecation")
	public Order getOrderById(int orderId) {
		
		Order order = null;
		Connection connection = null;
		try {
			
			connection =  DataSouceUtils.getConnection();
			
			String sql = "select id,submitTime,orderState,orderNo,orderPrice,paymentTime,message,paymentType,user_id "
						+ "	from t_order where id = ?";
			order = qr.query(connection,sql,orderId,new BeanHandler<Order>(Order.class));
		} catch (Exception e) {
			throw new DBException(e);
		}finally{
			DataSouceUtils.close(connection, null, null);
		}
		return order;
	}

	// 更新订单
	public void update(Order order) {
		Connection connection = null;
		try {
			connection = DataSouceUtils.getConnection();
			String sql = "update t_order set orderState = ? where id = ?";
			Object[] params = new Object[]{order.getOrderState(),order.getId()};
			qr.update(connection, sql,params);
		} catch (Exception e) {
			throw new DBException(e);
		}finally{
			DataSouceUtils.close(connection, null, null);
		}
	}
}
