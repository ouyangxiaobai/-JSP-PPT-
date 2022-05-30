<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>"/>

<title>网上花店管理后台</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="loginpanelwrap">
  	
	<div class="loginheader">
    <div class="logintitle">网上花店管理后台</div>
    </div>

     <form action="manageServlet/login" method="post">
    <div class="loginform">
        
        <div class="loginform_row">
        <label>用户名:</label>
        <input type="text" class="loginform_input" name="username" />
        </div>
        
        <div class="loginform_row">
        <label>密码:</label>
        <input type="password" class="loginform_input" name="password" />
        </div>
        
       
        
        <div class="loginform_row">
        <input type="submit" class="loginform_submit" value="Login" />
        
        <a href="./">返回首页</a>
        </div> 
        <div class="clear"></div>
    </div>
 	</form>

</div>

    	
</body>
</html>
