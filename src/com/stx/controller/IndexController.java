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

//���һ��Controller
@Controller("indexController")
public class IndexController {

	
	@RequestMapping("/gotoIndex")
	public ModelAndView gotoIndex(){
		ModelAndView modelAndView = new ModelAndView();
		//�����ݿ�������ʱ��
	/*	String time = TimeFormat.getLocalTime();
		indexService.insertTime(time);*/
		//������ϵ�˵�
		List<Dept> deptList = deptService.selAllDept();
		//���Ų���
		List<Cai> hotCai = indexService.hotCai();
	//	int getFangwenNum = indexService.getFangwenNum();	//��ѯ��������
		//��ϵ�Ƽ�����
		for(Dept d:deptList){
			modelAndView.addObject("tuijian"+d.getId(),indexService.getTuijianCai(d.getId()));
		}
		modelAndView.addObject("deptList",deptList);
		modelAndView.addObject("hotCai",hotCai);
	//	modelAndView.addObject("getFangwenNum", getFangwenNum);
		modelAndView.setViewName("index.jsp");
		return modelAndView;
	}
	
	//ʹ��Ajax����deptid��ȡ����ϵ�Ƽ�����
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
		System.out.println("�����ǣ�"+listCai.size());
		//��Java����תΪjson
		JSONArray arrayToJson = JSONArray.fromObject(listCai);
		try {
			response.getWriter().print(arrayToJson.toString());
		} catch (IOException e){
		}
	}
	//���ݹؼ��ֲ�ѯ��صĲ���
	@RequestMapping("/selCaiByKey")
	public ModelAndView selCaiByKey(String key){
		System.out.println("�ؼ����ǣ�"+key);
		//�����ݿ����ѯ����key�Ĳ���
		List<Cai> caiList = indexService.selCaiByKey(key);
		ModelAndView modelAndView = new  ModelAndView();
		modelAndView.addObject("caiList",caiList);
		modelAndView.setViewName("/WEB-INF/jsp/SelCaiByKey.jsp");	//��ת�����б�ҳ��
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
