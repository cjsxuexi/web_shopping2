package com.zrgj.service;

import com.zrgj.bean.User;

/**
 * 	�û�Service
*/
public interface UserService {

	// �û�ע��
	public void register(User user);

	// �û���¼
	public User login(String username, String password);
	
}
