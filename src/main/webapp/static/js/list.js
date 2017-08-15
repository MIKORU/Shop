/**
 * 
 */
var userId = 0;
var username = null

var app = angular.module("app", []);
app.directive("commodityDirective", function() {
	return {
		restrict : "EA",
		scope : true,
		link : function($scope, $el, $iattrs) {
			ajaxModule.getComById($iattrs.id, function(res) {
				$scope.res = JSON.parse(res)[0];
				$scope.$apply();
			});
		}
	}
});
app.directive("parse", function() {
	return {
		restrict : "EA",
		scope : true,
		link : function($scope, $el, $iattrs) {
			$scope.orderlist = JSON.parse($iattrs.id);
		}
	}
});
app.controller("form", function($scope) {
	$scope.items = [];
	$scope.pay = function(orderId) {
		ajaxModule.pay(userId, orderId, function(res) {
			if (res == "true") {
				alert("支付成功");
				location.reload();
			} else {
				alert("支付失败");
			}
		});
	}
});
var ajaxModule = {
	getFormList : function(userId, cb) {
		$.post("./getFormList.html", {
			userId : userId
		}, cb);
	},
	pay : function(userId, orderId, cb) {
		$.post("./pay.html", {
			userId : userId,
			orderId : orderId
		}, cb);
	},
	getComById : function(id, cb) {
		$.post("./getComById.html", {
			id : id
		}, cb);
	}
}
function init() {
	$.ajax({
		url : "./getcontext.html",
		type : "POST",
		data : {},
		async : false
	}).done(function(res) {
		res = JSON.parse(res);
		userId = res[0].id;
		username = res[0].name;
	});

}
function getformlist() {
	if (userId) {
		ajaxModule.getFormList(userId, function(res) {
			$("#form").scope().items = JSON.parse(res);
			$("#form").scope().$apply();
		});
	}
}
$(function() {
	init();
	getformlist();
});