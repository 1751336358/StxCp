$(function(){
	/*�ı䱳����ɫ*/
	var imgbk = $("#imgbk");
	var i = 0;
	setInterval(function(){
		i++;
		if(i>4){
			i=1;
		}
		imgbk.attr("src","/StxCp/image/loginbk"+i+".jpg");
	
	},3000);
	
	/*���ĵ�¼ע��ı�����ɫ*/
	var leftRegister = $("#cover #box #left");	//ע��title
	var rightLogin = $("#cover #box #right");	//��¼title
	var register_form = $("#cover #box #register_show");	//ע��ı�
	var login_form = $("#cover #box #login_show");	//��½�Ķ��
	var form = $("#cover #box #form");	//��
	var btn = $("#cover #box #form #btn");	//�ύ��ť
	leftRegister.mouseover(function(){
		$(this).css({"background":"white","color":"black"});
		rightLogin.css({"background":"black","color":"white"});
		//ע��ı���ʾ
		register_form.css({"display":"block"});
		//��¼�ı�����
		login_form.css({"display":"none"});
		//btn��Ϊע��
		btn.val("Register");
		//�ύ��ַ��Ϊע���ַ
		form.attr("action","/StxCp/register");
	})

	
	rightLogin.mouseover(function(){
		$(this).css({"background":"white","color":"black"});
		leftRegister.css({"background":"black","color":"white"});
		
		//��¼�ı���ʾ
		login_form.css({"display":"block"});
		//ע��ı�����
		register_form.css({"display":"none"});
		//btn��Ϊ��¼
		btn.val("Login");
		//�ύ��ַ��Ϊ��¼��ַ
		form.attr("action","/StxCp/Login");
	})
	
	/*Ajax���Ե�¼�û���������*/
	var login_uname = $("#cover #box #login_show #loginusername");
	var login_pwd = $("#cover #box #login_show #loginpassword");
	login_uname.blur(function(){
		//����¼�û���
		var value = $(this).val();
		$.post("/StxCp/test_loginUsername","login_username="+value,function(data){
			
			if(data == 0){
				//�û���������
				$("#cover #box #login_show  #test_login_username").css("display","block");
			}else{
				//�û�������
				$("#cover #box #login_show  #test_login_username").css("display","none");
			}
		
		})
	});
	login_pwd.blur(function(){
		//����¼����
		var value = $(this).val();
		$.post("/StxCp/test_loginPwd","login_password="+value,function(data){
			
			if(data == 0){
				//���� ����ȷ
				$("#cover #box #login_show  #test_login_pwd").css("display","block");
			}else{
				//������ȷ
				$("#cover #box #login_show  #test_login_pwd").css("display","none");
			}
		
		})
	});
	//Ajax����ע��ʱ�û����Ƿ����
	var r_username = $("#cover #box #register_show #r_uname");
	r_username.blur(function(){
		var value = $(this).val();
		$.post("/StxCp/test_registerUsername","register_username="+value,function(data){
		if(data == 0){
			//�û��������ڣ�����ע��
			$("#cover #box #register_show #test_register_username").css("display","none");
		}else{
			//�û������ڣ�������ע��
			$("#cover #box #register_show #test_register_username").css("display","block");
		}
		
	});
		
	})
	
})