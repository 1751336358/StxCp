package com.stx.mapper;

import java.util.List;

import com.stx.pojo.Dept;
import com.stx.pojo.User;


public interface DeptMapper {
	//查询出所有的菜系
	public List<Dept> selAllDept();
}
