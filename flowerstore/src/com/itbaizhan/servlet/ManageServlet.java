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
import com.itbaizhan.bean.Fenlei;
import com.itbaizhan.bean.Gonggao;
import com.itbaizhan.bean.Product;
import com.itbaizhan.bean.User;
import com.itbaizhan.dao.DingdanDao;
import com.itbaizhan.dao.FenleiDao;
import com.itbaizhan.dao.GonggaoDao;
import com.itbaizhan.dao.ProductDao;
import com.itbaizhan.dao.UserDao;
import com.itbaizhan.util.Util;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;


//后台servlet，用于处理后台的所有请求
public class ManageServlet extends HttpServlet {

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
		
		FenleiDao fenleiDao = new FenleiDao();
		
		ProductDao productDao = new ProductDao();
		
		GonggaoDao gonggaoDao = new GonggaoDao();
		
		DingdanDao dingdanDao = new DingdanDao();
		
		
		//用户登录
		if("login".equals(method)){
			//从jsp页面获取用户名和密码
			String username =  request.getParameter("username");
			String password =  request.getParameter("password");
			//查询用户名和密码是否匹配
			User bean = userDao.selectBean(" where username='"+username+"' and password ='"+password+"' and role=1 ");
			if(bean!=null){
				HttpSession session = request.getSession();
				session.setAttribute("manage", bean);
				
				writer.print("<script language='javascript'>alert('登录成功');window.location.href='"+basePath+"manage/index.jsp'; </script>");
			}else{
				
				writer.print("<script  language='javascript'>alert('用户名或者密码错误');window.location.href='"+basePath+"manage/login.jsp';</script>");
			}
		}
		
		//退出操作
		 else if("loginout".equals(method)){
			
			 HttpSession session  =request.getSession();
			 session.removeAttribute("manage");
			 writer.print("<script  language='javascript'>alert('退出成功');window.location.href='"+basePath+"manage/login.jsp';</script>");

		}
		
		//跳转到修改密码页面
		else if("passwordupdate".equals(method)){

				request.setAttribute("biaoti", "修改密码");
				request.setAttribute("url", "manageServlet/passwordupdate2");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/passwordupdate.jsp");
				dispatcher.forward(request, response);
		}
			
			//修改密码操作
		else if("passwordupdate2".equals(method)){
				
				//从JSP获取信息
				String password1 = request.getParameter("password1");
				String password2 = request.getParameter("password2");
				HttpSession session = request.getSession();
				User user = (User)session.getAttribute("manage");
				
				User u = userDao.selectBean(" where username='"+user.getUsername()+"' and password='"+password1+"'  ");
				if(u!=null){
					u.setPassword(password2);
					userDao.updateBean(u);
					writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/passwordupdate'; </script>");
				}else{
					writer.print("<script  language='javascript'>alert('操作失败，原密码错误！');window.location.href='"+basePath+"manageServlet/passwordupdate'; </script>");
				}
				
				
				
		}
		
		
		
