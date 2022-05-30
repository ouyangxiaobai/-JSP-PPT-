package com.itbaizhan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itbaizhan.bean.Fenlei;
import com.itbaizhan.util.DBConn;
import com.itbaizhan.util.Fenye;
import com.itbaizhan.util.Pager;



public class FenleiDao {

	
	//插入纪录
	public void insertBean(Fenlei bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "insert into t_Fenlei(fname) values(?)";
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getFname());
			
			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//更新记录
	public void updateBean(Fenlei bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "update  t_Fenlei set fname=? where id= "+bean.getId();
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getFname());

			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//删除记录
	public void deleteBean(Fenlei bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "delete from   t_Fenlei  where id= "+bean.getId();
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
	public Map<String,List<Fenlei>> getList(int pagenum,int pagesize ,String url,String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Fenlei> list = new ArrayList<Fenlei>();
		try{
			String sql = "SELECT * from t_Fenlei "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				Fenlei bean = new Fenlei();
				bean.setId(rs.getInt("id"));
				bean.setFname(rs.getString("fname"));
				

				list.add(bean);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		int currentpage = pagenum;
		Fenye pm = new Fenye(list, pagesize);
		
		List<Fenlei> fenyelist = pm.getObjects(currentpage);
		int total = list.size();
		Map<String,List<Fenlei>> map = new HashMap<String,List<Fenlei>>();
		map.put(Pager.getPagerNormal(total, pagesize,
				currentpage, url, "共有" + total + "条记录"), fenyelist);
		String pagerinfo = map.keySet().iterator().next();
		List<Fenlei> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
			
		
		return map;

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Fenlei> getList(String where ){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Fenlei> list = new ArrayList<Fenlei>();
		try{
			String sql = "SELECT * from t_Fenlei "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				Fenlei bean = new Fenlei();
				bean.setId(rs.getInt("id"));
				bean.setFname(rs.getString("fname"));
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
	public Fenlei selectBean(String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Fenlei bean =null;
		try{
			String sql = "SELECT * from t_Fenlei "+where;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				bean = new Fenlei();
				bean.setId(rs.getInt("id"));
				bean.setFname(rs.getString("fname"));

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
			String sql = "SELECT count(*) from t_Fenlei "+where;
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
