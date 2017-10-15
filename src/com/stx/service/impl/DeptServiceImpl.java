package com.stx.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.stx.dao.UserDao;
import com.stx.dao.impl.DeptDaoImpl;
import com.stx.dao.impl.UserDaoImpl;
import com.stx.pojo.Dept;
import com.stx.pojo.User;
import com.stx.service.DeptService;
import com.stx.service.UserService;

//标记一个Service
@Service("deptService")
public class DeptServiceImpl implements DeptService {

	
	//查询出所有的菜系
	public List<Dept> selAllDept(){
		List<Dept> list = deptDao.selAllDept();
		return list;
	}
	@Resource(name="deptDao")
	private DeptDaoImpl deptDao;//注入

	public DeptDaoImpl getDeptDao() {
		return deptDao;
	}

	public void setDeptDao(DeptDaoImpl deptDao) {
		this.deptDao = deptDao;
	}

	
	
}
