package com.stx.pojo;

import java.util.List;

public class PageCai {
	List<Cai> caiList;	//��ѯ���Ľ��
	private int current = 0;	//��ǰҳ��
	private int prePage;	//��һҳ
	private int nextPage;	//��һҳ
	private int totalPage;	//��ҳ��
	private int totalCount;	//�ܼ�¼��
	private int pageSize = 5;	//ҳ��ߴ�
	int bar[];	//ҳ����
	
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
