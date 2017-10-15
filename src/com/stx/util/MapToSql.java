package com.stx.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class MapToSql {
	public static String mapToSql(Map map){
		StringBuffer sql = new StringBuffer();
		String imgpath = (String) map.get("imgpath");

		System.out.println(imgpath);
		sql.append("insert into cai(userid,name,zhuliao,fuliao,time,detail,deptid,imgpath) values(");		
		sql.append(map.get("userid")+",");
		sql.append("'"+map.get("name")+"',");
		sql.append("'"+map.get("zhuliao")+"',");
		sql.append("'"+map.get("fuliao")+"',");
		sql.append("'"+map.get("time")+"',");
		sql.append("'"+map.get("detail")+"',");
		sql.append(map.get("deptid")+",");
		sql.append("'"+map.get("imgpath")+"'");
		sql.append(")");
		return sql.toString();
	}
	
	@Test
	public void test1(){
		Map map = new LinkedHashMap<String, String>();
		map.put("imgpath","F:\\aaa\\bb");
		mapToSql(map);
	}
}
