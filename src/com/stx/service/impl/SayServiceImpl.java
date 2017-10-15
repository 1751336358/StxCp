package com.stx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.stx.dao.SayDao;
import com.stx.pojo.Say;
import com.stx.service.SayService;
import com.stx.util.TimeFormat;


//标记一个Service
@Service("sayService")
public class SayServiceImpl implements SayService {

	
	
	//将评论插入数据库
	public void addSay(Say say){
		String time = TimeFormat.getLocalTime();
		say.setTime(time);	//设置评论时间
		sayDao.addSay(say);
	}
	
	//查询出所有评论，根据caiid
	public List<Say> selSayByCaiid(int caiid){
		
		return sayDao.selSayByCaiid(caiid);
	}
	
	//查出最新的评论
	public Say selNewSay(){
		return sayDao.selNewSay();
	}
	
	//删除评论，根据sayid
	public void delSayBysayid(int sayid){
		sayDao.delSayBysayid(sayid);
	}
	@Resource(name="sayDao")
	private SayDao sayDao;//注入
	public SayDao getSayDao() {
		return sayDao;
	}
	public void setSayDao(SayDao sayDao) {
		this.sayDao = sayDao;
	}

	

	

	
	
}
