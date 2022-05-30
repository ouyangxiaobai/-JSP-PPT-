package com.itbaizhan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itbaizhan.bean.Gonggao;
import com.itbaizhan.util.DBConn;
import com.itbaizhan.util.Fenye;
import com.itbaizhan.util.Pager;



public class GonggaoDao {

	
	//插入纪录
	public void insertBean(Gonggao bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "insert into t_Gonggao(biaoti,neirong,shijian) values(?,?,?)";
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getBiaoti());
			ps.setString(2, bean.getNeirong());
			ps.setString(3, bean.getShijian());
			
			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//更新记录
	public void updateBean(Gonggao bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "update  t_Gonggao set biaoti=?,neirong=?,shijian=? where id= "+bean.getId();
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getBiaoti());
			ps.setString(2, bean.getNeirong());
			ps.setString(3, bean.getShijian());

			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//删除记录
	public void deleteBean(Gonggao bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "delete from   t_Gonggao  where id= "+bean.getId();
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
	public Map<String,List<Gonggao>> getList(int pagenum,int pagesize ,String url,String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Gonggao> list = new ArrayList<Gonggao>();
		try{
			String sql = "SELECT * from t_Gonggao "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				Gonggao bean = new Gonggao();
				bean.setId(rs.getInt("id"));
				bean.setBiaoti(rs.getString("biaoti"));
				bean.setNeirong(rs.getString("neirong"));
				bean.setShijian(rs.getString("shijian"));
				

				list.add(bean);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		int currentpage = pagenum;
		Fenye pm = new Fenye(list, pagesize);
		
		List<Gonggao> fenyelist = pm.getObjects(currentpage);
		int total = list.size();
		Map<String,List<Gonggao>> map = new HashMap<String,List<Gonggao>>();
		map.put(Pager.getPagerNormal(total, pagesize,
				currentpage, url, "共有" + total + "条记录"), fenyelist);
		String pagerinfo = map.keySet().iterator().next();
		List<Gonggao> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
			
		
		return map;

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Gonggao> getList(String where ){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Gonggao> list = new ArrayList<Gonggao>();
		try{
			String sql = "SELECT * from t_Gonggao "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				Gonggao bean = new Gonggao();
				bean.setId(rs.getInt("id"));
				bean.setBiaoti(rs.getString("biaoti"));
				bean.setNeirong(rs.getString("neirong"));
				bean.setShijian(rs.getString("shijian"));
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
	public Gonggao selectBean(String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Gonggao bean =null;
		try{
			String sql = "SELECT * from t_Gonggao "+where;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				bean = new Gonggao();
				bean.setId(rs.getInt("id"));
				bean.setBiaoti(rs.getString("biaoti"));
				bean.setNeirong(rs.getString("neirong"));
				bean.setShijian(rs.getString("shijian"));

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
			String sql = "SELECT count(*) from t_Gonggao "+where;
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
