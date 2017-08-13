/**
 * 
 */
var userId = 0;
var userName = null;
var app = angular.module("app", []);
app.directive("commodityDirective", function() {
	return {
		restrict : "EA",
		scope : true,
		link : function($scope, $el, $iattrs) {
			$.post("./getComById.html", {
				id : userId
			}, function(res) {
				console.log("aaaaa");
				$scope.res = JSON.pares(res[0]);
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
	calcSum : function(list) {
		var sum = 0;
		list = JSON.parse(list);
		$.each(list, function(i, e) {
			sum += (e.commodityCount * e.price);
		});
		return sum;
	}
}
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
function getlist() {
	if(userId){
		$.post("./getOrderList.html", {
			userId : userId
		}, function(res) {
			$("#cart").scope().list = JSON.parse(res);
			setInterval(function() {
				$("#cart").scope().upDateSum();
				$("#cart").scope().$apply();
			}, 1000);
		});
	}
	$("#submit").on("click", function() {
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
}

$(function() {
	init();
	getlist();
});