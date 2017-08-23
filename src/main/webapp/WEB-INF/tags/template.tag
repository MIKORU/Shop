<%@tag description="template page" pageEncoding="UTF-8" language="java"
	trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="this is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Alice</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/static/img/shop.ico">
<jsp:invoke fragment="header" />
</head>
<body ng-app="app">
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="row">
				<ul class="nav navbar-nav">
					<li><c:if test="${name!=null }">
							<a href="###"> 欢迎 【${name}】 </a>
						</c:if></li>
					<li><c:if test="${name==null }">
							<a href="###">登录 </a>
						</c:if></li>
					<li><a href="./index">首页</a></li>
					<li><a href="./detail">分类页</a></li>
					<li><a href="./cart">购物车</a></li>
					<li><a href="./list">订单页</a></li>
					<li><a href="./user">用户信息</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="modal fade" id="detail" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true"> &times; </span>
					</button>
					<h4 class="modal-title" id="myModalLabel">登录</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="user">用户名</label> <input type="text"
								class="form-control" id="user" placeholder="user">
						</div>
						<div class="form-group">
							<label for="password">密码</label> <input type="password"
								class="form-control" id="password" placeholder="password">
						</div>
						<a href="###" id="submit" class="btn btn-default">登录</a>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:doBody />
	<jsp:invoke fragment="footer" />
</body>
</html>