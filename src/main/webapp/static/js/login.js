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
			url:"login.html",
			type:"POST",
			data:{
				username : $("#user").val(),
				password : $("#password").val()
			},
			contentType : "application/x-www-form-urlencoded"//!!!!
			
		}).done(function(res){
			if(res=="true"){
				location.href="index.html"
			}else{
				alert("登陆失败");
			}
		});
	});
}
