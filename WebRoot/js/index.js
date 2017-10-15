$(function(){

	//每个菜系推荐菜谱
	var spans = $(".dept");
	spans.mouseover(function(){
		
		var deptid=$(this).attr("id");
		var str = "deptid="+deptid;
		//启动一个Ajax,根据deptid查询数据
		$.post("/StxCp/getTuijianCai",str,function(data){	
		/*	alert("aaa");	*/	
			eval("var a ="+data);
			var tuijian = $("#tuijian");
			//先清除tuijianlist
			tuijian.empty();
			for(var i = 0;i<a.length;i++){
			/*	var img = "<div class='tuijianlist'><img class='tuijianimg' src='/upload/"+a[i].imgpath+"' /></div><br/>";
				var name = "<p class='tuijianname'><a href='/StxCp/showCai?id="+a[i].id+"' target='_blank'>"+a[i].name+"</a></p>";
				var time = "<p class='tuijiantime'>"+a[i].time+"</p>";
				var readnum = "<p class='tuijianreadnum'>点击量:"+a[i].readnum+"</p>";*/
				
				
				var img = "<div class='tuijianlist'><img class='tuijianimg' src='/upload/"+a[i].imgpath+"' />"
				+"<p class='tuijianname'><a href='/StxCp/showCai?id="+a[i].id+"' target='_blank'>"+a[i].name+"</a></p>"
				+"<p class='tuijiantime'>"+a[i].time+"</p>"
				+"<p class='tuijianreadnum'>"+a[i].readnum+"</p></div><br/>";
				tuijian.append(img);
			
			}
			
		});
	})
})
