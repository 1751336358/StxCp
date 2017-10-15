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
	

	//�������Ĳ��ײ��뵽���ݿ���
	public void addCai(Cai cai){
		System.out.println("��ϵID"+cai.getDeptid());
		System.out.println("�û�ID"+cai.getUserid());
		this.getSqlSession().getMapper(CaiMapper.class).addCai(cai);
	}
	
	//��ѯ�ҵĲ��ף�����userd
	public List<Cai> getMyCai(int userid){
		List<Cai> caiList = this.getSqlSession().getMapper(CaiMapper.class).getMyCai(userid);
		return caiList;
	}
	
	//ɾ���ˣ�����cai.id
	public void delCai(int id){
		this.getSqlSession().getMapper(CaiMapper.class).delCai(id);
	}
	
	
	//��ѯ���ף�����cai.id
	public Cai selCaiById(int id){
		return this.getSqlSession().getMapper(CaiMapper.class).selCaiById(id);
	}
	
	//�޸Ĳ��ף�����
	public void EditCai(Cai cai){
		this.getSqlSession().getMapper(CaiMapper.class).EditCai(cai);
	}
	
	//�޸��Ķ���
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
