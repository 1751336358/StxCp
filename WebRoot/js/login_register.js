$(function(){
	/*改变背景颜色*/
	var imgbk = $("#imgbk");
	var i = 0;
	setInterval(function(){
		i++;
		if(i>4){
			i=1;
		}
		imgbk.attr("src","/StxCp/image/loginbk"+i+".jpg");
	
	},3000);
	
	/*更改登录注册的背景颜色*/
	var leftRegister = $("#cover #box #left");	//注册title
	var rightLogin = $("#cover #box #right");	//登录title
	var register_form = $("#cover #box #register_show");	//注册的表单
	var login_form = $("#cover #box #login_show");	//登陆的额表单
	var form = $("#cover #box #form");	//表单
	var btn = $("#cover #box #form #btn");	//提交按钮
	leftRegister.mouseover(function(){
		$(this).css({"background":"white","color":"black"});
		rightLogin.css({"background":"black","color":"white"});
		//注册的表单显示
		register_form.css({"display":"block"});
		//登录的表单隐藏
		login_form.css({"display":"none"});
		//btn改为注册
		btn.val("Register");
		//提交地址改为注册地址
		form.attr("action","/StxCp/register");
	})

	
	rightLogin.mouseover(function(){
		$(this).css({"background":"white","color":"black"});
		leftRegister.css({"background":"black","color":"white"});
		
		//登录的表单显示
		login_form.css({"display":"block"});
		//注册的表单隐藏
		register_form.css({"display":"none"});
		//btn改为登录
		btn.val("Login");
		//提交地址改为登录地址
		form.attr("action","/StxCp/Login");
	})
	
	/*Ajax测试登录用户名和密码*/
	var login_uname = $("#cover #box #login_show #loginusername");
	var login_pwd = $("#cover #box #login_show #loginpassword");
	login_uname.blur(function(){
		//检测登录用户名
		var value = $(this).val();
		$.post("/StxCp/test_loginUsername","login_username="+value,function(data){
			
			if(data == 0){
				//用户名不存在
				$("#cover #box #login_show  #test_login_username").css("display","block");
			}else{
				//用户名存在
				$("#cover #box #login_show  #test_login_username").css("display","none");
			}
		
		})
	});
	login_pwd.blur(function(){
		//检测登录密码
		var value = $(this).val();
		$.post("/StxCp/test_loginPwd","login_password="+value,function(data){
			
			if(data == 0){
				//密码 不正确
				$("#cover #box #login_show  #test_login_pwd").css("display","block");
			}else{
				//密码正确
				$("#cover #box #login_show  #test_login_pwd").css("display","none");
			}
		
		})
	});
	//Ajax测试注册时用户名是否存在
	var r_username = $("#cover #box #register_show #r_uname");
	r_username.blur(function(){
		var value = $(this).val();
		$.post("/StxCp/test_registerUsername","register_username="+value,function(data){
		if(data == 0){
			//用户名不存在，可以注册
			$("#cover #box #register_show #test_register_username").css("display","none");
		}else{
			//用户名存在，不可以注册
			$("#cover #box #register_show #test_register_username").css("display","block");
		}
		
	});
		
	})
	
})