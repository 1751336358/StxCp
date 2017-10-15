package com.stx.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.stx.pojo.Cai;
import com.stx.pojo.PageCai;

public class PageUtils {
	//�����ܼ�¼��
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
	
	//����limit��ѯ����
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
	
	
	//���ɴ����ݷ�ҳ�ĺ��ķ���,��Ҫ
	public PageCai core(String current,String sql,String totalSql){
		System.out.println("����core����");
		if("0".equals(current)){
			current = "1";
		}
		
		int currentInt = Integer.parseInt(current);	//��ǰҳ��		
		int totalCount = 0;	//�ܼ�¼��
		try {
			totalCount = this.getTotalCount(totalSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pageSize = 3;	//ҳ��ߴ�
		int totalPage = (totalCount%pageSize==0?(totalCount/pageSize):(totalCount/pageSize+1));	//��ҳ��
		int prePage = ((currentInt<=1)?1:currentInt-1);	//��һҳ
		int nextPage = ((currentInt>=totalPage)?totalPage:currentInt+1);	//��һҳ
		int limit = (currentInt-1)*pageSize;
		List<Cai> caiList = null;
		System.out.println("------------------------------");
		try {
			System.out.println("++++++++++++++++++++++");
			caiList = this.getCai(limit,pageSize,sql);
			System.out.println("��ѯ���ļ�¼����"+caiList.size());
		} catch (Exception e) {
		}
		//ҳ����
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
		//��װ
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
