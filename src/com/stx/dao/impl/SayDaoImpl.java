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
	
	//�����۲������ݿ�
	public void addSay(Say say){
		this.getSqlSession().getMapper(SayMapper.class).addSay(say);
	}
	
	//��ѯ���������ۣ�����caiid
	public List<Say> selSayByCaiid(int caiid){
		return this.getSqlSession().getMapper(SayMapper.class).selSayByCaiid(caiid);
	}
	
	//������µ�����
	public Say selNewSay(){
		return this.getSqlSession().getMapper(SayMapper.class).selNewSay();
	}
	
	//ɾ�����ۣ�����sayid
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
