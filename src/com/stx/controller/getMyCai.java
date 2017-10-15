package com.stx.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stx.pojo.PageCai;
import com.stx.util.PageUtils;

public class getMyCai extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PageUtils pageUtils = new PageUtils();
		String current = request.getParameter("current");
		String userid = request.getParameter("userid");
		//根据不同的需求需要用到不同的sql语句传递到后台
		String sql = "select * from cai where userid="+userid;
		String totalSql = "select count(*) from cai where userid="+userid;
		PageCai pageCai = pageUtils.core(current,sql,totalSql);
		
		request.setAttribute("pageCai",pageCai);
		request.getRequestDispatcher("/WEB-INF/jsp/getMyCai.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
