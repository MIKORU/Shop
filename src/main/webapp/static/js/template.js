/**
 * 
 */
$(function(){
	$("#login").on("click",function(){
		$("#loginlabel").modal('show');
		$("#submits").on("click",function() {
			$.ajax({
				url:"./checkin",
				type:"POST",
				data:{
					username : $("#tuser").val(),
					password : $("#tpassword").val()
				},
				contentType : "application/x-www-form-urlencoded"//!!!!
				
			}).done(function(res){
				if(res=="true"){
					location.href="index"
				}else{
					alert("登陆失败");
				}
			});
		});
	});
	$("#logouts").on("click",function(){
		$.ajax({
			url:"./logout",
			type:"POST",
			data:{}
		}).done(function(res){
			if(res=="true"){
				alert("已经登出");
				window.location.reload();
			}else{
				alert("登出失败");
			}
		});
	});
});