package com.stx.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stx.dao.DeptDao;
import com.stx.dao.UserDao;
import com.stx.mapper.DeptMapper;
import com.stx.mapper.UserMapper;
import com.stx.pojo.Dept;
import com.stx.pojo.User;

@Repository("deptDao")
public class DeptDaoImpl extends  SqlSessionDaoSupport implements DeptDao {
	

	//查询出所有的菜系
	public List<Dept> selAllDept(){
		return this.getSqlSession().getMapper(DeptMapper.class).selAllDept();
	}
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
}
