<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网上花店管理后台</title>
<link rel="stylesheet" type="text/css" href="style.css" />

<!-- jQuery file -->
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
  	
	
	<%@ include file="../head.jsp" %>

    
    <div class="submenu">
    
    </div>          
                    
    <div class="center_content">  
 
    <div id="right_wrap">
    <div id="right_content">             
    
    <h2>${biaoti }</h2> 
      <form action="${url }" method="post">
      <input  onclick="javascript:window.location.href='<%=basePath %>${url2 }add';" class="buttoncss" type="button" value="添加新分类" />
     &nbsp;&nbsp;&nbsp;
       分类名:<input type="text" size="10"  name="fname"  value="${fname }"/>     
       <input type="submit"  value="查询" />     
      </form>
             
                    
<table id="rounded-corner">
    <thead>
    	<tr>
        	
            <th align="left">分类名</th>
            <th align="left">操作</th>
           
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
        	
            <td>
            ${bean.fname }&nbsp;
            </td>
            <td>
            <a href="${url2 }update3?id=${bean.id }">查看</a> &nbsp; &nbsp; &nbsp;
  			<a href="${url2 }update?id=${bean.id }">修改</a> &nbsp; &nbsp; &nbsp;
  			<a href="${url2 }delete?id=${bean.id }" onclick="return confirm('确定要删除吗?'); ">删除</a>
            </td>
           
        </tr>
        </c:forEach>

    	
    	
  
        
    </tbody>
</table>

	

      
     </div>
     
     </div><!-- end of right content-->
                     
                    
     <%@ include file="../left.jsp" %>        
    
    
    <div class="clear"></div>
    </div> 

</div>

    	
</body>
</html>
