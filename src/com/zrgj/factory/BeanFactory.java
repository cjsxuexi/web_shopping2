package com.zrgj.factory;

import java.io.InputStream;
import java.util.Properties;

/**
 * 	创建bean的工厂
*/
public final class BeanFactory {

	private BeanFactory(){
		
	}
	
	private static Properties properties = null;

	// 读取db.properties文件
	static{
		InputStream is = null;
		try {
			is = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
			properties = new Properties();
			properties.load(is);
			is.close();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	// 通过一个bean的名称获取一个实例读写
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz){
		String beanName = clazz.getSimpleName();
		String beanValue = properties.getProperty(beanName);
		T t = null;
		try {
			Class<T> bean = (Class<T>) Class.forName(beanValue);
			t = bean.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("创建bean失败", e);
		}
		return t;
	}
}
