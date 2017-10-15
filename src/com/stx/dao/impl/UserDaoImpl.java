package com.stx.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stx.dao.UserDao;
import com.stx.mapper.UserMapper;
import com.stx.pojo.User;

@Repository("userDao")
public class UserDaoImpl extends  SqlSessionDaoSupport implements UserDao {
	

	//将用户注册的数据插入数据库
	public void addUserToDb(User user) {
		this.getSqlSession().getMapper(UserMapper.class).addUserToDb(user);
		
	}
	
	//注册时判断用户名是否存在
	public int testUserIsRegister(User user){
		return this.getSqlSession().getMapper(UserMapper.class).testUserIsRegister(user);
	}
	
	//根据username查到user对象
	public User selUserByUserName(String username){
		return this.getSqlSession().getMapper(UserMapper.class).selUserByUserName(username);
	}
	
	//登录时验证登录的用户名和密码是否正确
	public int testUserLogin(User user){
		return this.getSqlSession().getMapper(UserMapper.class).testUserLogin(user);
	}
	
	//ajax测试登录时用户名是否存在,同时测试注册是也用到了此方法 
	public int test_loginUsername(String username){
		return this.getSqlSession().getMapper(UserMapper.class).test_loginUsername(username);
	}
	
	//ajax测试登录密码是否输入正确 
	public int test_loginPwd(String password){
		return this.getSqlSession().getMapper(UserMapper.class).test_loginPwd(password);
	}
	
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
}
