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
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="http://cdn.bootcss.com/bootstrap-table/1.9.1/bootstrap-table.min.js"></script>
<script
	src="http://cdn.bootcss.com/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/template.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
<link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/static/img/shop.ico">

<jsp:invoke fragment="header" />
<style>
#login-body {
	margin: 0 auto;
	width: 300px;
}

#content-login {
	margin: 0 auto;
	height: 300px;
	width: 400px;
}
#drop-down{
	margin: 5px;
}
</style>
</head>
<body ng-app="app">
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="row">
				<ul class="nav navbar-nav">
					<li><shiro:user>
							<div class="btn-group" id="drop-down">
								<button class="btn btn-defualt">
									欢迎 
									<shiro:principal /></button>
								<button class="btn btn-defualt dropdown-toggle"
									data-toggle="dropdown">
									 <span class="caret"></span></button>
								<ul class="dropdown-menu" role="menu">
									<li><a href="./user">个人中心</a></li>
									<li><c:if test='${role==1}'>
										<a href="./admin">管理界面</a>
									</c:if></li>
									<li class="divider"></li>
									<li><a href="###" id="logouts">退出</a></li>
								</ul>
							</div>
						</shiro:user></li>
					<li><shiro:guest>
							<a href="###" id="login">登录 </a>
						</shiro:guest></li>
					<li><a href="./index">首页</a></li>
					<li><a href="./detail">分类页</a></li>
					<li><a href="./cart">购物车</a></li>
					<li><a href="./list">订单页</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="modal fade" id="loginlabel" tabindex="-1" role="dialog"
		aria-labelledby="myLoginLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" id="content-login">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true"> &times; </span>
					</button>
					<h4 class="modal-title" id="myLoginLabel">登录</h4>
				</div>
				<div class="modal-body" id="login-body">
					<form>
						<div class="form-group">
							<label for="tuser">用户名</label> <input type="text"
								class="form-control" id="tuser" placeholder="user">
						</div>
						<div class="form-group">
							<label for="tpassword">密码</label> <input type="password"
								class="form-control" id="tpassword" placeholder="password">
						</div>
						<a href="###" id="submits" class="btn btn-default">登录</a>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:doBody />
	<jsp:invoke fragment="footer" />
</body>
</html>