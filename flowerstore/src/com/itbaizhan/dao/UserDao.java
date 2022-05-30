package com.itbaizhan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itbaizhan.bean.User;
import com.itbaizhan.util.DBConn;
import com.itbaizhan.util.Fenye;
import com.itbaizhan.util.Pager;



public class UserDao {

	
	//插入纪录
	public void insertBean(User bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "insert into t_User(username,password,xingming,role,dianhua,dizhi) values(?,?,?,?,?,?)";
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getUsername());
			ps.setString(2, bean.getPassword());
			ps.setString(3, bean.getXingming());
			ps.setInt(4, bean.getRole());
			ps.setString(5, bean.getDianhua());
			ps.setString(6, bean.getDizhi());
			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//更新记录
	public void updateBean(User bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "update  t_User set username=?,password=?,xingming=?,role=?,dianhua=?,dizhi=? where id= "+bean.getId();
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getUsername());
			ps.setString(2, bean.getPassword());
			ps.setString(3, bean.getXingming());
			ps.setInt(4, bean.getRole());
			ps.setString(5, bean.getDianhua());
			ps.setString(6, bean.getDizhi());
			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//删除记录
	public void deleteBean(User bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "delete from   t_User  where id="+bean.getId();
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
	public Map<String,List<User>> getList(int pagenum,int pagesize ,String url,String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try{
			String sql = "SELECT * from t_User "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				User bean = new User();
				bean.setId(rs.getInt("id"));
				bean.setDianhua(rs.getString("dianhua"));
				bean.setDizhi(rs.getString("dizhi"));
				bean.setPassword(rs.getString("password"));
				bean.setRole(rs.getInt("role"));
				bean.setUsername(rs.getString("username"));
				bean.setXingming(rs.getString("xingming"));

				list.add(bean);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		int currentpage = pagenum;
		Fenye pm = new Fenye(list, pagesize);
		
		List<User> fenyelist = pm.getObjects(currentpage);
		int total = list.size();
		Map<String,List<User>> map = new HashMap<String,List<User>>();
		map.put(Pager.getPagerNormal(total, pagesize,
				currentpage, url, "共有" + total + "条记录"), fenyelist);
		String pagerinfo = map.keySet().iterator().next();
		List<User> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
			
		
		return map;

	}
	
	//按查询条件查询记录信息
	public User selectBean(String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User bean =null;
		try{
			String sql = "SELECT * from t_User "+where;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				bean = new User();
				bean.setId(rs.getInt("id"));
				bean.setDianhua(rs.getString("dianhua"));
				bean.setDizhi(rs.getString("dizhi"));
				bean.setPassword(rs.getString("password"));
				bean.setRole(rs.getInt("role"));
				bean.setUsername(rs.getString("username"));
				bean.setXingming(rs.getString("xingming"));

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
			String sql = "SELECT count(*) from t_User "+where;
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
