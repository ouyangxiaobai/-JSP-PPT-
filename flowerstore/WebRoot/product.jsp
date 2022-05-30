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
    <h2>鲜花信息详情</h2> 
                    
                    


	
    
   
    <div id="tab1" class="tabcontent">
        
        <form action="indexServlet/gouwucheadd2?pid=${bean.id }" method="post" >
        
        <div class="form">
 
            
            <div class="form_row">
            <label>鲜花名:</label>
           ${bean.pname }
            </div>
             
            
            
            <div class="form_row">
            <label>鲜花图片:</label>
         <img src="<%=basePath %>uploadfile/${bean.imgpath }" width="300" height="300" />
            </div>
            
            <div class="form_row">
            <label>上架时间:</label>
           ${bean.createtime }
            </div>
            
            <div class="form_row">
            <label>分类名:</label>
           ${bean.fname }
            </div>
            
            <div class="form_row">
            <label>鲜花价格:</label>
          ￥ ${bean.jiage }
            </div>
            
            <div class="form_row">
            <label>是否推荐 :</label>
           ${bean.tuijian }
            </div>
            
            <div class="form_row">
            <label>鲜花点击数:</label>
           ${bean.dianjishu }
            </div>
            
            <div class="form_row">
            <label>鲜花销量:</label>
           ${bean.xiaoliang }
            </div>
            
            <div class="form_row">
            <label>鲜花介绍:</label>
           ${bean.miaoshu }
            </div>
            
            
            <div class="form_row">
            <input type="submit" class="form_submit" onclick="return confirm('确定要加入购物车吗?'); " value="加入购物车" />
            
           
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
