/**
 * 
 */
"use strict";
function login(){
	$("#submit").click(function() {
		$.post("login.do", {
			username : $("#user").val(),
			password : $("#password").val()
		}, function(res) {
			if (res === "true") {
				location.href = "index.do";
			} else {
				alert("登陆失败");
			}
		});
	});
}