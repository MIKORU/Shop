/**
 * 
 */
"use strict";
$(function(){
	login();
});
function login(){
	$("#submit").on("click",function() {
		$.ajax({
			url:"./checkin",
			type:"POST",
			data:{
				username : $("#user").val(),
				password : $("#password").val()
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
	$("#register").on("click",function(){
		$("#reglabel").modal("show");
//		$.ajax({
//			url:"/reg",
//			type:"POST",
//			data:{
//				
//			}
//		})
	});
}
