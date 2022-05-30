package com.itbaizhan.bean;


//商品

public class Product {

	private int id;//主键
	
	private String pname;//商品名
	
	private String imgpath;//商品图片
	
	private String createtime;//上架时间

	private String fenleiid;//分类ID，外键
	
	private String fname;//分类名
	
	private double jiage;//商品价格
	
	private String tuijian;//是否推荐  未推荐 已推荐
	
	private int dianjishu;//商品点击数
	
	private int xiaoliang;//商品销量
	
	private String miaoshu;//商品介绍  

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getFenleiid() {
		return fenleiid;
	}

	public void setFenleiid(String fenleiid) {
		this.fenleiid = fenleiid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public double getJiage() {
		return jiage;
	}

	public void setJiage(double jiage) {
		this.jiage = jiage;
	}

	public String getTuijian() {
		return tuijian;
	}

	public void setTuijian(String tuijian) {
		this.tuijian = tuijian;
	}

	public int getDianjishu() {
		return dianjishu;
	}

	public void setDianjishu(int dianjishu) {
		this.dianjishu = dianjishu;
	}

	public int getXiaoliang() {
		return xiaoliang;
	}

	public void setXiaoliang(int xiaoliang) {
		this.xiaoliang = xiaoliang;
	}

	public String getMiaoshu() {
		return miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

	
	


	
	
	
}
