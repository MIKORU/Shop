<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = 
request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href ="./bootstrap/css/bootstrap.min.css" />
	<script src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="http://cdn.bootcss.com/angular.js/1.3.15/angular.min.js"></script>
</head>
<title>Insert title here</title>
<body ng-app="app">
	<div class="container">
		<div class="row">
			<ul class="nav navbar-nav">
				<li>
					<c:if test="${name!=null}">
						<a href="###">
							欢迎${name }
						</a>
					</c:if>
				</li>
				<li>
					<a href="./index.do">首页</a>
				</li>
				<li>
					<a href="./detail.do">类型页</a>
				</li>
				<li>
					<a href="./cart.do">购物车</a>
				</li>
				<li>
					<a href="./list.do">订单页</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<c:if test="${name===null }">
				<a href="login.do">登录</a>
			</c:if>
			<c:if test='${role==1 }'>
				<a class="btn btn-default" hre="admin/admin.do">进入管理界面</a>
			</c:if>
		</div>
		<br/>
		<c:if test="${name!=null }">
		<div id="user" class="row" ng-controller="user">
			<button class="btn btn-success" ng-click="edit!=edit">
				{{edit ? "查看用户信息":"更新用户信息"}}
			</button>
			<br/>
			<br/>
			<div ng-show="!edit" ng-list="edit=false">
				<P>用户名：{{user.name}}</P>
				<p>默认收货地址:{{user.defaultAddress}}</p>
				<p>默认手机：{{user.defaultPhone}}</p>
				<p>默认邮箱地址：{{user.mail}}</p>
			</div>
			<div ng-show="edit">
				<form>
					<div class="form-group">
						<label for="name">用户名-</label>
						<input type="text" class="form-control" id="name"
							placeholder="name" ng-model="user.name">
					</div>
					<div class="form-group">
						<label for="password">密码</label>
						<input type="text" class="form-control" id="password"
							placeholder="Password" ng-model="user.password">
					</div>
					<div class="form-group">
						<label for="defaultAddress">默认收货地址</label>
						<input type="text" class="form-control" name="defaultAddress"
						id="defaultAddress" ng-model="user.defaultAddress">
					</div>
					<div class="form-group">
						<label for="defaultPhone">默认电话</label>
						<input type="text" class="form-control" name="defaultPhone"
							id="defaultPhone" ng-model="user.defaultPhone">
					</div>
					<div class="form-group">
						<label for="mail">用户邮箱</label>
						<input type="text" class="form-control" name="mail"
							id="mail" ng-model="user.mail">
					</div>
					<div>
						金额：{{user.money}}RMB
					</div>
						<button id="submit" type="submit" class="btn btn-default">Submit</button>
				</form>
				</div>
			</div>
			</c:if>
	</div>
	<script>
		var userId = "${id}";
		var app=angular.module("app",[]);
		
		app.controller("user",function($scope){
			$scope.user={
					name:"name",
					password:"passowrd",
					defaultAddress:"defaultAddress",
					defaultPhone:"defaultPhone",
					mail:"mail"
			}
		});
		
		$("#submit").click(function(ev){
			$.post("updateInfo.do",$.extend({},$("#user").scope().user,{userId:userId}),
					function(res){
						if(res){
							alert("Update Successful!");
						}else{
							alert("Update Failed");
						}
			});
		});
		$(function(){
			if(userId){
				$.post("getInfo.do",{userId:userId},function(res){
					var scope = $("#user").scope();
					scope.user = res;
					$("#user").scope().$apply();
				});
			}
		});
	</script>









</body>
</html>