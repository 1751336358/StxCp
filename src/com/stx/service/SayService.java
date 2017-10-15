package com.stx.service;

import java.util.List;

import com.stx.pojo.Cai;
import com.stx.pojo.Say;
import com.stx.pojo.User;

public interface SayService {
	//将评论插入数据库
	public void addSay(Say say);
	
	//查询出所有评论，根据caiid
	public List<Say> selSayByCaiid(int caiid);
	
	//查出最新的评论
	public Say selNewSay();
	
	//删除评论，根据sayid
	public void delSayBysayid(int sayid);
}