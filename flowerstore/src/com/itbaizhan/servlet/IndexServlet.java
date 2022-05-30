package com.itbaizhan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itbaizhan.bean.Dingdan;
import com.itbaizhan.bean.Gonggao;
import com.itbaizhan.bean.Gouwuche;
import com.itbaizhan.bean.Product;
import com.itbaizhan.bean.User;
import com.itbaizhan.dao.DingdanDao;
import com.itbaizhan.dao.GonggaoDao;
import com.itbaizhan.dao.GouwucheDao;
import com.itbaizhan.dao.ProductDao;
import com.itbaizhan.dao.UserDao;
import com.itbaizhan.util.Util;


//后台servlet，用于处理前台的所有请求
public class IndexServlet extends HttpServlet {

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
		
		//获取绝对地址
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//设置响应输出的字符串格式
		response.setCharacterEncoding("utf-8");response.setContentType("text/html; charset=utf-8");
		//获取输出对象
		PrintWriter writer = response.getWriter();
		//获取页面请求地址
		String uri = request.getRequestURI();	
		String[] s = uri.split("/");
		String method = s[3];
		
		//初始化跳转的地址
		String url ="";
		
		int pagenum =  1;//当前页
		int pagesize = 15;//每页显示的数量
		
		
		//初始化调用的数据库操作对象
		UserDao userDao = new UserDao();
		
		ProductDao productDao = new ProductDao();
		
		GonggaoDao gonggaoDao = new GonggaoDao();
		
		GouwucheDao gouwucheDao = new GouwucheDao();
		
		DingdanDao dingdanDao = new DingdanDao();
		
		//新用户注册
		if("register".equals(method)){
			//从jsp页面获取用户名和密码
			String username =  request.getParameter("username");
			String password =  request.getParameter("password");
			String xingming =  request.getParameter("xingming");
			String dianhua =  request.getParameter("dianhua");
			String dizhi =  request.getParameter("dizhi");
			//查询该用户名是否已经注册
			User bean = userDao.selectBean(" where username='"+username+"' ");
			if(bean==null){
				bean = new User();
				bean.setDianhua(dianhua);
				bean.setDizhi(dizhi);
				bean.setPassword(password);
				bean.setRole(0);
				bean.setUsername(username);
				bean.setXingming(xingming);
				userDao.insertBean(bean);

				writer.print("<script language='javascript'>alert('注册成功，请妥善保管您的账户');window.location.href='"+basePath+"login.jsp'; </script>");
			}else{
				
				writer.print("<script  language='javascript'>alert('该用户名已经被注册，请重新注册！');window.location.href='"+basePath+"register.jsp';</script>");
			}
		}
		
		//用户登录
		else if("login".equals(method)){
			//从jsp页面获取用户名和密码
			String username =  request.getParameter("username");
			String password =  request.getParameter("password");
			//查询用户名和密码是否匹配
			User bean = userDao.selectBean(" where username='"+username+"' and password ='"+password+"' and role=0 ");
			if(bean!=null){
				HttpSession session = request.getSession();
				session.setAttribute("user", bean);
				
				writer.print("<script language='javascript'>alert('登录成功');window.location.href='"+basePath+".'; </script>");
			}else{
				
				writer.print("<script  language='javascript'>alert('用户名或者密码错误');window.location.href='"+basePath+"login.jsp';</script>");
			}
		}
		
		//退出操作
		 else if("loginout".equals(method)){
			
			 HttpSession session  =request.getSession();
			 session.removeAttribute("user");
			 writer.print("<script  language='javascript'>alert('退出成功');window.location.href='"+basePath+".';</script>");

		}

