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

//���һ��Service
@Service("userService")
public class UserServiceImpl implements UserService {
	
	
	//����ע����û���ӵ����ݿ�
	public void addUserToDb(User user){
		//����dao�ж��û����Ƿ�ע��
		this.userDao.addUserToDb(user);
	}
	
	//ע��ʱ�ж��û����Ƿ����
	public int testUserIsRegister(User user){
		int count = userDao.testUserIsRegister(user);
		return count;
	}
	
	//����username�鵽user����
	public User selUserByUserName(String username){
		User user = this.userDao.selUserByUserName(username);
		return user;
	}
	
	//��¼ʱ��֤��¼���û����������Ƿ���ȷ
	public int testUserLogin(User user){
		int count = userDao.testUserLogin(user);
		return count;
	}
	
	//ajax���Ե�¼ʱ�û����Ƿ����
	public int test_loginUsername(HttpServletRequest request){
		String username = request.getParameter("login_username");
		System.out.println("���Ե�¼username="+username);
		return userDao.test_loginUsername(username);
	}
	
	//ajax���Ե�¼�����Ƿ�������ȷ 
	public int test_loginPwd(HttpServletRequest request){
		String password = request.getParameter("login_password");
		password = GetMD5.getMD5(password);
		return userDao.test_loginPwd(password);
	}
	//Ajaxע��ʱ�����û����Ƿ��ظ�
	public int test_registerUsername(HttpServletRequest request){
		String username = request.getParameter("register_username");
		return userDao.test_loginUsername(username);
	}
	@Resource(name="userDao")
	private UserDaoImpl userDao;//ע��
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}
	
	
}
