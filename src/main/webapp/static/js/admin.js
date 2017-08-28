/**
 * 
 */
var app = angular.module("app", []);
app.directive("commodityDirective", function() {
	return {
		restrict : "EA",
		scope : true,
		link : function($scope, $el, $iattrs) {
			$.post("./getComById", {
				id : $iattrs.id
			}, function(res) {
				$scope.res = JSON.parse(res)[0];
				$scope.$apply();
			});
		}
	}
});

app.controller("orderform", function($scope) {

	$scope.orderforms = [];
	$scope.commoditys = [];
	$scope.showInfo = function(info,index) {
		$scope.indexs = index;
		$scope.edit = !$scope.edit;
		if($scope.edit == false){
			$scope.indexs = $scope.indexs+1;
		}else{
			$scope.indexs = $scope.indexs-1;
		}
//		console.log($scope.indexs+" "+index+" "+$scope.edit);
		$scope.commoditys = JSON.parse(info);
	};

});

$("#tab0").click(function() {
	ajaxModule.getFormAllList(".orderform");
});

app.controller("types", function($scope) {
	$scope.types = [];
	$scope.new_type = "";
	$scope.delType = function(id) {
		ajaxModule.delType(id, function(res) {
			if (res === "true") {
				$("#tab1").click();
			} else {
				alert("删除失败");
			}
			;
		});
	};
	$scope.new_type_fn = function() {
		ajaxModule.addType($scope.new_type, function(res) {
			if (res === "true") {
				$("#tab1").click();
			} else {
				alert("创建失败");
			}
		});
	};
});
$("#tab1").click(function() {
	ajaxModule.getTypes(".types");
});

app.controller("pros", function($scope) {
	$scope.coms = [];
	$scope.removePro = function(id) {
		ajaxModule.delPro(id, function(res) {
			if (res == "true") {
				$("#tab2").click();
			} else {
				alert("删除失败");
			}
		});
	};
	$scope.editPro = function(id){
		$("#editLabel").modal('show');
		$.post("./getComById", {
			id : id
		}, function(res) {
			$scope.edit_commodity = JSON.parse(res)[0];
			$scope.$apply();
		});
	}
	$scope.addPro = function(){
		$("#myModal").modal('show');
	}
});

app.controller("select", function($scope) {
	$scope.types = [];
})

$("#tab2").click(function() {
	ajaxModule.getAllCom("#pro");
	ajaxModule.getTypes("#select");
	ajaxModule.getTypes("#selects");
});

$("#submit").click(function() {
	ajaxModule.addPro({
		name : $("#com_name").val(),
		depict : $("#com_depict").val(),
		price : $("#com_price").val(),
		amount : $("#com_amount").val(),
		manufacturer : $("#com_manufacturer").val(),
		img : $("#com_img").val(),
		type : $("#selects").val()
	}, function(res) {
		if (res == "true") {
			$('#myModal').modal('hide');
			window.location.reload();
			$("#tab2").click();
		} else {
			alert("添加失败");
			$('#myModal').modal('hide')
		}
	});
});
$("#edit").click(function() {
	console.log($("#select").val());
	ajaxModule.editPro({
		commodityId: $("#comid").val(),
		name : $("#name").val(),
		depict : $("#depict").val(),
		price : $("#price").val(),
		amount : $("#amount").val(),
		manufacturer : $("#manufacturer").val(),
		img : $("#img").val(),
		type : $("#select").val()
	}, function(res) {
		if (res == "true") {
			$('#editLabel').modal('hide');
			window.location.reload();
			$("#tab2").click();
		} else {
			alert("修改失败");
			$('#editLabel').modal('hide')
		}
	});
});

app.controller("comments", function($scope) {
	$scope.comments = [];
});
$("#tab3").click(function() {
	ajaxModule.getComments(function(res) {
		$scope = $(".comments").scope();
		$scope.datas = JSON.parse(res);
        
        // 分页总数
        $scope.pageSize = 5;
        $scope.pages = Math.ceil($scope.datas.length / $scope.pageSize); // 分页数
        $scope.newPages = $scope.pages > 5 ? 5 : $scope.pages;
        $scope.pageList = [];
        $scope.selPage = 1;
        // 设置表格数据源(分页)
        $scope.setData = function () {
        	$scope.comments = $scope.datas.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));// 通过当前页数筛选出表格当前显示数据
        }
        $scope.comments = $scope.datas.slice(0, $scope.pageSize);
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
                for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
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
	})
});

$("#upload").change(function(ev) {
	formData = new FormData();
	formData.append("name", ev.target.files[0]);
	var oReq = new XMLHttpRequest();
	oReq.open("POST", "./upload");
	oReq.onreadystatechange = function() {
		if (oReq.readyState === 4) {
			$("#img").val(oReq.responseText);
		}
	};
	console.log(ev.target.files[0]);
	oReq.send(formData);
});
$("#uploads").change(function(ev) {
	formData = new FormData();
	formData.append("com_name", ev.target.files[0]);
	var oReq = new XMLHttpRequest();
	oReq.open("POST", "./upload");
	oReq.onreadystatechange = function() {
		if (oReq.readyState === 4) {
			$("#com_img").val(oReq.responseText);
		}
	};
	console.log(ev.target.files[0]);
	oReq.send(formData);
});
$(function() {
	$("#tab0").click();
});

var ajaxModule = function() {
	return {
		getFormAllList : function(el) {
			$.post("./getFormAllList", function(res) {
				$(el).scope().orderforms = JSON.parse(res);
				$(el).scope().$apply();
			})
		},
		getTypes : function(el) {
			$.post("./getTypes", function(res) {
				$(el).scope().types = JSON.parse(res);
				$(el).scope().$apply();
			})
		},
		addType : function(type, callback) {
			$.post("./addType", {
				type : type
			}, function(res) {
				callback(res);
			});
		},
		delType : function(id, callback) {
			$.post("./delType", {
				id : id
			}, function(res) {
				callback(res);
			});
		},
		getAllCom : function(el) {
			$.post("./getAllCom", function(res) {
				$(el).scope().coms = JSON.parse(res);
				$(el).scope().$apply();
			});
		},
		delPro : function(id, callback) {
			$.post("./delPro", {
				id : id
			}, function(res) {
				callback(res);
			});

		},
		addPro : function(json, callback) {
			$.post("./addPro", json, function(res) {
				callback(res);
			});
		},
		getComments : function(callback) {
			$.post("./getComments", function(res) {
				callback(res);
			});
		},
		getCommentById : function(id, callback) {
			$.post("./getCommentById", {
				commodityId : id
			}, callback);
		},
		editPro : function(json,callback){
			$.post("./editPro", json, callback);
		}
	};
}();