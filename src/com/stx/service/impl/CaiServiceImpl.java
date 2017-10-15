package com.stx.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.stx.dao.impl.CaiDaoImpl;
import com.stx.pojo.Cai;
import com.stx.service.CaiService;
import com.stx.util.HtmlFilter;
import com.stx.util.TimeFormat;


//标记一个Service
@Service("caiService")
public class CaiServiceImpl implements CaiService {

	//将新增的菜谱插入到数据库中
	public void addCai(Cai cai){
		
		cai.setTime(TimeFormat.getLocalTime());	//设置添加菜谱的时间
		cai.setName(HtmlFilter.filter(cai.getName()));
		cai.setZhuliao(HtmlFilter.filter(cai.getZhuliao()));
		cai.setFuliao(HtmlFilter.filter(cai.getFuliao()));
		cai.setDetail(HtmlFilter.filter(cai.getDetail()));
		caiDao.addCai(cai);
	}
	
	//查询我的菜谱，根据userd
	public List<Cai> getMyCai(int userid){
		return caiDao.getMyCai(userid);
	}
	
	//删除菜，根据cai.id
	public void delCai(int id){
		//查到这个菜谱的信息，删除图片
		Cai cai = caiDao.selCaiById(id);
		String path = "F:"+File.separator+"StxCp"+File.separator+cai.getImgpath();
		File file = new File(path);
		file.delete();
		//删除菜谱
		caiDao.delCai(id);
	}
	
	//查询菜谱，根据cai.id
	public Cai selCaiById(int id){
		return caiDao.selCaiById(id);
	}
	
	//修改菜谱
	public void EditCai(Cai cai){
		cai.setTime(TimeFormat.getLocalTime());	//设置添加菜谱的时间
		cai.setName(HtmlFilter.filter(cai.getName()));
		cai.setZhuliao(HtmlFilter.filter(cai.getZhuliao()));
		cai.setFuliao(HtmlFilter.filter(cai.getFuliao()));
		cai.setDetail(HtmlFilter.filter(cai.getDetail()));
		
		caiDao.EditCai(cai);
	}
	
	//修改阅读量
	public void addReadNum(int id){
		caiDao.addReadNum(id);
	}
	@Resource(name="caiDao")
	private CaiDaoImpl caiDao;//注入

	public CaiDaoImpl getCaiDao() {
		return caiDao;
	}

	public void setCaiDao(CaiDaoImpl caiDao) {
		this.caiDao = caiDao;
	}

	

	
	
}
