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
	


	
	
	//��ѯ���Ų���
	public List<Cai> hotCai(){
		return this.getSqlSession().getMapper(IndexMapper.class).hotCai();
	}
	
	//����dept.id��ѯÿ����ϵ���Ƽ�����
	public List<Cai> getTuijianCai(int id){
		return this.getSqlSession().getMapper(IndexMapper.class).getTuijianCai(id);
	}
	
	//���ʱ���վ��ʱ��
	public void insertTime(String time){
		this.getSqlSession().getMapper(IndexMapper.class).insertTime(time);
	}
	
	//��վ�ķ�����
	public int getFangwenNum(){
		return this.getSqlSession().getMapper(IndexMapper.class).getFangwenNum();
	}
	
	//���ݹؼ���key��ѯ��صĲ���
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
