package com.zrgj.dao;

import org.apache.ibatis.annotations.Param;

import com.zrgj.bean.User;

/**
 * 	用户DAO
*/
public interface UserDao {

	public void insert(User user);

	// 根据用户名和密码查询用户
	public User queryUserByUsernameAndPassword(
				@Param("username")String username, 
				@Param("password")String password);
	
}
