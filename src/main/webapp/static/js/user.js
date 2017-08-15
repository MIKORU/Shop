/**
 * 
 */
var userId = 0;
var userName = null;
var app = angular.module("app", []);

app.controller("user", function($scope) {
	$scope.user = {
		name : "name",
		password : "passowrd",
		address : "address",
		phone : "phone",
		mail : "mail"
	}
});

$("#submit").click(function(ev) {
	$.post("./updateInfo.html", $.extend({}, $("#user").scope().user, {
		userId : userId
	}), function(res) {
		if (res == "true") {
			alert("更新成功");
			location.reload();
		} else {
			alert("更新失败");
		}
	});
});
function init() {
	$.ajax({
		url : "./getcontext.html",
		type : "POST",
		data : {},
		async:false
	}).done(function(res) {
		res = JSON.parse(res);
		userId = res[0].id;
		userName = res[0].name;
	});

}
$(function() {
	init();
	if (userId) {
		$.post("./getInfo.html", {
			userId : userId
		}, function(res) {
			var scope = $("#user").scope();
			scope.user = JSON.parse(res);
			$("#user").scope().$apply();
		});
	}
});