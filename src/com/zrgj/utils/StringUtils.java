package com.zrgj.utils;

// �ַ���������
public final class StringUtils {

	// У���ַ����Ƿ�Ϊ��
	public static boolean isEmpty(String value){
	
		if(value == null || "".equals(value.trim())){
			return true;
		}
		return false;
	}
}
