/**
 * 
 */



var app = angular.module("app", []);
app.controller("index", function ($scope) {
	$scope.coms = [];
	
	ajaxModule.getAllCom(function(res) {
        // 数据源
        $scope.datas = JSON.parse(res);
        
        // 分页总数
        $scope.pageSize = 4;
        $scope.pages = Math.ceil($scope.datas.length / $scope.pageSize); // 分页数
        $scope.newPages = $scope.pages > 4 ? 4 : $scope.pages;
        $scope.pageList = [];
        $scope.selPage = 1;
        // 设置表格数据源(分页)
        $scope.setData = function () {
            $scope.coms = $scope.datas.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));// 通过当前页数筛选出表格当前显示数据
        }
        $scope.coms = $scope.datas.slice(0, $scope.pageSize);
        // 分页要repeat的数组
        for (var i = 0; i < $scope.newPages; i++) {
            $scope.pageList.push(i + 1);
        }
        // 打印当前选中页索引
        $scope.selectPage = function (page) {
            // 不能小于1大于最大
            if (page < 1 || page > $scope.pages) return;
            // 最多显示分页数5
            if (page > 2) {
                // 因为只显示5个页数，大于2页开始分页转换
                var newpageList = [];
                for (var i = (page - 2) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
                    newpageList.push(i + 1);
                }
                $scope.pageList = newpageList;
            }
            $scope.selPage = page;
            $scope.setData();
            $scope.isActivePage(page);
            console.log("选择的页：" + page);
        };
        // 设置当前选中页样式
        $scope.isActivePage = function (page) {
            return $scope.selPage == page;
        };
        // 上一页
        $scope.Previous = function () {
            $scope.selectPage($scope.selPage - 1);
        }
        // 下一页
        $scope.Next = function () {
            $scope.selectPage($scope.selPage + 1);
        };
        $scope.$apply();
	});
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
	getAllCom : function(cb) {
		$.post("./getAllCom", cb);
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
