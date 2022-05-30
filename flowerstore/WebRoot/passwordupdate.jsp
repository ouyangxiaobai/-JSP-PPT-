<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<script type="text/javascript" language="javascript">

function checkform(){
	if(document.getElementById("password1id").value==""){
		
		alert('原密码不能为空');
		return false;
	}
	

	
	if(document.getElementById("password2id").value==""){
		
		alert('新密码不能为空');
		return false;
	}
	
	if(document.getElementById("password2id").value.length<6){
		
		alert('新密码长度必须大于6位');
		return false;
	}
	
	if(document.getElementById("password3id").value!=document.getElementById("password2id").value){
		
		alert('确认新密码和新密码不一致');
		return false;
	}
	
	
	
	
	
	return true;

}


</script>

</head>
<body>
<div id="panelwrap">
  	
	
    
    <%@ include file="head.jsp" %>         
                    
    <div class="center_content">  
 
    <div id="right_wrap">
    <div id="right_content">             
    <h2>修改密码</h2> 
                    
                    


	
    
   
    <div id="tab1" class="tabcontent">
        
        <form action="indexServlet/passwordupdate2" method="post" onsubmit="return checkform()">
        
        <div class="form">
            
            <div class="form_row">
            <label>原密码:</label>
            <input type="password" class="form_input" name="password1" id="password1id" />
            </div>
             
            <div class="form_row">
            <label>新密码:</label>
            <input type="password" class="form_input" name="password2"  id="password2id"/>
            </div>
            
            <div class="form_row">
            <label>确认新密码:</label>
            <input type="password" class="form_input" name="password3"  id="password3id"/>
            </div>
            
           
            
            <div class="form_row">
            <input type="submit" class="form_submit" value="提交" />
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
