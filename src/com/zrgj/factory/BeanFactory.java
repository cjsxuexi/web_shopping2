package com.zrgj.factory;

import java.io.InputStream;
import java.util.Properties;

/**
 * 	����bean�Ĺ���
*/
public final class BeanFactory {

	private BeanFactory(){
		
	}
	
	private static Properties properties = null;

	// ��ȡdb.properties�ļ�
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
	
	// ͨ��һ��bean�����ƻ�ȡһ��ʵ����д
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz){
		String beanName = clazz.getSimpleName();
		String beanValue = properties.getProperty(beanName);
		T t = null;
		try {
			Class<T> bean = (Class<T>) Class.forName(beanValue);
			t = bean.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("����beanʧ��", e);
		}
		return t;
	}
}
