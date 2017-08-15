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
				id : $iattrs.id
			}, function(res) {
				$scope.res = JSON.parse(res)[0];
				$scope.$apply();
			});
		}
	}
});
app.controller("cart", function($scope) {
	var list = [];// [{"id":1,"userId":19,"commodityId":1,"commodityCount":1},{"id":2,"userId":19,"commodityId":2,"commodityCount":5},{"id":3,"userId":19,"commodityId":3,"commodityCount":2},{"id":4,"userId":19,"commodityId":5,"commodityCount":4},{"id":5,"userId":19,"commodityId":4,"commodityCount":3}]
	$scope.list = list;
	
	$scope.scope = 0;

	$scope.comCount = function(constant, commodityId) {
		console.log($scope.list);
		$.each($scope.list, function(i, e) {
			if (e.commodityid == commodityId) {
				$scope.list[i].commoditycount += constant;
			}
			if (e.commoditycount <= 0) {
				e.commoditycount = 0;
			}
		});
		$scope.upDateSum();
	};
	$scope.upDateSum = function() {
		$scope.sum = util.calcSum($scope.list);
	};
});
var util = {
	calcSum : function(list) {
		var sum = 0;
		$.each(list, function(i, e) {
			if(e.price){
				sum += (e.commoditycount * e.price);
			}
		});
		return sum;
	}
};
function init() {
	$.ajax({
		url : "./getcontext.html",
		type : "POST",
		data : {},
		async : false
	}).done(function(res) {
		res = JSON.parse(res);
		userId = res[0].id;
		userName = res[0].name;
	});

}
function getlist() {
	if (userId) {
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
			userId : $("#userId").val(),
			address : $("#address").val(),
			phone : $("#phone").val(),
			totalPrice : $("#cart").scope().sum,
			orderList : JSON.stringify($("#cart").scope().list)
		}, function(res) {
			if (res == "true") {
				location.href = "./list.html";
			} else {
				alert("购买失败");
			}
		});
//		$.each($("#cart").scope().list,function(i,e){
//			$.post("./updateCart.html",{
//				
//			},function(res){
//				
//			});
//		});
	});
};

$(function() {
	init();
	getlist();
});