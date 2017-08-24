/**
 * 
 */
var app = angular.module("app", []);
app.controller("index", function ($scope) {
	$scope.coms = [];
	
	var pageNo = 1;
	var pageSize = 4;
	$scope.selPage = pageNo;
	
	ajaxModule.getAllCom(pageNo,pageSize);
	
	$scope.selectPage = function (page) {
		$scope.selPage = page;
        $scope.isActivePage(page);
        ajaxModule.getAllCom(page,pageSize);
        console.log("选择的页：" + page);
    };
	
	$scope.isActivePage = function (page) {
        return $scope.selPage == page;
    };
	$scope.Previous = function () {
		if($scope.selPage <= 1){
			$scope.selectPage(1);
		}
		else{
			$scope.selectPage($scope.selPage - 1);
		}
    }
    $scope.Next = function () {
    	if($scope.selPage >= $scope.pageList.length){
			$scope.selectPage($scope.pageList.length);
		}
		else{
			$scope.selectPage($scope.selPage + 1);
		}
    };
    
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
})

// app.controller("index", function($scope) {
// $scope.coms = [];
// $scope.addToCart = function(comId) {
// ajaxModule.addOrder(comId);
// }
// $scope.showDetail = function(com) {
// $("#detail").modal('show');
// $("#detail").scope().com = com;
// ajaxModule.getCommentById(com.id, function(res) {
// $("#detail").scope().comments = JSON.parse(res);
// $("#detail").scope().$apply();
//			
// });
// }
// });
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
	getAllCom : function(pageNo,pageSize) {
		$.post("./getAllComByPage",{
			pageNo:pageNo,
			pageSize:pageSize
		}, function(res) {
			var json = JSON.parse(res);
			$(".pagination").scope().pageList = json[0].navigatepageNums;
			$(".pagination").scope().coms = json[0].list;
			$(".pagination").scope().$apply();
		});
	},
	addOrder : function(commodityIds, cb) {
		$.post("./addOrder", {
			commodityIds : commodityIds,
			commodityCounts : "1"
		}, function(res) {
			console.log("addOrder response is " + res);
			if (res === "true") {
				alert("添加商品成功！");
			} else {
				alert("添加商品失败QAQ");
			}
		});
	},
	getCommentById : function(id, cb) {
		$.post("./getCommentById", {
			commodityId : id
		}, cb);
	},
	addComment : function(commodityID, comment, cb) {
		$.post("./addComment", {
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
// function index() {
// ajaxModule.getAllCom(function(res) {
// $("#index").scope().coms = JSON.parse(res); // !!
// $("#index").scope().$apply();
// });
// }
//
// $(function() {
// index();
// });
