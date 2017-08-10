<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>登录页</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
     <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css" /> 
    <script src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

  </head>
  <body>
  	<div class="container">
  		<div class="row">
		  	<form>
			  <div class="form-group">
			    <label for="user">用户名</label>
			    <input type="text" class="form-control" id="user" placeholder="user">
			  </div>
			  <div class="form-group">
			    <label for="password">密码</label>
			    <input type="password" class="form-control" id="password" placeholder="password">
			  </div>
			  <a href="###" id="submit" class="btn btn-default">登录</a>
			</form>
  		</div>
  	</div>
  	<script>
		$("#submit").click(function() {
			$.post("login.do", {
				username : $("#user").val(),
				password : $("#password").val()
			}, function(res) {
				if (res === "true") {
					location.href = "index.do";
				} else {
					alert("登陆失败");
				}
			});
		});
  	</script>
  </body>
</html>
