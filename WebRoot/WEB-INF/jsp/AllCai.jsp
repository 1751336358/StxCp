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
    
    <title>所有菜谱</title>
    
	<meta charset="utf-8"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AllCai.css">
  </head>
  
  <body>
  <!--
   当前页数：${requestScope.pageCai.current}<br/>
   上一页：${requestScope.pageCai.prePage}<br/>
   下一页：${requestScope.pageCai.nextPage}<br/>
   总页数：${requestScope.pageCai.totalPage}<br/>
   总记录数：${requestScope.pageCai.totalCount}<br/>
   页面尺寸：${requestScope.pageCai.pageSize}<br/>
   -->
   <h1>所有菜</h1>
   <div id="box">
   		<c:forEach items="${requestScope.pageCai.caiList}" var="cai">
   			<div class="show">
   				<div class="left">
   					<img src="/upload/${cai.imgpath}"/>
   				</div>
   				<div class="right">
   					<p class="p1"><a href="${pageContext.request.contextPath}/showCai?id=${cai.id}">${cai.name}</a></p>
   					<p class="p2">主料:${cai.zhuliao}</p>
   					<p class="p3">辅料:${cai.fuliao}</p>
   					<p class="p4">发布时间:${cai.time}</p>
   					<p class="p5">点击量:${cai.readnum}</p>
   				</div>			
   			</div>	
  		</c:forEach>
   	
   </div>
    <div id="last">
 		<div id="bar">
 		 	<a href="${pageContext.request.contextPath}/servlet/AllCai?current=${requestScope.pageCai.prePage}">上一页</a>
		   	<c:forEach items="${requestScope.pageCai.bar}" var="page">
		   		<a href="${pageContext.request.contextPath}/servlet/AllCai?current=${page}">${page}</a>
		  	</c:forEach>
		   	<a href="${pageContext.request.contextPath}/servlet/AllCai?current=${requestScope.pageCai.nextPage}">下一页</a>
 		</div>
 	</div>
   

  
   
  
   
  </body>
</html>
