package com.stx.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stx.dao.IndexDao;
import com.stx.mapper.IndexMapper;
import com.stx.pojo.Cai;


@Repository("indexDao")
public class IndexDaoImpl extends  SqlSessionDaoSupport implements IndexDao {
	


	
	
	//查询热门菜谱
	public List<Cai> hotCai(){
		return this.getSqlSession().getMapper(IndexMapper.class).hotCai();
	}
	
	//根据dept.id查询每个菜系的推荐菜谱
	public List<Cai> getTuijianCai(int id){
		return this.getSqlSession().getMapper(IndexMapper.class).getTuijianCai(id);
	}
	
	//访问本网站的时间
	public void insertTime(String time){
		this.getSqlSession().getMapper(IndexMapper.class).insertTime(time);
	}
	
	//网站的访问量
	public int getFangwenNum(){
		return this.getSqlSession().getMapper(IndexMapper.class).getFangwenNum();
	}
	
	//根据关键字key查询相关的菜谱
	public List<Cai> selCaiByKey(String key){
		return this.getSqlSession().getMapper(IndexMapper.class).selCaiByKey(key);
	}
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}


}
