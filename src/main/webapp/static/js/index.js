/**
 * 
 */
var app = angular.module("app", []);

app.controller("index", function($scope) {
	$scope.coms = [];
	$scope.addToCart = function(comId) {
		ajaxModule.addOrder(comId);
	}
	$scope.showDetail = function(com) {
		$("#detail").modal('show');
		$("#detail").scope().com = com;
		ajaxModule.getCommentById(com.id, function(res) {
			$("#detail").scope().comments = JSON.parse(res);
			$("#detail").scope().$apply();
			
		});
	}
});
app.controller("detail", function($scope) {
	$scope.comments = [];
	$scope.appendComment = function(names,commodityID) {
		if ($scope.comment) {
			ajaxModule.addComment(commodityID, $scope.comment,
					function() {
						$scope.comments.push({
							username : names,
							comment : $scope.comment
						});
						clear();
						$scope.$apply();
					});
		}
	}
});
function clear(){
	$("#text").val("");
}
var ajaxModule = {
	getAllCom : function(cb) {
		$.post("./getAllCom.html", cb);
	},
	addOrder : function(commodityIds, cb) {
		$.post("./addOrder.html", {
			commodityIds : commodityIds,
			commodityCounts : "1"
		}, function(res) {
			console.log("addOrder.html response is " + res);
			if (res === "true") {
				alert("添加商品成功！");
			} else {
				alert("添加商品失败QAQ");
			}
		});
	},
	getCommentById : function(id, cb) {
		$.post("./getCommentById.html", {
			commodityId : id
		}, cb);
	},
	addComment : function(commodityID, comment, cb) {
		$.post("./addComment.html", {
			commodityID : commodityID,
			comment : comment
		}, function(res) {
			if (res == "true") {
				cb && cb();
			} else {
				alert("评论添加失败");
			}
		});
	}
}
function index() {
	ajaxModule.getAllCom(function(res) {
		$("#index").scope().coms = JSON.parse(res); // !!
		$("#index").scope().$apply();
	});
}

$(function() {
	index();
});