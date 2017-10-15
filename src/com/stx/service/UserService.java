package com.stx.service;

import javax.servlet.http.HttpServletRequest;

import com.stx.pojo.User;

public interface UserService {
	//����ע����û���ӵ����ݿ�
	public void addUserToDb(User user);
	
	//ע��ʱ�ж��û����Ƿ����
	public int testUserIsRegister(User user);
	
	//����username�鵽user����
	public User selUserByUserName(String username);
	
	//��¼ʱ��֤��¼���û����������Ƿ���ȷ
	public int testUserLogin(User user);
	
	//ajax���Ե�¼ʱ�û����Ƿ����,ͬʱ����ע����Ҳ�õ��˴˷��� 
	public int test_loginUsername(HttpServletRequest request);
	
	//ajax���Ե�¼�����Ƿ�������ȷ 
	public int test_loginPwd(HttpServletRequest request);
	
	//Ajaxע��ʱ�����û����Ƿ��ظ�
	public int test_registerUsername(HttpServletRequest request);
}