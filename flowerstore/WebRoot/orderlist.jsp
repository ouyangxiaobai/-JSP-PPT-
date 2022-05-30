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
    
    <h2>我的订单</h2> 
      <form action="indexServlet/orderlist" method="post">

       订单号:<input type="text" size="10"  name="orderid"  value="${orderid }"/>  
       订单状态:
       <select name="status">
       <option value="">所有选项</option>
       <option value="未处理" <c:if test="${status=='未处理' }">selected</c:if> >未处理</option>
       <option value="已处理" <c:if test="${status=='已处理' }">selected</c:if> >已处理</option>
       </select>     
       <input type="submit"  value="查询" />     
      </form>
             
                    
<table id="rounded-corner">
    <thead>
    	<tr>
        	
            <th align="left">订单号</th>
            <th align="left">收货人姓名</th>
            <th align="left">订单状态</th>
            <th align="left">生成时间</th>
            <th align="left">总价</th>

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
            ${bean.orderid }&nbsp;
            </td>
             <td>
            ${bean.xingming }&nbsp;
            </td>
             <td>
            ${bean.status }&nbsp;
            </td>
             <td>
            ${bean.shijian }&nbsp;
            </td>
             <td>
            ￥${bean.zongjia }&nbsp;
            </td>
            <td>
            <a href="indexServlet/dingdanupdate3?id=${bean.id }">查看订单详情</a>

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
