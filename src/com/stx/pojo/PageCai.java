package com.stx.pojo;

import java.util.List;

public class PageCai {
	List<Cai> caiList;	//查询出的结果
	private int current = 0;	//当前页数
	private int prePage;	//上一页
	private int nextPage;	//下一页
	private int totalPage;	//总页数
	private int totalCount;	//总记录数
	private int pageSize = 5;	//页面尺寸
	int bar[];	//页码条
	
	public List<Cai> getCaiList() {
		return caiList;
	}
	public void setCaiList(List<Cai> caiList) {
		this.caiList = caiList;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getPrePage() {
		
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int[] getBar() {
		return bar;
	}
	public void setBar(int[] bar) {
		this.bar = bar;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