				//修改密码操作
		else if("passwordupdate2".equals(method)){
					
					//从JSP获取信息
					String password1 = request.getParameter("password1");
					String password2 = request.getParameter("password2");
					HttpSession session = request.getSession();
					User user = (User)session.getAttribute("user");
					
					User u = userDao.selectBean(" where username='"+user.getUsername()+"' and password='"+password1+"'  ");
					if(u!=null){
						u.setPassword(password2);
						userDao.updateBean(u);
						writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"passwordupdate.jsp'; </script>");
					}else{
						writer.print("<script  language='javascript'>alert('操作失败，原密码错误！');window.location.href='"+basePath+"passwordupdate.jsp'; </script>");
					}
					
					
					
		}
		
		
		//跳转到修改个人信息页面
		else if("userupdate".equals(method)){
			
			//通过ID获取对象
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			User bean = userDao.selectBean(" where id= "+user.getId());
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			request.setAttribute("url", "indexServlet/userupdate2?id="+bean.getId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/userupdate.jsp");
			dispatcher.forward(request, response);
		}
		
		//修改个人信息操作
		else if("userupdate2".equals(method)){
			
			//从JSP获取信息
			String xingming =  request.getParameter("xingming");
			String dianhua =  request.getParameter("dianhua");
			String dizhi =  request.getParameter("dizhi");
			//通过ID获取对象
			String id = request.getParameter("id");
			User bean = userDao.selectBean(" where id= "+id);
			//更新对象属性
			bean.setXingming(xingming);
			bean.setDianhua(dianhua);
			bean.setDizhi(dizhi);
			//更新操作
			userDao.updateBean(bean);
			
			
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"indexServlet/userupdate'; </script>");
		}
		
		//跳转到查看商品详情页面
		else if("productupdate".equals(method)){
			
			//通过ID获取对象
			String id = request.getParameter("id");
			Product bean = productDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			bean.setDianjishu(bean.getDianjishu()+1);
			productDao.updateBean(bean);
			request.setAttribute("bean", bean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/product.jsp");
			dispatcher.forward(request, response);
		}
		
		//跳转到查看公告页面
		else if("gonggaoupdate".equals(method)){
			
			//通过ID获取对象
			String id = request.getParameter("id");
			Gonggao bean = gonggaoDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/gonggao.jsp");
			dispatcher.forward(request, response);
		}
		
		//添加商品到购物车操作
		else if("gouwucheadd2".equals(method)){

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null) {
				writer.print("<script  language='javascript'>alert('请先登录');window.location.href='"+basePath+"login.jsp';</script>");
				return  ;
			}
			
			Product pro = productDao.selectBean(" where id= "+request.getParameter("pid"));
			
			Gouwuche bean = gouwucheDao.selectBean(" where userid="+user.getId()+" and pid="+pro.getId()+" ");
			if(bean!=null){
				writer.print("<script  language='javascript'>alert('该商品已经添加到购物车，请勿重复添加');window.location.href='"+basePath+"indexServlet/gouwuchelist';</script>");
				return  ;
			}
			bean = new Gouwuche();
			bean.setJiage(pro.getJiage());
			bean.setPid(pro.getId());
			bean.setPname(pro.getPname());
			bean.setShuliang(1);
			bean.setUserid(user.getId());
			
			gouwucheDao.insertBean(bean);
			
			writer.print("<script  language='javascript'>alert('添加成功');window.location.href='"+basePath+"indexServlet/gouwuchelist';</script>");
			
		}
		
		
		//我的购物车列表
		else if("gouwuchelist".equals(method)){
			
			//定义跳转的地址
			url = "indexServlet/gouwuchelist";
			

			
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
			sb.append(" userid="+user.getId()+" order by id desc ");
			String where = sb.toString();

			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String,List<Gouwuche>> map = gouwucheDao.getList(1,999,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<Gouwuche> list = map.get(pagerinfo);
			
			//返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);

			double zongjia = 0;
			for(Gouwuche g:list){
				zongjia = zongjia+(g.getJiage()*g.getShuliang());
			}
			request.setAttribute("zongjia", zongjia);
			
			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/gouwuchelist.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}
		
		
		//修改购物商品数量操作
		else if("gouwucheupdate2".equals(method)){

			String id = request.getParameter("id");
			String number = request.getParameter("number");
			
			Gouwuche bean = gouwucheDao.selectBean(" where id= "+id);
			
			
			bean.setShuliang(Integer.parseInt(number));

			gouwucheDao.updateBean(bean);
			
			writer.print("<script  language='javascript'>alert('变更成功');window.location.href='"+basePath+"indexServlet/gouwuchelist';</script>");
			
		}
		
		
		//删除购买的商品操作
		else if("gouwuchedelete".equals(method)){

			String id = request.getParameter("id");

			Gouwuche bean = gouwucheDao.selectBean(" where id= "+id);

			gouwucheDao.deleteBean(bean);
			
			writer.print("<script  language='javascript'>alert('删除成功');window.location.href='"+basePath+"indexServlet/gouwuchelist';</script>");
			
		}
		
		
		//跳转到生成订单页面
		else if("dingdanadd".equals(method)){
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
			User bean = userDao.selectBean(" where id= "+user.getId());
			
			
			request.setAttribute("bean", bean);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/dingdanadd.jsp");
			dispatcher.forward(request, response);
		}
		
		//生成订单操作
		else if("dingdanadd2".equals(method)){

			
			//从JSP获取信息
			String userid = request.getParameter("userid");
			String xingming = request.getParameter("xingming");
			String dianhua = request.getParameter("dianhua");
			String dizhi = request.getParameter("dizhi");
			String beizhu = request.getParameter("beizhu");
			
			Dingdan bean = new Dingdan();
			
			bean.setBeizhu(beizhu);
			bean.setDianhua(dianhua);
			bean.setDizhi(dizhi);
			bean.setOrderid(Util.getTime2());
			bean.setShijian(Util.getTime());
			bean.setStatus("未处理");
			bean.setUserid(Integer.parseInt(userid));
			bean.setXingming(xingming);
		
			
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
			sb.append(" userid="+user.getId()+" order by id desc ");
			String where = sb.toString();

			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String,List<Gouwuche>> map = gouwucheDao.getList(1,999,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<Gouwuche> list = map.get(pagerinfo);
			
			StringBuffer sbsb = new StringBuffer();
			double zongjia = 0;
			for(Gouwuche g:list){
				sbsb.append("商品名："+g.getPname()+",单价:"+g.getJiage()+",购买数量："+g.getShuliang()+",小计："+(g.getJiage()*g.getShuliang())+"<br/>");
				zongjia = zongjia+(g.getJiage()*g.getShuliang());
				
				Product p  =productDao.selectBean(" where id= "+g.getPid());
				p.setXiaoliang(p.getXiaoliang()+g.getShuliang());
				productDao.updateBean(p);
				
				gouwucheDao.deleteBean(g);
				
				
			}
			bean.setZongjia(zongjia);
			bean.setXiangqing(sbsb.toString());
			
			
			dingdanDao.insertBean(bean);
			
			//返回给JSP页面
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"indexServlet/orderlist'; </script>");
		}
		
		
		//我的订单列表
		else if("orderlist".equals(method)){
			
			//定义跳转的地址
			url = "indexServlet/orderlist";
			
			//获取查询的信息
			
			String orderid = request.getParameter("orderid");		
			String status = request.getParameter("status");		
			
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			if(orderid!=null&&!"".equals(orderid)){
							
				sb.append(" orderid like '%"+orderid+"%' ");
				sb.append(" and ");
				
				request.setAttribute("orderid", orderid);
			}
			
			if(status!=null&&!"".equals(status)){
				
				sb.append(" status like '%"+status+"%' ");
				sb.append(" and ");
				
				request.setAttribute("status", status);
			}
			
	
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
			sb.append(" userid="+user.getId()+" order by id desc ");
			String where = sb.toString();

			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String,List<Dingdan>> map = dingdanDao.getList(pagenum,pagesize,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<Dingdan> list = map.get(pagerinfo);
			
			//返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);

			
			
			
			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/orderlist.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}
		
		
		//跳转查看订单详情页面
		else if("dingdanupdate3".equals(method)){
			
			
			Dingdan bean = dingdanDao.selectBean(" where id= "+request.getParameter("id"));
			
			
			request.setAttribute("bean", bean);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/dingdanupdate3.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}
	
	
	

}
