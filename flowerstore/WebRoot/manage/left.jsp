<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path2 = request.getContextPath();
String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path2+"/";
%>

<div class="sidebar" id="sidebar">
    <h2>主菜单</h2>
    
        <ul>
            <li><a href="<%=basePath2 %>manageServlet/fenleilist" >分类管理</a></li>
            <li><a href="<%=basePath2 %>manageServlet/productlist" >鲜花管理</a></li>
            <li><a href="<%=basePath2 %>manageServlet/gonggaolist" >公告管理</a></li>
            <li><a href="<%=basePath2 %>manageServlet/dingdanlist" >订单管理</a></li>
            <li><a href="<%=basePath2 %>manageServlet/userlist" >用户管理</a></li>
        </ul>
        
    
        
    
         
          
    
 </div>
