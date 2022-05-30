<%@ page language="java" import="java.util.*,com.itbaizhan.dao.*,com.itbaizhan.bean.*" pageEncoding="UTF-8"%>
<div class="sidebar" id="sidebar">
    <h2>鲜花搜索</h2>
    <form action="./" method="post">
    <br/>
    鲜花名：<input type="text" name="pname" value="${pname }" />
    <br/> <br/>
     价格￥：<input type="text" name="jiage1" size="6" value="${jiage1 }" />--<input type="text" name="jiage2"  size="6" value="${jiage2 }"/>
    <br/>
    <input type="submit" value="搜索" />
        
      </form>  
    <h2>鲜花分类</h2>
    
        <ul>
            <li><a href="./?tuijian=1">热门推荐</a></li>
            <%
            FenleiDao fenleiDao = new FenleiDao();
            
            List<Fenlei> list = fenleiDao.getList("");
            
            for(Fenlei fl:list){
            %>
             <li><a href="./?fenleiid=<%=fl.getId() %>"><%=fl.getFname() %></a></li>
            <% 	
            }
            %>

        </ul> 
        
    <h2>销量排行榜</h2>
    <ul>
    <%
    ProductDao productDao = new ProductDao();
    
    Map<String,List<Product>> map = productDao.getList(1,10,""," order by xiaoliang desc ");
	String pagerinfo = map.keySet().iterator().next();
	List<Product> list2 = map.get(pagerinfo);
	for(Product p:list2){
	%>
	 <li><a href="indexServlet/productupdate?id=<%=p.getId() %>"><%=p.getPname() %></a></li>
	<% 	
	}
    %>
	</ul>   
         
    <h2>花店公告</h2> 
   <ul>
    <%
    GonggaoDao gonggaoDao = new GonggaoDao();
    
    Map<String,List<Gonggao>> map2 = gonggaoDao.getList(1,5,""," order by id desc ");
	String pagerinfo2 = map2.keySet().iterator().next();
	List<Gonggao> list3 = map2.get(pagerinfo2);
	for(Gonggao g:list3){
	%>
	 <li><a href="indexServlet/gonggaoupdate?id=<%=g.getId() %>" ><%=g.getBiaoti() %></a></li>
	<% 	
	}
    %>
	</ul>      
    
    </div>       
