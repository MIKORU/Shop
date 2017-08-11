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
<title>主页</title>
<!-- 	<script src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script> -->
<!-- 	<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script> -->

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<style>
	.com{
		margin:20px;
		height:240px;
		overflow:hidden;
	}
	.padding20{
		padding:20px;
	}
	#index{
		margin-top:100px;
	}
</style>
<body ng-app="app">
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="row">
				<ul class="nav navbar-nav">
					<li>
						<c:if test="${name!=null }">
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
						<a href="./list.html">订单页</a>
					</li>
					<li>
						<a href="./user.html">用户信息</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div clss="container">
		<div id="index" class="row" ng-controller="index">
			<div class="panel panel-default">
				<div clss="panel-body">
					<div class="thumbnail pull-left com" ng-repeat="com in coms track by $index">
						<img ng-src="{{com.img}}" width="40" height="40">
						<div class="caption">
							<h3>{{com.name}}</h3>
							<p>{{com.depict}}</p>
							<p>
								<a href="#" class="btn btn-primary" role="button" 
									ng-click="addToCart(com.id)">添加商品</a>
							</p>
							<p>
								<button class="btn btn-default" ng-click="showDetail(com)">
									查看详细
								</button>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
			<div class="row">
				<a href="./cart.html" class="btn btn-default" role="button">购物车</a>
			</div>
	</div>
	<div clss="modal fade" id="detail" tabindex="-1" role="dialog" 
		aria-labelledby="myModalLabel" ng-controller="detail">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">
							&times;
						</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						产品信息
					</h4>
				</div>
				<div class="modal-body">
					<p>产品名字{{com.name}}</p>
					<p>产品描述{{com.depict}}</p>
					<p>产品公司{{com.manufacturer}}</p>
					<p>产品金额{{com.price}}</p>
					<p>产品缩略图<img ng-src={{com.img}} width=50 height=50 /></p>
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
							<input type="text" name="text" id="text" placehoder="评论内容"
								ng-model="comment" />
								<button id="submit" class="btn btn-success"
									ng-click="appendComment(com.id)">评论</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var userId="${id}";
		var app = angular.module("app",[]);
		
		app.controller("index",function($scope){
			$scope.coms=[];
			$scope.addToCart = function(comId){
				ajaxModule.addOrder(userId,comId);
			}
			$scope.showDetail = function(com){
				$("#detail").modal('show');
				$("#detail").scope().com = com;
				ajaxModule.getCommentById(com.id,function(res){
					$("#detail").scope().comments = res;
					$("#detail").scope().$apply();
				});
			}
			$scope.$watch("coms",function(){  
			      console.log($scope.coms);
			});
			$ajaxModule.getAllCom(function(res){
				$("#index").scope().coms = res;
  				$("#index").scope().$apply();
			});
		});
		app.controller("detail",function($scope){
			$scope.comments = [];
			$scope.appendComment=function(commodityID){
				if($scope.comment){
					ajaxModule.addComment(commodityID,$scope.comment,function(){
						$scope.comments.push({
							userName:"${name}",
							comment:$scope.comment
						});
						$scope.$apply();
					});
				}
			}
		});
		
		var ajaxModule = {
			getAllCom:function(cb){
				$.post("./getAllCom.html",cb);
			},
			addOrder:function(userId,commodityIds,cb){
				$.post("./addOrder.html",{userId:userId,commodityIds:commodityIds,
					commodityCounts:"1"},function(res){
						console.log("addOrder.html response is "+res);
						if(res){
							alert("successful");
						}else{
							alert("failed");
						}
					});
			},
			getCommentById:function(id,cb){
				$.post("./getCommentById.html",{commodityId:id},cb);
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
		function index(){
// 			ajaxModule.getAllCom(function(res){
// 				var scope = angular.element('#index').scope();
// 				scope.coms = res;
// 				console.log(scope.coms)
// 				scope.$apply();
// // 				$("#index").scope().coms = res;
// //   				$("#index").scope().$apply();
// 			});
		}
		
		$(function(){
			index();
		});
	</script>
</body>
</html>