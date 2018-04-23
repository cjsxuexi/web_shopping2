package com.zrgj.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 	请求utils
*/
public final class RequestUtils {

	private RequestUtils(){
		
	}
	
	// 将map转换成一个bean对象
	public static <T> T map2bean(HttpServletRequest request,Class<T> clazz){
		Map<String,String[]> map = request.getParameterMap();
		T t = null;
		try {
			t = clazz.newInstance();
			BeanUtils.populate(t, map);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return t;
	}
}
