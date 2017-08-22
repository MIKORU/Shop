<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:template>
<jsp:attribute name="header">
<style>
.com {
	margin: 20px;
	overflow: hidden;
}

.padding20 {
	padding: 20px;
}

#index {
	margin-top: 100px;
}
.caption{
	width: 220px;
}
</style>
</jsp:attribute>
<jsp:attribute name="footer">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/index.js"></script>
</jsp:attribute>
<jsp:body >
	<div class="container">
		<div id="index" class="row" ng-controller="index">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="thumbnail pull-left com"
						ng-repeat="com in coms track by $index">
						<img ng-src="{{com.img}}" width="140" height="40">
						<div class="caption" >
							<h3>{{com.name}}</h3>
							<p>{{com.depict}}</p>
							<p>
								<a href="#" class="btn btn-primary" role="button"
									ng-click="addToCart(com.id)">添加商品</a>
							</p>
							<p>
								<button class="btn btn-default" ng-click="showDetail(com)">
									查看详细</button>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<a href="./cart" class="btn btn-default" role="button">购物车</a>
		</div>
	</div>
	</div>
	<div class="modal fade" id="detail" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" ng-controller="detail">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true"> &times; </span>
					</button>
					<h4 class="modal-title" id="myModalLabel">产品信息</h4>
				</div>
				<div class="modal-body">
					<p>产品名字：{{com.name}}</p>
					<p>产品描述：{{com.depict}}</p>
					<p>产品公司：{{com.manufacturer}}</p>
					<p>产品金额：{{com.price}}</p>
					<p>产品数量：{{com.amount}}</p>
					<p>
						产品缩略图：<img ng-src={{com.img}} width=300 height=250 />
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
</jsp:body>
</tags:template>