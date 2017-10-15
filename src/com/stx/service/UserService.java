package com.stx.service;

import javax.servlet.http.HttpServletRequest;

import com.stx.pojo.User;

public interface UserService {
	//将新注册的用户添加到数据库
	public void addUserToDb(User user);
	
	//注册时判断用户名是否存在
	public int testUserIsRegister(User user);
	
	//根据username查到user对象
	public User selUserByUserName(String username);
	
	//登录时验证登录的用户名和密码是否正确
	public int testUserLogin(User user);
	
	//ajax测试登录时用户名是否存在,同时测试注册是也用到了此方法 
	public int test_loginUsername(HttpServletRequest request);
	
	//ajax测试登录密码是否输入正确 
	public int test_loginPwd(HttpServletRequest request);
	
	//Ajax注册时测试用户名是否重复
	public int test_registerUsername(HttpServletRequest request);
}