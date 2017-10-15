package com.stx.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stx.pojo.Cai;
import com.stx.pojo.PageCai;
import com.stx.util.PageUtils;
//查看所有的菜谱，大数据分页
public class AllCai extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageUtils pageUtils = new PageUtils();
		String current = request.getParameter("current");
		//根据不同的需求需要用到不同的sql语句传递到后台
		String sql = "select * from cai";
		String totalSql = "select count(*) from cai";
		PageCai pageCai = pageUtils.core(current,sql,totalSql);
		request.setAttribute("pageCai",pageCai);
		request.getRequestDispatcher("/WEB-INF/jsp/AllCai.jsp").forward(request, response);	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
