package com.zrgj.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * 	数据源工具类
*/
public final class DataSouceUtils {
	
	private DataSouceUtils(){
		
	}
	
	private static DataSource ds = null;
	
	static{
		InputStream is = null;
		try {
			is = DataSouceUtils.class.getClassLoader().getResourceAsStream("dbcp.properties");
			Properties prop = new Properties();
			prop.load(is);
			
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	
	public static void close(Connection connection,PreparedStatement ps ,ResultSet rs ){
		try {
			if(connection != null){
				connection.close();
			}
		} catch (Exception e2) {
		}
		try {
			if(ps != null){
				ps.close();
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		try {
			if(rs != null){
				rs.close();
			}
		} catch (Exception e2) {
		}
	}
	
}
