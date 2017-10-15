<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发布菜谱</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		#detail{
			width:300px;
			height:300px;
		}
	</style>

  </head>
  
  <body> 
  
  <h1>发布菜谱</h1>
   <form action="<%=basePath%>Upload" method="post" enctype="multipart/form-data">
   		<input type="text" name="userid" value="${userid}" hidden><br/>
   		菜名：<input name="name"/><br/><br/>
   		主料：<input type="text" name="zhuliao"/><br/><br/>
   		辅料：<input type="text" name="fuliao"/><br/><br/>
   		描述：<textarea id="detail" name="detail"></textarea><br/><br/>
   		菜系：<select name="deptid">
   			<c:forEach items="${deptList}" var="dept">
   				<option value="${dept.id }">${dept.name }</option>
   			</c:forEach>	
   			</select>
   		<br/>
   		<input type="file" name="file1">
   		<input type="submit" value="提交">
   </form>

  </body>
</html>
