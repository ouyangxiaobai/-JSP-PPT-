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
	if(document.getElementById("usernameid").value==""){
		
		alert('用户名不能为空');
		return false;
	}
	
	var valid = /^\w+$/;
	if(!valid.test(document.getElementById("usernameid").value)){
		alert('用户名必须是数字，字母或者下划线');
		return false;
		
	}
	
	if(document.getElementById("passwordid").value==""){
		
		alert('密码不能为空');
		return false;
	}
	
	if(document.getElementById("passwordid").value.length<6){
		
		alert('密码长度至少6位');
		return false;
	}
	
	if(document.getElementById("passwordid").value!=document.getElementById("password2id").value){
		
		alert('确认密码和原密码不一致');
		return false;
	}
	
	if(document.getElementById("xingmingid").value==""){
		
		alert('姓名不能为空');
		return false;
	}
	
	
	if(document.getElementById("dizhiid").value==""){
		
		alert('收货地址不能为空');
		return false;
	}
	
	if(document.getElementById("dianhuaid").value==""){
		
		alert('手机不能为空');
		return false;
	}
	
	valid = /^0?1[3,5,8][0,1,2,3,4,5,6,7,8,9]\d{8}$/;
	
	if(!valid.test(document.getElementById("dianhuaid").value)){
		
		alert('请输入正确的手机格式');
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
    <h2>新用户注册</h2> 
                    
                    


	
    
   
    <div id="tab1" class="tabcontent">
        
        <form action="indexServlet/register" method="post" onsubmit="return checkform()">
        
        <div class="form">
            
            <div class="form_row">
            <label>用户名:</label>
            <input type="text" class="form_input" name="username" id="usernameid" />
            </div>
             
            <div class="form_row">
            <label>密码:</label>
            <input type="password" class="form_input" name="password"  id="passwordid"/>
            </div>
            
            <div class="form_row">
            <label>确认密码:</label>
            <input type="password" class="form_input" name="password2"  id="password2id"/>
            </div>
            
            <div class="form_row">
            <label>姓名:</label>
            <input type="text" class="form_input" name="xingming"  id="xingmingid"/>
            </div>
            
            <div class="form_row">
            <label>收货地址:</label>
            <input type="text" class="form_input" name="dizhi"  id="dizhiid"/>
            </div>
            
            <div class="form_row">
            <label>手机:</label>
            <input type="text" class="form_input" name="dianhua"  id="dianhuaid"/>
            </div>
            
            
            <div class="form_row">
            <input type="submit" class="form_submit" value="注册" />
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
