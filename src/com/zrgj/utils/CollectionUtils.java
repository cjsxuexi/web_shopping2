package com.zrgj.utils;

import java.util.Collection;
import java.util.Collections;

/**
 * 	���Ϲ�����
*/
public final class CollectionUtils {

	private CollectionUtils(){
		
	}
	
	// ����һ�����Ϸ���һ������
	public static Collection<?> returnCollection(Collection<?> collections){
		if(collections != null && !collections.isEmpty()){
			return collections;
		}
		return Collections.EMPTY_LIST;
	}
}
