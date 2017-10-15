package com.stx.pojo;

//评论表
public class Say {
	private int id;
	private int userid;	//评论者ID
	private int caiid;	//菜谱id
	private String name;	//用户名
	private String detail;	//评论内容
	private String time;	//评论时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCaiid() {
		return caiid;
	}
	public void setCaiid(int caiid) {
		this.caiid = caiid;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
