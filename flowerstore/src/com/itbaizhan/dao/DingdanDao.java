package com.itbaizhan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itbaizhan.bean.Dingdan;
import com.itbaizhan.util.DBConn;
import com.itbaizhan.util.Fenye;
import com.itbaizhan.util.Pager;



public class DingdanDao {

	
	//插入纪录
	public void insertBean(Dingdan bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "insert into t_Dingdan(status,userid,xingming,dianhua,dizhi,xiangqing,orderid,beizhu,shijian,zongjia) " +
					"values(?,?,?,?,?,?,?,?,?,?)";
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getStatus());
			ps.setInt(2, bean.getUserid());
			ps.setString(3, bean.getXingming());
			ps.setString(4, bean.getDianhua());
			ps.setString(5, bean.getDizhi());
			ps.setString(6, bean.getXiangqing());
			ps.setString(7, bean.getOrderid());
			ps.setString(8, bean.getBeizhu());
			ps.setString(9, bean.getShijian());
			ps.setDouble(10, bean.getZongjia());
			
			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//更新记录
	public void updateBean(Dingdan bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "update  t_Dingdan set status=?,userid=?,xingming=?,dianhua=?,dizhi=?,xiangqing=?," +
					"orderid=?,beizhu=?,shijian=?,zongjia=? where id= "+bean.getId();
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getStatus());
			ps.setInt(2, bean.getUserid());
			ps.setString(3, bean.getXingming());
			ps.setString(4, bean.getDianhua());
			ps.setString(5, bean.getDizhi());
			ps.setString(6, bean.getXiangqing());
			ps.setString(7, bean.getOrderid());
			ps.setString(8, bean.getBeizhu());
			ps.setString(9, bean.getShijian());
			ps.setDouble(10, bean.getZongjia());

			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//删除记录
	public void deleteBean(Dingdan bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "delete from   t_Dingdan  where id= "+bean.getId();
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	

	//按查询条件查询列表信息（支持分页）
	@SuppressWarnings("unchecked")
	public Map<String,List<Dingdan>> getList(int pagenum,int pagesize ,String url,String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Dingdan> list = new ArrayList<Dingdan>();
		try{
			String sql = "SELECT * from t_Dingdan "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				Dingdan bean = new Dingdan();
				bean.setId(rs.getInt("id"));
				bean.setBeizhu(rs.getString("beizhu"));
				bean.setDianhua(rs.getString("dianhua"));
				bean.setDizhi(rs.getString("dizhi"));
				bean.setOrderid(rs.getString("orderid"));
				bean.setShijian(rs.getString("shijian"));
				bean.setStatus(rs.getString("status"));
				bean.setUserid(rs.getInt("userid"));
				bean.setXiangqing(rs.getString("xiangqing"));
				bean.setXingming(rs.getString("xingming"));
				bean.setZongjia(rs.getDouble("zongjia"));
				

				list.add(bean);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		int currentpage = pagenum;
		Fenye pm = new Fenye(list, pagesize);
		
		List<Dingdan> fenyelist = pm.getObjects(currentpage);
		int total = list.size();
		Map<String,List<Dingdan>> map = new HashMap<String,List<Dingdan>>();
		map.put(Pager.getPagerNormal(total, pagesize,
				currentpage, url, "共有" + total + "条记录"), fenyelist);
		String pagerinfo = map.keySet().iterator().next();
		List<Dingdan> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
			
		
		return map;

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Dingdan> getList(String where ){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Dingdan> list = new ArrayList<Dingdan>();
		try{
			String sql = "SELECT * from t_Dingdan "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				Dingdan bean = new Dingdan();
				bean.setId(rs.getInt("id"));
				bean.setBeizhu(rs.getString("beizhu"));
				bean.setDianhua(rs.getString("dianhua"));
				bean.setDizhi(rs.getString("dizhi"));
				bean.setOrderid(rs.getString("orderid"));
				bean.setShijian(rs.getString("shijian"));
				bean.setStatus(rs.getString("status"));
				bean.setUserid(rs.getInt("userid"));
				bean.setXiangqing(rs.getString("xiangqing"));
				bean.setXingming(rs.getString("xingming"));
				bean.setZongjia(rs.getDouble("zongjia"));
				list.add(bean);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		
		
		return list;

	}
	
	
	//按查询条件查询记录信息
	public Dingdan selectBean(String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Dingdan bean =null;
		try{
			String sql = "SELECT * from t_Dingdan "+where;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				bean = new Dingdan();
				bean.setId(rs.getInt("id"));
				bean.setBeizhu(rs.getString("beizhu"));
				bean.setDianhua(rs.getString("dianhua"));
				bean.setDizhi(rs.getString("dizhi"));
				bean.setOrderid(rs.getString("orderid"));
				bean.setShijian(rs.getString("shijian"));
				bean.setStatus(rs.getString("status"));
				bean.setUserid(rs.getInt("userid"));
				bean.setXiangqing(rs.getString("xiangqing"));
				bean.setXingming(rs.getString("xingming"));
				bean.setZongjia(rs.getDouble("zongjia"));

			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		return bean;
	}
	
	
	public int selectBeancount(String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try{
			String sql = "SELECT count(*) from t_Dingdan "+where;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if(rs!=null){
				count = rs.getInt(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
