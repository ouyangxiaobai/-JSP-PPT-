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

function checkform()
{
	 
	

	if (document.getElementById('biaotiid').value=="")
	{
		alert("标题不能为空");
		return false;
	}
	
	return true;
	
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
    
    <h2>${biaoti }</h2> 
                    
                    


	
    
    
    <div id="tab1" class="tabcontent">
       
        <div class="form">
            
            <form action="${url }" method="post" onsubmit="return checkform()" >
            
            <TABLE cellSpacing=0 cellpadding="5" width="100%" align=center border=1>
            
            <tr>
            <td>
            标题:
            </td>
            <td> 
            <input type="text" name="biaoti" id="biaotiid" value="${bean.biaoti }" readonly="readonly" />
            </td>
            </tr>
            
            <tr>
            <td>
            内容:
            </td>
            <td> 
           <textarea rows="7" cols="50" name="neirong" readonly="readonly">${bean.neirong }</textarea>
            </td>
            </tr>
            
            
             <tr>
            <td>操作:</td><td> 
   
				<input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" />
            </td>
            </tr>
            
            </table>
            </form>
            
            
            <div class="clear"></div>
        </div>
    </div>
    

      
     </div>
     </div><!-- end of right content-->
                     
                    
   <%@ include file="../left.jsp" %>        
    
    
    <div class="clear"></div>
    </div> 

</div>

    	
</body>
</html>
