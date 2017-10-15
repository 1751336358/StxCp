package com.stx.service;

import java.util.List;

import com.stx.pojo.Cai;
import com.stx.pojo.User;

public interface CaiService {
	//将新增的菜谱插入到数据库中
	public void addCai(Cai cai);
	
	//查询我的菜谱，根据userd
	public List<Cai> getMyCai(int userid);
	
	//删除菜，根据cai.id
	public void delCai(int id);
	
	//查询菜谱，根据cai.id
	public Cai selCaiById(int id);
	
	//修改菜谱
	public void EditCai(Cai cai);
	
	//修改阅读量
	public void addReadNum(int id);
}