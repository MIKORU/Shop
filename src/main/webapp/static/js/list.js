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
		link : function($scope, $el, $attrs) {
			ajaxModule.getComById($iattrs.id, function(res) {
				$scope.res = res[0];
				$scope.$apply();
			});
		}
	}
});
app.directive("parse", function() {
	return {
		restrict : "EA",
		scope : true,
		link : function($scope, $el, $attrs) {
			$scope.orderlist = JSON.parse($iattrs.id);
		}
	}
});
app.controller("form", function($scope) {
	$scope.items = [];
	$scope.pay = function(orderformId) {
		ajaxModule.pay(userId, orderformId, function(res) {
			if (res == "true") {
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
	pay : function(userId, orderformId, cb) {
		$.post("./pay.html", {
			userId : userId,
			orderformId : orderformId
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
		$(function() {
			ajaxModule.getFormList(userId, function(res) {
				console.log(res);
				$("#form").scope().items = JSON.parse(res);
				$("#form").scope().$apply();
			});
		});
	}
}
$(function() {
	init();
	getformlist();
});