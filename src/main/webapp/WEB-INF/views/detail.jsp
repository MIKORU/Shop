<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>分类页</title>
<link rel="stylesheet" type="text/css"
	href="./bootstrap/css/bootstrap.min.css" />
<script src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/angular.js/1.3.15/angular.min.js"></script>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<style>
.commodity {
	margin: 10px;
}

.content {
	margin: 80px;
}

.commentBody {
	max-heiht: 200px;
}
#com_panel{
	width: 260px;
}
</style>
</head>
<body ng-app="app">

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="row">
				<ul class="nav navbar-nav">
					<li><c:if test="${name!=null}">
							<a href="###"> 欢迎【${name}】 </a>
						</c:if></li>
					<li><a href="./index.html">首页</a></li>
					<li><a href="./detail.html">分类页</a></li>
					<li><a href="./cart.html">购物车</a></li>
					<li><a href="./list.html">列表页</a></li>
					<li><a href="./user.html">用户信息</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container content">
		<div class="row">
			<p>
				<span class="glyphicon glyphicon-cd" aria-hidden="true"></span> <input
					class="form-control" id="keyword" placeholder="输入搜索关键词"><br>
				<button id="search" class="btn btn-primary">搜索</button>
			</p>
		</div>
		<div id="groups" class="row" ng-controller="groups">
			<div ng-repeat="coms in groups track by $index">
				<h3>
					<span class="glyphicon glyphicon-fire" aria-hidden="true"></span> <span
						class="label label-default"> {{coms[0].type}} </span>
				</h3>
				<div class="panel panel-default pull-left commodity" id="com_panel"
					ng-repeat="com in coms track by $index">
					<div class="panel-heading panel-primary">
						<p>{{com.name}}</p>
					</div>
					<div class="panel-body">
						<p>产品介绍：{{com.depict}}</p>
						<p>厂商：{{com.manufacturer}}</p>
						<p>金额：{{com.price}}</p>
						<p>
							产品图片：<img ng-src={{com.img}} width=50 height=50 />
						</p>
						<button class="btn btn-default" ng-click="addToCart(com.id)">
							添加到购物车</button>
						<button class="btn btn-default" ng-click="showDetail(com)">
							查看详细</button>
					</div>
				</div>
				<div class="clearfix "></div>
			</div>
		</div>
		<div class="row">
			<a href="./cart.html" class="btn btn-default" role="button">结账</a>
		</div>
	</div>

	<div class="modal fade" id="detail" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" ng-controller="detail">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">产品信息</h4>
				</div>
				<div class="modal-body">
					<p>产品名字:{{com.name}}</p>
					<p>产品描述:{{com.depict}}</p>
					<p>产品公司:{{com.manufacturer}}</p>
					<p>产品金额:{{com.price}}</p>
					<p>产品数量：{{com.amount}}</p>
					<p>
						产品缩略图:<img ng-src={{com.img}} width=50 height=50 />
					</p>
					<div class="commentBody">
						<div ng-repeat="c in comments track by $index">
							<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
							{{c.username}}
							<div class="alert" role="alert">{{c.comment}}</div>
						</div>
						<form>
							<label for="text"></label>
							<input type="text" name="text"
								id="text" placehoder="评论内容" ng-model="comment" />
							<button id="submit" class="btn btn-success"
								ng-click="appendComment('${name}',com.id)">评论</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/detail.js"></script>
</html>























