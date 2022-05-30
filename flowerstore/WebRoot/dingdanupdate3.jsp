<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>"/>
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
    <h2>订单信息详情</h2> 
                    
                    


	
    
   
    <div id="tab1" class="tabcontent">
        
        <form action="indexServlet/gouwucheadd2?pid=${bean.id }" method="post" >
        
        <div class="form">
 
            
            <div class="form_row">
            <label>订单号:</label>
           ${bean.orderid }
            </div>
             
            
            
            <div class="form_row">
            <label>收货人姓名:</label>
        ${bean.xingming }
            </div>
            
            <div class="form_row">
            <label>收货人手机:</label>
           ${bean.dianhua }
            </div>
            
            <div class="form_row">
            <label>收货地址:</label>
           ${bean.dizhi }
            </div>
            
            <div class="form_row">
            <label>生成时间:</label>
           ${bean.shijian }
            </div>
            
            <div class="form_row">
            <label>总价 :</label>
           ￥${bean.zongjia }
            </div>
            
            <div class="form_row">
            <label>订单状态:</label>
           ${bean.status }
            </div>
            
            <div class="form_row">
            <label>备注:</label>
           ${bean.beizhu }
            </div>
            
            <div class="form_row">
            <label>订单详情:</label><br/><br/><br/>
           ${bean.xiangqing }
            </div>
            
            <div class="form_row">
           <input type="button" class="form_submit" value="返回"  onclick="javascript:history.go(-1);"/>
            
           
            </div> 
           
            <div class="clear"></div>
        </div>
        </form>
        
    </div>
    

    
    
    
     
    

    
        
      
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
