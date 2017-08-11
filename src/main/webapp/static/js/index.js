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
	$scope.appendComment = function(commodityID) {
		if ($scope.comment) {
			ajaxModule.addComment(commodityID, $scope.comment, function() {
				$scope.comments.push({
					userName : "${name}",
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
	addComment : function(commodityID, comment, cb) {
		$.post("./addComment.html", {
			userId : '${id}',
			userName : '${name}',
			commodityID : commodityID,
			comment : comment
		}, function(res) {
			if (res) {
				cb && cb();
			} else {
				alert("add Comment failed");
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