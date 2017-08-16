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

app.controller("orderform", function($scope) {

	$scope.orderforms = [];
	$scope.commoditys = [];
	$scope.showInfo = function(info) {
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
		$("#myLabel").modal('show');
//		$("#myLabel").scope().commodity
	}
});
app.controller("select", function($scope) {
	$scope.types = [];
})

$("#tab2").click(function() {
	ajaxModule.getAllCom("#pro");
	ajaxModule.getTypes("#select");
});

$("#submit").click(function() {
	ajaxModule.addPro({
		name : $("#name").val(),
		depict : $("#depict").val(),
		price : $("#price").val(),
		amount : $("#amount").val(),
		manufacturer : $("#manufacturer").val(),
		img : $("#img").val(),
		type : $("#select").val()
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
	ajaxModule.editPro({
		name : $("#name").val(),
		depict : $("#depict").val(),
		price : $("#price").val(),
		amount : $("#amount").val(),
		manufacturer : $("#manufacturer").val(),
		img : $("#img").val(),
		type : $("#select").val()
	}, function(res) {
		if (res == "true") {
			$('#myModal').modal('hide');
			window.location.reload();
			$("#tab2").click();
		} else {
			alert("修改失败");
			$('#myModal').modal('hide')
		}
	});
});

app.controller("comments", function($scope) {
	$scope.comments = [];
});
$("#tab3").click(function() {
	ajaxModule.getComments(function(res) {
		$(".comments").scope().comments = JSON.parse(res);
		$(".comments").scope().$apply();
	})
});

$("#upload").change(function(ev) {
	formData = new FormData();
	formData.append("name", ev.target.files[0]);
	var oReq = new XMLHttpRequest();
	oReq.open("POST", "./upload.html");
	oReq.onreadystatechange = function() {
		if (oReq.readyState === 4) {
			$("#img").val(oReq.responseText);
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
			$.post("./getFormAllList.html", function(res) {
				$(el).scope().orderforms = JSON.parse(res);
				$(el).scope().$apply();
			})
		},
		getTypes : function(el) {
			$.post("./getTypes.html", function(res) {
				$(el).scope().types = JSON.parse(res);
				$(el).scope().$apply();
			})
		},
		addType : function(type, callback) {
			$.post("./addType.html", {
				type : type
			}, function(res) {
				callback(res);
			});
		},
		delType : function(id, callback) {
			$.post("./delType.html", {
				id : id
			}, function(res) {
				callback(res);
			});
		},
		getAllCom : function(el) {
			$.post("./getAllCom.html", function(res) {
				$(el).scope().coms = JSON.parse(res);
				$(el).scope().$apply();
			});
		},
		delPro : function(id, callback) {
			$.post("./delPro.html", {
				id : id
			}, function(res) {
				callback(res);
			});

		},
		addPro : function(json, callback) {
			$.post("./addPro.html", json, function(res) {
				callback(res);
			});
		},
		getComments : function(callback) {
			$.post("./getComments.html", function(res) {
				callback(res);
			});
		},
		getCommentById : function(id, callback) {
			$.post("./getCommentById.html", {
				commodityId : id
			}, callback);
		},
		editPro : function(json,callback){
			$.post("./editPro.html", json, callback);
		}
	};
}();