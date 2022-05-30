package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Util;
import bean.User;
import bean.Wenti;
import dao.WentiDao;



public class ShouyeServlet extends HttpServlet {
	
	

	
	private static final long serialVersionUID = 1L;
	
	
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		
		//分页页数定义
		int pagenum =1;//当前页
		int pagesize = 4;//每页显示的数量
		
		WentiDao wentiDao = new WentiDao();
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		
		
		String wenti = request.getParameter("wenti");	
		
		String url = "shouye";
		
		if(request.getParameter("pagenum")!=null){
			pagenum = Integer.parseInt(request.getParameter("pagenum"));
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" where  ");
		
		if(wenti!=null&&!"".equals(wenti)){
			wenti = Util.bianma(wenti);
			
			sb.append(" wenti like '%"+wenti+"%' ");
			sb.append(" and ");
			
			request.setAttribute("wenti", wenti);
		}
		
		if(u==null){
			
			sb.append(" kejianzhuangtai ='整个网络可见' ");
			sb.append(" and ");
		}else{

			sb.append(" (fromuserid in (select fromuserid from t_haoyou where tuuserid="+u.getId()+" ) or  kejianzhuangtai ='整个网络可见'  )");
			sb.append(" and ");
			
		}
		
		
		sb.append(" 1=1 ");
		String where = sb.toString();


		Map<String,List<Wenti>> map = wentiDao.getList(pagenum,pagesize,url,where);

		String pagerinfo = map.keySet().iterator().next();
		List<Wenti> list = map.get(pagerinfo);
		

		request.setAttribute("pagerinfo", pagerinfo);
		request.setAttribute("list", list);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		
		
		
		
	}

}
