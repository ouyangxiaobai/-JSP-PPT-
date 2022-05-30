package com.itbaizhan.bean;


//订单

public class Dingdan {

	private int id;//主键
	
	private String status;// 订单状态 已处理 未处理 
	

	private int  userid;//关联用户的id 外键
	
	private String xingming;//收货人姓名
	
	private String dianhua;//收货人手机
	
	private String dizhi;//收货地址
	
	private  String  xiangqing;//订单详情
	
	private String orderid;//订单号
	
	
	private String beizhu;//备注

	private String  shijian;//生成时间
	
	private double zongjia;//总价

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
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

	public String getXiangqing() {
		return xiangqing;
	}

	public void setXiangqing(String xiangqing) {
		this.xiangqing = xiangqing;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public double getZongjia() {
		return zongjia;
	}

	public void setZongjia(double zongjia) {
		this.zongjia = zongjia;
	}
	
	

	


	
	
	
}
