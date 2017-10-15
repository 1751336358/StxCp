package com.stx.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import com.stx.dao.UserDao;
import com.stx.dao.impl.UserDaoImpl;
import com.stx.pojo.User;
import com.stx.service.UserService;
import com.stx.util.GetMD5;

//标记一个Service
@Service("userService")
public class UserServiceImpl implements UserService {
	
	
	//将新注册的用户添加到数据库
	public void addUserToDb(User user){
		//调用dao判断用户名是否注册
		this.userDao.addUserToDb(user);
	}
	
	//注册时判断用户名是否存在
	public int testUserIsRegister(User user){
		int count = userDao.testUserIsRegister(user);
		return count;
	}
	
	//根据username查到user对象
	public User selUserByUserName(String username){
		User user = this.userDao.selUserByUserName(username);
		return user;
	}
	
	//登录时验证登录的用户名和密码是否正确
	public int testUserLogin(User user){
		int count = userDao.testUserLogin(user);
		return count;
	}
	
	//ajax测试登录时用户名是否存在
	public int test_loginUsername(HttpServletRequest request){
		String username = request.getParameter("login_username");
		System.out.println("测试登录username="+username);
		return userDao.test_loginUsername(username);
	}
	
	//ajax测试登录密码是否输入正确 
	public int test_loginPwd(HttpServletRequest request){
		String password = request.getParameter("login_password");
		password = GetMD5.getMD5(password);
		return userDao.test_loginPwd(password);
	}
	//Ajax注册时测试用户名是否重复
	public int test_registerUsername(HttpServletRequest request){
		String username = request.getParameter("register_username");
		return userDao.test_loginUsername(username);
	}
	@Resource(name="userDao")
	private UserDaoImpl userDao;//注入
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}
	
	
}