		//分类信息列表
		else if("fenleilist".equals(method)){
			
			//定义跳转的地址
			url = "manageServlet/fenleilist";
			
			//获取查询的信息
			String fname = request.getParameter("fname");	
			
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			if(fname!=null&&!"".equals(fname)){
							
				sb.append(" fname like '%"+fname+"%' ");
				sb.append(" and ");
				
				request.setAttribute("fname", fname);
			}
			sb.append(" 1=1 order by id desc ");
			String where = sb.toString();

			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String,List<Fenlei>> map = fenleiDao.getList(pagenum,pagesize,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<Fenlei> list = map.get(pagerinfo);
			
			//返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);
			request.setAttribute("biaoti", "分类信息列表");
			request.setAttribute("url", "manageServlet/fenleilist");
			request.setAttribute("url2", "manageServlet/fenlei");
			
			
			
			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/fenlei/fenleilist.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}
		
		
		//跳转到添加分类信息页面
		else if("fenleiadd".equals(method)){
			request.setAttribute("biaoti", "添加分类信息");
			request.setAttribute("url", "manageServlet/fenleiadd2");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/fenlei/fenleiadd.jsp");
			dispatcher.forward(request, response);
		}
		
		//添加分类信息操作
		else if("fenleiadd2".equals(method)){

			//从JSP获取信息
			String fname = request.getParameter("fname");
			//定义对象
			Fenlei bean = new Fenlei();
			//设置对象的属性
			bean.setFname(fname);
			//插入数据库
			fenleiDao.insertBean(bean);
			//返回给JSP页面
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/fenleilist'; </script>");
		}
		
		//跳转到更新分类信息页面
		else if("fenleiupdate".equals(method)){
			
			//通过ID获取对象
			String id = request.getParameter("id");
			Fenlei bean = fenleiDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			request.setAttribute("biaoti", "更新分类信息");
			request.setAttribute("url", "manageServlet/fenleiupdate2?id="+bean.getId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/fenlei/fenleiupdate.jsp");
			dispatcher.forward(request, response);
		}
		
		//更新分类信息操作
		else if("fenleiupdate2".equals(method)){
			
			//从JSP获取信息
			String fname = request.getParameter("fname");
			//通过ID获取对象
			String id = request.getParameter("id");
			Fenlei bean = fenleiDao.selectBean(" where id= "+id);
			//更新对象属性
			bean.setFname(fname);
			//更新操作
			fenleiDao.updateBean(bean);
			
			
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/fenleilist'; </script>");
		}
		
		//删除分类信息操作
		else if("fenleidelete".equals(method)){
			//通过ID获取对象
			String id = request.getParameter("id");
			Fenlei bean = fenleiDao.selectBean(" where id= "+id);
			//删除对象
			fenleiDao.deleteBean(bean);
			
		
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/fenleilist'; </script>");
		}
		
		//跳转到查看分类信息页面
		else if("fenleiupdate3".equals(method)){
			request.setAttribute("biaoti", "查看分类信息");
			//通过ID获取对象
			String id = request.getParameter("id");
			Fenlei bean = fenleiDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/fenlei/fenleiupdate3.jsp");
			dispatcher.forward(request, response);
		}
		
		
		//商品信息列表
		else if("productlist".equals(method)){
			
			//定义跳转的地址
			url = "manageServlet/productlist";
			
			//获取查询的信息
			String fname = request.getParameter("fname");	
			request.setAttribute("fenleilist", fenleiDao.getList(""));
			String pname = request.getParameter("pname");	
			
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			if(fname!=null&&!"".equals(fname)){
							
				sb.append(" fname like '%"+fname+"%' ");
				sb.append(" and ");
				
				request.setAttribute("fname", fname);
			}
			
			if(pname!=null&&!"".equals(pname)){
				
				sb.append(" pname like '%"+pname+"%' ");
				sb.append(" and ");
				
				request.setAttribute("pname", pname);
			}
			
			sb.append(" 1=1 order by id desc ");
			String where = sb.toString();

			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String,List<Product>> map = productDao.getList(pagenum,pagesize,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<Product> list = map.get(pagerinfo);
			
			//返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);
			request.setAttribute("biaoti", "商品信息列表");
			request.setAttribute("url", "manageServlet/productlist");
			request.setAttribute("url2", "manageServlet/product");
			
			
			
			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/product/productlist.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}
		
		
		//跳转到添加商品信息页面
		else if("productadd".equals(method)){
			request.setAttribute("biaoti", "添加商品信息");
			request.setAttribute("url", "manageServlet/productadd2");
			request.setAttribute("fenleilist", fenleiDao.getList(""));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/product/productadd.jsp");
			dispatcher.forward(request, response);
		}
		
		//添加商品信息操作
		else if("productadd2".equals(method)){

			
			//从JSP获取信息
			String fenleiid = request.getParameter("fenleiid");
			String jiage = request.getParameter("jiage");
			String miaoshu = request.getParameter("miaoshu");
			String pname = request.getParameter("pname");
			
			
			Fenlei fenlei = fenleiDao.selectBean(" where id= "+fenleiid);
			//定义对象
			Product bean = new Product();
			//设置对象的属性
			bean.setCreatetime(Util.getTime());
			bean.setDianjishu(0);
			bean.setFenleiid(fenlei.getId()+"");
			bean.setFname(fenlei.getFname());
			bean.setJiage(Double.parseDouble(jiage));
			bean.setMiaoshu(miaoshu);
			bean.setPname(pname);
			bean.setTuijian("未推荐");
			bean.setXiaoliang(0);
			
			
			//插入数据库
			productDao.insertBean(bean);
			//返回给JSP页面
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/productlist'; </script>");
		}
		
		//跳转到更新商品信息页面
		else if("productupdate".equals(method)){
			
			//通过ID获取对象
			String id = request.getParameter("id");
			Product bean = productDao.selectBean(" where id= "+id);
			request.setAttribute("fenleilist", fenleiDao.getList(""));
			//把信息传给jsp页面
			request.setAttribute("bean", bean);
			request.setAttribute("biaoti", "更新商品信息");
			request.setAttribute("url", "manageServlet/productupdate2?id="+bean.getId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/product/productupdate.jsp");
			dispatcher.forward(request, response);
		}
		
		//更新商品信息操作
		else if("productupdate2".equals(method)){
			
			//从JSP获取信息
			String fenleiid = request.getParameter("fenleiid");
			String imgpath = request.getParameter("imgpath");
			String jiage = request.getParameter("jiage");
			String miaoshu = request.getParameter("miaoshu");
			String pname = request.getParameter("pname");
			Fenlei fenlei = fenleiDao.selectBean(" where id= "+fenleiid);
			
			//通过ID获取对象
			String id = request.getParameter("id");
			Product bean = productDao.selectBean(" where id= "+id);
			//更新对象属性
			bean.setFenleiid(fenlei.getId()+"");
			bean.setFname(fenlei.getFname());
			bean.setImgpath(imgpath);
			bean.setJiage(Double.parseDouble(jiage));
			bean.setMiaoshu(miaoshu);
			bean.setPname(pname);
			//更新操作
			productDao.updateBean(bean);
			
			
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/productlist'; </script>");
		}
		
		
		//跳转到上传商品图片页面
		else if("productupdate5".equals(method)){
			
			//通过ID获取对象
			String id = request.getParameter("id");
			Product bean = productDao.selectBean(" where id= "+id);
			//把信息传给jsp页面
			request.setAttribute("bean", bean);
			request.setAttribute("biaoti", "上传商品图片");
			request.setAttribute("url", "manageServlet/productupdate6?id="+bean.getId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/product/productupdate5.jsp");
			dispatcher.forward(request, response);
		}
		
		//上传商品图片操作
		else if("productupdate6".equals(method)){
			
			SmartUpload su = new SmartUpload();
			su.initialize(this.getServletConfig(), request, response);
			//限制上传文件的最大长度。
			su.setMaxFileSize(1500000*8);
			//设定允许上传的文件（通过扩展名限制）
			su.setAllowedFilesList("jpg,gif,bmp,JPG,GIF,BMP");
			String imgpath="";
			try {
				// 上传文件
				su.upload();
				// 将上传文件全部保存到指定目录
				com.jspsmart.upload.File file = su.getFiles().getFile(0);
				if (!file.isMissing())
				{

					//将附件以 当前时间+附件扩展名 作为文件名保存		
					java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
					String sNowTime = dateFormatter.format(new java.util.Date());
					imgpath = sNowTime+"."+ file.getFileExt();
					file.saveAs("/uploadfile/"+imgpath);
				}
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
			
			//通过ID获取对象
			String id = request.getParameter("id");
			Product bean = productDao.selectBean(" where id= "+id);
			//更新对象属性
			bean.setImgpath(imgpath);

			//更新操作
			productDao.updateBean(bean);
			
			
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/productlist'; </script>");
		}
		
		//删除商品信息操作
		else if("productdelete".equals(method)){
			//通过ID获取对象
			String id = request.getParameter("id");
			Product bean = productDao.selectBean(" where id= "+id);
			//删除对象
			productDao.deleteBean(bean);
			
		
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/productlist'; </script>");
		}
		
		//推荐商品信息操作
		else if("productdelete2".equals(method)){
			//通过ID获取对象
			String id = request.getParameter("id");
			Product bean = productDao.selectBean(" where id= "+id);
			bean.setTuijian("已推荐");
			
			productDao.updateBean(bean);
		
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/productlist'; </script>");
		}
		
		//删除商品信息操作
		else if("productdelete3".equals(method)){
			//通过ID获取对象
			String id = request.getParameter("id");
			Product bean = productDao.selectBean(" where id= "+id);
			bean.setTuijian("未推荐");
			
			productDao.updateBean(bean);
			
		
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/productlist'; </script>");
		}
		
		//跳转到查看商品信息页面
		else if("productupdate3".equals(method)){
			request.setAttribute("biaoti", "查看商品信息");
			//通过ID获取对象
			String id = request.getParameter("id");
			Product bean = productDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/product/productupdate3.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
		//公告信息列表
		else if("gonggaolist".equals(method)){
			
			//定义跳转的地址
			url = "manageServlet/gonggaolist";
			
			//获取查询的信息
			String biaotibiaoti = request.getParameter("biaotibiaoti");	
			
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			if(biaotibiaoti!=null&&!"".equals(biaotibiaoti)){
							
				sb.append(" biaoti like '%"+biaotibiaoti+"%' ");
				sb.append(" and ");
				
				request.setAttribute("biaoti", biaotibiaoti);
			}
			sb.append(" 1=1 order by id desc ");
			String where = sb.toString();

			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String,List<Gonggao>> map = gonggaoDao.getList(pagenum,pagesize,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<Gonggao> list = map.get(pagerinfo);
			
			//返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);
			request.setAttribute("biaoti", "公告信息列表");
			request.setAttribute("url", "manageServlet/gonggaolist");
			request.setAttribute("url2", "manageServlet/gonggao");
			
			
			
			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/gonggao/gonggaolist.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}
		
		
		//跳转到添加公告信息页面
		else if("gonggaoadd".equals(method)){
			request.setAttribute("biaoti", "添加公告信息");
			request.setAttribute("url", "manageServlet/gonggaoadd2");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/gonggao/gonggaoadd.jsp");
			dispatcher.forward(request, response);
		}
		
		//添加公告信息操作
		else if("gonggaoadd2".equals(method)){

			//从JSP获取信息
			String biaoti = request.getParameter("biaoti");
			String neirong = request.getParameter("neirong");
			//定义对象
			Gonggao bean = new Gonggao();
			//设置对象的属性
			bean.setBiaoti(biaoti);
			bean.setNeirong(neirong);
			bean.setShijian(Util.getTime());
			//插入数据库
			gonggaoDao.insertBean(bean);
			//返回给JSP页面
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/gonggaolist'; </script>");
		}
		
		//跳转到更新公告信息页面
		else if("gonggaoupdate".equals(method)){
			
			//通过ID获取对象
			String id = request.getParameter("id");
			Gonggao bean = gonggaoDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			request.setAttribute("biaoti", "更新公告信息");
			request.setAttribute("url", "manageServlet/gonggaoupdate2?id="+bean.getId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/gonggao/gonggaoupdate.jsp");
			dispatcher.forward(request, response);
		}
		
		//更新公告信息操作
		else if("gonggaoupdate2".equals(method)){
			
			//从JSP获取信息
			String biaoti = request.getParameter("biaoti");
			String neirong = request.getParameter("neirong");
			//通过ID获取对象
			String id = request.getParameter("id");
			Gonggao bean = gonggaoDao.selectBean(" where id= "+id);
			//更新对象属性
			bean.setBiaoti(biaoti);
			bean.setNeirong(neirong);
			//更新操作
			gonggaoDao.updateBean(bean);
			
			
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/gonggaolist'; </script>");
		}
		
		//删除公告信息操作
		else if("gonggaodelete".equals(method)){
			//通过ID获取对象
			String id = request.getParameter("id");
			Gonggao bean = gonggaoDao.selectBean(" where id= "+id);
			//删除对象
			gonggaoDao.deleteBean(bean);
			
		
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/gonggaolist'; </script>");
		}
		
		//跳转到查看公告信息页面
		else if("gonggaoupdate3".equals(method)){
			request.setAttribute("biaoti", "查看公告信息");
			//通过ID获取对象
			String id = request.getParameter("id");
			Gonggao bean = gonggaoDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/gonggao/gonggaoupdate3.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
		//订单信息列表
		else if("dingdanlist".equals(method)){
			
			//定义跳转的地址
			url = "manageServlet/dingdanlist";
			
			//获取查询的信息
			String status = request.getParameter("status");	
			String orderid = request.getParameter("orderid");	
			
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
			sb.append(" 1=1 order by id desc ");
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
			request.setAttribute("biaoti", "订单信息列表");
			request.setAttribute("url", "manageServlet/dingdanlist");
			request.setAttribute("url2", "manageServlet/dingdan");
			
			
			
			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/dingdan/dingdanlist.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}
		
		
		
		
		//处理订单信息操作
		else if("dingdandelete".equals(method)){
			//通过ID获取对象
			String id = request.getParameter("id");
			Dingdan bean = dingdanDao.selectBean(" where id= "+id);
			
			bean.setStatus("已处理");
			dingdanDao.updateBean(bean);
			
		
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/dingdanlist'; </script>");
		}
		
		//跳转到查看订单信息页面
		else if("dingdanupdate3".equals(method)){
			request.setAttribute("biaoti", "查看订单信息");
			//通过ID获取对象
			String id = request.getParameter("id");
			Dingdan bean = dingdanDao.selectBean(" where id= "+id);
			//把对象传给jsp页面
			request.setAttribute("bean", bean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/dingdan/dingdanupdate3.jsp");
			dispatcher.forward(request, response);
		}
		
		
		//用户信息列表
		else if("userlist".equals(method)){
			
			//定义跳转的地址
			url = "manageServlet/userlist";
			
			//获取查询的信息
			String username = request.getParameter("username");	
	
			
			//组装查询的SQL语句
			StringBuffer sb = new StringBuffer();
			sb.append(" where  ");
			
			if(username!=null&&!"".equals(username)){
							
				sb.append(" username like '%"+username+"%' ");
				sb.append(" and ");
				
				request.setAttribute("username", username);
			}
			
			
			sb.append(" role=0 order by id desc ");
			String where = sb.toString();

			//获取当前的页数
			if(request.getParameter("pagenum")!=null){
				pagenum = Integer.parseInt(request.getParameter("pagenum"));
			}

			//从数据库查询列表信息，带分页功能
			Map<String,List<User>> map = userDao.getList(pagenum,pagesize,url,where);
			String pagerinfo = map.keySet().iterator().next();
			List<User> list = map.get(pagerinfo);
			
			//返回给jsp页面的信息
			request.setAttribute("pagerinfo", pagerinfo);
			request.setAttribute("list", list);
			request.setAttribute("biaoti", "用户信息列表");
			request.setAttribute("url", "manageServlet/userlist");
			request.setAttribute("url2", "manageServlet/user");
			
			
			
			//定义跳转的地址
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manage/user/userlist.jsp");
			//跳转操作
			dispatcher.forward(request, response);
		}
		
		
		
		
		//删除用户信息操作
		else if("userdelete".equals(method)){
			//通过ID获取对象
			String id = request.getParameter("id");
			User bean = userDao.selectBean(" where id= "+id);
			
			userDao.deleteBean(bean);
			
		
			writer.print("<script  language='javascript'>alert('操作成功');window.location.href='"+basePath+"manageServlet/userlist'; </script>");
		}
		
		
		
	}

}
