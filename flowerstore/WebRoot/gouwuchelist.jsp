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

<script language="javascript" type="text/javascript">

function changenum(id){
		var num = document.getElementById(id+"_num").value;
		var reg1 =  /^\d+$/;
	if (num.match(reg1) == null)
	{
		alert("购买数量必须为正整数");
		return false;
	}
		if (num == 0 )
	{
		alert("购买数量必须大于0的正整数");
		return false;
	}
		var now = new Date(); 
		var t = now.getTime()+''; 
		window.location.href="<%=basePath %>indexServlet/gouwucheupdate2?id="+id+"&number="+num+"&t="+t;
		
		
	}

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
    
    <h2>我的购物车</h2> 
      
             
                    
<table id="rounded-corner">
    <thead>
    	<tr>
        	
            <th align="left">商品名</th>
            <th align="left">单价</th>
            <th align="left">购买数量</th>
            <th align="left">小计</th>
            <th align="left">操作</th>
           
        </tr>
    </thead>
        <tfoot>
    	<tr>
        	<td colspan="12">
        	<span style="font-size: 30px;">
        	总计：￥${zongjia }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<a href="indexServlet/dingdanadd" >结算，生成订单</a>
    	</span>
        	</td>
        </tr>
    </tfoot>
    <tbody>
    	<c:forEach items="${list}" var="bean">
    	<tr class="odd">
        	
            <td>
            ${bean.pname }&nbsp;
            </td>
            <td>
            ￥${bean.jiage }&nbsp;
            </td>
            <td>
            <input type="text" name="number"  value="${bean.shuliang }" id="${bean.id }_num"  size="5"/>&nbsp;
    	<a href="javascript:;"  onclick="changenum(${bean.id })">变更</a> &nbsp;
            </td>
            <td>
            ￥${bean.jiage *bean.shuliang }&nbsp;
            </td>
            <td>
         <a href="indexServlet/gouwuchedelete?id=${bean.id }" onclick=" return confirm('确定要删除吗?'); ">删除</a>
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
