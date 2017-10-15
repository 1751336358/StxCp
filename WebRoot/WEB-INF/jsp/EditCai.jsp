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
    
    <title>修改菜谱</title>
    
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
		img{
			width:200px;
			height:200px;
		}
	</style>

  </head>
  
  <body> 
  <h1>&nbsp;修改菜谱</h1>
   <form action="<%=basePath%>EditCai" method="post">
   		<img src="/upload/${requestScope.cai.imgpath}"/>
   		<input type="text" name="id" value="${requestScope.cai.id}" hidden><br/>
   		菜名：<input name="name" value="${requestScope.cai.name}"/><br/><br/>
   		主料：<input type="text" name="zhuliao" value="${requestScope.cai.zhuliao}"/><br/><br/>
   		辅料：<input type="text" name="fuliao" value="${requestScope.cai.fuliao}"/><br/><br/>
   		描述：<textarea id="detail" name="detail">${requestScope.cai.detail}</textarea><br/><br/>
   		菜系：<select name="deptid">
   			<c:forEach items="${deptList}" var="dept">
   				<c:choose>
   					<c:when test="${dept.id == requestScope.cai.deptid}">
   						<option value="${dept.id }" selected>${dept.name }</option>
   					</c:when>
   					<c:otherwise>
   						<option value="${dept.id }">${dept.name }</option>
   					</c:otherwise>
   				</c:choose>	
   			</c:forEach>	
   			</select>
   		<br/>
   		<input type="submit" value="提交">
   </form>
  </body>
</html>
