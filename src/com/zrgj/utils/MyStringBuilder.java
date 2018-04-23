package com.zrgj.utils;

import java.util.List;

/**
 *	 我自己定义的 StringBuilder
*/
public class MyStringBuilder {

	private StringBuilder sb = new StringBuilder("");
	
	public MyStringBuilder append(List<String> list){
			
		if(list != null && list.size() > 0){
			for(String value : list){
				sb.append(" ").append(value).append(" ");
			}
		}
		return this;
	}
	
	public String toString(){
		return sb.toString();
	}
}
