<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网上花店</title>
<link rel="stylesheet" type="text/css" href="style.css" />

<script src="js/jquery.min.js"></script>
<script src="js/jquery.tabify.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var $ = jQuery.noConflict();
$(function() {
$('#tabsmenu').tabify();
$(".toggle_container").hide(); 
$(".trigger").click(function(){
	$(this).toggleClass("active").next().slideToggle("slow");
	return false;
});
});
</script>
</head>
<body>
<div id="panelwrap">
  	
	
    
    <%@ include file="head.jsp" %>         
                    
    <div class="center_content">  
 
    <div id="right_wrap">
    <div id="right_content">             
    <h2>鲜花列表</h2> 
                    
                    
<table id="rounded-corner">
    <thead>
    	<tr>
     
            <th>鲜花名</th>
            <th>图片</th>
            <th>分类名</th>
            <th>鲜花价格</th>
            <th>操作</th>
           
        </tr>
    </thead>
        <tfoot>
    	<tr>
        	<td colspan="12">${pagerinfo }</td>
        </tr>
    </tfoot>
    <tbody>
    <c:forEach items="${list}" var="bean">
    	<tr class="odd">
            <td align="center">${bean.pname }</td>
            <td align="center">
            <img src="<%=basePath %>uploadfile/${bean.imgpath }" width="100" height="100" />
            </td>
            <td align="center">${bean.fname }</td>
            <td align="center">￥${bean.jiage }</td>
            <td align="center">
             <a href="indexServlet/productupdate?id=${bean.id }">查看详情</a> &nbsp; 

  			<a href="indexServlet/gouwucheadd2?pid=${bean.id }" onclick="return confirm('确定要加入购物车吗?'); ">加入购物车</a>
            </td>
            
        </tr>
     </c:forEach>   
    	
  
        
    </tbody>
</table>

	
    

    
    
    
     
    

    
        
      
     </div>
     </div><!-- end of right content-->
                     
                    
    <%@ include file="left.jsp" %>          
    
    
    <div class="clear"></div>
    </div> <!--end of center_content-->
    
    <div class="footer">
<a href="manage/login.jsp">管理后台</a>
</div>

</div>

    	
</body>
</html>
