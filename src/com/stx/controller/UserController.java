package com.stx.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stx.pojo.User;
import com.stx.service.UserService;
import com.stx.service.impl.UserServiceImpl;
import com.stx.util.GetMD5;

//���һ��Controller
@Controller("userController")
public class UserController {
	@Resource(name="userService")
	private UserServiceImpl userService;
	
	
	//ע��
	@RequestMapping("/register")
	public String register(HttpServletRequest request,HttpServletResponse response)  {
		System.out.println("ע��++++++++++++++");
		String name = request.getParameter("register_name");
		String username = request.getParameter("register_username");
		String password = request.getParameter("register_password");
		User user = new User();

		password = GetMD5.getMD5(password);
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		int count = userService.testUserIsRegister(user);
		//ע����û���������,�������ݿ�
		if(count == 0){
			userService.addUserToDb(user);
			return "redirect:/gotoRegisterSuccessPage";
			
		}
		//�ض���ע��ɹ���ҳ��
		return "index.jsp";
	}	
	
	
	//�ض���ע��ɹ�ҳ��
	@RequestMapping("/gotoRegisterSuccessPage")
	public ModelAndView gotoRegisterSuccessPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/jsp/RegisterSuccess.jsp");
		return modelAndView;
	}
	
	//��¼����
	@RequestMapping("/Login")
	public String Login(HttpServletRequest request,HttpSession session){
		User user = new User();
		String username = request.getParameter("login_username");
	
		String password = request.getParameter("login_password");
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		password = GetMD5.getMD5(password);
		
		user.setUsername(username);
		user.setPassword(password);
		int count = userService.testUserLogin(user);
		if(count == 1){
			//�Ӷ��򵽵�¼�ɹ�ҳ��
			System.out.println("��¼�ɹ�");
			user = userService.selUserByUserName(user.getUsername());
			session.setAttribute("user",user);
			return "/WEB-INF/jsp/LoginSuccess.jsp";
		}else{
			//���µ�¼
			System.out.println("��¼ʧ��");
			return "gotoIndex.jsp";
		}
	}
	//��ת����¼ҳ��
	@RequestMapping("/gotoLoginPage")
	public ModelAndView gotoLoginPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/jsp/login_register.jsp");
		return modelAndView;
	}
	//�ض��򵽵�¼�ɹ�ҳ��
	@RequestMapping("/gotoLoginSuccessPage")
	public ModelAndView gotoLoginSuccessPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/jsp/LoginSuccess.jsp");
		return modelAndView;
	}
	
	
	//ע��
	@RequestMapping("/unRegister")
	public String unRegister(HttpSession session){
		session.removeAttribute("user");
		return "gotoIndex.jsp";
	}
	
	//Ajax���Ե�¼���û����Ƿ����
	@RequestMapping("/test_loginUsername")
	public void test_loginUsername(HttpServletRequest request,HttpServletResponse response){
		
		int count = userService.test_loginUsername(request);
		//count==0:�û���������		count==1:�û�������
		try {
			response.getWriter().print(count);
		} catch (IOException e) {			
		}
	}
	//Ajax���Ե�¼ʱ���������Ƿ���ȷ
	@RequestMapping("/test_loginPwd")
	public void test_loginPwd(HttpServletRequest request,HttpServletResponse response){
		
		int count = userService.test_loginPwd(request);
		try {
			response.getWriter().print(count);
		} catch (IOException e) {
		}
	}
	//Ajaxע��ʱ�����û����Ƿ��ظ�
	@RequestMapping("/test_registerUsername")
	public void test_registerUsername(HttpServletRequest request,HttpServletResponse response){
		int count = userService.test_registerUsername(request);
		try {
			response.getWriter().print(count);
		} catch (IOException e) {
		}
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
}
