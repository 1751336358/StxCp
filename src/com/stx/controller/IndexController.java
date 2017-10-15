package com.stx.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stx.pojo.Cai;
import com.stx.pojo.Dept;
import com.stx.service.DeptService;
import com.stx.service.IndexService;
import com.stx.util.TimeFormat;

//标记一个Controller
@Controller("indexController")
public class IndexController {

	
	@RequestMapping("/gotoIndex")
	public ModelAndView gotoIndex(){
		ModelAndView modelAndView = new ModelAndView();
		//向数据库插入访问时间
	/*	String time = TimeFormat.getLocalTime();
		indexService.insertTime(time);*/
		//各个菜系菜单
		List<Dept> deptList = deptService.selAllDept();
		//热门菜谱
		List<Cai> hotCai = indexService.hotCai();
	//	int getFangwenNum = indexService.getFangwenNum();	//查询出访问量
		//各系推荐菜谱
		for(Dept d:deptList){
			modelAndView.addObject("tuijian"+d.getId(),indexService.getTuijianCai(d.getId()));
		}
		modelAndView.addObject("deptList",deptList);
		modelAndView.addObject("hotCai",hotCai);
	//	modelAndView.addObject("getFangwenNum", getFangwenNum);
		modelAndView.setViewName("index.jsp");
		return modelAndView;
	}
	
	//使用Ajax根据deptid调取各菜系推荐菜谱
	@RequestMapping("/getTuijianCai")
	public void getTuijianCai(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
		}
		
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		System.out.println("deptid="+deptid);
		List<Cai> listCai = indexService.getTuijianCai(deptid);
		System.out.println("长度是："+listCai.size());
		//将Java对象转为json
		JSONArray arrayToJson = JSONArray.fromObject(listCai);
		try {
			response.getWriter().print(arrayToJson.toString());
		} catch (IOException e){
		}
	}
	//根据关键字查询相关的菜谱
	@RequestMapping("/selCaiByKey")
	public ModelAndView selCaiByKey(String key){
		System.out.println("关键字是："+key);
		//从数据库里查询包含key的菜谱
		List<Cai> caiList = indexService.selCaiByKey(key);
		ModelAndView modelAndView = new  ModelAndView();
		modelAndView.addObject("caiList",caiList);
		modelAndView.setViewName("/WEB-INF/jsp/SelCaiByKey.jsp");	//跳转到项列表页面
		return modelAndView;
	}
	@Resource(name="indexService")
	private IndexService indexService;
	@Resource(name="deptService")
	private DeptService deptService;
	public IndexService getIndexService() {
		return indexService;
	}
	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}
	public DeptService getDeptService() {
		return deptService;
	}
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}


	
	
}
