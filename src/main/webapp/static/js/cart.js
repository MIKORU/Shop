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
		$.each($scope.list, function(i, e) {
			if (e.commodityid == commodityId) {
				if (e.commoditycount <= 0) {
					e.commoditycount = 0;
					util.delCart(e.commodityid);
					
				}else{
					util.changeCart(e.commodityid,constant,function(res) {
						console.log("addOrder.html response is " + res);
						if(res == "true"){
							$scope.list[i].commoditycount += constant;
						}else{
							alert("添加失败，商品数量不足！");
						}
					});
				}
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
			if (e.price) {
				sum += (e.commoditycount * e.price);
			}
		});
		return sum;
	},
	changeCart : function(commodityIds,count,cb) {
			$.post("./addOrder.html", {
				commodityIds : commodityIds,
				commodityCounts : count
			}, cb);
	},
	delCart : function(commodityIds,cb){
		console.log("sdaaaaaa");
		$.ajax({
			url:"./delCart.html", 
			type:"POST",
			data:{
				commodityIds : commodityIds
			}
		}).done(function(res){
			console.log("delOrder.html response is " + res);
			if(res == "true"){
				window.location.reload();
			}else{
				alert("出错了！");
			}
		});
	}
};
function getlist() {

	$.post("./getOrderList.html", {

	}, function(res) {
		$("#cart").scope().list = JSON.parse(res);
		setInterval(function() {
			$("#cart").scope().upDateSum();
			$("#cart").scope().$apply();
		}, 1000);
	});
	$("#submit").on("click", function() {
		$.post("./addForm.html", {
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

	});
};

$(function() {
	getlist();
});