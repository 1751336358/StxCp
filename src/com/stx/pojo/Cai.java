package com.stx.pojo;

//菜谱
public class Cai {
	private int id;	//主键id
	private String name;	//菜名
	private String time;	//创建时间
	private String zhuliao;	//主料	
	private String fuliao;	//辅料
	private String detail;	//做法
	private int readnum;	//阅读量
	private String imgpath;	//图片的上传路径
	private int userid;	//userid，外键
	private int deptid;	//deptid，外键
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getZhuliao() {
		return zhuliao;
	}
	public void setZhuliao(String zhuliao) {
		this.zhuliao = zhuliao;
	}
	public String getFuliao() {
		return fuliao;
	}
	public void setFuliao(String fuliao) {
		this.fuliao = fuliao;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getReadnum() {
		return readnum;
	}
	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}	
}
