package com.stx.mapper;

import java.util.List;

import com.stx.pojo.Say;



public interface SayMapper {
	//将评论插入数据库
	public void addSay(Say say);
	
	//查询出所有评论，根据caiid
	public List<Say> selSayByCaiid(int caiid);
	
	//查出最新的评论
	public Say selNewSay();
	
	//删除评论，根据sayid
	public void delSayBysayid(int sayid);
}
