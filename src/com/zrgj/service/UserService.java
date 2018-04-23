package com.zrgj.service;

import com.zrgj.bean.User;

/**
 * 	用户Service
*/
public interface UserService {

	// 用户注册
	public void register(User user);

	// 用户登录
	public User login(String username, String password);
	
}
