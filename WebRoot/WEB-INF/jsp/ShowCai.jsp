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
    
    <title>${requestScope.cai.name}详情</title>
    
	<meta charset="utf-8"/>
	<style type="text/css">
	
	
	
	</style>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ShowCai.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ShowCai.js"></script>
	<script type="text/javascript">
		$(function(){
			/*Ajax评论效果*/
		//	var form = document.getElementById("forms");
			if(${sessionScope.user == null}){
				var detail = document.getElementById("detail");
				var submit = document.getElementById("submit");
				detail.value="评论请登录";
				detail.disabled = true;
				submit.disabled = true;
			
			}else{
				var detail = document.getElementById("detail");
				var submit = document.getElementById("submit");
				detail.disabled = false;
				submit.disabled = false;
			}		
			$("#submit").click(function(){

				var caiid = $("#caiid").val();
				var userid = $("#userid").val();
				var detail = $("#detail").val();			
				var str = "caiid="+caiid+"&userid="+userid+"&detail="+detail;
				$.post("/StxCp/addSay",str,function(data){	//将评论插入数据库
					
					//再启动一个Ajax从后台查数据库取出评论的第一条数据
					$.get("/StxCp/selNewSay",true,function(say){
						eval("var newSay="+say);
				
						
					//	$("tr").first().before("<tr><td>"+newSay.id+"</td><td>"+newSay.userid+"</td><td>"+newSay.caiid+"</td><td>"+newSay.detail+"</td><td>"+newSay.time+"</td><tr>");
						$(".show2").first().before("<div class='show2'><p class='p1'>"+newSay.detail+"</p><p class='p2'>评论时间："+newSay.time+"</p></div>");
					})
				});
				//插入节点			
			})
			
		})

	</script>
  </head>
  
  <body>
  
    <div id="top">
    	<p>${requestScope.cai.name}详情</p>
    </div>
   
   	<div id="box">
   		<div id="show">
   			<img src="/upload/${requestScope.cai.imgpath}"/>
   			<p id="zhuliao">主料：${requestScope.cai.zhuliao}</p>
		    <p id="fuliao">辅料：${requestScope.cai.fuliao}</p>
		    <p id="readnum">阅读量：${requestScope.cai.readnum}</p>
		    <p id="time">时间：${requestScope.cai.time}</p>
		    <div id="zuofa">
		    	做法：${requestScope.cai.detail}
		    </div>
   		</div>
   	</div>
   <!-- 发表评论部分 -->
   <!-- if条件，user.id != cai.userid,显示评论区域，本用户不能评论本用户发表的菜谱 -->
   <c:if test="${sessionScope.user.id != requestScope.cai.userid}">
   
	    <div id="makesay">
	   		<div id="show1">
	   			<input id="caiid" type="text" name="caiid" value="${requestScope.cai.id}" hidden/><!-- 隐藏域 -->
		   		<input id="userid" type="text" name="userid" value="${sessionScope.user.id}" hidden/>
		   		<!-- 在后台生成时间 -->
		   		<textarea id="detail" name="detail"></textarea>
		   		<button id="submit">发布评论</button>
	   		</div>
	   </div>
   
   </c:if>
  

	<!-- 查看所有的评论区 -->
   	<hr/>
   	<h2>评论</h2>
	<div id="text">
		<c:forEach items="${sayList}" var="say">
			<!-- 每一个删除评论的i的都为该sayid，目的是方便dom节点操作 -->
   			<div class="show2" id="${say.id}">
				<p class="p1">${say.detail}</p>
				<p class="p2">评论时间：${say.time}</p>
				<c:if test="${requestScope.cai.userid == sessionScope.user.id}">
					<a class="delSay"  href="/StxCp/delSayBysayid?sayid=${say.id}">删除评论</a>
				</c:if>
				
			</div>	
   		</c:forEach>	
	</div>  	
  </body>
</html>
