package com.stx.dao;

import java.util.List;

import com.stx.pojo.Cai;

public interface IndexDao {
	
	//查询热门菜谱
	public List<Cai> hotCai();
	
	//根据dept.id查询每个菜系的推荐菜谱
	public List<Cai> getTuijianCai(int id);
	
	//访问本网站的时间
	public void insertTime(String time);
	
	//网站的访问量
	public int getFangwenNum();
	
	//根据关键字key查询相关的菜谱
	public List<Cai> selCaiByKey(String key);
}
