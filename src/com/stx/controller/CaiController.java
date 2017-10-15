package com.stx.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stx.pojo.Cai;
import com.stx.pojo.Dept;
import com.stx.pojo.PageCai;
import com.stx.pojo.Say;
import com.stx.pojo.User;
import com.stx.service.CaiService;
import com.stx.service.DeptService;
import com.stx.service.SayService;
import com.stx.util.PageUtils;


//���һ��Controller
@Controller("caiController")
public class CaiController {
	
	
	@RequestMapping("/addCai")
	//������ף�����userid
	public ModelAndView addCai(HttpServletRequest request){
		//�õ�userid
		String userid = request.getParameter("userid");
		//�����ݿ�õ����еĲ�ϵ
		List deptList = deptService.selAllDept();
		//��ת��jspҳ��
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userid",userid);
		modelAndView.addObject("deptList", deptList);
		modelAndView.setViewName("/WEB-INF/jsp/AddCai.jsp");
		return modelAndView;
	}
	
	
/*	//�鿴���ף�����userid���õ������ݷ�ҳ����Ϊservlet
	@RequestMapping("/getMyCai")
	public ModelAndView getMyCai(int userid){
		List<Cai> caiList = caiService.getMyCai(userid);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("caiList",caiList);
		modelAndView.setViewName("/WEB-INF/jsp/getMyCai.jsp");
		return modelAndView;
	}*/
	
	//ɾ���ˣ�����cai.id
	@RequestMapping("/delCai")
	public String delCai(int id){
		//��ɾ��say���е����ۣ�����caiid=id
		caiService.delCai(id);
		return "redirect:/delRedirect";
		
	}
	//ɾ��������Ҫ�ض����ҵĲ���ҳ�棬��Ҫ������controller
	@RequestMapping("/delRedirect")
	public ModelAndView delRedirect(HttpSession session){
		
		String current =  "1";
		User u = (User)session.getAttribute("user");
		int userid = u.getId();
		//���ݲ�ͬ��������Ҫ�õ���ͬ��sql��䴫�ݵ���̨
		String sql = "select * from cai where userid="+userid;
		String totalSql = "select count(*) from cai where userid="+userid;
		PageUtils pageUtils = new PageUtils();
		PageCai pageCai = pageUtils.core(current,sql,totalSql);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageCai",pageCai);
		modelAndView.setViewName("/WEB-INF/jsp/getMyCai.jsp");
		return modelAndView;
	}	
	
	//��ѯ���ף�����cai.id
	@RequestMapping("/selCaiById")
	public ModelAndView selCaiById(int id){
		Cai cai = caiService.selCaiById(id);
		List<Dept> deptList = deptService.selAllDept();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cai",cai);
		modelAndView.addObject("deptList",deptList);
		modelAndView.setViewName("/WEB-INF/jsp/EditCai.jsp");
		return modelAndView;
	}
	
	//�޸Ĳ���
	@RequestMapping("/EditCai")
	public String EditCai(Cai cai){
		caiService.EditCai(cai);
		return "redirect:/delRedirect";	//�ض����ҵĲ����б�
	}
	
	//�鿴�˵����飬���޸��Ķ���
	@RequestMapping("/showCai")
	public ModelAndView showCai(int id){
		ModelAndView modelAndView = new ModelAndView();	
		//�޸��Ķ���
		caiService.addReadNum(id);
		//��ѯ��
		Cai cai = caiService.selCaiById(id);
		//��ѯ�����е�����,����cai.id
		List<Say> sayList = sayService.selSayByCaiid(id);
		
		modelAndView.addObject("cai",cai);
		modelAndView.addObject("sayList",sayList);
		modelAndView.setViewName("/WEB-INF/jsp/ShowCai.jsp");
		return modelAndView;
	}
	
/*	@RequestMapping("/test")
	public void test(Say say,HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("Ajax��ͨ");
		System.out.println(say.getCaiid());
		System.out.println(say.getDetail());
		System.out.println(say.getUserid());
		JSONObject json = JSONObject.fromObject(say);
		System.out.println(json.toString());
		response.getWriter().write(json.toString());
	}
	*/
	@Resource(name="caiService")
	private CaiService caiService;
	
	@Resource(name="deptService")
	private DeptService deptService;
	
	@Resource(name="sayService")
	private SayService sayService;

	public CaiService getCaiService() {
		return caiService;
	}

	public void setCaiService(CaiService caiService) {
		this.caiService = caiService;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public SayService getSayService() {
		return sayService;
	}

	public void setSayService(SayService sayService) {
		this.sayService = sayService;
	}
	
}
