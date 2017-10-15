<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录界面</title>
    
	<meta charset="utf-8"/>
	
	<link rel="stylesheet" type="text/css" href="css/login_register.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/login_register.js"></script>

  </head>
  
  <body id="bdy"> 
  <img id="imgbk" src="${pageContext.request.contextPath}/image/loginbk1.jpg"/>
  <div id="cover">
  	<div id="box">
  		<form id="form" action="<%=basePath%>Login" method="post">
  			<input type="text" name="test" value="xxxxx" style="display:none;"/>
  			<input type="text" name="test" value="yyyyy" style="display:none;"/>
  			<div id="left">注册</div>
  			<div id="right">登录</div>
	  		<div id="login_show">
	  			<input type="text" id="loginusername" name="login_username"/><span id="test_login_username">用户名错误</span><br/>
	    		<input type="text" id="loginpassword" name="login_password"/><span id="test_login_pwd">密码错误</span><br/>
	    		<input type="checkbox" id="checkbox" /><span id="nextautologin">下次自动登录</span>
	  		</div>
	    	<div id="register_show">
	    		<input type="text" id="r_zh" name="register_name" placeholder="账号"/><br/>
	    		<input type="text" id="r_uname" name="register_username" placeholder="用户名"/><span id="test_register_username">用户名存在</span><br/>
	    		<input type="text" id="r_pwd" name="register_password" placeholder="密码"/><br/>
	    	</div>
    		<input id="btn" type="submit" value="Login">
    	</form>
  	</div>
  </div>
    
  </body>
</html>
