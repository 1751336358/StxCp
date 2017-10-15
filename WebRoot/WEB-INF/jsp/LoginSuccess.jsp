<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录成功</title>
    

	
	<meta http-equiv="refresh" content="3; url=gotoIndex.jsp">

  </head>
  
  <body>
  	<h1>欢迎${sessionScope.user.name},登录成功</h1>
  	<p>3秒钟后跳转到首页</p>
  	
  </body>
</html>
