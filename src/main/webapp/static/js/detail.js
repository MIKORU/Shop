/**
 * 
 */

var app = angular.module("app", []);
app.controller("groups", function($scope) {
	$scope.groups = {};
	$scope.addToCart = function(comId) {
		ajaxModule.addOrder(comId);
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
	$scope.appendComment = function(names,commodityID) {
		if ($scope.comment) {
			ajaxModule.addComment(names,commodityID, $scope.comment, function() {
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
			if(res){
				var result = util.groupByType(res);
				$("#groups").scope().groups = result;
				$("#groups").scope().$apply();
			}
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
		$.post("./getAllCom", cb);
	},
	addOrder : function(commodityIds, cb) {
		$.post("./addOrder", {
			commodityIds : commodityIds,
			commodityCounts : "1"
		}, function(res) {
			console.log("addOrder response is " + res);
			if (res == "true") {
				alert("添加成功");
			} else {
				alert("添加失败QAQ");
			}
		});
	},
	search : function(keyword, cb) {
		$.post("./search", {
			keyword : keyword
		}, cb);
	},
	getCommentById : function(id, cb) {
		$.post("./getCommentById", {
			commodityId : id
		}, cb);
	},
	addComment : function(names,commodityID, comment, cb) {
		$.post("./addComment", {
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