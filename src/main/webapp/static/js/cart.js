/**
 * 
 */
var app = angular.module("app", []);
app.directive("commodityDirective", function() {
	return {
		restrict : "EA",
		scope : true,
		link : function($scope, $el, $iattrs) {
			$.post("./getComById.html", {
				id : $iattrs.id
			}, function(res) {
				$scope.res = res[0];
				$scope.$apply();
			});
		}
	}
});
app.controller("cart", function($scope) {
	var list = [];
	$scope.list = list;
	$scope.scope = 0;
	$scope.comCount = function(constant, commodityId) {
		$.each($scope.list, function(i, e) {
			if (e.commodityId === commodityId) {
				$scope.list[i].commodityCount += constant;
			}
			if (e.commodityCount <= 0) {
				e.commodityCount = 0;
			}
		});
		$scope.upDateSum();
	}
	$scope.upDateSum = function() {
		$scope.sum = util.calcSum($scope.list);
	}
});
var util = {
	canlcSum : function(list) {
		var sum = 0;
		$.each(list, function(i, e) {
			sum += (e.commodityCount * e.price);
		});
		return sum;
	}
}
function init(){
	$.post("./getOrderList.html", {
		userId : userId
	}, function(res) {
		$("#cart").scope().list = JSON.prase(res);
		setInterval(function() {
			$("#cart").scope().upDateSum();
			$("#cart").scope().$apply();
		}, 1000);
	});
}
$("#submit").on("click",function() {
	$.post("./addForm.html", {
		userId : $("#userId").varl(),
		address : $("#address").val(),
		phone : $("#phone").val(),
		totalPrice : ("#cart").scope().sum,
		orderList : JSON.stringify($("#cart").scope().list)
	}, function(res) {
		if (res === true) {
			location.href = "list.html";
		} else {
			alert("Buy Failed");
		}
	});
});
$(function(){
	init();
});