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


//标记一个Controller
@Controller("caiController")
public class CaiController {
	
	
	@RequestMapping("/addCai")
	//发表菜谱，根据userid
	public ModelAndView addCai(HttpServletRequest request){
		//得到userid
		String userid = request.getParameter("userid");
		//查数据库得到所有的菜系
		List deptList = deptService.selAllDept();
		//跳转到jsp页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userid",userid);
		modelAndView.addObject("deptList", deptList);
		modelAndView.setViewName("/WEB-INF/jsp/AddCai.jsp");
		return modelAndView;
	}
	
	
/*	//查看菜谱，根据userid，用到大数据分页，改为servlet
	@RequestMapping("/getMyCai")
	public ModelAndView getMyCai(int userid){
		List<Cai> caiList = caiService.getMyCai(userid);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("caiList",caiList);
		modelAndView.setViewName("/WEB-INF/jsp/getMyCai.jsp");
		return modelAndView;
	}*/
	
	//删除菜，根据cai.id
	@RequestMapping("/delCai")
	public String delCai(int id){
		//先删除say表中的评论，根据caiid=id
		caiService.delCai(id);
		return "redirect:/delRedirect";
		
	}
	//删除过后需要重定向到我的菜谱页面，需要借助此controller
	@RequestMapping("/delRedirect")
	public ModelAndView delRedirect(HttpSession session){
		
		String current =  "1";
		User u = (User)session.getAttribute("user");
		int userid = u.getId();
		//根据不同的需求需要用到不同的sql语句传递到后台
		String sql = "select * from cai where userid="+userid;
		String totalSql = "select count(*) from cai where userid="+userid;
		PageUtils pageUtils = new PageUtils();
		PageCai pageCai = pageUtils.core(current,sql,totalSql);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageCai",pageCai);
		modelAndView.setViewName("/WEB-INF/jsp/getMyCai.jsp");
		return modelAndView;
	}	
	
	//查询菜谱，根据cai.id
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
	
	//修改菜谱
	@RequestMapping("/EditCai")
	public String EditCai(Cai cai){
		caiService.EditCai(cai);
		return "redirect:/delRedirect";	//重定向到我的菜谱列表
	}
	
	//查看菜的详情，并修改阅读量
	@RequestMapping("/showCai")
	public ModelAndView showCai(int id){
		ModelAndView modelAndView = new ModelAndView();	
		//修改阅读量
		caiService.addReadNum(id);
		//查询菜
		Cai cai = caiService.selCaiById(id);
		//查询出所有的评论,根据cai.id
		List<Say> sayList = sayService.selSayByCaiid(id);
		
		modelAndView.addObject("cai",cai);
		modelAndView.addObject("sayList",sayList);
		modelAndView.setViewName("/WEB-INF/jsp/ShowCai.jsp");
		return modelAndView;
	}
	
/*	@RequestMapping("/test")
	public void test(Say say,HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("Ajax测通");
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
