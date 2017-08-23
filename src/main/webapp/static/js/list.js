/**
 * 
 */
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
		ajaxModule.pay(orderId, function(res) {
			if (res == "true") {
				alert("支付成功");
				location.reload();
			} else {
				alert("支付失败");
			}
		});
	}
	$scope.del = function(orderId){
		ajaxModule.delOrder(orderId,function(res){
			if(res == "true"){
				alert("取消订单成功");
				window.location.reload();
			}else{
				alert("取消订单失败");
			}
		});
	}
});
var ajaxModule = {
	getFormList : function(cb) {
		$.post("./getFormList", {}, cb);
	},
	pay : function(orderId, cb) {
		$.post("./pay", {
			orderId : orderId
		}, cb);
	},
	getComById : function(id, cb) {
		$.post("./getComById", {
			id : id
		}, cb);
	},
	delOrder:function(id,cb){
		$.post("./delOrder",{
			userId:id
		}, cb);
	}
}
function getformlist() {
	ajaxModule.getFormList(function(res) {
		$("#form").scope().items = JSON.parse(res);
		$("#form").scope().$apply();
	});
}
$(function() {
	getformlist();
});