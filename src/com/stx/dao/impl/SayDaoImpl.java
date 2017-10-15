package com.stx.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stx.dao.SayDao;
import com.stx.mapper.SayMapper;
import com.stx.pojo.Say;


@Repository("sayDao")
public class SayDaoImpl extends  SqlSessionDaoSupport implements SayDao {
	
	//将评论插入数据库
	public void addSay(Say say){
		this.getSqlSession().getMapper(SayMapper.class).addSay(say);
	}
	
	//查询出所有评论，根据caiid
	public List<Say> selSayByCaiid(int caiid){
		return this.getSqlSession().getMapper(SayMapper.class).selSayByCaiid(caiid);
	}
	
	//查出最新的评论
	public Say selNewSay(){
		return this.getSqlSession().getMapper(SayMapper.class).selNewSay();
	}
	
	//删除评论，根据sayid
	public void delSayBysayid(int sayid){
		this.getSqlSession().getMapper(SayMapper.class).delSayBysayid(sayid);
	}
	
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}


}
