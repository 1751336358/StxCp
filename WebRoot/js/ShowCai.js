$(function(){
	
	//ɾ������
	var dels = $(".delSay");
//	alert(dels[0].href);
	//��ֹ�����Ĭ����Ϊ
	dels.click(function(event){
		
		event.preventDefault();  //��ֹĬ����Ϊ 
		var url = $(this).attr("href");	//Ҫɾ�������۵�url��"/StxCp/delSayBysayid?sayid=48"��
//		alert(url);
		var id = '';
		var t = 0;
		//�ַ���ƴ�ӵõ�url����id������dom�ڵ����
		$.each(url,function(i,n){
			if(n=='=' || t==1){
				if(t == 1){
					id += n;
				}			
				t = 1;
			}
		})
	//	alert(id);
		
		//����Ajaxɾ�����ۣ������ݿ�ɾ������
		$.get(url,true,function(data){
			//ɾ��ǰ̨dom�ڵ�
			if(data == "ok"){
				$('#'+id).remove();
				alert("delete successful");
			}
		})
	})
}) 