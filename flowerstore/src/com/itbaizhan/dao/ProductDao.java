package com.itbaizhan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itbaizhan.bean.Product;
import com.itbaizhan.util.DBConn;
import com.itbaizhan.util.Fenye;
import com.itbaizhan.util.Pager;



public class ProductDao {

	
	//插入纪录
	public void insertBean(Product bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "insert into t_Product(pname,imgpath,createtime,fenleiid,fname,jiage,tuijian,dianjishu,xiaoliang,miaoshu) " +
					"values(?,?,?,?,?,?,?,?,?,?)";
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getPname());
			ps.setString(2, bean.getImgpath());
			ps.setString(3, bean.getCreatetime());
			ps.setString(4, bean.getFenleiid());
			ps.setString(5, bean.getFname());
			ps.setDouble(6, bean.getJiage());
			ps.setString(7, bean.getTuijian());
			ps.setInt(8, bean.getDianjishu());
			ps.setInt(9, bean.getXiaoliang());
			ps.setString(10, bean.getMiaoshu());
			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//更新记录
	public void updateBean(Product bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "update  t_Product set pname=?,imgpath=?,createtime=?,fenleiid=?,fname=?,jiage=?,tuijian=?,dianjishu=?,xiaoliang=?,miaoshu=? where id= "+bean.getId();
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getPname());
			ps.setString(2, bean.getImgpath());
			ps.setString(3, bean.getCreatetime());
			ps.setString(4, bean.getFenleiid());
			ps.setString(5, bean.getFname());
			ps.setDouble(6, bean.getJiage());
			ps.setString(7, bean.getTuijian());
			ps.setInt(8, bean.getDianjishu());
			ps.setInt(9, bean.getXiaoliang());
			ps.setString(10, bean.getMiaoshu());

			ps.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
	}
	
	//删除记录
	public void deleteBean(Product bean){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String sql = "delete from   t_Product  where id= "+bean.getId();
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
	public Map<String,List<Product>> getList(int pagenum,int pagesize ,String url,String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try{
			String sql = "SELECT * from t_Product "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				Product bean = new Product();
				bean.setId(rs.getInt("id"));
				bean.setCreatetime(rs.getString("createtime"));
				bean.setDianjishu(rs.getInt("dianjishu"));
				bean.setFenleiid(rs.getString("fenleiid"));
				bean.setFname(rs.getString("fname"));
				bean.setImgpath(rs.getString("imgpath"));
				bean.setJiage(rs.getDouble("jiage"));
				bean.setMiaoshu(rs.getString("miaoshu"));
				bean.setPname(rs.getString("pname"));
				bean.setTuijian(rs.getString("tuijian"));
				bean.setXiaoliang(rs.getInt("xiaoliang"));
				

				list.add(bean);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConn.close(conn, ps, null);
		}
		int currentpage = pagenum;
		Fenye pm = new Fenye(list, pagesize);
		
		List<Product> fenyelist = pm.getObjects(currentpage);
		int total = list.size();
		Map<String,List<Product>> map = new HashMap<String,List<Product>>();
		map.put(Pager.getPagerNormal(total, pagesize,
				currentpage, url, "共有" + total + "条记录"), fenyelist);
		String pagerinfo = map.keySet().iterator().next();
		List<Product> list2 = map.get(pagerinfo);
		if(list2==null){
			map.remove(pagerinfo);
			map.put(pagerinfo, list);
		}
			
		
		return map;

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Product> getList(String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try{
			String sql = "SELECT * from t_Product "+where ;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				Product bean = new Product();
				bean.setId(rs.getInt("id"));
				bean.setCreatetime(rs.getString("createtime"));
				bean.setDianjishu(rs.getInt("dianjishu"));
				bean.setFenleiid(rs.getString("fenleiid"));
				bean.setFname(rs.getString("fname"));
				bean.setImgpath(rs.getString("imgpath"));
				bean.setJiage(rs.getDouble("jiage"));
				bean.setMiaoshu(rs.getString("miaoshu"));
				bean.setPname(rs.getString("pname"));
				bean.setTuijian(rs.getString("tuijian"));
				bean.setXiaoliang(rs.getInt("xiaoliang"));
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
	public Product selectBean(String where){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product bean =null;
		try{
			String sql = "SELECT * from t_Product "+where;
			conn = DBConn.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs!=null &&rs.next()){
				bean = new Product();
				bean.setId(rs.getInt("id"));
				bean.setCreatetime(rs.getString("createtime"));
				bean.setDianjishu(rs.getInt("dianjishu"));
				bean.setFenleiid(rs.getString("fenleiid"));
				bean.setFname(rs.getString("fname"));
				bean.setImgpath(rs.getString("imgpath"));
				bean.setJiage(rs.getDouble("jiage"));
				bean.setMiaoshu(rs.getString("miaoshu"));
				bean.setPname(rs.getString("pname"));
				bean.setTuijian(rs.getString("tuijian"));
				bean.setXiaoliang(rs.getInt("xiaoliang"));

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
			String sql = "SELECT count(*) from t_Product "+where;
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
