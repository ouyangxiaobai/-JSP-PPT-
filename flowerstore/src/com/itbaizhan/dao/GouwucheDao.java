package com.itbaizhan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itbaizhan.bean.Gouwuche;
import com.itbaizhan.util.DBConn;
import com.itbaizhan.util.Fenye;
import com.itbaizhan.util.Pager;



public class GouwucheDao {

	
	//插入纪录
	public void insertBean(Gouwuche bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "insert into t_Gouwuche(userid,pid,pname,jiage,shuliang) values(?,?,?,?,?)";
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bean.getUserid());
			ps.setInt(2, bean.getPid());
			ps.setString(3, bean.getPname());
			ps.setDouble(4, bean.getJiage());
			ps.setInt(5, bean.getShuliang());
			
			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//更新记录
	public void updateBean(Gouwuche bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "update  t_Gouwuche set userid=?,pid=?,pname=?,jiage=?,shuliang=? where id= "+bean.getId();
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bean.getUserid());
			ps.setInt(2, bean.getPid());
			ps.setString(3, bean.getPname());
			ps.setDouble(4, bean.getJiage());
			ps.setInt(5, bean.getShuliang());

			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//删除记录
	public void deleteBean(Gouwuche bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "delete from   t_Gouwuche  where id= "+bean.getId();
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
	public Map<String,List<Gouwuche>> getList(int pagenum,int pagesize ,String url,String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Gouwuche> list = new ArrayList<Gouwuche>();
		try{
			String sql = "SELECT * from t_Gouwuche "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				Gouwuche bean = new Gouwuche();
				bean.setId(rs.getInt("id"));
				bean.setJiage(rs.getDouble("jiage"));
				bean.setPid(rs.getInt("pid"));
				bean.setPname(rs.getString("pname"));
				bean.setShuliang(rs.getInt("shuliang"));
				bean.setUserid(rs.getInt("userid"));
				

				list.add(bean);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		int currentpage = pagenum;
		Fenye pm = new Fenye(list, pagesize);
		
		List<Gouwuche> fenyelist = pm.getObjects(currentpage);
		int total = list.size();
		Map<String,List<Gouwuche>> map = new HashMap<String,List<Gouwuche>>();
		map.put(Pager.getPagerNormal(total, pagesize,
				currentpage, url, "共有" + total + "条记录"), fenyelist);
		String pagerinfo = map.keySet().iterator().next();
		List<Gouwuche> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
			
		
		return map;

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Gouwuche> getList(String where ){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Gouwuche> list = new ArrayList<Gouwuche>();
		try{
			String sql = "SELECT * from t_Gouwuche "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				Gouwuche bean = new Gouwuche();
				bean.setId(rs.getInt("id"));
				bean.setJiage(rs.getDouble("jiage"));
				bean.setPid(rs.getInt("pid"));
				bean.setPname(rs.getString("pname"));
				bean.setShuliang(rs.getInt("shuliang"));
				bean.setUserid(rs.getInt("userid"));
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
	public Gouwuche selectBean(String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Gouwuche bean =null;
		try{
			String sql = "SELECT * from t_Gouwuche "+where;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				bean = new Gouwuche();
				bean.setId(rs.getInt("id"));
				bean.setJiage(rs.getDouble("jiage"));
				bean.setPid(rs.getInt("pid"));
				bean.setPname(rs.getString("pname"));
				bean.setShuliang(rs.getInt("shuliang"));
				bean.setUserid(rs.getInt("userid"));

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
			String sql = "SELECT count(*) from t_Gouwuche "+where;
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
