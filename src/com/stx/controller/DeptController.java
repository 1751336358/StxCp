package com.stx.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stx.pojo.User;
import com.stx.service.UserService;
import com.stx.service.impl.DeptServiceImpl;
import com.stx.service.impl.UserServiceImpl;
import com.stx.util.GetMD5;

//标记一个Controller
@Controller("deptController")
public class DeptController {
	
	
	
	@Resource(name="deptService")
	private DeptServiceImpl deptService;

	public DeptServiceImpl getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptServiceImpl deptService) {
		this.deptService = deptService;
	}
	
	
	
	
}
