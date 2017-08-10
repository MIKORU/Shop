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
<title>购物车</title>
<link rel="stylesheet" type="text/css" href ="./css/bootstrap.min.css" />
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
</head>
<style type="text/css">
	.commodity{
		margin:10px;
	}
	#cart{
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
						<a href="./list.html">列表页</a>
					</li>
					<li>
						<a href="./user.html">用户信息</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div id="cart" class="row" ng-controller="cart">
			<ul class="list-group">
				<li class="list-group-item pull-left commodity" ng-repeat="order in list">
					<p>商品</p>
					<div commodity-directive id="{{order.commodityId}}">
						<p>商品名{{res.name}}</p>
						<P>商品描述{{res.depict}}</P>
						<p>商品厂商{{res.manufacturer}}</p>
						<p>商品logo<img ng-src={{res.img}} width=50 height=50/></p>
						<p>
							购买商品个数
							<span class="badge">
								{{order.commodityCount}}
							</span>
							<p style="display:none">{{order.price = res.price}}</p>
						</p>
						<button class="btn btn-danger" ng-click="comCount(-1,order.commodityId)">
							少买一个
						</button>
						<button classs="btn btn-warning" ng-click="comCount(1,order.commodityId)">
							再来一个
						</button>
					</div>
				</li>
			</ul>
			<div class="clearfix">
				<p>总金额{{sum}}</p>
			</div>
			<input type="hidden" value="${id }" class="form-control" id="userId">
			<br/>
			<label class="clearfix">
				输入用户地址
				<input type="text" class="form-control" id="address">
 			</label>
 			<br/>
 			<label class="clearfix">
 				输入收货人手机
 				<input type="text" class="form-control" id="phone">
 			</label>
 			<br/>
 			<button id="sumbit" class="btn btn-success">
 				提交信息
 			</button>
		</div>
	</div>
	<script>
		var userId="${id}";
		var app=angular.module("app",[]);
		app.directive("commodityDirective",function(){
			return{
				restrict:"EA",
				scope:true,
				link:function($scope,$el,$iattrs){
					$.post("./getComById.html",{id:$iattrs.id},function(res){
						$scope.res=res[0];
						$scope.$apply();
					});
				}
			}
		});
		app.controller("cart",function($scope){
			var list=[];
			$scope.list=list;
			$scope.scope=0;
			$scope.comCount = function(constant,commodityId){
				$.each($scope.list,function(i,e){
					if(e.commodityId === commodityId){
						$scope.list[i].commodityCount+=constant;
					}
					if(e.commodityCount<=0){
						e.commodityCount=0;
					}
				});
				$scope.upDateSum();
			}
			$scope.upDateSum = function(){
				$scope.sum=util.calcSum($scope.list);
			}
		});
		var util={
			canlcSum:function(list){
				var sum = 0;
				$.each(list,function(i,e){
					sum+=(e.commodityCount*e.price);
				});
				return sum;
			}
		}
		if(userId){
			$.post("./getOrderList.html",{userId:userId},function(res){
				$("#cart").scope().list = JSON.prase(res);
				setInterval(function(){
					$("#cart").scope().upDateSum();
					$("#cart").scope().$apply();
				},1000);
			});
		}
		$("#submit").click(function(){
			$.post("./addForm.html",{
				userId:$("#userId").varl(),
				address:$("#address").val(),
				phone:$("#phone").val(),
				totalPrice:("#cart").scope().sum,
				orderList:JSON.stringify($("#cart").scope().list)
			},function(res){
				if(res === true){
					location.href="list.html";
				}else{
					alert("Buy Failed");
				}
			});
		});
	</script>
</body>
</html>