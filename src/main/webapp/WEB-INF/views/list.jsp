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
<title>订单页</title>
<link rel="stylesheet" type="text/css" href ="./css/bootstrap.min.css" />
	<script src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/angular.js/1.3.15/angular.min.js"></script>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<style>
    	.list{
			margin:20px;
		}
		.margin80{
			margin-top:80px;
		}
    </style>
</head>
<body ng-app="app">
	<nav class="navbar navbar-default navbar-fixed-top">
	  <div class="container">
			<ul class="nav navbar-nav">
				<li>
					<c:if test="${name!=null}">
						<a href="###">
							欢迎【${name}】
						</a>
					</c:if>
				</li>
	           <li><a href="./index.html">首页</a></li>
	           <li><a href="./detail.html">类型页</a></li>
	           <li><a href="./cart.html">购物车</a></li>
	           <li><a href="./user.html">用户信息</a></li>
	         </ul>
	  </div>
	</nav>
	<div id="form" class="container margin80" ng-controller="form">
		<div class="row">
			<h1>未支付列表</h1>
			<ul class="list-group">
				<li class="list-group-item" ng-repeat="item in items" ng-if="item.pay==0">
						<p>
							<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
							收货人地址{{item.address}}
						</p>
						<p>
							<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
							收货人手机{{item.phone}}
						</p>
						<p>
							<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
							商品总价格{{item.totalprice}}
						</p>
						<div parse id={{item.orderlist}}>
							<div class="list pull-left" ng-repeat="order in  orderlist track by $index">
			        			<div commodity-directive id="{{order.commodityId}}">
			        				<p>商品名{{res.name}}</p>
			        				<p>商品描述{{res.depict}}</p>
			        				<p>商品厂商{{res.manufacturer}}</p>
			        				<p>商品价格{{res.price}}</p>
			        				<p>商品logo<img ng-src={{res.img}}  width=50 height=50/></p>
				        			<p>
				        				购买的商品个数
					        			<span class="badge">
					        				{{order.commodityCount}}
										</span>
				        			</p>
			        			</div>
			        		</div>
		        		</div>
		        		<button class="btn btn-default" ng-click="pay(item.id)"> 支付 </button>
	        		<div class="clearfix"></div>
				</li>
			</ul>
			<br>
			<h1>已支付列表</h1>
			<ul>
				<li class="list-group-item" ng-repeat="item in items track by $index" ng-if="item.pay==1">
						<p>
							<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
							收货人地址{{item.address}}
						</p>
						<p>
							<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
							收货人手机{{item.phone}}
						</p>
						<p>
							<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
							商品总价格{{item.totalprice}}
						</p>
						<div parse id={{item.orderlist}}>
							<div class="list pull-left" ng-repeat="order in  orderlist track by $index">
			        			<div commodity-directive id="{{order.commodityId}}">
			        				<p>商品名{{res.name}}</p>
			        				<p>商品描述{{res.depict}}</p>
			        				<p>商品厂商{{res.manufacturer}}</p>
			        				<p>商品价格{{res.price}}</p>
			        				<p>商品logo<img ng-src={{res.img}}  width=50 height=50/></p>
				        			<p>
				        				购买的商品个数
					        			<span class="badge">
					        				{{order.commodityCount}}
										</span>
				        			</p>
			        			</div>
			        		</div>
		        		</div>
	        		<div class="clearfix"></div>
				</li>
			</ul>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/list.js"></script>		
</body>
</html>