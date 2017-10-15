package com.stx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.stx.dao.impl.IndexDaoImpl;
import com.stx.pojo.Cai;
import com.stx.service.IndexService;


//标记一个Service
@Service("indexService")
public class IndexServiceImpl implements IndexService {

	
	
	//查询热门菜谱
	public List<Cai> hotCai(){
		return indexDao.hotCai();
	}
	
	//根据dept.id查询每个菜系的推荐菜谱
	public List<Cai> getTuijianCai(int id){
		return indexDao.getTuijianCai(id);
	}
	
	//访问本网站的时间
	public void insertTime(String time){
		indexDao.insertTime(time);
	}
	
	//网站的访问量
	public int getFangwenNum(){
		return indexDao.getFangwenNum();
	}
	
	//根据关键字key查询相关的菜谱
	public List<Cai> selCaiByKey(String key){
		return indexDao.selCaiByKey(key);
	}
	@Resource(name="indexDao")
	private IndexDaoImpl indexDao;//注入

	public IndexDaoImpl getIndexDao() {
		return indexDao;
	}

	public void setIndexDao(IndexDaoImpl indexDao) {
		this.indexDao = indexDao;
	}



	

	
	
}
