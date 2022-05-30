package com.itbaizhan.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itbaizhan.bean.Product;
import com.itbaizhan.dao.ProductDao;



//首页Servlet
public class ShouyeServlet extends HttpServlet {
	
	

	
	private static final long serialVersionUID = 1L;
	
	
	
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
	}
	
	
	public void destroy() {
		
		super.destroy();
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//设置获取的参数的编码格式
		request.setCharacterEncoding("utf-8");
		
		//分页页数定义
		int pagenum =1;//当前页
		int pagesize = 10;//每页显示的数量
		
		ProductDao ProductDao = new ProductDao();
		
	
		
		
		String pname = request.getParameter("pname");	
		String fenleiid = request.getParameter("fenleiid");	
		String tuijian = request.getParameter("tuijian");
		String jiage1 = request.getParameter("jiage1");
		String jiage2 = request.getParameter("jiage2");
		
		String url = "shouye";
		
		if(request.getParameter("pagenum")!=null){
			pagenum = Integer.parseInt(request.getParameter("pagenum"));
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" where  ");
		
		if(jiage1!=null&&!"".equals(jiage1)){
			
			
			sb.append(" jiage >= "+jiage1+"");
			sb.append(" and ");
			
			request.setAttribute("jiage1", jiage1);
		}
		
		if(jiage2!=null&&!"".equals(jiage2)){
			
			
			sb.append(" jiage <= "+jiage2+"");
			sb.append(" and ");
			
			request.setAttribute("jiage2", jiage2);
		}
		
		if(pname!=null&&!"".equals(pname)){
			
			
			sb.append(" pname like '%"+pname+"%' ");
			sb.append(" and ");
			
			request.setAttribute("pname", pname);
		}
		
		if(fenleiid!=null&&!"".equals(fenleiid)){
			
			
			sb.append(" fenleiid like '%"+fenleiid+"%' ");
			sb.append(" and ");
			
			request.setAttribute("fenleiid", fenleiid);
		}
		
		if(tuijian!=null&&!"".equals(tuijian)){
			
			
			sb.append(" tuijian = '已推荐' ");
			sb.append(" and ");
			
			request.setAttribute("tuijian", tuijian);
		}

		
		sb.append(" 1=1  order by id desc ");
		String where = sb.toString();


		Map<String,List<Product>> map = ProductDao.getList(pagenum,pagesize,url,where);

		String pagerinfo = map.keySet().iterator().next();
		List<Product> list = map.get(pagerinfo);
		

		request.setAttribute("pagerinfo", pagerinfo);
		request.setAttribute("list", list);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		
		
		
		
	}

}
