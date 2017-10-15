<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>双体菜谱by刘莅</title>
	<meta charset="utf-8">
	
	<link rel="stylesheet" href="css/style.css"><!-- 首页轮播 -->
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.qrcode.min.js"></script><!-- 生成二维码 -->
	<script type="text/javascript" src="js/modernizr.custom.53451.js"></script><!-- 生成分享 -->
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript">
	
		/*	window.onload = function(){
				var getFangwenNum = ${requestScope.getFangwenNum};
				var message = "您好，您是第"+getFangwenNum+"个访问该网站的人,点击屏幕下方的分享给好友有福利哦";
				alert(message);
			}*/
	</script>
  </head>
  
  <body>
  <audio src="/upload/aaa.mp3" controls="controls"></audio>
  	<div id="top">
  		
  		
  		<c:if test="${sessionScope.user!=null}">
			<a href="${pageContext.request.contextPath }/addCai?userid=${sessionScope.user.id }">发布</a>
			<a href="${pageContext.request.contextPath }/servlet/getMyCai?current=1&userid=${sessionScope.user.id}" target="_blank">查看我菜谱</a>
			
		</c:if>
  		<a href="${pageContext.request.contextPath}/servlet/AllCai?current=1" target="_blank">查看所有菜谱</a>
		<c:if test="${sessionScope.user!=null}">
			<a href="${pageContext.request.contextPath }/unRegister">注销</a>
		</c:if>
		<c:if test="${sessionScope.user==null}">
			<a href="${pageContext.request.contextPath}/gotoLoginPage">登录</a>
		</c:if>
  	</div>
  	<div id="list">
  		<div id="deptlist">
  			<img id="logo" src="image/logo.png"/>
  			<c:forEach items="${deptList}" var="dept">
	 			<a href="${pageContext.request.contextPath}/servlet/GetCaiByDeptId?id=${dept.id}&current=0" target="_blank">${dept.name}</a>
	 		</c:forEach>
	 		<form id="search" action="${pageContext.request.contextPath}/selCaiByKey" method="post">
	 			<input id="inputs" name="key" type="text" style="border:1px solid #ccc;"/>
	 			<input id="submit" type="submit" value="搜索"/>
	 		</form>
  		</div>	
  	</div>
  	<br/><br/>
  	<center>
		<div class="demo">
			<a class="control prev"></a><a class="control next abs"></a><!--自定义按钮，移动端可不写-->
			<div class="slider"><!--主体结构，请用此类名调用插件，此类名可自定义-->
				<ul>
					<li><a href=""><img src="image/lunbo1.jpg" alt="两弯似蹙非蹙笼烟眉，一双似喜非喜含情目。" /></a></li>
					<li><a href=""><img src="image/lunbo2.jpg" alt="态生两靥之愁，娇袭一身之病。" /></a></li>
					<li><a href=""><img src="image/lunbo3.jpg" alt="泪光点点，娇喘微微。" /></a></li>
					<li><a href=""><img src="image/lunbo4.jpg" alt="闲静似娇花照水，行动如弱柳扶风。" /></a></li>
					<li><a href=""><img src="image/lunbo5.jpg" alt="心较比干多一窍，病如西子胜三分。" /></a></li>
					<li><a href=""><img src="image/lunbo6.jpg" alt="心较比干多一窍，病如西子胜三分。" /></a></li>
				</ul>
			</div>
		</div>
		
		<script src="js/YuxiSlider.jQuery.min.js"></script>
		<script>
		$(".slider").YuxiSlider({
			width:900, //容器宽度
			height:450, //容器高度
			control:$('.control'), //绑定控制按钮
			during:4000, //间隔4秒自动滑动
			speed:800, //移动速度0.8秒
			mousewheel:true, //是否开启鼠标滚轮控制
			direkey:true //是否开启左右箭头方向控制
		});
		</script>
	</center>
	<div id="box1">
		<div id="hotCai">
  			<p id="tip">热门菜谱</p>
			<c:forEach items="${hotCai}" var="hc">
				<div class="hotlist">
					<img class="hotimg" src="/upload/${hc.imgpath}"/><br/>
					<p class="hotname"><a href="${pageContext.request.contextPath}/showCai?id=${hc.id}" target="_blank">${hc.name}</a></p>
					<p class="hottime">${hc.time}</p>
					<p class="hotreadnum">阅读量:${hc.readnum}</p>	
				</div>
			</c:forEach>	
  		</div>
	
	</div>
	
	<br/>
	
	<div id="box2">
		<div id="show">
			<p id="tip">各菜系推荐菜谱</p>
  			<span class="dept" id="1">川菜</span>
  			<span class="dept" id="2">广东菜</span>
  			<span class="dept" id="3">山西菜</span>
  			<span class="dept" id="4">上海菜</span>
  			<span class="dept" id="5">北京菜</span>
		</div>
			
		<div id="tuijian"> 
  		  	<c:forEach items="${tuijian1}" var="t1">
  				<div class="tuijianlist">
  					<img class="tuijianimg" src="/upload/${t1.imgpath}"/><br/>
  					<p class="tuijianname"><a href="${pageContext.request.contextPath}/showCai?id=${t1.id}" target="_blank">${t1.name}</a></p>
  					<p class="tuijiantime">${t1.time}</p>
  					<p class="tuijianreadnum">阅读量:${t1.readnum}</p>
  				</div>
  			</c:forEach>
  		
  			<div class="tuijianlist">
  				<!-- ajax动态生成节点插入 -->
  			</div>
  			
  		</div>
	</div>

<hr/><hr/>
	<!-- Baidu Button BEGIN -->
		<div id="share">
			<div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare">
			        <a class="bds_tsina"></a>
			        <a class="bds_tqq"></a>
			        <a class="bds_renren"></a>
			        <a class="bds_qzone"></a>
			        <a class="bds_douban"></a>
			        <a class="bds_xg"></a>
			        <span class="bds_more">更多</span>
					<a class="shareCount"></a>
			</div>
			<script type="text/javascript" id="bdshare_js" data="type=tools" ></script>
			<script type="text/javascript" id="bdshell_js"></script>
			<script type="text/javascript">
			    document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + new Date().getHours();
			</script>
			<!-- Baidu Button END -->
			
			<div id="myqrcode">
  		<div id="qrcode"></div>
  	</div>
  	
  
	 <script type="text/javascript">  
	
	        jQuery('#qrcode').qrcode("http://stx.tunnel.2bdata.com/StxCp");  
	
	        //生成二维码的同时可以设置宽度和高度  
	   //   jQuery('#qrcode').qrcode({width: 400,height: 400,text: "www.baidu.com"});  
	  </script>  
		</div>
		    
	</body>
</html>
