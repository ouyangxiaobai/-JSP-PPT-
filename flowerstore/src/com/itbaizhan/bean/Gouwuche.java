package com.itbaizhan.bean;


//购物车

public class Gouwuche {

	private int id;//主键
	
	private int userid;//关联的用户ID，外键
	
	private int pid;//关联的商品ID，外键
	
	private String pname;//商品名
	
	private double jiage;//价格
	
	private int shuliang;//购买数量

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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getJiage() {
		return jiage;
	}

	public void setJiage(double jiage) {
		this.jiage = jiage;
	}

	public int getShuliang() {
		return shuliang;
	}

	public void setShuliang(int shuliang) {
		this.shuliang = shuliang;
	}

	
	


	
	
	
}
