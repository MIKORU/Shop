/**
 * 
 */

var app = angular.module("app", []);
app.controller("groups", function($scope) {
	$scope.groups = {};
	$scope.addToCart = function(userId,comId) {
		ajaxModule.addOrder(userId, comId);
	};
	$scope.showDetail = function(com) {
		$("#detail").modal('show');
		$("#detail").scope().com = com;
		ajaxModule.getCommentById(com.id, function(res) {
			$("#detail").scope().comments = JSON.parse(res);
			$("#detail").scope().$apply();
		});

	};
});

app.controller("detail", function($scope) {
	$scope.coments = {};
	$scope.appendComment = function(id,names,commodityID) {
		if ($scope.comment) {
			ajaxModule.addComment(id,names,commodityID, $scope.comment, function() {
				$scope.comments.push({
					username : names,
					comment : $scope.comment
				});
				$scope.$apply();
			});
		}
	}
});
function updateIndex() {
	ajaxModule.getAllCom(function(res) {
		var result = util.groupByType(res);
		$("#groups").scope().groups = result;
		$("#groups").scope().$apply();
	});
}
function bind() {
	$("#search").on("click",function() {
		ajaxModule.search($("#keyword").val(), function(res) {
			var result = util.groupByType(res);
			$("#groups").scope().groups = result;
			$("#groups").scope().$apply();
		});
	});
}
var util = {
	groupByType : function(res) {
		var jsons = JSON.parse(res);
		var obj = {};
		for (var i = 0; i < jsons.length; i++) {
			obj[jsons[i].type] = obj[jsons[i].type] || [];
			obj[jsons[i].type].push(jsons[i]);
		}
		return obj;
	}
}
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
				alert("添加成功");
			} else {
				alert("添加失败QAQ");
			}
		});
	},
	search : function(keyword, cb) {
		$.post("./search.html", {
			keyword : keyword
		}, cb);
	},
	getCommentById : function(id, cb) {
		$.post("./getCommentById.html", {
			commodityId : id
		}, cb);
	},
	addComment : function(id,names,commodityID, comment, cb) {
		$.post("./addComment.html", {
			userId : id,
			userName : names,
			commodityID : commodityID,
			comment : comment
		}, function(res) {
			if (res == "true") {
				cb && cb();
			} else {
				alert("添加评论失败");
			}
		});
	}
}
$(function() {
	updateIndex();
	bind();
});