/**
 * 
 */
$(function(){
	$("#login").on("click",function(){
		$("#loginlabel").modal('show');
// $("#reglabel").modal('show');
		$("#submits").on("click",function() {
			$.ajax({
				url:"./checkin",
				type:"POST",
				data:{
					username : $("#tuser").val(),
					password : $("#tpassword").val()
				},
				contentType : "application/x-www-form-urlencoded"// !!!!
				
			}).done(function(res){
				if(res==true){
					location.href="index";
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
			if(res==true){
				alert("已经登出");
				window.location.reload();
			}else{
				alert("登出失败");
			}
		});
	});
	$("#register").on("click",function(){
		$("#loginlabel").modal('hide');
		$("#reglabel").modal('show');
		$("#toregister").on("click",function(){
			$.ajax({
			url:"./reg",
			type:"POST",
			data:{
				name:$("#rname").val(),
				password:$("#rpassword").val(),
				defaultAddress:$("#raddress").val(),
				defaultPhone:$("#rphone").val(),
				mail:$("#rmail").val()
			}
		}).done(function(res){
			if(res==true){
				$("#reglabel").modal('hide');
				alert("注册成功！")
				$("#loginlabel").modal('show');
			}else{
				alert("注册失败");
				$("#reglabel").modal('hide');
			}
		});
		});
	});
});