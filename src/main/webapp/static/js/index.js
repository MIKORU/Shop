/**
 * 
 */
var userId = "${id}";
var app = angular.module("app", []);

app.controller("index", function($scope) {
	$scope.coms = [];
	$scope.addToCart = function(comId) {
		ajaxModule.addOrder(userId, comId);
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
	$scope.appendComment = function(id,names,commodityID) {
		if ($scope.comment) {
			ajaxModule.addComment(id,names,commodityID, $scope.comment, function() {
				console.log(names);
				$scope.comments.push({
					username : names , //无法同步更新
					comment : $scope.comment
				});
				$scope.$apply();
			});
		}
	}
});

var ajaxModule = {
	getAllCom : function(cb) {
		$.post("./getAllCom.html", cb);
	},
	addOrder : function(userId, commodityIds, cb) {
		$.post("./addOrder.html", {
			userId : userId,
			commodityIds : commodityIds,
			commodityCounts : "1"
		}, function(res) {
			console.log("addOrder.html response is " + res);
			if (res) {
				alert("successful");
			} else {
				alert("failed");
			}
		});
	},
	getCommentById : function(id, cb) {
		$.post("./getCommentById.html", {
			commodityId : id
		}, cb);
	},
	addComment : function(id, names , commodityID, comment, cb) {
		$.post("./addComment.html", {
			userId : id,
			userName : names,
			commodityID : commodityID,
			comment : comment
		}, function(res) {
			if (res =="true" ) {
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