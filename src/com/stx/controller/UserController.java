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

//标记一个Controller
@Controller("userController")
public class UserController {
	@Resource(name="userService")
	private UserServiceImpl userService;
	
	
	//注册
	@RequestMapping("/register")
	public String register(HttpServletRequest request,HttpServletResponse response)  {
		System.out.println("注册++++++++++++++");
		String name = request.getParameter("register_name");
		String username = request.getParameter("register_username");
		String password = request.getParameter("register_password");
		User user = new User();

		password = GetMD5.getMD5(password);
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		int count = userService.testUserIsRegister(user);
		//注册的用户名不存在,插入数据库
		if(count == 0){
			userService.addUserToDb(user);
			return "redirect:/gotoRegisterSuccessPage";
			
		}
		//重定向到注册成功的页面
		return "index.jsp";
	}	
	
	
	//重定向到注册成功页面
	@RequestMapping("/gotoRegisterSuccessPage")
	public ModelAndView gotoRegisterSuccessPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/jsp/RegisterSuccess.jsp");
		return modelAndView;
	}
	
	//登录界面
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
			//从定向到登录成功页面
			System.out.println("登录成功");
			user = userService.selUserByUserName(user.getUsername());
			session.setAttribute("user",user);
			return "/WEB-INF/jsp/LoginSuccess.jsp";
		}else{
			//重新登录
			System.out.println("登录失败");
			return "gotoIndex.jsp";
		}
	}
	//跳转到登录页面
	@RequestMapping("/gotoLoginPage")
	public ModelAndView gotoLoginPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/jsp/login_register.jsp");
		return modelAndView;
	}
	//重定向到登录成功页面
	@RequestMapping("/gotoLoginSuccessPage")
	public ModelAndView gotoLoginSuccessPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/WEB-INF/jsp/LoginSuccess.jsp");
		return modelAndView;
	}
	
	
	//注销
	@RequestMapping("/unRegister")
	public String unRegister(HttpSession session){
		session.removeAttribute("user");
		return "gotoIndex.jsp";
	}
	
	//Ajax测试登录的用户名是否存在
	@RequestMapping("/test_loginUsername")
	public void test_loginUsername(HttpServletRequest request,HttpServletResponse response){
		
		int count = userService.test_loginUsername(request);
		//count==0:用户名不存在		count==1:用户名存在
		try {
			response.getWriter().print(count);
		} catch (IOException e) {			
		}
	}
	//Ajax测试登录时密码输入是否正确
	@RequestMapping("/test_loginPwd")
	public void test_loginPwd(HttpServletRequest request,HttpServletResponse response){
		
		int count = userService.test_loginPwd(request);
		try {
			response.getWriter().print(count);
		} catch (IOException e) {
		}
	}
	//Ajax注册时测试用户名是否重复
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
