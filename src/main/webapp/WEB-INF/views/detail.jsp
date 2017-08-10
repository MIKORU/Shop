<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = 
request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href ="./bootstrap/css/bootstrap.min.css" />
	<script src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="http://cdn.bootcss.com/angular.js/1.3.15/angular.min.js"></script>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<style type="text/css">
	.commodity{
		margin:10px;
	}
	.content{
		margin:80px;
	}
	.commentBody{
		max-heiht:200px;
	}
</style>
</head>
<body ng-app="app">
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="row">
			<ul class="nav navbar-nav">
				<li>
					<c:if test="${name!=null}">
						<a href="###">
							欢迎${name}
						</a>
					</c:if>
				</li>
				<li>
					<a href="./index.html">首页</a>
					</li>
				<li>
					<a href="./detail.html">分类页</a>
					</li>
				<li>
					<a href="./cart.html">购物车</a>
					</li>
				<li>
					<a href="./list.html">列表页</a>
					</li>
				<li><a href="./user.html">用户</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container content">
		<div class="row">
			<p>
				<span class="glyphicon glyphicon-cd aria-hidden="true"></span>
				<input class="form-control" id="keyword" placeholder="输入关键词"><br>
				<button id="search" class="btn btn-primary">搜索</button>
			</p>
		</div>
		<div id="groups" class="row" ng-controller="groups">
			<div ng-repeat="coms in groups">
				<h3>
					<span class="glyphicon glyphion-fire" aria-hidden="true"></span>
					<span class="label label-default">
						{{coms[0].type}}
					</span>
				</h3>
				<div class="panel panel-default pull-left commodity" ng-repeat="com in coms">
                    <div class="panel-heading panel-primary">
                        <p>{{com.name}}</p>
                    </div>
					<div class="panel-body">
						<p>产品介绍{{com.depict}}</p>
						<p>厂商{{com.manufacturer}}</p>
						<p>金额{{com.price}}</p>
						<p>产品图片<img ng-src={{com.img}} width=50 height=50 /></p>
						<button class="btn btn-default" ng-click="addToCart(com.id)">添加到购物车</button>
						<button class="btn btn-default" ng-click="showDdetail(com)">查看详细</button>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="row">
			<a href="./car.html" class="btn btn-default" role="button">结账</a>
		</div>
	</div>
	<!-- Modal --start -->
	<div class="modal fade" id="detail" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" ng-controller="detail">
		<div class="modal-dialog" role="document">
			<div clss="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					<h4 class="model-title" id="myModalLabel">
						产品信息						
					</h4>
				</div>
				<div class="modal-body">
					<p>产品名称{{com.name}}</p>
					<p>产品描述{{com.depict}}</p>
					<p>产品公司{{com.manufacturer}}</p>
					<p>产品金额{{com.price}}</p>
					<p>产品略缩图<img ng-src={{com.img}} width=50 height=50 /></p>
					<div class="commentBody">
						<div ng-repeat="c in comments">
							<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
							{{c.userName}}
							<div class="alert" role="alert">
								{{c.comment}}
							</div>
						</div>
						<form>
							<label for="text"></label>
							<input type="text" name="text" id="text" placehoder="评论内容" ng-model="comment"/>
							<button id="submit" class="btn btn-success" ng-click="appendComment(com.id)">评论</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var userId="${id}";
	var app = angular.module("app",[]);
	app.controller("groups",function($scope){
		$scope.groups={};
		$scope.addToCart = function(comId){
			ajaxModule.addOrder(userId,comId);
		};
		$scope.showDetail = function(com){
			$("#detail").modal('show');
			$("#detail").scope().com = com;
			ajaModule.getCommentById(com.id,function(res){
				$("#detail").scope().comments=res;
				$("#detail").scope().$apply();
			});
			
		};
	});
	
	app.controller("detail",function($scope){
		$scope.coments={};
		$scope.appendComment = function(commodityID){
			if($scope.comment){
				ajaxModule.addComment( commodityID, $scope.comment, function(){
					$scope.comment.push({
						userName:"${name}",
						comment:"$scope.comment"
					});
					$scope.$apply();
				});
			}	
		}
	});
	function updateIndex(){
		ajaxModule.getAllCom(function(res){
			var result = util.groupByType(res);
			$("#groups").scope().groups = result;
			$("#groups").scope().$apply();
		});
	}
	function bind(){
		$("#search").click(function(){
			ajaxModule.search($("#keyword").val(),function(res){
				var result = util.groupByType(res);
				$("#groups").scope().groups = result;
				$("#groups").scope().$apply();
			});
		})
	}
	var util = {
			groupByType:function(res){
				var obj = {};
				for(var i = 0;i<res.length;i++){
					obj[res[i].type] = object[res[i].type] || [];
					obj[res[i].type].push(res[i]);
				}
				return obj;
			}
	}
	var ajaxModule = {
			getAllCom:function(cb){
				$.post("admin/getAllCom.html",cb);
			},
			addOrder:function(userId,commodityIds,cb){
				$.post("addOrder.html",{userId:userId,commodityIds:commodityIds,
					commodityCounts:"1"},function(res){
						console.log("addOrder.html response is "+res);
						if(res){
							alert("successful");
						}else{
							alert("failed");
						}
					});
			},
			search:function(keyword,cb){
				$.post("search.html",{keyword:keyword},cb);
			},
			getCommentById:function(id,cb){
				$.post("/getCommentById.html",{commodityId:id},cb);
			},
			addComment:function(commodityID,comment,cb){
				$.post("./addComment.html",{userId:'${id}',userName:'${name}',
					commodityID:commodityID,comment:comment},function(res){
						if(res){
							cb&&cb();
						}else{
							alert("add Comment failed");
						}
					});
			}
	}
	$(function(){
		updateIndex();
		bind();
	});
</script>
</html>























