package com.zrgj.utils;

import java.util.Collection;
import java.util.Collections;

/**
 * 	集合工具类
*/
public final class CollectionUtils {

	private CollectionUtils(){
		
	}
	
	// 给定一个集合返回一个集合
	public static Collection<?> returnCollection(Collection<?> collections){
		if(collections != null && !collections.isEmpty()){
			return collections;
		}
		return Collections.EMPTY_LIST;
	}
}
