package com.zrgj.utils;

// 字符串工具类
public final class StringUtils {

	// 校验字符串是否为空
	public static boolean isEmpty(String value){
	
		if(value == null || "".equals(value.trim())){
			return true;
		}
		return false;
	}
}
