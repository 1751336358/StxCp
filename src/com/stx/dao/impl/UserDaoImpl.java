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
	

	//���û�ע������ݲ������ݿ�
	public void addUserToDb(User user) {
		this.getSqlSession().getMapper(UserMapper.class).addUserToDb(user);
		
	}
	
	//ע��ʱ�ж��û����Ƿ����
	public int testUserIsRegister(User user){
		return this.getSqlSession().getMapper(UserMapper.class).testUserIsRegister(user);
	}
	
	//����username�鵽user����
	public User selUserByUserName(String username){
		return this.getSqlSession().getMapper(UserMapper.class).selUserByUserName(username);
	}
	
	//��¼ʱ��֤��¼���û����������Ƿ���ȷ
	public int testUserLogin(User user){
		return this.getSqlSession().getMapper(UserMapper.class).testUserLogin(user);
	}
	
	//ajax���Ե�¼ʱ�û����Ƿ����,ͬʱ����ע����Ҳ�õ��˴˷��� 
	public int test_loginUsername(String username){
		return this.getSqlSession().getMapper(UserMapper.class).test_loginUsername(username);
	}
	
	//ajax���Ե�¼�����Ƿ�������ȷ 
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
