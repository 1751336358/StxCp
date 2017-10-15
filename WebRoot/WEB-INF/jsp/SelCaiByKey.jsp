<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>根据关键字查询</title>
    
	<meta charset="utf-8"/>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SelCaiByKey.css">
	<!--<script type="text/javascript" src="${pageContext.request.contextPath}/js/SelCaiByKey.js"></script> -->
  </head>
  
  <body>
 	<h1>根据关键字查询菜谱列表</h1>
 	<table width="80%" border="1">
 		<tr>
 			<td>图片</td>
 			<td>菜名</td>
 			<td>主料</td>
 			<td>辅料</td>
 			<td>创建时间</td>
 			<td>点击量</td>
 		</tr>
 		<c:forEach items="${requestScope.caiList}" var="cai">
 			<tr>
 				<td><img src="/upload/${cai.imgpath }"/></td>
 				<td><a href="${pageContext.request.contextPath}/showCai?id=${cai.id}">${cai.name }</a></td>
 				<td>${cai.zhuliao}</td>
 				<td>${cai.fuliao }</td>
 				<td>${cai.time }</td>
 				<td>${cai.readnum }</td>
 			</tr>
 		</c:forEach>
 	
 	</table>
  </body>
</html>
