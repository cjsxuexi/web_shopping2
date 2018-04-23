package com.zrgj.service.impl;

import java.util.Date;

import com.zrgj.bean.User;
import com.zrgj.dao.UserDao;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.UserService;
import com.zrgj.utils.MD5Util;

/**
 * 	�û�����
*/
public class UserServiceImpl implements UserService {

	private UserDao userDao = BeanFactory.getBean(UserDao.class);
	
	// ע���û�
	public void register(User user) {
		
		// Ҫά����������
		user.setActivation(false); // ��ʾδ����
		user.setDeleted(true);     // ��ʾ����
		user.setPassword(MD5Util.encode(user.getPassword())); // ����Ҫǩ��
		user.setRegisterTime(new Date()); // ��ʾ�û���ע��ʱ��
		
		userDao.insert(user);
	}

	// �û���¼
	public User login(String username, String password) {
		
		password = MD5Util.encode(password);
		
		return userDao.queryUserByUsernameAndPassword(username,password);
	}
}
