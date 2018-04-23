package com.zrgj.service.impl;

import java.util.Date;

import com.zrgj.bean.User;
import com.zrgj.dao.UserDao;
import com.zrgj.factory.BeanFactory;
import com.zrgj.service.UserService;
import com.zrgj.utils.MD5Util;

/**
 * 	用户服务
*/
public class UserServiceImpl implements UserService {

	private UserDao userDao = BeanFactory.getBean(UserDao.class);
	
	// 注册用户
	public void register(User user) {
		
		// 要维护其他属性
		user.setActivation(false); // 表示未激活
		user.setDeleted(true);     // 表示可用
		user.setPassword(MD5Util.encode(user.getPassword())); // 密码要签名
		user.setRegisterTime(new Date()); // 表示用户的注册时间
		
		userDao.insert(user);
	}

	// 用户登录
	public User login(String username, String password) {
		
		password = MD5Util.encode(password);
		
		return userDao.queryUserByUsernameAndPassword(username,password);
	}
}
