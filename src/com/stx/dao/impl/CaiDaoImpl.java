package com.stx.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stx.dao.CaiDao;
import com.stx.mapper.CaiMapper;
import com.stx.pojo.Cai;


@Repository("caiDao")
public class CaiDaoImpl extends  SqlSessionDaoSupport implements CaiDao {
	

	//将新增的菜谱插入到数据库中
	public void addCai(Cai cai){
		System.out.println("菜系ID"+cai.getDeptid());
		System.out.println("用户ID"+cai.getUserid());
		this.getSqlSession().getMapper(CaiMapper.class).addCai(cai);
	}
	
	//查询我的菜谱，根据userd
	public List<Cai> getMyCai(int userid){
		List<Cai> caiList = this.getSqlSession().getMapper(CaiMapper.class).getMyCai(userid);
		return caiList;
	}
	
	//删除菜，根据cai.id
	public void delCai(int id){
		this.getSqlSession().getMapper(CaiMapper.class).delCai(id);
	}
	
	
	//查询菜谱，根据cai.id
	public Cai selCaiById(int id){
		return this.getSqlSession().getMapper(CaiMapper.class).selCaiById(id);
	}
	
	//修改菜谱，根据
	public void EditCai(Cai cai){
		this.getSqlSession().getMapper(CaiMapper.class).EditCai(cai);
	}
	
	//修改阅读量
	public void addReadNum(int id){
		this.getSqlSession().getMapper(CaiMapper.class).addReadNum(id);
	}
	
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}


}
