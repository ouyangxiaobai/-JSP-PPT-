package com.itbaizhan.bean;


//用户

public class User {

	private int id;//主键
	
	private String username;//用户名
	
	private String password;//密码
	
	private String xingming;//姓名
	
	private int role;//用户角色 0表示用户，1表示系统管理员
	
	private String dianhua;//手机
	
	private String dizhi;//收货地址

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

	public String getDizhi() {
		return dizhi;
	}

	public void setDizhi(String dizhi) {
		this.dizhi = dizhi;
	}
	
	
	

	

	


	
	
	
}
