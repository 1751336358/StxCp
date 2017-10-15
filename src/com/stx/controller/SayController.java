package com.stx.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stx.pojo.Say;
import com.stx.service.SayService;


//���һ��Controller
@Controller("sayController")
public class SayController {
	
	@RequestMapping("/addSay")
	public String addSay(Say say){
		sayService.addSay(say);
		return "gotoIndex.jsp";
	}
	
	//������µ�����
	@RequestMapping("/selNewSay")
	public void selNewSay(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		Say say = sayService.selNewSay();
		JSONObject json = JSONObject.fromObject(say);
		System.out.println(json.toString());
		response.getWriter().print(json.toString());
	}
	
	//ɾ�����ۣ�����sayid
	@RequestMapping("/delSayBysayid")
	public void delSayBysayid(HttpServletRequest request,HttpServletResponse response){
		int sayid = Integer.parseInt(request.getParameter("sayid"));
		System.out.println("Ҫɾ�����۵ĵ�sayid"+sayid);
		sayService.delSayBysayid(sayid);
		try {
			response.getWriter().print("ok");
		} catch (IOException e) {
		}
	}
	@Resource(name="sayService")
	private SayService sayService;

	public SayService getSayService() {
		return sayService;
	}

	public void setSayService(SayService sayService) {
		this.sayService = sayService;
	}
	
	
}
