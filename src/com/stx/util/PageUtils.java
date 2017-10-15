package com.stx.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.stx.pojo.Cai;
import com.stx.pojo.PageCai;

public class PageUtils {
	//返回总记录数
	public int getTotalCount(String totalSql) throws Exception{
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstm = conn.prepareStatement(totalSql);
		ResultSet rs = null;
		rs = pstm.executeQuery();
		int totalCount = 0;
		while(rs.next()){
			totalCount = rs.getInt(1);
		}
		JdbcUtils.release(conn, pstm, rs);
		return totalCount;
	}
	
	//根据limit查询菜谱
	public List<Cai> getCai(int limit,int pageSize,String sql) throws Exception{
		Connection conn = JdbcUtils.getConnection();
		PreparedStatement pstm = conn.prepareStatement(sql+" limit ?,?");
		pstm.setInt(1, limit);
		pstm.setInt(2, pageSize);
		ResultSet rs = null;
		List<Cai> caiList = new ArrayList<Cai>();
		rs = pstm.executeQuery();
		while(rs.next()){
			Cai cai = new Cai();
			cai.setId(rs.getInt("id"));
			cai.setName(rs.getString("name"));
			cai.setZhuliao(rs.getString("zhuliao"));
			cai.setFuliao(rs.getString("fuliao"));
			cai.setDetail(rs.getString("detail"));
			cai.setReadnum(rs.getInt("readnum"));
			cai.setImgpath(rs.getString("imgpath"));
			cai.setUserid(rs.getInt("userid"));
			cai.setDeptid(rs.getInt("deptid"));
			cai.setTime(rs.getString("time"));
			caiList.add(cai);
		}
		
		return caiList;
	}
	
	
	//生成大数据分页的核心方法,重要
	public PageCai core(String current,String sql,String totalSql){
		System.out.println("进入core方法");
		if("0".equals(current)){
			current = "1";
		}
		
		int currentInt = Integer.parseInt(current);	//当前页数		
		int totalCount = 0;	//总记录数
		try {
			totalCount = this.getTotalCount(totalSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pageSize = 3;	//页面尺寸
		int totalPage = (totalCount%pageSize==0?(totalCount/pageSize):(totalCount/pageSize+1));	//总页数
		int prePage = ((currentInt<=1)?1:currentInt-1);	//上一页
		int nextPage = ((currentInt>=totalPage)?totalPage:currentInt+1);	//下一页
		int limit = (currentInt-1)*pageSize;
		List<Cai> caiList = null;
		System.out.println("------------------------------");
		try {
			System.out.println("++++++++++++++++++++++");
			caiList = this.getCai(limit,pageSize,sql);
			System.out.println("查询到的记录数是"+caiList.size());
		} catch (Exception e) {
		}
		//页码条
		int bar[] = null;
		if(totalPage >= 10){
			bar = new int[10];
			int start = currentInt-4;
			int end = currentInt+5;
			if(start <= 1){
				start = 1;
				end = 10;
			}else if(end > totalPage){
				end = totalPage+1;
				start = end-10;
			}
			for(int i = 0;i<bar.length;i++){
				bar[i] = start++;
			}
		}else{
			bar = new int[totalPage];
			for(int i = 0;i<bar.length;i++){
				bar[i] = i+1;
			}
		}
		//封装
		PageCai pageCai = new PageCai();
		pageCai.setCurrent(currentInt);
		pageCai.setPageSize(pageSize);
		pageCai.setPrePage(prePage);
		pageCai.setNextPage(nextPage);
		pageCai.setTotalCount(totalCount);
		pageCai.setTotalPage(totalPage);
		pageCai.setBar(bar);
		pageCai.setCaiList(caiList);
		return pageCai;
	}
}
