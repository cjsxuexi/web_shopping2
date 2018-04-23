package com.zrgj.dao;

import org.apache.ibatis.annotations.Param;

import com.zrgj.bean.User;

/**
 * 	�û�DAO
*/
public interface UserDao {

	public void insert(User user);

	// �����û����������ѯ�û�
	public User queryUserByUsernameAndPassword(
				@Param("username")String username, 
				@Param("password")String password);
	
}
