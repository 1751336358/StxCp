$(function(){
	
	//删除评论
	var dels = $(".delSay");
//	alert(dels[0].href);
	//阻止浏览器默认行为
	dels.click(function(event){
		
		event.preventDefault();  //阻止默认行为 
		var url = $(this).attr("href");	//要删除的评论的url（"/StxCp/delSayBysayid?sayid=48"）
//		alert(url);
		var id = '';
		var t = 0;
		//字符串拼接得到url最后的id，方便dom节点操作
		$.each(url,function(i,n){
			if(n=='=' || t==1){
				if(t == 1){
					id += n;
				}			
				t = 1;
			}
		})
	//	alert(id);
		
		//启动Ajax删除评论，从数据库删除评论
		$.get(url,true,function(data){
			//删除前台dom节点
			if(data == "ok"){
				$('#'+id).remove();
				alert("delete successful");
			}
		})
	})
}) 