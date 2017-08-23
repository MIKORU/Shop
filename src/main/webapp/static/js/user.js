/**
 * 
 */
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

$("#submit").click(
		function(ev) {
			$.post("./updateInfo", $.extend({}, $("#user").scope().user, {}),
					function(res) {
						if (res == "true") {
							alert("更新成功");
							window.location.reload();
						} else {
							alert("更新失败");
						}
					});
		});
$(function() {
	$.post("./getInfo", {}, function(res) {
		$("#user").scope().user = JSON.parse(res);
		$("#user").scope().$apply();
	});

});